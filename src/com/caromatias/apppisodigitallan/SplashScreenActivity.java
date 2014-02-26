package com.caromatias.apppisodigitallan;


import java.util.Timer;
import java.util.TimerTask;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

public class SplashScreenActivity extends Activity {
	
	private long splashDelay = 3000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash_screen);
		
		// //////// Cambio de activity ////////////

		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				Intent act = new Intent(SplashScreenActivity.this,	LaminaUnoActivity.class);
				startActivity(act);
				overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
				//finish();
			}
		};

		Timer timer = new Timer();
		timer.schedule(task, splashDelay);
		
		//////////////////////////////////////////////

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.splash_screen, menu);
		return true;
	}

}
