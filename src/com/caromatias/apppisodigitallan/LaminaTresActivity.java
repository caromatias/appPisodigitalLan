package com.caromatias.apppisodigitallan;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.view.animation.ScaleAnimation;
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
	private ImageView imgBackTriviaUno;
	private ImageView imgMundoTrivia;
	private Animation animMundoIn;
	private Animation animMundoOut;
	private Animation animMundoRotacion;
	private MediaPlayer mpTrivia;
	public static MediaPlayer mpFondo;
	private MediaPlayer mpMundo;
	private MediaPlayer mpoK;
	private MediaPlayer mpFail;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lamina_tres);

		LaminaDosActivity.mpDespegue.stop();
		mpoK = MediaPlayer.create(this, R.raw.sonido_correcto);
		mpFail = MediaPlayer.create(this, R.raw.sonido_incorrecto);
		mpMundo = MediaPlayer.create(this, R.raw.engranaje_mundo);
		mpTrivia = MediaPlayer.create(this, R.raw.reloj_trivia);
		mpFondo = MediaPlayer.create(this, R.raw.jazz_dance);
		mpFondo.start();
		AudioManager audio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
		int currentVolume = audio.getStreamVolume(AudioManager.STREAM_MUSIC);
		int maxVolume = audio.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		float percent = 0.7f;
		int seventyVolume = (int) (maxVolume * percent);
		audio.setStreamVolume(AudioManager.STREAM_MUSIC, seventyVolume, 0);

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
		imgBackTriviaUno = (ImageView) findViewById(R.id.img_back_trivia_uno);
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
		animMundoIn = AnimationUtils.loadAnimation(this,
				R.anim.anim_scale_translation_world);
		animMundoOut = AnimationUtils.loadAnimation(this,
				R.anim.anim_translate_mundo_out);
		animMundoRotacion = AnimationUtils.loadAnimation(this,
				R.anim.anim_rotacion_mundo);
		// /////////////////////////////////////
		videoBackTrivia
				.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
					public void onCompletion(MediaPlayer mp) {
						videoBackTrivia.setVisibility(View.GONE);
						videoBackTriviaB
								.setVideoPath("android.resource://com.caromatias.apppisodigitallan/"
										+ R.raw.back_trivia);
						videoBackTriviaB.start();
						AudioManager audio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
						int currentVolume = audio
								.getStreamVolume(AudioManager.STREAM_MUSIC);
						int maxVolume = audio
								.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
						float percent = 0.5f;
						int seventyVolume = (int) (maxVolume * percent);
						audio.setStreamVolume(AudioManager.STREAM_MUSIC,
								seventyVolume, 0);
						mpTrivia.start();
						mpTrivia.setLooping(true);
					}
				});

		/*
		 * videoBackTriviaB .setOnCompletionListener(new
		 * MediaPlayer.OnCompletionListener() { public void
		 * onCompletion(MediaPlayer mp) {
		 * imgBackTrivia.setVisibility(View.VISIBLE);
		 * imgBackTrivia.startAnimation(animVideoMain); final Handler
		 * handlerVideoBack = new Handler(); handlerVideoBack.postDelayed(new
		 * Runnable() {
		 * 
		 * @Override public void run() { // Do something after 5s = 5000ms
		 * videoBackTriviaB.setVisibility(View.GONE); } }, 2000); } });
		 */

		findViewById(R.id.btn_abre).setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {

				imgBackTrivia.setVisibility(View.VISIBLE);
			}
		});

		findViewById(R.id.btn_cierra).setOnClickListener(new OnClickListener() {
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
				// layPregTrivia.setVisibility(View.VISIBLE);
				// layPregTrivia.startAnimation(animTriviaIn);
			}
		}, 11000);
		final Handler handlerCuatro = new Handler();
		handlerCuatro.postDelayed(new Runnable() {
			@Override
			public void run() {
				// Do something after 5s = 5000ms
				/*
				 * imgBackTrivia.setVisibility(View.VISIBLE);
				 * imgBackTriviaUno.setVisibility(View.VISIBLE);
				 * imgBackTriviaUno.startAnimation(animVideoMain);
				 */
				imgMundoTrivia.setVisibility(View.VISIBLE);
				imgMundoTrivia.startAnimation(animMundoIn);
				layPregTrivia.setVisibility(View.VISIBLE);
				/*
				 * ResizeWidthAnimation anim = new
				 * ResizeWidthAnimation(layPregTrivia, 1200);
				 * anim.setDuration(2000); layPregTrivia.startAnimation(anim);
				 */
				layPregTrivia.startAnimation(animMundoIn);
			}
		}, 15500);
		final Handler handlerCinco = new Handler();
		handlerCinco.postDelayed(new Runnable() {
			@Override
			public void run() {
				// Do something after 5s = 5000ms
				// imgMundoTrivia.setVisibility(View.VISIBLE);
				// imgMundoTrivia.startAnimation(animVideoMain);
			}
		}, 14000);
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
		respuestaUno.setEnabled(true);
		respuestaDos.setEnabled(true);
		respuestaTres.setEnabled(true);
		layPregTrivia.startAnimation(animMundoOut);
		imgMundoTrivia.startAnimation(animMundoOut);
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
					// layPregTrivia.startAnimation(animTriviaIn);
					layPregTrivia.startAnimation(animMundoIn);
					imgMundoTrivia.startAnimation(animMundoIn);
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
					// layPregTrivia.startAnimation(animTriviaIn);
					layPregTrivia.startAnimation(animMundoIn);
					imgMundoTrivia.startAnimation(animMundoIn);
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
						mpMundo.start();
						respuestaUno
								.setBackgroundResource(R.drawable.botoncomenzar);
						imgMundoTrivia.setVisibility(View.VISIBLE);
						imgMundoTrivia.startAnimation(animMundoRotacion);
						respuestaUno.setEnabled(false);
						respuestaDos.setEnabled(false);
						respuestaTres.setEnabled(false);
						Handler handlerBtnUno = new Handler();
						handlerBtnUno.postDelayed(new Runnable() {
							@Override
							public void run() {
								// Do something after 5s = 5000ms
								// mpMundo.stop();
								switch (respuestaCorrecta) {
								case 1:
									respuestaUno
											.setBackgroundResource(R.anim.anim_respuesta_correcta);
									savingAnimation = (AnimationDrawable) respuestaUno
											.getBackground();
									savingAnimation.start();
									ImageView imaCorrecta = (ImageView) findViewById(R.id.img_respuesta_correcta);
									imaCorrecta.setVisibility(View.VISIBLE);
									mpoK.start();
									break;
								case 2:
									respuestaDos
											.setBackgroundResource(R.anim.anim_respuesta_correcta);
									savingAnimation = (AnimationDrawable) respuestaDos
											.getBackground();
									savingAnimation.start();
									ImageView imaIncorrecta = (ImageView) findViewById(R.id.img_respuesta_incorrecta);
									imaIncorrecta.setVisibility(View.VISIBLE);
									mpFail.start();
									break;
								case 3:
									respuestaTres
											.setBackgroundResource(R.anim.anim_respuesta_correcta);
									savingAnimation = (AnimationDrawable) respuestaTres
											.getBackground();
									savingAnimation.start();
									ImageView imaIncorrecta2 = (ImageView) findViewById(R.id.img_respuesta_incorrecta);
									imaIncorrecta2.setVisibility(View.VISIBLE);
									mpFail.start();
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
									// mp.stop();
									mpTrivia.stop();
								}
							}, 5000);
						} else if (respuestaCorrecta == 1) {
							Handler handlerPasoGameOver = new Handler();
							handlerPasoGameOver.postDelayed(new Runnable() {
								@Override
								public void run() {
									if (numeroDeRespuesta != 2) {
										savingAnimation.stop();
										preguntasLan();
										ImageView imaIncorrecta2 = (ImageView) findViewById(R.id.img_respuesta_correcta);
										imaIncorrecta2.setVisibility(View.GONE);
									} else if (numeroDeRespuesta == 2) {
										Intent act = new Intent(
												LaminaTresActivity.this,
												GameOverActivity.class);
										act.putExtra("game", 2);
										startActivity(act);
										overridePendingTransition(
												R.anim.fade_in, R.anim.fade_out);
										// mp.stop();
										mpTrivia.stop();
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
						mpMundo.start();
						respuestaDos
								.setBackgroundResource(R.drawable.botoncomenzar);
						respuestaUno.setEnabled(false);
						respuestaDos.setEnabled(false);
						respuestaTres.setEnabled(false);
						imgMundoTrivia.startAnimation(animMundoRotacion);
						Handler handlerBtnDos = new Handler();
						handlerBtnDos.postDelayed(new Runnable() {
							@Override
							public void run() {
								// Do something after 5s = 5000ms
								// mpMundo.stop();
								switch (respuestaCorrecta) {
								case 1:
									respuestaUno
											.setBackgroundResource(R.anim.anim_respuesta_correcta);
									savingAnimation = (AnimationDrawable) respuestaUno
											.getBackground();
									savingAnimation.start();
									ImageView imaIncorrecta = (ImageView) findViewById(R.id.img_respuesta_incorrecta);
									imaIncorrecta.setVisibility(View.VISIBLE);
									mpFail.start();
									break;
								case 2:
									respuestaDos
											.setBackgroundResource(R.anim.anim_respuesta_correcta);
									savingAnimation = (AnimationDrawable) respuestaDos
											.getBackground();
									savingAnimation.start();
									ImageView imaCorrecta = (ImageView) findViewById(R.id.img_respuesta_correcta);
									imaCorrecta.setVisibility(View.VISIBLE);
									mpoK.start();
									break;
								case 3:
									respuestaTres
											.setBackgroundResource(R.anim.anim_respuesta_correcta);
									savingAnimation = (AnimationDrawable) respuestaTres
											.getBackground();
									savingAnimation.start();
									ImageView imaIncorrecta2 = (ImageView) findViewById(R.id.img_respuesta_incorrecta);
									imaIncorrecta2.setVisibility(View.VISIBLE);
									mpFail.start();
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
									// mp.stop();
									mpTrivia.stop();

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
										// layPregTrivia.startAnimation(animTriviaOut);
										preguntasLan();
										ImageView imaIncorrecta2 = (ImageView) findViewById(R.id.img_respuesta_correcta);
										imaIncorrecta2.setVisibility(View.GONE);
									} else if (numeroDeRespuesta == 2) {
										Intent act = new Intent(
												LaminaTresActivity.this,
												GameOverActivity.class);
										act.putExtra("game", 2);
										startActivity(act);
										overridePendingTransition(
												R.anim.fade_in, R.anim.fade_out);
										// mp.stop();
										mpTrivia.stop();
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
						mpMundo.start();
						respuestaTres
								.setBackgroundResource(R.drawable.botoncomenzar);
						respuestaUno.setEnabled(false);
						respuestaDos.setEnabled(false);
						respuestaTres.setEnabled(false);
						imgMundoTrivia.startAnimation(animMundoRotacion);
						Handler handlerBtnTres = new Handler();
						handlerBtnTres.postDelayed(new Runnable() {
							@Override
							public void run() {
								// Do something after 5s = 5000ms
								// mpMundo.stop();
								switch (respuestaCorrecta) {
								case 1:
									respuestaUno
											.setBackgroundResource(R.anim.anim_respuesta_correcta);
									savingAnimation = (AnimationDrawable) respuestaUno
											.getBackground();
									savingAnimation.start();
									ImageView imaIncorrecta = (ImageView) findViewById(R.id.img_respuesta_incorrecta);
									imaIncorrecta.setVisibility(View.VISIBLE);
									mpFail.start();
									break;
								case 2:
									respuestaDos
											.setBackgroundResource(R.anim.anim_respuesta_correcta);
									savingAnimation = (AnimationDrawable) respuestaDos
											.getBackground();
									savingAnimation.start();
									ImageView imaIncorrecta2 = (ImageView) findViewById(R.id.img_respuesta_incorrecta);
									imaIncorrecta2.setVisibility(View.VISIBLE);
									mpFail.start();
									break;
								case 3:
									respuestaTres
											.setBackgroundResource(R.anim.anim_respuesta_correcta);
									savingAnimation = (AnimationDrawable) respuestaTres
											.getBackground();
									savingAnimation.start();
									ImageView imaCorrecta = (ImageView) findViewById(R.id.img_respuesta_correcta);
									imaCorrecta.setVisibility(View.VISIBLE);
									mpoK.start();
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
									// mp.stop();
									mpTrivia.stop();
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
										// layPregTrivia.startAnimation(animTriviaOut);
										preguntasLan();
										ImageView imaIncorrecta2 = (ImageView) findViewById(R.id.img_respuesta_correcta);
										imaIncorrecta2.setVisibility(View.GONE);
									} else if (numeroDeRespuesta == 2) {
										Intent act = new Intent(
												LaminaTresActivity.this,
												GameOverActivity.class);
										act.putExtra("game", 2);
										startActivity(act);
										overridePendingTransition(
												R.anim.fade_in, R.anim.fade_out);
										// mp.stop();
										mpTrivia.stop();
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

	@Override
	public void onBackPressed() {
		return;
	}
}
