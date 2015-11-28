package org.hetec.bincal;

import org.hetc.binaryNumber.BinaryNumber;
import org.hetc.binaryNumber.BinaryNumberFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigInteger;

import static org.junit.Assert.*;

/**
 * Created by patrick on 24.11.15.
 */
@RunWith(MockitoJUnitRunner.class)
public class TwosComplementServletTest {

    @InjectMocks
    TwosComplementServlet servlet = new TwosComplementServlet();

    @Mock
    private BinaryNumberFactory factory;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private RequestDispatcher dispatcher;

    @Mock
    private HttpSession session;


    private final String testInputSigned = "-111000111";
    private final String testInputUnsigned = "111000111";
    private final BinaryNumber spy = spy(BinaryNumber.of("111000111"));
    private final String testTwosComplement = BinaryNumber.of("111000111").twosComplement().toString();

    @Before
    public void setup(){
        doReturn(session).when(request).getSession(false);
        doReturn(dispatcher).when(request).getRequestDispatcher(anyString());
    }


    @Test
    public void test_returns_tows_complement_representation() throws Exception{
        doReturn("bin").when(request).getParameter("field");
        doReturn(testInputSigned).when(session).getAttribute("bin");
        doReturn(spy).when(factory).instanceOf(testInputUnsigned);

        servlet.doGet(request,response);

        verify(session, times(1)).getAttribute("bin");
        verify(request, times(1)).getParameter("field");
        verify(factory, times(1)).instanceOf(anyString());
        verify(spy).twosComplement();
        verify(request).setAttribute("bin", testTwosComplement);
        verify(request).setAttribute("showUndo", true);
    }

    @Test
    public void test_returns_input_value_for_unsigned_number() throws Exception{
        doReturn("bin").when(request).getParameter("field");
        doReturn(testInputUnsigned).when(session).getAttribute("bin");

        servlet.doGet(request,response);

        verify(session, times(1)).getAttribute("bin");
        verify(request, times(1)).getParameter("field");
        verify(factory, never()).instanceOf(anyString());
        verify(spy, never()).twosComplement();
        verify(request).setAttribute("bin", testInputUnsigned);
    }


    @Test
    public void test_returns_signed_input_on_parameter_undo() throws Exception{
        doReturn("bin").when(request).getParameter("field");
        doReturn(testInputSigned).when(session).getAttribute("bin");
        doReturn("true").when(request).getParameter("undo");

        servlet.doGet(request,response);

        verify(request, times(1)).getParameter("undo");
        verify(session, times(1)).getAttribute("bin");
        verify(request, times(1)).getParameter("field");
        verify(factory, never()).instanceOf(anyString());
        verify(spy, never()).twosComplement();
        verify(request).setAttribute("bin", testInputSigned);
    }


    @Test
    public void test_forward_for_valid_input() throws Exception{
        doReturn("bin").when(request).getParameter("field");
        doReturn(testInputSigned).when(session).getAttribute("bin");
        doReturn("test.jsp").when(request).getParameter("target");
        doReturn(spy).when(factory).instanceOf(testInputUnsigned);

        servlet.doGet(request,response);

        verify(request).getRequestDispatcher("test.jsp");
        verify(dispatcher).forward(request,response);
    }

    @Test
    public void test_forward_for_invalid_input() throws Exception{
        doReturn("bin").when(request).getParameter("field");
        doReturn(testInputUnsigned).when(session).getAttribute("bin");
        doReturn("test.jsp").when(request).getParameter("target");

        servlet.doGet(request,response);

        verify(request).getRequestDispatcher("test.jsp");
        verify(dispatcher).forward(request,response);
    }

    @Test
    public void test_returns_empty_numbers_for_not_existing_session_on_undo() throws Exception{
        doReturn(null).when(request).getSession(false);
        doReturn("test.jsp").when(request).getParameter("target");

        servlet.doGet(request,response);

        verify(request).getSession(false);
        verify(request).getRequestDispatcher("test.jsp");
        verify(dispatcher).forward(request,response);
    }

}