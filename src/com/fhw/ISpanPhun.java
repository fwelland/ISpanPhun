package com.fhw;

import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.client.hotrod.configuration.ConfigurationBuilder;

public class ISpanPhun
{    
    private RemoteCacheManager cacheManager;
    private RemoteCache<Long, Listing> cache;    
    
    public ISpanPhun()
    { }

    public static void main(String[] args)
    {
        System.out.println("Here we go to play with inifini span");                
        new ISpanPhun().go();         
    }    
        
    private void connect() 
    {
        ConfigurationBuilder builder = new ConfigurationBuilder();
        builder.addServer().host("localhost").port(11222);
        cacheManager = new RemoteCacheManager(builder.build());
        cache = cacheManager.getCache("Listings", true);
        System.out.println("ok I connected and created a cache....is my cache null?  " + (null == cache) ); 
    }
    
    private void go()
    {
        connect(); 
        //addListing(1L, "fred", "type", "city", "state", "44444", 334L);
        getListing();
        disconnect(); 
    }
    
    private void getListing()
    {
        Listing l = cache.get(1L);
        System.out.println(l.toString()); 
    }
    
    public void disconnect()
    {
        cacheManager.stop();
    }
    
    
    
    private void addListing(Long lid, String desc, String pType, String city, String state, String zip, Long price)
    {
        Listing l = new Listing().
                setListingId(lid).
                setDescription(desc).
                setPropertyType(pType).
                setCity(city).
                setState(state).
                setZip(zip).
                setPrice(price);                   
        cache.put(lid, l); 
    }
    
}
