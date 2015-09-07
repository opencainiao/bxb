package com.bxb.common.globalobj;

import java.util.List;

import org.mou.common.JsonUtil;

import com.mongodb.util.JSON;

@SuppressWarnings("rawtypes")
public class PageVO {
	private int total; // 锟杰硷拷录锟斤拷
	private int page; // 锟斤拷前锟角第硷拷页
	private int maxPage; // 锟杰癸拷锟斤拷锟斤拷页
	private int pageCount; // 每页锟斤拷锟斤拷锟斤拷

	private int offSet; // 锟斤拷询锟斤拷始锟斤拷锟斤拷(锟斤拷锟斤拷锟�
	private List rows; // 锟斤拷锟�

	public PageVO() {
		offSet = 0;
		pageCount = Integer.MAX_VALUE;
	}

	/****
	 * 锟斤拷荽锟斤拷锟侥碉拷前页锟诫，锟斤拷锟斤拷锟窖拷锟斤拷锟斤拷械锟絆FFSET
	 */
	public void calOffset() {
		this.offSet = this.page * this.pageCount;
	}

	/****
	 * 锟斤拷锟斤拷锟绞硷拷锟斤拷锟斤拷锟姐当前锟角第硷拷页
	 */
	private void calCurrentPage() {
		page = offSet / pageCount + 1;
	}

	/****
	 * 锟斤拷锟斤拷芗锟铰硷拷锟斤拷锟姐共锟叫讹拷锟斤拷页
	 */
	public void calMaxPage() {
		maxPage = total / pageCount;

		if (total % pageCount != 0) {
			maxPage += 1;
		}
	}

	/****
	 * 锟斤拷菁锟铰硷拷锟斤拷锟较拷锟斤拷锟斤拷锟揭筹拷锟斤拷锟较�
	 */
	public void calPageInf() {
		calMaxPage();
		calCurrentPage();
	}

	public int getOffSet() {
		return offSet;
	}

	public void setOffSet(int offSet) {
		this.offSet = offSet;
	}

	public List getRows() {
		return rows;
	}

	public void setRows(List rows) {
		this.rows = rows;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int totalCount) {
		this.total = totalCount;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int currentPage) {
		this.page = currentPage;
	}

	public int getMaxPage() {
		return maxPage;
	}

	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
}
