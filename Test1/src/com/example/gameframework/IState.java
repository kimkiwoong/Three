package com.example.gameframework;

import android.graphics.Canvas;
import android.view.KeyEvent;
import android.view.MotionEvent;

public interface IState {
	public void init();
	public void update();
	public void Myturn();
	public void enemyturn();
	public void Fightturn();
	public void render(Canvas c);
	public void end();
	public boolean onTouchEvent(MotionEvent e);
	public boolean onKeyDown(int keyCode, KeyEvent event);
}
