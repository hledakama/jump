/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lhedav.pp.backing_beans.provider.services;

import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.lhedav.pp.business.json.ItemdataJsonBuilder;
import org.lhedav.pp.business.logic.ProviderEJB;
import org.lhedav.pp.business.model.service.Itemdata;

/**
 *
 * @author client
 */
@WebServlet(name = "ProviderServlet", urlPatterns = {"/itemdata_details"})
public class ProviderServlet extends HttpServlet {
private ServletContext context;
            @EJB
private ProviderEJB provider_services;

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.context = config.getServletContext();
    }

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
            out.println("<title>Servlet ProviderServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProviderServlet at " + request.getContextPath() + "</h1>");
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
        //processRequest(request, response);
    String action = request.getParameter("action");
    String targetId = request.getParameter("id");

    if (targetId != null) {
        targetId = targetId.trim().toLowerCase();
    } else {
        context.getRequestDispatcher("targetId /error.jsf").forward(request, response);
        return;
    }
    if (action != null) {
        action = action.trim().toLowerCase();
    } else {
        context.getRequestDispatcher("action /error.jsf").forward(request, response);
        return;
    }
    
    switch(action){
        case "address_details":
            Itemdata theItemdata = provider_services.getItemdataById(targetId);
            if(theItemdata != null){
                JsonObject theAnswer = ItemdataJsonBuilder.buildItemdata(theItemdata);
                response.setContentType("application/json;charset=UTF-8");
                try (PrintWriter out = response.getWriter()) {
                    out.print(theAnswer);
                }
            }
            break;
            
        case "blablabla":
            break;
            
        default: 
            break;
    }
    
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
