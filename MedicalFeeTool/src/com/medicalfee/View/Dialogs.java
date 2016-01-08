package com.medicalfee.View;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.medicalfee.R;
import com.medicalfee.Activity.QueryActivity;
import com.medicalfee.data.UseData;
import com.medicalfee.javabean.MedicalFeeBean;

public class Dialogs {

	/**
	 * 显示更改对话�?
	 * 
	 * @param context
	 */
	public static void ShowUpdataDialog(final Context context, final int position, final int key, String value) {
		LayoutInflater inflater = LayoutInflater.from(context);
		View view = inflater.inflate(R.layout.dialog_updata, null, false);
		TextView key_value = (TextView) view.findViewById(R.id.key_value);
		final EditText updata_value = (EditText) view.findViewById(R.id.updata_value);
		Button cancal = (Button) view.findViewById(R.id.cancal);
		Button updata = (Button) view.findViewById(R.id.updata);

		key_value.setText(UseData.key[key] + " : " + value);

		final Dialog dialog = new AlertDialog.Builder(context).create();
		dialog.show();
		dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
		dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
		dialog.getWindow().setContentView(view);

		cancal.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});
		// 确认修改
		updata.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				UseData useData = new UseData(context);
				MedicalFeeBean medicalFee = useData.medicalFeeList.get(position);
				String setStr = updata_value.getText().toString();
				switch (key) {
				case 0:
					medicalFee.setName(setStr);
					break;
				case 1:
					medicalFee.setWorkAge(setStr);
					break;
				case 2:
					medicalFee.setReceipt(setStr);
					break;
				case 3:
					medicalFee.setDeduction(setStr);
					break;
				}
				useData.upDataMedicalFee(position, medicalFee);
				((QueryActivity) context).listAdapter.updataList();
				dialog.dismiss();
			}
		});
	}
}
