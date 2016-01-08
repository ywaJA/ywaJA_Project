package com.medicalfee.javabean;

import java.util.List;

public class JokeBean {
	private String showapi_res_code;
	private String showapi_res_error;
	private String showapi_res_body;
	private String allNum;
	private String allPages;
	private List<content> contentlist;
	private String currentPage;// ": 1,
	private String maxResult;// ": 20

	public String getShowapi_res_code() {
		return showapi_res_code;
	}

	public void setShowapi_res_code(String showapi_res_code) {
		this.showapi_res_code = showapi_res_code;
	}

	public String getShowapi_res_error() {
		return showapi_res_error;
	}

	public void setShowapi_res_error(String showapi_res_error) {
		this.showapi_res_error = showapi_res_error;
	}

	public String getShowapi_res_body() {
		return showapi_res_body;
	}

	public void setShowapi_res_body(String showapi_res_body) {
		this.showapi_res_body = showapi_res_body;
	}

	public String getAllNum() {
		return allNum;
	}

	public void setAllNum(String allNum) {
		this.allNum = allNum;
	}

	public String getAllPages() {
		return allPages;
	}

	public void setAllPages(String allPages) {
		this.allPages = allPages;
	}

	public List<content> getContentlist() {
		return contentlist;
	}

	public void setContentlist(List<content> contentlist) {
		this.contentlist = contentlist;
	}

	public String getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}

	public String getMaxResult() {
		return maxResult;
	}

	public void setMaxResult(String maxResult) {
		this.maxResult = maxResult;
	}

	public class content {
		private String ct;// ": "2015-08-13 13:10:26.149",
		private String text;// ": "新人发帖求过……
		private String title;// ": "媳妇儿有了…",
		private String type;// ": 1

		public String getCt() {
			return ct;
		}

		public void setCt(String ct) {
			this.ct = ct;
		}

		public String getText() {
			return text;
		}

		public void setText(String text) {
			this.text = text;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

	}
}
