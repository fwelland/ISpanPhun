package com.fhw;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import org.hibernate.search.cfg.SearchMapping;
import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.client.hotrod.configuration.ConfigurationBuilder;
import org.infinispan.client.hotrod.Search; 
import org.infinispan.client.hotrod.marshall.ProtoStreamMarshaller;
import org.infinispan.query.dsl.QueryFactory ; 
import org.infinispan.query.dsl.Query; 
import org.infinispan.query.remote.ProtobufMetadataManager;

public class ISpanPhun
{    
    private static final String JMX_DOMAIN = ProtobufMetadataManager.class.getSimpleName();    
    private static final String PROTOBIN_LOC = "com/fhw/Listing.protobin";
    
    private RemoteCacheManager cacheManager;
    private RemoteCache<Long, Listing> cache;    
    
    public ISpanPhun()
    { }
    
    public static void main(String[] args)
    {
        //String myargs[] = {"GET", "43971"};
        String myargs[] = {"search", "foo"};  
        //String myargs[] = {"load"};  
        
        try
        {
            new ISpanPhun().go(myargs);
            System.out.println("DONE!"); 
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }    
    
        
    private void connect() 
            throws Exception
    {
        ClassLoader cl =  Thread.currentThread().getContextClassLoader();            
        ConfigurationBuilder builder = new ConfigurationBuilder();
        
        SearchMapping mapping = new SearchMapping(); 
        mapping.entity(Listing.class);        
        Properties props = new Properties(); 
        props.put(org.hibernate.search.Environment.MODEL_MAPPING, mapping);
        builder.addServer().host("localhost").port(11222).marshaller(new ProtoStreamMarshaller()).withProperties(props);
        cacheManager = new RemoteCacheManager(builder.build());       
        ProtoStreamMarshaller.getSerializationContext(cacheManager).registerProtofile(cl.getResourceAsStream(PROTOBIN_LOC));        
        ProtoStreamMarshaller.getSerializationContext(cacheManager).registerMarshaller(Listing.class, new ListingMarshaller());                    
        cache = cacheManager.getCache("Listings", true);
                               
        InputStream is = cl.getResourceAsStream(PROTOBIN_LOC);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        int len;
        while ((len = is.read(buf)) != -1)
        {
            os.write(buf, 0, len);
        }
        is.close();
        byte[] descriptor = os.toByteArray();         
        //String otherMbeanName = "jboss.infinispan:type=RemoteQuery,name=\"local\",component=ProtobufMetadataManager"; 
        
        
        String urlString = System.getProperty("jmx.service.url","service:jmx:remoting-jmx://localhost:9999");
        JMXServiceURL serviceURL = new JMXServiceURL(urlString);
        JMXConnector jmxConnector = JMXConnectorFactory.connect(serviceURL, null); 
        MBeanServerConnection mbs = jmxConnector.getMBeanServerConnection(); 
        
        String otherMbeanName = "jboss.infinispan:type=RemoteQuery,name=\"local\",component=ProtobufMetadataManager";         
        System.out.println("locating:  " + otherMbeanName + " .....");
        ObjectName objName = new ObjectName(otherMbeanName);
        mbs.invoke(objName, "registerProtofile", new Object[]{descriptor}, new String[]{byte[].class.getName()});
        jmxConnector.close();
    }
    
    private void go(String args[])
        throws Exception
    {        
        String cmd = args[0];         
        connect(); 
        
        if("load".equalsIgnoreCase(cmd))        
        {
            loadListings();
        }
        else if("get".equalsIgnoreCase(cmd))
        {
            Long lid = null; 
            try
            {
                lid = Long.parseLong(args[1]);
            } 
            catch(Exception e)
            {
                badArgs();
            }
            if(null != lid)
            {
                getListing(lid);
            }
        }
        else if("search".equalsIgnoreCase(cmd))
        {
            search(args[1]);
        }
        else
        {
            badArgs();
        }                        
        disconnect(); 
    }
    
    private void badArgs()
    {
        System.err.println("bad args -- do one of: ");
        System.err.println("load");
        System.err.println("get <listing-id>");
        System.err.println("search \"searchstring\"");        
    }
    
    private void search(String srchString)
    {
        QueryFactory qf =  Search.getQueryFactory(cache);         
        Query q = qf.from(Listing.class).having("state").eq("NY").toBuilder().build();
        List<Listing> results = q.list(); 
        for(Listing l : results)
        {
            System.out.println(l); 
        }
    }
    
    private void loadListings()
        throws Exception
    {
        ClassLoader cl =  Thread.currentThread().getContextClassLoader();            
        JAXBContext context = JAXBContext.newInstance(new Class[] {JAXBLoadableListing.class, Listings.class, ListingValue.class, ListingValueAddress.class});
        Unmarshaller unmarshaller = context.createUnmarshaller();
        InputStream is = cl.getResourceAsStream("com/fhw/Listings.xml");
        Listings ls = (Listings)unmarshaller.unmarshal(is); 
        int ii=0; 
        System.out.println("Loading....."); 
        for(JAXBLoadableListing l : ls.getListings())
        {
            Listing ll = l.makeListing();
            cache.put(ll.getListingId(), ll); 
            ii++;
            if((ii % 20) == 0)
            {
                System.out.println("done about " + ii  + " Listings");
            }                
        }
        System.out.println("loadded " + ii + " listings"); 
    }
    
    private void getListing(Long lid)
    {
        Listing l = cache.get(lid);
        System.out.println(l); 
    }
    
    public void disconnect()
    {
        cacheManager.stop();
    }           
}



