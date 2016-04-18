package com.example.suryansh.contactsmap;

/**
 * Created by Suryansh on 4/17/2016.
 */
public class Contacts {

     private String name, email,phone,officePhone;
//create the constructor
    public Contacts(String name,String email,String phone, String officePhone){
     this.setName(name);
        this.setEmail(email);
        this.setPhone(phone);
        this.setOfficePhone(officePhone);
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

    public String getOfficePhone() {

        return officePhone;
    }

    public void setOfficePhone(String officePhone) {

        this.officePhone = officePhone;
    }
}
