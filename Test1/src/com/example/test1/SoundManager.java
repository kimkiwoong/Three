package com.example.test1;

import java.util.HashMap;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.util.Log;

public class SoundManager {
	private SoundPool m_soundPool;
	private MediaPlayer m_MediaPlayer;
	private MediaPlayer m_Game_MediaPlayer;
	private HashMap m_soundpoolmap;
	private AudioManager m_AudioManager;
	private Context m_Activity;
	private static SoundManager m_instance;
	public static int progressnum=0;
public void Init(Context context){
	m_soundPool = new SoundPool(4, AudioManager.STREAM_MUSIC, 0);
	m_MediaPlayer = new MediaPlayer();
	m_soundPool.setOnLoadCompleteListener(mListener);
	m_soundpoolmap = new HashMap();
	m_AudioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
	m_Activity = context;
}
SoundPool.OnLoadCompleteListener mListener = new SoundPool.OnLoadCompleteListener() {
	
	@Override
	public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
		// TODO Auto-generated method stub
		if(status==0){
			Log.d("Sound", " "+sampleId);
			progressnum+=1;
			loadingActivity.progressBar1.setProgress(progressnum);
			loadingActivity.textview2.setText(progressnum+"%");
			if(progressnum==100){
				loadingActivity.textview1.setText("게임을 시작합니다.");
			}
		}
	}
};
public void addSound(String name, int soundID){
	
	int id = m_soundPool.load(m_Activity, soundID, 1);
	m_soundpoolmap.put(name, id);
	
	
}
public void backgroundAddSound(int id){
	m_MediaPlayer=MediaPlayer.create(m_Activity, id);
	
	
}
public void backgroundPlaySound(){
	
	m_MediaPlayer.start();
	m_MediaPlayer.setLooping(true);
}
public void backgroundStopSound(){
	m_MediaPlayer.pause();
	m_MediaPlayer.setLooping(false);
}
public void gamebackgroundAddSound(int id){
	m_Game_MediaPlayer= MediaPlayer.create(m_Activity, id);
}
public void gamebackgroundPlaySound(){
	
	m_Game_MediaPlayer.start();
	m_Game_MediaPlayer.setLooping(true);
}
	public void gamebackgroundStopSound(){
		m_Game_MediaPlayer.pause();
		m_Game_MediaPlayer.setLooping(false);
	}	
	
public void gamebackgroundVolume(float leftvolume, float rightvolume){
	m_Game_MediaPlayer.setVolume(leftvolume,rightvolume);
}
public void play(String name){
	float streamVolume = m_AudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
	streamVolume = streamVolume/m_AudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
	m_soundPool.play((Integer) m_soundpoolmap.get(name), streamVolume, streamVolume, 1, 0, 1f);
}
public void PlayLooped(String name){
	float streamVolume = m_AudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
	streamVolume = streamVolume/m_AudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
	m_soundPool.play((Integer) m_soundpoolmap.get(name), streamVolume, streamVolume, 1, -1, 1f);
}
public static SoundManager getInstance(){
	if(m_instance==null){
		m_instance = new SoundManager();
		
	}
	return m_instance;
	}
