package com.caromatias.apppisodigitallan;

import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
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
	private Button botonStop;
	private ImageView intentoUno;
	private ImageView intentoDos;
	private ImageView intentoTres;
	private int intentos = 1;
	private VideoView videoBackDespegue;
	private ImageView ImagenBackDespegue;

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
		botonStop = (Button)findViewById(R.id.button1);
		intentoUno = (ImageView) findViewById(R.id.img_intento_uno);
		intentoDos = (ImageView) findViewById(R.id.img_intento_dos);
		intentoTres = (ImageView) findViewById(R.id.img_intento_tres);
		
		findViewById(R.id.button1).setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				estadoProgress = 3;
				//botonStop.setEnabled(false);
				switch (intentos) {
				case 1:
					intentoTres.setImageResource(R.drawable.intento_red);
					break;
				case 2:
					intentoDos.setImageResource(R.drawable.intento_red);
					break;
				case 3:
					intentoUno.setImageResource(R.drawable.intento_red);
					break;
				default:
					break;
				}
			}
		});

		// //////////////////////////////////
		// ///////// BOTON COMENZAR /////////

		final RelativeLayout layPopup = (RelativeLayout) findViewById(R.id.lay_popup_despegue);
		final Animation animPopup = AnimationUtils.loadAnimation(this,
				R.anim.anim_mapa_inter_out);
		final ImageView layLogoIzq = (ImageView) findViewById(R.id.img_logo_izq);
		final Animation animLogoIzq = AnimationUtils.loadAnimation(this,
				R.anim.anim_mapa_in);
		
		videoBackDespegue = (VideoView) findViewById(R.id.video_back_despegue);
		videoBackDespegue.setVideoPath("android.resource://com.caromatias.apppisodigitallan/"
				+ R.raw.world_transition);
		ImagenBackDespegue = (ImageView) findViewById(R.id.img_back_despegue);
		final Animation animImgBackDespegue = AnimationUtils.loadAnimation(this,R.anim.fade_out);

		findViewById(R.id.btn_comenzar_juego_despegue).setOnClickListener(
				new OnClickListener() {
					public void onClick(View arg0) {
						layPopup.startAnimation(animPopup);
						layPopup.setVisibility(View.GONE);
						layLogoIzq.setVisibility(View.VISIBLE);
						layLogoIzq.startAnimation(animLogoIzq);
						// ////// COMIENZA ANIMACION DE CARGA ////////
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
									if (mProgressStatus == 99) {
										estadoProgress = 2;
									}
									break;
								case 2:
									mProgressStatus -= 1;
									if (mProgressStatus == 1) {
										estadoProgress = 1;
									}
									break;
								case 3:
									timer.cancel();
									intentos = 2;
									if (mProgressStatus < 70) {										
										intentoDos();
									}else if(mProgressStatus > 70){
										ImagenBackDespegue.startAnimation(animImgBackDespegue);
										videoBackDespegue.start();
										ImagenBackDespegue.setVisibility(View.GONE);
									}
									break;
								}
								mProgress.setProgress(mProgressStatus);
								runOnUiThread(new Runnable() // run on ui thread
								{
									public void run() {
										textoDos.setText(String
												.valueOf(mProgressStatus + "%"));
									}
								});
							}
						}, delay, period);
						// ///////////////////////////////////////////
					}
				});

		// //////////////////////////////////
		goRutas();
		// //////////////////////////////////
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lamina_dos, menu);
		return true;
	}

	public void intentoDos() {
		final TextView textoDos = (TextView) findViewById(R.id.txt_porcentaje);
		int delay = 3000; // delay for 1 sec.
		int period = 3; // repeat every 10 sec.
		estadoProgress = 1;
		//botonStop.setEnabled(true);
		final Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				// mProgressStatus += 1;
				// mProgress.setProgress(mProgressStatus);
				switch (estadoProgress) {
				case 1:
					mProgressStatus += 1;
					if (mProgressStatus == 99) {
						estadoProgress = 2;
					}
					break;
				case 2:
					mProgressStatus -= 1;
					if (mProgressStatus == 1) {
						estadoProgress = 1;
					}
					break;
				case 3:
					timer.cancel();
					intentos = 3;
					if (mProgressStatus < 70) {
						intentoTres();
					}else if(mProgressStatus > 70){
						videoBackDespegue.start();
					}
					break;
				}
				mProgress.setProgress(mProgressStatus);
				runOnUiThread(new Runnable() // run on ui thread
				{
					public void run() {
						textoDos.setText(String.valueOf(mProgressStatus + "%"));
					}
				});
			}
		}, delay, period);
	}
	
	public void intentoTres(){
		final TextView textoDos = (TextView) findViewById(R.id.txt_porcentaje);
		int delay = 3000; // delay for 1 sec.
		int period = 3; // repeat every 10 sec.
		estadoProgress = 1;
		//botonStop.setEnabled(true);
		final Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				// mProgressStatus += 1;
				// mProgress.setProgress(mProgressStatus);
				switch (estadoProgress) {
				case 1:
					mProgressStatus += 1;
					if (mProgressStatus == 99) {
						estadoProgress = 2;
					}
					break;
				case 2:
					mProgressStatus -= 1;
					if (mProgressStatus == 1) {
						estadoProgress = 1;
					}
					break;
				case 3:
					timer.cancel();
					if (mProgressStatus < 70) {
						intentoTres();
					}else if(mProgressStatus > 70){
						videoBackDespegue.start();
					}
					break;
				}
				mProgress.setProgress(mProgressStatus);
				runOnUiThread(new Runnable() // run on ui thread
				{
					public void run() {
						textoDos.setText(String
								.valueOf(mProgressStatus + "%"));
					}
				});
			}
		}, delay, period);
	}

	public void goRutas() {

		final RelativeLayout layJuegoDespegue = (RelativeLayout) findViewById(R.id.lay_juego_master);
		final Animation animJuego = AnimationUtils.loadAnimation(this,
				R.anim.anim_in_juego);
		// /////// BOTON UNO //////////
		findViewById(R.id.btnUno).setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				layJuegoDespegue.setVisibility(View.VISIBLE);
				layJuegoDespegue.startAnimation(animJuego);
			}
		});
		// ////////////////////////////

	}

}
