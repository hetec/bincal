package org.hetec.bincal;

import org.hetc.binaryNumber.BinaryNumber;
import org.hetc.binaryNumber.BinaryNumberFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigInteger;

import static org.junit.Assert.*;

/**
 * Created by patrick on 23.11.15.
 */
@RunWith(MockitoJUnitRunner.class)
public class CalculationServletTest {

    @InjectMocks
    private CalculationServlet calculationServlet = new CalculationServlet();

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private RequestDispatcher dispatcher;

    @Mock
    private BasicOperation basicOp;

    @Mock
    private BinaryNumberFactory factory;

    //73 and 455
    private String[] numbersParam = {"1001001", "111000111"};

    @Before
    public void setUp(){
        doReturn(numbersParam).when(request).getParameterValues("binaryNumber");
        doReturn(dispatcher).when(request).getRequestDispatcher(anyString());
        doReturn(BinaryNumber.of("1001001")).when(factory).instanceOf("1001001");
        doReturn(BinaryNumber.of("111000111")).when(factory).instanceOf("111000111");
    }

    @Test
    public void test_valid_addition_returns_a_binary_number() throws Exception{
        doReturn("+").when(request).getParameter("operation");
        String res = "1000010000";
        calculationServlet.doGet(request,response);
        verify(request).setAttribute("result", res);

    }

    @Test
    public void test_return_parameter_values_to_restore_input_fields() throws Exception{
        calculationServlet.doGet(request,response);
        verify(request).setAttribute("number1", "1001001");
        verify(request).setAttribute("number2", "111000111");
    }

}