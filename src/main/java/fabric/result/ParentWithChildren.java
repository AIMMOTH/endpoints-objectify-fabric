package fabric.result;

import java.util.List;

/**
 * Wraps a parent Datastore entity and a list of different entities. All have same id.
 * 
 * @author Carl
 *
 */
public class ParentWithChildren {

  /**
   * A parent entity with the same id as its children.
   */
  public Object parent;
  /**
   * Different entities with the same id as the parent.
   */
  public List<Object> children;
  
  ParentWithChildren() {}
  
  public ParentWithChildren(Object parent, List<Object> children) {
    this.parent = parent;
    this.children = children;
  }

}
