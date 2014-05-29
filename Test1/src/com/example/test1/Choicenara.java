package com.example.test1;

import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

public class Choicenara extends Activity {
	public static String name;
	public String result;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		ImageView ch1;
		ImageView ch2;
		ImageView ch3;
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		name = getPackageName();
		Log.i(name, "ss");
		// finish();
		setContentView(R.layout.activity_cardchois);

		ch1 = (ImageView) findViewById(R.id.go_back);
		ch2 = (ImageView) findViewById(R.id.back_back);
		ch3 = (ImageView) findViewById(R.id.sin_back);

		ch1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//Toast.makeText(Choicenara.this, "go", 0).show();

				try {
					result = Util.DownloadText("http://hsbug.hamt.co.kr/api/set_kind?m_fb_id="+ LoginManager.id + "&kind="+ "GO");
					//Log.i(result,result);
					//Toast.makeText(Choicenara.this, result, 10).show();
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Game.myType = 1;
				Intent i = new Intent(Choicenara.this, SocketIOActivity.class);
				startActivity(i);
				
				
				//startActivityForResult(i, 1); 
				// setContentView(new GameView(Choicenara.this));

			}
		});

		ch2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					result = Util.DownloadText("http://hsbug.hamt.co.kr/api/set_kind?m_fb_id="+ LoginManager.id + "&kind="+ "BACK");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Toast.makeText(getApplicationContext(), "back", 0).show();
				//Toast.makeText(, "back", 0).show();
				Game.myType = 2;
				Intent i = new Intent(Choicenara.this, SocketIOActivity.class);
				startActivity(i);
				
				//startActivityForResult(i, 2);
			}
		});

		ch3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					result = Util.DownloadText("http://hsbug.hamt.co.kr/api/set_kind?m_fb_id="+ LoginManager.id + "&kind="+ "SIN");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Game.myType = 3;
				
				Intent i = new Intent(Choicenara.this, SocketIOActivity.class);
				startActivity(i);
				
				//startActivityForResult(i, 3);
			}
		});

	}

}
