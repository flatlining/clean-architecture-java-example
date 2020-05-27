package dev.schertel.cq.presenter.rest;

import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class RequestResponseLoggingFilter implements Filter {
    private Logger logger;

    public RequestResponseLoggingFilter(Logger logger) {
        this.logger = logger;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        logger.info("Request {} {}", req.getMethod(), req.getRequestURI());

        filterChain.doFilter(servletRequest, servletResponse);

        logger.info("Response: {}", res.getStatus());
    }
}
