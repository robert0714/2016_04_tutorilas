/*
 * Copyright (c) 2010-2020 IISI.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of IISI.
 */
package org.springframework.demo.ws;


import javax.jws.WebService; 

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.demo.controllers.Controller01;
import org.springframework.demo.utils.ContextUtils;
@WebService(endpointInterface = "org.springframework.demo.ws.NotificationWs" )
public class NotificationWsImpl implements NotificationWs {
	private final Logger logger = LoggerFactory
			.getLogger(NotificationWsImpl.class);
	private ApplicationContext appContext;
	@Autowired
	public void setAppContext(ApplicationContext appContext) {
		this.appContext = appContext;
	}
	
	public String notification(String title, String content) {
		ContextUtils.showInfo(appContext);
		
		try {
			Controller01  testSpringBean = ContextUtils.getSprinBean(appContext, Controller01.class);
			System.out.println("testSpringBean: "+testSpringBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
		
		logger.debug("ws response: {}", "Deprecated");
		return "Deprecated";
	}
}