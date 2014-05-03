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
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		name = getPackageName();
		Log.i(name, "ss");
		// finish();
		setContentView(R.layout.activity_cardchois);

		ImageView ch1 = (ImageView) findViewById(R.id.imageView4);
		ImageView ch2 = (ImageView) findViewById(R.id.imageView5);
		ImageView ch3 = (ImageView) findViewById(R.id.imageView6);

		ch1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(Choicenara.this, "go", 0).show();

				try {
					result = Util.DownloadText("http://hansung.hamt.co.kr/api/set_kind?m_fb_id="+ LoginManager.id + "&kind="+ "GO");
					Log.i(result,result);
					Toast.makeText(Choicenara.this, result, 10).show();
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Intent i = new Intent(Choicenara.this, GameActivity.class);
				//startActivity(i);
				
				startActivityForResult(i, 1); 
				// setContentView(new GameView(Choicenara.this));

			}
		});

		ch2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					result = Util.DownloadText("http://hansung.hamt.co.kr/api/set_kind?m_fb_id="+ LoginManager.id + "&kind="+ "BACK");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Toast.makeText(Choicenara.this, "back", 0).show();
			}
		});

		ch3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					result = Util.DownloadText("http://hansung.hamt.co.kr/api/set_kind?m_fb_id="+ LoginManager.id + "&kind="+ "SIN");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Toast.makeText(Choicenara.this, "sin", 0).show();
			}
		});

	}

}
