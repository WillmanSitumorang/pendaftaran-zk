package pendaftaran.acl.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pendaftaran.acl.entity.User;

@Repository
public class UserDao {

  @PersistenceContext
  private EntityManager em;

  @Transactional(readOnly = true)
  public User get(String username) throws NoResultException {
    Query query = em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class);
    query.setParameter("username", username);
    return (User) query.getSingleResult();
  }
}
