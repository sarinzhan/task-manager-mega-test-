package kg.kazbekov.megatesttask.filter;


import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class LoggingFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest) request;
        log.info("Incoming request: {} {}", httpReq.getMethod(), httpReq.getRequestURI());

        chain.doFilter(request, response);

        HttpServletResponse httpResp = (HttpServletResponse) response;
        log.info("Response status: {}", httpResp.getStatus());
    }
}