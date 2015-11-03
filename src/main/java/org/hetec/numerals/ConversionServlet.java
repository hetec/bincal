package org.hetec.numerals;

import java.io.IOException;
import java.math.BigInteger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hetc.binaryNumber.BinaryNumber;

@WebServlet(name = "ConversionServlet", urlPatterns = {"/convert"})
public class ConversionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String binaryNumber = request.getParameter("bin");
        String decimalNumber = request.getParameter("dec");
        String lastChangedValue = request.getParameter("last");
        String errMessage = "";
        
        errMessage = this.handleConvertion(lastChangedValue, binaryNumber, decimalNumber, request);
        
        request.setAttribute("err", errMessage);
        RequestDispatcher reqDispatcher = request.getRequestDispatcher("index.jsp");
        reqDispatcher.forward(request, response);
    }
    
    private boolean checkNotNullOrEmpty(String s){
        if(s != null)
            if(s.length() > 0)
                return true;
        return false;
    }
    
    private String handleConvertion(String lastChangedValue,
                                  String binaryNumber,
                                  String decimalNumber,
                                  HttpServletRequest request){
        String message = "";
        
        try {
            if("bin".equals(lastChangedValue)){
                decimalNumber = this.convertToDecimal(binaryNumber);
                message = decimalNumber;
            }else if("dec".equals(lastChangedValue)){
                binaryNumber = this.convertToBinary(decimalNumber);
                message = binaryNumber;
            }
        } catch (NumberFormatException numberFormatEx) {
            message = "Your input has an illegal format! Please use only valid numbers.";
        }
        
        request.setAttribute("bin", binaryNumber);
        request.setAttribute("dec", decimalNumber);
        
        return message;
    }
    
    private String convertToDecimal(String binaryNumber){
        if(checkNotNullOrEmpty(binaryNumber)){
            final BinaryNumber tmp = BinaryNumber.of(binaryNumber);
            return tmp.asBigInt().toString();
        }else{
            return "";
        }
    }
    
    private String convertToBinary(String decimalNumber){
        if(checkNotNullOrEmpty(decimalNumber)){
            return BinaryNumber.of(new BigInteger(decimalNumber)).toString();
        }else{
            return "";
        }
    }
}
