/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phone.view;

import com.phone.model.PhoneUserDao;
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
public class LogUserFormServlet extends HttpServlet {

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
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        
        PhoneUserDao phoneUser = new PhoneUserDao();
        
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LogUserServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LogUserServlet</h1>");
            
            out.println("<hr/>");
            out.println("coś tam, coś tam "+phoneUser.initDB());
            out.println("coś tam, coś tam "+phoneUser.pokazUserDane());
            out.println("<hr/>");
            if(session.getAttribute("login") != null){
            out.println("Jesteś zalogowany jako : "+session.getAttribute("login"));
            
            out.println("<br/><a href='log_out.do'>Wyloguj</a>");
            }
            else{
            
            String login = request.getParameter("login");
            String password = request.getParameter("password");
            
            out.println("<form action='log_user_servlet.do' method='POST'>");

            out.print("  Login: <input type='text' name='login' ");
            if (login == null) {
                login = "";
            }
            out.println("value = '" + login + "' /> <br/><br/>");

            out.print("  Password: <input type='password' name='password' ");
            if (password == null) {
                password = "";
            }
            out.println("value = '" + password + "' /> <br/><br/>");
            
            out.println("  <input type='Submit' value='Login' />");
            out.println("</form>");
            }
            out.println("<hr/>");
            out.println("<br/><a href='index.html'>Home</a>");
            
            out.println("</body>");
            out.println("</html>");
        }
        catch (ClassNotFoundException ex) {
            Logger.getLogger(LogUserFormServlet.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            out.close();
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
