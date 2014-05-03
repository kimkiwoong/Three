package com.example.test1;



import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.View;

import com.example.gameframework.AppManager;

public class Startview extends View {

	private Bitmap mBackgroundrImg;
	

	public Startview(Context context) {
	  
		super(context);
		AppManager aMgr = AppManager.getInstance();
		aMgr.setStartView(this);
		aMgr.setResources(getResources());
	

	}

	//@Override
	/*protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		canvas.drawBitmap(mBackgroundrImg, 0, 0,null);
		for(int row = 0;row <mShuffle.length;row++){
			for(int col = 0; col <mShuffle[row].length; col++)
			{
				if(mShuffle[row][col].mState !=CardInfo.CS_CLOSE)
				{
			
				int color = mShuffle[row][col].mColor;
				canvas.drawBitmap(mCard[color],35+col*90,150+row*130,null);
				}else
				{
					canvas.drawBitmap(mBackgroundTag,35+col*90,150+row*130,null);
				}
			}
		}
	}
	*/

}
