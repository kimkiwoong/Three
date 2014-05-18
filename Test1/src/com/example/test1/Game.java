package com.example.test1;

import java.util.ArrayList;
import java.util.Collections;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.gameframework.AppManager;
import com.example.gameframework.CardInterface;

public class Game extends Activity {
	public Player m_User;//��������
	public Player m_Enemy;//���� ����
	GameManager GM;
	public int[] UserimageNum;//R.drawble.ī�� �̹����� ��ȣ
	public int[] EnemyimageNum;//R.drawble.ī�� �̹����� ��ȣ
	public JSONObject attacks ;
	public JSONObject depense;
	Animation mtranslate;
	Animation mmove;
	ArrayList<Integer> ranNumber;//��������
	ArrayList<CardInterface> myCard;//�� ī�� 10���� ���� �迭
	ArrayList<CardInterface> attackCard;// �� ����ī��3��
	ArrayList<CardInterface> handsCard;// �� �ڵ�ī�� 7��
	ArrayList<CardInterface> enemyCard;// �� ����ī�� 3��
	public boolean choiceMycard_1 = false;// �� ����ī�� ù��° ���ÿ���
	public boolean choiceMycard_2 = false;// �� ����ī�� �ι�° ���ÿ���
	public boolean choiceMycard_3 = false;// �� ����ī�� ����° ���ÿ���
	public boolean choiceMycard_4 = false;// �� ����ī�� ù��° ���ÿ���
	public boolean choiceMycard_5 = false;// �� ����ī�� �ι�° ���ÿ���
	public boolean choiceMycard_6 = false;// �� ����ī�� ����° ���ÿ���
	
	////////���̾�α� ���� ���� ������//////////
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
//////////////////////////////////
	
	

	// ���Ӻ��� ��� ��ư��/////

	public static Button fightmycard_1, fightmycard_2, fightmycard_3,
			fightyoucard_1, fightyoucard_2, fightyoucard_3, handcard_1,
			handcard_2, handcard_3, handcard_4, handcard_5, handcard_6,
			handcard_7, e_handcard_1, e_handcard_2, e_handcard_3, e_handcard_4,
			e_handcard_5, e_handcard_6, e_handcard_7;

	// //////////////turnend////////////
	public static Button turnend;
	// /////////////////////////////

	// ////////////
	
	///// ī�� ���� ������/////////
	View.OnClickListener choicecard = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if (v.getId() == R.id.fmcard_1) {     //ù��° �� ����ī�� ���ý�
				choiceMycard_1 = true;
				choiceMycard_2 = false;
				choiceMycard_3 = false;

			} else if (v.getId() == R.id.fmcard_2) {//�ι�° �� ����ī�� ���ý�
				choiceMycard_1 = false;
				choiceMycard_2 = true;
				choiceMycard_3 = false;

			} else if (v.getId() == R.id.fmcard_3) {//����° �� ����ī�� ���ý�
				choiceMycard_1 = false;
				choiceMycard_2 = false;
				choiceMycard_3 = true;

			}

