package pendaftaran.service.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pendaftaran.entity.Student;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class StudentDao {

    @PersistenceContext
    private EntityManager em;

    @Transactional(readOnly = true)
    public List<Student> getAll() {
        Query query = em.createQuery("SELECT s FROM Student s");
        return query.getResultList();
    }

    @Transactional(readOnly = true)
    public Student get(Integer id) {
        return em.find(Student.class, id);
    }

    @Transactional
    public Student save(Student student) {
        if (student.getId() == null) {
            em.persist(student);
        } else {
            student = em.merge(student);
        }
        em.flush();
        return student;
    }

    @Transactional
    public void delete(Student student) {
        Student s = get(student.getId());
        if (s != null) {
            em.remove(s);
        }
    }

    @Transactional(readOnly = true)
    public boolean existsByNameIgnoreCase(String name) {
        Query query = em.createQuery("SELECT COUNT(s) FROM Student s WHERE LOWER(s.name) = LOWER(:name)");
        query.setParameter("name", name.trim());
        Long count = (Long) query.getSingleResult();
        return count > 0;
    }
}
