package pendaftaran.controller;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zul.Messagebox;

import pendaftaran.entity.StudentModel;
import pendaftaran.service.StudentService;

import java.util.List;

public class StudentController {

    private String name;
    private String address;
    private String jurusan;
    private StudentService studentService;
    private StudentModel.Student selectedStudent;

    public StudentController() {
        studentService = new StudentService();  // Hubungkan Controller dengan Service
    }

    // Getter dan Setter
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

    // Method untuk cek apakah nama sudah ada (kecuali yang sedang diedit)
    private boolean isDuplicateName(String name) {
        return studentService.getAllStudents().stream()
                .anyMatch(s -> s.getName().trim().equalsIgnoreCase(name.trim()) && s != selectedStudent);
    }

    // Command untuk Simpan atau Update Mahasiswa
    @Command
    @NotifyChange({"students", "name", "address", "jurusan"})
    public void saveStudent() {
        if (name != null && !name.isEmpty() &&
            address != null && !address.isEmpty() &&
            jurusan != null && !jurusan.isEmpty()) {

            if (isDuplicateName(name)) {
                Messagebox.show("Nama sudah terdaftar!", "Peringatan", Messagebox.OK, Messagebox.EXCLAMATION);
                return;
            }

            if (selectedStudent != null) {
                studentService.editStudent(selectedStudent, name, address, jurusan); // Edit
                selectedStudent = null;
            } else {
                studentService.addStudent(name, address, jurusan); // Tambah
            }

            // Reset form
            name = "";
            address = "";
            jurusan = "";

        } else {
            Messagebox.show("Semua field harus diisi!", "Peringatan", Messagebox.OK, Messagebox.EXCLAMATION);
        }
    }

    @Command
    @NotifyChange({"name", "address", "jurusan", "selectedStudent"})
    public void editStudent(@BindingParam("student") StudentModel.Student student) {
        this.name = student.getName();
        this.address = student.getAddress();
        this.jurusan = student.getJurusan();
        this.selectedStudent = student;
    }

    @Command
    @NotifyChange("students")
    public void deleteStudent(@BindingParam("student") StudentModel.Student student) {
        studentService.deleteStudent(student);
    }

    public List<StudentModel.Student> getStudents() {
        return studentService.getAllStudents();
    }

    public String getButtonLabel() {
        return selectedStudent != null ? "Update" : "Simpan";
    }
}
