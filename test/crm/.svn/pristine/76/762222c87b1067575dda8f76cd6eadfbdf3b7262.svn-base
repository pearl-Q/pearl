package com.ssh.web.action;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.opensymphony.xwork2.ModelDriven;
import com.ssh.domain.Customer;
import com.ssh.service.CustomerService;

public class CountAction extends HibernateDaoSupport implements ModelDriven<Customer> {

	private Customer customer = new Customer();
	@Override
	public Customer getModel() {
		return customer;
	}
	
	private CustomerService customerService;
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	public String findByIndustry() {
		return "findByIndustrySuccess";
	}
}
