package com.thoughtworks.star.configuration.security;

import com.thoughtworks.star.exception.InvalidCredentialException;
import com.thoughtworks.star.service.AuthService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private AuthService authService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // TODO implement by students #start#
        if (!authService.hasJWTToken(request)) {
            filterChain.doFilter(request, response);
        } else {
            handlerRequestAttachedJWTToken(request, response, filterChain);
        }
        // TODO implement by students #end#
    }

    private void handlerRequestAttachedJWTToken(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        // TODO implement by students #start#
        try {
            JWTUser jwtUser = authService.getAuthorizedJWTUser(request);

            UsernamePasswordAuthenticationToken token =
                    new UsernamePasswordAuthenticationToken(
                            jwtUser, null, jwtUser.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(token);

            filterChain.doFilter(request, response);
        } catch (ExpiredJwtException | SignatureException | InvalidCredentialException e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }
        // TODO implement by students #end#
    }
}