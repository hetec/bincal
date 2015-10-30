package org.hetec.numerals;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hetc.binaryNumber.BinaryNumber;

@WebServlet(name = "CalcuateServlet", urlPatterns = {"/calculate"})
public class CalcuateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String bin = request.getParameter("bin");
        String dec = request.getParameter("dec");
        String lastOne = request.getParameter("last");
        String errMessage = "";
        if(checkNotNullOrEmpty(lastOne)){
            if(checkNotNullOrEmpty(bin) && "bin".equals(lastOne)){
                final BinaryNumber tmp = BinaryNumber.of(bin);
                dec = tmp.asBigInt().toString();
            }else if(checkNotNullOrEmpty(dec) && "dec".equals(lastOne)){
                bin = BinaryNumber.of(new BigInteger(dec)).toString();
            }else {
                errMessage = "Missing input! Please enter a number.";
            }
        }else{
            errMessage = "Sorry, an unexpected error occured!";
        }
        request.setAttribute("err", errMessage);
        request.setAttribute("bin", bin);
        request.setAttribute("dec", dec);
        RequestDispatcher reqDispatcher = request.getRequestDispatcher("index.jsp");
        reqDispatcher.forward(request, response);
    }
    
    private static boolean checkNotNullOrEmpty(String s){
        if(s != null)
            if(s.length() > 0)
                return true;
        return false;
    }
}
