package com.example.gameframework;

import com.example.test1.R;

import android.R.drawable;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public  class CardInterface {
	 public String mName;
	 public String mID;//카드번호
	 public int mPower;//카드 파워
	 public int mnara;
	 public int mhp;//카드 피
	 public int mCState;//카드 상태
	 public boolean mMagic;// 마법 유무 상태
	 public String mMagicName;

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

