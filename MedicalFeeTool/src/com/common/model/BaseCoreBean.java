/**
 * sunrise, Inc. All rights reserved. Copyright (C): 2015
 */
package com.common.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.common.config.Constants;

/**
 *  @Description: 
 *		 class 参数查询基础VO
 *  Revision History:
 *  DATE        AUTHOR        VERSION        DESCRIPTION
 *
 * @author 黄钢
 * @createDate 2015-5-19
 * @since iccs V01.00.000
 */

@SuppressWarnings("serial")
public class BaseCoreBean implements Serializable{
	
	private int requestMode=Constants.REQUEST_MODE_POST;//默认是post请求方式 如果是GET请求方式 这为REQUEST_MODE_GET
	private int dataCompress=Constants.DATA_COMPRESS_YES;//数据返回是否压缩
	private Integer page=null; //当前页码
	private Integer rows=null;//每页条数
	private String  url;//URL
	private Map<String, Object> map=new HashMap<String, Object>();//页面字段存放Map对象
	
	public BaseCoreBean(String url){
		this.url=url;
	}
	
	public BaseCoreBean(String url,Integer rows){
		this.url=url;
		this.rows=rows;
	}
	
	public BaseCoreBean(String url,Integer rows,Integer page){
		this.url=url;
		this.rows=rows;
		this.page=page;
	}

	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getRows() {
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	public Map<String, Object> getMap() {
		return map;
	}
	
	public void setMap(String key,Object obj) {
		this.map.put(key, obj);
	}
	
	public int getRequestMode() {
		return requestMode;
	}
	
	public void setRequestMode(int requestMode) {
		this.requestMode = requestMode;
	}
	public int getDataCompress() {
		return dataCompress;
	}
	public void setDataCompress(int dataCompress) {
		this.dataCompress = dataCompress;
	}
	
}
