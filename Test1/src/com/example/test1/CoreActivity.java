package com.example.test1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.test1.GameManager;
import com.example.test1.MainActivity;
import com.example.test1.R;
import com.example.test1.SocketIOActivity;

public class CoreActivity extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_core);
	    
	    Button m_GameStart = (Button)findViewById(R.id.button1);
	    m_GameStart.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GameManager.Init();
				Intent i = new Intent(CoreActivity.this,SocketIOActivity.class);
				startActivity(i);				
			}
		});
	}

}
