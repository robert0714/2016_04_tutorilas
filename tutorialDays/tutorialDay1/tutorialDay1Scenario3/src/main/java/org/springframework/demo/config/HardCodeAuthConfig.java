 
package org.springframework.demo.config;

/**
 *
 */
public class HardCodeAuthConfig   {
	 
	public String[] getRangeRole() {
		return new String[]{"ROLE_ADMIN","ROLE_AM"};
	} 
	
	public String getAdminRole(){
		return "ROLE_ADMIN";
	}
}
