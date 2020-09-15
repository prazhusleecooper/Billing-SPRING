package com.billing.spring.com.billing.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Customers {

    @Id
    @GeneratedValue
    private int cus_ID;

    private String cus_name;
    private String cus_email;
    private String cus_number;
    private String cus_address;

    public Customers() {

    }

    public Customers(String cus_name, String cus_email, String cus_number, String cus_address) {
        this.cus_name = cus_name;
        this.cus_email = cus_email;
        this.cus_number = cus_number;
        this.cus_address = cus_address;
    }

    public int getCus_ID() {
        return cus_ID;
    }

    public void setCus_ID(int cus_ID) {
        this.cus_ID = cus_ID;
    }

    public String getCus_name() {
        return cus_name;
    }

    public void setCus_name(String cus_name) {
        this.cus_name = cus_name;
    }

    public String getCus_email() {
        return cus_email;
    }

    public void setCus_email(String cus_email) {
        this.cus_email = cus_email;
    }

    public String getCus_number() {
        return cus_number;
    }

    public void setCus_number(String cus_number) {
        this.cus_number = cus_number;
    }

    public String getCus_address() {
        return cus_address;
    }

    public void setCus_address(String cus_address) {
        this.cus_address = cus_address;
    }

    @Override
    public String toString() {
        return "Customers{" +
                "cus_ID=" + cus_ID +
                ", cus_name='" + cus_name + '\'' +
                ", cus_email='" + cus_email + '\'' +
                ", cus_number='" + cus_number + '\'' +
                ", cus_address='" + cus_address + '\'' +
                '}';
    }
}
