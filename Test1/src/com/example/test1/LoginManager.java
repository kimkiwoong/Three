package com.example.test1;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.facebook.HttpMethod;
import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.model.GraphUser;

public class LoginManager {
	private static final String APP_ID = "429632717172222";
    private static final String ACCESS_TOKEN = "hidden";
    private static LoginManager m_instance;
    public static String id;
    public static String email;
    public static String name;
    public static final String TAG = "FACEBOOK";
    public static URL imageUrl;
    public Activity activity;
    private static Session.StatusCallback statusCallback;
    protected LoginManager() {
    }

    public static void initialize(Context context, Session.StatusCallback callback_object) {
    	statusCallback = callback_object;
        Session session = Session.getActiveSession();
        if (session == null) {
            session = new Session.Builder(context)
                .setApplicationId(APP_ID)
                .build();
            Log.i("ses","ses");
         }
        Session.setActiveSession(session);
       
    }

    public static LoginManager getInstance() {

        if (m_instance == null) {
            m_instance = new LoginManager();
        }

        return m_instance;
    }
    
    public Session getSession() {
        return Session.getActiveSession();
    }
/*
    public void tryOpenSession(Activity activity) {
        Session session = Session.openActiveSession(activity, false, statusCallback);
        Session.setActiveSession(session);
       
    }
    */

    public void login(Activity activity) {
        Session session = Session.getActiveSession();
        Session.openActiveSession(activity, true,statusCallback);
        
       				
    }

    public void logout() {
        Session session = Session.getActiveSession();

        if (!session.isClosed()) {
            session.closeAndClearTokenInformation();
            this.setId(null);
            
        }
         
    }
    public String getId()  {
    	return this.id;
    }
    public static void setId(String ids){
        id=ids;
    }

    public String getName()  {
    	return this.getName();
    }
    
    public String getEmail()  {
    	return this.getEmail();
    }

    
    
    public boolean isOpen() {

        Session session = Session.getActiveSession();
        return session.isOpened();
    }
}
	
	
	


