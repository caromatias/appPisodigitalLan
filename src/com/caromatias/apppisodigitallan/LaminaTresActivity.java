package com.caromatias.apppisodigitallan;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.VideoView;

public class LaminaTresActivity extends Activity {

	private VideoView videoBackTrivia;
	private VideoView videoBackTriviaB;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lamina_tres);
		
		// INICIO VIDEO DE TRANSICION TRIVIA //
		videoBackTrivia = (VideoView) findViewById(R.id.video_back_trivia);
		videoBackTrivia.setVideoPath("android.resource://com.caromatias.apppisodigitallan/" + R.raw.transicion);
		videoBackTrivia.start();
		final ImageView imgWhite = (ImageView) findViewById(R.id.img_fade_white);
    	final Animation animVideoMain = AnimationUtils.loadAnimation(this,R.anim.fade_in);
		///////////////////////////////////////
		videoBackTrivia.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
		    public void onCompletion(MediaPlayer mp) {
		    	imgWhite.setVisibility(View.VISIBLE);
				imgWhite.setAnimation(animVideoMain);
		    	videoBackTrivia.setVisibility(View.GONE);
		    	videoBackTriviaB.setVideoPath("android.resource://com.caromatias.apppisodigitallan/" + R.raw.back_trivia);
				videoBackTriviaB.start();
				imgWhite.setVisibility(View.GONE);
		    }
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lamina_tres, menu);
		return true;
	}
}
