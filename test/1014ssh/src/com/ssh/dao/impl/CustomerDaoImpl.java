package com.ssh.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import com.ssh.dao.CustomerDao;
import com.ssh.domain.Customer;
import com.ssh.domain.PageBean;

public class CustomerDaoImpl extends HibernateDaoSupport implements CustomerDao{

	@Override
	public void save(Customer customer) {
		this.getHibernateTemplate().save(customer);
	}

	@Override
	public void delete(Customer customer) {
		this.getHibernateTemplate().delete(customer);
	}

	@Override
	public void update(Customer customer) {
		this.getHibernateTemplate().update(customer);
	}

	@Override
	public Customer findById(Long id) {
		return this.getHibernateTemplate().get(Customer.class,id);
	}
//
//	@Override
//	public List<Customer> findByHQL() {
//		List<Customer> list = (List<Customer>) this.getHibernateTemplate().find("from Customer");
//		return list;
//	}
//
//	@Override
//	public List<Customer> findByNameQuery() {
//		System.out.println("dao......");
//		return null;
//	}
//
//	@Override
//	public List<Customer> findByQBC() {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public Integer findCount(DetachedCriteria criteria) {
		criteria.setProjection(Projections.rowCount());
		List<Long> list = (List<Long>) this.getHibernateTemplate().findByCriteria(criteria);
		if(list != null && list.size()>0) {
			return list.get(0).intValue();
		}
		return null;
	}

	@Override
	public List<Customer> findByPage(DetachedCriteria criteria, Integer begin, Integer pageSize) {
		criteria.setProjection(null);
		return (List<Customer>) this.getHibernateTemplate().findByCriteria(criteria, begin, pageSize);
	}

	@Override
	public List<Customer> findAll() {
		return (List<Customer>) this.getHibernateTemplate().find("from Customer");
	}

	
	
}
