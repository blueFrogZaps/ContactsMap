package com.example.suryansh.contactsmap;

/**
 * Created by Suryansh on 4/17/2016.
 */
public class Contacts {

     private String name, email,phone,office_phone;
//create the constructor
    public Contacts(String name,String email,String phone, String office_phone){
     this.setName(name);
        this.setEmail(email);
        this.setPhone(phone);
        this.setOffice_phone(office_phone);
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getEmail() {

        return email;
    }

    public void setEmail(String email) {

        this.email = email;
    }

    public String getPhone() {

        return phone;
    }

    public void setPhone(String phone) {

        this.phone = phone;
    }

    public String getOffice_phone() {

        return office_phone;
    }

    public void setOffice_phone(String office_phone) {

        this.office_phone = office_phone;
    }
}
