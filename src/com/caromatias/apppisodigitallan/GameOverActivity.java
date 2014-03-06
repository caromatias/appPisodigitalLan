package com.caromatias.apppisodigitallan;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.VideoView;

public class GameOverActivity extends Activity {
	
	private VideoView videoGameOver;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_over);
		Bundle bundle = getIntent().getExtras();
		switch (bundle.getInt("game")) {
		case 1:
			// INICIO VIDEO GAMEOVER //
			videoGameOver = (VideoView) findViewById(R.id.video_game_over);
			videoGameOver
					.setVideoPath("android.resource://com.caromatias.apppisodigitallan/"
							+ R.raw.game_over);
			videoGameOver.start();
			// /////////////////////////////////////
			videoGameOver.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
			    public void onCompletion(MediaPlayer mp) {
			    	Intent act = new Intent(GameOverActivity.this,	LaminaUnoActivity.class);
					startActivity(act);
					overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
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
			// /////////////////////////////////////
			videoGameOver.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
			    public void onCompletion(MediaPlayer mp) {
			    	Intent act = new Intent(GameOverActivity.this,	LaminaDosActivity.class);
					startActivity(act);
					overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
			    }
			});
			break;
		case 3:
			// INICIO VIDEO GAMEOVER //
			videoGameOver = (VideoView) findViewById(R.id.video_game_over);
			videoGameOver
					.setVideoPath("android.resource://com.caromatias.apppisodigitallan/"
							+ R.raw.fail_prueba);
			videoGameOver.start();
			// /////////////////////////////////////
			videoGameOver.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
			    public void onCompletion(MediaPlayer mp) {
			    	Intent act = new Intent(GameOverActivity.this,	LaminaDosActivity.class);
					startActivity(act);
					overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
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

}
