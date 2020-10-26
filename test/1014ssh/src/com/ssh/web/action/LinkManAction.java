package com.ssh.web.action;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.ssh.domain.Customer;
import com.ssh.domain.LinkMan;
import com.ssh.domain.PageBean;
import com.ssh.service.CustomerService;
import com.ssh.service.LinkManServive;

public class LinkManAction extends ActionSupport implements ModelDriven<LinkMan> {
	private LinkMan linkMan =new LinkMan();
	@Override
	public LinkMan getModel() {
		return linkMan;
	}
	private LinkManServive linkManServive;
	public void setLinkManServive(LinkManServive linkManServive) {
		this.linkManServive = linkManServive;
	}
	
	private Integer currPage = 1;
	private Integer pageSize = 3;
	
	public void setCurrPage(Integer currPage) {
		if(currPage == null) {
			currPage = 1;
		}
		this.currPage = currPage;
	}

	public void setPageSize(Integer pageSize) {
		if(pageSize == null) {
			pageSize = 3;
		}
		this.pageSize = pageSize;
	}
	private CustomerService customerService;
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	/**
	 * Description: 待条件分页查询联系人
	 * @return
	 */
	public String findByPage() {
		//创建离线条件查询
		DetachedCriteria criteria = DetachedCriteria.forClass(LinkMan.class);
		//设置条件
		System.out.println(linkMan.getLkm_name() +"wrewrwrwe");
		if(linkMan.getLkm_name() != null) {
			criteria.add(Restrictions.like("lkm_name", "%"+linkMan.getLkm_name()+"%"));
		}
		if(linkMan.getLkm_gender() != null && !"".equals(linkMan.getLkm_gender())) {
			criteria.add(Restrictions.eq("lkm_gender", linkMan.getLkm_gender()));
		}
		//调用业务层
		PageBean<LinkMan> pageBean = linkManServive.findByPage(criteria,currPage,pageSize);
		//将 pageBean 压入值栈中
		ActionContext.getContext().getValueStack().push(pageBean);
		return "findByPageSuccess";
	}
	public String saveUI() {
		//查询所有客户
		List<Customer> list = customerService.findAll();
		// 将所有客户存入值栈
		ActionContext.getContext().getValueStack().set("list", list);
		return "saveUI";
	}
	public String save() {
		System.out.println(linkMan);
		linkManServive.save(linkMan);
		return "saveSuccess";
	}
	public String edit() {
		List<Customer> list = customerService.findAll();
		linkMan = linkManServive.findById(linkMan.getLkm_id());
		// 将所有客户和联系人带回到页面
		ActionContext.getContext().getValueStack().set("list", list);
		ActionContext.getContext().getValueStack().push(linkMan);
		return "editSuccess";
	}
	public String update() {
		linkManServive.update(linkMan);
		return "updateSuccess";
	}
	public String delete() {
		linkMan = linkManServive.findById(linkMan.getLkm_id());
		linkManServive.delete(linkMan);
		return "deleteSuccess";
	}
}
