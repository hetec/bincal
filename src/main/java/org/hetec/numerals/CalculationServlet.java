/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hetec.numerals;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hetc.binaryNumber.BinaryNumber;

/**
 *
 * @author patrick
 */
@WebServlet(name = "CalculationServlet", urlPatterns = {"/calculate"})
public class CalculationServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        final RequestDispatcher rd = request.getRequestDispatcher("calculate.jsp");
        final String[] numbers = request.getParameterValues("binaryNumber");
        final String op = request.getParameter("operation");
        final List<BinaryNumber> binaryNumbers = this.convertParametersToBinaryNumbers(numbers);
        request.setAttribute("result", this.calculate(binaryNumbers.get(0), binaryNumbers.get(1), op).asLong());
        rd.forward(request, response);
    }
    
    private List<BinaryNumber> convertParametersToBinaryNumbers(String[] binaryNumbers){
        List<BinaryNumber> bins = new ArrayList<>();
        for(String bin : binaryNumbers){
            bins.add(BinaryNumber.of(bin));
        }
        return bins;
    }
    
    private BinaryNumber calculate(BinaryNumber bin1, BinaryNumber bin2, String op){
        BinaryNumber result = null;
//        if("+".equals(op)){
//           result = bin1.add(bin2);
//        }
        
        result = BasicOperation.fromString(op).apply(bin1, bin2);
        return result;
    }


}
