package com.medicalfee.Activity;

import org.apache.http.client.methods.HttpHead;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.common.Constants;
import com.common.config.Config;
import com.common.model.BaseCoreVo;
import com.common.service.BaseService;
import com.common.utils.ServiceUtil;
import com.google.gson.reflect.TypeToken;
import com.medicalfee.R;
import com.medicalfee.data.UseData;
import com.medicalfee.javabean.JokeBean;
import com.medicalfee.javabean.MedicalFeeBean;
import com.medicalfee.util.MediaUtil;
import com.medicalfee.util.SensorManagerHelper;
import com.medicalfee.util.SensorManagerHelper.OnShakeListener;
import com.medicalfee.util.Util;

public class MainActivity extends Activity implements OnClickListener, OnShakeListener {
	private EditText name, work_age, receipt, deduction;
	private TextView show_result;
	private Button ok, add, query;

	private MedicalFeeBean medicalFee;
	private UseData useData;

	private SensorManagerHelper sensorHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		name = (EditText) findViewById(R.id.name);
		work_age = (EditText) findViewById(R.id.work_age);
		receipt = (EditText) findViewById(R.id.receipt);
		deduction = (EditText) findViewById(R.id.deduction);
		ok = (Button) findViewById(R.id.ok);
		add = (Button) findViewById(R.id.add);
		query = (Button) findViewById(R.id.query);
		show_result = (TextView) findViewById(R.id.show_result);

		name.setTypeface(Util.getOfficialScript(this));
		work_age.setTypeface(Util.getOfficialScript(this));
		receipt.setTypeface(Util.getOfficialScript(this));
		deduction.setTypeface(Util.getOfficialScript(this));
		ok.setTypeface(Util.getOfficialScript(this));
		add.setTypeface(Util.getOfficialScript(this));
		query.setTypeface(Util.getOfficialScript(this));
		show_result.setTypeface(Util.getOfficialScript(this));

		ok.setOnClickListener(this);
		add.setOnClickListener(this);
		query.setOnClickListener(this);
		sensorHelper = new SensorManagerHelper(this);
		sensorHelper.setOnShakeListener(this);
//		getJok();
	}

	private void getJok() {
		// TODO Auto-generated method stub
		HttpHead hh = new HttpHead(Config.JOKE);
		hh.addHeader("apikey", Config.BAIDU_KEY);
		BaseCoreVo vo = new BaseCoreVo(hh.toString());
		vo.setRequestMode(Constants.REQUEST_MODE_GET);
		vo.setDataCompress(Constants.DATA_COMPRESS_NO);
		vo.setMap("page", "1");
		ServiceUtil.getJsonData(vo, new TypeToken<JokeBean>() {
		}, new BaseService<JokeBean>() {

			@Override
			public void callBackJsonData(JokeBean object) {
				// TODO Auto-generated method stub
				if (object != null) {
					object.getAllNum();
				}
			}
		});
	}

	public void onClick(View v) {
		String name = this.name.getText().toString().trim();
		String workage = this.work_age.getText().toString().trim();
		String receipt = this.receipt.getText().toString().trim();
		String deduction = this.deduction.getText().toString().trim();
		switch (v.getId()) {
		case R.id.ok:
			// 让软键盘消失
			InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
			imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
			if (workage == null || workage.length() == 0) {
				Toast.makeText(this, "请输入工龄", Toast.LENGTH_SHORT).show();
				return;
			}
			medicalFee = new MedicalFeeBean(name, workage, receipt, deduction);
			show_result.setText("结果：\n 姓名:" + name + "\n 工龄:" + workage + "\n 限额:" + medicalFee.getYearLimits() + "\n 收据核定:" + medicalFee.getReceiptApproval() + "\n 核销额:" + medicalFee.getAmountVerification());
			break;
		case R.id.add:
			if (name == null || name.length() == 0) {
				Toast.makeText(this, "请输入姓名", Toast.LENGTH_SHORT).show();
				return;
			}
			if (workage == null || workage.length() == 0) {
				Toast.makeText(this, "请输入工龄", Toast.LENGTH_SHORT).show();
				return;
			}
			if (receipt == null || receipt.length() == 0) {
				Toast.makeText(this, "请输入收据", Toast.LENGTH_SHORT).show();
				return;
			}
			if (deduction == null || deduction.length() == 0) {
				Toast.makeText(this, "请输入减项", Toast.LENGTH_SHORT).show();
				return;
			}
			useData = new UseData(this);
			useData.addmedicalFeeData(medicalFee);
			Toast.makeText(this, "数据插入成功", Toast.LENGTH_SHORT).show();
			break;
		case R.id.query:
			startActivity(new Intent(this, QueryActivity.class));
			break;
		}
	}

	@Override
	public void onShake() {
		// TODO Auto-generated method stub
		MediaUtil.startMP3(this, R.raw.kiddie);
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		sensorHelper.stop();
		super.onStop();
	}
}
