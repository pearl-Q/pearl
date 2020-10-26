package com.ssh.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.ssh.dao.LinkManDao;
import com.ssh.domain.LinkMan;
import com.ssh.domain.PageBean;
import com.ssh.service.LinkManServive;

@Transactional
public class LinkManServiveImpl implements LinkManServive {
	
	private LinkManDao linkManDao;
	public void setLinkManDao(LinkManDao linkManDao) {
		this.linkManDao = linkManDao;
	}
	@Override
	public PageBean<LinkMan> findByPage(DetachedCriteria criteria, Integer currPage, Integer pageSize) {
		PageBean<LinkMan> pageBean = new PageBean<LinkMan>();
		//设置当前页
		pageBean.setCurrPage(currPage);
		//设置每页的记录数
		pageBean.setPageSize(pageSize);
		//设置总记录数
		Integer totalCount = linkManDao.findCount(criteria);
		pageBean.setTotalCount(totalCount);
		//设置总页数
		Double num = Math.ceil(totalCount.doubleValue()/pageSize);
		pageBean.setTotalPage(num.intValue());
		//设置每页显示的数据
		Integer begin = (currPage-1)*pageSize;
		List<LinkMan> list = linkManDao.findByPage(criteria,begin,pageSize);
		pageBean.setList(list);
		return pageBean;
	}
	@Override
	public void save(LinkMan linkMan) {
		linkManDao.save(linkMan);
	}
	@Override
	public LinkMan findById(Long lkm_id) {
		return linkManDao.findById(lkm_id);
	}
	@Override
	public void update(LinkMan linkMan) {
		linkManDao.update(linkMan);
	}
	@Override
	public void delete(LinkMan linkMan) {
		linkManDao.delete(linkMan);
	}
	
}
