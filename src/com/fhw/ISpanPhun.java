package com.fhw;

import java.io.InputStream;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.client.hotrod.configuration.ConfigurationBuilder;
import org.infinispan.client.hotrod.Search; 
import org.infinispan.client.hotrod.marshall.ProtoStreamMarshaller;
import org.infinispan.query.dsl.QueryFactory ; 
import org.infinispan.query.dsl.Query; 

public class ISpanPhun
{    
    private RemoteCacheManager cacheManager;
    private RemoteCache<Long, Listing> cache;    
    
    public ISpanPhun()
    { }
    
    public static void main(String[] args)
    {
        //String myargs[] = {"GET", "43971"};
        String myargs[] = {"search", "foo"};        
        new ISpanPhun().go(myargs);         
    }    
        
    private void connect() 
    {
        ConfigurationBuilder builder = new ConfigurationBuilder();
        builder.addServer().host("localhost").port(11222).marshaller(new ProtoStreamMarshaller());
        cacheManager = new RemoteCacheManager(builder.build());
        cache = cacheManager.getCache("Listings", true);
    }
    
    private void go(String args[])
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
    {
        try
        {
            JAXBContext context = JAXBContext.newInstance(Listings.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("com/fhw/Listings.xml");
            Listings ls = (Listings)unmarshaller.unmarshal(is); 
            for(Listing l : ls.getListings())
            {
                l.initializeValues();
                cache.put(l.getListingId(), l); 
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
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
            
    private void addListing(Long lid, String desc, String pType, String city, String state, String zip, Long price)
    {
        Listing l = new Listing().
                setListingId(lid).
                setTitle(desc).
                setPropertyType(pType).
                setCity(city).
                setState(state).
                setZip(zip).
                setPrice(price);                   
        cache.put(lid, l); 
    }    
}



