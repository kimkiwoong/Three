package com.example.test1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.internal.RealSystem;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gameframework.AppManager;
import com.example.gameframework.CardInterface;

public class Game extends Activity {
	public Player m_User;// 나의정보
	public Player m_Enemy;// 적의 정보
	
	public static int myType;

	private static String TAG_Total = "total";
	private static String TAG_Add = "Add";
	public static int UserTotalCount;
	public static int EnemyTotalCount;
	public static int backnum;
	public static Boolean activitychange;

	GameManager GM;
	SoundManager SM;
	public int[] UserimageNum;// R.drawble.카드 이미지의 번호
	public int[] EnemyimageNum;// R.drawble.카드 이미지의 번호
	public JSONObject attacks;
	public JSONObject depense;

	Animation mtranslate;
	Animation mmove;
	ArrayList<Integer> ranNumber;// 랜덤변수
	// ArrayList<Integer> Death_num;// turn 하기 전 상대 편 index
	ArrayList<Integer> Death_danger;// 2개 미만일 때
	ArrayList<Integer> Death_danger1;
	ArrayList<CardInterface> myCard;// 총 카드 10개를 담을 배열
	ArrayList<CardInterface> attackCard;// 내 공격카드3장
	ArrayList<CardInterface> handsCard;// 내 핸드카드 7장
	ArrayList<CardInterface> enemyCard;// 적 공격카드 3장

	CardInterface tempCard;

	public boolean choiceMycard_1;// 내 공격카드 첫번째 선택여부
	public boolean choiceMycard_2;// 내 공격카드 두번째 선택여부
	public boolean choiceMycard_3;// 내 공격카드 세번째 선택여부
	public boolean choiceMycard_4;// 적 공격카드 첫번째 선택여부
	public boolean choiceMycard_5;// 적 공격카드 두번째 선택여부
	public boolean choiceMycard_6;// 적 공격카드 세번째 선택여부

	public static boolean selectMycard_1;
	public static boolean selectMycard_2;
	public static boolean selectMycard_3;

	// 적 데스 카드 인지 확인 여부
	public static boolean Enemy_Deathcard1;
	public static boolean Enemy_Deathcard2;
	public static boolean Enemy_Deathcard3;

	// //////다이얼로그 띄우기 위한 변수들//////////
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

	public final int Death_change1 = 100;
	public final int Death_change2 = 101;
	public final int Death_change3 = 102;

	public static int Death_changeid = 0;
	public static int Hand_changeid = 0;

	// ////////////////////////////////
	public int My_handcard_count;
	public int Enemy_handcard_count = 0;

	private String TAG = "Game_tag";
	private String TAG_attack = "Game_attack";
	private String TAG_depense = "Game_depense";

	// 게임뷰의 모든 버튼들/////

	public static Button fightmycard_1, fightmycard_2, fightmycard_3,
			fightyoucard_1, fightyoucard_2, fightyoucard_3, handcard_1,
			handcard_2, handcard_3, handcard_4, handcard_5, handcard_6,
			handcard_7;
	public static Button[] e_handcard;
	public static Button[] dialog_end;
	public static ImageView[] handButton;
	// //////////////turnend////////////
	public LinearLayout turnedlayout;
	public Button turnend;
	public LinearLayout magiclayout;
	public Button magicon;
	// /////////////////////////////
	public int search_handcard;
	// //////////////////////////////////////////
	public RelativeLayout m1_layout;
	public RelativeLayout m2_layout;
	public RelativeLayout m3_layout;
	public RelativeLayout e1_layout;
	public RelativeLayout e2_layout;
	public RelativeLayout e3_layout;
		
	public TextView m1;
	public TextView m2;
	public TextView m3;

	public TextView e1;
	public TextView e2;
	public TextView e3;//////////////////////////////////////////////////
	// effect
	public FrameLayout myCardEffect1;
	public FrameLayout myCardEffect2;
	public FrameLayout myCardEffect3;
	public FrameLayout enemyCardEffect1;
	public FrameLayout enemyCardEffect2;
	public FrameLayout enemyCardEffect3;

	// gameovertext
	public static ImageView m_gameovertext;
	public static ImageView m_magicImg;
	// explosion
	public ImageView myExplosion1;
	public ImageView myExplosion2;
	public ImageView myExplosion3;
	public ImageView enemyExplosion1;
	public ImageView enemyExplosion2;
	public ImageView enemyExplosion3;

	// win lose
	public String result;
	public static String MyId;
	public static String EnemyId;

	// /// 카드 선택 리스너/////////
	View.OnClickListener choicecard = new View.OnClickListener() {

		@Override
		public void onClick(View v) {

			// Log.d("Game", msg);

			SM.play("touch");
			if (GameManager.myTurn == 1) {
				return;
			}

			// Log.d(TAG,"v.getBackground().equals(R.drawable.card_death) : " +
			// v.getBackground().equals(R.drawable.card_death));
			// Log.d(TAG,"v.getBackground().getCurrent().equals(R.drawable.card_death) : "
			// + v.getBackground().getCurrent().equals(R.drawable.card_death));
			// Log.d(TAG,"v.getResources().equals(R.drawable.card_death) : " +
			// v.getResources().equals(R.drawable.card_death));
			// Log.d(TAG,"v.getResources().getDrawable(R.drawable.card_death) : "
			// + v.getResources().getDrawable(R.drawable.card_death));

			// TODO Auto-generated method stub
			if (v.getId() == R.id.fmcard_1 && selectMycard_1 == true
					&& !m1.getText().equals("")) { // 첫번째 내 공격카드 선택시
				if(attackCard.get(0).mMagic==true){
					magicon.setVisibility(View.VISIBLE);
					magiclayout.setVisibility(View.VISIBLE);
				}else{
					magicon.setVisibility(View.INVISIBLE);
					magiclayout.setVisibility(View.INVISIBLE);
				}
				choiceMycard_1 = true;
				choiceMycard_2 = false;
				choiceMycard_3 = false;
				myCardEffect1.setBackgroundColor(Color.RED);
				myCardEffect2.setBackgroundColor(0);
				myCardEffect3.setBackgroundColor(0);

			} else if (v.getId() == R.id.fmcard_2 && selectMycard_2 == true
					&& !m2.getText().equals("")) {// 두번째 내 공격카드 선택시
				if(attackCard.get(1).mMagic==true){
					magicon.setVisibility(View.VISIBLE);
					magiclayout.setVisibility(View.VISIBLE);
				}else{
					magicon.setVisibility(View.INVISIBLE);
					magiclayout.setVisibility(View.INVISIBLE);
				}
				choiceMycard_1 = false;
				choiceMycard_2 = true;
				choiceMycard_3 = false;
				myCardEffect1.setBackgroundColor(0);
				myCardEffect2.setBackgroundColor(Color.RED);
				myCardEffect3.setBackgroundColor(0);

			} else if (v.getId() == R.id.fmcard_3 && selectMycard_3 == true
					&& !m3.getText().equals("")) {// 세번째 내 공격카드 선택시
				if(attackCard.get(2).mMagic==true){
					magicon.setVisibility(View.VISIBLE);
					magiclayout.setVisibility(View.VISIBLE);
				}else{
					magicon.setVisibility(View.INVISIBLE);
					magiclayout.setVisibility(View.INVISIBLE);
				}
				choiceMycard_1 = false;
				choiceMycard_2 = false;
				choiceMycard_3 = true;
				myCardEffect1.setBackgroundColor(0);
				myCardEffect2.setBackgroundColor(0);
				myCardEffect3.setBackgroundColor(Color.RED);
			}

			if (v.getId() == R.id.fycard_1) {// /// 첫번째 적카드 선택시 나의 몇번째 공격카드가
												// 선택되어 있는지 확인후
				// Log.d(TAG, "Enemy_Deathcard1 "+Enemy_Deathcard1); // // 데이터를
				// 변환하여 보냄///
				if (Enemy_Deathcard1 == true)
					return;

				if (choiceMycard_1 == true || choiceMycard_2 == true
						|| choiceMycard_3 == true) {
					enemyCardEffect1.setBackgroundColor(Color.RED);
					enemyCardEffect2.setBackgroundColor(0);
					enemyCardEffect3.setBackgroundColor(0);
					attackDataStream(3);

				}
			} else if (v.getId() == R.id.fycard_2) {// /// 두번째 적카드 선택시 나의 몇번째
													// 공격카드가 선택되어 있는지 확인후
				// // 데이터를 변환하여 보냄///
				// Log.d(TAG, "Enemy_Deathcard2 "+Enemy_Deathcard2);
				if (Enemy_Deathcard2 == true)
					return;

				if (choiceMycard_1 == true || choiceMycard_2 == true
						|| choiceMycard_3 == true) {
					enemyCardEffect1.setBackgroundColor(0);
					enemyCardEffect2.setBackgroundColor(Color.RED);
					enemyCardEffect3.setBackgroundColor(0);
					attackDataStream(2);
				}
			} else if (v.getId() == R.id.fycard_3) {// /// 세번째 적카드 선택시 나의 몇번째
													// 공격카드가 선택되어 있는지 확인후
				// // 데이터를 변환하여 보냄///
				// Log.d(TAG, "Enemy_Deathcard3 "+Enemy_Deathcard3);
				if (Enemy_Deathcard3 == true)
					return;

				if (choiceMycard_1 == true || choiceMycard_2 == true
						|| choiceMycard_3 == true) {
					enemyCardEffect1.setBackgroundColor(0);
					enemyCardEffect2.setBackgroundColor(0);
					enemyCardEffect3.setBackgroundColor(Color.RED);
					attackDataStream(1);

				}
			}
		}
	};

