package org.springframework.demo.service;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.demo.controllers.Controller01;
import org.springframework.demo.utils.ContextUtils;
import org.springframework.stereotype.Service;

@Service
public class DemoServiceImpl implements IDemoService {
	private ApplicationContext appContext;
	@Autowired
	public void setAppContext(ApplicationContext appContext) {
		this.appContext = appContext;
	}
//	@Resource
//	private Controller01 controller01;
	
//	@Autowired
//	private Controller01 controller02;
	
	@Override
	public void demoOperation() {
//		System.out.println("controller01: "+controller01);
//		System.out.println("controller02: "+controller02);
		ContextUtils.showInfo(appContext);
		
		try {
			Controller01  testSpringBean = ContextUtils.getSprinBean(appContext, Controller01.class);
			System.out.println("testSpringBean: "+testSpringBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
		System.out.println("I'm Demo operatition... live in root application not child one..");
	}

}
