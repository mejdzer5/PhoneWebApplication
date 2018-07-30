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
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hsqldb.jdbc.JDBCConnection;
import org.hsqldb.jdbcDriver;

/**
 *
 * @author User
 */
public class PhoneDao {
    
    List<Phone> myPhones = new ArrayList<Phone>();
    
    Phone myPhone;
    
    Statement stat;
    Connection connection;
    
    JDBCConnection conn;
        
    int rozmiar = 0;
    
    int wyn = 0;
    
    String wynik = "";
    
    int retid = 0;
    String retbrand = "";
    String retmodel = "";
    String retimei = "";
    Date retsaledate = null;
    String retopen = "";
        
    
    
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
            return "coś nie tak bla bla phonephone" ;
        }
        return "jest good phonephone";
    }
    
        public List<Phone> pokazPhonesDane() {

        //pobranie danych z bazy danych
        try {
                    
            //pobranie wielkości tabeli ITEMS
            String select = "SELECT PHONE_ID FROM PHONES";     // w hsqldb nie działa count(*)
            
            ResultSet rs = stat.executeQuery(select);
            
            while (rs.next()) {
                rozmiar++;
            }
                       
            select = "SELECT * FROM PHONES";
            rs = stat.executeQuery(select);
            
            while(rs.next()){
            retid = rs.getInt(1);
            retbrand = rs.getString(2);
            retmodel = rs.getString(3);
            retimei = rs.getString(4);
            retsaledate = rs.getDate(5);
            retopen = rs.getString(6);

            
            myPhone = new Phone(retid, retbrand, retmodel, retimei, retsaledate, retopen);
            myPhones.add(myPhone);
            }
            
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            
        }
        return myPhones;
    }
        
        //TESTOWE POBRANIE ID Z TESTOWEJ TABELI PHONES2!!!!!!!!!!!!!!!
        public String ShowId(){
            try {
            //pobranie wielkości tabeli PHONES
           // String select = "SELECT PHONE_ID FROM PHONES";     // w hsqldb nie działa count(*)
            String select = "SELECT * FROM PHONES2";  //a potem rs.getString(1);
            ResultSet rs = stat.executeQuery(select);
            
            if(rs.next()) {
                wynik = rs.getString("ID");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PhoneDao.class.getName()).log(Level.SEVERE, null, ex);
        }
            return wynik;
        }
	
        public void addPhone(String update) {
                try {
                        stat.executeUpdate(update);                       
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                      catch(NullPointerException ex){
                        ex.printStackTrace();
                    }
        }
                
        public void closePhone(String update){
            try {
                        stat.executeUpdate(update);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
        }
	
	public List<Phone> getPhones() {
		return myPhones;
	}
    
}
