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
public class Repair {
    
    private int id;
    private String repairtype;
    private String description;
    private Date repairDate;

    public Repair(int id, String repairtype, String description, Date repairDate) {
        this.id = id;
        this.repairtype = repairtype;
        this.description = description;
        this.repairDate = repairDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRepairtype() {
        return repairtype;
    }

    public void setRepairtype(String repairtype) {
        this.repairtype = repairtype;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getRepairDate() {
        return repairDate;
    }

    public void setRepairDate(Date repairDate) {
        this.repairDate = repairDate;
    }

    @Override
    public String toString() {
        return "Repair{" + "id=" + id + ", repairtype=" + repairtype + ", description=" + description + ", repairDate=" + repairDate + '}';
    }   
}
