package com.common.model;

import java.util.List;

/**
 * 
 * @Description: 请求后台服务响应的json数据映射基础对象
 * @author qinwang
 * @createDate 2015-6-4
 * @since iccs V01.00.000
 */
public class BaseShowVo<T> {

	/** 总记录数 */
	private int total;
	/** 结果集 */
	private List<T> rows;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

}
