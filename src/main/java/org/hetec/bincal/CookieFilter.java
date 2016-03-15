package org.hetec.bincal;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * Created by patrick on 29.11.15.
 */
@WebFilter(filterName = "CookieFilter", urlPatterns = {"/index.jsp", "/calculate.jsp"})
public class CookieFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        Cookie testCookie = new Cookie("test", "test");
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpServletRequest request = (HttpServletRequest) req;
        response.addCookie(testCookie);
        Cookie[] cookies = request.getCookies();
        if(Objects.nonNull(cookies)){
            for(Cookie c : cookies){
                if("test".equals(c.getName()))
                    chain.doFilter(req, resp);
            }
        }else {
            req.setAttribute("missingCookies", "Some Features are not available if cookies are disabled!");
            chain.doFilter(req,resp);
        }

    }

    public void init(FilterConfig config) throws ServletException {
    }

}
