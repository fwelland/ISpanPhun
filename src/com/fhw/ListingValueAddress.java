package com.fhw;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Address")
public class ListingValueAddress 
{
    private String address;
    private String city;
    private String state; 
    private String zip;
    private Double latitude;
    private Double longitude; 

    @XmlElement(name="Address1")
    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }
    
    @XmlElement(name="City")
    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    @XmlElement(name="StateProvince")
    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }
    
    @XmlElement(name="PostalCode")
    public String getZip()
    {
        return zip;
    }

    public void setZip(String zip)
    {
        this.zip = zip;
    }
    
    @XmlElement(name="Latitude")
    public Double getLatitude()
    {
        return latitude;
    }

    public void setLatitude(Double latitude)
    {
        this.latitude = latitude;
    }

    @XmlElement(name="Longitude")
    public Double getLongitude()
    {
        return longitude;
    }

    public void setLongitude(Double longitude)
    {
        this.longitude = longitude;
    }            
}