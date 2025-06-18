package pendaftaran.controller;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import pendaftaran.service.StudentService;
import pendaftaran.model.StudentModel;

import java.util.List;

public class StudentController {

    private String name;
    private String address;
    private String jurusan;
    private StudentService studentService;
    private StudentModel.Student selectedStudent;

    public StudentController() {
        studentService = new StudentService();  // Menghubungkan Controller dengan Service
    }

    // Getter and Setter for name, address, and jurusan
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getJurusan() {
        return jurusan;
    }

    public void setJurusan(String jurusan) {
        this.jurusan = jurusan;
    }

    // Command untuk menyimpan atau mengedit data mahasiswa
    @Command
    @NotifyChange({"students", "name", "address", "jurusan"})
    public void saveStudent() {
        if (name != null && !name.isEmpty() && address != null && !address.isEmpty() && jurusan != null && !jurusan.isEmpty()) {
            if (selectedStudent != null) {
                studentService.editStudent(selectedStudent, name, address, jurusan); // Edit student via Service
                selectedStudent = null;
            } else {
                studentService.addStudent(name, address, jurusan); // Add student via Service
            }
            name = "";
            address = "";
            jurusan = "";
        }
    }

    @Command
    @NotifyChange({"name", "address", "selectedStudent", "jurusan"})
    public void editStudent(@BindingParam("student") StudentModel.Student student) {
        this.name = student.getName();
        this.address = student.getAddress();
        this.jurusan = student.getJurusan();
        this.selectedStudent = student;
    }

    @Command
    @NotifyChange("students")
    public void deleteStudent(@BindingParam("student") StudentModel.Student student) {
        studentService.deleteStudent(student); // Delete student via Service
    }

    public List<StudentModel.Student> getStudents() {
        return studentService.getAllStudents(); // Get students from Service
    }

    public String getButtonLabel() {
        return selectedStudent != null ? "Update" : "Simpan";
    }
}
