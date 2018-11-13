/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phone.model;

/**
 *
 * @author 
 */
public class Customer {
    
    private int id;
    private String name;
    private String surname;
    private String street;
    private String zipcode;
    private String city;
    private String contact;

    public Customer(int id, String name, String surname, String street, String zipcode, String city, String contact) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.street = street;
        this.zipcode = zipcode;
        this.city = city;
        this.contact = contact;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
    

    @Override
    public String toString() {
        return "Customer{" + "id=" + id + ", name=" + name + ", surname=" + surname + ", street=" + street + ", zipcode=" + zipcode + ", city=" + city + ", contact=" + contact +'}';
    }
    
}
