package com.woniuxy.entities;

import java.util.List;

public class PageBean<T> {
	//����ҳ�������Ϣ���з�װ
	private int totalCount;//������
	private int pageSize;//ÿҳ��ʾ����Ŀ��
	private int currentPage;//��ǰ��ҳ����
	private int pages;//��ҳ��
	private List<T> data;//ÿҳ��ʾ������
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	public int getPages() {
		//��ҳ��
		this.pages = totalCount%pageSize==0?totalCount/pageSize:totalCount/pageSize+1;
		return pages;
	}
	@Override
	public String toString() {
		return "PageBean [totalCount=" + totalCount + ", pageSize=" + pageSize + ", currentPage=" + currentPage
				+ ", pages=" + pages + ", data=" + data + "]";
	}
	
}
