package org.sabzzil.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		System.out.println("authInterceptor preHandle");
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute("login")==null) {
			System.out.println("in if");
			response.sendRedirect("/user/login");
			return false;
		}
		
		return true;
	}
	
}
