package com.system.failed.backendtienda.config.security.authorization;

import com.system.failed.backendtienda.exception.ObjectNotFoundException;
import com.system.failed.backendtienda.persistence.entity.security.GrantedPermission;
import com.system.failed.backendtienda.persistence.entity.security.Operation;
import com.system.failed.backendtienda.persistence.entity.security.User;
import com.system.failed.backendtienda.persistence.repository.security.OperationRepository;
import com.system.failed.backendtienda.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.authorization.AuthorizationResult;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
public class CustomAuthorizationManager implements AuthorizationManager<RequestAuthorizationContext> {

	@Autowired
	private OperationRepository operationRepository;
	@Autowired
	private UserService userService;

	@Override
	public void verify(Supplier<Authentication> authentication, RequestAuthorizationContext object) {
		AuthorizationManager.super.verify(authentication, object);
	}

	@Override
	public AuthorizationDecision check(Supplier<Authentication> authentication, RequestAuthorizationContext object) {

		HttpServletRequest request = object.getRequest();

		String url = extractUrl(request);
		String httpMethod = request.getMethod();

		boolean isPublic = isPublic(url, httpMethod);
		if (isPublic) {
			return new AuthorizationDecision(true);
		}
		boolean isGranted = isGranted(url, httpMethod, authentication.get());

		return new AuthorizationDecision(isGranted);
	}

	private boolean isGranted(String url, String httpMethod, Authentication authentication) {
		if (authentication == null || !(authentication instanceof UsernamePasswordAuthenticationToken)) {
			throw new AuthenticationCredentialsNotFoundException("User not logged in");
		}

		List<Operation> operations = obtainedOperations(authentication);

		return operations.stream()
				.anyMatch(getOperationPredicate(url, httpMethod));

	}

	private static Predicate<Operation> getOperationPredicate(String url, String httpMethod) {
		return operation -> {
			String basePath = operation.getModule().getBasePath();
			Pattern pattern = Pattern.compile(basePath.concat(operation.getPath()));
			Matcher matcher = pattern.matcher(url);
			return matcher.matches() && operation.getHttpMethod().equals(httpMethod);
		};
	}

	private List<Operation> obtainedOperations(Authentication authentication) {
		UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;

		String username = (String) token.getPrincipal();

		User user = userService.findOneByUsername(username)
				.orElseThrow(() -> new ObjectNotFoundException("User not found"));

		return user.getRole().getPermissions().stream().map(GrantedPermission::getOperation)
				.collect(Collectors.toList());


	}

	private boolean isPublic(String url, String httpMethod) {
		List<Operation> publicAccessEndpoints = operationRepository.findByPublicAccess();

		return publicAccessEndpoints.stream()
				.anyMatch(getOperationPredicate(url, httpMethod));

	}

	private String extractUrl(HttpServletRequest request) {

		String contextPath = request.getContextPath();
		String url = request.getRequestURI();

		url = url.replace(contextPath, "");

		return url;
	}

	@Override
	public AuthorizationResult authorize(Supplier<Authentication> authentication, RequestAuthorizationContext object) {
		return AuthorizationManager.super.authorize(authentication, object);
	}
}
