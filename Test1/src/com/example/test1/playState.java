package com.example.test1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.StrictMode.ThreadPolicy.Builder;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;




import com.example.gameframework.AppManager;

import com.example.gameframework.CardInfo;
import com.example.gameframework.CardInterface;
import com.example.gameframework.CardFactory;
import com.example.gameframework.IState;
import com.example.test1.Card_GO.GO_NORMAL_POJOL;

public class playState implements IState {
		
		private Player mPlayer;
		
		public String Card_id;
	
		private int ca1;
		private Bitmap ca2;
		
		
	@Override
	public void init() {
	
	
		//// TODO Auto-generated method stub
		//mPlayer = new Player(AppManager.getInstance().getBitmap(R.drawable.player));
		//mPlayer.setPosition(140, 300);
		//CardInfos CB = new GO_NORMAL_POJOL();
		//CardFactory 	CM = new CardFactory(CB);
		//CM.build(); CardInterface GO_NORMALPOJOL = CM.getCard();
		mPlayer= new Player(1);
		mPlayer.initsetcard();
		
		//c1 = CardFactory.create("GO_NORMAL_POJOL");
		ca1 =AppManager.getInstance().getmResources().getIdentifier("card_5", "drawable","com.example.test1");
		
		ca2 = AppManager.getInstance().getBitmap(ca1);
	
		
		
		
				
		//mPlayer.setPlayer(0);
		//Log.(mPlayer.playerCard[0].GetmID(), mPlayer.playerCard[0].GetmID());
		
	
		// mBackground = new Background(0);
		
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		long gameTime = System.currentTimeMillis();
		//mPlayer.update(gameTime);
		//mBackground.update(gameTime);
	}

	@Override
	public void render(Canvas c) {
	//mBackground.draw(c);
		
			c.drawBitmap(ca2, 90,90, null);
			
		
		
		// TODO Auto-generated method stub
		//mPlayer.draw(c);
		
	}

	@Override
	public boolean onTouchEvent(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}
	public boolean onKeyDown(int keyCode, KeyEvent event){
	
		return false;
	}

	@Override
	public void Myturn() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enemyturn() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void end() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Fightturn() {
		// TODO Auto-generated method stub
		
	}
	

}
