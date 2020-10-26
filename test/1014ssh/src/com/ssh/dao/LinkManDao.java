package com.ssh.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.ssh.domain.LinkMan;
import com.ssh.domain.PageBean;

public interface LinkManDao {

	Long findCount(DetachedCriteria criteria);

	List<LinkMan> findByPage(DetachedCriteria criteria, Integer begin, Integer pageSize);

	void save(LinkMan linkMan);

	LinkMan findById(Long lkm_id);

	void update(LinkMan linkMan);

	void delete(LinkMan linkMan);
}
