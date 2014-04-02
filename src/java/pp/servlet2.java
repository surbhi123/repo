/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Aman
 */
public class servlet2 extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String sTag = request.getParameter("s1");
        //String[] str = new String[11];
        //String sTag = search.substring(, search.length() - 1);
        out.println("tag:"+sTag);
       
        int url_id=0;
        try {
          Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Connection cc = DriverManager.getConnection("jdbc:odbc:jstll", "sa", "perx");
           Statement stmt=cc.createStatement();
           ResultSet rr1= stmt.executeQuery("SELECT tag,url_id FROM tag1 where tag='"+sTag+"' ");
            //PreparedStatement pp = cc.prepareStatement("SELECT tag,url_id FROM tag1 where tag='"+sTag+"' ");
             //ResultSet rr1 = pp.executeQuery();
            //out.println("tag:"+sTag);
            
            while(rr1.next()){

                String  tag=rr1.getString("tag");
             // out.println("tag:"+tag);
                url_id=rr1.getInt("url_id");
            //out.println("tag:"+tag);
             }
            Statement pp1 = cc.createStatement();
             ResultSet rr2 = pp1.executeQuery("SELECT url FROM url1 where url_id='" + url_id + "' ");
             while(rr2.next()){
            String url=rr2.getString("url");
            out.println("url:"+url);
             }
             
           



        } catch(Exception ee) {
            out.print(ee.getMessage());
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
