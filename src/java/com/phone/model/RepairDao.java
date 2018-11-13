/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phone.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hsqldb.jdbc.JDBCConnection;
import org.hsqldb.jdbcDriver;

/**
 *
 * @author 
 */
public class RepairDao {
    
    List<Repair> myRepairs = new ArrayList<Repair>();
    
    Repair myRepair;
    
    Statement stat;
    Connection connection;
    
    JDBCConnection conn;
        
    int rozmiar = 0;
    
    int retid = 0;
    String retrepairType = "";
    String retdescription = "";
    Date retrepairDate = null;
        
    
    
    public String initDB() throws ClassNotFoundException {       //do testow public, ma byc private
        
       
        Class.forName("org.hsqldb.jdbcDriver");
        
        try {
        DriverManager.registerDriver(new jdbcDriver());
        } catch (Exception e) {}
        
        //inicjowanie połączenia z bazą
        try {
            
            connection = DriverManager.getConnection("jdbc:hsqldb:file:C:\\Users\\User\\NetBeansProject\\Baza", "SA", "");
            stat = connection.createStatement();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return "coś nie tak bla bla" ;
        }
        return "jest good  REPAIR";
    }
    
        public List<Repair> pokazRepairDane() {

        //pobranie danych z bazy danych
        try {
                       
            //pobranie wielkości tabeli ITEMS
            String select = "SELECT id FROM REPAIRS";     // w hsqldb nie działa count(*)
            ResultSet rs = stat.executeQuery(select);
            
            while (rs.next()) {
                rozmiar++;
            }
                       
            select = "SELECT * FROM REPAIRS";
            rs = stat.executeQuery(select);
            
            while(rs.next()){
            retid = rs.getInt(1);
            retrepairType = rs.getString(2);
            retdescription = rs.getString(3);
            retrepairDate = rs.getDate(4);

            
            myRepair = new Repair(retid, retrepairType, retdescription, retrepairDate);
            myRepairs.add(myRepair);
            }
            
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            
        }
        return myRepairs;
    }
	
	public void addRepair(String update) {
		try {
                        stat.executeUpdate(update);                       
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
        }
	
	public List<Repair> getRapairs() {
		return myRepairs;
	}
}
