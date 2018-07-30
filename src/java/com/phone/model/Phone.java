/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phone.model;

import java.util.Date;

/**
 *
 * @author User
 */
public class Phone {
    
    private int id;
    private String brand;
    private String model;
    private String imei;
    private Date saleDate;
    private String isOpen;

    public Phone(int id, String brand, String model, String imei, Date saleDate, String isOpen) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.imei = imei;
        this.saleDate = saleDate;
        this.isOpen = isOpen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }    

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public String getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(String isOpen) {
        this.isOpen = isOpen;
    }

    @Override
    public String toString() {
        return "Phone{" + "id=" + id + "brand=" + brand + ", model=" + model + ", imei=" + imei + ", saleDate=" + saleDate + ", isOpen=" + isOpen +'}';
    }
    
}
