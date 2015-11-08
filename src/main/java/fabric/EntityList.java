package fabric;

import java.util.List;

import com.google.common.collect.Lists;

public class EntityList {

  public List<Object> entities;
  
  EntityList() {}
  
  public EntityList(List<?> entityList) {
    this.entities = Lists.newArrayList(entityList);
  }

}
