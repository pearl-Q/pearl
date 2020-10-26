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
	 * Description: ��������ҳ��ѯ��ϵ��
	 * @return
	 */
	public String findByPage() {
		//��������������ѯ
		DetachedCriteria criteria = DetachedCriteria.forClass(LinkMan.class);
		//��������
		System.out.println(linkMan.getLkm_name() +"wrewrwrwe");
		if(linkMan.getLkm_name() != null) {
			criteria.add(Restrictions.like("lkm_name", "%"+linkMan.getLkm_name()+"%"));
		}
		if(linkMan.getLkm_gender() != null && !"".equals(linkMan.getLkm_gender())) {
			criteria.add(Restrictions.eq("lkm_gender", linkMan.getLkm_gender()));
		}
		//����ҵ���
		PageBean<LinkMan> pageBean = linkManServive.findByPage(criteria,currPage,pageSize);
		//�� pageBean ѹ��ֵջ��
		ActionContext.getContext().getValueStack().push(pageBean);
		return "findByPageSuccess";
	}
	public String saveUI() {
		//��ѯ���пͻ�
		List<Customer> list = customerService.findAll();
		// �����пͻ�����ֵջ
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
		// �����пͻ�����ϵ�˴��ص�ҳ��
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
