package com.caromatias.apppisodigitallan;

import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

public class LaminaDosActivity extends Activity {

	private ImageView ivAnimacion;
	private AnimationDrawable savingAnimation;
	// ///////////////////////////////////////
	private ProgressBar mProgress;
	private int mProgressStatus = 0;
	private int terminaProceso = 0;
	private Handler mHandler = new Handler();
	private int estadoProgress = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lamina_dos);

		RelativeLayout layLineaRoja = (RelativeLayout) findViewById(R.id.img_back_barra);
		Animation animLayBarra = AnimationUtils.loadAnimation(this,
				R.anim.slide_down);
		layLineaRoja.startAnimation(animLayBarra);

		RelativeLayout layBackRutas = (RelativeLayout) findViewById(R.id.img_back_rutas);
		Animation animLayRutas = AnimationUtils.loadAnimation(this,
				R.anim.slide_rutas);
		layBackRutas.startAnimation(animLayRutas);

		ivAnimacion = (ImageView) findViewById(R.id.img_elige_ruta);
		ivAnimacion.setBackgroundResource(R.anim.anim_elige_ruta);
		savingAnimation = (AnimationDrawable) ivAnimacion.getBackground();
		savingAnimation.start();

		mProgress = (ProgressBar) findViewById(R.id.ProgressBar_carga);
		Resources res = getResources();
		mProgress.setProgressDrawable(res
				.getDrawable(R.drawable.estilo_progressbar));

		// ///////////////////////////////////

		findViewById(R.id.button1).setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				estadoProgress = 3;
			}
		});

		/*
		 * new Thread(new Runnable() { public void run() { mas();
		 * 
		 * } }).start();
		 */
		final TextView textoDos = (TextView) findViewById(R.id.txt_porcentaje);
		int delay = 1000; // delay for 1 sec.
		int period = 3; // repeat every 10 sec.
		final Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				// mProgressStatus += 1;
				// mProgress.setProgress(mProgressStatus);
				switch (estadoProgress) {
				case 1:
					mProgressStatus += 1;
					if(mProgressStatus == 99){
						estadoProgress = 2;
					}
					break;
				case 2:
					mProgressStatus -= 1;
					if(mProgressStatus == 1){
						estadoProgress = 1;
					}
					break;
				case 3:
					timer.cancel();
					break;
				}
				mProgress.setProgress(mProgressStatus);
				runOnUiThread(new Runnable() //run on ui thread
	             {
	              public void run() 
	              {
	            	  textoDos.setText(String.valueOf(mProgressStatus + "%"));
	              }
	             });
			}
		}, delay, period);
		// //////////////////////////////////
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lamina_dos, menu);
		return true;
	}

	public int doWork() throws InterruptedException {
		Thread.sleep(3);
		return 1;
	}

	public void menos() {
		while (mProgressStatus < 100) {
			try {
				mProgressStatus -= doWork();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// Update the progress bar
			mHandler.post(new Runnable() {
				public void run() {
					mProgress.setProgress(mProgressStatus);
				}
			});
			if (mProgressStatus == 1) {
				mas();
			}
			if (terminaProceso == 1) {
				return;
			}
		}
	}

	public void mas() {
		while (mProgressStatus < 100) {
			try {
				mProgressStatus += doWork();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// Update the progress bar
			mHandler.post(new Runnable() {
				public void run() {
					mProgress.setProgress(mProgressStatus);
				}
			});
			if (mProgressStatus == 99) {
				menos();
			}
			if (terminaProceso == 1) {
				return;
			}
		}
	}

}
