package org.springframework.demo.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.web.context.support.SpringBeanAutowiringSupport; 


/**
 * Servlet implementation class TestServlet
 */
@WebServlet(name="TestServlet", urlPatterns={"/path", "/alt","/testServlet"}) 
public class TestServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1577004276823232990L;
	
	@Autowired
	private Locale locale ;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, 
	 *				HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
		       
        PrintWriter out=response.getWriter();
        out.println("<br>Name: "+locale.getCountry() );
        out.println("<br>Designation: "+locale.getDisplayLanguage());
        out.close();		
	} 
}
