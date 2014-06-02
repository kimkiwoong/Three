package com.example.test1;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class loadingActivity extends Activity implements Runnable {
	public static ProgressBar progressBar1;
	public static TextView textview2;
	public static TextView textview1;
	
	public ImageView title_t,title_s;	
	public ImageView title;
	SoundManager SM;
	public static Boolean soundgo;
	// progress값을 저장해 두는 코드입니다
	// 아래에서 progress값을 증가시킨다음, 지정하는 코드가 존재합니다
	int progress=0;
	Thread thread;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loading);
		
		textview1 = (TextView) findViewById(R.id.textView1);
		textview2 = (TextView)findViewById(R.id.textView2);
		
		title = (ImageView)findViewById(R.id.imageView1);
		
		title_t = (ImageView)findViewById(R.id.title2);
		title_s = (ImageView)findViewById(R.id.title3);
		
		progressBar1 = (ProgressBar) findViewById(R.id.progressBar1);
		SM = SoundManager.getInstance();
		SM.Init(this);
		soundgo=false;
		Start();
		
		AnimationSet animSet = new AnimationSet(true);
		Animation title_move = AnimationUtils.loadAnimation(this, R.anim.title_ani);
		animSet.addAnimation(title_move);
		
		title_t.setAnimation(animSet);
		title_s.setAnimation(animSet);
		animSet.start();
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	public void recycle(){
		Log.d("recycle","recycle" );
			recycleBitmap(title);
			recycleBitmap(title_s);
			recycleBitmap(title_t);
			
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		//this.recycle();
		this.recycle();
		super.onDestroy();
	}

	private static void recycleBitmap(ImageView iv) {        
			Drawable d = iv.getDrawable();      
			if (d instanceof BitmapDrawable) {           
				Bitmap b = ((BitmapDrawable)d).getBitmap();       
				b.recycle();        
				} 
		d.setCallback(null);   
		}
	private static void recycleBitmap1(Button iv) {        
		Drawable d = iv.getBackground();      
		if (d instanceof BitmapDrawable) {           
			Bitmap b = ((BitmapDrawable)d).getBitmap();       
			b.recycle();        
			} 
	d.setCallback(null);   
	}
	/**
	 * 버튼을 누르면 thread를 시작합니다 
	 */
	public void Start(){
		thread = new Thread(this);
		thread.start();
	}
	
	/**
	 * 쓰래드가 시작되면 호출되는 run메소드입니다
	 */
	@Override
	public void run(){
		
		// progress의 값을 0으로 지정합니다
		
		// progress이 100이상되면 작동을 멈추도록 코드를 작성했습니다
		// android:max값이 100이기 때문이죠 ㅎㅎ
		while(SoundManager.progressnum<100){
			// 값을 하나씩 늘립니다
			if(soundgo==false){
				
				SM.Initsound();
				soundgo=true;
			}
			
			// 프로그래스 진행도를 설정합니다
			
		
			// thread.sleep문을 작동하기 위해서는 try문으로 감싸줘야 합니다
			// 이 통채가 한 세트라고 이해해 주세요 ㅎㅎ
			try {
				
				thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// try문 끝
		}
		Intent i = new Intent(loadingActivity.this,MainActivity.class);
		startActivity(i);
		onDestroy();
		
	}

}
