package com.rockerz.storageexample;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SharedMemory extends Activity {
EditText book,author,description;
static int i=0;
static boolean flag=false; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		System.out.println("life");
		setContentView(R.layout.activity_shared_memory);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.shared_memory, menu);
		return true;
	}
   public void cancel(View view){
	   flag=false;
		finish();
   }
	public void save(View view){
		flag=true;
		i++;
		Sqlitedb sq=new Sqlitedb(this);
		book=(EditText) findViewById(R.id.bookname);
		author=(EditText) findViewById(R.id.authorname);
		description=(EditText) findViewById(R.id.description1);
		savePreferences("bookName",book.getText().toString());
		savePreferences("authorName",author.getText().toString());
		savePreferences("description",description.getText().toString());
		savePreferences("date",sq.getDateTime());		
	    Toast.makeText(getApplicationContext(),"Preference Saved", Toast.LENGTH_LONG).show();

		finish();
		}
	private void savePreferences(String key, String value) {
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
		Editor editor = sharedPreferences.edit();
		editor.putString(key, value);
		editor.commit();
	}
	public static boolean test() {
		return flag;
	}		
}