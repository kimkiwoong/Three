package com.example.test1;




import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;

import android.widget.Button;


public class SocketIOActivity extends Activity {
	
	
	public Button make, in,btnType;
	

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		Log.i("sss", "sss");
		super.onCreate(savedInstanceState);

		setContentView(R.layout.roomtext);
		Log.i("sss", "sss2");
		GameManager.activitySocket = this;
		make = (Button) findViewById(R.id.makeroom);
		in = (Button) findViewById(R.id.inroom);
		
		make.setOnClickListener(GameManager.Click);
		in.setOnClickListener(GameManager.Click);
		
		//init();
		
		
		
		

	}
	
	public void openMakeroom(){
		Intent i = new Intent(
				SocketIOActivity.this,
				Makeroom.class);
		startActivity(i);
	}
	/*
	private  void init(){
		Log.i("sss","init");
		try {
			socket = new SocketIO("http://113.198.87.193:3000");
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		SocketIOActivity.handler = new Handler();
		View.OnClickListener Click = new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				

				 btnType = (Button) v;
				socket.connect(new IOCallback() {
					@Override
					public void onMessage(JSONObject json, IOAcknowledge ack) {
						try {
							System.out.println("Server said:"
									+ json.toString(2));
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
							if (socketCnt < 10) {
								socket = new SocketIO(
										"http://113.198.87.193:3000");
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
					public void on(String event, IOAcknowledge ack,
							Object... args) {
						System.out.println("[make] Server triggered event '"
								+ event + "'");

						Object[] arguments = args;
						String temp = arguments[0].toString();
						Log.i("sss", temp);
						if (event.equals("out_roomed")) {

							try {
								JSONObject jobj = new JSONObject(temp);
								String user_id = jobj.getString("userID");
								if (user_id.equals(LoginManager.id) == false) {
									return;
								}
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								return;
							}

							SocketIOActivity.handler.post(new Runnable() {

								@Override
								public void run() {
									// TODO Auto-generated method stub
									if (SocketIOActivity.makerooms.getIsOwner()) {
										System.out
												.println("call outroom.setEnemyinfo()");
										SocketIOActivity.makerooms
												.setEnemyOutInfo();
									} else {
										System.out
												.println("call outroom.setenemyinfo()");
										SocketIOActivity.makerooms
												.changeUserInfo();
									}
								}

							});
							return;

						}
						if (event.equals("created_room")) {
							SocketIOActivity.handler.postDelayed(new Runnable() {
								public void run() {
									SocketIOActivity.makerooms.setIsOwner(true);
								}
							},100);
							return;
						}

						// event : inserted_room

						// /event inserted_room
						Log.i("sss","Line99");
						if (Makeroom.isOwner ==false && flagTimer == false){
							Log.i("sss","flag Timer = false");
							return;
						}
						Log.i("sss","Line100");

						try {
							Log.i("sss","Line101");
							user = new JSONObject(temp);
							user1 = user.getJSONObject("user1");
							user2 = user.getJSONObject("user2");
							user1_id = user1.getString("userID");
							user2_id = user2.getString("userID");
							Log.i("sss","Line102");
							//if(user1_id.equ)
							if(user1_id.equals(LoginManager.id)==false && user2_id.equals(LoginManager.id)==false){
								Log.i("sss", "not member!");
								return;
							}
							user1_url = user1.getString("userImgUrl");
							user1_name = user1.getString("userName");
							user1_wincount = user1.getString("userWinCount");
							user1_losecount = user1.getString("userLostCount");
							
							user2_url = user2.getString("userImgUrl");
							user2_name = user2.getString("userName");
							user2_wincount = user2.getString("userWinCount");
							user2_losecount = user2.getString("userLostCount");
							Log.i("sss","Line1");
							if (Makeroom.isOwner){
								Log.i("sss","Line2");
								SocketIOActivity.handler.post(new Runnable() {
									
									@Override
									public void run() {
										// TODO Auto-generated method stub
										SocketIOActivity.makerooms
										.setUsersInfo();
									}
								});
							
								return;
							}
							Log.i("sss","Line3");
							flagTimer = false;
							SocketIOActivity.handler.post(new Runnable() {
								

								@Override
								public void run() {
									Log.i("sss","Line4");
									// TODO Auto-generated method stub
									Intent i = new Intent(
											SocketIOActivity.this,
											Makeroom.class);
									startActivity(i);

									SocketIOActivity.handler.postDelayed(
											new Runnable() {

												@Override
												public void run() {
													// TODO Auto-generated
													// method stub
													Log.i("sss","Line5");
													SocketIOActivity.makerooms
															.setUsersInfo();
												}
											}, 100);

								}
							});

						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});

				// This line is cached until the connection is establisched.
				// socket.send("Hello Server!");

				try {
					if(btnType.getId()==R.id.makeroom){
						Makeroom.isOwner=true;
						Intent i = new Intent(SocketIOActivity.this, Makeroom.class);
	
						startActivity(i);
						JSONObject jobj = new JSONObject();
	
						jobj.put("userID", LoginManager.id);
						jobj.put("userName", LoginManager.name);
						jobj.put("userImgUrl", "https://graph.facebook.com/"
								+ LoginManager.id + "/picture?");
						jobj.put("userWinCount", 0);
						jobj.put("userLostCount", 0);
	
						socket.emit("create_room", jobj);
						user1_id = LoginManager.id;
						user1_url = "https://graph.facebook.com/" + LoginManager.id
								+ "/picture?";
						user1_name = LoginManager.name;
						
					}
					if(btnType.getId()==R.id.inroom){
						JSONObject jobj = new JSONObject();
						try {
							Makeroom.isOwner=false;
							jobj.put("userID", LoginManager.id);
							jobj.put("userName", LoginManager.name);
							jobj.put("userImgUrl", "https://graph.facebook.com/"
									+ LoginManager.id + "/picture?");
							jobj.put("userWinCount", 0);
							jobj.put("userLostCount", 0);
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						flagTimer = true;

						socket.emit("insert_room", jobj);
						SocketIOActivity.handler.postDelayed(new Runnable() {

							@Override
							public void run() {
								// TODO Auto-generated method stub
								if (SocketIOActivity.flagTimer == true) {
									Toast.makeText(getApplicationContext(), "no room",
											Toast.LENGTH_SHORT).show();
									flagTimer = false;
								}
							}
						}, 2000);
						System.out.println(" " + user1_id + " " + user1_name + " "
								+ user1_url + " " + " " + user1_wincount + " "
								+ user1_losecount);
					}
					// socket.emit("create_room", jarr);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};

		make.setOnClickListener(Click);		
		in.setOnClickListener(Click);

	}
	*/

}