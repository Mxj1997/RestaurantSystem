package com.catering.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CateringFilter implements Filter{
    private String encoder;
    public void init(FilterConfig filterConfig) throws ServletException {
    	// TODO Auto-generated method stub
    	encoder=filterConfig.getInitParameter("encoder");
    }
    
    /**
     * ServletRequest 是HttpServletRequest的父接口
     * request 是父接口的接口对象  
     * 父接口对象强转为子接口对象了
     * 
     */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filter) throws IOException, ServletException {
		    HttpServletRequest req=(HttpServletRequest)request;
		    HttpServletResponse resp=(HttpServletResponse)response;
		    
		    req.setCharacterEncoding(encoder);
		    resp.setContentType("text/html;charset="+encoder);
		    filter.doFilter(req, resp); 
	
	}
	public void destroy() {
		// TODO Auto-generated method stub
		
	}


}
