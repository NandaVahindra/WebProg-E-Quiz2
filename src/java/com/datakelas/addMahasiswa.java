/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.datakelas;

import jakarta.servlet.RequestDispatcher;
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
import java.sql.ResultSet;

/**
 *
 * @author Nandavahindra
 */
@WebServlet(name = "addMahasiswa", urlPatterns = {"/addMahasiswa"})
public class addMahasiswa extends HttpServlet {

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
            out.println("<title>Servlet addMahasiswa</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet addMahasiswa at " + request.getContextPath() + "</h1>");
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
        String nrpParam = request.getParameter("NRP");
        int m_nrp = Integer.parseInt(nrpParam);
        String kid = request.getParameter("id");
        String trimkid = kid.trim();
        int k_id = Integer.parseInt(trimkid);
        int m_id=0;
        RequestDispatcher dispatcher = null;
        Connection con = null;
        
        PrintWriter out = response.getWriter();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_project_ets?useSSL=false","root","");
            PreparedStatement pst = con.prepareStatement("SELECT id FROM datamahasiswa WHERE NRP=?;");
            pst.setInt(1, m_nrp);
            ResultSet rs = pst.executeQuery();
            
            if (rs.next()) {
                m_id = rs.getInt("id");
            }
            else{
                out.println("NRP not found");
                return;
            }
        }catch(Exception e)
        {
             e.printStackTrace();
        }


        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_project_ets?useSSL=false","root","");
            PreparedStatement pst = con.prepareStatement("INSERT INTO class_student (mahasiswa_id, kelas_id) VALUES (?, ?);");
            pst.setInt(1, m_id);
            pst.setInt(2, k_id);
 
            int rowsAffected = pst.executeUpdate();
            
            if (rowsAffected > 0) {
                response.sendRedirect("halaman_dashboard/data_kelas/index.jsp");
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
