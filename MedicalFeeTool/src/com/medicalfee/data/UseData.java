package com.medicalfee.data;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.medicalfee.javabean.MedicalFeeBean;

public class UseData {
	private static String SHARED_MEDICAL_FEE = "sharedMedicalFee";// 缓存小区信息缓存主键
	public List<MedicalFeeBean> medicalFeeList;
	private Context context;
	public static String key[] = { "姓名", "工龄", "收据", "收据减项", "收据核定", "核销额" };

	public UseData(Context context) {
		super();
		this.context = context;
		getMedicalFee();
	}

	/**
	 * 缓存信息
	 * 
	 * @param user
	 */
	public void setMedicalFeeData() {
		Shared.clear(context, SHARED_MEDICAL_FEE);
		Shared.saveGson(context, SHARED_MEDICAL_FEE, medicalFeeList);
	}

	/**
	 * 获取缓存中医疗表的信息
	 * 
	 * @return
	 */
	public void getMedicalFee() {
		medicalFeeList = Shared.getGson(context, SHARED_MEDICAL_FEE, new TypeToken<List<MedicalFeeBean>>() {
		});
		if (medicalFeeList == null) {
			medicalFeeList = new ArrayList<MedicalFeeBean>();
		}
	}

	public void addmedicalFeeData(MedicalFeeBean medicalFee) {
		medicalFeeList.add(medicalFee);
		setMedicalFeeData();
	}

	public void deleteMedicalFee(int position) {
		medicalFeeList.remove(position);
		setMedicalFeeData();
	}

	public void upDataMedicalFee(int position, MedicalFeeBean medicalFee) {
		medicalFeeList.set(position, medicalFee);
		setMedicalFeeData();
	}
}
