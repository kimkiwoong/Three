package com.example.test1;

import java.io.IOException;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Gameover extends Activity {

	private String TAG = "Gameover";
	//Game Games;
	SoundManager SM;
	ImageView win_image;
	ImageView lose_image;
	Button game_button;
	public String result;
	public String msg[];
	public TextView UserName, UserNation, UserWinLose;
	//Button finish_button;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gameover);
		Log.d(TAG, "Gameover onCreate ");
		SM = SoundManager.getInstance();
		SM.gamebackgroundStopSound();
		SM.backgroundPlaySound();
		
		win_image = (ImageView)findViewById(R.id.winimage);
		lose_image = (ImageView)findViewById(R.id.loseimage);
		
		UserName = (TextView)findViewById(R.id.UserName);
		//UserNation = (TextView)findViewById(R.id.UserNation);
		UserWinLose = (TextView)findViewById(R.id.UserWinLose);
		
		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				GameManager.socket.disconnect();
			}
		}, 1000);
		
		if(Game.UserTotalCount == 0 && Game.EnemyTotalCount == 0){
			//내가 턴일 때
			if(GameManager.myTurn == 0){
				Log.d(TAG, "Gameover 1 ");
				Log.d(TAG, "Game.UserTotalCount " + Game.UserTotalCount + "Game.EnemyTotalCount " + Game.EnemyTotalCount);
				win_image.setVisibility(View.VISIBLE);
				lose_image.setVisibility(View.INVISIBLE);
			}
			//상대방 턴
			else if(GameManager.myTurn == 1){
				Log.d(TAG, "Gameover 2 ");
				Log.d(TAG, "Game.UserTotalCount " + Game.UserTotalCount + " Game.EnemyTotalCount " + Game.EnemyTotalCount);
				win_image.setVisibility(View.INVISIBLE);
				lose_image.setVisibility(View.VISIBLE);
			}
		}
			
		//내가 졌을경우
		if(Game.UserTotalCount == 0 && Game.EnemyTotalCount != 0 ){
			Log.d(TAG, "Gameover 3 ");
			Log.d(TAG, "myTurn" + GameManager.myTurn);
			Log.d(TAG, " Game.UserTotalCount " + Game.UserTotalCount + "Game.EnemyTotalCount " + Game.EnemyTotalCount);
			win_image.setVisibility(View.INVISIBLE);
			lose_image.setVisibility(View.VISIBLE);
		}
		
		//내가 이겼을 경우
		else if(Game.EnemyTotalCount == 0 && Game.UserTotalCount != 0){
			Log.d(TAG, "Gameover 4 ");
			Log.d(TAG, "myTurn" + GameManager.myTurn);
			Log.d(TAG, " Game.UserTotalCount " + Game.UserTotalCount + "Game.EnemyTotalCount " + Game.EnemyTotalCount);
			win_image.setVisibility(View.VISIBLE);
			lose_image.setVisibility(View.INVISIBLE);
		}		
		
		try {
			result = Util
					.DownloadText("http://hsbug.hamt.co.kr/api/get_member_info?m_fb_id="
							+ LoginManager.id);
		} catch (IOException e) {  
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		result = result.trim();
		//Toast.makeText(MainActivity.this, result, 0).show();		
		Log.d(TAG, result);
		msg = result.split("iamspliter");
		Log.d(TAG, msg[0] + " "  + msg[1] + " " + msg[2]);
				
		if(msg[0].equals("SUC")){
			UserName.setText(msg[1]);
			UserWinLose.setText(msg[2] + "승  " + msg[3]+ "패");		
		}
		
				
		game_button = (Button)findViewById(R.id.game);
		game_button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GameManager.isInit=false;
				Intent intent = new Intent(Gameover.this,SocketIOActivity.class);
				startActivity(intent);
				
			}
		});
		
		/*
		finish_button = (Button)findViewById(R.id.end);
		finish_button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				System.exit(0);
				ActivityManager exit = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
				exit.restartPackage(getPackageName());				
			}
		});
		*/
		
		
		
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	   switch (keyCode) {
	   case KeyEvent.KEYCODE_BACK:
	      return true;
	   }
	   return super.onKeyDown(keyCode, event);
	}

}
