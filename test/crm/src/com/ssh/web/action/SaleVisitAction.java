package com.ssh.web.action;

import static org.hamcrest.CoreMatchers.nullValue;

import java.util.List;

import org.apache.jasper.tagplugins.jstl.core.If;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.ssh.domain.Customer;
import com.ssh.domain.PageBean;
import com.ssh.domain.SaleVisit;
import com.ssh.domain.User;
import com.ssh.service.CustomerService;
import com.ssh.service.SaleVisitService;
import com.ssh.service.UserService;

public class SaleVisitAction extends ActionSupport implements ModelDriven<SaleVisit>{
	
	private SaleVisit saleVisit = new SaleVisit();
	@Override
	public SaleVisit getModel() {
		return saleVisit;
	}
	
	private SaleVisitService saleVisitService;
	public void setSaleVisitService(SaleVisitService saleVisitService) {
		this.saleVisitService = saleVisitService;
	}
	private CustomerService customerService;
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
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

	public String findByPage() {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SaleVisit.class);
		
		//查询客户传回页面 筛选
		List<Customer> customers = customerService.findAll();
		ActionContext.getContext().getValueStack().set("customers", customers);
		
		List<User> users = userService.findAll();
		ActionContext.getContext().getValueStack().set("users", users);
		
		if(saleVisit.getCustomer() != null) {
			if(saleVisit.getCustomer().getCust_id() != null && !"".equals(saleVisit.getCustomer().getCust_id())) {
				System.out.println(saleVisit.getCustomer().getCust_id());
				detachedCriteria.add(Restrictions.eq("customer.cust_id", saleVisit.getCustomer().getCust_id()));
			}
		}
		if(saleVisit.getUser() != null) {
			if(saleVisit.getUser().getUser_id() != null && !"".equals(saleVisit.getUser().getUser_id())) {
				System.out.println(saleVisit.getUser().getUser_id());
				detachedCriteria.add(Restrictions.eq("user.user_id", saleVisit.getUser().getUser_id()));
			}
		}
		if(saleVisit.getVisit_time() != null && !"".equals(saleVisit.getVisit_time())) {
			System.out.println(saleVisit.getVisit_time());
			detachedCriteria.add(Restrictions.eq("visit_time", saleVisit.getVisit_time()));
		}
		
		PageBean<SaleVisit> pageBean = saleVisitService.findByPage(detachedCriteria, currPage, pageSize);
		ActionContext.getContext().getValueStack().push(pageBean);
		return "findByPageSuccess";
	}
	
	public String saveUI() {
		List<Customer> customers = customerService.findAll();
		List<User> users = userService.findAll();
		
		ActionContext.getContext().getValueStack().set("customers", customers);
		ActionContext.getContext().getValueStack().set("users", users);
		return "saveUI";
	}
	public String save() {
		saleVisitService.save(saleVisit);
		return "saveSuccess";
	}
	public String delete() {
		saleVisit = saleVisitService.findById(saleVisit.getVisit_id());
		saleVisitService.delete(saleVisit);
		return "deleteSuccess";
	}
	public String edit() {
		List<Customer> customers = customerService.findAll();
		List<User> users = userService.findAll();
		saleVisit = saleVisitService.findById(saleVisit.getVisit_id());
		
		ActionContext.getContext().getValueStack().set("customers", customers);
		ActionContext.getContext().getValueStack().set("users", users);
		ActionContext.getContext().getValueStack().push(saleVisit);
		return "editSuccess";
	}
	public String update() {
		saleVisitService.update(saleVisit);
		return "updateSuccess";
	}
}
