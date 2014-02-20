package com.caromatias.apppisodigitallan;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.view.Display;
import android.view.Menu;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.VideoView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final VideoView videoView = (VideoView) findViewById(R.id.videoPrincipal);

		videoView.setVideoPath("android.resource://com.caromatias.lanappfidae/"
				+ R.raw.back_a_2);
		Display display = getWindowManager().getDefaultDisplay();

		videoView.start();
		
		Button botonAnimado = (Button) findViewById(R.id.btn_com);
	    Animation animacion = AnimationUtils.loadAnimation(this, R.anim.animacion);
	    botonAnimado.startAnimation(animacion);
		// Button botonAnimado = (Button) findViewById(R.id.btn_com);
		// Animation animacion = AnimationUtils.loadAnimation(this,
		// R.anim.animacion);
		// botonAnimado.startAnimation(animacion);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
