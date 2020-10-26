package com.ssh.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.ssh.domain.LinkMan;
import com.ssh.domain.PageBean;
import com.ssh.domain.SaleVisit;

public interface SaleVisitService {
	PageBean<SaleVisit> findByPage(DetachedCriteria criteria, Integer currPage, Integer pageSize);

	void save(SaleVisit saleVisit);

	void delete(SaleVisit saleVisit);

	SaleVisit findById(String visit_id);

	void update(SaleVisit saleVisit);

}
