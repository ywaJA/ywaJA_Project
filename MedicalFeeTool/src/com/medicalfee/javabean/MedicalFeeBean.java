package com.medicalfee.javabean;

import android.util.Log;

import com.medicalfee.util.Util;

public class MedicalFeeBean {
	private String name;// 名字
	private String workAge;// 工龄
	private String receipt;// 住院收据
	private String deduction;// 其它（减项）
	private String receiptApproval;// 住院收据核定
	private String amountVerification;// 住院核销额
	private String yearLimits;// 年度限额标准

	public String getYearLimits() {
		return yearLimits;
	}

	public void setYearLimits(String yearLimits) {
		this.yearLimits = yearLimits;
	}

	public void setYearLimits() {
		String strs[] = Util.deal(workAge, getReceiptApproval());
		this.yearLimits = strs[1];
	}

	public MedicalFeeBean() {
		super();
	}

	public MedicalFeeBean(String name, String workAge, String receipt, String deduction) {
		super();
		this.name = name;
		this.workAge = workAge;
		this.receipt = receipt;
		this.deduction = deduction;
		setReceiptApproval();
		setYearLimits();
		setAmountVerification();
	}

	private void setReceiptApproval() {
		if (receipt != null && deduction != null) {
			try {
				setReceiptApproval("" + (Float.parseFloat(receipt) - Float.parseFloat(deduction)));
			} catch (Exception e) {
				Log.e("数据类型转化异常", "未知错误");
			}
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWorkAge() {
		return workAge;
	}

	public void setWorkAge(String workAge) {
		setYearLimits();
		setAmountVerification();
		this.workAge = workAge;
	}

	public String getReceipt() {
		return receipt;
	}

	public void setReceipt(String receipt) {
		if (receipt != null && deduction != null) {
			try {
				setReceiptApproval("" + (Float.parseFloat(receipt) - Float.parseFloat(deduction)));
			} catch (Exception e) {
				Log.e("数据类型转化异常", "未知错误");
			}
		}
		this.receipt = receipt;
	}

	public String getDeduction() {
		return deduction;
	}

	public void setDeduction(String deduction) {
		if (receipt != null && deduction != null) {
			try {
				setReceiptApproval("" + (Float.parseFloat(receipt) - Float.parseFloat(deduction)));
			} catch (Exception e) {
				Log.e("数据类型转化异常", "未知错误");
			}
		}
		this.deduction = deduction;
	}

	public String getReceiptApproval() {
		return receiptApproval;
	}

	public void setReceiptApproval(String receiptApproval) {
		setAmountVerification();
		this.receiptApproval = receiptApproval;
	}

	public String getAmountVerification() {
		return amountVerification;
	}

	public void setAmountVerification(String amountVerification) {
		this.amountVerification = amountVerification;
	}

	public void setAmountVerification() {
		String strs[] = Util.deal(workAge, getReceiptApproval());
		this.amountVerification = strs[0];
	}
}
