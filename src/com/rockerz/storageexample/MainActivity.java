package com.rockerz.storageexample;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	TextView tv,tv1;
	String text;
	static int i=0,j=0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		addListenerOnButton();
	}
	@Override
	protected void onRestart(){
		super.onRestart();
		if(SQLite.test()){
		getSqlite();
		}
		if(SharedMemory.test()){
			getPreferences();
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	public void addListenerOnButton() {	
		final Context context=this;
		Button sharedmemory = (Button) findViewById(R.id.sharedmemory);
		sharedmemory.setOnClickListener(new OnClickListener() { 
			@Override
			public void onClick(View arg0) {		
				Intent sharedintent=new Intent(context,SharedMemory.class);
				startActivity(sharedintent);
			}
		});
		Button sqlite = (Button) findViewById(R.id.sqlite);
		sqlite.setOnClickListener(new OnClickListener() { 
			@Override
			public void onClick(View arg0) {		
				Intent sharedintent=new Intent(context,SQLite.class);
				startActivity(sharedintent);
			}
		});
		Button close = (Button) findViewById(R.id.close);
		close.setOnClickListener(new OnClickListener() { 
			@Override
			public void onClick(View arg0) {	
				finish();
			}
		});

	}
	public void getSqlite(){
		SQLite.flag=false;
		List<String> dates=new ArrayList<String>();
		Sqlitedb sq=new Sqlitedb(this);
		tv=(TextView)findViewById(R.id.update);
		text=(String)tv.getText();
		System.out.println(text);
		dates=sq.getMessages();
	    //tv.setText("");
		for(i=0;i<dates.size();i++){
	  //tv.setText(text+"\n"+ "SQLite" +i+", "+dates.get(i));
	    tv.setText(text+"\n"+"SQLite:" +(i+1)+", "+dates.get(i));	
		}
	}
	public void getPreferences(){
		SharedMemory.flag=false;
		//tv1=(TextView)findViewById(R.id.update1);
		SharedPreferences preferences=PreferenceManager.getDefaultSharedPreferences(this);
		//String text1=(String)tv1.getText();
		//tv1.setText(text1+"\n"+ "SavedPrefernces:"+SharedMemory.i+","+preferences.getString("date",""));	
		tv=(TextView)findViewById(R.id.update);
		text=(String)tv.getText();
		tv.setText(text+"\n"+ "SavedPrefernces:"+SharedMemory.i+","+preferences.getString("date",""));
	}
}