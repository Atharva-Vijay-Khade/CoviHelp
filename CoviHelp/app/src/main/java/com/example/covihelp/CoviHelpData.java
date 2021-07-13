package com.example.covihelp;

public class CoviHelpData {

    private String Adder;
    private String HelpDescription;
    private String Address;
    private String Email;
    private String Contact;

    public CoviHelpData() {
    }

    public CoviHelpData(String helpDescription, String address, String email, String contact,String adder) {
        Adder = adder;
        HelpDescription = helpDescription;
        Address = address;
        Email = email;
        Contact = contact;
    }

    public String getAdder() {
        return Adder;
    }

    public void setAdder(String adder) {
        Adder = adder;
    }

    public String getHelpDescription() {
        return HelpDescription;
    }

    public String getAddress() {
        return Address;
    }

    public String getEmail() {
        return Email;
    }

    public String getContact() {
        return Contact;
    }

    public void setHelpDescription(String helpDescription) {
        HelpDescription = helpDescription;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setContact(String contact) {
        Contact = contact;
    }

}


