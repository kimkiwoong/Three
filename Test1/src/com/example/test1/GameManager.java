package com.example.test1;

import android.graphics.Canvas;
import android.view.KeyEvent;
import android.view.MotionEvent;





public class GameManager {
	private static GameManager Game_instance;
	public static final int Userturn=1; 
	public static final int Enemyturn=2;
	
	public static int turn;
	
	public boolean GameEnd = true;
	 public static GameManager getInstance() {

	        if (Game_instance == null) {
	        	Game_instance = new GameManager();
	        }

	        return Game_instance;
	    }
	
		public void setTurn(int turn) {
			
			
			GameManager.turn=turn;
			
		}
		public void Gameover(){
			this.GameEnd=false;
		}
		public int GetTurn(){
			return GameManager.turn;
		}
		
	
		

}