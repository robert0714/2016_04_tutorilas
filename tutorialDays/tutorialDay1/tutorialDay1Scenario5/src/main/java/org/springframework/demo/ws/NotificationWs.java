/*
 * Copyright (c) 2010-2020 IISI.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of IISI.
 */
package org.springframework.demo.ws;
 

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface NotificationWs { 
    String notification(@WebParam(name="title") String title,@WebParam(name="content") String content);
    
}
