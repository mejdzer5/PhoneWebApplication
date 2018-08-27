/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phone.view;

import com.phone.model.Customer;
import com.phone.model.CustomerDao;
import com.phone.model.Phone;
import com.phone.model.PhoneDao;
import com.phone.model.Repair;
import com.phone.model.RepairDao;
import com.phone.model.Services;
import com.phone.model.ServicesDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author User
 */
public class ServisPhoneFormServlet extends HttpServlet {
    
    
    List<Phone> myPhones = new ArrayList<Phone>();
    List<Repair> myRepairs = new ArrayList<Repair>();
    List<Customer> myCustomers = new ArrayList<Customer>();
    List<Services> myServices = new ArrayList<Services>();
    

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
        CustomerDao customer = new CustomerDao();
        RepairDao repair = new RepairDao();
        ServicesDao services = new ServicesDao();
        
        int id = 0;
        int id2 = 0;
        int id3 = 0;
        int id4 = 0;
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ServisPhoneFormServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ServisPhoneFormServlet at " + request.getContextPath() + "</h1>");
            
            List errorMsgs = (List) request.getAttribute("errorMsgs");
            if (errorMsgs != null) {
                out.println("<h2>Error Messages</h2>");
                out.println("<font color='red'>Please correct the following errors:");
                out.println("<ul>");
                Iterator items = errorMsgs.iterator();
                while (items.hasNext()) {
                    String message = (String) items.next();
                    out.println("  <li>" + message + "</li>");
                }
                out.println("</ul>");
                out.println("</font><p>");
            }
            
            out.println("<hr/>");
            out.println("initDB phonesphones"+phone.initDB());
            out.println("initDB REPAIR"+repair.initDB());
            out.println("initDB CUST"+customer.initDB());
            out.println("InitDB SERV"+services.initDB());
            out.println("pokazDane phonesphones"+phone.pokazPhonesDane());
            out.println("pokazDane REPAIR"+repair.pokazRepairDane());
            out.println("pokazDane CUST"+customer.pokazCustomersDane());
            out.println("POKAZ DANE SERVICES"+services.pokazServicesDane());
            myPhones = phone.getPhones();
            myRepairs = repair.getRapairs();
            myCustomers = customer.getCustomers();
            myServices = services.getServices();
            
            String model = request.getParameter("model");
            String brand = request.getParameter("brand");
            
            out.println("<hr/>");
            out.println("bdhbdjhfb");
            out.println(myPhones);
            out.println(myRepairs);
            out.println(myCustomers);
            out.println(myServices);
            
            Iterator myIt = phone.getPhones().iterator();
            while(myIt.hasNext()){
                Phone ph = (Phone)myIt.next();
                out.println(ph);
                
                id = ph.getId();
            }
            out.println("bdhbdjhfb");
            out.println("           IDIDIDIDI"+id+"         IDIDIDI");
            
            Iterator myIt2 = repair.getRapairs().iterator();
            while(myIt2.hasNext()){
                Repair re = (Repair)myIt2.next();
                out.println(re);
                
                id2 = re.getId();
            }
            out.println("bdhbdjhfb");
            out.println("   REPA       IDIDIDIDI"+id2+"      REPA   IDIDIDI");
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String date1 = sdf.format(date);
            out.println("<hr/>");
            
            Iterator myIt3 = customer.getCustomers().iterator();
            while(myIt3.hasNext()){
                Customer cu = (Customer)myIt3.next();
                out.println(cu);
                
                id3 = cu.getId();
            }
            out.println("bdhbdjhfb");
            out.println("   cust        IDIDIDIDI"+id3+"      cust   IDIDIDI");
            out.println("<hr/>");
            
            Iterator myIt4 = services.getServices().iterator();
            while(myIt4.hasNext()){
                Services sv = (Services)myIt4.next();
                id4 = sv.getId();
            }
            
            out.println("Services ID :"+id4+"<br/>");
            
            out.println("<h3>Podaj dane telefonu:</h3>");
            
            out.println("<form action='servis_phone_servlet.do' method='POST'>");
            