	// ///////attack데이터 변환///////////////
	public void attackDataStream(int youbutton) {
		Log.d("func", "attackDataStream");

		
		if (GameManager.isBlockAttack == true)
			return;
		
		GameManager.isBlockAttack = true;
		
		attacks = new JSONObject();
		try {
			if (choiceMycard_1 == true) {
				SM.play(attackCard.get(0).mID + "_attack");
				attacks.put("userID", LoginManager.id);
				attacks.put("att", attackCard.get(0).mPower);
				attacks.put("hp", attackCard.get(0).mhp);
				attacks.put("cardID", attackCard.get(0).mID);
				attacks.put("myButtonID", 3);
				selectMycard_1 = false;
			} else if (choiceMycard_2 == true) {
				SM.play(attackCard.get(1).mID + "_attack");
				attacks.put("userID", LoginManager.id);
				attacks.put("att", attackCard.get(1).mPower);
				attacks.put("hp", attackCard.get(1).mhp);
				attacks.put("cardID", attackCard.get(1).mID);
				attacks.put("myButtonID", 2);
				selectMycard_2 = false;
			} else if (choiceMycard_3 == true) {
				SM.play(attackCard.get(2).mID + "_attack");
				attacks.put("userID", LoginManager.id);
				attacks.put("att", attackCard.get(2).mPower);
				attacks.put("hp", attackCard.get(2).mhp);
				attacks.put("cardID", attackCard.get(2).mID);
				attacks.put("myButtonID", 1);
				selectMycard_3 = false;
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

	// / LongClickListener : 카드 확대 ///////////////////////

	View.OnLongClickListener Click = new View.OnLongClickListener() {

		@Override
		public boolean onLongClick(View v) {

			myCardEffect1.setBackgroundColor(0);
			myCardEffect2.setBackgroundColor(0);
			myCardEffect3.setBackgroundColor(0);

			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.fycard_1:
				if (!e1.getText().equals("")) {
					Game.this.onCreateDialog(E_CardID1);
					showDialog(E_CardID1);
				}
				break;
			case R.id.fycard_2:
				if (!e2.getText().equals("")) {
					Game.this.onCreateDialog(E_CardID2);
					showDialog(E_CardID2);
				}
				break;
			case R.id.fycard_3:
				if (!e3.getText().equals("")) {
					Game.this.onCreateDialog(E_CardID3);
					showDialog(E_CardID3);
				}
				break;
			case R.id.fmcard_1:
				// death_card 이면
				if (m1.getText().equals("")) {
					// 내 차례 이면
					if (GameManager.myTurn == 0 && My_handcard_count > 0) {
						// Log.d(TAG, " fmcard_1 - if");
						Game.this.onCreateDialog(Death_change1);
						Death_changeid = 0;
						showDialog(Death_change1);
					}
				}
				// death_card 아니면
				else {
					// Log.d(TAG, " fmcard_1 - else");
					Game.this.onCreateDialog(MYCardID1);
					showDialog(MYCardID1);
				}
				break;
			case R.id.fmcard_2:
				// death_card 이면
				if (m2.getText().equals("")) {
					// 내 차례 이면
					if (GameManager.myTurn == 0 && My_handcard_count > 0) {
						// Log.d(TAG, " fmcard_2 - if");
						Game.this.onCreateDialog(Death_change2);
						Death_changeid = 1;
						showDialog(Death_change2);
					}
				}
				// death_card가 아니면
				else {
					// Log.d(TAG, " fmcard_2 - else");
					Game.this.onCreateDialog(MYCardID2);
					showDialog(MYCardID2);
				}
				break;
			case R.id.fmcard_3:
				// death_card 이면
				if (m3.getText().equals("")) {
					// 내 차례 이면
					if (GameManager.myTurn == 0 && My_handcard_count > 0) {
						// Log.d(TAG, " fmcard_3 - if");
						Game.this.onCreateDialog(Death_change3);
						Death_changeid = 2;
						showDialog(Death_change3);
					}
				}
				// death_card가 아니면
				else {
					// Log.d(TAG, " fmcard_3 - else");
					Game.this.onCreateDialog(MYCardID3);
					showDialog(MYCardID3);
				}
				break;

			}
			return false;
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d("start", "start");
		setContentView(R.layout.gameview);
		AppManager aMgr = AppManager.getInstance();
		aMgr.setResources(getResources());
		GM = GameManager.getInstance();
		SM = SoundManager.getInstance();
		GameManager.nowActivity="game";
		// enemy hand card
		e_handcard = new Button[7];

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

		e_handcard[0] = (Button) findViewById(R.id.e_handcard_1);
		e_handcard[1] = (Button) findViewById(R.id.e_handcard_2);
		e_handcard[2] = (Button) findViewById(R.id.e_handcard_3);
		e_handcard[3] = (Button) findViewById(R.id.e_handcard_4);
		e_handcard[4] = (Button) findViewById(R.id.e_handcard_5);
		e_handcard[5] = (Button) findViewById(R.id.e_handcard_6);
		e_handcard[6] = (Button) findViewById(R.id.e_handcard_7);
		// ///////////////////////////////////////////////////
		m1_layout = (RelativeLayout)findViewById(R.id.Mycard1Layout);
		m2_layout = (RelativeLayout)findViewById(R.id.Mycard2Layout);
		m3_layout = (RelativeLayout)findViewById(R.id.Mycard3Layout);
		
		e1_layout = (RelativeLayout)findViewById(R.id.EnemyCard1Layout);
		e2_layout = (RelativeLayout)findViewById(R.id.EnemyCard2Layout);
		e3_layout = (RelativeLayout)findViewById(R.id.EnemyCard3Layout);
		///////////////////////////////////////////////////////////
		m1 = (TextView)findViewById(R.id.m1);
		m2 = (TextView)findViewById(R.id.m2);
		m3 = (TextView)findViewById(R.id.m3);
		
		e1 = (TextView)findViewById(R.id.e1);
		e2 = (TextView)findViewById(R.id.e2);
		e3 = (TextView)findViewById(R.id.e3);
		///////////////////////////////////////////////////////////
		

		myCardEffect1 = (FrameLayout) findViewById(R.id.my_effect1);
		myCardEffect2 = (FrameLayout) findViewById(R.id.my_effect2);
		myCardEffect3 = (FrameLayout) findViewById(R.id.my_effect3);

		enemyCardEffect1 = (FrameLayout) findViewById(R.id.enemy_effect1);
		enemyCardEffect2 = (FrameLayout) findViewById(R.id.enemy_effect2);
		enemyCardEffect3 = (FrameLayout) findViewById(R.id.enemy_effect3);

		// explosion
		myExplosion1 = (ImageView) findViewById(R.id.myexplosion1);
		myExplosion2 = (ImageView) findViewById(R.id.myexplosion2);
		myExplosion3 = (ImageView) findViewById(R.id.myexplosion3);
		enemyExplosion1 = (ImageView) findViewById(R.id.enemyexplosion1);
		enemyExplosion2 = (ImageView) findViewById(R.id.enemyexplosion2);
		enemyExplosion3 = (ImageView) findViewById(R.id.enemyexplosion3);
		/////////////////
		magicon = (Button)findViewById(R.id.magicon);
		magiclayout = (LinearLayout)findViewById(R.id.magiclayout);
		//////////magic///////////////
		magicon.setVisibility(View.INVISIBLE);
		magiclayout.setVisibility(View.INVISIBLE);
		
		magicon.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(attackCard.get(0).mMagic){
					switch(attackCard.get(0).mMagicName){
					case CardInterface.GO_MAGIC1:
						magicEffect(CardInterface.GO_MAGIC1);
						break;
					case CardInterface.SIN_MAGIC1:
						magicEffect(CardInterface.SIN_MAGIC1);
						break;
					case CardInterface.BACK_MAGIC1:
						magicEffect(CardInterface.BACK_MAGIC1);
						break;
						
					}
					attackCard.get(0).mMagic=false;
				} else if(attackCard.get(1).mMagic){
					switch(attackCard.get(1).mMagicName){
					case CardInterface.GO_MAGIC1:
						magicEffect(CardInterface.GO_MAGIC1);
						break;
					case CardInterface.SIN_MAGIC1:
						magicEffect(CardInterface.SIN_MAGIC1);
						break;
					case CardInterface.BACK_MAGIC1:
						magicEffect(CardInterface.BACK_MAGIC1);
						break;
						
					}
					attackCard.get(1).mMagic=false;
				} else if(attackCard.get(2).mMagic){
					switch(attackCard.get(2).mMagicName){
					case CardInterface.GO_MAGIC1:
						magicEffect(CardInterface.GO_MAGIC1);
						break;
					case CardInterface.SIN_MAGIC1:
						magicEffect(CardInterface.SIN_MAGIC1);
						break;
					case CardInterface.BACK_MAGIC1:
						magicEffect(CardInterface.BACK_MAGIC1);
						break;
						
					}
					attackCard.get(2).mMagic=false;
				}
				magicon.setVisibility(View.INVISIBLE);
				magiclayout.setVisibility(View.INVISIBLE);
			}
		});
		// Turn Button
		turnend = (Button) findViewById(R.id.turnend);
		turnedlayout = (LinearLayout) findViewById(R.id.turnendlayout);

		turnend.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				magicon.setVisibility(View.INVISIBLE);
				magiclayout.setVisibility(View.INVISIBLE);
				// 상대방 Turn
				if (GameManager.myTurn == 1) {
					Toast.makeText(getApplicationContext(), "상대방 Turn 입니다 ", 0)
							.show();
					return;
				}

				if (UserTotalCount >= 3) {
					
					// Death Card 존재
					if (m1.getText().equals("")
							|| m2.getText().equals("")
							|| m3.getText().equals("")) {
						Toast.makeText(getApplicationContext(),
								"Death Card가 존재 합니다 ", 0).show();
						return;
					}
				} else {
					if (m1.getText().equals("")
							&& m2.getText().equals("")
							&& m3.getText().equals("")) {
						Toast.makeText(getApplicationContext(),
								"한장이상의 Card가 존재 합니다 ", 0).show();
						return;
					}
				}

				Toast.makeText(getApplicationContext(), "Turn이 종료하였습니다 ", 0)
						.show();
				GameManager.myTurn = 1;
				turnend.setVisibility(View.INVISIBLE);
				turnedlayout.setVisibility(View.INVISIBLE);
				JSONObject jobj = new JSONObject();
				try {
					jobj.put("userID", LoginManager.id);
					jobj.put("handcount", My_handcard_count);

				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				GameManager.socket.emit("turnchange", jobj);

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
		GameManager.isBlockAttack = false;
		GameManager.socket.emit("start", LoginManager.id);

	}

	public void init() {
		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				SM.gamebackgroundPlaySound();
			}
		}, 2000);
		choiceMycard_1 = false;// 내 공격카드 첫번째 선택여부
		choiceMycard_2 = false;// 내 공격카드 두번째 선택여부
		choiceMycard_3 = false;// 내 공격카드 세번째 선택여부
		choiceMycard_4 = false;// 적 공격카드 첫번째 선택여부
		choiceMycard_5 = false;// 적 공격카드 두번째 선택여부
		choiceMycard_6 = false;// 적 공격카드 세번째 선택여부
		selectMycard_1 = true;
		selectMycard_2 = true;
		selectMycard_3 = true;
		Enemy_Deathcard1 = false;
		Enemy_Deathcard2 = false;
		Enemy_Deathcard3 = false;
		Log.d("type", Game.myType+"");
		switch (Game.myType) {
		case 1:
			m_User = new Player(1);
			m_User.initsetcard(1);
		
			

			break;
		case 2:
			m_User = new Player(2);
			m_User.initsetcard(2);
		
			break;
		case 3:
			m_User = new Player(3);
			m_User.initsetcard(3);
		
			break;
		default:
			break;
		}
		

		// 나의 정보 생성,적의 정보 생성
		// //test///// Player.java 참조
		
		UserTotalCount = 10;
		My_handcard_count = 7;
		EnemyTotalCount = 10;
		UserimageNum = new int[10];
		EnemyimageNum = new int[10];
		// myCardEffect = new LinearLayout[3];
		// enemyCardEffect = new LinearLayout[3];

		ranNumber = new ArrayList<Integer>();
		// Death_num = new ArrayList<Integer>();
		Death_danger = new ArrayList<Integer>();
		Death_danger1 = new ArrayList<Integer>();
		myCard = new ArrayList<CardInterface>();
		attackCard = new ArrayList<CardInterface>();
		handsCard = new ArrayList<CardInterface>();
		enemyCard = new ArrayList<CardInterface>();

		// /////랜덤변수 셋팅///////////
		for (int i = 0; i < 10; i++) {
			ranNumber.add(i);
		}
		Collections.shuffle(ranNumber);

		// for (int i = 0; i < 10; i++) {
		// Log.i(ranNumber.get(i).toString(), ranNumber.get(i).toString());
		// }

		// // ////////////////////////////

		for (int j = 0; j < ranNumber.size(); j++) {
			// Log.i(m_User.myCardname[ranNumber.get(j)],
			// m_User.myCardname[ranNumber.get(j)]);
			// /User 정보에 있는 카드 정보들을 이용해서 R.drawbble.변수 의 인트값을 얻어온다.
			UserimageNum[j] = AppManager
					.getInstance()
					.getmResources()
					.getIdentifier(m_User.myCardname[ranNumber.get(j)],
							"drawable", "com.example.test1");
			// // mycard라는 임의의 카드리스트에 User 카드의정보 10개를 랜덤변수들로 통해 add함
			myCard.add(m_User.myCard[ranNumber.get(j)]);

		}

		// Log.i("ccccc", String.valueOf(ranNumber.size()));

		// /mycard 10개의 카드리스트에서 0,1,2를 뽑아 공격카드로 선언

		attackCard.add(myCard.get(0));
		attackCard.add(myCard.get(1));
		attackCard.add(myCard.get(2));
		

		// Log.i("ccccc", attackCard.get(0).mID);
		// Log.i("ccccc", attackCard.get(1).mID);
		// Log.i("ccccc", attackCard.get(2).mID);

		// /mycard 10개의 카드리스트에서 공격카드 이외의 카드를 핸드카드로 선언
		handsCard.add(myCard.get(3));
		handsCard.add(myCard.get(4));
		handsCard.add(myCard.get(5));
		handsCard.add(myCard.get(6));
		handsCard.add(myCard.get(7));
		handsCard.add(myCard.get(8));
		handsCard.add(myCard.get(9));

		// attackCard.set(1, myCard.get(6));

	}

	public void turntoast(String text) {
		Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT)
				.show();
	}

	public void GameStart() {

		// String a = Integer.toString(UserimageNum[0]);

		fightmycard_1.setBackgroundResource(UserimageNum[0]);
		m1.setText("공:"+attackCard.get(0).mPower + " 방:"+attackCard.get(0).mhp);
		
		fightmycard_2.setBackgroundResource(UserimageNum[1]);
		m2.setText("공:"+attackCard.get(1).mPower + " 방:"+attackCard.get(1).mhp);
		
		fightmycard_3.setBackgroundResource(UserimageNum[2]);
		m3.setText("공:"+attackCard.get(2).mPower + " 방:"+attackCard.get(2).mhp);
		
		if(Makeroom.Enemynum==1){
			backnum = AppManager
					.getInstance()
					.getmResources()
					.getIdentifier("go_back",
							"drawable", "com.example.test1");
		}else if(Makeroom.Enemynum==2){
			backnum = AppManager
					.getInstance()
					.getmResources()
					.getIdentifier("back_back",
							"drawable", "com.example.test1");
		}else if(Makeroom.Enemynum==3){
			backnum = AppManager
					.getInstance()
					.getmResources()
					.getIdentifier("sin_back",
							"drawable", "com.example.test1");
		}
	
		fightyoucard_1.setBackgroundResource(backnum);
		fightyoucard_2.setBackgroundResource(backnum);
		fightyoucard_3.setBackgroundResource(backnum);

		handcard_1.setBackgroundResource(UserimageNum[3]);
		handcard_2.setBackgroundResource(UserimageNum[4]);
		handcard_3.setBackgroundResource(UserimageNum[5]);
		handcard_4.setBackgroundResource(UserimageNum[6]);
		handcard_5.setBackgroundResource(UserimageNum[7]);
		handcard_6.setBackgroundResource(UserimageNum[8]);
		handcard_7.setBackgroundResource(UserimageNum[9]);

		activitychange = false;

		for (int i = 0; i < 7; i++) {
			e_handcard[i].setBackgroundResource(backnum);
		}

	}

	public void turned_img() {

		Log.d("func", "turned_img");
		
		Log.d("turn", "EnemyTotalCount : " + EnemyTotalCount + " ");

		for (int i = 6; i >= Enemy_handcard_count && i >= 0; i--) {
			e_handcard[i].setBackgroundResource(R.drawable.card_death);
		}

		if (Death_danger.size() > 0) {

			for (int i = 0; i < Death_danger.size(); i++) {
				Log.d("turn", " turned_img 받아온 값 " + Death_danger.get(i));
				if (Death_danger.get(i) == 2 ) {
					
					if(e1.getText().equals("")){
						fightyoucard_1.setBackgroundResource(backnum);
						fightyoucard_1.setText("");
					}
					
				} else if (Death_danger.get(i) == 1 ) {
					
					if(e2.getText().equals("")){
						fightyoucard_2.setBackgroundResource(backnum);
						fightyoucard_2.setText("");
					};
				} else if (Death_danger.get(i) == 0) {
					
					if(e3.getText().equals("")){
						fightyoucard_3.setBackgroundResource(backnum);
						fightyoucard_3.setText("");
					}
				}
			}

			Log.d("turn", "Death_danger size : " + Death_danger.size()
					+ " Clear!! ");
		}
		
		Death_danger.clear();
		//////////////////////////
		if (Death_danger1.size() > 0) {

			for (int i = 0; i < Death_danger1.size(); i++) {
				Log.d("turn", " turned_img 받아온 값 " + Death_danger1.get(i));
				if (Death_danger1.get(i) == 2 ) {
					
					Enemy_Deathcard1 = false;
					
				} else if (Death_danger1.get(i) == 1 ) {
					
					Enemy_Deathcard2 = false;
				
				} else if (Death_danger1.get(i) == 0) {
										
					Enemy_Deathcard3 = false;
				}
			}

			
		}
		Death_danger1.clear();
		/*
		 * else{
		 * 
		 * //Death_num 있으면 if(Death_num.size() > 0){
		 * Log.d(TAG,"Death_num.size() : " + Death_num.size()); for(int i = 0; i
		 * < Death_num.size(); i++){ Log.d(TAG, " Death_num  " +
		 * Death_num.get(i)); if(Death_num.get(i) == R.id.fycard_1 ){
		 * fightyoucard_1.setBackgroundResource(backnum);
		 * fightyoucard_1.setText(""); Enemy_Deathcard1 = false; } else
		 * if(Death_num.get(i) == R.id.fycard_2){
		 * fightyoucard_2.setBackgroundResource(backnum);
		 * fightyoucard_2.setText(""); Enemy_Deathcard2 = false; } else
		 * if(Death_num.get(i) == R.id.fycard_3){
		 * fightyoucard_3.setBackgroundResource(backnum);
		 * fightyoucard_3.setText(""); Enemy_Deathcard3 = false; } }
		 * 
		 * Death_num.clear(); Log.d(TAG_Add, "Death_num size : " +
		 * Death_num.size()+" Clear!! "); }
		 * 
		 * for(int i = 6; i >=Enemy_handcard_count && i >= 0; i--){
		 * e_handcard[i].setBackgroundResource(R.drawable.card_death); } }
		 */

	}

	public void Death_Change(int deathcard, int handcard) {
		
		Log.d("func", "Death_Change");

		Log.d(TAG, "UserTotalCount " + UserTotalCount);

		JSONObject jobj1 = new JSONObject();
		try {
			jobj1.put("userID", LoginManager.id);
			jobj1.put("deathcard", deathcard);

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		GameManager.socket.emit("deathcard", jobj1);
		Log.d(TAG, "UserTotalCount 보내는 값 : " + deathcard);

		tempCard = new CardInterface();

		// 새로운 카드
		tempCard = handsCard.get(handcard);
		handsCard.set(handcard, attackCard.get(deathcard));
		if (handcard == 0) {
			handcard_1.setBackgroundResource(R.drawable.card_death);
		} else if (handcard == 1) {
			handcard_2.setBackgroundResource(R.drawable.card_death);
		} else if (handcard == 2) {
			handcard_3.setBackgroundResource(R.drawable.card_death);
		} else if (handcard == 3) {
			handcard_4.setBackgroundResource(R.drawable.card_death);
		} else if (handcard == 4) {
			handcard_5.setBackgroundResource(R.drawable.card_death);
		} else if (handcard == 5) {
			handcard_6.setBackgroundResource(R.drawable.card_death);
		} else if (handcard == 6) {
			handcard_7.setBackgroundResource(R.drawable.card_death);
		}

		attackCard.set(deathcard, tempCard);
	

		if (deathcard == 0) {
			search_handcard = AppManager
					.getInstance()
					.getmResources()
					.getIdentifier(attackCard.get(0).mID, "drawable",
							"com.example.test1");
			fightmycard_1.setBackgroundResource(search_handcard);
			m1.setText("공:"+attackCard.get(0).mPower + " 방:"+attackCard.get(0).mhp);
			
			SM.play(attackCard.get(0).mID + "_come");
		} else if (deathcard == 1) {
			search_handcard = AppManager
					.getInstance()
					.getmResources()
					.getIdentifier(attackCard.get(1).mID, "drawable",
							"com.example.test1");
			fightmycard_2.setBackgroundResource(search_handcard);
			m2.setText("공:"+attackCard.get(1).mPower + " 방:"+attackCard.get(1).mhp);
			
			SM.play(attackCard.get(1).mID + "_come");
		} else if (deathcard == 2) {
			search_handcard = AppManager
					.getInstance()
					.getmResources()
					.getIdentifier(attackCard.get(2).mID, "drawable",
							"com.example.test1");
			fightmycard_3.setBackgroundResource(search_handcard);
			m3.setText("공:"+attackCard.get(2).mPower + " 방:"+attackCard.get(2).mhp);
			
			SM.play(attackCard.get(2).mID + "_come");
		}

		My_handcard_count--;
	}

	// ////////depense 데이터 ////////////
	public void depenseDataStream(int index, int mybutton, int youbutton) {
		
		Log.d("func", "depenseDataStream");
		
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

	// //////// 어택을 받았을때 수행되는 함수////O//////////////
	// ///////어택을 받은 카드의 정보를 depense로 쏜다./////
	public void attack(int mybutton, int yourbutton) {
		
		Log.d("func", "attack");

		
		// Activity Not Touch

		// getWindow().addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

		if (mybutton == 1) {
			EnemyimageNum[0] = AppManager
					.getInstance()
					.getmResources()
					.getIdentifier(GameManager.m_cardID, "drawable",
							"com.example.test1");

			// Log.i("sss", GameManager.m_cardID);
			fightyoucard_1.setBackgroundResource(EnemyimageNum[0]);

			if (yourbutton == 1) {
				// Log.d(TAG_attack, "myButton 1 -> yourbutton 1 ");
				depenseDataStream(0, 3, 3);
				// System.out.println(attackCard.get(0).GetHp());
				att_changeMycardStat(fightmycard_1, 0);
				att_changeEnemycardStat(fightyoucard_1, 0);
			} else if (yourbutton == 2) {
				// Log.d(TAG_attack, "myButton 1 -> yourbutton 2 ");
				depenseDataStream(1, 3, 2);
				att_changeMycardStat(fightmycard_2, 1);
				att_changeEnemycardStat(fightyoucard_1, 1);

			} else if (yourbutton == 3) {
				// Log.d(TAG_attack, "myButton 1 -> yourbutton 3 ");
				depenseDataStream(2, 3, 1);
				att_changeMycardStat(fightmycard_3, 2);
				att_changeEnemycardStat(fightyoucard_1, 2);
			}

		} else if (mybutton == 2) {
			EnemyimageNum[1] = AppManager
					.getInstance()
					.getmResources()
					.getIdentifier(GameManager.m_cardID, "drawable",
							"com.example.test1");

			fightyoucard_2.setBackgroundResource(EnemyimageNum[1]);

			if (yourbutton == 1) {
				// Log.d(TAG_attack, "myButton 2 -> yourbutton 1 ");
				depenseDataStream(0, 2, 3);
				att_changeMycardStat(fightmycard_1, 0);
				att_changeEnemycardStat(fightyoucard_2, 0);
			} else if (yourbutton == 2) {
				// Log.d(TAG_attack, "myButton 2 -> yourbutton 2 ");
				depenseDataStream(1, 2, 2);
				att_changeMycardStat(fightmycard_2, 1);
				att_changeEnemycardStat(fightyoucard_2, 1);

			} else if (yourbutton == 3) {
				// Log.d(TAG_attack, "myButton 2 -> yourbutton 3 ");
				depenseDataStream(2, 2, 1);
				att_changeMycardStat(fightmycard_3, 2);
				att_changeEnemycardStat(fightyoucard_2, 2);
			}

		} else if (mybutton == 3) {
			EnemyimageNum[2] = AppManager
					.getInstance()
					.getmResources()
					.getIdentifier(GameManager.m_cardID, "drawable",
							"com.example.test1");

			fightyoucard_3.setBackgroundResource(EnemyimageNum[2]);

			if (yourbutton == 1) {
				// Log.d(TAG_attack, "myButton 3 -> yourbutton 1 ");
				depenseDataStream(0, 1, 3);
				att_changeMycardStat(fightmycard_1, 0);
				att_changeEnemycardStat(fightyoucard_3, 0);
			} else if (yourbutton == 2) {
				// Log.d(TAG_attack, "myButton 3 -> yourbutton 2 ");
				depenseDataStream(1, 1, 2);
				att_changeMycardStat(fightmycard_2, 1);
				att_changeEnemycardStat(fightyoucard_3, 1);
			} else if (yourbutton == 3) {
				// Log.d(TAG_attack, "myButton 3 -> yourbutton 3 ");
				depenseDataStream(2, 1, 1);
				att_changeMycardStat(fightmycard_3, 2);
				att_changeEnemycardStat(fightyoucard_3, 2);
			}
		}
	}

	public void explosionStart(final Button b) {
		
		Log.d("func", "explosionStart");
		
		if (b.getId() == R.id.fmcard_1) {
			myExplosion1.setVisibility(View.VISIBLE);
		} else if (b.getId() == R.id.fmcard_2) {
			myExplosion2.setVisibility(View.VISIBLE);
		} else if (b.getId() == R.id.fmcard_3) {
			myExplosion3.setVisibility(View.VISIBLE);
		} else if (b.getId() == R.id.fycard_1) {
			enemyExplosion1.setVisibility(View.VISIBLE);
		} else if (b.getId() == R.id.fycard_2) {
			enemyExplosion2.setVisibility(View.VISIBLE);
		} else if (b.getId() == R.id.fycard_3) {
			enemyExplosion3.setVisibility(View.VISIBLE);
		}
	}

	public void explosionEnd(final Button b) {
		
		Log.d("func", "explosionEnd");

		if (b.getId() == R.id.fmcard_1) {
			myExplosion1.setVisibility(View.INVISIBLE);
		} else if (b.getId() == R.id.fmcard_2) {
			myExplosion2.setVisibility(View.INVISIBLE);
		} else if (b.getId() == R.id.fmcard_3) {
			myExplosion3.setVisibility(View.INVISIBLE);
		} else if (b.getId() == R.id.fycard_1) {
			enemyExplosion1.setVisibility(View.INVISIBLE);
		} else if (b.getId() == R.id.fycard_2) {
			enemyExplosion2.setVisibility(View.INVISIBLE);
		} else if (b.getId() == R.id.fycard_3) {
			enemyExplosion3.setVisibility(View.INVISIBLE);
		}
	}

	// //화면에 데이터 변환..///////// //Me

	// 여기
	// 내가 공격을 받았을 때 내 카드 데이터 갱신
	public void att_changeMycardStat(final Button b, final int index) {

		Log.d("func", "att_changeMycardStat");
		
		// Log.d(TAG,b.getId()+"");
		attackCard.get(index).SetHp(
				attackCard.get(index).GetHp() - GameManager.att_i);
		if (b.getId() == R.id.fmcard_1) {
			myCardEffect1.setBackgroundColor(Color.RED);
			myCardEffect2.setBackgroundColor(0);
			myCardEffect3.setBackgroundColor(0);
		} else if (b.getId() == R.id.fmcard_2) {
			myCardEffect1.setBackgroundColor(0);
			myCardEffect2.setBackgroundColor(Color.RED);
			myCardEffect3.setBackgroundColor(0);
		} else if (b.getId() == R.id.fmcard_3) {
			myCardEffect1.setBackgroundColor(0);
			myCardEffect2.setBackgroundColor(0);
			myCardEffect3.setBackgroundColor(Color.RED);
		}

		if (attackCard.get(index).GetHp() <= 0) {
			explosionStart(b);
			attackCard.get(index).mMagic=false;
			magicon.setVisibility(View.INVISIBLE);
			magiclayout.setVisibility(View.INVISIBLE);
			
		}
		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				if (b.getId() == R.id.fmcard_1) {
					m1.setText("공:"+attackCard.get(index).mPower + " 방:"+attackCard.get(index).mhp);
					
				} else if (b.getId() == R.id.fmcard_2) {
					m2.setText("공:"+attackCard.get(index).mPower + " 방:"+attackCard.get(index).mhp);
				
				} else if (b.getId() == R.id.fmcard_3) {
					m3.setText("공:"+attackCard.get(index).mPower + " 방:"+attackCard.get(index).mhp);
					
				}
				

				if (attackCard.get(index).GetHp() <= 0) {

					SM.play(attackCard.get(index).mID + "_die");
					explosionEnd(b);

					b.setBackgroundResource(R.drawable.card_death);
					if (b.getId() == R.id.fmcard_1) {
						m1.setText("");
					} else if (b.getId() == R.id.fmcard_2) {
						m2.setText("");
					} else if (b.getId() == R.id.fmcard_3) {
						m3.setText("");
					}
					
					UserTotalCount--;

					JSONObject jobj = new JSONObject();
					try {
						jobj.put("userID", LoginManager.id);
						jobj.put("Totalcount", UserTotalCount);

					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					GameManager.socket.emit("count", jobj);

					if (UserTotalCount == 0) {
						m_gameovertext = (ImageView) findViewById(R.id.gameovertext);
						m_gameovertext.setVisibility(View.VISIBLE);

						Handler handler = new Handler();
						handler.postDelayed(new Runnable() {

							@Override
							public void run() {
								// TODO Auto-generated method stub

								Intent i = new Intent(Game.this, Gameover.class);
								startActivity(i);
								activitychange = true;
								// System.exit(0);
								
								finish();

							}
						}, 3000);
					}
				}

				myCardEffect1.setBackgroundColor(0);
				myCardEffect2.setBackgroundColor(0);
				myCardEffect3.setBackgroundColor(0);
				enemyCardEffect1.setBackgroundColor(0);
				enemyCardEffect2.setBackgroundColor(0);
				enemyCardEffect3.setBackgroundColor(0);

			}
		}, 1000);
	}

	// 내가 공격 했을 때 내 카드 데이터 갱신
	public void des_changeMycardStat(final Button b, final int index) {
		
		Log.d("func", "des_changeMycardStat");

		attackCard.get(index).SetHp(
				attackCard.get(index).GetHp() - GameManager.e_att_i);

		if (attackCard.get(index).GetHp() <= 0) {
			explosionStart(b);
			attackCard.get(index).mMagic=false;
			magicon.setVisibility(View.INVISIBLE);
			magiclayout.setVisibility(View.INVISIBLE);
		}
			JSONObject jobj = new JSONObject();
			try {
				jobj.put("userID", LoginManager.id);
				if(b.getId()==R.id.fmcard_1){
					jobj.put("deathcard", 2);
				}else if(b.getId()==R.id.fmcard_2){
					jobj.put("deathcard", 1);
				}else if(b.getId()==R.id.fmcard_3){
					jobj.put("deathcard", 0);
				}
				GameManager.socket.emit("nodeathcard", jobj);
				
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
				

		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				if (b.getId() == R.id.fmcard_1) {
					m1.setText("공:"+attackCard.get(index).mPower + " 방:"+attackCard.get(index).mhp);
				
				} else if (b.getId() == R.id.fmcard_2) {
					m2.setText("공:"+attackCard.get(index).mPower + " 방:"+attackCard.get(index).mhp);
				
				} else if (b.getId() == R.id.fmcard_3) {
					m3.setText("공:"+attackCard.get(index).mPower + " 방:"+attackCard.get(index).mhp);
					
				}

				if (attackCard.get(index).GetHp() <= 0) {
					SM.play(attackCard.get(index).mID + "_die");
					explosionEnd(b);

					b.setBackgroundResource(R.drawable.card_death);
					if (b.getId() == R.id.fmcard_1) {
						m1.setText("");
					} else if (b.getId() == R.id.fmcard_2) {
						m2.setText("");
					} else if (b.getId() == R.id.fmcard_3) {
						m3.setText("");
					}
					UserTotalCount--;

					// Log.d(TAG_Total, "3 UserTotalCount " + UserTotalCount +
					// " EnemyTotalCount " + EnemyTotalCount);

					JSONObject jobj = new JSONObject();
					try {
						jobj.put("userID", LoginManager.id);
						jobj.put("Totalcount", UserTotalCount);

					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					GameManager.socket.emit("count", jobj);

					if (UserTotalCount == 0) {
						m_gameovertext = (ImageView) findViewById(R.id.gameovertext);
						m_gameovertext.setVisibility(View.VISIBLE);

						Handler handler = new Handler();
						handler.postDelayed(new Runnable() {

							@Override
							public void run() {
								// TODO Auto-generated method stub

								Intent i = new Intent(Game.this, Gameover.class);
								startActivity(i);
								activitychange = true;
								// System.exit(0);
								
								finish();

							}
						}, 3000);
					}
				}

			}
		}, 1000);
		
		
		GameManager.isBlockAttack = false;
		
	}

	// 여기랑
	// 내가 공격을 받았을 때 적 카드 데이터 갱신
	public void att_changeEnemycardStat(final Button b, final int index) {
		
		Log.d("func", "att_changeEnemycardStat");

		// Log.d(TAG, "b % 10 -> "+ b.getId() % 10);
		if (b.getId() == R.id.fycard_1) {
			enemyCardEffect1.setBackgroundColor(Color.RED);
			enemyCardEffect2.setBackgroundColor(0);
			enemyCardEffect3.setBackgroundColor(0);
			e1.setText("공:"+GameManager.att_i + " 방:"+GameManager.hp_i);
		} else if (b.getId() == R.id.fycard_2) {
			enemyCardEffect1.setBackgroundColor(0);
			enemyCardEffect2.setBackgroundColor(Color.RED);
			enemyCardEffect3.setBackgroundColor(0);
			e2.setText("공:"+GameManager.att_i + " 방:"+GameManager.hp_i);
		} else if (b.getId() == R.id.fycard_3) {
			enemyCardEffect1.setBackgroundColor(0);
			enemyCardEffect2.setBackgroundColor(0);
			enemyCardEffect3.setBackgroundColor(Color.RED);
			e3.setText("공:"+GameManager.att_i + " 방:"+GameManager.hp_i);
			
		}

		if (GameManager.hp_i - attackCard.get(index).mPower <= 0) {
			explosionStart(b);
		}

		
		// Log.d(TAG, "enemy attack : " + GameManager.att_i + "  enemy hp : "+
		// GameManager.hp_i);
		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				if (b.getId() == R.id.fycard_1) {
					e1.setText("공:"+GameManager.att_i + " 방:"+(GameManager.hp_i - attackCard.get(index).mPower));
				
				} else if (b.getId() == R.id.fycard_2) {
				
					e2.setText("공:"+GameManager.att_i + " 방:"+(GameManager.hp_i - attackCard.get(index).mPower));
				
				} else if (b.getId() == R.id.fycard_3) {
				
					e3.setText("공:"+GameManager.att_i + " 방:"+(GameManager.hp_i - attackCard.get(index).mPower));
				
				}

				
				if (GameManager.hp_i - attackCard.get(index).mPower <= 0) {

					explosionEnd(b);
					b.setBackgroundResource(R.drawable.card_death);
					

					if (b.getId() == R.id.fycard_1) {
						e1.setText("");
						Enemy_Deathcard1 = true;
					} else if (b.getId() == R.id.fycard_2) {
						e2.setText("");
						Enemy_Deathcard2 = true;
					} else if (b.getId() == R.id.fycard_3) {
						e3.setText("");
						Enemy_Deathcard3 = true;
					}

					Log.d(TAG_Add, "b.getId : " + b.getId()
							+ " Enemy_Deathcard1 : " + Enemy_Deathcard1
							+ " Enemy_Deathcard2 " + Enemy_Deathcard2
							+ " Enemy_Deathcard3 " + Enemy_Deathcard3);

				}
				myCardEffect1.setBackgroundColor(0);
				myCardEffect2.setBackgroundColor(0);
				myCardEffect3.setBackgroundColor(0);
				enemyCardEffect1.setBackgroundColor(0);
				enemyCardEffect2.setBackgroundColor(0);
				enemyCardEffect3.setBackgroundColor(0);
			}

		}, 1000);
	}

	// 내가 공격 했을 때 적 카드 데이터 갱신
	public void des_changeEnemycardStat(final Button b, final int index) {

		Log.d("func", "des_changeEnemycardStat");
		
		if (b.getId() == R.id.fycard_1) {
	
			e1.setText("공:"+GameManager.e_att_i + " 방:"+GameManager.e_hp_i);
		} else if (b.getId() == R.id.fycard_2) {
			
			e2.setText("공:"+GameManager.e_att_i + " 방:"+GameManager.e_hp_i);
		} else if (b.getId() == R.id.fycard_3) {
			
			e3.setText("공:"+GameManager.e_att_i + " 방:"+GameManager.e_hp_i);
		}
		
		// Log.d(TAG, "enemy attack : " + GameManager.e_att_i + "  enemy hp : "+
		// GameManager.e_hp_i);

		if (GameManager.e_hp_i - attackCard.get(index).mPower <= 0) {
			explosionStart(b);
		}

		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				if (b.getId() == R.id.fycard_1) {
					
					e1.setText("공:"+GameManager.e_att_i + " 방:"+(GameManager.e_hp_i - attackCard.get(index).mPower));
				
				} else if (b.getId() == R.id.fycard_2) {
				
					e2.setText("공:"+GameManager.e_att_i + " 방:"+(GameManager.e_hp_i - attackCard.get(index).mPower));
	
				} else if (b.getId() == R.id.fycard_3) {
				
					e3.setText("공:"+GameManager.e_att_i + " 방:"+(GameManager.e_hp_i - attackCard.get(index).mPower));
				
				}
				
				if (GameManager.e_hp_i - attackCard.get(index).mPower <= 0) {

					explosionEnd(b);

					b.setBackgroundResource(R.drawable.card_death);
					if (b.getId() == R.id.fycard_1) {
						e1.setText("");
						Enemy_Deathcard1 = true;
					} else if (b.getId() == R.id.fycard_2) {
						e2.setText("");
						Enemy_Deathcard2 = true;
					} else if (b.getId() == R.id.fycard_3) {
						e3.setText("");
						Enemy_Deathcard3 = true;
					}
					

				

					Log.d(TAG_Add, "b.getId : " + b.getId()
							+ " Enemy_Deathcard1 : " + Enemy_Deathcard1
							+ " Enemy_Deathcard2 " + Enemy_Deathcard2
							+ " Enemy_Deathcard3 " + Enemy_Deathcard3);

				}
			}
		}, 1000);
	}

	public void EnemyGameover() {

		
		
		// TODO Auto-generated method stub

		if (UserTotalCount == 0 || activitychange) {
			Log.d(TAG, "UserTotalCount = 0 ");
			return;
		}

		ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);

		List<RunningTaskInfo> info;

		info = activityManager.getRunningTasks(1);
		// Log.i(TAG, info.get(0).topActivity.getClassName());
		if (info.get(0).topActivity.getClassName().equals(
				"com.example.test1.Gameover")) {
			Log.d(TAG, "if ");
			return;

		} else {
			
			try {
				result = Util
						.DownloadText("http://hsbug.hamt.co.kr/api/save_winlose?win_fb_id="
								+ Game.MyId + "&lose_fb_id=" + Game.EnemyId);
				Log.d(TAG, "MyId : "+ Game.MyId +" EnemyId " + Game.EnemyId);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			result = result.trim();

			m_gameovertext = (ImageView) findViewById(R.id.gameovertext);
			m_gameovertext.setVisibility(View.VISIBLE);
			Handler handler = new Handler();
			handler.postDelayed(new Runnable() {
				@Override
				public void run() {
					Log.d(TAG, "else ");

					Intent i = new Intent(Game.this, Gameover.class);
					startActivity(i);
					
					// System.exit(0);
					
					finish();
				}
			}, 3000);
		}

	}

	// ///////event : depensed 를 받았을때 행해지는 함수//////////////////

	public void depense(int buttonid) {

		Log.d("func", "depense");
		
		myCardEffect1.setBackgroundColor(0);
		myCardEffect2.setBackgroundColor(0);
		myCardEffect3.setBackgroundColor(0);
		enemyCardEffect1.setBackgroundColor(0);
		enemyCardEffect2.setBackgroundColor(0);
		enemyCardEffect3.setBackgroundColor(0);

		if (buttonid == 1) {
			EnemyimageNum[0] = AppManager
					.getInstance()
					.getmResources()
					.getIdentifier(GameManager.e_cardID, "drawable",
							"com.example.test1");

			fightyoucard_1.setBackgroundResource(EnemyimageNum[0]);

			if (GameManager.e_mybutton == 1) {
				// Log.d(TAG_depense, "buttonid 1 -> e_mybutton 1 ");
				des_changeEnemycardStat(fightyoucard_1, 0);
			} else if (GameManager.e_mybutton == 2) {
				// Log.d(TAG_depense, "buttonid 1 -> e_mybutton 2 ");
				des_changeEnemycardStat(fightyoucard_1, 1);
			} else if (GameManager.e_mybutton == 3) {
				// Log.d(TAG_depense, "buttonid 1 -> e_mybutton 3 ");
				des_changeEnemycardStat(fightyoucard_1, 2);
			}

		} else if (buttonid == 2) {
			EnemyimageNum[1] = AppManager
					.getInstance()
					.getmResources()
					.getIdentifier(GameManager.e_cardID, "drawable",
							"com.example.test1");

			fightyoucard_2.setBackgroundResource(EnemyimageNum[1]);
			if (GameManager.e_mybutton == 1) {
				// Log.d(TAG_depense, "buttonid 2 -> e_mybutton 1 ");
				des_changeEnemycardStat(fightyoucard_2, 0);
			} else if (GameManager.e_mybutton == 2) {
				// Log.d(TAG_depense, "buttonid 2 -> e_mybutton 2 ");
				des_changeEnemycardStat(fightyoucard_2, 1);
			} else if (GameManager.e_mybutton == 3) {
				// Log.d(TAG_depense, "buttonid 2 -> e_mybutton 3 ");
				des_changeEnemycardStat(fightyoucard_2, 2);
			}

		} else if (buttonid == 3) {
			EnemyimageNum[2] = AppManager
					.getInstance()
					.getmResources()
					.getIdentifier(GameManager.e_cardID, "drawable",
							"com.example.test1");

			fightyoucard_3.setBackgroundResource(EnemyimageNum[2]);

			if (GameManager.e_mybutton == 1) {
				// Log.d(TAG_depense, "buttonid 3 -> e_mybutton 1 ");
				des_changeEnemycardStat(fightyoucard_3, 0);
			} else if (GameManager.e_mybutton == 2) {
				// Log.d(TAG_depense, "buttonid 3 -> e_mybutton 2 ");
				des_changeEnemycardStat(fightyoucard_3, 1);
			} else if (GameManager.e_mybutton == 3) {
				// Log.d(TAG_depense, "buttonid 3 -> e_mybutton 3 ");
				des_changeEnemycardStat(fightyoucard_3, 2);
			}
		}

		if (GameManager.e_mybutton == 1) {
			des_changeMycardStat(fightmycard_1, 0);
		} else if (GameManager.e_mybutton == 2) {
			des_changeMycardStat(fightmycard_2, 1);
		} else if (GameManager.e_mybutton == 3) {
			des_changeMycardStat(fightmycard_3, 2);
		}

		// getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

	}

	// ////////////////////////////////////////
	@Override
	protected Dialog onCreateDialog(final int id) {
		// TODO Auto-generated method stub
		final Dialog dialog = new Dialog(this);

		if (id < 20) {
			dialog.setContentView(R.layout.dialog_card);
			dialog.setTitle("CardInfo");
			ImageView img = (ImageView) dialog.findViewById(R.id.image1);
			dialog.findViewById(R.id.dia_end).setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					removeDialog(id);
				}
			});
			
			int num;
			if (id / 10 == 0) {
				if (id % 10 == 0) {
					num = AppManager
							.getInstance()
							.getmResources()
							.getIdentifier(attackCard.get(0).mID, "drawable",
									"com.example.test1");
					Log.d("image",attackCard.get(0).mID);
					
					img.setImageResource(num);
				} else if (id % 10 == 1) {
					num  = AppManager
							.getInstance()
							.getmResources()
							.getIdentifier(attackCard.get(1).mID, "drawable",
									"com.example.test1");
					Log.d("image",attackCard.get(1).mID);
					img.setImageResource(num);
				} else if (id % 10 == 2) {
					num = AppManager
							.getInstance()
							.getmResources()
							.getIdentifier(attackCard.get(2).mID, "drawable",
									"com.example.test1");
					Log.d("image",attackCard.get(2).mID);
					img.setImageResource(num);
				}

			} else if (id / 10 == 1) {
				img.setImageResource(EnemyimageNum[id % 10]);
			}
		} else {

			dialog.setContentView(R.layout.dialog_changecard);
			// Button[] handButton = new Button[7];
			dialog.setCancelable(false);
			handButton = new ImageView[7];
			
			
			// Log.d(TAG, "ImageView 생성 ");

			handButton[0] = (ImageView) dialog.findViewById(R.id.handcardlist1);
			handButton[1] = (ImageView) dialog.findViewById(R.id.handcardlist2);
			handButton[2] = (ImageView) dialog.findViewById(R.id.handcardlist3);
			handButton[3] = (ImageView) dialog.findViewById(R.id.handcardlist4);
			handButton[4] = (ImageView) dialog.findViewById(R.id.handcardlist5);
			handButton[5] = (ImageView) dialog.findViewById(R.id.handcardlist6);
			handButton[6] = (ImageView) dialog.findViewById(R.id.handcardlist7);

			final View.OnClickListener click = new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					// Log.d(TAG, "Dialog Click ");
					switch (v.getId()) {
					case R.id.handcardlist1:
						Hand_changeid = 0;
						Death_Change(Death_changeid, Hand_changeid);
						// dialog.dismiss();
						
						removeDialog(id);
						break;
					case R.id.handcardlist2:
						Hand_changeid = 1;
						Death_Change(Death_changeid, Hand_changeid);
						// dialog.dismiss();
						removeDialog(id);
						break;
					case R.id.handcardlist3:
						Hand_changeid = 2;
						Death_Change(Death_changeid, Hand_changeid);
						// dialog.dismiss();
						removeDialog(id);
						break;
					case R.id.handcardlist4:
						Hand_changeid = 3;
						Death_Change(Death_changeid, Hand_changeid);
						// dialog.dismiss();
						removeDialog(id);
						break;
					case R.id.handcardlist5:
						Hand_changeid = 4;
						Death_Change(Death_changeid, Hand_changeid);
						// dialog.dismiss();
						removeDialog(id);
						break;
					case R.id.handcardlist6:
						Hand_changeid = 5;
						Death_Change(Death_changeid, Hand_changeid);
						// dialog.dismiss();
						removeDialog(id);
						break;
					case R.id.handcardlist7:
						Hand_changeid = 6;
						Death_Change(Death_changeid, Hand_changeid);
						// dialog.dismiss();
						removeDialog(id);
						break;
					default:
						break;
					}
				}
			};

			Handler handler = new Handler();
			handler.post(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub

					for (int i = 0; i < handsCard.size(); i++) {
						if (handsCard.get(i).mhp <= 0) {
							// Log.d(TAG, "handButton INVISIBLE : " + i);
							handButton[i].setVisibility(View.GONE);
						} else {
							// Log.d(TAG, "handButton setBackground : " + i );
							handButton[i].setImageResource(UserimageNum[i + 3]);
							handButton[i].setOnClickListener(click);
						}
					}

				}
			});
			dialog.getWindow().setGravity(Gravity.BOTTOM);
			dialog.setTitle("Change Card");
		}

		return dialog;

	}
	public void magicEffect(int magicNum){
		JSONObject jobj = new JSONObject();
		try {
			jobj.put("userID", LoginManager.id);
			jobj.put("magic", magicNum);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		GameManager.socket.emit("magic", jobj);
		
		Handler handler = new Handler();
		Handler handler1 = new Handler();
		switch (magicNum) {
		case CardInterface.GO_MAGIC1:
			
			
			handler.post(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					m_magicImg = (ImageView)findViewById(R.id.magicImg);
					m_magicImg.setImageResource(R.drawable.magic1);
					m_magicImg.setVisibility(View.VISIBLE);
				}
			});
			
			SM.play("card_9_magic");
			
			handler1.postDelayed(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					m_magicImg.setVisibility(View.INVISIBLE);
				}
			}, 2000);
			if(!m1.getText().equals("")){
			attackCard.get(0).SetmPower(attackCard.get(0).GetmPower()+5);
			m1.setText("공: "+attackCard.get(0).mPower + " 방: "+attackCard.get(0).mhp);
			
			}
			if(!m2.getText().equals("")){
				attackCard.get(1).SetmPower(attackCard.get(1).GetmPower()+5);
				m2.setText("공: "+attackCard.get(1).mPower + " 방: "+attackCard.get(1).mhp);				
			}
			if(!m3.getText().equals("")){
				attackCard.get(2).SetmPower(attackCard.get(2).GetmPower()+5);
				m3.setText("공: "+attackCard.get(2).mPower +" 방: "+attackCard.get(2).mhp);				
			}
			
			
			
			
			break;
		case CardInterface.BACK_MAGIC1:
			handler.post(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					m_magicImg = (ImageView)findViewById(R.id.magicImg);
					m_magicImg.setImageResource(R.drawable.magic2);
					m_magicImg.setVisibility(View.VISIBLE);
				}
			});
			
			SM.play("card_19_magic");
			
			handler1.postDelayed(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					m_magicImg.setVisibility(View.INVISIBLE);
				}
			}, 2000);
			if(!m1.getText().equals("")){
				attackCard.get(0).SetHp(attackCard.get(0).GetHp()+10);
				m1.setText("공: "+attackCard.get(0).mPower + " 방: "+attackCard.get(0).mhp);
			
				
				}
				if(!m2.getText().equals("")){
					attackCard.get(1).SetHp(attackCard.get(1).GetHp()+10);
					m2.setText("공: "+attackCard.get(1).mPower + " 방: "+attackCard.get(1).mhp);
			
					
				}
				if(!m3.getText().equals("")){
					attackCard.get(2).SetHp(attackCard.get(2).GetHp()+10);
					m3.setText("공: "+attackCard.get(2).mPower + " 방: "+attackCard.get(2).mhp);
			
					
				}
			
				
			
			break;
		case CardInterface.SIN_MAGIC1:
			handler.post(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					m_magicImg = (ImageView)findViewById(R.id.magicImg);
					m_magicImg.setImageResource(R.drawable.magic3);
					m_magicImg.setVisibility(View.VISIBLE);
				}
			});
			
			SM.play("card_29_magic");
			
			handler1.postDelayed(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					m_magicImg.setVisibility(View.INVISIBLE);
				}
			}, 2000);
		
			if(!e1.getText().equals("")){
				String str = e1.getText().toString();
				String Arr[] = str.split(":");
				for(int i = 0; i < Arr.length; i++)
					System.out.println(Arr[i]);
				
				String Arr2[] = Arr[1].split(" ");
				int attack = Integer.parseInt(Arr2[0]);
				int depense = Integer.parseInt(Arr[2]);

				e1.setText("공:" + depense/2 + " 방:" + attack/2);
			}
			
			if(!e2.getText().equals("")){
				String str = e2.getText().toString();
				String Arr[] = str.split(":");
				for(int i = 0; i < Arr.length; i++)
					System.out.println(Arr[i]);
				
				String Arr2[] = Arr[1].split(" ");
				int attack = Integer.parseInt(Arr2[0]);
				int depense = Integer.parseInt(Arr[2]);

				e2.setText("공:" + depense/2 + " 방:" +  attack/2);
			}
			
			if(!e3.getText().equals("")){
				String str = e3.getText().toString();
				String Arr[] = str.split(":");
				for(int i = 0; i < Arr.length; i++)
					System.out.println(Arr[i]);
				
				String Arr2[] = Arr[1].split(" ");
				int attack = Integer.parseInt(Arr2[0]);
				int depense = Integer.parseInt(Arr[2]);

				e3.setText("공:" + depense/2 + " 방:" +  attack/2);
			}
			
				
			
			break;
			
	


		default:
			break;
		}
	}
	public void EnemymagicEffect(int num){
		Handler handler = new Handler();
		Handler handler1 = new Handler();
		switch (num) {
		case CardInterface.GO_MAGIC1:
			
			//on
			handler.post(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					m_magicImg = (ImageView)findViewById(R.id.magicImg);
					m_magicImg.setImageResource(R.drawable.magic1);
					m_magicImg.setVisibility(View.VISIBLE);
					
				}
			});
			
			SM.play("card_9_magic");
			
			//off
			handler1.postDelayed(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					m_magicImg.setVisibility(View.INVISIBLE);


				}
			}, 2000);
			
			if(!e1.getText().equals("")){
				String str = e1.getText().toString();
				String Arr[] = str.split(":");
				for(int i = 0; i < Arr.length; i++)
					System.out.println(Arr[i]);
				
				String Arr2[] = Arr[1].split(" ");
				int attack = Integer.parseInt(Arr2[0]);
				int depense = Integer.parseInt(Arr[2]);

				e1.setText("공:" + (attack+5) + " 방:" + depense);
			}
			
			if(!e2.getText().equals("")){
				String str = e2.getText().toString();
				String Arr[] = str.split(":");
				for(int i = 0; i < Arr.length; i++)
					System.out.println(Arr[i]);
				
				String Arr2[] = Arr[1].split(" ");
				int attack = Integer.parseInt(Arr2[0]);
				int depense = Integer.parseInt(Arr[2]);

				e2.setText("공:" + (attack+5) + " 방:" + depense);
			}
			
			if(!e3.getText().equals("")){
				String str = e3.getText().toString();
				String Arr[] = str.split(":");
				for(int i = 0; i < Arr.length; i++)
					System.out.println(Arr[i]);
				
				String Arr2[] = Arr[1].split(" ");
				int attack = Integer.parseInt(Arr2[0]);
				int depense = Integer.parseInt(Arr[2]);

				e3.setText("공:" + (attack+5) + " 방:" + depense);
			}
			
			break;
		case CardInterface.BACK_MAGIC1:
			handler.post(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					m_magicImg = (ImageView)findViewById(R.id.magicImg);
					m_magicImg.setImageResource(R.drawable.magic2);
					m_magicImg.setVisibility(View.VISIBLE);
				}
			});
			
			SM.play("card_19_magic");
			
			handler1.postDelayed(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					m_magicImg.setVisibility(View.INVISIBLE);


				}
			}, 2000);
			if(!e1.getText().equals("")){
				String str = e1.getText().toString();
				String Arr[] = str.split(":");
				for(int i = 0; i < Arr.length; i++)
					System.out.println(Arr[i]);
				
				String Arr2[] = Arr[1].split(" ");
				int attack = Integer.parseInt(Arr2[0]);
				int depense = Integer.parseInt(Arr[2]);

				e1.setText("공:" + attack + " 방:" + (depense+10));
			}
			
			if(!e2.getText().equals("")){
				String str = e2.getText().toString();
				String Arr[] = str.split(":");
				for(int i = 0; i < Arr.length; i++)
					System.out.println(Arr[i]);
				
				String Arr2[] = Arr[1].split(" ");
				int attack = Integer.parseInt(Arr2[0]);
				int depense = Integer.parseInt(Arr[2]);

				e2.setText("공:" + attack + " 방:" + (depense+10));
			}
			
			if(!e3.getText().equals("")){
				String str = e3.getText().toString();
				String Arr[] = str.split(":");
				for(int i = 0; i < Arr.length; i++)
					System.out.println(Arr[i]);
				
				String Arr2[] = Arr[1].split(" ");
				int attack = Integer.parseInt(Arr2[0]);
				int depense = Integer.parseInt(Arr[2]);

				e3.setText("공:" + attack + " 방:" + (depense+10));
			}
			
			break;
		case CardInterface.SIN_MAGIC1:
			handler.post(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					m_magicImg = (ImageView)findViewById(R.id.magicImg);
					m_magicImg.setImageResource(R.drawable.magic3);
					m_magicImg.setVisibility(View.VISIBLE);
				}
			});
			
			SM.play("card_29_magic");
			
			handler1.postDelayed(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					m_magicImg.setVisibility(View.INVISIBLE);


				}
			}, 2000);
			if(!m1.getText().equals("")){
				int hp = attackCard.get(0).GetHp();
				
				attackCard.get(0).SetHp(attackCard.get(0).GetmPower()/2);
				attackCard.get(0).SetmPower(hp/2);
				m1.setText("공: "+attackCard.get(0).mPower + " 방: "+attackCard.get(0).mhp);
			
			}
			if(!m2.getText().equals("")){
				int hp1 = attackCard.get(1).GetHp();
				attackCard.get(1).SetHp(attackCard.get(1).GetmPower()/2);
				attackCard.get(1).SetmPower(hp1/2);
				m2.setText("공: "+attackCard.get(1).mPower + " 방: "+attackCard.get(1).mhp);
			}
			
			if(!m3.getText().equals("")){
				int hp2 = attackCard.get(2).GetHp();
				
				attackCard.get(2).SetHp(attackCard.get(2).GetmPower()/2);
				attackCard.get(2).SetmPower(hp2/2);
				m3.setText("공: "+attackCard.get(2).mPower + " 방: "+attackCard.get(2).mhp);
				
			}
			break;
			default :
				break;
		}
		
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	public void recycle(){
		Log.d("recycle","recycle" );
		recycleBitmap1(fightmycard_1);
		recycleBitmap1(fightmycard_2);
		recycleBitmap1(fightmycard_3);
		recycleBitmap1(fightyoucard_1);
		recycleBitmap1(fightyoucard_2);
		recycleBitmap1(fightyoucard_3);
		recycleBitmap1(e_handcard[0]);
		recycleBitmap1(e_handcard[1]);
		recycleBitmap1(e_handcard[2]);
		recycleBitmap1(e_handcard[3]);
		recycleBitmap1(e_handcard[4]);
		recycleBitmap1(e_handcard[5]);
		recycleBitmap1(e_handcard[6]);
		
		
	}
	

	private static void recycleBitmap(ImageView iv) {        
			Drawable d = iv.getDrawable();      
			if (d instanceof BitmapDrawable) {           
				Bitmap b = ((BitmapDrawable)d).getBitmap();       
				b.recycle();        
				} 
		d.setCallback(null);   
		}
	private static void recycleBitmap1(Button iv) {        
		Drawable d = iv.getBackground();      
		if (d instanceof BitmapDrawable) {           
			Bitmap b = ((BitmapDrawable)d).getBitmap();       
			b.recycle();        
			} 
	d.setCallback(null);   
	}


}