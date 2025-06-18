package pendaftaran.model;

public class Student {
    private int id;
    private String name;
    private String jurusan;
    private String address;

    public Student(int id, String name, String jurusan, String address) {
        this.id = id;
        this.name = name;
        this.jurusan = jurusan;
        this.address = address;
    }

    // Getters & Setters
    public int getId() { return id; }
    public String getName() { return name; }
    public String getJurusan() { return jurusan; }
    public String getAddress() { return address; }

    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setJurusan(String jurusan) { this.jurusan = jurusan; }
    public void setAddress(String address) { this.address = address; }

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
