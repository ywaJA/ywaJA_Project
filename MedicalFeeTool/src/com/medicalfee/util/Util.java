package com.medicalfee.util;

import java.util.ArrayList;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.util.Log;

import com.medicalfee.data.DBHelper;
import com.medicalfee.javabean.MedicalFeeBean;

public class Util {
	private static DBHelper db;

	public static Typeface getOfficialScript(Context context) {
		AssetManager mgr = context.getAssets();// �õ�AssetManager
		Typeface tf = Typeface.createFromAsset(mgr, "fonts/official_script.ttf");// ���·���õ�Typeface
		return tf;
	}

	public static ArrayList<MedicalFeeBean> getDBdate(Context context) {
		if (db == null) {
			db = new DBHelper(context, "lxlList", null, 1);
		}
		return db.QueryData();
	}

	public static String[] deal(String workAge, String receiptApproval) {
		// ����޶��׼
		float yearLimits = 0;
		// סԺ�����
		float amountVerification = 0;
		// ����֣�ÿ��ÿ��1300Ԫ
		int basic = 1300;
		// �����
		int add = 0;
		// Ա���Ĺ���
		int workAge_int = 0;
		// סԺ�վݺ˶�
		float receiptApproval_float = 0;
		String strs[] = new String[2];
		if (workAge == null && receiptApproval == null) {
			return strs;
		}
		try {
			workAge_int = Integer.parseInt(workAge);
			receiptApproval_float = Float.parseFloat(receiptApproval);
		} catch (Exception e) {
			Log.e("�������ת���쳣", "δ֪����");
		}

		add = 90 * workAge_int;

		yearLimits = basic + add;
		if (receiptApproval_float <= yearLimits)
			amountVerification = yearLimits;
		else if (yearLimits < receiptApproval_float && receiptApproval_float <= 5000)
			amountVerification = yearLimits + (receiptApproval_float - yearLimits) * 3 / 4;
		else if (receiptApproval_float > 5000 && receiptApproval_float <= 10000)
			amountVerification = yearLimits + (5000 - yearLimits) * 3 / 4 + (receiptApproval_float - 5000) * 4 / 5;
		else if (receiptApproval_float > 10000 && receiptApproval_float <= 20000)
			amountVerification = yearLimits + (5000 - yearLimits) * 3 / 4 + (10000 - 5000) * 4 / 5 + (receiptApproval_float - 10000) * 85 / 100;
		else if (receiptApproval_float > 20000)
			amountVerification = yearLimits + (5000 - yearLimits) * 3 / 4 + (10000 - 5000) * 4 / 5 + (20000 - 10000) * 85 / 100 + (receiptApproval_float - 20000) * 9 / 10;
		strs[0] = "" + amountVerification;
		strs[1] = "" + yearLimits;
		return strs;
	}
}
