package com.example.test1;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Makeroom extends Activity {
	
	private String TAG = "Makeroom";
	
	public ImageView owner;
	public ImageView joiner;
	public URL ownerImg;
	public URL joinerImg;
	public Bitmap bitmap;
	public Bitmap bitmap1;
	public Boolean connect;
	public Button roomout;
	public Button ready;
	public Button cancle;
	public LinearLayout linearready;
	public LinearLayout linearcancle;
	public String result;
	public String msg1[];
	public String msg2[];
	
	public TextView UserName1, UserNation1, UserWin1, UserLose1;
	public TextView UserName2, UserNation2, UserWin2, UserLose2;
	
	SoundManager SM;
	
	public static Boolean isOwner=false;
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.waitrooms);
		
		owner = (ImageView)findViewById(R.id.UserImage1);
		joiner = (ImageView)findViewById(R.id.UserImage2);
		connect=true;
		
		GameManager.makerooms = this;
		SM = SoundManager.getInstance();
		
		roomout = (Button)findViewById(R.id.outbutton);
		ready = (Button)findViewById(R.id.readybutton);
		cancle = (Button)findViewById(R.id.canclebutton);
		linearready = (LinearLayout)findViewById(R.id.linearready);
		linearcancle = (LinearLayout)findViewById(R.id.linearcancle);
		
		UserName1 = (TextView)findViewById(R.id.UserName1);
		UserNation1 = (TextView)findViewById(R.id.UserNation1);
		UserWin1 = (TextView)findViewById(R.id.UserWin1);
		UserLose1 = (TextView)findViewById(R.id.UserLose1);
		
		UserName2 = (TextView)findViewById(R.id.UserName2);
		UserNation2 = (TextView)findViewById(R.id.UserNation2);
		UserWin2 = (TextView)findViewById(R.id.UserWin2);
		UserLose2 = (TextView)findViewById(R.id.UserLose2);
		
		try {
			result = Util
					.DownloadText("http://hsbug.hamt.co.kr/api/get_member_info?m_fb_id="
							+ GameManager.user1_id);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		result = result.trim();
		//Toast.makeText(MainActivity.this, result, 0).show();		
		Log.d(TAG, result);
		msg1 = result.split("iamspliter");
		Log.d(TAG, msg1[0] + " "  + msg1[1] + " " + msg1[2]);
		if(msg1[0].equals("SUC")){
			UserName1.setText(msg1[1]);
			UserWin1.setText(msg1[2] + "��");
			UserLose1.setText(msg1[3]+ "��");
			
			//UserNation1.setText();
		}
		try {
			result = Util
					.DownloadText("http://hsbug.hamt.co.kr/api/get_member_info?m_fb_id="
							+ GameManager.user2_id);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		result = result.trim();
		//Toast.makeText(MainActivity.this, result, 0).show();		
		Log.d(TAG, result);
		msg2 = result.split("iamspliter");
		
		if(msg2[0] == "SUC"){
			UserName2.setText(msg2[1]);
			UserWin2.setText(msg2[2] + "��");
			UserLose2.setText(msg2[3]+ "��");
			
			//UserNation2.setText();
		}
		
		roomout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SM.play("touch");
				GameManager.socket.emit("out_room", LoginManager.id);
				//GameManager.socket.disconnect();
				//GameManager.socket = null;
				Log.i("sss","disconnect");
				Intent i = new Intent(Makeroom.this,SocketIOActivity.class);
				startActivity(i);				
			}
			
		});
		
		
		ready.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				SM.play("touch");
				// TODO Auto-generated method stub
				GameManager.socket.emit("ready", LoginManager.id);
				roomout.setVisibility(View.INVISIBLE);
				ready.setVisibility(View.INVISIBLE);
				linearready.setVisibility(View.INVISIBLE);
				cancle.setVisibility(View.VISIBLE);
				linearcancle.setVisibility(View.VISIBLE);
			}
			
		});
		
		cancle.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				SM.play("touch");
				roomout.setVisibility(View.VISIBLE);
				ready.setVisibility(View.VISIBLE);
				linearready.setVisibility(View.VISIBLE);
				cancle.setVisibility(View.INVISIBLE);
				linearcancle.setVisibility(View.INVISIBLE);
			}
			
		});
	}
	
	public void gameStart(){
		if(GameManager.gameStarted.equals("gamestart")){
		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				SM.backgroundStopSound();
				SM.play("gamestart");
				Intent i = new Intent(Makeroom.this,Game.class);
				startActivity(i);
				
			}
		}, 5000);
	}
	}
	public void setUsersInfo() {/////////////////////////  event  :  connect Joiner
		System.out.println("called Makeroom.setUsersInfo()");
		try {
			ownerImg = new URL(GameManager.user1_url);
			joinerImg = new URL(GameManager.user2_url);
		
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Makeroom.setUsersInfo() - set URLs");
		
		try {
			bitmap = BitmapFactory.decodeStream(ownerImg.openStream());
			bitmap1 = BitmapFactory.decodeStream(joinerImg.openStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		owner.setImageBitmap(bitmap);
		joiner.setImageBitmap(bitmap1);
		UserName2.setText(GameManager.user2_name);
		
		try {
			result = Util
					.DownloadText("http://hsbug.hamt.co.kr/api/get_member_info?m_fb_id="
							+ GameManager.user2_id);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		result = result.trim();
		//Toast.makeText(MainActivity.this, result, 0).show();		
		Log.d(TAG, result);
		msg2 = result.split("iamspliter");
		
		if(msg2[0].equals("SUC")){
			UserName2.setText(msg2[1]);
			UserWin2.setText(msg2[2] + "��");
			UserLose2.setText(msg2[3]+ "��");
			
			//UserNation2.setText();
		}
		System.out.println("Makeroom.setUsersInfo() - End");
		
	}
	
	
	public void setEnemyOutInfo(){///////////////////////// event :  JoinerOut;
		
		joiner.setImageResource(R.drawable.ic_launcher);
		bitmap1.recycle();
		joinerImg=null;
		
		
		System.out.println("Outroom.setEnemyInfo() - End");
	}
	public Boolean getIsOwner() {
		return isOwner;
	}
	public void setIsOwner(Boolean isOwner){
		Makeroom.isOwner= isOwner;

		try {
			bitmap = BitmapFactory.decodeStream(LoginManager.imageUrl.openStream());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		owner.setImageBitmap(bitmap);
		
	}
	public void changeUserInfo(){/////////////////////////  event :  ownerOut
		System.out.println("called Insertroom.changeUsersInfo()");

		
		joiner.setImageResource(R.drawable.ic_launcher);
		System.out.println("Insertroom.changeUsersInfo() - End");
		setIsOwner(true);
	}

		
}
