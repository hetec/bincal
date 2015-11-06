/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hetec.bincal;

import java.io.IOException;
import java.util.ArrayList;
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

    static final String RESULT = "result";
    static final String NUMBERS_PARAM = "binaryNumber";
    static final String OPERATION_PARAM = "operation";
    static final String MESSAGE = "message";
    static final String TARGET = "calculate.jsp";
    static final String MISSING_ARGUMENTS_EX = "Too less arguments!";
    static final String DIVISION_BY_ZERO_EX = "Sorry, division by zero is not allowed!";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        final RequestDispatcher rd = request.getRequestDispatcher(TARGET);
        final String[] numbers = request.getParameterValues(NUMBERS_PARAM);
        final String op = request.getParameter(OPERATION_PARAM);
        final List<BinaryNumber> binaryNumbers = this.convertParametersToBinaryNumbers(numbers);
        request.setAttribute(MESSAGE, this.handleCalculation(binaryNumbers, op, request));
        rd.forward(request, response);
        
    }
    
    private String handleCalculation(List<BinaryNumber> binaryNumbers, String operation, HttpServletRequest request){
        String message = "";
        if(binaryNumbers.size() >= 2){
            BinaryNumber bin1 = binaryNumbers.get(0);
            BinaryNumber bin2 = binaryNumbers.get(1);
            try {
                request.setAttribute(RESULT, this.calculate(bin1, bin2, operation).toSignedString());
            } catch (ArithmeticException arithEx) {
                message = DIVISION_BY_ZERO_EX;
            }
        }else{
            message = MISSING_ARGUMENTS_EX;
        }
        
        return message;
    }
    
    private List<BinaryNumber> convertParametersToBinaryNumbers(String[] binaryNumbers){
        List<BinaryNumber> bins = new ArrayList<>();
        for(String bin : binaryNumbers){
            try {
                bins.add(BinaryNumber.of(bin));
            } catch (IllegalArgumentException illegalArgEx) {
                bins.add(BinaryNumber.ZERO);
            }
        }
        return bins;
    }
    
    private BinaryNumber calculate(BinaryNumber bin1, BinaryNumber bin2, String op){
        BinaryNumber result = null;
        result = BasicOperation.fromString(op).apply(bin1, bin2);
        return result;
    }


}
