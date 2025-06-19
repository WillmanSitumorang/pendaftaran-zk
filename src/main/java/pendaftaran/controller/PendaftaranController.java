package pendaftaran.controller;

import pendaftaran.model.Student;
import pendaftaran.service.PendaftaranService;
import org.zkoss.zk.ui.select.annotation.*;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.zk.ui.Component;
import org.zkoss.zul.*;


public class PendaftaranController extends SelectorComposer<Component> {

    @Wire private Listbox studentList;
    @Wire private Textbox nameBox;
    @Wire private Textbox jurusanBox;
    @Wire private Textbox addressBox;
    @Wire private Button saveBtn;

    private final PendaftaranService service = new PendaftaranService();
    private Student selectedStudent = null;

    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        Selectors.wireComponents(comp, this, false);
        refreshList();
    }

    @Listen("onClick = #saveBtn")
    public void saveStudent() {
        String name = nameBox.getValue();
        String jurusan = jurusanBox.getValue();
        String address = addressBox.getValue();

        if (selectedStudent != null) {
            service.updateStudent(selectedStudent, name, jurusan, address);
            selectedStudent = null;
            saveBtn.setLabel("Simpan");
        } else {
            service.addStudent(name, jurusan, address);
        }

        nameBox.setValue("");
        jurusanBox.setValue("");
        addressBox.setValue("");

        refreshList();
    }

    @Listen("onClick = button.editBtn")
    public void editStudent(@BindingParam("student") Student student) {
        selectedStudent = student;
        nameBox.setValue(student.getName());
        jurusanBox.setValue(student.getJurusan());
        addressBox.setValue(student.getAddress());
        saveBtn.setLabel("Update");
    }

    @Listen("onClick = button.deleteBtn")
    public void deleteStudent(@BindingParam("student") Student student) {
        service.deleteStudent(student);
        refreshList();
    }

    private void refreshList() {
    studentList.getItems().clear();

    for (Student s : service.getAllStudents()) {
        Listitem item = new Listitem();

        item.appendChild(new Listcell(s.getName()));
        item.appendChild(new Listcell(s.getJurusan()));
        item.appendChild(new Listcell(s.getAddress()));

        Listcell actionCell = new Listcell();

        // Tombol Edit
        Button editBtn = new Button("Edit");
        editBtn.setStyle("margin-right: 5px;background:#FF0090" );
        editBtn.addEventListener("onClick", e -> {
            selectedStudent = s;
            nameBox.setValue(s.getName());
            jurusanBox.setValue(s.getJurusan());
            addressBox.setValue(s.getAddress());
            saveBtn.setLabel("Update");
        });

        // Tombol Hapus
        Button deleteBtn = new Button("Hapus");
        deleteBtn.setStyle("background:#FF0090");
        deleteBtn.addEventListener("onClick", e -> {
            service.deleteStudent(s);
            refreshList();
        });

        actionCell.appendChild(editBtn);
        actionCell.appendChild(deleteBtn);
        item.appendChild(actionCell);

        studentList.appendChild(item);
    }
}

}
