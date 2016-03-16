package org.hetec.bincal;

import org.hetc.binaryNumber.BinaryNumber;
import org.hetc.binaryNumber.BinaryNumberFactory;

import javax.inject.Inject;
import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
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
    private static final String ERR_MSG = "convertionErrorMessage";
    static final String INVALID_FORMAT_EX = "Two's complement calculation on invalid binary numbers is not possible!";
    static final String UNEXPECTED_EX = "Sorry, something went wrong :(";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String errorMsg = "";
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
                String validationMsg = validateInput(number);
                if(!validationMsg.isEmpty()){
                    request.setAttribute(field, number);
                    request.setAttribute(ERR_MSG, errorMsg);
                    dispatcher.forward(request,response);
                }else{
                    request.setAttribute(field, calcTwosComplement(number));
                    request.setAttribute("showUndo", true);
                    dispatcher.forward(request,response);
                }
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final HttpSession session = req.getSession(false);
        String sourcePage;
        if(Objects.nonNull(session)){
           sourcePage = (String)session.getAttribute("sourcePage");
        }else {
            sourcePage = "index.jsp";
        }
        resp.setHeader("location", sourcePage);
        resp.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
    }

    private String validateInput(String number){
        try{
            factory.instanceOf(number);
            if(number.charAt(0) != '-'){
                return UNEXPECTED_EX;
            }
            return "";
        }catch (NumberFormatException nfe){
            return INVALID_FORMAT_EX;
        }catch (Exception e){
            e.printStackTrace();
            return UNEXPECTED_EX;
        }
    }

    private String removeSign(String number){
        return number.substring(1,number.length());
    }

    private String calcTwosComplement(String number){
        final BinaryNumber binaryNumber = factory.instanceOf(removeSign(number));
        return binaryNumber.twosComplement().toString();
    }
}


