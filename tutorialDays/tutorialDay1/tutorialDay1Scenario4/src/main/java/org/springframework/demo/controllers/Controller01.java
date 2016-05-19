package org.springframework.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.demo.config.HardCodeAuthConfig;
import org.springframework.demo.service.IDemoService;
import org.springframework.demo.utils.ContextUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping 
@Controller
public class Controller01 {
    @Autowired
    private IDemoService service;
    
    private ApplicationContext appContext;
	@Autowired
	public void setAppContext(ApplicationContext appContext) {
		this.appContext = appContext;
	}

    @RequestMapping("/helloWorld")
    public String loadPage(Model uiModel) {
        System.out.println("######################################################################");
        return "dbPuregeSetting/loadPage";
    } 
    
    @RequestMapping(value = "/listgrid")
    @ResponseBody
    public HardCodeAuthConfig loadPersonData(){
    	System.out.println("listgridlistgridlistgridlistgrid");
    	ContextUtils.showInfo(appContext);
    	
    	IDemoService  testSpringBean = ContextUtils.getSprinBean(appContext, IDemoService.class);
    	System.out.println("testSpringBean: "+testSpringBean);
    	
    	service.demoOperation();
    	return new HardCodeAuthConfig();
    }
}
