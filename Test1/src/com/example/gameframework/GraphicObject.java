package com.example.gameframework;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class GraphicObject {
	protected Bitmap bitmap;
	protected int x;
	protected int y;
public GraphicObject(Bitmap bitmap, int x, int y){
	this.bitmap = bitmap;
	this.x = x;
	this.y = y;
	
}
public int getX(){
	return x;
}
public int getY(){
	return y;
}
public void setPosition(int x, int y){
	this.x = x;
	this.y = y;
	
	
}
public GraphicObject(Bitmap bitmap) {
	this(bitmap, 0,0);
	
}
public void draw(Canvas c) {
	c.drawBitmap(bitmap, x, y,null);
	
}

}
