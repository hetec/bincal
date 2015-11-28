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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

/**
 * Created by patrick on 24.11.15.
 */
@WebServlet("/twos")
public class TwosComplementServlet extends HttpServlet {

    @Inject
    private BinaryNumberFactory factory;

    private static final String FIELD = "field";
    private static final String TARGET = "target";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        final String targetPage = request.getParameter(TARGET);
        RequestDispatcher dispatcher = request.getRequestDispatcher(targetPage);
        HttpSession session = request.getSession(false);
        if(Objects.isNull(session)){
            dispatcher.forward(request,response);
        }else{
            final String undo = request.getParameter("undo");
            final String field = request.getParameter(FIELD);
            final String number = (String)session.getAttribute(field);
            if("true".equals(undo)){
                request.setAttribute(field, number);
                dispatcher.forward(request,response);
            }else{
                if(!validateInput(number)){
                    request.setAttribute(field, number);
                    dispatcher.forward(request,response);
                }else{
                    request.setAttribute(field, calcTwosComplement(number));
                    request.setAttribute("showUndo", true);
                    dispatcher.forward(request,response);
                }
            }
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


