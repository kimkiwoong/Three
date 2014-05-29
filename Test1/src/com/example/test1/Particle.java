package com.example.test1;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Particle {
	private int xpos;
    private int ypos;
    private int speed;
    private Bitmap bitmap;
 
    public Particle(int startYPos, int startXPos,
    		int xPosRange, int minSpeed, int speedRange,
    		Bitmap bitmap) {
    	xpos = startXPos + (int) (Math.random() * xPosRange);
    	ypos = startYPos;
 
    	this.speed = (int) (minSpeed + Math.random() * speedRange);
    	this.bitmap = bitmap;
    }
 
    public void updatePhysics(int distChange) {
    	ypos -= distChange * speed;
    }
 
    public void doDraw(Canvas canvas) {
	canvas.drawBitmap(bitmap, xpos, ypos, null);
    }
 
    public boolean outOfSight() {
    	 return ypos <= -1 * bitmap.getHeight();
    }
}
