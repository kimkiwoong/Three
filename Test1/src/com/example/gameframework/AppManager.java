package com.example.gameframework;

import com.example.test1.GameView;
import com.example.test1.Startview;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class AppManager {
	
	private GameView mGameView;
	private Resources mResources;
	private Startview mStartview;
	
	
	
	public GameView getmGameView() {
		return mGameView;
	}

	public void setGameView(GameView gameView) {
		this.mGameView = gameView;
	}
	public Startview getmStartview() {
		return mStartview;
	}

	public void setStartView(Startview startview) {
		this.mStartview = startview;
	}

	public Resources getmResources() {
		return mResources;
	}

	public void setResources(Resources resources) {
		this.mResources = resources;
	}

	private static AppManager instance = null;
	
	public static AppManager getInstance(){
		if(instance == null)
			instance = new AppManager();
		
		return instance;
	}
	
	private AppManager(){}
	
	public Bitmap getBitmap(int res){
		return BitmapFactory.decodeResource(mResources,res);
		}
	
}
