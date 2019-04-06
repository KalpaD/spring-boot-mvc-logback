package com.kds.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class LogUtilFilter extends GenericFilterBean {

    private Logger LOGGER  = LoggerFactory.getLogger(LogUtilFilter.class);

    private static final String HTTP_METHOD = "HTTP_METHOD";
    private static final String REQUEST_URI = "REQUEST_URI";

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;

        try {
            LOGGER.info("Adding request information to MDC");
            MDC.put(HTTP_METHOD, request.getMethod());
            MDC.put(REQUEST_URI, request.getRequestURI());

            LOGGER.info("Forwarding the request filter chain");
            filterChain.doFilter(servletRequest, servletResponse);

        } finally {
            LOGGER.info("Cleaning up the MDC");
            // cleanup the MDC for this request context
            MDC.remove(HTTP_METHOD);
            MDC.remove(REQUEST_URI);
        }
    }
}
