package com.example.gameframework;




import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class AppManager {
	
	
	private Resources mResources;
	
	
	
	


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
