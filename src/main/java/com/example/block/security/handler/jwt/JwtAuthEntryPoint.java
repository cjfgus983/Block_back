package com.example.block.security.handler.jwt;

import com.example.block.global.apiPayload.code.status.ErrorStatus;
import com.example.block.security.info.AbstractAuthenticationFailure;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtAuthEntryPoint extends AbstractAuthenticationFailure implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ErrorStatus errorCode = request.getAttribute("exception") == null ?
                ErrorStatus.NOT_FOUND_END_POINT : (ErrorStatus) request.getAttribute("exception");

        setErrorResponse(response, errorCode);
    }

}
