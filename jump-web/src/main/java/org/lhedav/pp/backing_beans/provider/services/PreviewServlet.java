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
import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.lhedav.pp.business.model.common.Global;

/**
 *
 * @author client
 */
//https://docs.oracle.com/javaee/7/api/javax/servlet/annotation/MultipartConfig.html
//https://docs.oracle.com/javaee/7/api/javax/servlet/annotation/WebServlet.html
//http://www.codejava.net/java-ee/servlet/webservlet-annotation-examples
//https://docs.oracle.com/javaee/6/tutorial/doc/gmhal.html
@WebServlet(name = "PreviewServlet", urlPatterns = {"/PreviewServlet/*"})
/*@MultipartConfig(location="/opt/lhedav/images/tmp", fileSizeThreshold=1024*1024, 
    maxFileSize=1024*1024*5, maxRequestSize=1024*1024*5*5)*/
public class PreviewServlet extends HttpServlet {
@Inject
private AddItemBean addItemBean;
@Inject
private ModifyItemBean modifyItemBean;
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
        
        String source   = request.getParameter("source");
        String data     = request.getParameter("data");
        System.out.println("data: "+request.getParameter("data")+", source: "+source);        
            //https://www.ntu.edu.sg/home/ehchua/programming/java/JavaServlets.html   
            //http://www.codejava.net/java-ee/servlet/java-file-upload-example-with-servlet-30-api
            //https://stackoverflow.com/questions/2633112/get-jsf-managed-bean-by-name-in-any-servlet-related-class
        try {
            System.out.println("preview1");
            //HttpSession session = request.getSession(false);
            Part theFile = null;
            //https://stackoverflow.com/questions/2633112/get-jsf-managed-bean-by-name-in-any-servlet-related-class
                
                if (data.equals("-1")) {
                    if(addItemBean != null){
                        //AddItemBean addItemBean = (AddItemBean) session.getAttribute("beanName");
                        System.out.println("preview2 add dat is null");
                        if (addItemBean.getFile() != null) {
                            System.out.println("preview2 addItemBean bean --");
                            theFile = addItemBean.getFile();
                        }                         
                    }
                }
                else{
                    if(modifyItemBean != null){
                        //ModifyItemBean modifyItemBean = (ModifyItemBean) session.getAttribute("beanName");
                        System.out.println("preview2 modify data ok");
                        if(modifyItemBean.getFile() != null){
                            System.out.println("preview2 modify bean ++");
                            theFile = modifyItemBean.getFile();
                        }
                    }
                }
                if (theFile != null) {
                        System.out.println("preview3");
                        BufferedImage image = ImageIO.read(theFile.getInputStream());
                        BufferedImage  resizedImage = new BufferedImage(Global.IMAGE_MIN_WIDTH, Global.IMAGE_MIN_HEIGTH, BufferedImage.TYPE_INT_ARGB);
                        Graphics2D g = resizedImage.createGraphics();
                        g.drawImage(image, 0, 0, Global.IMAGE_MIN_WIDTH, Global.IMAGE_MIN_HEIGTH, null);
                        g.dispose();
                        ImageIO.write(resizedImage, "png", out);
                    }

        } finally {
            out.close();
            System.out.println("preview4");
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
