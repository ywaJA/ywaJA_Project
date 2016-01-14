package com.medicalfee.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.medicalfee.R;
import com.medicalfee.View.Dialogs;
import com.medicalfee.data.UseData;

public class ListAdapter extends BaseAdapter implements OnClickListener {
	private Context context;
	private UseData useData;

	public ListAdapter(Context context) {
		super();
		this.context = context;
		useData = new UseData(context);
	}

	public int getCount() {
		return useData.medicalFeeList.size();
	}

	public Object getItem(int position) {
		return useData.medicalFeeList.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(final int position, View convertView, ViewGroup parent) {
		Holder holder;
		if (convertView == null) {
			holder = new Holder();
			convertView = LayoutInflater.from(context).inflate(R.layout.list_item, null);
			holder.item_id = (TextView) convertView.findViewById(R.id.item_id);
			holder.item_name = (TextView) convertView.findViewById(R.id.item_name);
			holder.work_age = (TextView) convertView.findViewById(R.id.work_age);
			holder.yearLimits = (TextView) convertView.findViewById(R.id.yearLimits);
			holder.receipt = (TextView) convertView.findViewById(R.id.receipt);
			holder.deduction = (TextView) convertView.findViewById(R.id.deduction);
			holder.receipt_approval = (TextView) convertView.findViewById(R.id.receipt_approval);
			holder.amount_verification = (TextView) convertView.findViewById(R.id.amount_verification);
			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
		}
		holder.item_id.setText((position + 1) + "");
		holder.item_name.setText(useData.medicalFeeList.get(position).getName());
		holder.work_age.setText(useData.medicalFeeList.get(position).getWorkAge());
		holder.yearLimits.setText(useData.medicalFeeList.get(position).getYearLimits());
		holder.receipt.setText(useData.medicalFeeList.get(position).getReceipt());
		holder.deduction.setText(useData.medicalFeeList.get(position).getDeduction());
		holder.receipt_approval.setText(useData.medicalFeeList.get(position).getReceiptApproval());
		holder.amount_verification.setText(useData.medicalFeeList.get(position).getAmountVerification());

		holder.item_id.setTag(position);
		holder.item_name.setTag(position);
		holder.work_age.setTag(position);
		holder.receipt.setTag(position);
		holder.deduction.setTag(position);

		holder.item_id.setOnClickListener(this);
		holder.item_name.setOnClickListener(this);
		holder.work_age.setOnClickListener(this);
		holder.receipt.setOnClickListener(this);
		holder.deduction.setOnClickListener(this);

		return convertView;
	}

	public class Holder {
		private TextView item_id, item_name, work_age,yearLimits, receipt, deduction, receipt_approval, amount_verification;
	}

	public void updataList() {
		useData.getMedicalFee();
		notifyDataSetChanged();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int position = (Integer) v.getTag();
		if (v.getId() == R.id.item_id) {
			useData.deleteMedicalFee(position);
			updataList();
			return;
		}
		String value = ((TextView) v).getText().toString();
		switch (v.getId()) {
		case R.id.item_name:
			Dialogs.ShowUpdataDialog(context, position, 0, value);
			break;
		case R.id.work_age:
			Dialogs.ShowUpdataDialog(context, position, 1, value);
			break;
		case R.id.receipt:
			Dialogs.ShowUpdataDialog(context, position, 2, value);
			break;
		case R.id.deduction:
			Dialogs.ShowUpdataDialog(context, position, 3, value);
			break;
		}
	}
}
