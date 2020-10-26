package com.ssh.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.ssh.dao.CustomerDao;
import com.ssh.domain.Customer;
import com.ssh.domain.PageBean;
import com.ssh.service.CustomerService;

@Transactional
public class CustomerServiceImpl implements CustomerService{
	
	private CustomerDao customerDao;
	
	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	@Override
	public void save(Customer customer) {
		customerDao.save(customer);
	}

	@Override
	public void delete(Customer customer) {
		customerDao.delete(customer);
	}

	@Override
	public void update(Customer customer) {
		customerDao.update(customer);
	}

	@Override
	public Customer findById(Long id) {
		return customerDao.findById(id);
	}

//	@Override
//	public List<Customer> findByHQL() {
//		List<Customer> list = customerDao.findByHQL();
//		return list;
//	}
//
//	@Override
//	public List<Customer> findByNameQuery() {
//		customerDao.findByNameQuery();
//		System.out.println("service.............");
//		return null;
//	}
//
//	@Override
//	public List<Customer> findByQBC() {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public PageBean<Customer> findByPage(DetachedCriteria criteria, Integer currPage, Integer pageSize) {
		PageBean<Customer> pageBean = new PageBean<Customer>();
		//封装当前页
		pageBean.setCurrPage(currPage);
		//封装每页记录数
		pageBean.setPageSize(pageSize);
		//封装总记录数:查询_调用Dao
		Integer totalCount = customerDao.findCount(criteria);
		pageBean.setTotalCount(totalCount);
		//封装总页数:计算
		Double num = Math.ceil(totalCount.doubleValue()/pageSize);
		pageBean.setTotalPage(num.intValue());
		//封装每页显示的数据的集合
		Integer begin = (currPage-1)*pageSize;
		List<Customer> customers = customerDao.findByPage(criteria,begin,pageSize);
		pageBean.setList(customers);
		return pageBean;
	}

	@Override
	public List<Customer> findAll() {
		return customerDao.findAll();
	}

}
