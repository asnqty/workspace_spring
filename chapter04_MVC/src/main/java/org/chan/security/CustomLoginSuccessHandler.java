package org.chan.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import lombok.extern.log4j.Log4j;

@Log4j
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler{
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		log.warn("Login Success");
		
		// 로그인에 성공 시 그 계정의 ROLE을 받아옴. 권한이 여러개일 수도 있기에 리스트로 받음
		List<String> roleNames = new ArrayList<String>();
		
		authentication.getAuthorities().forEach(auth ->{
			roleNames.add(auth.getAuthority());
		});
		
		log.warn("ROLE NAMES : " + roleNames);
		
		if(roleNames.contains("ROLE_ADMIN")) {
			response.sendRedirect("sample/admin");
			return;
		}
		
		if(roleNames.contains("ROLE_MEMBER")) {
			response.sendRedirect("sample/member");
			return;
		}
		
		response.sendRedirect("/");
	}
}
