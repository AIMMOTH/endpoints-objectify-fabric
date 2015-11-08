package fabric.entity;

import java.util.List;

import com.google.appengine.api.datastore.Email;
import com.google.appengine.api.datastore.Link;
import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
@Cache
public class EntityPrimitives {

  public enum Radio {
    value1, value2
    ;
  }
  
  @Id
  public Long entityId;
  public Boolean flag;
  public String title;
  public Integer nof;
  public Double price;
  public Long created; // Default long to date input
  public Email email;
  public Link link;
  public Radio type; // Default enums to radio buttons
  
  public List<Boolean> flags;
  public List<String> texts;
  public List<Integer> numbers;
  public List<Double> prices;
  public List<Long> dates;
  public List<Email> emails;
  public List<Link> links;
  public List<Radio> types;
}
