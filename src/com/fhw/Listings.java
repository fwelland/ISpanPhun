package com.fhw;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Listings")
public class Listings 
{
    private List<JAXBLoadableListing> listings; 
    
    public Listings()
    {
        
    }

    @XmlElement(name="Listing")
    public List<JAXBLoadableListing> getListings()
    {        
        if(null == listings)
            listings = new ArrayList<JAXBLoadableListing>(); 
        
        return listings;
    }

    public void setListings(List<JAXBLoadableListing> listings)
    {
        this.listings = listings;
    }

}
