package com.taotao.common.bean;

import java.util.List;

/**
 * 针对EasyUi的表格返回结果的封装对象
 */

public class EasyUiResult {
	
	private Long total;//总记录数
	
	private List<?> rows; //结果集集合

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}

	public EasyUiResult(Long total, List<?> rows) {
		super();
		this.total = total;
		this.rows = rows;
	}

	public EasyUiResult() {
		super();
	}
}
