package org.hetec.bincal;

import org.hetc.binaryNumber.BinaryNumber;
import org.hetc.binaryNumber.BinaryNumberFactory;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by patrick on 24.11.15.
 */
@WebServlet(name = "TwosComplementServlet")
public class TwosComplementServlet extends HttpServlet {

    @Inject
    private BinaryNumberFactory factory;

    private static final String TOWS = "twosComplement";
    private static final String INPUT = "binaryNumber";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String number = request.getParameter(INPUT);
        if(!validateInput(number)){
            request.setAttribute(TOWS, number);
        }else{
            request.setAttribute(TOWS, calcTwosComplement(number));
        }

    }

    private boolean validateInput(String number){
        return number.charAt(0) == '-';
    }

    private String removeSign(String number){
        return number.substring(1,number.length());
    }

    private String calcTwosComplement(String number){
        final BinaryNumber binaryNumber = factory.instanceOf(removeSign(number));
        return binaryNumber.twosComplement().toString();
    }
}


