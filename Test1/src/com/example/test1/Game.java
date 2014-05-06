package com.example.test1;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

import com.example.gameframework.AppManager;
import com.example.gameframework.CardInterface;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class Game extends Activity {
	public Player m_User;
	public Player m_Enemy;
	GameManager GM;
	public int[] UserimageNum;
	public int[] EnemyimageNum;
	public Bitmap[] m_UserCardImage;
	public Bitmap[] m_EnemyCardImage;
	Animation mtranslate;
	Animation mmove;
	ArrayList<Integer> ranNumber;
	ArrayList<CardInterface> myCard;
	ArrayList<CardInterface> enemyCard;
	boolean choice = false;
	public final int myDialogCardID1 = 0;
	public final int myDialogCardID2 = 1;
	public final int myDialogCardID3 = 2;
	public final int myDialogCardID4 = 3;
	public final int myDialogCardID5 = 4;
	public final int myDialogCardID6 = 5;
	public final int myDialogCardID7 = 6;

	public final int MYCardID1 = 0;
	public final int MYCardID2 = 1;
	public final int MYCardID3 = 2;

	public final int E_CardID1 = 10;
	public final int E_CardID2 = 11;
	public final int E_CardID3 = 12;

	// public

	public static Button bt1, bt2, bt3, bt4, bt5, bt6;
	int index = 0;
	View.OnLongClickListener Click = new View.OnLongClickListener() {
		
		@Override
		public boolean onLongClick(View v) {
			
			// TODO Auto-generated method stub
			switch(v.getId()){
			case R.id.button8:
				Game.this.onCreateDialog(E_CardID1);
				showDialog(E_CardID1);
				break;
			case R.id.button9:
				Game.this.onCreateDialog(E_CardID2);
				showDialog(E_CardID2);
				break;
			case R.id.button10:
				Game.this.onCreateDialog(E_CardID3);
				showDialog(E_CardID3);
				break;
			case R.id.button11:
				Game.this.onCreateDialog(MYCardID1);
				showDialog(MYCardID1);
				break;
			case R.id.button12:
				Game.this.onCreateDialog(MYCardID2);
				showDialog(MYCardID2);
				break;
			case R.id.button13:
				Game.this.onCreateDialog(MYCardID3);
				showDialog(MYCardID3);
				break;
				
			}
			return false;
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gameview);
		AppManager aMgr = AppManager.getInstance();
		aMgr.setResources(getResources());
		GM=GameManager.getInstance();
		//myCard= new ArrayList<CardInterface>();
		//enemyCard = new ArrayList<CardInterface>();
		bt1 = (Button) findViewById(R.id.button11);
		bt2 = (Button) findViewById(R.id.button12);
		bt3 = (Button) findViewById(R.id.button13);

		bt4 = (Button) findViewById(R.id.button8);
		bt5 = (Button) findViewById(R.id.button9);
		bt6 = (Button) findViewById(R.id.button10);
		bt1.setOnLongClickListener(Click);
		bt2.setOnLongClickListener(Click);
		bt3.setOnLongClickListener(Click);
		bt4.setOnLongClickListener(Click);
		bt5.setOnLongClickListener(Click);
		bt6.setOnLongClickListener(Click);
		mtranslate = AnimationUtils.loadAnimation(this, R.anim.ani);
		mmove = AnimationUtils.loadAnimation(this, R.anim.moveani);
		
		//GM = GameManager.getInstance();
		// TODO Auto-generated constructor stub
		this.init();
		this.GameStart();

		

	
		
		
	}

	public void init() {

		m_User = new Player(1);
		m_User.initsetcard();

		m_Enemy = new Player(2);
		m_Enemy.initsetcard();
		 UserimageNum = new int[10];
		 EnemyimageNum = new int[10];
		 m_UserCardImage = new Bitmap[10];
		 m_EnemyCardImage = new Bitmap[10];
		
		ranNumber = new ArrayList<Integer>();
		myCard = new ArrayList<CardInterface>();
		enemyCard = new ArrayList<CardInterface>();
		for (int i = 0; i < 10; i++) {

			ranNumber.add(i);
		
		}
		Collections.shuffle(ranNumber);
		for (int i = 0; i < 10; i++) {

		
			Log.i(ranNumber.get(i).toString(), ranNumber.get(i).toString());
		}
		
		// ////////////////////////////

		for (int j = 0; j <=ranNumber.size()-1; j++) {
			Log.i(m_User.myCardname[ranNumber.get(j)],m_User.myCardname[ranNumber.get(j)]);
			UserimageNum[j] =AppManager.getInstance().getmResources().getIdentifier(m_User.myCardname[ranNumber.get(j)],	"drawable", "com.example.test1");
			m_UserCardImage[j] = AppManager.getInstance().getBitmap(UserimageNum[j]);
			
			EnemyimageNum[j] = AppManager.getInstance().getmResources().getIdentifier(m_Enemy.myCardname[ranNumber.get(j)],"drawable", "com.example.test1");
			m_EnemyCardImage[j] = AppManager.getInstance().getBitmap(EnemyimageNum[j]);
			
			myCard.add(m_User.myCard[ranNumber.get(j)]);
			enemyCard.add(m_Enemy.myCard[ranNumber.get(j)]);
			m_UserCardImage[j].recycle();
			m_EnemyCardImage[j].recycle();
			
		}
		
		
	}


	public void GameStart() {
	

		
		String a = Integer.toString(UserimageNum[0]);

		GM.setTurn(1);
		Log.i(a,a);
		bt1.setBackgroundResource(UserimageNum[0]);
		bt2.setBackgroundResource(UserimageNum[1]);
		bt3.setBackgroundResource(UserimageNum[2]);
		
		bt4.setBackgroundResource(EnemyimageNum[0]);
		bt5.setBackgroundResource(EnemyimageNum[1]);
		bt6.setBackgroundResource(EnemyimageNum[2]);
		
	}
	public void GamePlay() {
		while (GM.GameEnd) {
			if (GM.GetTurn() == 1) {
				this.Myturn();
			} else if (GM.GetTurn() == 2) {
				this.EnemyTurn();
			}
			if (m_User.getRemain_Card() == 0 || m_Enemy.getRemain_Card() == 0) {
				GM.Gameover();
			}
		}
	}

	public void Myturn() {
		// TODO Auto-generated method stub

		GM.setTurn(GameManager.Enemyturn);

	}

	public void EnemyTurn() {

		GM.setTurn(GameManager.Userturn);

	}

	@Override
	protected Dialog onCreateDialog(int id) {
		// TODO Auto-generated method stub
		Dialog dialog = null;
		dialog = new Dialog(this);
		dialog.setContentView(R.layout.dialog_card);
		dialog.setTitle("CardInfo");
		ImageView img = (ImageView) dialog.findViewById(R.id.image1);
		if(id/10==0){
			img.setImageResource(UserimageNum[id%10]);
		} else if(id/10==1){
			img.setImageResource(EnemyimageNum[id%10]);
		}
		
		

		return dialog;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