public void Initsound(){
	this.addSound("touch", R.raw.touchs);
	this.addSound("gamestart", R.raw.gamestarts);
	this.backgroundAddSound(R.raw.mainsound);
	this.gamebackgroundAddSound(R.raw.gamebgm);
	this.addSound("card_0_attack",R.raw.card_0_attack);
	this.addSound("card_0_come",R.raw.card_0_come);
	this.addSound("card_0_die",R.raw.card_0_die);
	this.addSound("card_1_attack",R.raw.card_1_attack);
	this.addSound("card_1_come",R.raw.card_1_come);
	this.addSound("card_1_die",R.raw.card_1_die);
	this.addSound("card_2_attack",R.raw.card_2_attack);
	this.addSound("card_2_come",R.raw.card_2_come);
	this.addSound("card_2_die",R.raw.card_2_die);
	this.addSound("card_3_attack",R.raw.card_3_attack);
	this.addSound("card_3_come",R.raw.card_3_come);
	this.addSound("card_3_die",R.raw.card_3_die);
	this.addSound("card_4_attack",R.raw.card_4_attack);
	this.addSound("card_4_come",R.raw.card_4_come);
	this.addSound("card_4_die",R.raw.card_4_die);
	this.addSound("card_5_attack",R.raw.card_5_attack);
	this.addSound("card_5_come",R.raw.card_5_come);
	this.addSound("card_5_die",R.raw.card_5_die);
	this.addSound("card_6_attack",R.raw.card_6_attack);
	this.addSound("card_6_come",R.raw.card_6_come);
	this.addSound("card_6_die",R.raw.card_6_die);
	this.addSound("card_7_attack",R.raw.card_7_attack);
	this.addSound("card_7_come",R.raw.card_7_come);
	this.addSound("card_7_die",R.raw.card_7_die);
	this.addSound("card_8_attack",R.raw.card_8_attack);
	this.addSound("card_8_come",R.raw.card_8_come);
	this.addSound("card_8_die",R.raw.card_8_die);
	this.addSound("card_9_attack",R.raw.card_9_attack);
	this.addSound("card_9_come",R.raw.card_9_come);
	this.addSound("card_9_die",R.raw.card_9_die);
	this.addSound("card_9_magic", R.raw.card_9_magic);
	this.addSound("card_10_attack",R.raw.card_10_attack);
	this.addSound("card_10_come",R.raw.card_10_come);
	this.addSound("card_10_die",R.raw.card_10_die);
	this.addSound("card_11_attack",R.raw.card_11_attack);
	this.addSound("card_11_come",R.raw.card_11_come);
	this.addSound("card_11_die",R.raw.card_11_die);
	this.addSound("card_12_attack",R.raw.card_12_attack);
	this.addSound("card_12_come",R.raw.card_12_come);
	this.addSound("card_12_die",R.raw.card_12_die);
	this.addSound("card_13_attack",R.raw.card_13_attack);
	this.addSound("card_13_come",R.raw.card_13_come);
	this.addSound("card_13_die",R.raw.card_13_die);
	this.addSound("card_14_attack",R.raw.card_14_attack);
	this.addSound("card_14_come",R.raw.card_14_come);
	this.addSound("card_14_die",R.raw.card_14_die);
	this.addSound("card_15_attack",R.raw.card_15_attack);
	this.addSound("card_15_come",R.raw.card_15_come);
	this.addSound("card_15_die",R.raw.card_15_die);
	this.addSound("card_16_attack",R.raw.card_16_attack);
	this.addSound("card_16_come",R.raw.card_16_come);
	this.addSound("card_16_die",R.raw.card_16_die);
	this.addSound("card_17_attack",R.raw.card_17_attack);
	this.addSound("card_17_come",R.raw.card_17_come);
	this.addSound("card_17_die",R.raw.card_17_die);
	this.addSound("card_18_attack",R.raw.card_18_attack);
	this.addSound("card_18_come",R.raw.card_18_come);
	this.addSound("card_18_die",R.raw.card_18_die);
	this.addSound("card_19_attack",R.raw.card_19_attack);
	this.addSound("card_19_come",R.raw.card_19_come);
	this.addSound("card_19_die",R.raw.card_19_die);
	this.addSound("card_19_magic", R.raw.card_19_magic);
	this.addSound("card_20_attack",R.raw.card_20_attack);
	this.addSound("card_20_come",R.raw.card_20_come);
	this.addSound("card_20_die",R.raw.card_20_die);
	this.addSound("card_21_attack",R.raw.card_21_attack);
	this.addSound("card_21_come",R.raw.card_21_come);
	this.addSound("card_21_die",R.raw.card_21_die);
	this.addSound("card_22_attack",R.raw.card_22_attack);
	this.addSound("card_22_come",R.raw.card_22_come);
	this.addSound("card_22_die",R.raw.card_22_die);
	this.addSound("card_23_attack",R.raw.card_23_attack);
	this.addSound("card_23_come",R.raw.card_23_come);
	this.addSound("card_23_die",R.raw.card_23_die);
	this.addSound("card_24_attack",R.raw.card_24_attack);
	this.addSound("card_24_come",R.raw.card_24_come);
	this.addSound("card_24_die",R.raw.card_24_die);
	this.addSound("card_25_attack",R.raw.card_25_attack);
	this.addSound("card_25_come",R.raw.card_25_come);
	this.addSound("card_25_die",R.raw.card_25_die);
	this.addSound("card_26_attack",R.raw.card_26_attack);
	this.addSound("card_26_come",R.raw.card_26_come);
	this.addSound("card_26_die",R.raw.card_26_die);
	this.addSound("card_27_attack",R.raw.card_27_attack);
	this.addSound("card_27_come",R.raw.card_27_come);
	this.addSound("card_27_die",R.raw.card_27_die);
	this.addSound("card_28_attack",R.raw.card_28_attack);
	this.addSound("card_28_come",R.raw.card_28_come);
	this.addSound("card_28_die",R.raw.card_28_die);
	this.addSound("card_29_attack",R.raw.card_29_attack);
	this.addSound("card_29_come",R.raw.card_29_come);
	this.addSound("card_29_die",R.raw.card_29_die);
	this.addSound("card_29_magic", R.raw.card_29_magic);
	this.addSound("card_30_magic", R.raw.card_30_magic);
	this.addSound("card_31_magic", R.raw.card_31_magic);
	this.addSound("card_32_magic", R.raw.card_32_magic);
	this.addSound("card_33_magic", R.raw.card_33_magic);
	this.addSound("card_34_magic", R.raw.card_34_magic);
	
	
}
}
