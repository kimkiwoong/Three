package com.example.test1;

import com.example.gameframework.CardFactory;

import com.example.gameframework.CardInterface;

public class Player {
	int nara;

	String[] myCardname;
	CardInterface[] myCard;

	private int remain_Card;
	boolean ready;

	public Player() {
		this.myCardname = new String[10];
		this.myCard = new CardInterface[10];
		this.remain_Card = 10;

		// card_id="card_1";
	}

	public Player(int na) {
		this.myCardname = new String[10];
		this.myCard = new CardInterface[10];
		this.remain_Card = 10;
		this.nara=na;
	}

	public void setnara(int na) {
		this.nara = na;
	}

	public Boolean setReady() {
		return true;
	}

	public void initsetcard(int na) {
		if(na==1){
			for (int i = 0; i < 10; i++) {
				myCardname[i] = "card_" + i;
				remain_Card = i;
	
			}
		}else if(na==2){
			for (int i = 0; i < 10; i++) {
				myCardname[i] = "card_" + (i+10);
				remain_Card = i;

			}
		}else if(na==3){
			for (int i = 0; i < 10; i++) {
				myCardname[i] = "card_" + (i+20);
				remain_Card = i;

			}
		}
		if (this.nara == 1) {
			myCard[0] = CardFactory.create("GO_NORMAL_POJOL");
			myCard[1] = CardFactory.create("GO_NORMAL_POJOL1");
			myCard[2] = CardFactory.create("GO_NORMAL_POJOL2");
			myCard[3] = CardFactory.create("GO_NORMAL_JOISUNIN");
			myCard[4] = CardFactory.create("GO_NORMAL_JOISUNIN1");
			myCard[5] = CardFactory.create("GO_NORMAL_JOISUNIN2");
			myCard[6] = CardFactory.create("GO_NORMAL_JOISUNIN3");
			myCard[7] = CardFactory.create("GO_RARE_ULJIMUN");
			myCard[8] = CardFactory.create("GO_RARE_YUNGAE");
			myCard[9] = CardFactory.create("GO_SP_GWANGKING");

		} else if (this.nara == 2) {
			myCard[0] = CardFactory.create("BACK_NORMAL_POJOL");
			myCard[1] = CardFactory.create("BACK_NORMAL_POJOL1");
			myCard[2] = CardFactory.create("BACK_NORMAL_POJOL2");
			myCard[3] = CardFactory.create("BACK_NORMAL_SSAU");
			myCard[4] = CardFactory.create("BACK_NORMAL_SSAU1");
			myCard[5] = CardFactory.create("BACK_NORMAL_SSAU2");
			myCard[6] = CardFactory.create("BACK_NORMAL_SSAU3");
			myCard[7] = CardFactory.create("BACK_RARE_GN");
			myCard[8] = CardFactory.create("BACK_RARE_UJWANG");
			myCard[9] = CardFactory.create("BACK_SP_GAEBAK");
		} else if (this.nara == 3) {
			myCard[0] = CardFactory.create("SIN_NORMAL_POJOL");
			myCard[1] = CardFactory.create("SIN_NORMAL_POJOL1");
			myCard[2] = CardFactory.create("SIN_NORMAL_POJOL2");
			myCard[3] = CardFactory.create("SIN_NORMAL_HWALANG");
			myCard[4] = CardFactory.create("SIN_NORMAL_HWALANG1");
			myCard[5] = CardFactory.create("SIN_NORMAL_HWALANG2");
			myCard[6] = CardFactory.create("SIN_NORMAL_HWALANG3");
			myCard[7] = CardFactory.create("SIN_RARE_KIMCHCH");
			myCard[8] = CardFactory.create("SIN_RARE_KIMYOUSIN");
			myCard[9] = CardFactory.create("SIN_SP_WANHYO");
		}
	}

	public void setcard() {
	
		for (int j = 0; j < 10; j++) {
			myCard[j] = CardFactory.create(myCardname[j]);
		}

	}

	public void setMinusRemain_Card() {
		this.remain_Card -= 1;
	}
	public void setRemain_Card(int num) {
		this.remain_Card = num;
	}
	public int getRemain_Card() {
		return this.remain_Card;
	}

}
