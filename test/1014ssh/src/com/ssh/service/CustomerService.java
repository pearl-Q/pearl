package com.ssh.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.ssh.domain.Customer;
import com.ssh.domain.PageBean;

public interface CustomerService {
	public void save(Customer customer);
	public void delete(Customer customer);
	public void update(Customer customer);
	public Customer findById(Long id);
//	public List<Customer> findByHQL();
//	public List<Customer> findByQBC();
//	public List<Customer> findByNameQuery();
	public PageBean<Customer> findByPage(DetachedCriteria criteria, Integer currPage, Integer pageSize);
	public List<Customer> findAll();
}
