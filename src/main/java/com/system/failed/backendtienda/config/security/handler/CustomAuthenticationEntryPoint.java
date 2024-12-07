package com.system.failed.backendtienda.config.security.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Autowired
	private AccessDeniedHandler accessDeniedHandler;
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
//		APIError apiError = new APIError();
//		apiError.setBackendMessage(authException.getLocalizedMessage());
//		apiError.setMessage("No se encontraron credenciales de autenticacion. " +
//				"Por favor inicie sesion para acceder a este recurso.");
//		apiError.setPath(request.getRequestURL().toString());
//		apiError.setTimestamp(LocalDateTime.now());
//		apiError.setMethod(request.getMethod());
//
//		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
//
//		response.setStatus(HttpStatus.UNAUTHORIZED.value());
//
//		ObjectMapper objectMapper = new ObjectMapper();
//
//		objectMapper.registerModule(new JavaTimeModule());
//
//		String apiErrorAsJson = objectMapper.writeValueAsString(apiError);
//
//		response.getWriter().write(apiErrorAsJson);
		accessDeniedHandler.handle(request, response, new AccessDeniedException("No se encontraron credenciales de autenticacion. " +
				"Por favor inicie sesion para acceder a este recurso."));
	}
}
