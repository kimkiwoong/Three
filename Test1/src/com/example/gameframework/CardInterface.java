package com.example.gameframework;

import com.example.test1.R;

import android.R.drawable;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public  class CardInterface {
	 public String mName;
	 public String mID;//ī���ȣ
	 public int mPower;//ī�� �Ŀ�
	 public int mnara;
	 public int mhp;//ī�� ��
	 public int mCState;//ī�� ����
	 public boolean mMagic;// ���� ���� ����
	 public String mMagicName;

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
	public void SetMagicName(String magicName){
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

