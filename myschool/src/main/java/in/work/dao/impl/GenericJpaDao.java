package in.work.dao.impl;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import in.work.dao.GenericDao;

public abstract class GenericJpaDao<E extends Serializable, PK extends Serializable> implements GenericDao<E, PK> {
  
  private Class<E> entityClass;
  
  public GenericJpaDao(Class<E> entityClass) {
    this.entityClass = entityClass;
  }
  
  public Class<E> getEntityClass() {
    return entityClass;
  }
  
  @Override
  public void create(E entity) {
    getEntityManager().persist(entity);
  }
  
  @Override
  public E edit(E entity) {
    return getEntityManager().merge(entity);
  }
  
  @Override
  public E findByKey(PK primaryKey) {
    return getEntityManager().find(entityClass, primaryKey);
  }
  
  @Override
  public E getReferenceByKey(PK primaryKey) {
    return getEntityManager().getReference(entityClass, primaryKey);
  }
  
  @Override
  public List<E> findAll() {
    CriteriaQuery<E> cq = getEntityManager().getCriteriaBuilder().createQuery(entityClass);
    cq.select(cq.from(entityClass));
    List<E> resultEntities = getEntityManager().createQuery(cq).getResultList();
    return resultEntities;
  }
  
  @Override
  public List<E> findRange(int[] range) {
    CriteriaQuery<E> cq = getEntityManager().getCriteriaBuilder().createQuery(entityClass);
    cq.select(cq.from(entityClass));
    Query query = getEntityManager().createQuery(cq);
    query.setMaxResults(range[1] - range[0]);
    query.setFirstResult(range[0]);
    List<E> resultEntities = query.getResultList();
    return resultEntities;
  }
  
  @Override
  public long count() {
    CriteriaQuery<Long> cq = getEntityManager().getCriteriaBuilder().createQuery(Long.class);
    Root<E> rt = cq.from(entityClass);
    cq.select(getEntityManager().getCriteriaBuilder().count(rt));
    Query query = getEntityManager().createQuery(cq);
    long resultCount = (long) query.getSingleResult();
    return resultCount;
  }
  
  @Override
  public void removeByKeys(PK... primaryKeys) {
    for (PK primaryKey : primaryKeys) {
      getEntityManager().remove(getEntityManager().getReference(entityClass, primaryKey));
    }
  }
}