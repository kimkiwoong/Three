package com.example.test1;

import java.net.MalformedURLException;

import org.json.JSONException;
import org.json.JSONObject;

import io.socket.IOAcknowledge;
import io.socket.IOCallback;
import io.socket.SocketIO;
import io.socket.SocketIOException;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;





public class GameManager {
	static public int socketCnt = 0;
	public static SocketIO socket;
	LoginManager LM;
	public static JSONObject user;
	public static JSONObject user1;
	public static JSONObject user2;
	public static String turns;
	public static String attackdata;
	public static int att_i;
	public static int hp_i;
	public static String m_cardID;
	public static int mybutton;
	public static int youbutton;
	public static String gameStarted;
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
	public static SocketIOActivity activitySocket;
	public static Makeroom makerooms;
	public static Game Games;
	public static View.OnClickListener Click;
	

	public static Handler handler;
	private static Boolean flagTimer;
	private static Boolean isInit = false;
	
	
	
	
	
	
	private static GameManager Game_instance;
	public static final int Userturn=1; 
	public static final int Enemyturn=2;
	
	public static int turn;
	
	public static boolean GameEnd = true;
	 public static GameManager getInstance() {

	        if (Game_instance == null) {
	        	Game_instance = new GameManager();
	        }

	        return Game_instance;
	    }
	
		public void setTurn(int turn) {
			
			
			GameManager.turn=turn;
			
		}
		public void Gameover(){
			this.GameEnd=false;
		}
		public int GetTurn(){
			return GameManager.turn;
		}
		
		public static void Init() {
			if(isInit == true) {
				return;
			}
			isInit = true;
			init();
		}
		
		private static void init(){
			Log.i("sss","init");
			try {
				socket = new SocketIO("http://113.198.87.193:3000");
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
						//Log.i("sss", temp);
						if(event.equals("readyed")){
							try {
								JSONObject jbj = new JSONObject(temp);
								String owner_id = jbj.getString("ownerID");
								String joiner_id = jbj.getString("joinerID");
								
								gameStarted = jbj.getString("Data");
								if(owner_id.equals(LoginManager.id)== false && joiner_id.equals(LoginManager.id)== false) {
									return;
								}
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							handler.post(new Runnable() {
								
								@Override
								public void run() {
									// TODO Auto-generated method stub
									makerooms.gameStart();
								}
							});
							return;
						}
						////////////game start owner turn
						if(event.equals("start_data")){
							try {
								JSONObject jbj = new JSONObject(temp);
								String user_id = jbj.getString("userID");
								turns = jbj.getString("Data");
								if(user_id.equals(LoginManager.id)== false) {
									return;
								}
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							handler.post(new Runnable() {
								
								@Override
								public void run() {
									// TODO Auto-generated method stub
									Games.turntoast(turns);
								}
							});
							return;
							
						}
						////////////turnchanged/////////////
						if(event.equals("turnchanged")){
							try {
								JSONObject jbj = new JSONObject(temp);
								String user_id = jbj.getString("userID");
								turns = jbj.getString("Data");
								if(user_id.equals(LoginManager.id)== false) {
									return;
								}
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							handler.post(new Runnable() {
								
								@Override
								public void run() {
									// TODO Auto-generated method stub
									Games.turntoast(turns);
								}
							});
							return;
							
						}
						///////////////////////////attack///////
						if(event.equals("attacked")){
							try {
								JSONObject jbj = new JSONObject(temp);
								
								String joiner_id = jbj.getString("joiner");
								String owner_id = jbj.getString("owner");
								
								
								
								if(joiner_id.equals(LoginManager.id)==false && owner_id.equals(LoginManager.id)==false) {
									return;
								}
								attackdata = jbj.getString("data");
								JSONObject jj = new JSONObject(attackdata);
								att_i = jj.getInt("att");
								hp_i = jj.getInt("hp");
								mybutton = jj.getInt("myButtonID");
								youbutton = jj.getInt("yourButtonID");
								m_cardID = jj.getString("cardID");
								Log.i("sssss", "sssss");
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							handler.post(new Runnable() {
								
								@Override
								public void run() {
									// TODO Auto-generated method stub
									Log.i("sssss", "sssss");
									Games.attack();
								}
							});
							return;
						}
						/////////////////////event : out_roomed///////////
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

							handler.post(new Runnable() {

								@Override
								public void run() {
									// TODO Auto-generated method stub
									if (makerooms.getIsOwner()) {
										System.out
												.println("call outroom.setEnemyinfo()");
									makerooms
												.setEnemyOutInfo();
									} else {
										System.out
												.println("call outroom.setenemyinfo()");
										makerooms
												.changeUserInfo();
									}
								}

							});
							return;

						}
						if (event.equals("created_room")) {
							handler.postDelayed(new Runnable() {
								public void run() {
									makerooms.setIsOwner(true);
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
								handler.post(new Runnable() {
									
									@Override
									public void run() {
										// TODO Auto-generated method stub
										makerooms
										.setUsersInfo();
									}
								});
							
								return;
							}
							Log.i("sss","Line3");
							flagTimer = false;
							handler.post(new Runnable() {
								

								@Override
								public void run() {
									Log.i("sss","Line4");
									// TODO Auto-generated method stub
									activitySocket.openMakeroom();

									handler.postDelayed(
											new Runnable() {

												@Override
												public void run() {
													// TODO Auto-generated
													// method stub
													Log.i("sss","Line5");
													makerooms
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
			} catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			handler = new Handler();
			Click = new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub

					

					Button btnType = (Button) v;
					

					// This line is cached until the connection is establisched.
					// socket.send("Hello Server!");

					try {
						if(btnType.getId()==R.id.makeroom){
							Makeroom.isOwner=true;
							activitySocket.openMakeroom();
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
							handler.postDelayed(new Runnable() {

								@Override
								public void run() {
									// TODO Auto-generated method stub
									if (flagTimer == true) {
										Toast.makeText(activitySocket, "no room",
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



		}
		

}