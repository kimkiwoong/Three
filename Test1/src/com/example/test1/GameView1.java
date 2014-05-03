package com.example.test1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;
import android.view.View;

import com.example.gameframework.AppManager;
import com.example.gameframework.CardInterface;
import com.example.gameframework.CardInfo;
import com.example.gameframework.CardFactory;

public class GameView1 extends View {
	private Player mPlayer;
	private CardInterface c1;
	public String Card_id;
	private Bitmap ca2;
	 int ca1;
	public Player m_Users;
	public Player m_Enemys;
	GameManager GM;
	int t;
	public int[] UserimageNum;
	public int[] EnemyimageNum;
	public Bitmap[] m_UserCardImage;
	public Bitmap[] m_EnemyCardImage;
	public GameView1(Context context) {
		
		super(context);
		AppManager aMgr = AppManager.getInstance();
		aMgr.setResources(getResources());
		mPlayer= new Player(1);
		mPlayer.initsetcard();
		
	//	ca1 =AppManager.getInstance().getmResources().getIdentifier("card_1", "drawable","com.example.test1");
		//t =AppManager.getInstance().getmResources().getIdentifier(m_Users.myCardname[1], "drawable","com.example.test1");
	//	m_UserCardImage=(Bitmap)AppManager.getInstance().getBitmap(ca1);
		//GM = GameManager.getInstance();
		// TODO Auto-generated constructor stub
		init1();
		//this.GameStart();
		//this.GamePlay();
	}
	public void init1(){

		
		 m_Users = new Player(1);
		m_Users.initsetcard();
		
		
		//c1 = CardFactory.create("GO_NORMAL_POJOL");

		m_Enemys = new Player();
		m_Enemys.initsetcard();
		UserimageNum = new int[10];
		EnemyimageNum= new int[10];
		m_UserCardImage= new Bitmap[10];
		m_EnemyCardImage= new Bitmap[10];
		
		for(int i=0;i<10;i++){
			UserimageNum[i] =AppManager.getInstance().getmResources().getIdentifier(m_Users.myCardname[i], "drawable","com.example.test1");
			m_UserCardImage[i]=AppManager.getInstance().getBitmap(UserimageNum[i]);
		
			ca2 = AppManager.getInstance().getBitmap(ca1);
		}
			//EnemyimageNum[0]=AppManager.getInstance().getmResources().getIdentifier(m_Enemys.myCardname[5], "drawable","com.example.test1");
			//m_EnemyCardImage[0]=AppManager.getInstance().getBitmap(EnemyimageNum[0]);
			
		
		
	}
	public void GameStart(){
		

	    // 1~10까지의 정수를 랜덤하게 출력
		ArrayList<Integer> ranNumber = new ArrayList<Integer>();

		for(int i=0; i <3;i++){

		ranNumber.add(i);

		}

		Collections.shuffle(ranNumber);
		
		
		GM.setTurn(1);
		
		
	}
	public void GamePlay(){
		while(GM.GameEnd){
			if(GM.GetTurn()==1){
				this.Myturn();
			} else if(GM.GetTurn()==2){
				this.EnemyTurn();
			}
			if(m_Users.getRemain_Card()==0 || m_Enemys.getRemain_Card()==0){
				GM.Gameover();
			}
		}
	}
	public void Myturn() {
		// TODO Auto-generated method stub
		
		
		
		GM.setTurn(GameManager.Enemyturn);
		
	}
	public void EnemyTurn(){
		
		GM.setTurn(GameManager.Userturn);
		
	}
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		
			canvas.drawBitmap(m_UserCardImage[0], 30*5, 30*5,null);

		
		
		super.onDraw(canvas);
	}
	
	
}
