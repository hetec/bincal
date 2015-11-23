package org.hetec.bincal;

import java.io.IOException;
import java.math.BigInteger;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hetc.binaryNumber.BinaryNumber;
import org.hetc.binaryNumber.BinaryNumberFactory;

@WebServlet(name = "ConversionServlet", urlPatterns = {"/convert"})
public class ConversionServlet extends HttpServlet {
    static final String ERROR_MESSAGE_ID = "err";
    static final String BINARY_NUMBER_PARAM = "bin";
    static final String DECIMAL_NUMBER_PARAM = "dec";
    static final String LAST_USED_PARAM = "last";
    static final String TARGET = "convert.jsp";
    static final String INVALID_FORMAT_EX = "Please use only valid binary numbers! ";

    @Inject
    private BinaryNumberFactory binaryFactory;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String binaryNumber = request.getParameter(BINARY_NUMBER_PARAM);
        String decimalNumber = request.getParameter(DECIMAL_NUMBER_PARAM);
        String lastChangedValue = request.getParameter(LAST_USED_PARAM);
        String errMessage = "";
        
        errMessage = this.handleConversion(lastChangedValue, binaryNumber, decimalNumber, request);
        
        request.setAttribute(ERROR_MESSAGE_ID, errMessage);
        RequestDispatcher reqDispatcher = request.getRequestDispatcher(TARGET);
        reqDispatcher.forward(request, response);
    }
    
    private boolean checkNotNullOrEmpty(String s){
        if(s != null)
            if(s.length() > 0)
                return true;
        return false;
    }
    
    private String handleConversion(String lastChangedValue,
                                  String binaryNumber,
                                  String decimalNumber,
                                  HttpServletRequest request){
        String message = "";
        
        try {
            if(BINARY_NUMBER_PARAM.equals(lastChangedValue)){
                decimalNumber = this.convertToDecimal(binaryNumber);
            }else if(DECIMAL_NUMBER_PARAM.equals(lastChangedValue)){
                binaryNumber = this.convertToBinary(decimalNumber);
            }
        } catch (NumberFormatException numberFormatEx) {
            message = INVALID_FORMAT_EX;
        }
        
        request.setAttribute(BINARY_NUMBER_PARAM, binaryNumber);
        request.setAttribute(DECIMAL_NUMBER_PARAM, decimalNumber);
        
        return message;
    }
    
    private String convertToDecimal(String binaryNumber){
        if(checkNotNullOrEmpty(binaryNumber)){
            final BinaryNumber tmp = binaryFactory.instanceOf(binaryNumber);
            return tmp.asBigInt().toString();
        }else{
            return "";
        }
    }
    
    private String convertToBinary(String decimalNumber){
        if(checkNotNullOrEmpty(decimalNumber)){
            return binaryFactory.instanceOf(new BigInteger(decimalNumber)).toSignedString();
        }else{
            return "";
        }
    }
}
