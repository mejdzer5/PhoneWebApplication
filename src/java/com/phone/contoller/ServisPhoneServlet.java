/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phone.contoller;

import com.phone.model.CustomerDao;
import com.phone.model.Phone;
import com.phone.model.PhoneDao;
import com.phone.model.RepairDao;
import com.phone.model.ServicesDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 *
 * @author User
 */
public class ServisPhoneServlet extends HttpServlet {

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
        
        SimpleDateFormat today = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sale = new SimpleDateFormat("yyyy-MM-dd");
        
        List errorMsgs = new LinkedList();
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ServisPhoneServlet!!!</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ServisPhoneServlet at " + request.getContextPath() + "</h1>");
            
            List<Phone> myPhones = phone.getPhones();
            
            HttpSession session = request.getSession();
            
            out.println("<hr/>");
            out.println("phoneservis "+phone.initDB());
            out.println("<br/>REPAIR"+repair.initDB());
            out.println("<br/>CUST"+customer.initDB());
            out.println("<br/>SERVICES!!!!!!!!!!!"+services.initDB());
            phone.pokazPhonesDane();
            
            // Porabranie danych telefonu z formularza
            String id = request.getParameter("phoneid");
            String brand = request.getParameter("brand");
            String model = request.getParameter("model");
            String imei = request.getParameter("imei");
            String saleDate = request.getParameter("sale_date");
        
            
            if(!saleDate.matches("[0-9]{4}.[0-1]?[0-9].[0-3]?[0-9]")){
                errorMsgs.add("Błędna data!!!");
            }
            
            if(!imei.matches("[0-9]{15}")){
                errorMsgs.add("Błędny imei!!!");
            }
            
            out.println("POCZATEK!!!!!!");
            out.println(imei+"IMEI<br/>");
            
            for(Phone myPhone: myPhones){
               if(imei.equals(myPhone.getImei())){
                       if(myPhone.getIsOpen().equals("true")){
                       errorMsgs.add("Istnieje nie zamknieta naprawa dla tego IMEIa");
                       }
                  }
            }
            
            out.println("<br>");
            
            
            out.println("<br/>PHONES ID<br/>");
            out.println("<br/>");
            
            String update = "INSERT INTO PHONES VALUES ('"+id+"', '"+brand+"', '"+model+"', '"+imei+"', '"+saleDate+"')";
            out.println("<br>");
                       
            out.println(update);
                        
            //pobieranie danych z formularza opis usterki
            String id2 = request.getParameter("repid");
            String repairType = request.getParameter("repair_type");
            String description = request.getParameter("description");
            String repairDate = request.getParameter("repair_date");
            
            if(!repairDate.matches("[0-9]{4}.[0-1]?[0-9].[0-3]?[0-9]")){
                errorMsgs.add("Błędna data!!!");
            }
                        
            if(repairDate.matches("[0-9]{4}.[0-1]?[0-9].[0-3]?[0-9]") && saleDate.matches("[0-9]{4}.[0-1]?[0-9].[0-3]?[0-9]")){
            
            out.println("<br/>TODAY"+repairDate);
            out.println("<br/>SALEDATE"+saleDate);
            
            out.println("<br/>DATYYYY");
                        
            out.println("<br/>NOWE DATY!!!!");
            
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate ld1 = LocalDate.parse(repairDate, formatter);
            LocalDate ld2 = LocalDate.parse(saleDate, formatter);
                        
            out.println("<br/>LOCALDATE BETWEEN!!!: ");
                       
            Period p = Period.between(ld2, ld1);
            
            out.println(p);
            
            out.println("<br/>CHRORNOUNIT!!!!!!!!!!!!!!");
            
            long days = ChronoUnit.DAYS.between(ld1, ld2);
            
            out.println(days);
            
            if(days > 0){
                errorMsgs.add("Nie może być przyszłej daty zakupu");
            }
            
            if(days < -730 && repairType.equals("Gwarancja")){
                repairType = "Pograwarancyjna";
                errorMsgs.add("Gwaracja na urządzenie mineła. Może skorzystac z narawy pogwarancyjnej");
            }
            
            }
                        
            out.println("<br/>REPAIRS ID<br/>");
            
            out.println("<br/>");
            
            String update2 = "INSERT INTO REPAIRS VALUES ('"+id2+"', '"+repairType+"', '"+description+"', '"+repairDate+"')";
            out.println("<br>");
            
            out.println(update2);
            
                       
            //pobieranie danych z formularza danych klienta
            String id3 = request.getParameter("custid");
            String name = request.getParameter("name");
            String surname = request.getParameter("surname");
            String street = request.getParameter("street");
            String zipcode = request.getParameter("zipcode");
            String city = request.getParameter("city");
            String contact = request.getParameter("contact");
            
            if(!zipcode.matches("[0-9]{2}-[0-9]{3}")){
                errorMsgs.add("Błędny KOD!!!");
            }
            
            if ( ! errorMsgs.isEmpty() ) {
                request.setAttribute("errorMsgs", errorMsgs);
                RequestDispatcher view = request.getRequestDispatcher("/servis_phone_form.view");
                view.forward(request, response);
                return;
            }
            
            out.println("<br/>CUSTOMERS ID<br/>");
            
            out.println("<br/>");
            
            String update3 = "INSERT INTO CUSTOMERS VALUES ('"+id3+"', '"+name+"', '"+surname+"', '"+street+"', '"+zipcode+"', '"+city+"', '"+contact+"')"; 
            out.println("<br/>");
            
            out.println(update3);
            
            String id4 = request.getParameter("servid");   //servis id pobrane
            out.println("SERVIS ID!!!!!!!!!!!!!!!!   "+id4);
            
            
            out.println("<form action='save_repair.do' method='POST'>");
            
            out.println("<input type='hidden' name='phoneid' value='"+id+"'/>");
            out.println("<input type='hidden' name='brand' value='"+brand+"'/>");
            out.println("<input type='hidden' name='model' value='"+model+"'/>");
            out.println("<input type='hidden' name='imei' value='"+imei+"'/>");
            out.println("<input type='hidden' name='saledate' value='"+saleDate+"'/>");
            
            out.println("<input type='hidden' name='repid' value='"+id2+"'/>");
            out.println("<input type='hidden' name='repair_type' value='"+repairType+"'/>");
            out.println("<input type='hidden' name='description' value='"+description+"'/>");
            out.println("<input type='hidden' name='repair_date' value='"+repairDate+"'/>");
                        
            out.println("<input type='hidden' name='custid' value='"+id3+"'/>");
            out.println("<input type='hidden' name='name' value='"+name+"'/>");
            out.println("<input type='hidden' name='surname' value='"+surname+"'/>");
            out.println("<input type='hidden' name='street' value='"+street+"'/>");
            out.println("<input type='hidden' name='zipcode' value='"+zipcode+"'/>");
            out.println("<input type='hidden' name='city' value='"+city+"'/>");
            out.println("<input type='hidden' name='contact' value='"+contact+"'/>");
            
            out.println("<input type='hidden' name='servid' value='"+id4+"'/>");
            
            out.println("<input type='Submit' value='Zapisz' />");
            out.println("</form>");
            
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
