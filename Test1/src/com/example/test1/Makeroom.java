package com.example.test1;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class Makeroom extends Activity {
	public ImageView user;
	public ImageView enemy;
	public URL userImg;
	public URL e_Img;
	public Bitmap bitmap;
	public Bitmap bitmap1;
	public Boolean connect;
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.waitrooms);
		user = (ImageView)findViewById(R.id.imageView1);
		enemy = (ImageView)findViewById(R.id.imageView2);
		connect=true;
		TextView user_id = (TextView)findViewById(R.id.Myid);
		TextView user_name = (TextView)findViewById(R.id.myname);
		TextView user_win = (TextView)findViewById(R.id.Mywin);
		TextView user_lose = (TextView)findViewById(R.id.Mylose);
		TextView enemy_id = (TextView)findViewById(R.id.e_id);
		TextView enemy_name = (TextView)findViewById(R.id.e_name);
		TextView enemy_win = (TextView)findViewById(R.id.EWin);
		TextView enemy_lose = (TextView)findViewById(R.id.Elose);
		
		
		try {
			userImg = new URL(SocketIOActivity.user1_url);
			//e_Img = new URL(SocketIOActivity.user2_url);
		
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try {
			bitmap = BitmapFactory.decodeStream(userImg.openStream());
			//bitmap1 = BitmapFactory.decodeStream(e_Img.openStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		user.setImageBitmap(bitmap);
		//enemy.setImageBitmap(bitmap1);
		
		
		
		  new Thread(new Runnable() {            

	            public void run() {        

	                

	                while (true) {

	                    if(SocketIOActivity.user2_id!=null){
	                    	Log.i("okok","okok");
	                    	try {
	        					
	        					e_Img = new URL(SocketIOActivity.user2_url);
	        					Log.i(userImg.toString(), e_Img.toString());
	        				} catch (MalformedURLException e) {
	        					// TODO Auto-generated catch block
	        					e.printStackTrace();
	        				}
	        				
	        				
	        				try {
	        					
	        					bitmap1 = BitmapFactory.decodeStream(e_Img.openStream());
	        				} catch (IOException e) {
	        					// TODO Auto-generated catch block
	        					e.printStackTrace();
	        				}
	        				
	        				enemy.setImageBitmap(bitmap1);
	        				
	        				
	        				

	                    }else{                    
	                    try {

	                        Thread.sleep(5000);                     
	                        	Log.i("ss", "ss");
	                        	onRestart();
	                        			
	                       

	                    } catch (InterruptedException ie) {

	                        ie.printStackTrace();

	                    	}
	                    }
	                }

	            }

	        }).start();        

	    }    


		
}
