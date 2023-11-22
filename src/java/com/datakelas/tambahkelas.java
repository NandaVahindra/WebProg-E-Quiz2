/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.datakelas;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 *
 * @author Nandavahindra
 */
@WebServlet(name = "tambahkelas", urlPatterns = {"/tambahkelas"})
public class tambahkelas extends HttpServlet {

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
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet tambahkelas</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet tambahkelas at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        String k_name = request.getParameter("name");
        String k_hari = request.getParameter("hari");
        String k_jam = request.getParameter("jam");
        String k_ruangan = request.getParameter("ruangan");   
        String semesterParam = request.getParameter("semester");
        int k_semester = Integer.parseInt(semesterParam);
        String k_th = request.getParameter("tahunAjaran");
        
        Connection con = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_project_ets?useSSL=false","root","");
            PreparedStatement pst = con.prepareStatement("INSERT INTO data_kelas (name, hari, jam, ruangan, semester, tahunAjaran) VALUES (?, ?, ?, ?, ?, ?);");
            pst.setString(1, k_name);
            pst.setString(2, k_hari);
            pst.setString(3, k_jam);
            pst.setString(4, k_ruangan);
            pst.setInt(5, k_semester);
            pst.setString(6, k_th);
            
            int rowsAffected = pst.executeUpdate();
            
            if (rowsAffected > 0) {
                // Update successful
                request.setAttribute("status", "success");
                response.sendRedirect("halaman_dashboard/data_kelas/index.jsp");
            } else {
                // Update failed
                request.setAttribute("status", "failed");
            }
            con.close();
        }catch(Exception e)
        {
             e.printStackTrace();
        }

        
        
        
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
