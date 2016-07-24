package in.work.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import in.work.dao.MemberDao;
import in.work.model.Member;
import org.springframework.stereotype.Repository;

@Repository
public class MemberJpaDao extends GenericJpaDao<Member, Long> implements MemberDao {
  @PersistenceContext(unitName = "mainPU")
  private EntityManager entityManager;
  
  @Override
  public EntityManager getEntityManager() {
    return entityManager;
  }
  
  public MemberJpaDao() {
    super(Member.class);
  }
}