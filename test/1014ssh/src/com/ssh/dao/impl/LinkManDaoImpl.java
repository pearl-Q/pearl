package com.ssh.dao.impl;

import static org.hamcrest.CoreMatchers.nullValue;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.ssh.dao.LinkManDao;
import com.ssh.domain.LinkMan;
import com.ssh.domain.PageBean;

public class LinkManDaoImpl extends HibernateDaoSupport implements LinkManDao {

	@Override
	public Long findCount(DetachedCriteria criteria) {
		criteria.setProjection(Projections.rowCount());
		List<Long> list = (List<Long>) this.getHibernateTemplate().findByCriteria(criteria);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<LinkMan> findByPage(DetachedCriteria criteria, Integer begin, Integer pageSize) {
		criteria.setProjection(null);
		return (List<LinkMan>) this.getHibernateTemplate().findByCriteria(criteria, begin, pageSize);
	}

	@Override
	public void save(LinkMan linkMan) {
		this.getHibernateTemplate().save(linkMan);
	}

	@Override
	public LinkMan findById(Long lkm_id) {
		return this.getHibernateTemplate().get(LinkMan.class, lkm_id);
	}

	@Override
	public void update(LinkMan linkMan) {
		this.getHibernateTemplate().update(linkMan);
	}

	@Override
	public void delete(LinkMan linkMan) {
		this.getHibernateTemplate().delete(linkMan);
	}

	
	
}
