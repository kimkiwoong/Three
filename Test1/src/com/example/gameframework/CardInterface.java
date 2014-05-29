package com.example.gameframework;

import com.example.test1.R;

import android.R.drawable;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public  class CardInterface {
	public static final int CC_GOGURU			= 	0;
	public static final int CC_BACKJAE			= 	1;
	public static final int CC_SINRA			= 	2;
	
	
	
	//ī�� ����
	public static final int CC_NORMAL 			=	0;//�Ϲ�ī��
	public static final int CC_RARE				=	1;//����ī��
	public static final int CC_UNIQUE			= 	2;//����ũī��
	//Card state
	public static final int CS_SHOW				=	0;//���»���
	public static final int CS_CLOSE			=	1;// ������ ����
	public static final int CS_PLAYEROPEN		= 	2;//�÷��̾��� �������� �������� ����
	public static final int CS_DIE				=	3; //����ī��
	public static final int CS_LIVE				= 	4;//����ִ�ī��
	////���� CardInfo
	public static final int GO_NORMAL_POJOL		= 	1;//���� ����
	public static final int GO_NORMAL_POJOL2	=	2;//���� ����2
	public static final int	GO_NORMAL_POJOL3	=	3;//���� ����3
	public static final int GO_NORMAL_XX		=	4;//���� ??
	public static final int GO_NORMAL_XXX		=	5;//���� ??
	public static final int GO_NORMAL_JOISUNIN	=	6;//���� ���Ǽ���
	public static final int GO_RARE_YUNGAE		=	7;//���� �����ҹ�
	public static final int GO_RARE_ULJIMUN		=	8;//���� ��������
	public static final int GO_RARE_GOJUMONG	=	9;//���� ���ָ�
	public static final int GO_SP_GWANGKING		= 	10;//���� ��������
	//���� CardInfo
	public static final int BACK_NORMAL_POJOL	= 	51;//���� ����
	public static final int BACK_NORMAL_POJOL2	=	52;//����  ����2
	public static final int BACK_NORMAL_POJOL3	=	53;//����  ����3
	public static final int BACK_NORMAL_X		=	54;//����  ??
	public static final int BACK_NORMAL_XX		=	55;//����  ??
	public static final int BACK_NORMAL_SSAU	=	56;//���� �ο�ƺ�
	public static final int BACK_RARE_ONJO		=	57;//���� ������
	public static final int BACK_RARE_GOON		=	58;//���� ���ʰ��
	public static final int BACK_RARE_UJWANG	=	59;//���� ���ڿ�
	public static final int BACK_RARE_GAEBAK	= 	60;//���� ���
	/////�Ŷ� CardInfo
	public static final int SIN_NORMAL_POJOL	= 	101;//�Ŷ� ����
	public static final int SIN_NORMAL_POJOL2	=	102;//�Ŷ� ����2
	public static final int SIN_NORMAL_POJOL3	=	103;//�Ŷ� ����3
	public static final int SIN_NORMAL_X		=	104;//�Ŷ� XX
	public static final int SIN_NORMAL_XX		=	105;//�Ŷ� ??
	public static final int SIN_NORMAL_HWALANG	=	106;//�Ŷ� ȭ��
	public static final int SIN_RARE_WANHYO		=	107;//�Ŷ� ��ȿ
	public static final int SIN_RARE_JANGBOGO	=	108;//�Ŷ� �庸��
	public static final int SIN_RARE_KIMCHCH	=	109;//�Ŷ� ������
	public static final int SIN_SP_KIMYOUSIN	= 	110;//�Ŷ� ������
	
	public static final int GO_MAGIC1 = 1000;
	public static final int SIN_MAGIC1 = 2000;
	public static final int BACK_MAGIC1 = 3000;
	
	 public String mName;//
	 public String mID;//ī���ȣ
	 public int mPower;//ī�� �Ŀ�
	 public int mnara;
	 public int mhp;//ī�� ��
	 public int mCState;//ī�� ����
	 public boolean mMagic;// ���� ���� ����
	 public int mMagicName;

	//public Bitmap mImage;
	
	 public  String GetmID(){
		 return this.mID;
	 }

	public int GetNara(){/////ī���ȣ ��������//////
		
		return this.mnara;
	}
	public void SetNara(int nara){/////������//////
		this.mnara = nara;
		
	}
	
	public void SetHp(int hp){/////HP����//////
		this.mhp = hp;
		
	}
	public int GetHp(){/////HP��������//////
		return this.mhp;
		
	}
	public void SetmPower(int power){////Power����////
		this.mPower = power;
		
		
	}
	public int GetmPower(){////Power��������////
		return this.mPower;
		
		
	}
	public void SetId(String Id){////Power����////
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

