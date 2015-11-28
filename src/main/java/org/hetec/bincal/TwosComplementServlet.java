package org.hetec.bincal;

import org.hetc.binaryNumber.BinaryNumber;
import org.hetc.binaryNumber.BinaryNumberFactory;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by patrick on 24.11.15.
 */
@WebServlet("/twos")
public class TwosComplementServlet extends HttpServlet {

    @Inject
    private BinaryNumberFactory factory;

    private static final String FIELD = "field";
    private static final String TARGET = "target";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher = null;
        final String field = request.getParameter(FIELD);
        final String number = request.getParameter(field);
        final String dec = request.getParameter("dec");
        final String targetPage = request.getParameter(TARGET);
        if(!validateInput(number)){
            request.setAttribute(field, number);
            request.setAttribute("dec", dec);
            dispatcher = request.getRequestDispatcher(targetPage);
            dispatcher.forward(request,response);
        }else{
            request.setAttribute(field, calcTwosComplement(number));
            dispatcher = request.getRequestDispatcher(targetPage);
            request.setAttribute("dec", dec);
            dispatcher.forward(request,response);
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


