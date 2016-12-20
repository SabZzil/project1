package org.sabzzil.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.sabzzil.domain.UserVO;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	
		System.out.println("loginInterceptor postHandle");
		
		HttpSession session = request.getSession();
		ModelMap modelMap = modelAndView.getModelMap();
		Object userVO = modelMap.get("userVO");
		
		if(((UserVO)userVO).getUid()!=null) {
			System.out.println("in if");
			session.setAttribute("login", userVO);
//			response.sendRedirect("/");
		}
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, 
			Object handler) throws Exception {
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute("login")!=null) {
			session.removeAttribute("login");
		}
		
		return true;
	}
}
