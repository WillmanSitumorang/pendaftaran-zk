package pendaftaran;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;

public class MyViewModel {

    private String name;
    private String address;
    private String jurusan;
	
    private List<Student> students;
    private Student selectedStudent;

    private static AtomicInteger idGenerator = new AtomicInteger(1);

    @Init
    public void init() {
        students = new ArrayList<>();
    }

    @Command
    @NotifyChange({"students", "name", "address", "selectedStudent"})
    public void saveStudent() {
        if (name != null && !name.isEmpty() && address != null && !address.isEmpty() && jurusan != null && !jurusan.isEmpty()) {
            if (selectedStudent != null) {
                // Update mode
                selectedStudent.setName(name);
                selectedStudent.setAddress(address);
                selectedStudent.setJurusan(jurusan);
                selectedStudent = null;
            } else {
                // Insert mode
                students.add(new Student(idGenerator.getAndIncrement(), name, address, jurusan));
            }
            name = "";
            address = "";
            jurusan = "";
        }
    }

    @Command
    @NotifyChange({"name", "address", "selectedStudent", "jurusan"})
    public void editStudent(@BindingParam("student") Student student) {
        this.name = student.getName();
        this.address = student.getAddress();
        this.jurusan = student.getJurusan();
        this.selectedStudent = student;
    }

    @Command
    @NotifyChange("students")
    public void deleteStudent(@BindingParam("student") Student student) {
        students.remove(student);
    }

    // Getters & Setters
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

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Student getSelectedStudent() {
        return selectedStudent;
    }

    public void setSelectedStudent(Student selectedStudent) {
        this.selectedStudent = selectedStudent;
    }


    // Inner class Student
    public static class Student {
        private int id;
        private String name;
        private String address;
        private String jurusan;

        public Student() {}

        public Student(int id, String name, String address, String jurusan) {
            this.id = id;
            this.name = name;
            this.address = address;
            this.jurusan = jurusan;
        }

        // Getters & Setters
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

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
        public String getJurusan() {return jurusan;}

        public void setJurusan(String jurusan) {this.jurusan = jurusan;}

        // Agar .remove() berfungsi
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Student)) return false;
            Student other = (Student) o;
            return this.id == other.id;
        }

        @Override
        public int hashCode() {
            return Integer.hashCode(id);
        }
    }
}
