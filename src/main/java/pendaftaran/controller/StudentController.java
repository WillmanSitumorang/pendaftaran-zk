package pendaftaran.controller;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Messagebox;
import pendaftaran.entity.Student;
import pendaftaran.service.StudentService;

import java.util.List;

public class StudentController {

    private String name;
    private String address;
    private String jurusan;

    private Student selectedStudent;


    @WireVariable("studentService")
    private StudentService studentService;

    @Init
    public void init() {
        if (studentService == null) {
            studentService = (StudentService) SpringUtil.getBean("studentService");
        }
    }

    // Getter dan Setter form input
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getJurusan() { return jurusan; }
    public void setJurusan(String jurusan) { this.jurusan = jurusan; }

    // Cek nama duplikat
    private boolean isDuplicateName(String name) {
        return studentService.isDuplicateName(name, selectedStudent);
    }

    // Simpan atau update mahasiswa
    @Command
    @NotifyChange({"students", "name", "address", "jurusan", "selectedStudent"})
    public void saveStudent() {
        if (name == null || name.isEmpty() ||
            address == null || address.isEmpty() ||
            jurusan == null || jurusan.isEmpty()) {
            Messagebox.show("Semua field harus diisi!", "Peringatan", Messagebox.OK, Messagebox.EXCLAMATION);
            return;
        }

        if (isDuplicateName(name)) {
            Messagebox.show("Nama sudah terdaftar!", "Peringatan", Messagebox.OK, Messagebox.EXCLAMATION);
            return;
        }

        if (selectedStudent != null) {
            studentService.update(selectedStudent, name, address, jurusan);
            selectedStudent = null;
        } else {
            studentService.save(name, address, jurusan);
        }

        // Reset form
        name = "";
        address = "";
        jurusan = "";
    }

    // Load data ke form untuk edit
    @Command
    @NotifyChange({"name", "address", "jurusan", "selectedStudent"})
    public void editStudent(@BindingParam("student") Student student) {
        this.name = student.getName();
        this.address = student.getAddress();
        this.jurusan = student.getJurusan();
        this.selectedStudent = student;
    }

    // Hapus data
    @Command
    @NotifyChange("students")
    public void deleteStudent(@BindingParam("student") Student student) {
        studentService.delete(student);
    }

    // Ambil semua data mahasiswa
    public List<Student> getStudents() {
        return studentService.getAll();
    }

    public String getButtonLabel() {
        return selectedStudent != null ? "Update" : "Simpan";
    }
}
