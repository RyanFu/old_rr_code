package com.xiaonei.platform.core.opt.base.action;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.xiaonei.platform.framework.log.XnLog;

public class CharacterEncodingFilter implements Filter ,XnLog{

	private String encoding;
	
	
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		if(encoding==null||encoding.trim().length()==0)
			request.setCharacterEncoding("UTF-8");
		else
			request.setCharacterEncoding(encoding);
		chain.doFilter(request, res);
	}

	public void init(FilterConfig config) throws ServletException {
	
	}			
}
