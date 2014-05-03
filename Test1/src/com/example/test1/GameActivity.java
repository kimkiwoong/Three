package com.example.test1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.facebook.Session;


public class GameActivity extends Activity {
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(new GameView1(this));
		
	
			
	}

}


