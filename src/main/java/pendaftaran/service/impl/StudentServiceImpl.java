package pendaftaran.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
// import pendaftaran.service.impl.StudentDao;
import pendaftaran.entity.Student;
import pendaftaran.service.StudentService;

import java.util.List;

@Service("studentService")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao dao;

    @Override
    public Student save(String name, String address, String jurusan) {
        Student student = new Student(name, address, jurusan);
        return dao.save(student);
    }

    @Override
    public Student update(Student student, String name, String address, String jurusan) {
        student.setName(name);
        student.setAddress(address);
        student.setJurusan(jurusan);
        return dao.save(student);
    }

    @Override
    public void delete(Student student) {
        dao.delete(student);
    }

    @Override
    public List<Student> getAll() {
        return dao.getAll();
    }

    @Override
    public boolean isDuplicateName(String name, Student except) {
        return dao.getAll().stream()
            .anyMatch(s -> s.getName().equalsIgnoreCase(name.trim())
                        && (except == null || !s.getId().equals(except.getId())));
    }
}
