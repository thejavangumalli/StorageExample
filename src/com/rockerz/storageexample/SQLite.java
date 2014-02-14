package com.rockerz.storageexample;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SQLite extends Activity {
	EditText et;
	static boolean flag=false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sqlite);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sqlite, menu);
		return true;
	}
	public void save(View view){
	//List<String> test=new ArrayList<String>();
	et= (EditText) findViewById(R.id.blogmessage);
	Sqlitedb sq=new Sqlitedb(this);
    sq.addMessage(et.getText().toString());
    //test=sq.getMessages();
    flag=true;
    Toast.makeText(getApplicationContext(),"Saved to DB", Toast.LENGTH_LONG).show();
    finish();
	}
	public void cancel(View view){
		flag=false;
		finish();
	}
	public static boolean test(){
		return flag;
	}
}
