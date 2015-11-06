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
    static final String MISSING_ARGUMENTS_EX = "Two binary numbers are necessary to process a valid calculation! ";
    static final String DIVISION_BY_ZERO_EX = "Sorry, division by zero is not allowed!";
    static final String NO_VALID_OP_EX = "No valid operation! Please use '+','-','*' or '/'! ";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        final RequestDispatcher rd = request.getRequestDispatcher(TARGET);
        final String[] numbers = request.getParameterValues(NUMBERS_PARAM);
        final String op = request.getParameter(OPERATION_PARAM);
        String message = validateParams(numbers,op);
        if(validateParams(numbers,op).isEmpty()){
            final List<BinaryNumber> binaryNumbers = this.convertParametersToBinaryNumbers(numbers);
            message = this.handleCalculation(binaryNumbers, op, request);
        }
        request.setAttribute(MESSAGE,message);
        rd.forward(request, response);

        
    }
    
    private String handleCalculation(List<BinaryNumber> binaryNumbers, String operation, HttpServletRequest request){
        String message = "";
        BinaryNumber bin1 = binaryNumbers.get(0);
        BinaryNumber bin2 = binaryNumbers.get(1);
        try {
            request.setAttribute(RESULT, this.calculate(bin1, bin2, operation).toSignedString());
        } catch (ArithmeticException arithEx) {
            message = DIVISION_BY_ZERO_EX;
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

    private String validateParams(String[] numbers, String op){
        String message = "";
        if(numbers.length < 2 || numbers[0].isEmpty() || numbers[1].isEmpty()) {
            message += MISSING_ARGUMENTS_EX;
        }
        if(!BasicOperation.isValidOperation(op)){
            message += NO_VALID_OP_EX;
        }
        return message;
    }

    private void restoreFieldValues(String[] numbers, HttpServletRequest request){

    }


}
