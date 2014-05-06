package com.example.test1;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.model.GraphUser;

public class MainActivity extends Activity implements Session.StatusCallback {

	public Button button_Login;
	public Button button_Logout;
	public Button button_GameStart;
	public ImageView userImage;
	Handler mHandler = null;
	private String TAG = "MainActivity";
	LoginManager LM;
	public String result;
	public String msg[];

	// @Override

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
				.permitNetwork().build());
		setContentView(R.layout.activity_main);
		// setContentView(new Startview(this));
		mHandler = new Handler();

		LM = LoginManager.getInstance();
		LM.initialize(getApplicationContext(), this);
		// LM.login(this);

		button_Login = (Button) findViewById(R.id.loginbutton);
		button_GameStart = (Button) findViewById(R.id.gamestart);
		button_Logout = (Button) findViewById(R.id.logoutbutton);
		userImage = (ImageView) findViewById(R.id.userImage);
		View.OnClickListener Click = new OnClickListener() {

			@Override
			public void onClick(View v) {
				LoginManager LM = LoginManager.getInstance();
				// TODO Auto-generated method stub
				switch (v.getId()) {
				case R.id.loginbutton:
					LM.login(MainActivity.this);

					break;
				case R.id.gamestart:
					Toast.makeText(getApplicationContext(), "ok", 10).show();
					// Intent i = new
					// Intent(MainActivity.this,Choicenara.class);
					// overridePendingTransition(R.anim.anim_in,
					// R.anim.anim_out);
					// startActivity(i);
					//Toast.makeText(MainActivity.this, msg[0] + msg[1],	Toast.LENGTH_LONG).show();
					
					if (msg[0].equals("ERR")) {
						Toast.makeText(MainActivity.this, msg[1],
								Toast.LENGTH_LONG).show();
					} else if (msg[0].equals("SUC")) {
						if (msg[1].equals("FIRST")) {
							Intent i = new Intent(MainActivity.this,Choicenara.class);
							startActivity(i);
						} else if (msg[1].equals("EXIST")) {
							Intent i = new Intent(MainActivity.this,Game.class);
							startActivity(i);
						}
					}
					break;
					
				case R.id.logoutbutton:
					
					GameManager.Init();
					Intent i = new Intent(MainActivity.this,SocketIOActivity.class);
					startActivity(i);
					
					//LM.logout();/*
					Toast.makeText(getApplicationContext(), LM.getId(),
							Toast.LENGTH_SHORT).show();
					v.setVisibility(View.INVISIBLE);
					button_GameStart.setVisibility(View.INVISIBLE);
					button_Login.setVisibility(View.VISIBLE);
					userImage.setVisibility(View.INVISIBLE);

					break;
				default:
					break;
				}

			}
		};
		button_Login.setOnClickListener(Click);
		button_GameStart.setOnClickListener(Click);
		button_Logout.setOnClickListener(Click);

	}

	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		Session.getActiveSession().onActivityResult(this, requestCode,
				resultCode, data);
	}

	public void call(Session session, SessionState state, Exception exception) {

		// Log.i(TAG, state.toString());
		if (session.isOpened()) {
			Log.i("a", "a");
			Request.executeMeRequestAsync(session,
					new Request.GraphUserCallback() {
						public void onCompleted(GraphUser user,
								Response response) {
							if (user != null) {
								LoginManager.id = user.getId();
								LoginManager.email = user.getUsername();
								LoginManager.name = user.getName();

								// LoginManager.setId(user.getId());
								Log.i(user.getId(), user.getId());

								if (LM.getId() == null) {
									Toast.makeText(getApplicationContext(),
											"fail", 0).show();
								} else {
									Toast.makeText(getApplicationContext(),
											LM.getId(), 0).show();
								}
								// v.invalidate();
								button_GameStart.setVisibility(View.VISIBLE);
								button_Logout.setVisibility(View.VISIBLE);
								button_Login.setVisibility(View.INVISIBLE);
								userImage.setVisibility(View.VISIBLE);
								try {
									LoginManager.imageUrl= new URL(
											"https://graph.facebook.com/"
													+ LM.getId() + "/picture?");

									Bitmap bitmap = BitmapFactory
											.decodeStream(LoginManager.imageUrl.openStream());
									userImage.setImageBitmap(bitmap);

									// URL url = new
									// URL("http://hansung.hamt.co.kr/api/is_exist_member?"
									// + LoginManager.id + "&m_email=" +
									// LoginManager.email + "&m_name=" +
									// LoginManager.name);
									
									Log.d(TAG, "ID " + LoginManager.id	+ " // Name = " + LoginManager.name	+ " // Email = "+ LoginManager.email);
									result = Util
											.DownloadText("http://hansung.hamt.co.kr/api/is_exist_member?m_fb_id="
													+ LoginManager.id
													+ "&m_email="
													+ LoginManager.email
													+ "&m_name="
													+ LoginManager.name);
									result = result.trim();
									Toast.makeText(MainActivity.this, result, 0)
											.show();		
									Log.d(TAG, result);
									msg = result.split("iamspliter");
									Toast.makeText(MainActivity.this, msg[0] + msg[1],
											Toast.LENGTH_LONG).show();
									Log.d(TAG, msg[0] +"//"+ msg[1] +" length " +msg.length);
									
								} catch (MalformedURLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}

							}
						}
					});
		}
	}
}
