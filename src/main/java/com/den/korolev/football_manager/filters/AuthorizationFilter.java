package com.den.korolev.football_manager.filters;

import com.den.korolev.football_manager.user.InvalidTokenException;
import com.den.korolev.football_manager.services.JwtTokenService;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@WebFilter(filterName = "authorizationFilter", urlPatterns = {"/user/get/*", "/user/update/*", "/event/add/*",
        "/event/get/*", "/exercise/*", "/event/delete/*", "/event/update/*", "/club/*"})
@Component
public class AuthorizationFilter extends OncePerRequestFilter {

    private final JwtTokenService jwtTokenService;

    public AuthorizationFilter(JwtTokenService jwtTokenService) {
        this.jwtTokenService = jwtTokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String token = request.getHeader("Authorization");
        Long userId;
        try {
            userId = jwtTokenService.verifyToken(token);
        } catch (InvalidTokenException | ExpiredJwtException e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token");
            return;
        }
        logger.info("Good user: id: " + userId);
        request.setAttribute("Uid", userId);
        filterChain.doFilter(request, response);
    }

}
