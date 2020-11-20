package com.jimall.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


//  /member/**  주소로 요청하는 부분을 인터셉터 설정하여 제어하겠다
public class LoginInterceptor extends HandlerInterceptorAdapter {

	// 요청이 들어오면 얘로 들어옴
	// return이 true값이면 원래주소의 컨트롤러로 넘어감
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		// 인터셉터 설정이 관리자메뉴 주소요청이 발생
		// (관리자 쪽일 경우) 관리자 로그인 세션여부 체크
		// 1) 정상 로그인 상태 : return true; -> 관리자 메뉴 주소로 넘아감
		// 2) 로그인 상태 아님 : 주소 이동작업(관리자 로그인 주소로) return false;
		
		return super.preHandle(request, response, handler);
	}
	

	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
	}

	
}
