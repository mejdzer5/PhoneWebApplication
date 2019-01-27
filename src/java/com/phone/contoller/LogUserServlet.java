/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phone.contoller;

import com.phone.model.PhoneUserDao;
import com.phone.model.PhoneUsers;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author User
 */
public class LogUserServlet extends HttpServlet {

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
        
        PhoneUserDao phoneUser = new PhoneUserDao();
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LogUserServlet!!!</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LogUserServlet at " + request.getContextPath() + "</h1>");
            out.println("<hr/>");
            ServletContext context = getServletContext();
            
            HttpSession session = request.getSession();
            
            // Porabranie danych z formularza
            String login = request.getParameter("login");
            String password = request.getParameter("password");
            
            List<PhoneUsers> myUsers = phoneUser.getUsers();
            
            //out.println("początek!!!");
            
            //out.println("usersi!!");
            out.println(phoneUser.initDB());
            out.println(phoneUser.pokazUserDane());
            
            out.println("attrybuty!!!");
            out.println(login+"to to");
            out.println(password+"to to");
            
            out.println("login : "+session.getAttribute("login"));
            if(login == null && password == null){
                login = session.getAttribute("login").toString();
                password = session.getAttribute("password").toString();
                out.println("działa!!!");
            }
           // out.println("cos jeszcze!!!");
           // out.println(myUsers);
            
            out.println("size!!!");
            int usersSize = myUsers.size();
            
            out.println(usersSize);
            out.println(login);
            
            for(PhoneUsers phu: myUsers){
                out.println(phu.getLogin());
               if((login.equals(phu.getLogin())) && (password.equals(phu.getPassword()))){
                    session.setAttribute("login", login);
                    session.setAttribute("password", password);
                    session.setAttribute("name", phu.getName());
                    session.setAttribute("surname", phu.getSurname());
               }
            }
                      
            out.println("koniec!!!");
            out.println("<hr/>");
            
            if(session.getAttribute("login") != null){
                out.println("<hr/>");
                out.println("Zostałeś logowany jako : " + session.getAttribute("login"));
                out.println("Reszta danych: "+session.getAttribute("password")+session.getAttribute("name")+session.getAttribute("surname"));
                out.println("<br/><a href='log_out.do'>Wyloguj</a>");
                out.println("<ul>");
                out.println("<li><a href='servis_phone_form.view'>dodaj naprawę</a></li>");
                out.println("<li><a href='search_form.view'>wyszukaj naprawę</a></li>");
                out.println("<li><a href='close_form.view'>zamknij naprawę</a></li>");
                out.println("</ul>");
            }
            else{
                out.println("<hr/>");
                out.println("Błędny login lub hasło");
            }
            out.println("<hr/>");
            out.println("<br/><a href='index.html'>Home</a>");
            
            out.println("</body>");
            out.println("</html>");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LogUserServlet.class.getName()).log(Level.SEVERE, null, ex);
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
