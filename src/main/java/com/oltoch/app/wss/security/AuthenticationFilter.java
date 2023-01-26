//package com.oltoch.app.wss.security;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Date;
//
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.oltoch.app.wss.model.request.UserLoginRequestModel;
//
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
//	private final AuthenticationManager authenticationManager;
//
//	public AuthenticationFilter(AuthenticationManager authenticationManager) {
//		this.authenticationManager = authenticationManager;
//	}
//
//	@Override
//	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
//			throws AuthenticationException {
//		try {
//			UserLoginRequestModel creds = new ObjectMapper().readValue(req.getInputStream(),
//					UserLoginRequestModel.class);
//			return authenticationManager.authenticate(
//					new UsernamePasswordAuthenticationToken(
//							creds.getEmail(), creds.getPassword(), new ArrayList<>()));
//		} catch (IOException e) {
//			throw new RuntimeException(e);
//		}
//	}
//
//	@Override
//	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res,
//			FilterChain chain, Authentication auth) throws IOException, ServletException{
//		String userName = ((User) auth.getPrincipal()).getUsername();
//		//String tokenSecret = new SecurityConstraints().getTokenSecret();
//
//		String token = Jwts.builder()
//				.setSubject(userName)
//				.setExpiration(new Date(System.currentTimeMillis()+SecurityConstants.EXPIRATION_TIME))
//				.signWith(SignatureAlgorithm.HS512, SecurityConstants.TOKEN_SECRET)
//				.compact();
//		res.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX+token);
//	}
//
//}
