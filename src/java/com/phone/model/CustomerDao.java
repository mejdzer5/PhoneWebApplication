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
public class CustomerDao {
    
    List<Customer> myCustomers = new ArrayList<Customer>();
    
    Customer customer;
    
    Statement stat;
    Connection connection;
    
    JDBCConnection conn;
    
    int rozmiar = 0;
    
    int wyn = 0;
    
    String wynik = "";
    
    int retid = 0;
    String retname = "";
    String retsurname = "";
    String retstreet = "";
    String retzipcode = "";
    String retcity = "";
    String retcontact = "";
        
    
    
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
        return "jest good CUSTOMERS";
    }
    
        public List<Customer> pokazCustomersDane() {

        //pobranie danych z bazy danych
        try {
                       
            //pobranie wielkości tabeli ITEMS
            String select = "SELECT ID FROM CUSTOMERS";     // w hsqldb nie działa count(*)
            
            ResultSet rs = stat.executeQuery(select);
            
            while (rs.next()) {
                rozmiar++;
            }
                       
            select = "SELECT * FROM CUSTOMERS";
            rs = stat.executeQuery(select);
            
            while(rs.next()){
            retid = rs.getInt(1);
            retname = rs.getString(2);
            retsurname = rs.getString(3);
            retstreet = rs.getString(4);
            retzipcode = rs.getString(5);
            retcity = rs.getString(6);
            retcontact = rs.getString(7);

            
            customer = new Customer(retid, retname, retsurname, retstreet, retzipcode, retcity, retcontact);
            myCustomers.add(customer);
            }
            
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            
        }
        return myCustomers;
    }
        
        public void addCustomer(String update) {
		//myPhones.add(phone);
                try {
                        stat.executeUpdate(update);                       
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
        }
                
        public List<Customer> getCustomers() {
		return myCustomers;
	}
}
