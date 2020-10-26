package com.ssh.web.action;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.catalina.startup.WebAnnotationSet;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.ssh.domain.Customer;
import com.ssh.domain.PageBean;
import com.ssh.service.CustomerService;
import com.ssh.utils.UploadUtils;


public class CustomerAction extends ActionSupport implements ModelDriven<Customer>{
	
	private CustomerService customerService;

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	private Customer customer = new Customer();
	
	@Override
	public Customer getModel() {
		return customer;
	}
	// 注入当前页
	private Integer currPage = 1;
	
	public void setCurrPage(Integer currPage) {
		if(currPage == null) {
			currPage = 1;
		}
		this.currPage = currPage;
	}
	// 注入每页记录数
	private Integer pageSize = 3;
	
	public void setPageSize(Integer pageSize) {
		if(pageSize == null) {
			pageSize = 3;
		}
		this.pageSize = pageSize;
	}
	/**
	 * 文件上传提供的三个属性
	 */
	private String uploadFileName;//文件名称
	private File upload;//上传的文件
	private String uploadContextType;//文件类型
	
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public void setUploadContextType(String uploadContextType) {
		this.uploadContextType = uploadContextType;
	}

	public String findByPage() {
		// 接收分页参数
		// (条件查询,带分页)
		DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
		// 在web层设置条件
		if(customer.getCust_name() != null) {
			criteria.add(Restrictions.ilike("cust_name", "%"+customer.getCust_name()+"%"));
		}
		if(customer.getBaseDictLevel() != null) {
			if(customer.getBaseDictLevel().getDict_id() != null && !"".equals(customer.getBaseDictLevel().getDict_id())) {
				criteria.add(Restrictions.eq("baseDictLevel.dict_id", customer.getBaseDictLevel().getDict_id()));
			}
		}
		if(customer.getBaseDictIndustry() != null) {
			if(customer.getBaseDictIndustry().getDict_id() != null && !"".equals(customer.getBaseDictIndustry().getDict_id())) {
				criteria.add(Restrictions.eq("baseDictIndustry.dict_id", customer.getBaseDictIndustry().getDict_id()));
			}
		}
		if(customer.getBaseDictSource() != null) {
			if(customer.getBaseDictSource().getDict_id() != null && !"".equals(customer.getBaseDictSource().getDict_id())) {
				criteria.add(Restrictions.eq("baseDictSource.dict_id", customer.getBaseDictSource().getDict_id()));
			}
		}
		//调用业务层查询
		PageBean<Customer> pageBean = customerService.findByPage(criteria,currPage,pageSize);
		//将分页对象保存到值栈
		ActionContext.getContext().getValueStack().push(pageBean);
		return "findByPageSuccess";
	}
	
	public String saveUI() {
		return "saveUI";
	}
	public String save() throws IOException {
		//文件上传
		if(upload != null) {
			//上传资质图片
			//设置文件上传的路径
			String path = "D:/study/Demo/day1/upload";
			// 一个目录下存放的相同文件名:文件名随机
			String uuidFileName = UploadUtils.getUuidFileNam(uploadFileName);
			// 一个目录下存放的文件过多:目录分离
			String realPath = UploadUtils.getPath(uuidFileName);
			
			//创建目录
			String url = path + realPath;
			File file = new File(url);
			if(!file.exists()) {
				//创建多级目录
				file.mkdirs();
			}
			//进行文件上传
			File destFile=new File(url + "/" + uuidFileName);
			FileUtils.copyFile(upload, destFile);
			
			//设置文件上传的路径
			customer.setCust_image(url+"/"+uuidFileName);
		}
		
		//如果web层没有使用 Struts2 ,获取业务层的类就要进行如下编写
//		WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(ServletActionContext.getServletContext());
//		CustomerService customerService = (CustomerService) webApplicationContext.getBean("customerService");
		customerService.save(customer);
		return "saveSuccess";
	}
	
	public String delete() {
		//先查询在删除
		customer = customerService.findById(customer.getCust_id());
		//删除图片
		if(customer.getCust_image() != null) {
			File file = new File(customer.getCust_image());
			if(file != null) {
				file.delete();
			}
		}
		//删除客户
		customerService.delete(customer);
		return "deleteSuccess";
	}
	public String edit() {
		// 根据 ID 查询,跳转页面,回显数据
		customer = customerService.findById(customer.getCust_id());
		// 将 customer 传递到页面
		// 两种方式:第一种:手动压栈. 第二种:因为模型驱动的对象,默认就在栈顶
		// 如果使用第一种方式:回显数据: <s:property value="cust_name"/>
		// 如果使用第二种方式:回显数据: <s:property value="model.cust_name"/>
		return "editSuccess";
	}
	public String update() throws IOException {
		//判断文件项是否已经选择,如果选择了,删除原有文件,上传新文件,如果没有选择,使用原有文件即可
		if(upload != null) {
			//已经选择了
			//删除原有文件
			String cust_image = customer.getCust_image();
			if(cust_image != null || "".equals(cust_image)) {
				File file = new File(cust_image);
				if(file != null) {
					file.delete();
				}
			}
			//文件上传
			if(upload != null) {
				//上传资质图片
				//设置文件上传的路径
				String path = "D:/study/Demo/day1/upload";
				// 一个目录下存放的相同文件名:文件名随机
				String uuidFileName = UploadUtils.getUuidFileNam(uploadFileName);
				// 一个目录下存放的文件过多:目录分离
				String realPath = UploadUtils.getPath(uuidFileName);
				
				//创建目录
				String url = path + realPath;
				File file = new File(url);
				if(!file.exists()) {
					//创建多级目录
					file.mkdirs();
				}
				//进行文件上传
				File destFile=new File(url + "/" + uuidFileName);
				FileUtils.copyFile(upload, destFile);
				
				//设置文件上传的路径
				customer.setCust_image(url+"/"+uuidFileName);
			}
		}
		//修改客户
		customerService.update(customer);
		return "updateSuccess";
	}
}
