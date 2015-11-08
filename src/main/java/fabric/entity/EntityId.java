package fabric.entity;

import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;


@Entity
@Cache
public class EntityId {

  @Id
  public Long id;
  
  EntityId() {}
}
