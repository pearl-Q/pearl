package com.ssh.service;

import org.hibernate.criterion.DetachedCriteria;

import com.ssh.domain.LinkMan;
import com.ssh.domain.PageBean;

public interface LinkManServive {

	PageBean<LinkMan> findByPage(DetachedCriteria criteria, Integer currPage, Integer pageSize);

	void save(LinkMan linkMan);

	LinkMan findById(Long lkm_id);

	void update(LinkMan linkMan);

	void delete(LinkMan linkMan);

}
