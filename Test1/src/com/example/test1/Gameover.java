package com.example.test1;

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

public class Gameover extends Activity {

	private String TAG = "Gameover";
	//Game Games;
	SoundManager SM;
	ImageView win_image;
	ImageView lose_image;
	Button game_button;
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
		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				GameManager.socket.disconnect();
			}
		}, 2000);
		
		if(Game.UserTotalCount == 0 && Game.EnemyTotalCount == 0){
			//���� ���� ��
			if(GameManager.myTurn == 0){
				Log.d(TAG, "Gameover 1 ");
				Log.d(TAG, "Game.UserTotalCount " + Game.UserTotalCount + "Game.EnemyTotalCount " + Game.EnemyTotalCount);
				win_image.setVisibility(View.VISIBLE);
				lose_image.setVisibility(View.INVISIBLE);
			}
			//���� ��
			else if(GameManager.myTurn == 1){
				Log.d(TAG, "Gameover 2 ");
				Log.d(TAG, "Game.UserTotalCount " + Game.UserTotalCount + " Game.EnemyTotalCount " + Game.EnemyTotalCount);
				win_image.setVisibility(View.INVISIBLE);
				lose_image.setVisibility(View.VISIBLE);
			}
		}
			
		//���� �������
		if(Game.UserTotalCount == 0 && Game.EnemyTotalCount != 0 ){
			Log.d(TAG, "Gameover 3 ");
			Log.d(TAG, "myTurn" + GameManager.myTurn);
			Log.d(TAG, " Game.UserTotalCount " + Game.UserTotalCount + "Game.EnemyTotalCount " + Game.EnemyTotalCount);
			win_image.setVisibility(View.INVISIBLE);
			lose_image.setVisibility(View.VISIBLE);
		}
		
		//���� �̰��� ���
		else if(Game.EnemyTotalCount == 0 && Game.UserTotalCount != 0){
			Log.d(TAG, "Gameover 4 ");
			Log.d(TAG, "myTurn" + GameManager.myTurn);
			Log.d(TAG, " Game.UserTotalCount " + Game.UserTotalCount + "Game.EnemyTotalCount " + Game.EnemyTotalCount);
			win_image.setVisibility(View.VISIBLE);
			lose_image.setVisibility(View.INVISIBLE);
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
