package com.system.failed.backendtienda.config.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.system.failed.backendtienda.dto.APIError;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
		APIError apiError = new APIError();
		apiError.setBackendMessage(accessDeniedException.getLocalizedMessage());
		apiError.setMessage("Acceso denegado. No tiene permisos para acceder a este recurso" +
				"Por favor contacte al administrador del sistema");
		apiError.setPath(request.getRequestURL().toString());
		apiError.setTimestamp(LocalDateTime.now());
		apiError.setMethod(request.getMethod());

		response.setContentType(MediaType.APPLICATION_JSON_VALUE);

		response.setStatus(HttpStatus.FORBIDDEN.value());

		ObjectMapper objectMapper = new ObjectMapper();

		objectMapper.registerModule(new JavaTimeModule());

		String apiErrorAsJson = objectMapper.writeValueAsString(apiError);

		response.getWriter().write(apiErrorAsJson);
	}
}
