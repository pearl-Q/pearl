package com.qidi.ssh.service;

import java.util.List;

import com.qidi.ssh.pojo.Customer;

public interface CustomerService {
	List<Customer> list();

	void save(Customer customer);

	Customer findById(Long cust_id);

	void delete(Customer customer);

	void update(Customer customer);
}
