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
import javax.servlet.http.HttpSession;

/**
 *
 * @author Mustafa Sidiqi
 */
@WebServlet(name = "registerServlet", urlPatterns = {"/registerServlet"})
public class registerServlet extends HttpServlet {

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
            out.println("<title>Servlet registerServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet registerServlet at " + request.getContextPath() + "</h1>");

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

                String username = request.getParameter("user");
                String password = request.getParameter("pass");
                String email = request.getParameter("email");

                boolean reg = db.CallcreateUser(username, password, email, username);

                if (reg) {
                    response.sendRedirect("login.jsp"); //logged-in page      		
                } else {
                    out.println("<br>");
                    out.println("Something Went Wrong.");
                    out.println("<br>");
                    out.println("Try Again...");
                    response.addHeader("REFRESH", "5;URL=register.jsp");
                    out.println("</body>");
                    out.println("</html>");
                }
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
