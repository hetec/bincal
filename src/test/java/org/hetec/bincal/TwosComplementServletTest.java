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

    private final String testInputSigned = "-111000111";
    private final String testInputUnsigned = "111000111";
    private final BinaryNumber spy = spy(BinaryNumber.of("111000111"));
    private final String testTwosComplement = BinaryNumber.of("111000111").twosComplement().toString();


    @Test
    public void test_returns_tows_complement_representation() throws Exception{
        doReturn(testInputSigned).when(request).getParameter("binaryNumber");
        doReturn(spy).when(factory).instanceOf(testInputUnsigned);

        servlet.doGet(request,response);

        verify(request, times(1)).getParameter("binaryNumber");
        verify(factory, times(1)).instanceOf(anyString());
        verify(spy).twosComplement();
        verify(request).setAttribute("twosComplement", testTwosComplement);
    }

}