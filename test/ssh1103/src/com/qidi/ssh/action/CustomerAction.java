package com.qidi.ssh.action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.qidi.ssh.pojo.Customer;
import com.qidi.ssh.service.CustomerService;

public class CustomerAction extends ActionSupport implements ModelDriven<Customer> {

	private Customer customer = new Customer();
	@Override
	public Customer getModel() {
		return customer;
	}
	private CustomerService customerService;
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	public String list() {
		List<Customer> list = customerService.list();
		ActionContext.getContext().getValueStack().set("list", list);
		return "listSuccess";
	}
	
	public String save() {
		customerService.save(customer);
		return "saveSuccess";
	}
	
	public String delete() {
		customer = customerService.findById(customer.getCust_id());
		customerService.delete(customer);
		return "delectSuccess";
	}
	public String edit() {
		customer = customerService.findById(customer.getCust_id());
		ActionContext.getContext().getValueStack().push(customer);
		return "editUI";
	}
	public String update() {
		customerService.update(customer);
		return "updateSuccess";
	}
}
