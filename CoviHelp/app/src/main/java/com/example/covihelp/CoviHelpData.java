package com.example.covihelp;

public class CoviHelpData {

    private String HelpDescription;
    private String Address;
    private String Email;
    private String Contact;

    public CoviHelpData() {
    }

    public CoviHelpData(String helpDescription, String address, String email, String contact) {
        HelpDescription = helpDescription;
        Address = address;
        Email = email;
        Contact = contact;
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


