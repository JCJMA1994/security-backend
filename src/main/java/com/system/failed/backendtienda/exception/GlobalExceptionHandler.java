package com.system.failed.backendtienda.exception;

import com.system.failed.backendtienda.dto.APIError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handlerGenericException(Exception exception, HttpServletRequest request) {
		{
			APIError apiError = new APIError();
			apiError.setBackendMessage(exception.getLocalizedMessage());
			apiError.setMessage("Error interno en el servidor");
			apiError.setPath(request.getRequestURL().toString());
			apiError.setTimestamp(LocalDateTime.now());
			apiError.setMethod(request.getMethod());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiError);
		}
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handlerMethodArgumentNotValidException(MethodArgumentNotValidException exception, HttpServletRequest request) {
		{
			APIError apiError = new APIError();
			apiError.setBackendMessage(exception.getLocalizedMessage());
			apiError.setMessage("Error en la peticion enviada");
			apiError.setPath(request.getRequestURL().toString());
			apiError.setTimestamp(LocalDateTime.now());
			apiError.setMethod(request.getMethod());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
		}
	}
}