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
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigInteger;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by patrick on 22.11.15.
 */
@RunWith(MockitoJUnitRunner.class)
public class ConversionServletTest {


    @Mock
    private HttpServletRequest mockRequest;

    @Mock
    private HttpServletResponse mockResponse;

    @Mock
    private RequestDispatcher dispatcher;

    @Mock
    private BinaryNumberFactory factory;

    @InjectMocks
    private ConversionServlet servlet = new ConversionServlet();

    private BinaryNumber bin;

    private final String binaryNumber = "100101010";
    private final String decimalNumber = "298";

    @Before
    public void setUp(){
        bin = BinaryNumber.of(binaryNumber);
        when(mockRequest.getRequestDispatcher("convert.jsp")).thenReturn(dispatcher);

    }

    @Test
    public void test_set_correct_attributes_if_converting_a_binary_to_a_decimal_number() throws Exception{
        when(mockRequest.getParameter("bin")).thenReturn(binaryNumber);
        when(mockRequest.getParameter("dec")).thenReturn("");
        when(mockRequest.getParameter("last")).thenReturn("bin");
        when(factory.instanceOf(anyString())).thenReturn(bin);

        servlet.doGet(mockRequest, mockResponse);

        verify(factory).instanceOf(anyString());
        verify(mockRequest).setAttribute(eq("bin"), eq(binaryNumber));
        verify(mockRequest).setAttribute(eq("dec"), eq(decimalNumber));
        verify(mockRequest).setAttribute(eq("err"), eq(""));
    }

    @Test
    public void test_set_correct_attributes_if_converting_a_decimal_to_a_binary_number() throws Exception{
        when(mockRequest.getParameter("dec")).thenReturn(decimalNumber);
        when(mockRequest.getParameter("bin")).thenReturn("");
        when(mockRequest.getParameter("last")).thenReturn("dec");
        when(factory.instanceOf(any(BigInteger.class))).thenReturn(bin);

        servlet.doGet(mockRequest, mockResponse);

        verify(factory).instanceOf(isA(BigInteger.class));
        verify(mockRequest).setAttribute(eq("bin"), eq(binaryNumber));
        verify(mockRequest).setAttribute(eq("dec"), eq(decimalNumber));
        verify(mockRequest).setAttribute(eq("err"), eq(""));
    }

    @Test
    public void test_set_correct_attributes_if_no_value_is_set_for_last_changed() throws Exception{
        when(mockRequest.getParameter("bin")).thenReturn(binaryNumber);
        when(mockRequest.getParameter("dec")).thenReturn(decimalNumber);
        when(mockRequest.getParameter("last")).thenReturn("");
        when(factory.instanceOf(any(BigInteger.class))).thenReturn(bin);

        servlet.doGet(mockRequest, mockResponse);

        verify(factory, never()).instanceOf(anyString());
        verify(factory, never()).instanceOf(any(BigInteger.class));
        verify(mockRequest).setAttribute(eq("bin"), eq(binaryNumber));
        verify(mockRequest).setAttribute(eq("dec"), eq(decimalNumber));
        verify(mockRequest).setAttribute(eq("err"), eq(""));
    }

}