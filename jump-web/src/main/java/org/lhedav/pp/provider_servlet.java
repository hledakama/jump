/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lhedav.pp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.json.JsonArray;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
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
public class provider_servlet extends HttpServlet {
    private ServletContext context;
                @EJB
    private ProviderEJB provider_services;
    @Override
    public void init(ServletConfig config) throws ServletException {
        //super.init(); //To change body of generated methods, choose Tools | Templates.
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
            out.println("<title>Servlet provider_servlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet provider_servlet at " + request.getContextPath() + "</h1>");
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
        String itemdataId = request.getParameter("id");
        //processRequest(request, response);
        response.setContentType("application/json;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        Itemdata theData = provider_services.getItemdataById(itemdataId);
        JsonArray jsonDetails = ItemdataJsonBuilder.buildProviderAddress(theData);
        if(jsonDetails != null){
            try (PrintWriter out = response.getWriter()) {            
                out.print(jsonDetails);
            }
        }
        else{
            //nothing to show
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
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
            throws ServletException, IOException{
 
        // 1. get received JSON data from request
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String json = "";
        if(br != null){
            json = br.readLine();
        }
 
        // 2. initiate jackson mapper
       //ObjectMapper mapper = new ObjectMapper();
 
        // 3. Convert received JSON to Article
      //  Article article = mapper.readValue(json, Article.class);
 
        // 4. Set response type to JSON
        response.setContentType("application/json");            
 
        // 5. Add article to List<Article>
     //   articles.add(article);
 
        // 6. Send List<Article> as JSON to client
      //  mapper.writeValue(response.getOutputStream(), articles);
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
