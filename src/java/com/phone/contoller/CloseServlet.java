/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phone.contoller;

import com.phone.model.Phone;
import com.phone.model.PhoneDao;
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
public class CloseServlet extends HttpServlet {

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
            
            HttpSession session = request.getSession();
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CloseServlet!!!</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CloseServlet at " + request.getContextPath() + "</h1>");
            out.println("<hr/>");
            out.println(request.getParameter("search_imei"));
            
            String imei = request.getParameter("search_imei");
            
            List<Phone> myPhones = phone.getPhones();
                        
            out.println("początek!!!");
            
            out.println("usersi!!");
            out.println(phone.initDB());
            out.println(phone.pokazPhonesDane());
            
            
            for(Phone myPhone: myPhones){
                out.println(myPhone.getImei());
               if(imei.equals(myPhone.getImei())){
                    session.setAttribute("imei", imei);
                    session.setAttribute("brand", myPhone.getBrand());
                    session.setAttribute("model", myPhone.getModel());
                    session.setAttribute("sale_date", myPhone.getSaleDate());
                    session.setAttribute("is_open", myPhone.getIsOpen());
               }
            }
                      
            if(session.getAttribute("imei") != null){
                out.println("Wyszukany Imei, dane tel");
                out.println("Imei :"+session.getAttribute("imei"));
                out.println("Marka :"+session.getAttribute("brand"));
                out.println("Model :"+session.getAttribute("model"));
                out.println("Data sprzedazy :"+session.getAttribute("sale_date"));
                out.println("Naprawa otwarta :"+session.getAttribute("is_open"));
                
                
                String open = session.getAttribute("is_open").toString();
                out.println(open);
                if(open.equals("true")){
                
                String update = "UPDATE PHONES SET OPEN='false' WHERE IMEI='"+imei+"'";
                out.println(update);
                phone.closePhone(update);
                }
                else{
                    out.println("Naprawa jest juz zamknieta!!!");
                }
            }
            else
                out.println("Nie znaleziono imeia!!!");
            out.println("</body>");
            out.println("</html>");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CloseServlet.class.getName()).log(Level.SEVERE, null, ex);
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
