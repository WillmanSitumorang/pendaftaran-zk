package pendaftaran.service;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import pendaftaran.model.Student;

public class PendaftaranService {

    private List<Student> students = new ArrayList<>();
    private static AtomicInteger idGen = new AtomicInteger(1);

    public List<Student> getAllStudents() {
        return students;
    }

    public void addStudent(String name, String jurusan, String address) {
        students.add(new Student(idGen.getAndIncrement(), name, jurusan, address));
    }

    public void updateStudent(Student student, String name, String jurusan, String address) {
        student.setName(name);
        student.setJurusan(jurusan);
        student.setAddress(address);
    }

    public void deleteStudent(Student student) {
        students.remove(student);
    }
}
