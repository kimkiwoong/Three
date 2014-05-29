package com.example.gameframework;

import com.example.test1.R;

import android.R.drawable;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public  class CardInterface {
	public static final int CC_GOGURU			= 	0;
	public static final int CC_BACKJAE			= 	1;
	public static final int CC_SINRA			= 	2;
	
	
	
	//카드 레벨
	public static final int CC_NORMAL 			=	0;//일반카드
	public static final int CC_RARE				=	1;//레어카드
	public static final int CC_UNIQUE			= 	2;//유니크카드
	//Card state
	public static final int CS_SHOW				=	0;//오픈상태
	public static final int CS_CLOSE			=	1;// 뒤집힌 상태
	public static final int CS_PLAYEROPEN		= 	2;//플레이어의 선택으로 보여지는 상태
	public static final int CS_DIE				=	3; //죽은카드
	public static final int CS_LIVE				= 	4;//살아있는카드
	////고구려 CardInfo
	public static final int GO_NORMAL_POJOL		= 	1;//고구려 포졸
	public static final int GO_NORMAL_POJOL2	=	2;//고구려 포졸2
	public static final int	GO_NORMAL_POJOL3	=	3;//고구려 포졸3
	public static final int GO_NORMAL_XX		=	4;//고구려 ??
	public static final int GO_NORMAL_XXX		=	5;//고구려 ??
	public static final int GO_NORMAL_JOISUNIN	=	6;//고구려 조의선인
	public static final int GO_RARE_YUNGAE		=	7;//고구려 연개소문
	public static final int GO_RARE_ULJIMUN		=	8;//고구려 을지문덕
	public static final int GO_RARE_GOJUMONG	=	9;//고구려 고주몽
	public static final int GO_SP_GWANGKING		= 	10;//고구려 광개토대왕
	//백제 CardInfo
	public static final int BACK_NORMAL_POJOL	= 	51;//백제 포졸
	public static final int BACK_NORMAL_POJOL2	=	52;//백제  포졸2
	public static final int BACK_NORMAL_POJOL3	=	53;//백제  포졸3
	public static final int BACK_NORMAL_X		=	54;//백제  ??
	public static final int BACK_NORMAL_XX		=	55;//백제  ??
	public static final int BACK_NORMAL_SSAU	=	56;//백제 싸울아비
	public static final int BACK_RARE_ONJO		=	57;//백제 온조왕
	public static final int BACK_RARE_GOON		=	58;//백제 근초고왕
	public static final int BACK_RARE_UJWANG	=	59;//백제 의자왕
	public static final int BACK_RARE_GAEBAK	= 	60;//백제 계백
	/////신라 CardInfo
	public static final int SIN_NORMAL_POJOL	= 	101;//신라 포졸
	public static final int SIN_NORMAL_POJOL2	=	102;//신라 포졸2
	public static final int SIN_NORMAL_POJOL3	=	103;//신라 포졸3
	public static final int SIN_NORMAL_X		=	104;//신라 XX
	public static final int SIN_NORMAL_XX		=	105;//신라 ??
	public static final int SIN_NORMAL_HWALANG	=	106;//신라 화랑
	public static final int SIN_RARE_WANHYO		=	107;//신라 원효
	public static final int SIN_RARE_JANGBOGO	=	108;//신라 장보고
	public static final int SIN_RARE_KIMCHCH	=	109;//신라 김춘추
	public static final int SIN_SP_KIMYOUSIN	= 	110;//신라 김유신
	
	public static final int GO_MAGIC1 = 1000;
	public static final int SIN_MAGIC1 = 2000;
	public static final int BACK_MAGIC1 = 3000;
	
	 public String mName;//
	 public String mID;//카드번호
	 public int mPower;//카드 파워
	 public int mnara;
	 public int mhp;//카드 피
	 public int mCState;//카드 상태
	 public boolean mMagic;// 마법 유무 상태
	 public int mMagicName;

	//public Bitmap mImage;
	
	 public  String GetmID(){
		 return this.mID;
	 }

	public int GetNara(){/////카드번호 가져오기//////
		
		return this.mnara;
	}
	public void SetNara(int nara){/////나라설정//////
		this.mnara = nara;
		
	}
	
	public void SetHp(int hp){/////HP설정//////
		this.mhp = hp;
		
	}
	public int GetHp(){/////HP가져오기//////
		return this.mhp;
		
	}
	public void SetmPower(int power){////Power설정////
		this.mPower = power;
		
		
	}
	public int GetmPower(){////Power가져오기////
		return this.mPower;
		
		
	}
	public void SetId(String Id){////Power설정////
		this.mID = Id;
		
		
	}
	public void SetMagic(Boolean magic){
		this.mMagic = magic;
		
		
	}
	public void SetMagicName(int magicName){
		this.mMagicName = magicName;
		
		
	}
	public int GetCState(){
		
		return this.mCState;
		
	}
	public void SetCState(int state){
		
		this.mCState= state;
		
	}
	
}
	/*
	public void Image(Bitmap image){
		//image=
		this.mImage = AppManager.getInstance().getBitmap(res);
	}
	*/

