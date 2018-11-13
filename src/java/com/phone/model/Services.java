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
public class Services {
    
    private int id;
    private int phoneId;
    private int custId;
    private int repairId;

    public Services(int id, int phoneId, int custId, int repairId) {
        this.id = id;
        this.phoneId = phoneId;
        this.custId = custId;
        this.repairId = repairId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPhoneId() {
        return phoneId;
    }

    public void setPhoneId(int phoneId) {
        this.phoneId = phoneId;
    }

    public int getCustId() {
        return custId;
    }

    public void setCustId(int custId) {
        this.custId = custId;
    }

    public int getRepairId() {
        return repairId;
    }

    public void setRepairId(int repairId) {
        this.repairId = repairId;
    }

    @Override
    public String toString() {
        return "Services{" + "id=" + id + ", phoneId=" + phoneId + ", custId=" + custId + ", repairId=" + repairId + '}';
    }
    
    
    
}
