package com.fhw;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="ListingValue")
public class ListingValue 
{
    private Long templateAttributeId;
    private Long listingValueId; 
    private Integer orderNumber;
    private String stringValue; 
    private Double numberValue; 
    private ListingValueAddress address; 

    @XmlElement(name="TemplateAttributeId")
    public Long getTemplateAttributeId()
    {
        return templateAttributeId;
    }

    public void setTemplateAttributeId(Long templateAttributeId)
    {
        this.templateAttributeId = templateAttributeId;
    }
    
    @XmlElement(name="OrderNumber")
    public Integer getOrderNumber()
    {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber)
    {
        this.orderNumber = orderNumber;
    }
    
    @XmlElement(name="StringValue")
    public String getStringValue()
    {
        return stringValue;
    }

    public void setStringValue(String value)
    {
        this.stringValue = value;
    }
    
    @XmlElement(name="ListingValueId")
    public Long getListingValueId()
    {
        return listingValueId;
    }

    public void setListingValueId(Long listingValueId)
    {
        this.listingValueId = listingValueId;
    }

    @XmlElement(name="NumberValue")
    public Double getNumberValue()
    {
        return numberValue;
    }

    public void setNumberValue(Double numberValue)
    {
        this.numberValue = numberValue;
    }
    
    public Long getLongNumberValue()
    {
        Long l = null; 
        if(null != numberValue)
        {
            l = numberValue.longValue(); 
        }
        return(l); 
    }

    @XmlElement(name="Address")
    public ListingValueAddress getAddress()
    {
        return address;
    }

    public void setAddress(ListingValueAddress address)
    {
        this.address = address;
    }
}
