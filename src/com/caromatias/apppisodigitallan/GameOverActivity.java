package com.caromatias.apppisodigitallan;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.widget.VideoView;

public class GameOverActivity extends Activity {

	private VideoView videoGameOver;
	private MediaPlayer sdBeginds;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_over);
		// ///// AUDIO ////////
		// sdBeginds = MediaPlayer.create(this, R.raw.jazz_dance);
		AudioManager audio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
		int currentVolume = audio.getStreamVolume(AudioManager.STREAM_MUSIC);
		int maxVolume = audio.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		float percent = 0.9f;
		int seventyVolume = (int) (maxVolume * percent);
		audio.setStreamVolume(AudioManager.STREAM_MUSIC, seventyVolume, 0);
		// ///// AUDIO ////////
		// ///// REDUCCION DE AUDIO /////////
		Handler handlerReducUno = new Handler();
		handlerReducUno.postDelayed(new Runnable() {
			@Override
			public void run() {
				AudioManager audio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
				int currentVolume = audio.getStreamVolume(AudioManager.STREAM_MUSIC);
				int maxVolume = audio.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
				float percent = 0.8f;
				int seventyVolume = (int) (maxVolume * percent);
				audio.setStreamVolume(AudioManager.STREAM_MUSIC, seventyVolume, 0);
			}
		}, 7000);
		Handler handlerReducDos = new Handler();
		handlerReducDos.postDelayed(new Runnable() {
			@Override
			public void run() {
				AudioManager audio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
				int currentVolume = audio.getStreamVolume(AudioManager.STREAM_MUSIC);
				int maxVolume = audio.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
				float percent = 0.7f;
				int seventyVolume = (int) (maxVolume * percent);
				audio.setStreamVolume(AudioManager.STREAM_MUSIC, seventyVolume, 0);
			}
		}, 7500);
		Handler handlerReductres = new Handler();
		handlerReductres.postDelayed(new Runnable() {
			@Override
			public void run() {
				AudioManager audio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
				int currentVolume = audio.getStreamVolume(AudioManager.STREAM_MUSIC);
				int maxVolume = audio.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
				float percent = 0.6f;
				int seventyVolume = (int) (maxVolume * percent);
				audio.setStreamVolume(AudioManager.STREAM_MUSIC, seventyVolume, 0);
			}
		}, 8000);
		Handler handlerReducCuatro = new Handler();
		handlerReducCuatro.postDelayed(new Runnable() {
			@Override
			public void run() {
				AudioManager audio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
				int currentVolume = audio.getStreamVolume(AudioManager.STREAM_MUSIC);
				int maxVolume = audio.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
				float percent = 0.5f;
				int seventyVolume = (int) (maxVolume * percent);
				audio.setStreamVolume(AudioManager.STREAM_MUSIC, seventyVolume, 0);
			}
		}, 8500);
		Handler handlerReducCinco = new Handler();
		handlerReducCinco.postDelayed(new Runnable() {
			@Override
			public void run() {
				AudioManager audio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
				int currentVolume = audio.getStreamVolume(AudioManager.STREAM_MUSIC);
				int maxVolume = audio.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
				float percent = 0.4f;
				int seventyVolume = (int) (maxVolume * percent);
				audio.setStreamVolume(AudioManager.STREAM_MUSIC, seventyVolume, 0);
			}
		}, 9000);
		Handler handlerReducSeis = new Handler();
		handlerReducSeis.postDelayed(new Runnable() {
			@Override
			public void run() {
				AudioManager audio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
				int currentVolume = audio.getStreamVolume(AudioManager.STREAM_MUSIC);
				int maxVolume = audio.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
				float percent = 0.3f;
				int seventyVolume = (int) (maxVolume * percent);
				audio.setStreamVolume(AudioManager.STREAM_MUSIC, seventyVolume, 0);
			}
		}, 9500);
		Handler handlerReducSiete = new Handler();
		handlerReducSiete.postDelayed(new Runnable() {
			@Override
			public void run() {
				AudioManager audio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
				int currentVolume = audio.getStreamVolume(AudioManager.STREAM_MUSIC);
				int maxVolume = audio.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
				float percent = 0.2f;
				int seventyVolume = (int) (maxVolume * percent);
				audio.setStreamVolume(AudioManager.STREAM_MUSIC, seventyVolume, 0);
			}
		}, 10000);
		// ///// REDUCCION DE AUDIO /////////
		Bundle bundle = getIntent().getExtras();
		switch (bundle.getInt("game")) {
		case 1:
			// INICIO VIDEO GAMEOVER //
			sdBeginds = MediaPlayer.create(this, R.raw.a_life_begins);
			sdBeginds.start();
			LaminaTresActivity.mpFondo.release();
			videoGameOver = (VideoView) findViewById(R.id.video_game_over);
			videoGameOver
					.setVideoPath("android.resource://com.caromatias.apppisodigitallan/"
							+ R.raw.game_over);
			videoGameOver.start();
			///////////////////////////////////////
			videoGameOver
					.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
						public void onCompletion(MediaPlayer mp) {
							//LaminaTresActivity.mpFondo.stop();
							sdBeginds.release();
							Intent act = new Intent(GameOverActivity.this,
									LaminaUnoActivity.class);
							startActivity(act);
							overridePendingTransition(R.anim.fade_in,
									R.anim.fade_out);
						}
					});
			break;
		case 2:
			// INICIO VIDEO GAMEOVER //
			videoGameOver = (VideoView) findViewById(R.id.video_game_over);
			videoGameOver
					.setVideoPath("android.resource://com.caromatias.apppisodigitallan/"
							+ R.raw.felicitaciones);
			videoGameOver.start();
			///////////////////////////////////////
			videoGameOver
					.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
						public void onCompletion(MediaPlayer mp) {
							LaminaTresActivity.mpFondo.release();
							Intent act = new Intent(GameOverActivity.this,
									LaminaDosActivity.class);
							act.putExtra("isInterface", 1);
							startActivity(act);
							overridePendingTransition(R.anim.fade_in,
									R.anim.fade_out);
						}
					});
			break;
		case 3:
			// INICIO VIDEO GAMEOVER //
			sdBeginds = MediaPlayer.create(this, R.raw.a_life_begins);
			sdBeginds.start();
			videoGameOver = (VideoView) findViewById(R.id.video_game_over);
			videoGameOver
					.setVideoPath("android.resource://com.caromatias.apppisodigitallan/"
							+ R.raw.fail_prueba);
			videoGameOver.start();
			///////////////////////////////////////
			videoGameOver
					.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
						public void onCompletion(MediaPlayer mp) {
							//LaminaTresActivity.mpFondo.stop();
							sdBeginds.release();
							Intent act = new Intent(GameOverActivity.this,
									LaminaUnoActivity.class);
							startActivity(act);
							overridePendingTransition(R.anim.fade_in,
									R.anim.fade_out);
						}
					});
			break;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game_over, menu);
		return true;
	}

	@Override
	public void onBackPressed() {
		return;
	}

}
