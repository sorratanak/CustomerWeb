package com.customer.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.customer.model.CustomerModel;
import com.customer.services.CustomerService;

@Controller
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@RequestMapping(value="/api/Customer/load", method = RequestMethod.GET)
	public @ResponseBody List<Map<String, Object>> loadCustomer(){
		return customerService.loadCustomer();
	}
	
	@RequestMapping(value="/api/Customer/insert", method=RequestMethod.POST)
	public @ResponseBody Map<String , Object> insertCustomer(@RequestBody CustomerModel model){
		return customerService.insertCustomer(model);
	}
	
	@RequestMapping(value="/api/Customer/update", method=RequestMethod.POST)
	public @ResponseBody Map<String , Object> updateCustomer(@RequestBody CustomerModel model){
		return customerService.updateCustomer(model);
	}
	
	@RequestMapping(value="/api/Customer/remove/{id}", method=RequestMethod.DELETE)
	public @ResponseBody Map<String , Object> removeCustomer(@PathVariable("id") Integer id){
		return customerService.removeCustomer(id);
	}
}
