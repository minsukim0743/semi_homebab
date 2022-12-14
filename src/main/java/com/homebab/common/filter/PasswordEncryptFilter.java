package com.homebab.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import com.homebab.common.wrapper.EncryptRequestWrapper;

@WebFilter("/admin/*")
public class PasswordEncryptFilter implements Filter{
	
	public PasswordEncryptFilter() {}
	
	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest hrequest = (HttpServletRequest) request;
		String uri = hrequest.getRequestURI();
		System.out.println("uri : " + uri);
		
		String intent = uri.substring(uri.lastIndexOf("/"));
		System.out.println("intent : " + intent);
		
		if(!"/login".equals(intent)) {
			
			EncryptRequestWrapper wrapper = new EncryptRequestWrapper(hrequest);
			
			chain.doFilter(wrapper, response);
		} else {
			
			chain.doFilter(request, response);
		}
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}
	
 

}
