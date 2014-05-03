package com.example.test1;

import com.example.test1.R;
import com.example.gameframework.AppManager;
import com.example.gameframework.GameViewThread;
import com.example.gameframework.GraphicObject;
import com.example.gameframework.IState;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

	private GameViewThread mThread;
	private IState mState;

	public GameView(Context context) {
		super(context);

		this.getHolder().addCallback(this);
		mThread = new GameViewThread(getHolder(), this);
		AppManager aMgr = AppManager.getInstance();
		aMgr.setResources(getResources());
		aMgr.setGameView(this);
		
		changeGameState(new playState());
		getHolder().addCallback(this);
		setFocusable(true);

	}

	@Override
	protected void onDraw(Canvas canvas) {

	}

	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub

	}

	public void surfaceCreated(SurfaceHolder arg0) {
		// TODO Auto-generated method stub
		mThread.setRunning(true);
		mThread.start();
	}

	public void surfaceDestroyed(SurfaceHolder arg0) {
		// TODO Auto-generated method stub
		mThread.setRunning(false);
		boolean retry = true;
		while (retry) {
			try {
				mThread.join();
				retry = false;
			} catch (InterruptedException e) {
			}
		}
	}

	public void draw(Canvas canvas) {
		// Bitmap icon =
		// BitmapFactory.decodeResource(getResources(),R.drawable.ic_launcher);
		canvas.drawColor(Color.BLACK);

		mState.render(canvas);
		// m_Image.draw(canvas);

	}

	public void update() {
		mState.update();
	}

	public void changeGameState(IState state) {
		if (mState != null)
			mState.end();
		state.init();
		mState = state;
	}

	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		return mState.onTouchEvent(event);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		return mState.onKeyDown(keyCode, event);
	}

}
