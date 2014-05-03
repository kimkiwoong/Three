package com.example.gameframework;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

import com.example.test1.GameView;

public class GameViewThread extends Thread {
	
	private SurfaceHolder mSurfaceHolder;
	private GameView mGameView;
	private boolean mRun = false;
	
	public GameViewThread(SurfaceHolder surfaceHolder, GameView gameView){
		this.mSurfaceHolder = surfaceHolder;
		this.mGameView = gameView;
	}
	public void setRunning(boolean run){
		this.mRun=run;
	}
	public void run(){
		Canvas canvas;
		while(mRun){
			canvas = null;
			try{mGameView.update();
			canvas = mSurfaceHolder.lockCanvas(null);
			synchronized(mSurfaceHolder){
				if(canvas!=null)mGameView.draw(canvas);
			}
			}
			finally{
				if(canvas!=null)
					mSurfaceHolder.unlockCanvasAndPost(canvas);
			}
			
			
		}
	}

}
