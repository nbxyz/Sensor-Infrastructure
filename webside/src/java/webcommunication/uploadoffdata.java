/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webcommunication;

import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.Naming;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Mustafa Sidiqi
 */
@WebServlet(name = "uploadoffdata", urlPatterns = {"/uploadoffdata"})
public class uploadoffdata extends HttpServlet {

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
            out.println("<title>Servlet uploadDataServlet</title>");
            out.println("</head>");
            out.println("<body>");

            WebInterface db = null;
            try {
                db = (WebInterface) Naming.lookup("rmi://ubuntu4.javabog.dk:53168/data");
            } catch (Exception e) {
                out.println("<br>");
                out.println("<h1>" + "No Connection To Database." + "</h1>");
                out.println("<h1>" + "Redirecting to home..." + "</h1>");
                response.addHeader("REFRESH", "5;URL=index.html");
                out.println("</body>");
                out.println("</html>");
                e.printStackTrace();
            }

            try {
                int ID = Integer.parseInt(request.getParameter("ID"));
                String loc = request.getParameter("loc");
                String type = request.getParameter("type");
                String unit = request.getParameter("unit");
                int value = Integer.parseInt(request.getParameter("value"));
                String date = request.getParameter("date");

                db.CallinsertData("expdata", ID, loc, type, unit, value, date, 2);
                out.println("<h1>" + "Uploading data..." + "</h1>");
                out.println("<h1>" + "Redirecting to home. Please wait." + "</h1>");
                response.addHeader("REFRESH", "5;URL=index.html");
                //response.sendRedirect("index.html"); //logged-in page      		

            } catch (Throwable theException) {
                System.out.println(theException);
            }

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
