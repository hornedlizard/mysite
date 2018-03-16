package com.cafe24.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

//@WebListener
public class ContextLoaderListener implements ServletContextListener {

    public ContextLoaderListener() {
        
    }

	public void contextDestroyed(ServletContextEvent servletContextEvent)  { 
         
    }

	public void contextInitialized(ServletContextEvent servletContextEvent)  { 
		String contextConfigLocation = 
					servletContextEvent.getServletContext()
						.getInitParameter("contextConfigLocation");
		System.out.println(contextConfigLocation);
         System.out.println("container starts...");
    }
	
}
