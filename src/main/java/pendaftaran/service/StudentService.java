package pendaftaran.service;

import pendaftaran.entity.Student;
import java.util.List;

public interface StudentService {
    Student save(String name, String address, String jurusan);
    Student update(Student student, String name, String address, String jurusan);
    void delete(Student student);
    List<Student> getAll();
    boolean isDuplicateName(String name, Student except);
}
