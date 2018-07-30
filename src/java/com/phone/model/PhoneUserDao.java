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
 * @author User
 */
public class PhoneUserDao {
    
    List<PhoneUsers> myUsers = new ArrayList<PhoneUsers>();
    
    PhoneUsers myUser;
    
    Statement stat;
    Connection connection;
    
    JDBCConnection conn;
        
    int rozmiar = 0;
    
    String retlog = "";
    String retpass = "";
    String retname = "";
    String retsurname = "";
        
    
    
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
        return "jest good";
    }
    
        public List<PhoneUsers> pokazUserDane() {

        //pobranie danych z bazy danych
        try {
                      
            //pobranie wielkości tabeli ITEMS
            String select = "SELECT id FROM PHONEUSERS";     // w hsqldb nie działa count(*)
            ResultSet rs = stat.executeQuery(select);
            
            while (rs.next()) {
                rozmiar++;
            }
                       
            select = "SELECT * FROM PHONEUSERS";
            rs = stat.executeQuery(select);
            
            while(rs.next()){
            retlog = rs.getString(2);
            retpass = rs.getString(3);
            retname = rs.getString(4);
            retsurname = rs.getString(5);

            
            myUser = new PhoneUsers(retlog, retpass, retname, retsurname);
            myUsers.add(myUser);
            }
            
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            
        }
        return myUsers;
    }
	
	public void dodajUser(PhoneUsers user) {
		myUsers.add(user);
	}
	
	public List<PhoneUsers> getUsers() {
		return myUsers;
	}
}
