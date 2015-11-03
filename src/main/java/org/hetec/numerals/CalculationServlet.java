/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hetec.numerals;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author patrick
 */
@WebServlet(name = "CalculationServlet", urlPatterns = {"/calculate"})
public class CalculationServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String[] numbers = request.getParameterValues("binaryNumber");
        String op = request.getParameter("operation");
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CalculationServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println(Arrays.toString(numbers));
            out.println(op);
            out.println("</body>");
            out.println("</html>");
        }
    }


}
