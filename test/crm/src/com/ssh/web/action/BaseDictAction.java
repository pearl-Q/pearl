package com.ssh.web.action;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.core.config.json.JsonConfiguration;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.ssh.domain.BaseDict;
import com.ssh.service.BaseDictService;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

public class BaseDictAction extends ActionSupport implements ModelDriven<BaseDict> {

	private BaseDict baseDict = new BaseDict();
	@Override
	public BaseDict getModel() {
		return baseDict;
	}
	private BaseDictService baseDictService;
	public void setBaseDictService(BaseDictService baseDictService) {
		this.baseDictService = baseDictService;
	}
	/**
	* Title: findByTypeCode
	* Description: �����������Ʋ�ѯ�ֵ�ķ���
	* @return
	* @throws IOException
	*/
	public String findByTypeCode() throws IOException {
		List<BaseDict> list = baseDictService.findByTypeCode(baseDict.getDict_type_code());
		//�� list ת��Ϊ json jsonlib fastjson
		/**
		* JSONConfig:תjson�����ö���
		* JSONArray:�������Listת��Ϊjson
		* JSONObject:�������Mapת��Ϊjson
		*/
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[] {"dict_sort","dict_enable","dict_memo"});
		JSONArray jsonArray = JSONArray.fromObject(list, jsonConfig);
		//��json���ݴ�ӡ��ҳ��
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		ServletActionContext.getResponse().getWriter().write(jsonArray.toString());
		return NONE;
	}
}
