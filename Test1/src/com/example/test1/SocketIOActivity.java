package com.example.test1;




import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;

import android.widget.Button;


public class SocketIOActivity extends Activity {
	
	
	public Button make, in,btnType;
	

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		//Log.i("sss", "sss");
		super.onCreate(savedInstanceState);
		GameManager.Init();
		
		setContentView(R.layout.roomtext);
		//Log.i("sss", "sss2");
		GameManager.activitySocket = this;
		make = (Button) findViewById(R.id.makeroom);
		in = (Button) findViewById(R.id.inroom);
		
		make.setOnClickListener(GameManager.Click);
		in.setOnClickListener(GameManager.Click);
		
	}
	
	public void openMakeroom(){
		Intent i = new Intent(
				SocketIOActivity.this,
				Makeroom.class);
		startActivity(i);
	}
	

}