package com.example.gameframework;

// 화면의 설정을 맡는다.
public class ScreenConfig {
	public   int mScreenWidth;
	public   int mScreenHeight;
	
	public int mVirtualScreenWidth;
	public int mVirtualScreenHeight;
	
	public   int mVirtualWidth;
	public   int mVirtualHeight;
	
	public int mCardWidth;
	public int mCardheight;
	
	public int mBigCardWidth;
	public int mBigCardheight;
	
	public int mInMyHandX;
	public int mInYourHandX;
	
	public int mInMyHandY;
	public int mInYourHandY;
	
	public ScreenConfig(int screenWidth , int screenHeight, int virtualWidth, int virtualHeight)
	{
		mScreenWidth = screenWidth;
		mScreenHeight = screenHeight;
		
		mVirtualScreenWidth = virtualWidth;
		mVirtualScreenHeight = virtualHeight;
		
		double mCalcScreenWidth = (double) virtualWidth / (double) screenWidth;
		double mCalcScreenHeight = (double) virtualHeight/ (double) screenHeight;
		
		mCardWidth = (int) (mVirtualScreenWidth/14);
		mCardheight = (int) (mVirtualScreenHeight/5);
		
		mBigCardWidth = (int) (mVirtualScreenWidth/10);
		mBigCardheight = (int) (mVirtualScreenWidth/5);
		
		mInMyHandX=1;
		mInYourHandX=1;
		
		mInMyHandY = mVirtualScreenHeight - mBigCardheight;
		mInYourHandY = (int)(mBigCardheight); 
	}
	public void setSize(int width, int height)
	{
		mVirtualWidth = width; 
		mVirtualHeight = height;
	}	
	public int getX(int x)
	{
		return (int)( x * mScreenWidth/mVirtualWidth);
	}	
	public int getY(int y)
	{
		return (int)( y * mScreenHeight/mVirtualHeight);
	}	
}