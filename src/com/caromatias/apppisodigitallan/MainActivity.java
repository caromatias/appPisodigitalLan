package com.caromatias.apppisodigitallan;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Display;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
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

		videoView.setVideoPath("android.resource://com.caromatias.apppisodigitallan/"
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
	    pasaLamina();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	public void pasaLamina(){
		findViewById(R.id.btn_com).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Button botonAnimado = (Button) findViewById(R.id.btn_com);
				botonAnimado.setBackgroundResource(R.drawable.botoncomenzar);
				Intent act = new Intent(MainActivity.this, LaminaUnoActivity.class);
				startActivity(act);
				overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
			}
		});
	}

}
