package fabric.entity;

import com.google.appengine.api.datastore.Text;
import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
@Cache
public class EntityData {

  @Id
  public Long entityId; // Same as Entity.class
  public Text description;
}
