package com.fhw;
import java.io.Serializable;
import org.hibernate.search.annotations.*;
//import org.infinispan.protostream.BaseMessage;

@Indexed
public class Listing 
//    extends BaseMessage
    implements Serializable
{
    @Field private Long listingId;     
    @Field private String title; 
    @Field private String propertyType; 
    @Field private String city; 
    @Field private String state;
    @Field private String zip; 
    @Field private Long price; 
        
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
        return( new StringBuilder().
                append("LID: ").
                append(listingId).
                append(" TYPE: ").
                append(propertyType). 
                append(" TITLE: ").
                append(title).
                append(" CITY: ").
                append(city).
                append(" STATE: ").
                append(state).
                append(" ZIP: ").
                append(zip).
                append(" PRICE: ").
                append(price).toString());
    }        
    public String getTitle()
    {
        return title;
    }

    public Listing setTitle(String title)
    {
        this.title = title;
        return(this); 
    }
}
