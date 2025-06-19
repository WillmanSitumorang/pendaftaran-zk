package pendaftaran.service;

import java.util.List;

import pendaftaran.entity.StudentModel;

public class StudentService {

    private StudentModel studentModel;

    public StudentService() {
        studentModel = new StudentModel(); // Inisialisasi Model
    }

    public void addStudent(String name, String address, String jurusan) {
        studentModel.addStudent(name, address, jurusan);
    }

    public void editStudent(StudentModel.Student student, String name, String address, String jurusan) {
        studentModel.editStudent(student, name, address, jurusan);
    }

    public void deleteStudent(StudentModel.Student student) {
        studentModel.deleteStudent(student);
    }

    public List<StudentModel.Student> getAllStudents() {
        return studentModel.getStudents();
    }
}
