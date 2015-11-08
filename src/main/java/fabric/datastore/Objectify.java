package fabric.datastore;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.LoadResult;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.cmd.Deleter;
import com.googlecode.objectify.cmd.LoadType;
import com.googlecode.objectify.cmd.Loader;
import com.googlecode.objectify.cmd.Saver;

import fabric.entity.EntityData;
import fabric.entity.EntityId;
import fabric.entity.EntityPrimitives;

public class Objectify {

  private static final List<Class<?>> classes = new ArrayList<Class<?>>();
  
  static {
    classes.add(EntityId.class);
    classes.add(EntityData.class);
    classes.add(EntityPrimitives.class);
    
    for (Class<?> className : classes)
      ObjectifyService.register(className);
  }

  public static Saver save() {
    return ofy().save();
  }

  public static Loader load() {
    return ofy().load();
  }

  public static Deleter delete() {
    return ofy().delete();
  }

  public static <T> LoadResult<T> loadFromClass(Class<T> className, Long id) {
    return Objectify.load().key(Key.create(className, id));
  }

  public static <T> LoadType<T> loadFromClass(Class<T> className) {
    return Objectify.load().type(className);
  }

  public static Class<?> forName(String name) {
    String lowerCaseName = name.toLowerCase();
    for (Class<?> className : classes) {
      if (className.getName().toLowerCase().endsWith(lowerCaseName)) {
        return className;
      }
    }
    throw new RuntimeException("Could not find lower cased class name '" + lowerCaseName.toLowerCase() + "'");
  }
}
