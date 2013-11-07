package com.fhw;
import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.search.annotations.*;
import org.infinispan.protostream.BaseMessage;

@XmlRootElement(name="Listing")
@Indexed
public class Listing 
    extends BaseMessage
    implements Serializable
{
    @Field private Long listingId;     
    @Field private String title; 
    @Field private String propertyType; 
    @Field private String city; 
    @Field private String state;
    @Field private String zip; 
    @Field private Long price; 
    
    private transient List<ListingValue> listingValues; 
    
    public Listing() {} 
    
    @XmlElement(name="ListingId")
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

    @XmlElementWrapper(name="ListingValues")
    @XmlElement(name="ListingValue")
    public List<ListingValue> getListingValues()
    {
        return listingValues;
    }

    public void setListingValues(List<ListingValue> listingValues)
    {
        this.listingValues = listingValues;
    }
    
    
    public void initializeValues()
    {
        for(ListingValue lv : listingValues)
        {
            Long tid = lv.getTemplateAttributeId(); 
            switch(tid.intValue())
            {
                case 4652:
                case 4653:
                case 3332:
                case 2909:
                case 2911:
                case 2912:
                case 2913:
                case 2914:
                case 2915:
                case 2916:
                case 2918:
                case 2933:
                case 4370:    
                            setPropertyType(lv.getStringValue());
                break;

                case 4656:
                case 4657:
                case 3333:
                case 2815:
                case 2817:
                case 2818:
                case 2819:
                case 2820:
                case 2821:
                case 2822:
                case 2824:
                case 2836:
                case 4371:      
                    setTitle(lv.getStringValue()); 
                break; 
                    
                case 4687:
                case 4688:
                case 3341:
                case 1010:
                case 1094:
                case 1138:
                case 1182:
                case 1226:
                case 1270:
                case 1314:
                case 1402:
                case 2710:
                case 4379:                    
                    setPrice(lv.getLongNumberValue()); 
                break;
                    
                case 4711:
                case 4712:
                case 3351:
                case 1008:
                case 1092:
                case 1136:
                case 1180:
                case 1224:
                case 1268:
                case 1312:
                case 1400:
                case 4393:
                    ListingValueAddress lva = lv.getAddress(); 
                    if(null != lva)
                    {
                        setCity(lva.getCity());
                        setState(lva.getState());
                        setZip(lva.getZip()); 
                    }
                break;  
                    
            }
        }
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









