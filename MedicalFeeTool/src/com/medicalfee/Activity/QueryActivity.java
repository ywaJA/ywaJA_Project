package com.medicalfee.Activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.ListView;
import android.widget.Toast;

import com.medicalfee.R;
import com.medicalfee.Adapter.ListAdapter;

public class QueryActivity extends FragmentActivity {
	private ListView listView;
	public ListAdapter listAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.query_act);
		listView = (ListView) findViewById(R.id.listView);
		initListView();
	}

	private void initListView() {
		if (listAdapter == null) {
			listAdapter = new ListAdapter(this);
			listView.setAdapter(listAdapter);
		} else {
			listAdapter.updataList();
			Toast.makeText(this, "已更新", Toast.LENGTH_SHORT).show();
		}
	}
}
