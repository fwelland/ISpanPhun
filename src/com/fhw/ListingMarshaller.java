package com.fhw;
import org.infinispan.protostream.MessageMarshaller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ListingMarshaller 
    implements MessageMarshaller<Listing>
{

    @Override
    public Listing readFrom(ProtoStreamReader reader) throws IOException
    {
        Listing l = new Listing(); 
        l.setListingId(reader.readLong("ListingId"));
        l.setTitle(reader.readString("title"));
        l.setPropertyType(reader.readString("propertType")); 
        l.
    }

    @Override
    public void writeTo(ProtoStreamWriter writer, Listing t) throws IOException
    {
        writer.writeLong("listingId", t.getListingId());
        writer.writeString("title", t.getTitle());
        writer.writeString("propertyType", t.getPropertyType());
        writer.writeString("city", t.getCity());
        writer.writeString("state", t.getState());
        writer.writeString("zip", t.getZip());        
        writer.writeLong("price", t.getPrice()); 
    }

    @Override
    public Class<? extends Listing> getJavaClass()
    {
        return(Listing.class); 
    }

    @Override
    public String getTypeName()
    {
        return("Listing"); 
    }
}



//   @Override
//   public User readFrom(ProtoStreamReader reader) throws IOException {
//      int id = reader.readInt("id");
//      List<Integer> accountIds = reader.readCollection("accountIds", new ArrayList<Integer>(), Integer.class);
//
//      String surname = reader.readString("surname");
//      String name = reader.readString("name");
//
//      List<Address> addresses = reader.readCollection("addresses", new ArrayList<Address>(), Address.class);
//
//      Integer age = reader.readInt("age");
//      User.Gender gender = reader.readObject("gender", User.Gender.class);
//
//      User user = new User();
//      user.setId(id);
//      user.setAccountIds(accountIds);
//      user.setName(name);
//      user.setSurname(surname);
//      user.setAge(age);
//      user.setGender(gender);
//      user.setAddresses(addresses);
//      return user;
//   }
//
//   @Override
//   public void writeTo(ProtoStreamWriter writer, User user) throws IOException {
//      writer.writeInt("id", user.getId());
//      writer.writeCollection("accountIds", user.getAccountIds(), Integer.class);
//      writer.writeString("name", user.getName());
//      writer.writeString("surname", user.getSurname());
//      writer.writeCollection("addresses", user.getAddresses(), Address.class);
//      writer.writeInt("age", user.getAge());
//      writer.writeObject("gender", user.getGender(), User.Gender.class);
//   }
//}
