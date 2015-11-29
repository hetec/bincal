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

import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigInteger;

import static org.junit.Assert.*;

/**
 * Created by patrick on 29.11.15.
 */
@RunWith(MockitoJUnitRunner.class)
public class CookieFilterTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterChain chain;


    private CookieFilter filter;

    private Cookie[] cookies = {new Cookie("test","test")};

    @Before
    public void setup(){
        filter = new CookieFilter();
    }

    @Test
    public void test_add_message_if_cookies_are_not_enabled() throws Exception{
        doReturn(null).when(request).getCookies();

        filter.doFilter(request, response, chain);

        verify(response).addCookie(any(Cookie.class));
        verify(request).getCookies();
        verify(request).setAttribute("missingCookies", "Some Features are not available if cookies are disabled!");
        verify(chain).doFilter(request,response);
    }

    @Test
    public void test_add_no_message_if_cookies_are_enabled() throws Exception{
        doReturn(cookies).when(request).getCookies();

        filter.doFilter(request, response, chain);

        verify(response).addCookie(any(Cookie.class));
        verify(request).getCookies();
        verify(request, never()).setAttribute("missingCookies", "Some Features are not available if cookies are disabled!");
        verify(chain).doFilter(request,response);
    }

}