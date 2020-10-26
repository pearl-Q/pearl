package com.ssh.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.ssh.dao.SaleVisitDao;
import com.ssh.domain.LinkMan;
import com.ssh.domain.PageBean;
import com.ssh.domain.SaleVisit;
import com.ssh.service.SaleVisitService;

@Transactional
public class SaleVisitServiceImpl implements SaleVisitService {
	
	private SaleVisitDao saleVisitDao;

	public void setSaleVisitDao(SaleVisitDao saleVisitDao) {
		this.saleVisitDao = saleVisitDao;
	}

	@Override
	public PageBean<SaleVisit> findByPage(DetachedCriteria criteria, Integer currPage, Integer pageSize) {
		PageBean<SaleVisit> pageBean = new PageBean<SaleVisit>();
		
		pageBean.setCurrPage(currPage);
		pageBean.setPageSize(pageSize);
		
		Integer totalCount = saleVisitDao.findCount(criteria);
		pageBean.setTotalCount(totalCount);
		
		Double num = Math.ceil(totalCount.doubleValue()/pageSize);
		pageBean.setTotalPage(num.intValue());
		
		Integer begin = (currPage - 1)*pageSize;
		List<SaleVisit> list = saleVisitDao.findByPage(criteria, begin, pageSize);
		pageBean.setList(list);
		return pageBean;
	}

	@Override
	public void save(SaleVisit saleVisit) {
		saleVisitDao.save(saleVisit);
	}

	@Override
	public void delete(SaleVisit saleVisit) {
		saleVisitDao.delete(saleVisit);
	}

	@Override
	public SaleVisit findById(String visit_id) {
		return saleVisitDao.findById(visit_id);
	}

	@Override
	public void update(SaleVisit saleVisit) {
		saleVisitDao.update(saleVisit);
	}


}
