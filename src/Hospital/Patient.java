package Hospital;

import java.io.Serializable;

public class Patient implements Serializable {
    private String id;
    private String Name;
    private String Phone;
    private String Date;
    private String Gender;
    private String Address;
    private String Status;

    public Patient(String id, String name, String phone, String date, String gender, String address, String status) {
        this.id = id;
        Name = name;
        Phone = phone;
        Date = date;
        Gender = gender;
        Address = address;
        Status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
