package pendaftaran.model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class StudentModel {

    private List<Student> students;
    private static AtomicInteger idGenerator = new AtomicInteger(1);

    public StudentModel() {
        students = new ArrayList<>();
    }

    public void addStudent(String name, String address, String jurusan) {
        students.add(new Student(idGenerator.getAndIncrement(), name, address, jurusan));
    }

    public void editStudent(Student student, String name, String address, String jurusan) {
        student.setName(name);
        student.setAddress(address);
        student.setJurusan(jurusan);
    }

    public void deleteStudent(Student student) {
        students.remove(student);
    }

    public List<Student> getStudents() {
        return students;
    }

    public static class Student {
        private int id;
        private String name;
        private String address;
        private String jurusan;

        public Student(int id, String name, String address, String jurusan) {
            this.id = id;
            this.name = name;
            this.address = address;
            this.jurusan = jurusan;
        }

        // Getters and Setters
        public int getId() { return id; }
        public void setId(int id) { this.id = id; }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getAddress() { return address; }
        public void setAddress(String address) { this.address = address; }
        public String getJurusan() { return jurusan; }
        public void setJurusan(String jurusan) { this.jurusan = jurusan; }
    }
}
