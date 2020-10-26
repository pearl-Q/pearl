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
	* Description: 根据类型名称查询字典的方法
	* @return
	* @throws IOException
	*/
	public String findByTypeCode() throws IOException {
		List<BaseDict> list = baseDictService.findByTypeCode(baseDict.getDict_type_code());
		//将 list 转换为 json jsonlib fastjson
		/**
		* JSONConfig:转json的配置对象
		* JSONArray:将数组和List转换为json
		* JSONObject:将对象和Map转换为json
		*/
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[] {"dict_sort","dict_enable","dict_memo"});
		JSONArray jsonArray = JSONArray.fromObject(list, jsonConfig);
		//将json数据打印到页面
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		ServletActionContext.getResponse().getWriter().write(jsonArray.toString());
		return NONE;
	}
}
