package com.cafe24.mysite.vo;

public class PageVo {
	private int page;
	private int totalData;
	private int dataPerPage = 10;
	private int displayPage = 5;
	private int totalPage;
	private int startData;
	private int endData;
	private int startPage;
	private int endPage;
	private boolean prev;
	private boolean next;
	
	public PageVo() {
		this.page = 1;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		if (page <= 0) {
			this.page = 1;
			return;
		}
		this.page = page;
	}
	public long getTotalData() {
		return totalData;
	}
	public void setTotalData(int totalData) {
		this.totalData = totalData;
	}
	public long getTotalPage() {
		return (int) Math.ceil(totalData / dataPerPage);
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getStartData() {
		return (page-1) * dataPerPage;
	}
	public void setStartData(int startData) {
		this.startData = startData;
	}
	public int getEndData() {
		return page * dataPerPage;
	}
	public void setEndData(int endData) {
		this.endData = endData;
	}
	public int getStartPage() {
		return endPage - displayPage + 1;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return (int) (Math.ceil(page/displayPage) * displayPage);
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public boolean getPrev() {
		return prev = startPage == 1 ? false : true;
	}
	public void setPrev(boolean prev) {
		this.prev = prev;
	}
	public boolean getNext() {
		return next = endPage * dataPerPage >= totalData ? false : true;
	}
	public void setNext(boolean next) {
		this.next = next;
	}
	
	public void paging() {
		this.totalPage = (int) Math.ceil(totalData / dataPerPage);
		this.startData = (page-1) * dataPerPage;
		this.endData = page * dataPerPage;
		this.endPage = (int) (Math.ceil(page/displayPage) * displayPage);
		this.startPage = endPage - displayPage + 1;
		this.prev = startPage == 1 ? false : true;
		this.next = endPage * dataPerPage >= totalData ? false : true; 
	}
	
}
