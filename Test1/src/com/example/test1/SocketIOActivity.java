package com.example.test1;

import java.net.MalformedURLException;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.SSLContext;

import io.socket.IOAcknowledge;
import io.socket.IOCallback;
import io.socket.SocketIO;
import io.socket.SocketIOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;

public class SocketIOActivity extends Activity {
	static public int socketCnt=0;
	private SocketIO socket;
	
	LoginManager LM;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      
        setContentView(R.layout.roomtext);
           	
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
			String js = "{\"user\":[ss,https://graph.facebook.com/ss/picture?,1]}";
			JSONObject jobj = new JSONObject();
			jobj.put("userID","ss");
			jobj.put("userName", "ss");
			jobj.put("userImgUrl", "https://graph.facebook.com/ss/picture?");
			jobj.put("userWinCount", 0);
			jobj.put("userLostCount",0);
			
			
			//JSONArray jarr = jobj.getJSONArray("user");
			
			socket.emit("create_room", jobj);
			socket.emit("insert_room", jobj);
			
			//socket.emit("create_room", jarr);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    	
    }
    

}