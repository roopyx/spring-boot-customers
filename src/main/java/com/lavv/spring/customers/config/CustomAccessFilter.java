package com.lavv.spring.customers.config;

import com.lavv.spring.customers.utils.JwtUtil;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

@Component
public class CustomAccessFilter implements Filter {

    public CustomAccessFilter() {}

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if (isAuthorized(request)) {
            SecurityContextHolder
                    .getContext()
                    .setAuthentication(
                            new UsernamePasswordAuthenticationToken(
                                    null,
                                    null,
                                    Collections.emptyList()
                            )
                    );
            filterChain.doFilter(request, response);
        } else {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
    }

    private boolean isAuthorized(HttpServletRequest request) {

        String currentUrl = request.getRequestURI();

        String[] availableUrl = new String[] {
            "/api/auth/login",
            "/api/auth/register"
        };

        boolean authorized = Arrays.asList(availableUrl).contains(currentUrl);
        boolean isApiResource = currentUrl.startsWith("/api/");

        if (authorized || !isApiResource) return true;

        try {
            String token = request.getHeader("Authorization");
            String userId = JwtUtil.getUserIdByToken(token);
            return true;

        } catch(Exception e) {
            return false;
        }
    }


}