            out.println("<input type='hidden' name='phoneid' value='"+id+"'/>");
            out.println("Brand : <select name='brand'>");
           
            if(brand == null){
            out.println("<option value='Nokia'>Nokia</option>");
            out.println("<option value='Sony'>Sony</option>");
            out.println("<option value='Samsung'>Samsung</option>");
            out.println("<option value='LG'>LG</option>");
            out.println("<option value='Huawei'>Huawei</option>");
            out.println("</select><br/>");
            }else{
            if(brand.equals("Nokia")){
            out.println("<option selected='selected' value="+brand+">"+brand+"</option>");
            }else
            {
            out.println("<option value='Nokia'>Nokia</option>");
            }
            if(brand.equals("Sony")){
                out.println("<option selected='selected' value="+brand+">"+brand+"</option>");
            }else
            {
            out.println("<option value='Sony'>Sony</option>");
            }
            if(brand.equals("Samsung")){
                out.println("<option selected='selected' value="+brand+">"+brand+"</option>");
            }else
            {
            out.println("<option value='Samsung'>Samsung</option>");
            }
            if(brand.equals("LG")){
                out.println("<option selected='selected' value="+brand+">"+brand+"</option>");
            }else
            {
            out.println("<option value='LG'>LG</option>");
            }
            if(brand.equals("Huawei")){
                out.println("<option selected='selected' value="+brand+">"+brand+"</option>");
            }else
            {
            out.println("<option value='Huawei'>Huawei</option>");
            }
            out.println("</select><br/>");
            }

            // Display the year field
            out.print("  Model: <input type='text' name='model' ");
            if (model == null) {
                model = "podaj model";
            }
            out.println("value = '" + model + "' /><br/>");
            
            out.print("  Imei : <input type='text' name='imei' ");
            out.println("value = 'podaj Imei' /> <br/>");
            out.print("  Data zakupu : <input type='text' name='sale_date' ");
            out.println("value = 'RRRR-MM-DD' /> ");
            
            out.println("<h3>Opis usterki:</h3>");
            out.println("<input type='hidden' name='repid' value='"+id2+"'/>");
            out.println("Rodzaj usterki : <select name='repair_type'>");
            out.println("<option value='Gwarancja'>Gwarancja</option>");
            out.println("<option value='Niezgodnosc'>Niezgodnosc</option>");
            out.println("<option value='Pogwarancyjna'>Pogwarancyjna</option>");
            out.println("<option value='Przedgwarancyjna'>Przedgwarancyjna</option>");
            out.println("</select><br/>");
            out.println("<textarea name='description' cols='50' rows='5'>Opis usterki...</textarea><br/>");
            
            out.print("<input type='hidden' name='repair_date' value='"+date1+"'");
            out.println("value = 'RRRR-MM-DD' /> <br/>");
            
            out.println("<h3>Dane klienta:</h3>");
            out.println("<input type='hidden' name='custid' value='"+id3+"'/>");
            out.print("  Imie : <input type='text' name='name' ");
            out.println("value = 'podaj imie' /> <br/>");
            out.print("  Nazwisko : <input type='text' name='surname' ");
            out.println("value = 'podaj nazwisko' /> <br/>");
            out.print("  Adres : <input type='text' name='street' ");
            out.println("value = 'podaj ulice/miejscowosc' /> <br/>");
            out.print("  Kod pocztowy: <input type='text' name='zipcode' ");
            out.println("value = 'podaj pod kod' />, ");
            out.print("  Miasto : <input type='text' name='city' ");
            out.println("value = 'podaj miasto' /> <br/>");
            out.print("  Kontakt : <input type='text' name='contact' ");
            out.println("value = 'nr_tel/e-mail' /> <br/>");
            
            out.println("<input type='hidden' name='servid' value='"+id4+"'/>");
            
            out.println("  <input type='Submit' value='Wyślij' />");
            out.println("</form>");
            
            out.println("</body>");
            out.println("</html>");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServisPhoneFormServlet.class.getName()).log(Level.SEVERE, null, ex);
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
