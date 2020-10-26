package com.ssh.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.ssh.dao.BaseDictDao;
import com.ssh.domain.BaseDict;
import com.ssh.service.BaseDictService;

@Transactional
public class BaseDictServiceImpl implements BaseDictService {
	private BaseDictDao baseDictDao;

	public void setBaseDictDao(BaseDictDao baseDictDao) {
		this.baseDictDao = baseDictDao;
	}

	@Override
	public List<BaseDict> findByTypeCode(String dict_type_code) {
		return baseDictDao.findByTypeCode(dict_type_code);
	}
	
}
