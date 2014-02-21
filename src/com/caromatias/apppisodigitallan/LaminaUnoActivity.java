package com.caromatias.apppisodigitallan;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.VideoView;

public class LaminaUnoActivity extends Activity {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lamina_uno);
		
		final VideoView videoView = (VideoView) findViewById(R.id.video_lamina_uno);

		videoView.setVideoPath("android.resource://com.caromatias.apppisodigitallan/"
				+ R.raw.transicion);

		videoView.start();
		
		////////// Cambio de imagen a video ////////////

		final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 5s = 5000ms
            	videoView.setVisibility(View.INVISIBLE);
            }
        }, 5000);
				
		//////////////////////////////////////////////
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lamina_uno, menu);
		return true;
	}

}
