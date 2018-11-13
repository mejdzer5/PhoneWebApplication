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
import java.util.List;
import org.hsqldb.jdbc.JDBCConnection;
import org.hsqldb.jdbcDriver;

/**
 *
 * @author 
 */
public class ServicesDao {
    
    List<Services> myServices = new ArrayList<Services>();
    
    Services myServ;
    
    Statement stat;
    Connection connection;
    
    JDBCConnection conn;
       
    int rozmiar = 0;
    
    int retid = 0;
    int retPhoneId = 0;
    int retCustId = 0;
    int retRepairId = 0;
        
    
    
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
        return "jest good  SERVICES";
    }
    
        public List<Services> pokazServicesDane() {

        //pobranie danych z bazy danych
        try {
                       
            //pobranie wielkości tabeli ITEMS
            String select = "SELECT id FROM SERVICES";     // w hsqldb nie działa count(*)
            ResultSet rs = stat.executeQuery(select);
            
            while (rs.next()) {
                rozmiar++;
            }
                       
            select = "SELECT * FROM SERVICES";
            rs = stat.executeQuery(select);
            
            while(rs.next()){
            retid = rs.getInt(1);
            retPhoneId = rs.getInt(2);
            retCustId = rs.getInt(3);
            retRepairId = rs.getInt(4);

            
            myServ = new Services(retid, retPhoneId, retCustId, retRepairId);
            myServices.add(myServ);
            }
            
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            
        }
        return myServices;
    }
	
	public void addServices(String update) {
		try {
                        stat.executeUpdate(update);                       
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
        }
	
	public List<Services> getServices() {
		return myServices;
	}
}
