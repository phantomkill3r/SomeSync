package com.example.demo.security;


import com.example.demo.service.cache.MemCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@Component
public class RequestFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException, AuthenticationException {

		final String requestTokenHeader = request.getHeader("SS-Token");

		if (requestTokenHeader == null || requestTokenHeader.isEmpty()) {
			logger.warn("SS-Token missing");
		} else {
			logger.info("TOKEN :: " + requestTokenHeader);
		}


		if (requestTokenHeader != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserToken userToken = new UserToken(requestTokenHeader, null);
			userToken
					.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

			SecurityContextHolder.getContext().setAuthentication(userToken);
		}
		chain.doFilter(request, response);
	}
}