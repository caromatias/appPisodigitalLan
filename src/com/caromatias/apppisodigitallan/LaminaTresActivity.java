package com.caromatias.apppisodigitallan;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

public class LaminaTresActivity extends Activity {

	private long splashDelay = 9500;
	private VideoView videoBackTrivia;
	private VideoView videoBackTriviaB;
	private ImageView imgWhiteTres;
	private RelativeLayout layPregTrivia;
	private TextView preguntaTrivia;
	private Button respuestaUno;
	private Button respuestaDos;
	private Button respuestaTres;
	private int respuestaCorrecta = 0;
	private int numeroDeRespuesta = 0;
	private AnimationDrawable savingAnimation;
	private Animation animTriviaIn;
	private Animation animTriviaOut;
	private RelativeLayout imgBackTrivia;
	private ImageView imgMundoTrivia;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lamina_tres);

		// INICIO VIDEO DE TRANSICION TRIVIA //
		videoBackTrivia = (VideoView) findViewById(R.id.video_back_trivia);
		videoBackTriviaB = (VideoView) findViewById(R.id.video_back_trivia_dos);
		imgWhiteTres = (ImageView) findViewById(R.id.img_transicion_tres);
		layPregTrivia = (RelativeLayout) findViewById(R.id.lay_trivia_preg);
		preguntaTrivia = (TextView) findViewById(R.id.txt_pregunta_trivia);
		respuestaUno = (Button) findViewById(R.id.btn_respuesta1);
		respuestaDos = (Button) findViewById(R.id.btn_respuesta2);
		respuestaTres = (Button) findViewById(R.id.btn_respuesta3);
		imgBackTrivia = (RelativeLayout) findViewById(R.id.lay_img_back_trivia);
		imgMundoTrivia = (ImageView) findViewById(R.id.img_mundo_back_trivia);
		rutaSeleccionada();
		videoBackTrivia.start();
		final Animation animVideoMain = AnimationUtils.loadAnimation(this,
				R.anim.fade_in);
		final Animation animVideoMainOut = AnimationUtils.loadAnimation(this,
				R.anim.fade_out);
		animTriviaIn = AnimationUtils
				.loadAnimation(this, R.anim.anim_trivia_in);
		animTriviaOut = AnimationUtils.loadAnimation(this,
				R.anim.anim_trivia_out);
		// /////////////////////////////////////
		videoBackTrivia
				.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
					public void onCompletion(MediaPlayer mp) {
						videoBackTrivia.setVisibility(View.GONE);
						videoBackTriviaB
								.setVideoPath("android.resource://com.caromatias.apppisodigitallan/"
										+ R.raw.back_trivia);
						videoBackTriviaB.start();

					}
				});
		
		/*
		videoBackTriviaB
		.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
			public void onCompletion(MediaPlayer mp) {
				imgBackTrivia.setVisibility(View.VISIBLE);
				imgBackTrivia.startAnimation(animVideoMain);
				final Handler handlerVideoBack = new Handler();
				handlerVideoBack.postDelayed(new Runnable() {
					@Override
					public void run() {
						// Do something after 5s = 5000ms
						videoBackTriviaB.setVisibility(View.GONE);
					}
				}, 2000);
			}
		});
		*/
		
		findViewById(R.id.btn_abre).setOnClickListener(
				new OnClickListener() {
					public void onClick(View arg0) {
						
						imgBackTrivia.setVisibility(View.VISIBLE);
					}
				});
		
		findViewById(R.id.btn_cierra).setOnClickListener(
				new OnClickListener() {
					public void onClick(View arg0) {
						
						imgBackTrivia.setVisibility(View.GONE);
					}
				});

		final Handler handlerDos = new Handler();
		handlerDos.postDelayed(new Runnable() {
			@Override
			public void run() {
				// Do something after 5s = 5000ms
				imgWhiteTres.setVisibility(View.VISIBLE);
				imgWhiteTres.startAnimation(animVideoMain);
			}
		}, 9800);
		final Handler handlerTres = new Handler();
		handlerTres.postDelayed(new Runnable() {
			@Override
			public void run() {
				// Do something after 5s = 5000ms
				imgWhiteTres.startAnimation(animVideoMainOut);
				imgWhiteTres.setVisibility(View.GONE);
				layPregTrivia.setVisibility(View.VISIBLE);
				layPregTrivia.startAnimation(animTriviaIn);
			}
		}, 11000);
		/*
		 * TimerTask task = new TimerTask() {
		 * 
		 * @Override public void run() {
		 * imgWhiteTres.setVisibility(View.VISIBLE);
		 * imgWhiteTres.setAnimation(animVideoMain);
		 * //imgWhite.setVisibility(View.GONE); } };
		 * 
		 * Timer timer = new Timer(); timer.schedule(task, splashDelay);
		 * 
		 * TimerTask taskOut = new TimerTask() {
		 * 
		 * @Override public void run() {
		 * imgWhiteTres.setAnimation(animVideoMainOut);
		 * imgWhiteTres.setVisibility(View.GONE); } };
		 * 
		 * Timer timerOut = new Timer(); timerOut.schedule(taskOut, 10500);
		 */
		btnRespuestas();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lamina_tres, menu);
		return true;
	}

	public void rutaSeleccionada() {
		Bundle bundle = getIntent().getExtras();
		switch (bundle.getInt("ruta")) {
		case 1:
			videoBackTrivia
					.setVideoPath("android.resource://com.caromatias.apppisodigitallan/"
							+ R.raw.buenos_aires);
			preguntaTrivia.setText(getResources().getString(R.string.baires));
			respuestaUno.setText("a) "
					+ getResources().getString(R.string.rbaires1));
			respuestaDos.setText("b) "
					+ getResources().getString(R.string.rbaires2));
			respuestaTres.setText("c) "
					+ getResources().getString(R.string.rbaires3));
			respuestaCorrecta = 3;
			numeroDeRespuesta = 1;
			break;
		case 2:
			videoBackTrivia
					.setVideoPath("android.resource://com.caromatias.apppisodigitallan/"
							+ R.raw.sao_paulo);
			preguntaTrivia
					.setText(getResources().getString(R.string.sao_paulo));
			respuestaUno.setText("a) "
					+ getResources().getString(R.string.rsao_paulo1));
			respuestaDos.setText("b) "
					+ getResources().getString(R.string.rsao_paulo2));
			respuestaTres.setText("c) "
					+ getResources().getString(R.string.rsao_paulo3));
			respuestaCorrecta = 2;
			numeroDeRespuesta = 1;
			break;
		case 3:
			videoBackTrivia
					.setVideoPath("android.resource://com.caromatias.apppisodigitallan/"
							+ R.raw.rio_janeiro);
			preguntaTrivia.setText(getResources().getString(
					R.string.rio_janeiro));
			respuestaUno.setText("a) "
					+ getResources().getString(R.string.rrio_janeiro1));
			respuestaDos.setText("b) "
					+ getResources().getString(R.string.rrio_janeiro2));
			respuestaTres.setText("c) "
					+ getResources().getString(R.string.rrio_janeiro3));
			respuestaCorrecta = 2;
			numeroDeRespuesta = 1;
			break;
		case 4:
			videoBackTrivia
					.setVideoPath("android.resource://com.caromatias.apppisodigitallan/"
							+ R.raw.bogota);
			preguntaTrivia.setText(getResources().getString(R.string.bogota));
			respuestaUno.setText("a) "
					+ getResources().getString(R.string.rbogota1));
			respuestaDos.setText("b) "
					+ getResources().getString(R.string.rbogota2));
			respuestaTres.setText("c) "
					+ getResources().getString(R.string.rbogota3));
			respuestaCorrecta = 1;
			numeroDeRespuesta = 1;
			break;
		case 5:
			videoBackTrivia
					.setVideoPath("android.resource://com.caromatias.apppisodigitallan/"
							+ R.raw.quito);
			preguntaTrivia.setText(getResources().getString(R.string.quito));
			respuestaUno.setText("a) "
					+ getResources().getString(R.string.rquito1));
			respuestaDos.setText("b) "
					+ getResources().getString(R.string.rquito2));
			respuestaTres.setText("c) "
					+ getResources().getString(R.string.rquito3));
			respuestaCorrecta = 3;
			numeroDeRespuesta = 1;
			break;
		case 6:
			videoBackTrivia
					.setVideoPath("android.resource://com.caromatias.apppisodigitallan/"
							+ R.raw.guayaquil);
			preguntaTrivia
					.setText(getResources().getString(R.string.guayaquil));
			respuestaUno.setText("a) "
					+ getResources().getString(R.string.rguayaquil1));
			respuestaDos.setText("b) "
					+ getResources().getString(R.string.rguayaquil2));
			respuestaTres.setText("c) "
					+ getResources().getString(R.string.rguayaquil3));
			respuestaCorrecta = 1;
			numeroDeRespuesta = 1;
			break;
		case 7:
			videoBackTrivia
					.setVideoPath("android.resource://com.caromatias.apppisodigitallan/"
							+ R.raw.lima);
			preguntaTrivia.setText(getResources().getString(R.string.lima));
			respuestaUno.setText("a) "
					+ getResources().getString(R.string.rlima1));
			respuestaDos.setText("b) "
					+ getResources().getString(R.string.rlima2));
			respuestaTres.setText("c) "
					+ getResources().getString(R.string.rlima3));
			respuestaCorrecta = 3;
			numeroDeRespuesta = 1;
			break;
		case 8:
			videoBackTrivia
					.setVideoPath("android.resource://com.caromatias.apppisodigitallan/"
							+ R.raw.madrid);
			preguntaTrivia.setText(getResources().getString(R.string.madrid));
			respuestaUno.setText("a) "
					+ getResources().getString(R.string.rmadrid1));
			respuestaDos.setText("b) "
					+ getResources().getString(R.string.rmadrid2));
			respuestaTres.setText("c) "
					+ getResources().getString(R.string.rmadrid3));
			respuestaCorrecta = 3;
			numeroDeRespuesta = 1;
			break;
		case 9:
			videoBackTrivia
					.setVideoPath("android.resource://com.caromatias.apppisodigitallan/"
							+ R.raw.miami);
			preguntaTrivia.setText(getResources().getString(R.string.miami));
			respuestaUno.setText("a) "
					+ getResources().getString(R.string.rmiami1));
			respuestaDos.setText("b) "
					+ getResources().getString(R.string.rmiami2));
			respuestaTres.setText("c) "
					+ getResources().getString(R.string.rmiami3));
			respuestaCorrecta = 2;
			numeroDeRespuesta = 1;
			break;
		default:
			break;
		}
	}

	public void preguntasLan() {
		int numeroRandom = generaRandom();
		switch (numeroRandom) {
		case 1:
			final Handler handlerPregUno = new Handler();
			handlerPregUno.postDelayed(new Runnable() {
				@Override
				public void run() {
					// Do something after 5s = 5000ms
					preguntaTrivia.setText(getResources().getString(
							R.string.plan1));
					respuestaUno.setText("a) "
							+ getResources().getString(R.string.rlan1_1));
					respuestaDos.setText("b) "
							+ getResources().getString(R.string.rlan1_2));
					respuestaTres.setText("c) "
							+ getResources().getString(R.string.rlan1_3));
					respuestaCorrecta = 1;
					numeroDeRespuesta = 2;
					layPregTrivia.startAnimation(animTriviaIn);
				}
			}, 1000);
			break;
		case 2:
			final Handler handlerPregDos = new Handler();
			handlerPregDos.postDelayed(new Runnable() {
				@Override
				public void run() {
					// Do something after 5s = 5000ms
					preguntaTrivia.setText(getResources().getString(
							R.string.plan1));
					respuestaUno.setText("a) "
							+ getResources().getString(R.string.rlan1_1));
					respuestaDos.setText("b) "
							+ getResources().getString(R.string.rlan1_2));
					respuestaTres.setText("c) "
							+ getResources().getString(R.string.rlan1_3));
					respuestaCorrecta = 1;
					numeroDeRespuesta = 2;
					layPregTrivia.startAnimation(animTriviaIn);
				}
			}, 1000);
			break;
		}
	}

	public void btnRespuestas() {
		// /////// RESPUESTA 1 /////////////
		findViewById(R.id.btn_respuesta1).setOnClickListener(
				new OnClickListener() {
					public void onClick(View arg0) {
						respuestaUno
								.setBackgroundResource(R.drawable.botoncomenzar);
						Handler handlerBtnUno = new Handler();
						handlerBtnUno.postDelayed(new Runnable() {
							@Override
							public void run() {
								// Do something after 5s = 5000ms
								switch (respuestaCorrecta) {
								case 1:
									respuestaUno
											.setBackgroundResource(R.anim.anim_respuesta_correcta);
									savingAnimation = (AnimationDrawable) respuestaUno
											.getBackground();
									savingAnimation.start();
									break;
								case 2:
									respuestaDos
											.setBackgroundResource(R.anim.anim_respuesta_correcta);
									savingAnimation = (AnimationDrawable) respuestaDos
											.getBackground();
									savingAnimation.start();
									break;
								case 3:
									respuestaTres
											.setBackgroundResource(R.anim.anim_respuesta_correcta);
									savingAnimation = (AnimationDrawable) respuestaTres
											.getBackground();
									savingAnimation.start();
									break;
								default:
									break;
								}
							}
						}, 2000);
						// //////////////////////
						if (respuestaCorrecta != 1) {
							Handler handlerPasoGameOver = new Handler();
							handlerPasoGameOver.postDelayed(new Runnable() {
								@Override
								public void run() {
									// Do something after 5s = 5000ms
									Intent act = new Intent(
											LaminaTresActivity.this,
											GameOverActivity.class);
									act.putExtra("game", 1);
									startActivity(act);
									overridePendingTransition(R.anim.fade_in,
											R.anim.fade_out);
								}
							}, 5000);
						} else if (respuestaCorrecta == 1) {
							Handler handlerPasoGameOver = new Handler();
							handlerPasoGameOver.postDelayed(new Runnable() {
								@Override
								public void run() {
									// Do something after 5s = 5000ms
									/*
									 * Intent act = new Intent(
									 * LaminaTresActivity.this,
									 * GameOverActivity.class);
									 * act.putExtra("game", 2);
									 * startActivity(act);
									 * overridePendingTransition(R.anim.fade_in,
									 * R.anim.fade_out);
									 */
									if (numeroDeRespuesta != 2) {
										 savingAnimation.stop();
										layPregTrivia.startAnimation(animTriviaOut);
										preguntasLan();
									} else if (numeroDeRespuesta == 2) {
										Intent act = new Intent(
												LaminaTresActivity.this,
												GameOverActivity.class);
										act.putExtra("game", 2);
										startActivity(act);
										overridePendingTransition(
												R.anim.fade_in, R.anim.fade_out);
									}
								}
							}, 5000);
						}
					}
				});
		// /////////////////////////////////
		// /////// RESPUESTA 2 /////////////
		findViewById(R.id.btn_respuesta2).setOnClickListener(
				new OnClickListener() {
					public void onClick(View arg0) {
						respuestaDos
								.setBackgroundResource(R.drawable.botoncomenzar);
						Handler handlerBtnDos = new Handler();
						handlerBtnDos.postDelayed(new Runnable() {
							@Override
							public void run() {
								// Do something after 5s = 5000ms
								switch (respuestaCorrecta) {
								case 1:
									respuestaUno
											.setBackgroundResource(R.anim.anim_respuesta_correcta);
									savingAnimation = (AnimationDrawable) respuestaUno
											.getBackground();
									savingAnimation.start();
									break;
								case 2:
									respuestaDos
											.setBackgroundResource(R.anim.anim_respuesta_correcta);
									savingAnimation = (AnimationDrawable) respuestaDos
											.getBackground();
									savingAnimation.start();
									break;
								case 3:
									respuestaTres
											.setBackgroundResource(R.anim.anim_respuesta_correcta);
									savingAnimation = (AnimationDrawable) respuestaTres
											.getBackground();
									savingAnimation.start();
									break;
								default:
									break;
								}
							}
						}, 2000);
						// //////////////////////
						if (respuestaCorrecta != 2) {
							Handler handlerPasoGameOver = new Handler();
							handlerPasoGameOver.postDelayed(new Runnable() {
								@Override
								public void run() {
									// Do something after 5s = 5000ms
									Intent act = new Intent(
											LaminaTresActivity.this,
											GameOverActivity.class);
									act.putExtra("game", 1);
									startActivity(act);
									overridePendingTransition(R.anim.fade_in,
											R.anim.fade_out);
								}
							}, 5000);
						} else if (respuestaCorrecta == 2) {
							Handler handlerPasoGameOver = new Handler();
							handlerPasoGameOver.postDelayed(new Runnable() {
								@Override
								public void run() {
									// Do something after 5s = 5000ms
									if (numeroDeRespuesta != 2) {
										savingAnimation.stop();
										layPregTrivia.startAnimation(animTriviaOut);
										preguntasLan();
									} else if (numeroDeRespuesta == 2) {
										Intent act = new Intent(
												LaminaTresActivity.this,
												GameOverActivity.class);
										act.putExtra("game", 2);
										startActivity(act);
										overridePendingTransition(
												R.anim.fade_in, R.anim.fade_out);
									}
								}
							}, 5000);
						}
					}
				});
		// /////////////////////////////////
		// /////// RESPUESTA 3 /////////////
		findViewById(R.id.btn_respuesta3).setOnClickListener(
				new OnClickListener() {
					public void onClick(View arg0) {
						respuestaTres
								.setBackgroundResource(R.drawable.botoncomenzar);
						Handler handlerBtnTres = new Handler();
						handlerBtnTres.postDelayed(new Runnable() {
							@Override
							public void run() {
								// Do something after 5s = 5000ms
								switch (respuestaCorrecta) {
								case 1:
									respuestaUno
											.setBackgroundResource(R.anim.anim_respuesta_correcta);
									savingAnimation = (AnimationDrawable) respuestaUno
											.getBackground();
									savingAnimation.start();
									break;
								case 2:
									respuestaDos
											.setBackgroundResource(R.anim.anim_respuesta_correcta);
									savingAnimation = (AnimationDrawable) respuestaDos
											.getBackground();
									savingAnimation.start();
									break;
								case 3:
									respuestaTres
											.setBackgroundResource(R.anim.anim_respuesta_correcta);
									savingAnimation = (AnimationDrawable) respuestaTres
											.getBackground();
									savingAnimation.start();
									break;
								default:
									break;
								}
							}
						}, 2000);
						// //////////////////////
						if (respuestaCorrecta != 3) {
							Handler handlerPasoGameOver = new Handler();
							handlerPasoGameOver.postDelayed(new Runnable() {
								@Override
								public void run() {
									// Do something after 5s = 5000ms
									Intent act = new Intent(
											LaminaTresActivity.this,
											GameOverActivity.class);
									act.putExtra("game", 1);
									startActivity(act);
									overridePendingTransition(R.anim.fade_in,
											R.anim.fade_out);
								}
							}, 5000);
						} else if (respuestaCorrecta == 3) {
							Handler handlerPasoGameOver = new Handler();
							handlerPasoGameOver.postDelayed(new Runnable() {
								@Override
								public void run() {
									// Do something after 5s = 5000ms
									if (numeroDeRespuesta != 2) {
										savingAnimation.stop();
										layPregTrivia.startAnimation(animTriviaOut);
										preguntasLan();
									} else if (numeroDeRespuesta == 2) {
										Intent act = new Intent(
												LaminaTresActivity.this,
												GameOverActivity.class);
										act.putExtra("game", 2);
										startActivity(act);
										overridePendingTransition(
												R.anim.fade_in, R.anim.fade_out);
									}
								}
							}, 5000);
						}
					}
				});
		// /////////////////////////////////
	}

	public int generaRandom() {
		int min = 1;
		int max = 2;

		Random r = new Random();
		int i1 = r.nextInt(max - min + 1) + min;
		return i1;
	}
}
