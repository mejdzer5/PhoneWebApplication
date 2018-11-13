/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phone.contoller;

import com.phone.model.CustomerDao;
import com.phone.model.PhoneDao;
import com.phone.model.RepairDao;
import com.phone.model.ServicesDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author User
 */
public class SaveRepair extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        PhoneDao phone = new PhoneDao();
        RepairDao repair = new RepairDao();
        CustomerDao customer = new CustomerDao();
        ServicesDao services = new ServicesDao();
                
        try (PrintWriter out = response.getWriter()) {
            
            /* TODO output your page here. You may use following sample code. */
            
            int id = 0;
            int id2 = 0;
            int id3 = 0;
            int id4 = 0;
            
            HttpSession session = request.getSession();
            
            out.println("<!DOCTYPE html>");
            
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SaveRepair!!!</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SaveRepair at " + request.getContextPath() + "</h1>");
            
            out.println("pobieram dane... bla bla...   NEW COMMIT</br>");
            
            String phoneId = request.getParameter("phoneid");
            try{
            id = Integer.parseInt(phoneId)+1;
            }
            catch(NumberFormatException ex){
                ex.printStackTrace();
            }
            out.println("hhjjhdsfj        "+id+"       fbdjbdfj");
            
            
            String brand = request.getParameter("brand");
            String model = request.getParameter("model");
            String imei = request.getParameter("imei");
            String saleDate = request.getParameter("saledate");
            out.println(phoneId);
            out.println(brand);
            out.println(model);
            out.println(imei);
            out.println(saleDate);
            
            String update = "INSERT INTO PHONES VALUES('"+id+"','"+brand+"','"+model+"','"+imei+"','"+saleDate+"','true')";
            out.println(update);
            
            phone.initDB();
            
            phone.addPhone(update);
                       
            String repairId = request.getParameter("repid");
            try{
            id2 = Integer.parseInt(repairId)+1;
            }
            catch(NumberFormatException ex){
                ex.printStackTrace();
            }
            out.println("hhjjhdsfj  REPA      "+id2+"    REPA   fbdjbdfj");
            
            
            String repairType = request.getParameter("repair_type");
            String description = request.getParameter("description");
            String repairDate = request.getParameter("repair_date");
            out.println(repairId);
            out.println(repairType);
            out.println(description);
            out.println(repairDate);
            
            
            String update2 = "INSERT INTO REPAIRS VALUES('"+id2+"','"+repairType+"','"+description+"','"+repairDate+"')";
            out.println(update2);
            
            repair.initDB();
            
            repair.addRepair(update2);
                        
            String customerId = request.getParameter("custid");
            try{
            id3 = Integer.parseInt(customerId)+1;
            }
            catch(NumberFormatException ex){
                ex.printStackTrace();
            }
            out.println("hhjjhdsfj  CUST      "+id3+"    CUST   fbdjbdfj");
            
            
            String name = request.getParameter("name");
            String surname = request.getParameter("surname");
            String street = request.getParameter("street");
            String zipcode = request.getParameter("zipcode");
            String city = request.getParameter("city");
            String contact = request.getParameter("contact");
            out.println(customerId);
            out.println(name);
            out.println(surname);
            out.println(street);
            out.println(zipcode);
            out.println(city);
            out.println(contact);
            
            String update3 = "INSERT INTO CUSTOMERS VALUES('"+id3+"','"+name+"','"+surname+"','"+street+"','"+zipcode+"','"+city+"','"+contact+"')";
            out.println(update3);
            
            customer.initDB();
            
            customer.addCustomer(update3);
            
            out.println("<br/");
            
            String servisId = request.getParameter("servid");
            try{
            id4 = Integer.parseInt(servisId)+1;
            }
            catch(NumberFormatException ex){
                ex.printStackTrace();
            }
            
            String update4 = "INSERT INTO SERVICES VALUES('"+id4+"','"+id+"','"+id3+"','"+id2+"')";
            
            out.println(update4);
            
            services.initDB();
            
            services.addServices(update4);
            
            
                          
            out.println("</br>zapisuje");
            out.println("</body>");
            out.println("</html>");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServisPhoneServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
