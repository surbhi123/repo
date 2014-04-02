/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pp;

import java.sql.Statement;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Aman
 */
public class servlet1 extends HttpServlet {

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
        String add = request.getParameter("a1");
        String[] str = new String[100];
        int len = add.length();
        int temp = 0;
        int t = 1;
        int id = 0;
        int k = 0;

        for (int i = 0; i < len; i++) {
            char abc = add.charAt(i);
            if (abc == ',') {

                str[k] = add.substring(temp, i);
                if (t == 1) {
                    if (!(str[k].endsWith(".com") && (str[k].startsWith("http://") || str[k].startsWith("https://")))) {
                        out.print("invalid url");
                        return;
                    }
                }

                temp = i + 1;
                k++;
                t++;
            }
            if (i == (len - 1)) {
                str[k] = add.substring(temp, i + 1);
                // out.println(str[k]);
            }
        }
        //out.print(str[1]);

        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Connection cc = DriverManager.getConnection("jdbc:odbc:jstll", "sa", "perx");

            Statement stmt = cc.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            // out.println("executing query");
            stmt.executeUpdate("INSERT INTO url1 (url) VALUES('" + str[0] + "')");

            //out.println("OK");
            PreparedStatement pp2 = cc.prepareStatement("select * from url1 where( url='" + str[0] + "')");
            // out.println("OK");
            ResultSet rr3 = pp2.executeQuery();
            while (rr3.next()) {
                id = rr3.getInt(1);
                //out.println(id);

            }
            for (int i = 1; i < t; i++) {
                stmt.executeUpdate("INSERT INTO tag1 (tag,url_id) VALUES('" + str[i] + "','" + id + "')");

                //pp1.executeQuery();
            }
            ResultSet rr4 = stmt.executeQuery("SELECT url,tag FROM URL1,TAG1 WHERE URL1.url_id=TAG1.url_id");
            while (rr4.next()) {
                out.println("url:" + rr4.getString(1));
                out.println("tag:" + rr4.getString(2) + "\n");
                out.println("\n");
            }

        } catch (Exception ee) {
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
