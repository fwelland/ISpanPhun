package com.fhw;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Listings")
public class Listings 
{
    private List<Listing> listings; 
    
    public Listings()
    {
        
    }

    @XmlElement(name="Listing")
    public List<Listing> getListings()
    {        
        if(null == listings)
            listings = new ArrayList<Listing>(); 
        
        return listings;
    }

    public void setListings(List<Listing> listings)
    {
        this.listings = listings;
    }

}
