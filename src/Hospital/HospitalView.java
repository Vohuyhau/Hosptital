package Hospital;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class HospitalView extends JFrame{
    JFrame frontScreen;
    private JPanel mainPanel;
    private JTextField txtname;
    private JTextField txtphone;
    private JTextField txtbirth;
    private JTextField txtaddress;
    private JButton addButton;
    private JButton updateButton;
    private JButton deletedButton;
    private JTable tbPatient;
    private JComboBox cbgender;
    private JButton sortByNameButton;
    private JTextField txtid;
    private JComboBox cbStatus;

    DefaultTableModel tbModel; //kho quan li rieng cua bang
    DefaultComboBoxModel cbModel = new DefaultComboBoxModel();
    DefaultComboBoxModel cbModel2 = new DefaultComboBoxModel();
    ArrayList<Patient> panList;
    String filePath = "patient.dat";
    int currentRow = -1;
    public <Login> HospitalView(String title, Login aThis){
        //1. Initialize Setup
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        //2.First load ComboBox, Table
        initTable();
        loadCb();
        loadCb2();
        //load data file (in your project)
        panList = new ArrayList<>();
        boolean file = loadFile();
        if(file){
            filToTable();
        }else {
            showMess("Nothing to show");
        }

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addlist();
            }
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updatelist();
            }
        });
        tbPatient.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                currentRow = tbPatient.getSelectedRow();
                showDetail(currentRow);
            }
        });
        deletedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deletedlist();
            }
        });
        sortByNameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sortbyname();
            }
        });
    }

    private void sortbyname() {
        Collections.sort(panList, new Comparator<Patient>() {
            @Override
            public int compare(Patient o1, Patient o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
    }

    private void deletedlist() {
        deletedPatient();
        //2. fill to table
        filToTable();
        //3. save arraylist
        saveFile();
    }

    private void deletedPatient() {
        int re = JOptionPane.showConfirmDialog(this,""+"Do you want to delete this one?",
                "Delete Warning",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);
        if(re==JOptionPane.YES_OPTION){
            panList.remove(currentRow);
            resetForm();
        }
    }

    private void resetForm() {
        txtid.setText("");
        txtname.setText("");
        txtphone.setText("");
        txtbirth.setText("");
        cbgender.setSelectedIndex(0);
        txtaddress.setText("");
        cbStatus.setSelectedIndex(0);
    }

    private void updatelist() {
        updatePatient();
        //2. fill to table
        filToTable();
        //3. save arraylist
        saveFile();
    }

    private void updatePatient() {
        Patient p = panList.get(currentRow);

        String id = txtid.getText();
        p.setId(id);

        String name = txtname.getText();
        p.setName(name);

        String phone = txtphone.getText();
        p.setPhone(phone);

        String date = txtbirth.getText();
        p.setDate(date);

        String gender = cbgender.getSelectedItem().toString();
        p.setGender(gender);

        String address = txtaddress.getText();
        p.setAddress(address);

        String status = cbStatus.getSelectedItem().toString();
        p.setStatus(status);

    }
    private void showDetail(int currentRow) {
        Patient p = panList.get(currentRow);

        String id = p.getId();
        txtid.setText(id);

        String name = p.getName();
        txtname.setText(name);

        String phone = p.getPhone();
        txtphone.setText(phone);

        String date = p.getDate();
        txtbirth.setText(date);

        String gender = p.getGender();
        cbgender.setSelectedItem(gender);

        String address = p.getAddress();
        txtaddress.setText(address);

        String status = p.getStatus();
        cbStatus.setSelectedItem(status);


    }

    private void addlist() {

        addTolist();
        //2. fill to table
        filToTable();
        //3. save arraylist
        saveFile();
    }

    private void saveFile() {
        XFile.writeObject(filePath,panList);
    }

    private void addTolist() {
        String id = txtid.getText();
        String name = txtname.getText();
        String phone = txtphone.getText();
        String date = txtbirth.getText();
        String gen = cbgender.getSelectedItem().toString();
        String address = txtaddress.getText();
        String status = cbStatus.getSelectedItem().toString();
        Patient c = new Patient(id, name, phone,date, gen, address, status);

        if (id.isEmpty() || name.isEmpty() || phone.isEmpty() || date.isEmpty() || gen.isEmpty() || address.isEmpty() || status.isEmpty()) {
            showMess("You need to fill in the information!!!");
            return;
        }

        panList.add(c);
    }

    private void initTable() {
        String[] columnName = { " ID", "Name", "Phone","Birth", "Gender","Address", "Status"};
        tbModel = new DefaultTableModel(columnName,0);
        tbPatient.setModel(tbModel);
    }

    private void filToTable() {
        //clean old data in the table
        tbModel.setRowCount(0);
        for (Patient c : panList) {
            Object[] row = new Object[]{
                    c.getId(), c.getName(), c.getPhone(), c.getDate(), c.getGender(), c.getAddress(), c.getStatus()
            };
            tbModel.addRow(row);
        }
    }

    private boolean loadFile(){
        if(XFile.readObject(filePath)==null){
            return false;
        }
        panList = (ArrayList<Patient>)
                XFile.readObject(filePath);
        return true;
    }
    private void loadCb(){
        String[] genderLst = {"Choose your gender", "Male","Female"};
        for (String s : genderLst) {
            cbModel.addElement(s);
        }
        cbgender.setModel(cbModel);
    }
    private void SaveFile() {
        XFile.writeObject(filePath,panList);
    }

    private void loadCb2(){
        String[] statusLst = {"Being treated", "Discharge"};
        for (String s : statusLst) {
            cbModel2.addElement(s);
        }
        cbStatus.setModel(cbModel2);
    }


    private void showMess(String mess) {
        JOptionPane.showMessageDialog(this,mess);
    }

}
