package com.example.test1;

import com.example.gameframework.CardFactory;

import com.example.gameframework.CardInterface;

public class Player {
	int nara;

	String[] myCardname;
	CardInterface[] myCard;

	int remain_Card;
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
		this.nara=1;
	}

	public void setnara(int na) {
		this.nara = na;
	}

	public Boolean setReady() {
		return true;
	}

	public void initsetcard() {
		for (int i = 0; i < 10; i++) {
			myCardname[i] = "card_" + i;
			remain_Card = i;

		}
		if (this.nara == 1) {
			myCard[0] = CardFactory.create("GO_NORMAL_POJOL");
			myCard[1] = CardFactory.create("GO_NORMAL_POJOL1");
			myCard[2] = CardFactory.create("GO_NORMAL_POJOL2");
			myCard[3] = CardFactory.create("GO_NORMAL_JOISUNIN");
			myCard[4] = CardFactory.create("GO_NORMAL_XX");
			myCard[5] = CardFactory.create("GO_NORMAL_XXX");
			myCard[6] = CardFactory.create("GO_RARE_ULJIMUN");
			myCard[7] = CardFactory.create("GO_RARE_GOJUMONG");
			myCard[8] = CardFactory.create("GO_RARE_YUNGAE");
			myCard[9] = CardFactory.create("GO_SP_GWANGKING");

		} else if (this.nara == 2) {
			myCard[0] = CardFactory.create("BACK_NORMAL_POJOL");
			myCard[1] = CardFactory.create("BACK_NORMAL_POJOL1");
			myCard[2] = CardFactory.create("BACK_NORMAL_POJOL2");
			myCard[3] = CardFactory.create("BACK_NORMAL_SSAU");
			myCard[4] = CardFactory.create("BACK_NORMAL_XX");
			myCard[5] = CardFactory.create("BACK_RARE_ONJO");
			myCard[6] = CardFactory.create("BACK_RARE_GOON");
			myCard[7] = CardFactory.create("BACK_RARE_ONJO");
			myCard[8] = CardFactory.create("BACK_RARE_UJWANG");
			myCard[9] = CardFactory.create("BACK_SP_GAEBAK");
		} else if (this.nara == 3) {
			myCard[0] = CardFactory.create("SIN_NORMAL_POJOL");
			myCard[1] = CardFactory.create("SIN_NORMAL_POJOL1");
			myCard[2] = CardFactory.create("SIN_NORMAL_POJOL2");
			myCard[3] = CardFactory.create("SIN_NORMAL_HWALANG");
			myCard[4] = CardFactory.create("SIN_NORMAL_X");
			myCard[5] = CardFactory.create("SIN_NORMAL_XX");
			myCard[6] = CardFactory.create("SIN_RARE_KIMCHCH");
			myCard[7] = CardFactory.create("SIN_RARE_GOJUMONG");
			myCard[8] = CardFactory.create("SIN_RARE_WANHYO");
			myCard[9] = CardFactory.create("SIN_SP_KIMYOUSIN");
		}
	}

	public void setcard() {
	
		for (int j = 0; j < 10; j++) {
			myCard[j] = CardFactory.create(myCardname[j]);
		}

	}

	public void setRemain_Card() {
		remain_Card -= 1;
	}

	public int getRemain_Card() {
		return remain_Card;
	}

}
