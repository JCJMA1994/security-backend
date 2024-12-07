package com.system.failed.backendtienda.config.security.filter;

import com.system.failed.backendtienda.exception.ObjectNotFoundException;
import com.system.failed.backendtienda.persistence.entity.security.User;
import com.system.failed.backendtienda.services.UserService;
import com.system.failed.backendtienda.services.auth.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	@Autowired
	private JwtService jwtService;
	@Autowired
	private UserService userService;


	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		// Obtener el encabezado http  llamado Authorization
		String authorizationHeader = request.getHeader("Authorization");
		if (!StringUtils.hasText(authorizationHeader) || !authorizationHeader.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}

		// Obtener jwt desde el header

		String jwt = authorizationHeader.split(" ")[1].trim();

		// Obtener el subject/username del token

		String username = jwtService.extractUsername(jwt);

		// Setear objeto authentication en el contexto de seguridad

		User user = userService.findOneByUsername(username).orElseThrow(() -> new ObjectNotFoundException("User not found" + username));
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, null, user.getAuthorities());
		authentication.setDetails(new WebAuthenticationDetails(request));
		SecurityContextHolder.getContext().setAuthentication(authentication);

		// Ejecutar el siguiente filtro

		filterChain.doFilter(request, response);
	}
}
