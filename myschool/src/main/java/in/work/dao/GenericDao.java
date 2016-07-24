package in.work.dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;

public interface GenericDao<E extends Serializable, PK extends Serializable> {
  EntityManager getEntityManager();
  
  void create(E entity);
  
  E edit(E entity);
  
  E findByKey(PK primaryKey);
  
  List<E> findAll();
  
  List<E> findRange(int[] range);
  
  E getReferenceByKey(PK primaryKey);
  
  long count();
  
  void removeByKeys(PK... primaryKeys);
}