			if (v.getId() == R.id.fycard_1) {///// ù��° ��ī�� ���ý� ���� ���° ����ī�尡 ���õǾ� �ִ��� Ȯ���� 
				                             //// �����͸� ��ȯ�Ͽ� ����///
				if (choiceMycard_1 == true || choiceMycard_2 == true || choiceMycard_3 == true) {
					attackDataStream(3);
				}
			} else if (v.getId() == R.id.fycard_2) {///// �ι�° ��ī�� ���ý� ���� ���° ����ī�尡 ���õǾ� �ִ��� Ȯ���� 
                //// �����͸� ��ȯ�Ͽ� ����///
				if (choiceMycard_1 == true || choiceMycard_2 == true || choiceMycard_3 == true) {
					attackDataStream(2);
				}
			} else if (v.getId() == R.id.fycard_3) {///// ����° ��ī�� ���ý� ���� ���° ����ī�尡 ���õǾ� �ִ��� Ȯ���� 
                //// �����͸� ��ȯ�Ͽ� ����///
				if (choiceMycard_1 == true || choiceMycard_2 == true || choiceMycard_3 == true) {
					attackDataStream(1);
				}
			}
		}
	};
	/////////attack������ ��ȯ///////////////
	public void attackDataStream(int youbutton){
		 attacks = new JSONObject();
			try {
				if (choiceMycard_1 == true) {
					attacks.put("userID", LoginManager.id);
					attacks.put("att", attackCard.get(0).mPower);
					attacks.put("hp", attackCard.get(0).mhp);
					attacks.put("cardID", attackCard.get(0).mID);
					attacks.put("myButtonID", 3);
				} else if (choiceMycard_2 == true) {
					attacks.put("userID", LoginManager.id);
					attacks.put("att", attackCard.get(1).mPower);
					attacks.put("hp", attackCard.get(1).mhp);
					attacks.put("cardID", attackCard.get(1).mID);
					attacks.put("myButtonID", 2);
				} else if (choiceMycard_3 == true) {
					attacks.put("userID", LoginManager.id);
					attacks.put("att", attackCard.get(2).mPower);
					attacks.put("hp", attackCard.get(2).mhp);
					attacks.put("cardID", attackCard.get(2).mID);
					attacks.put("myButtonID", 1);
				}
				attacks.put("yourButtonID", youbutton);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			GameManager.socket.emit("attack", attacks);
			choiceMycard_1 = false;
			choiceMycard_2 = false;
			choiceMycard_3 = false;
	}

	
	/// LongClickListener : ī�� Ȯ�� ///////////////////////
	
	View.OnLongClickListener Click = new View.OnLongClickListener() {

		@Override
		public boolean onLongClick(View v) {

			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.fycard_1:
				Game.this.onCreateDialog(E_CardID1);
				showDialog(E_CardID1);
				break;
			case R.id.fycard_2:
				Game.this.onCreateDialog(E_CardID2);
				showDialog(E_CardID2);
				break;
			case R.id.fycard_3:
				Game.this.onCreateDialog(E_CardID3);
				showDialog(E_CardID3);
				break;
			case R.id.fmcard_1:
				Game.this.onCreateDialog(MYCardID1);
				showDialog(MYCardID1);
				break;
			case R.id.fmcard_2:
				Game.this.onCreateDialog(MYCardID2);
				showDialog(MYCardID2);
				break;
			case R.id.fmcard_3:
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
		GM = GameManager.getInstance();
		// myCard= new ArrayList<CardInterface>();
		// enemyCard = new ArrayList<CardInterface>();
		
		// ////myattackcard///////
		fightmycard_1 = (Button) findViewById(R.id.fmcard_1);
		fightmycard_2 = (Button) findViewById(R.id.fmcard_2);
		fightmycard_3 = (Button) findViewById(R.id.fmcard_3);
		
		// ///youattackcard//////
		fightyoucard_1 = (Button) findViewById(R.id.fycard_1);
		fightyoucard_2 = (Button) findViewById(R.id.fycard_2);
		fightyoucard_3 = (Button) findViewById(R.id.fycard_3);
		
		// ///myhandcard/////
		handcard_1 = (Button) findViewById(R.id.handcard_1);
		handcard_2 = (Button) findViewById(R.id.handcard_2);
		handcard_3 = (Button) findViewById(R.id.handcard_3);
		handcard_4 = (Button) findViewById(R.id.handcard_4);
		handcard_5 = (Button) findViewById(R.id.handcard_5);
		handcard_6 = (Button) findViewById(R.id.handcard_6);
		handcard_7 = (Button) findViewById(R.id.handcard_7);
		
		// ///////enemy handcard/////////////////////////
		e_handcard_1 = (Button) findViewById(R.id.e_handcard_1);
		e_handcard_2 = (Button) findViewById(R.id.e_handcard_2);
		e_handcard_3 = (Button) findViewById(R.id.e_handcard_3);
		e_handcard_4 = (Button) findViewById(R.id.e_handcard_4);
		e_handcard_5 = (Button) findViewById(R.id.e_handcard_5);
		e_handcard_6 = (Button) findViewById(R.id.e_handcard_6);
		e_handcard_7 = (Button) findViewById(R.id.e_handcard_7);

		turnend = (Button) findViewById(R.id.turnend);
		turnend.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GameManager.socket.emit("turnchange", LoginManager.id);

			}
		});

		fightmycard_1.setOnClickListener(choicecard);
		fightmycard_2.setOnClickListener(choicecard);
		fightmycard_3.setOnClickListener(choicecard);

		fightyoucard_1.setOnClickListener(choicecard);
		fightyoucard_2.setOnClickListener(choicecard);
		fightyoucard_3.setOnClickListener(choicecard);

		fightmycard_1.setOnLongClickListener(Click);
		fightmycard_2.setOnLongClickListener(Click);
		fightmycard_3.setOnLongClickListener(Click);
		fightyoucard_1.setOnLongClickListener(Click);
		fightyoucard_2.setOnLongClickListener(Click);
		fightyoucard_3.setOnLongClickListener(Click);

		mtranslate = AnimationUtils.loadAnimation(this, R.anim.ani);
		mmove = AnimationUtils.loadAnimation(this, R.anim.moveani);
		GameManager.Games = this;

		// GM = GameManager.getInstance();
		// TODO Auto-generated constructor stub
		this.init();
		this.GameStart();
		GameManager.socket.emit("start", LoginManager.id);

	}

	public void init() {

		m_User = new Player(1);
		
		//���� ���� ����,���� ���� ����
		////test///// Player.java ���� 
		m_User.initsetcard();
		
		m_Enemy = new Player(2);
		m_Enemy.initsetcard();
		UserimageNum = new int[10];
		EnemyimageNum = new int[10];
	

	
		ranNumber = new ArrayList<Integer>();
		myCard = new ArrayList<CardInterface>();
		attackCard = new ArrayList<CardInterface>();
		handsCard = new ArrayList<CardInterface>();
		enemyCard = new ArrayList<CardInterface>();

		
		///////�������� ����///////////
		for (int i = 0; i < 10; i++) {
			ranNumber.add(i);
		}
		Collections.shuffle(ranNumber);

		for (int i = 0; i < 10; i++) {
			Log.i(ranNumber.get(i).toString(), ranNumber.get(i).toString());
		}

		//// ////////////////////////////

		
		for (int j = 0; j < ranNumber.size(); j++) {
			Log.i(m_User.myCardname[ranNumber.get(j)],m_User.myCardname[ranNumber.get(j)]);
			///User ������ �ִ� ī�� �������� �̿��ؼ� R.drawbble.���� �� ��Ʈ���� ���´�.
			UserimageNum[j] = AppManager.getInstance().getmResources()
					.getIdentifier(m_User.myCardname[ranNumber.get(j)],
							"drawable", "com.example.test1"); 
			//// mycard��� ������ ī�帮��Ʈ�� User ī�������� 10���� ����������� ���� add��
			myCard.add(m_User.myCard[ranNumber.get(j)]);

		}

		Log.i("ccccc", String.valueOf(ranNumber.size()));

		///mycard 10���� ī�帮��Ʈ���� 0,1,2�� �̾� ����ī��� ����
		
		attackCard.add(myCard.get(0));
		attackCard.add(myCard.get(1));
		attackCard.add(myCard.get(2));

		//Log.i("ccccc", attackCard.get(0).mID);
		//Log.i("ccccc", attackCard.get(1).mID);
		//Log.i("ccccc", attackCard.get(2).mID);

		///mycard 10���� ī�帮��Ʈ���� ����ī�� �̿��� ī�带 �ڵ�ī��� ����
		handsCard.add(myCard.get(3));
		handsCard.add(myCard.get(4));
		handsCard.add(myCard.get(5));
		handsCard.add(myCard.get(6));
		handsCard.add(myCard.get(7));
		handsCard.add(myCard.get(8));
		handsCard.add(myCard.get(9));

	}

	public void turntoast(String text) {
		Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT)
				.show();
	}

	public void GameStart() {

		// String a = Integer.toString(UserimageNum[0]);

		fightmycard_1.setBackgroundResource(UserimageNum[0]);
		fightmycard_1.setText("A: "+attackCard.get(0).mPower+"\n"+"D: "+attackCard.get(0).mhp);
		fightmycard_2.setBackgroundResource(UserimageNum[1]);
		fightmycard_2.setText("A: "+attackCard.get(1).mPower+"\n"+"D: "+attackCard.get(1).mhp);
		fightmycard_3.setBackgroundResource(UserimageNum[2]);
		fightmycard_3.setText("A: "+attackCard.get(2).mPower+"\n"+"D: "+attackCard.get(2).mhp);
		

		fightyoucard_1.setBackgroundResource(R.drawable.backcard);
		fightyoucard_2.setBackgroundResource(R.drawable.backcard);
		fightyoucard_3.setBackgroundResource(R.drawable.backcard);

		handcard_1.setBackgroundResource(UserimageNum[3]);
		handcard_2.setBackgroundResource(UserimageNum[4]);
		handcard_3.setBackgroundResource(UserimageNum[5]);
		handcard_4.setBackgroundResource(UserimageNum[6]);
		handcard_5.setBackgroundResource(UserimageNum[7]);
		handcard_6.setBackgroundResource(UserimageNum[8]);
		handcard_7.setBackgroundResource(UserimageNum[9]);

		e_handcard_1.setBackgroundResource(R.drawable.backcard);
		e_handcard_2.setBackgroundResource(R.drawable.backcard);
		e_handcard_3.setBackgroundResource(R.drawable.backcard);
		e_handcard_4.setBackgroundResource(R.drawable.backcard);
		e_handcard_5.setBackgroundResource(R.drawable.backcard);
		e_handcard_6.setBackgroundResource(R.drawable.backcard);
		e_handcard_7.setBackgroundResource(R.drawable.backcard);

	}
	
	//////////depense ������ ////////////
	public void depenseDataStream(int index, int mybutton, int youbutton){
		depense = new JSONObject();
		try {
			depense.put("userID", LoginManager.id);
			depense.put("att", attackCard.get(index).mPower);
			depense.put("hp", attackCard.get(index).mhp);
			depense.put("cardID", attackCard.get(index).mID);
			depense.put("myButtonID", mybutton);
			depense.put("yourButtonID", youbutton);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		GameManager.socket.emit("depense", depense);
	}
	
	////////// ������ �޾����� ����Ǵ� �Լ�//////////////////
	/////////������ ���� ī���� ������ depense�� ���./////
	public void attack(int mybutton, int yourbutton) {

		if (mybutton == 1) {
			EnemyimageNum[0] = AppManager
					.getInstance()
					.getmResources()
					.getIdentifier(GameManager.m_cardID, "drawable",
							"com.example.test1");

			Log.i("sss", GameManager.m_cardID);
			fightyoucard_1.setBackgroundResource(EnemyimageNum[0]);
			
			if (yourbutton == 1) {
				depenseDataStream(0, 3, 3);
				
				System.out.println(attackCard.get(0).GetHp());
				changeMycardStat(fightmycard_1, 0);
				

			} else if (yourbutton == 2) {
				depenseDataStream(1, 3, 2);
				changeMycardStat(fightmycard_2, 1);
			
			} else if (yourbutton == 3) {
				depenseDataStream(2, 3, 1);
				changeMycardStat(fightmycard_3, 2);
				
			}
			

		} else if (mybutton == 2) {
			EnemyimageNum[1] = AppManager
					.getInstance()
					.getmResources()
					.getIdentifier(GameManager.m_cardID, "drawable",
							"com.example.test1");

			fightyoucard_2.setBackgroundResource(EnemyimageNum[1]);

			
			if (yourbutton == 1) {
				depenseDataStream(0, 2, 3);
				changeMycardStat(fightmycard_1, 0);
				
			} else if (yourbutton == 2) {
				depenseDataStream(1, 2, 2);
				changeMycardStat(fightmycard_2, 1);
				
			} else if (yourbutton == 3) {
				depenseDataStream(2, 2, 1);
				changeMycardStat(fightmycard_3, 2);
				
			}
		

		} else if (mybutton == 3) {
			EnemyimageNum[2] = AppManager
					.getInstance()
					.getmResources()
					.getIdentifier(GameManager.m_cardID, "drawable",
							"com.example.test1");

			fightyoucard_3.setBackgroundResource(EnemyimageNum[2]);

			
			if (yourbutton == 1) {
				depenseDataStream(0, 1, 3);
				changeMycardStat(fightmycard_1, 0);
				
			} else if (yourbutton == 2) {
				depenseDataStream(1, 1, 2);
				changeMycardStat(fightmycard_2, 1);
				
			} else if (yourbutton == 3) {
				depenseDataStream(2, 1, 1);
				changeMycardStat(fightmycard_3, 2);
			}

		}

	}
	////ȭ�鿡 ������ ��ȯ../////////
	public void changeMycardStat(final Button b,final int index){
		attackCard.get(index).SetHp(attackCard.get(index).GetHp()-GameManager.att_i);
		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
				b.setText("A: "+attackCard.get(index).mPower+"\n"+"D: "+attackCard.get(index).mhp);
				
			}
		}, 500);
	}
	public void changeEnemycardStat(final Button b, final int index){
		b.setText("A: "+GameManager.e_att_i+"\n"+"D: "+GameManager.e_hp_i);
		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
				b.setText("A: "+GameManager.e_att_i+"\n"+"D: "+(GameManager.e_hp_i - attackCard.get(0).mPower));
				
			}
		}, 1000);
	}
	/////////event : depensed �� �޾����� �������� �Լ�//////////////////

	public void depense(int buttonid) {
		if (buttonid == 1) {
			EnemyimageNum[0] = AppManager
					.getInstance()
					.getmResources()
					.getIdentifier(GameManager.e_cardID, "drawable",
							"com.example.test1");
			
			fightyoucard_1.setBackgroundResource(EnemyimageNum[0]);
			
			if (GameManager.e_mybutton==1) {
				changeEnemycardStat(fightyoucard_1, 0);
			} else if(GameManager.e_mybutton == 2){
				changeEnemycardStat(fightyoucard_1, 1);		
			} else if(GameManager.e_mybutton==3){
				changeEnemycardStat(fightyoucard_1, 2);			
			}
			
		} else if (buttonid == 2) {
			EnemyimageNum[1] = AppManager
					.getInstance()
					.getmResources()
					.getIdentifier(GameManager.e_cardID, "drawable",
							"com.example.test1");

			fightyoucard_2.setBackgroundResource(EnemyimageNum[1]);
			if (GameManager.e_mybutton==1) {
				changeEnemycardStat(fightyoucard_2, 0);
			} else if(GameManager.e_mybutton == 2){
				changeEnemycardStat(fightyoucard_2, 1);		
			} else if(GameManager.e_mybutton==3){
				changeEnemycardStat(fightyoucard_2, 2);			
			}
			

		} else if (buttonid == 3) {
			EnemyimageNum[2] = AppManager
					.getInstance()
					.getmResources()
					.getIdentifier(GameManager.e_cardID, "drawable",
							"com.example.test1");

			fightyoucard_3.setBackgroundResource(EnemyimageNum[2]);
			
			if (GameManager.e_mybutton==1) {
				changeEnemycardStat(fightyoucard_3, 0);
			} else if(GameManager.e_mybutton == 3){
				changeEnemycardStat(fightyoucard_1, 1);		
			} else if(GameManager.e_mybutton==3){
				changeEnemycardStat(fightyoucard_3, 2);			
			}
			
		}
		
		//////////
		
		
		if (GameManager.e_mybutton==1) {
			changeMycardStat(fightmycard_1, 0);		
		} else if(GameManager.e_mybutton == 2){
			changeMycardStat(fightmycard_1, 1);			
		} else if(GameManager.e_mybutton==3){
			changeMycardStat(fightmycard_1, 2);			
		}

	}

	/////���� ������� �ʴ� �Լ���///////////////////////
	public void GamePlay() {
		while (GameManager.GameEnd) {
			if (GameManager.turn == GameManager.Userturn) {
				this.Myturn();
			} else if (GameManager.turn == GameManager.Enemyturn) {
				this.EnemyTurn();
			}
			if (m_User.getRemain_Card() == 0 || m_Enemy.getRemain_Card() == 0) {
				GM.Gameover();
			}
		}
	}

	public void Myturn() {
		// TODO Auto-generated method stub

		GameManager.turn = GameManager.Enemyturn;

	}

	public void EnemyTurn() {

		GameManager.turn = GameManager.Userturn;

	}
//////////////////////////////////////////
	@Override
	protected Dialog onCreateDialog(int id) {
		// TODO Auto-generated method stub
		Dialog dialog = null;
		dialog = new Dialog(this);
		dialog.setContentView(R.layout.dialog_card);
		dialog.setTitle("CardInfo");
		ImageView img = (ImageView) dialog.findViewById(R.id.image1);
		if (id / 10 == 0) {
			img.setImageResource(UserimageNum[id % 10]);
		} else if (id / 10 == 1) {
			img.setImageResource(EnemyimageNum[id % 10]);
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