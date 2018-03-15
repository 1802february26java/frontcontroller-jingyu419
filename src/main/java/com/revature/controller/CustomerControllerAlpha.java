package com.revature.controller;

import javax.servlet.http.HttpServletRequest;

import com.revature.ajax.ClientMessage;
import com.revature.model.Customer;
import com.revature.service.CustomerServiceAlpha;

public class CustomerControllerAlpha implements CustomerController {

	private static CustomerController customerController = new CustomerControllerAlpha();
	
	private CustomerControllerAlpha(){}
	
	public static CustomerController getInstance(){
		return customerController;
	}
	
	
	@Override
	public Object register(HttpServletRequest request) {
		
		if(request.getMethod().equals("GET")){
			return "register.html";
		}
				
		//Logic for POST
		Customer newCustomer = new Customer(
				  999,
				  request.getParameter("firstname"),
	              request.getParameter("lastname"),
				  request.getParameter("username"),
	              request.getParameter("password")
				);
		Boolean registerSuccess = CustomerServiceAlpha.getInstance().registerCustomer(newCustomer);
		
		if(registerSuccess == true){
			return new ClientMessage("REGISTRATION SUCCESSFUL");
			//return newCustomer;
		}
		else {
			return new ClientMessage("SOMETHING WENT WRONG");
		}
			
		//return null;
	}

	@Override
	public Object getAllCustomers(HttpServletRequest request) {
		
		Customer loggedCustomer = (Customer) request.getSession().getAttribute("loggedCustomer");
		System.out.println(loggedCustomer);
		//If customer is not logged in
		if(request.getSession().getAttribute("loggedCustomer") == null){
			System.out.println("Back to Login.html");
			return "login.html";
		}
		
		//If not null, check the user role if this is a employee, he/she cannot see all employees
		
		//client is requesting the view
		if(request.getParameter("fetch") == null){
			System.out.println("Empty");
			return "all-customers.html";			
		}
		else{
			//client is requesting the list of customers
			System.out.println(CustomerServiceAlpha.getInstance().listAllCustomers());
			return CustomerServiceAlpha.getInstance().listAllCustomers();
		}
	}

}
