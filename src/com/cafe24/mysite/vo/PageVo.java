package com.cafe24.mysite.vo;

public class PageVo {
	private int page;
	private long totalPost;
	private long postPerPage = 10;
	private long totalPage = (long) Math.ceil(postPerPage / totalPost);
	private long startData;
	private long endData;
	private boolean isPrev;
	private boolean isNext;
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public long getTotalPost() {
		return totalPost;
	}
//	public long getPostPerPage() {
//		return postPerPage;
//	}
//	public void setPostPerPage(long postPerPage) {
//		this.postPerPage = postPerPage;
//	}
	public long getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(long totalPage) {
		this.totalPage = totalPage;
	}
	public long getStartData() {
		return startData;
	}
	public void setStartData(long page) {
		this.startData = (page -1) * this.postPerPage;
	}
	public long getEndData() {
		return endData;
	}
	public void setEndData(long page) {
		this.endData = page * postPerPage;
	}
	public boolean getIsPrev() {
		return isPrev;
	}
	public void setPrev(boolean isPrev) {
		this.isPrev = isPrev;
	}
	public boolean getIsNext() {
		return isNext;
	}
	public void setIsNext(boolean isNext) {
		this.isNext = isNext;
	}
	
	
}
