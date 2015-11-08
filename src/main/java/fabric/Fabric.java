package fabric;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;

import fabric.datastore.Objectify;
import fabric.entity.EntityData;
import fabric.entity.EntityId;
import fabric.entity.EntityPrimitives;

@Api(
    name = "fabric",
    version = "v1"
    )
public class Fabric {
  
  /**
   * Create an EntityId with a POST.
   * 
   * @param entity
   * @return
   */
  @ApiMethod(httpMethod = "post")
  public IdResult postEntityId(EntityId entity) {
    Objectify.save().entity(entity).now();
    return new IdResult(entity.id);
  }
  
  /**
   * Returns all EntityId with a GET.
   * 
   * @return
   */
  @ApiMethod(httpMethod = "get")
  public List<EntityId> getEntityId() {
    return Objectify.load().type(EntityId.class).list();
  }

  /**
   * Create an EntityData with a POST. Returns its new id.
   * 
   * @param entity
   * @return
   */
  @ApiMethod(httpMethod = "post")
  public IdResult postEntityData(EntityData entity) {
    Objectify.save().entity(entity).now();
    return new IdResult(entity.entityId);
  }

  /**
   * Returns all EntityData with a GET.
   * 
   * @return
   */
  @ApiMethod(httpMethod = "get")
  public List<EntityData> getEntityData() {
    return Objectify.load().type(EntityData.class).list();
  }
  
  /**
   * Create an EntityPrimitive with a POST. Returns its new id.
   * 
   * @param entity
   * @return
   */
  @ApiMethod(httpMethod = "post")
  public IdResult postEntityPrimitives(EntityPrimitives entity) {
    Objectify.save().entity(entity).now();
    return new IdResult(entity.entityId);
  }
  
  /**
   * Get all EntityPrimitives with GET.
   * 
   * @return
   */
  @ApiMethod(httpMethod = "get")
  public List<EntityPrimitives> getEntityPrimitives() {
    return Objectify.load().type(EntityPrimitives.class).list();
  }

  /**
   * Loads from DataStore the kind specified in parameter 'parent'.
   * 
   * @param parent The name of the Kind's class name. 
   * @return Type FabricResult which wraps the result.
   * @throws ClassNotFoundException
   */
  @ApiMethod(httpMethod = "get", path = "{parent}")
  public EntityList getParent(@Named("parent") String parent) throws ClassNotFoundException {
    return new EntityList(Objectify.loadFromClass(Objectify.forName(parent)).list());
  }

  /**
   * Loads from DataStore the kind specified in parameter 'parent' with the specified id.
   * 
   * @param parent The name of the Kind's class name. 
   * @param id Id of the entity to fetch.
   * @return Type FabricResult which wraps the result.
   * @throws ClassNotFoundException
   */
  @ApiMethod(httpMethod = "get", path = "{parent}/{id}")
  public SingleEntity getParentById(@Named("parent") String parent, @Named("id") Long id) throws ClassNotFoundException {
    return new SingleEntity(Objectify.loadFromClass(Objectify.forName(parent), id).now());
  }

  /**
   * Loads specified parent kind and all specified children kinds with the same id. Example:
   * <pre>
   * localhost:8080/_ah/api/fabric/v1/entityid/4785074604081152/entitydata+entityprimitives
   * </pre>
   * 
   * @param parentName Name of the parent kind's class name.
   * @param id Id of all entities.
   * @param childrenInPath String separated by whitespace specifying all children kinds to load.
   * @return Type FabricResult which wraps the result.
   * @throws ClassNotFoundException
   */
  @ApiMethod(httpMethod = "get", path = "{parent}/{id}/{children}")
  public ParentWithChildren getParentByIdWithChildren(@Named("parent") String parentName, @Named("id") Long id, @Named("children") String childrenInPath) throws ClassNotFoundException {
    String[] children = childrenInPath.split("\\s");
    
    Object parent = Objectify.loadFromClass(Objectify.forName(parentName), id).now();
    
    List<Object> childrenEntities = new ArrayList<Object>();
    for (String child : children) {
      childrenEntities.add(Objectify.loadFromClass(Objectify.forName(child), id).now());
    }
    return new ParentWithChildren(parent, childrenEntities);
  }
}
