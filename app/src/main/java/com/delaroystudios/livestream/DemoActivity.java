package com.delaroystudios.livestream;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import com.delaroystudios.livestream.R;

public class DemoActivity extends ListActivity implements OnItemClickListener {

	List<String> items;
	ArrayAdapter<String> adapter;

	@SuppressLint("SdCardPath")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_demo);

		items = new ArrayList<String>();
		items.add("http://img1.peiyinxiu.com/2014121211339c64b7fb09742e2c.mp4");
		items.add("http://163.172.217.53:8000/live/5grandiptv915/67863345/1338.ts");
		items.add("http://163.172.217.53:8000/live/5grandiptv915/67863345/1339.ts");
		items.add("http://163.172.217.53:8000/live/5grandiptv915/67863345/687.ts");
		items.add("http://163.172.217.53:8000/live/5grandiptv915/67863345/686.ts");
		items.add("http://caribrootstv.com/tv/smarttv.m3u8");

		//read data
		SharedPreferences mySharedPreferences = getSharedPreferences("test", Activity.MODE_PRIVATE);
		((EditText) findViewById(R.id.et_url)).setText(mySharedPreferences.getString("url", ""));

		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
		setListAdapter(adapter);
		getListView().setOnItemClickListener(this);

		findViewById(R.id.btn_play).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				String url = ((EditText) findViewById(R.id.et_url)).getText().toString().trim();
				if (!TextUtils.isEmpty(url)) {
					startActivity(new Intent(DemoActivity.this, PlayerActivity.class).putExtra("url", url));
				}

				//save data
				SharedPreferences mySharedPreferences = getSharedPreferences("test", Activity.MODE_PRIVATE);
				SharedPreferences.Editor editor = mySharedPreferences.edit();
				editor.putString("url", ((EditText) findViewById(R.id.et_url)).getText().toString().trim());
				editor.commit();

			}
		});
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
		startActivity(new Intent(this, PlayerActivity.class).putExtra("url", items.get(position)));
	}
}
