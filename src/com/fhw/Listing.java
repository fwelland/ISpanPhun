package com.fhw;

import java.io.Serializable;

public class Listing 
    implements Serializable
{
    private Long listingId; 
    private String description; 
    private String propertyType; 
    private String city; 
    private String state;
    private String zip; 
    private Long price; 

    
    public Listing() {} 
    
    public Long getListingId()
    {
        return listingId;
    }

    public Listing setListingId(Long listingId)
    {
        this.listingId = listingId;
        return(this);         
    }

    public String getDescription()
    {
        return description;
    }

    public Listing setDescription(String description)
    {
        this.description = description;
        return(this);         
    }

    public String getPropertyType()
    {
        return propertyType;
    }

    public Listing setPropertyType(String propertyType)
    {
        this.propertyType = propertyType;
        return(this); 
    }

    public String getCity()
    {
        return city;
    }

    public Listing setCity(String city)
    {
        this.city = city;
        return(this); 
    }

    public String getState()
    {
        return state;
    }

    public Listing setState(String state)
    {
        this.state = state;
        return(this); 
    }
    
    public String getZip()
    {
        return zip;
    }

    public Listing setZip(String zip)
    {
        this.zip = zip;
        return(this); 
    }

    public Long getPrice()
    {
        return price;
    }

    public Listing setPrice(Long price)
    {
        this.price = price;
        return(this); 
    }
    
    @Override
    public String toString()
    {
        return( new StringBuilder().append("lid:  ").append(price).toString());
    }        
}
