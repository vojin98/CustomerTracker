package com.vox.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vojin.springdemo.entity.Customer;
import com.vojin.springdemo.service.CustomerService;
import com.vox.springdemo.dao.CustomerDAO;

@Controller("/customer")
public class CustomerController {
	
	
	@Autowired
	
	private CustomerService customerService;
	
	
	
	@GetMapping("/list")
@RequestMapping("/list")
public String listCustomers(Model theModel)
{
		
	// get Customers from the dao
	List<Customer> theCustomers= customerService.getCustomers();
	// add Customers to the model
	theModel.addAttribute("customers",theCustomers);
	
	
	return "list-customers";
	
	
}
	
	
	@GetMapping("showFormForAdd")
	public String showFormForAdd(Model theModel)
	
	{
		Customer theCustomer = new Customer();
		
		theModel.addAttribute("customer",theCustomer);
		
		
		return "show-form";
		
	}
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute ("customer") Customer theCustomer)
	{
		
		
		customerService.saveCustomer(theCustomer);
		
		return "redirecct:/customer/list";
		
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate (@RequestParam("customerId") int theId, Model theModel)
	
	{
		
		Customer theCustomer = CustomerService.getCustomer(theId);
		
		theModel.addAttribute("customer",theCustomer);
		
		return "customer-form";
		
		
	}
	
	
	@GetMapping("/delete")
	public String deleteCustomer(@RequestParam("customerId") int theId) {
		
		// delete the customer
		customerService.deleteCustomer(theId);
		
		return "redirect:/customer/list";
	}
	
	
	
	
}
