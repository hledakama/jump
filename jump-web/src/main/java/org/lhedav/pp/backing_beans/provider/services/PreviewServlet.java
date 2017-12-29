/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lhedav.pp.backing_beans.provider.services;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import javax.ejb.EJB;
import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.lhedav.pp.business.logic.SellerEJB;
import org.lhedav.pp.business.model.service.Itemdata;

/**
 *
 * @author client
 */
@WebServlet(name = "PreviewServlet", urlPatterns = {"/PreviewServlet/*"})
public class PreviewServlet extends HttpServlet {
@Inject
private AddItem additemBean;
    @EJB
private SellerEJB provider_services;
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

        OutputStream out = response.getOutputStream();
        response.setHeader("Expires", "Sat, 6 May 1995 12:00:00 GMT");
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        
        String model_id = request.getParameter("id");
        try {
            //https://stackoverflow.com/questions/2633112/get-jsf-managed-bean-by-name-in-any-servlet-related-class
            //System.out.println("preview1");
            HttpSession session = request.getSession(false);
            if (additemBean != null) {
                //System.out.println("preview2");                
                Part theFile = null;
                if((model_id != null) && (!model_id.equals("-1"))){
                    Itemdata theItemdata = provider_services.getItemdataById(model_id);
                    theFile = theItemdata.getFile();                  
                }
                else{                    
                    theFile = additemBean.getItemdata().getFile(); 
                }
                if (theFile != null) {
                    //System.out.println("preview3");
                    BufferedImage image = ImageIO.read(theFile.getInputStream());
                    BufferedImage resizedImage = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
                    Graphics2D g = resizedImage.createGraphics();
                    g.drawImage(image, 0, 0, 100, 100, null);
                    g.dispose();
                    ImageIO.write(resizedImage, "png", out);                    
                }
            }
        } finally {
            out.close();
            //System.out.println("preview4");
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
