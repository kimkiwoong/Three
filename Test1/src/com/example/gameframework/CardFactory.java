package com.example.gameframework;


//import android.os.StrictMode.ThreadPolicy.Builder;

public class CardFactory {

	public static CardInterface create(String Nara){
		
		try {
			if(Nara.matches(".*GO.*")){
				
			
			Class c = Class.forName("com.example.test1.Card_GO."+Nara);
			
			try {
				CardInterface c1 = (CardInterface)c.newInstance();
				return c1;
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}
			}else if(Nara.matches(".*BACK.*")){
				Class c = Class.forName("com.example.test1.Card_Back."+Nara);
				
				try {
					CardInterface c1 = (CardInterface)c.newInstance();
					return c1;
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					}
			}else if(Nara.matches(".*SIN.*")){
				Class c = Class.forName("com.example.test1.Card_Sin."+Nara);
				
				try {
					CardInterface c1 = (CardInterface)c.newInstance();
					return c1;
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					}
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
		
}
