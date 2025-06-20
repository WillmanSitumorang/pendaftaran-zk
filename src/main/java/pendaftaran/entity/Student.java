package pendaftaran.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "students")
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT
    private Integer id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String jurusan;

    // Constructor kosong wajib untuk JPA
    public Student() {}

    public Student(String name, String address, String jurusan) {
        this.name = name;
        this.address = address;
        this.jurusan = jurusan;
    }

    // Getter dan Setter
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getJurusan() {
        return jurusan;
    }

    public void setJurusan(String jurusan) {
        this.jurusan = jurusan;
    }

    // equals & hashCode (berdasarkan ID)
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Student)) return false;
        Student other = (Student) obj;
        return id != null && id.equals(other.getId());
    }

    @Override
    public int hashCode() {
        return 31 + (id == null ? 0 : id.hashCode());
    }
}
