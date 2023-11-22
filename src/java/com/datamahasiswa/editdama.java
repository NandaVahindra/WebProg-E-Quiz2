/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.datamahasiswa;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Nandavahindra
 */
@WebServlet(name = "editdama", urlPatterns = {"/editdama"})
public class editdama extends HttpServlet {

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
            out.println("<title>Servlet editdama</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet editdama at " + request.getContextPath() + "</h1>");
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
        String idParam = request.getParameter("id");
        int m_id = Integer.parseInt(idParam);
        String m_name = request.getParameter("name");
        String m_email = request.getParameter("email");
        String nrpParam = request.getParameter("NRP");
        int m_nrp = Integer.parseInt(nrpParam);
        String batchParam = request.getParameter("Batch");
        int m_batch = Integer.parseInt(batchParam);
        String m_major = request.getParameter("Major");
        Connection con = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_project_ets?useSSL=false","root","");
            PreparedStatement pst = con.prepareStatement("UPDATE datamahasiswa SET name=?, email=?, NRP=?, Batch=?, Major=? WHERE id=?");
            pst.setString(1, m_name);
            pst.setString(2, m_email);
            pst.setInt(3, m_nrp);
            pst.setInt(4, m_batch);
            pst.setString(5, m_major);
            pst.setInt(6, m_id);
            
            int rowsAffected = pst.executeUpdate();
            
            if (rowsAffected > 0) {
                // Update successful
                request.setAttribute("status", "success");
                response.sendRedirect("halaman_dashboard/data_mahasiswa/index.jsp");
            } else {
                // Update failed
                request.setAttribute("status", "failed");
            }
            con.close();
        }catch(Exception e)
        {
            
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
