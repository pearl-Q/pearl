package com.ssh.domain;

import java.util.List;

public class PageBean<T> {
	// ��ǰҳ
	private Integer currPage;
	// ÿҳ��¼��
	private Integer pageSize;
	// ��ҳ��
	private Integer totalPage;
	// �ܼ�¼��
	private Integer totalCount;
	// ÿҳ��ʾ������
	private List<T> list;
	public Integer getCurrPage() {
		return currPage;
	}
	public void setCurrPage(Integer currPage) {
		this.currPage = currPage;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	
}
