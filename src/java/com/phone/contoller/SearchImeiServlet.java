/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phone.contoller;

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
import java.util.List;
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
public class SearchImeiServlet extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
            PhoneDao phone = new PhoneDao();
            ServicesDao service = new ServicesDao();
            CustomerDao customer = new CustomerDao();
            RepairDao repair = new RepairDao();
            
            
            HttpSession session = request.getSession();
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SearchImeiServlet!!!</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SearchImeiServlet at " + request.getContextPath() + "</h1>");
            out.println("<hr/>");
            out.println(request.getParameter("search_imei"));
            
            String imei = request.getParameter("search_imei");
            
            List<Phone> myPhones = phone.getPhones();
            List<Services> myService = service.getServices();
            List<Customer> myCustomers = customer.getCustomers();
            List<Repair> myRepairs = repair.getRapairs();
            
            int id;
                        
            out.println("początek!!!");
            
            out.println("usersi!!");
            out.println("phone tabela cala!!!!!");
            out.println(phone.initDB());
            out.println(phone.pokazPhonesDane());
            out.println("service tabela cala!!!!");
            out.println(service.initDB());
            out.println(service.pokazServicesDane());
            out.println(customer.initDB());
            out.println(customer.pokazCustomersDane());
            out.println(repair.initDB());
            out.println(repair.pokazRepairDane());
            
            for(Phone myPhone: myPhones){
                out.println(myPhone.getImei());
               if(imei.equals(myPhone.getImei())){
                    session.setAttribute("id", myPhone.getId());
                    session.setAttribute("imei", imei);
                    session.setAttribute("brand", myPhone.getBrand());
                    session.setAttribute("model", myPhone.getModel());
                    session.setAttribute("sale_date", myPhone.getSaleDate());
                    session.setAttribute("is_open", myPhone.getIsOpen());
               }
            }
            
            if(session.getAttribute("imei") != null){
                out.println("Wyszukany Imei, dane tel :");
                out.println("Imei :"+session.getAttribute("imei"));
                out.println("Marka :"+session.getAttribute("brand"));
                out.println("Model :"+session.getAttribute("model"));
                out.println("Data sprzedazy :"+session.getAttribute("sale_date"));
                out.println("Naprawa otwarta :"+session.getAttribute("is_open"));
                
                for(Services myServ: myService){
                    if(session.getAttribute("id").equals(myServ.getPhoneId()))
                        //out.println("jest ok");
                    //else
                    //{
                    session.setAttribute("serid", myServ.getId());
                    //out.println(session.getAttribute("serid"));
                    session.setAttribute("phoneid", myServ.getPhoneId());
                    //out.println(session.getAttribute("phoneid"));
                    session.setAttribute("custid", myServ.getCustId());
                    //out.println(session.getAttribute("custid"));
                    session.setAttribute("repairid", myServ.getRepairId());
                    //out.println(session.getAttribute("repairid"));
                    //}
                }
                
                out.println("ID TEEFONU");
                out.println(session.getAttribute("id"));
                out.println(session.getAttribute("phoneid"));
                out.println("ID KLIENTA");
                out.println(session.getAttribute("custid"));
                out.println("ID NAPRAWY");
                out.println(session.getAttribute("repairid"));
                
             /**   if(session.getAttribute("id").equals(session.getAttribute("serid"))){
                    out.println("SERVIS "+session.getAttribute("serid")+" "
                            +session.getAttribute("phoneid")+" "
                            +session.getAttribute("custid")+" "
                            +session.getAttribute("repairid"));
                            
                }
                else
                    out.println("servis problem");
                    * */
                
                for(Customer myCust: myCustomers){
                    //out.println(myCust.getId());
                    //out.println(session.getAttribute("custid"));
                    if(session.getAttribute("custid").equals(myCust.getId())){
                    //out.println(myCust.getId());
                    //out.println("id się zgadza. To :"+session.getAttribute("customid"));
                    session.setAttribute("customid", myCust.getId());
                    session.setAttribute("custname", myCust.getName());
                    out.println(session.getAttribute("custname"));
                    session.setAttribute("custsurname", myCust.getSurname());
                    out.println(session.getAttribute("custsurname"));
                    session.setAttribute("custstreet", myCust.getStreet());
                    out.println(session.getAttribute("custstreet"));
                    session.setAttribute("custzip", myCust.getZipcode());
                    out.println(session.getAttribute("custzip"));
                    session.setAttribute("custcity", myCust.getCity());
                    out.println(session.getAttribute("custcity"));
                    session.setAttribute("custcontact", myCust.getContact());   
                    out.println(session.getAttribute("custcontact"));
                }   
                }
                for(Repair myRepair: myRepairs){
                    out.println(myRepair.getId());
                    out.println(session.getAttribute("repairid"));
                    if(session.getAttribute("repairid").equals(myRepair.getId())){
                    //out.println(myCust.getId());
                    //out.println("id się zgadza. To :"+session.getAttribute("customid"));
                    session.setAttribute("repairtype", myRepair.getRepairtype());
                    out.println(session.getAttribute("repairtype"));
                    session.setAttribute("repairdescription", myRepair.getDescription());
                    out.println(session.getAttribute("repairdescription"));
                    session.setAttribute("repairdate", myRepair.getRepairDate());
                    out.println(session.getAttribute("repairdate"));
                    }       
                }
            }
            else
                out.println("Nie znaleziono imeia!!!");
            
            
            out.println("</body>");
            out.println("</html>");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SearchImeiServlet.class.getName()).log(Level.SEVERE, null, ex);
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
