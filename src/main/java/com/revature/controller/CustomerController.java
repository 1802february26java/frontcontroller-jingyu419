package com.revature.controller;

import javax.servlet.http.HttpServletRequest;

public interface CustomerController {

	  public Object register(HttpServletRequest request);
	  
	  /*
	   * If it's GET with no parameters, then we return the view
	   * If it's GET with parameters, then we return the list
	   */
	  public Object getAllCustomers(HttpServletRequest request);
	  
}
