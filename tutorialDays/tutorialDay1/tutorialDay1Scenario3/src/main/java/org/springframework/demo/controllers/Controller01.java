package org.springframework.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.demo.config.HardCodeAuthConfig;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping 
@Controller
public class Controller01 {
    @Autowired
    private MessageSource messageSource;

    @RequestMapping("/helloWorld")
    public String loadPage(Model uiModel) {
        System.out.println("######################################################################");
        return "dbPuregeSetting/loadPage";
    } 
    
    @RequestMapping(value = "/listgrid")
    @ResponseBody
    public HardCodeAuthConfig loadPersonData(){
    	System.out.println("listgridlistgridlistgridlistgrid");
    	return new HardCodeAuthConfig();
    }
}
