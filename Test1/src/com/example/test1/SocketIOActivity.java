package com.example.test1;

import io.socket.IOAcknowledge;
import io.socket.IOCallback;
import io.socket.SocketIO;
import io.socket.SocketIOException;

import java.net.MalformedURLException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SocketIOActivity extends Activity {
	static public int socketCnt=0;
	private SocketIO socket;
	public Button make,in;
	LoginManager LM;
	public static JSONObject user;
	public static JSONObject user1;
	public static JSONObject user2;
	public static String user1_id;
	public static String user1_url;
	public static String user1_name;
	public static String user1_wincount;
	public static String user1_losecount;
	public static String user2_id;
	public static String user2_url;
	public static String user2_name;
	public static String user2_wincount;
	public static String user2_losecount;
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      
        setContentView(R.layout.roomtext);
      
        Log.i(LoginManager.name,LoginManager.name);
        
        make = (Button)findViewById(R.id.makeroom);
        in = (Button)findViewById(R.id.inroom);
        
        make.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					socket = new SocketIO("http://113.198.87.193:3000");
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				socket.connect(new IOCallback() {
					@Override
					public void onMessage(JSONObject json, IOAcknowledge ack) {
						try {
							System.out.println("Server said:" + json.toString(2));
						} catch (JSONException e) {
							e.printStackTrace();
						}
					}

					@Override
					public void onMessage(String data, IOAcknowledge ack) {
						System.out.println("Server said: " + data);
					}

					@Override
					public void onError(SocketIOException socketIOException) {
						System.out.println("an Error occured");
						  try {
							  if(socketCnt<10){
							  socket = new SocketIO("http://113.198.87.193:3000");
							  socket.connect(this);
							  socketCnt++;
							  }
							 } catch (MalformedURLException e) {
							  // TODO Auto-generated catch block
							  e.printStackTrace();
							 }
							 socketIOException.printStackTrace();
							 

					}

					@Override
					public void onDisconnect() {
						System.out.println("Connection terminated.");
					}

					@Override
					public void onConnect() {
						System.out.println("Connection established");
					}

					@Override
					public void on(String event, IOAcknowledge ack, Object... args) {
						System.out.println("Server triggered event '" + event + "'");
					}
				});
				
				// This line is cached until the connection is establisched.
				//socket.send("Hello Server!");
				
				try {
					
					JSONObject jobj = new JSONObject();
					
					jobj.put("userID",LoginManager.id);
					jobj.put("userName", LoginManager.name);
					jobj.put("userImgUrl", "https://graph.facebook.com/"+LoginManager.id+"/picture?");
					jobj.put("userWinCount", 0);
					jobj.put("userLostCount",0);
						
					socket.emit("create_room", jobj);
					user1_id = LoginManager.id;
					 user1_url =  "https://graph.facebook.com/"+LoginManager.id+"/picture?";
					 user1_name = LoginManager.name;
					 Intent i = new Intent(SocketIOActivity.this,Makeroom.class);
						startActivity(i);
					
					
					//socket.emit("create_room", jarr);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
           	
        in.setOnClickListener(new OnClickListener() {
			
        	@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					socket = new SocketIO("http://113.198.87.193:3000");
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				socket.connect(new IOCallback() {
					@Override
					public void onMessage(JSONObject json, IOAcknowledge ack) {
						try {
							System.out.println("Server said:" + json.toString(2));
						} catch (JSONException e) {
							e.printStackTrace();
						}
					}

					@Override
					public void onMessage(String data, IOAcknowledge ack) {
						System.out.println("kkkkkkkkkkkkkkkkkkk: " + data);
					}

					@Override
					public void onError(SocketIOException socketIOException) {
						System.out.println("an Error occured");
						  try {
							  if(socketCnt<10){
							  socket = new SocketIO("http://113.198.87.193:3000");
							  socket.connect(this);
							  socketCnt++;
							  }
							 } catch (MalformedURLException e) {
							  // TODO Auto-generated catch block
							  e.printStackTrace();
							 }
							 socketIOException.printStackTrace();
							 

					}

					@Override
					public void onDisconnect() {
						System.out.println("Connection terminated.");
					}

					@Override
					public void onConnect() {
						System.out.println("Connection established");
					}

					@Override
					public void on(String event, IOAcknowledge ack, Object... args) {
						System.out.println("Server triggered event '" + event + "'");
						
						Object[] arguments = args;
						String temp = arguments[0].toString();
						Log.i("sss", temp);
						
							
							try {
								 user = new JSONObject(temp);
								 user1 = user.getJSONObject("user1");
								 user2  = user.getJSONObject("user2");
								 user1_id = user1.getString("userID");
								 user1_url = user1.getString("userImgUrl");
								 user1_name = user1.getString("userName");
								 user1_wincount = user1.getString("userWinCount");
								 user1_losecount = user1.getString("userLostCount");
								 user2_id = user2.getString("userID");
								 user2_url = user2.getString("userImgUrl");
								 user2_name = user2.getString("userName");
								 user2_wincount = user2.getString("userWinCount");
								 user2_losecount = user2.getString("userLostCount");
								 
								
								
								
								
								
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
				
			           

					}
				});
				
				
				
				JSONObject jobj = new JSONObject();
				try {
					
					jobj.put("userID",LoginManager.id);
					jobj.put("userName", LoginManager.name);
					jobj.put("userImgUrl", "https://graph.facebook.com/"+LoginManager.id+"/picture?");
					jobj.put("userWinCount", 0);
					jobj.put("userLostCount",0);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				 socket.emit("insert_room", jobj);
				 System.out.println(" "+user1_id+" "+user1_name+" "+user1_url+" "+" "+user1_wincount + " "+user1_losecount);
				 Intent i = new Intent(SocketIOActivity.this,GameActivity.class);
					startActivity(i);
			}
		});
		

    	
    }
    

}