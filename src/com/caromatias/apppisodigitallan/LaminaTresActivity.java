package com.caromatias.apppisodigitallan;

import java.util.Random;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Context;
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
	private ImageView imgBackTriviaUno;
	private ImageView imgMundoTrivia;
	private Animation animMundoIn;
	private Animation animMundoOut;
	private Animation animMundoRotacion;
	private Animation animMundoOutMundo;
	private MediaPlayer mpTrivia;
	public static MediaPlayer mpFondo;
	private MediaPlayer mpFail;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lamina_tres);

		mpFail = MediaPlayer.create(this, R.raw.sonido_incorrecto);
		mpTrivia = MediaPlayer.create(this, R.raw.reloj_trivia);
		mpFondo = MediaPlayer.create(this, R.raw.jazz_dance);
		mpFondo.start();
		AudioManager audio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
		int currentVolume = audio.getStreamVolume(AudioManager.STREAM_MUSIC);
		int maxVolume = audio.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		float percent = 0.8f;
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

		mpTrivia.setOnCompletionListener(new OnCompletionListener() {
			public void onCompletion(MediaPlayer mpTri) {
				mpTri.release();
			};
		});
		mpFondo.setOnCompletionListener(new OnCompletionListener() {
			public void onCompletion(MediaPlayer mpFondo) {
				mpFondo.release();

			};
		});
		mpFail.setOnCompletionListener(new OnCompletionListener() {
			public void onCompletion(MediaPlayer mpFa) {
				mpFa.release();
			};
		});

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
		animMundoOutMundo = AnimationUtils.loadAnimation(this,R.anim.anim_translate_mundo_out);
		// /////////////////////////////////////
		videoBackTrivia.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
					public void onCompletion(MediaPlayer mp) {
						fadeInWhite();
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
		videoBackTriviaB.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
			public void onCompletion(MediaPlayer mp) {
				imgMundoTrivia.setVisibility(View.VISIBLE);
				imgMundoTrivia.startAnimation(animMundoIn);
				layPregTrivia.setVisibility(View.VISIBLE);
				layPregTrivia.startAnimation(animMundoIn);
				creaSalidaTrivia();
			}
		});

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

		/*
		 * final Handler handlerDos = new Handler(); handlerDos.postDelayed(new
		 * Runnable() {
		 * 
		 * @Override public void run() { // Do something after 5s = 5000ms
		 * imgWhiteTres.setVisibility(View.VISIBLE);
		 * imgWhiteTres.startAnimation(animVideoMain); } }, 9800); final Handler
		 * handlerTres = new Handler(); handlerTres.postDelayed(new Runnable() {
		 * 
		 * @Override public void run() { // Do something after 5s = 5000ms
		 * imgWhiteTres.startAnimation(animVideoMainOut);
		 * imgWhiteTres.setVisibility(View.GONE); //
		 * layPregTrivia.setVisibility(View.VISIBLE); //
		 * layPregTrivia.startAnimation(animTriviaIn); } }, 11000);
		 */
		/*
		final Handler handlerCuatro = new Handler();
		handlerCuatro.postDelayed(new Runnable() {
			@Override
			public void run() {
				imgMundoTrivia.setVisibility(View.VISIBLE);
				imgMundoTrivia.startAnimation(animMundoIn);
				layPregTrivia.setVisibility(View.VISIBLE);
				layPregTrivia.startAnimation(animMundoIn);
				creaSalidaTrivia();
			}
		}, 15500);
		
		final Handler handlerCinco = new Handler();
		handlerCinco.postDelayed(new Runnable() {
			@Override
			public void run() {
				// Do something after 5s = 5000ms
				// imgMundoTrivia.setVisibility(View.VISIBLE);
				// imgMundoTrivia.startAnimation(animVideoMain);
				creaSalidaTrivia();
			}
		}, 16500);
		*/
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
		case 10:
			videoBackTrivia
					.setVideoPath("android.resource://com.caromatias.apppisodigitallan/"
							+ R.raw.nuevayork);
			preguntaTrivia
					.setText(getResources().getString(R.string.nuevayork));
			respuestaUno.setText("a) "
					+ getResources().getString(R.string.rnuevayork1));
			respuestaDos.setText("b) "
					+ getResources().getString(R.string.rnuevayork2));
			respuestaTres.setText("c) "
					+ getResources().getString(R.string.rnuevayork3));
			respuestaCorrecta = 1;
			numeroDeRespuesta = 1;
			break;
		case 11:
			videoBackTrivia
					.setVideoPath("android.resource://com.caromatias.apppisodigitallan/"
							+ R.raw.langeles);
			preguntaTrivia.setText(getResources().getString(R.string.langeles));
			respuestaUno.setText("a) "
					+ getResources().getString(R.string.rlangeles1));
			respuestaDos.setText("b) "
					+ getResources().getString(R.string.rlangeles2));
			respuestaTres.setText("c) "
					+ getResources().getString(R.string.rlangeles3));
			respuestaCorrecta = 3;
			numeroDeRespuesta = 1;
			break;
		case 12:
			videoBackTrivia
					.setVideoPath("android.resource://com.caromatias.apppisodigitallan/"
							+ R.raw.pcana);
			preguntaTrivia.setText(getResources().getString(R.string.pcana));
			respuestaUno.setText("a) "
					+ getResources().getString(R.string.pcana1));
			respuestaDos.setText("b) "
					+ getResources().getString(R.string.pcana2));
			respuestaTres.setText("c) "
					+ getResources().getString(R.string.pcana3));
			respuestaCorrecta = 3;
			numeroDeRespuesta = 1;
			break;
		case 13:
			videoBackTrivia
					.setVideoPath("android.resource://com.caromatias.apppisodigitallan/"
							+ R.raw.mexico);
			preguntaTrivia.setText(getResources().getString(R.string.mexico));
			respuestaUno.setText("a) "
					+ getResources().getString(R.string.mexico1));
			respuestaDos.setText("b) "
					+ getResources().getString(R.string.mexico2));
			respuestaTres.setText("c) "
					+ getResources().getString(R.string.mexico3));
			respuestaCorrecta = 2;
			numeroDeRespuesta = 1;
			break;
		case 14:
			videoBackTrivia
					.setVideoPath("android.resource://com.caromatias.apppisodigitallan/"
							+ R.raw.sidney);
			preguntaTrivia.setText(getResources().getString(R.string.sidney));
			respuestaUno.setText("a) "
					+ getResources().getString(R.string.sidney1));
			respuestaDos.setText("b) "
					+ getResources().getString(R.string.sidney2));
			respuestaTres.setText("c) "
					+ getResources().getString(R.string.sidney3));
			respuestaCorrecta = 1;
			numeroDeRespuesta = 1;
			break;
		case 15:
			videoBackTrivia
					.setVideoPath("android.resource://com.caromatias.apppisodigitallan/"
							+ R.raw.auckland);
			preguntaTrivia.setText(getResources().getString(R.string.auckland));
			respuestaUno.setText("a) "
					+ getResources().getString(R.string.auckland1));
			respuestaDos.setText("b) "
					+ getResources().getString(R.string.auckland2));
			respuestaTres.setText("c) "
					+ getResources().getString(R.string.auckland3));
			respuestaCorrecta = 1;
			numeroDeRespuesta = 1;
			break;
		case 16:
			videoBackTrivia
					.setVideoPath("android.resource://com.caromatias.apppisodigitallan/"
							+ R.raw.ipascua);
			preguntaTrivia.setText(getResources().getString(R.string.ipascua));
			respuestaUno.setText("a) "
					+ getResources().getString(R.string.ipascua1));
			respuestaDos.setText("b) "
					+ getResources().getString(R.string.ipascua2));
			respuestaTres.setText("c) "
					+ getResources().getString(R.string.ipascua3));
			respuestaCorrecta = 1;
			numeroDeRespuesta = 1;
			break;
		case 17:
			videoBackTrivia
					.setVideoPath("android.resource://com.caromatias.apppisodigitallan/"
							+ R.raw.chiloe);
			preguntaTrivia.setText(getResources().getString(R.string.chiloe));
			respuestaUno.setText("a) "
					+ getResources().getString(R.string.chiloe1));
			respuestaDos.setText("b) "
					+ getResources().getString(R.string.chiloe2));
			respuestaTres.setText("c) "
					+ getResources().getString(R.string.chiloe3));
			respuestaCorrecta = 3;
			numeroDeRespuesta = 1;
			break;
		case 18:
			videoBackTrivia
					.setVideoPath("android.resource://com.caromatias.apppisodigitallan/"
							+ R.raw.papeete);
			preguntaTrivia.setText(getResources().getString(R.string.papeete));
			respuestaUno.setText("a) "
					+ getResources().getString(R.string.papeete1));
			respuestaDos.setText("b) "
					+ getResources().getString(R.string.papeete2));
			respuestaTres.setText("c) "
					+ getResources().getString(R.string.papeete3));
			respuestaCorrecta = 3;
			numeroDeRespuesta = 1;
			break;
		case 19:
			videoBackTrivia
					.setVideoPath("android.resource://com.caromatias.apppisodigitallan/"
							+ R.raw.frankfurt);
			preguntaTrivia
					.setText(getResources().getString(R.string.frankfurt));
			respuestaUno.setText("a) "
					+ getResources().getString(R.string.frankfurt1));
			respuestaDos.setText("b) "
					+ getResources().getString(R.string.frankfurt2));
			respuestaTres.setText("c) "
					+ getResources().getString(R.string.frankfurt3));
			respuestaCorrecta = 3;
			numeroDeRespuesta = 1;
			break;
		case 20:
			videoBackTrivia
					.setVideoPath("android.resource://com.caromatias.apppisodigitallan/"
							+ R.raw.londres);
			preguntaTrivia.setText(getResources().getString(R.string.londres));
			respuestaUno.setText("a) "
					+ getResources().getString(R.string.londres1));
			respuestaDos.setText("b) "
					+ getResources().getString(R.string.londres2));
			respuestaTres.setText("c) "
					+ getResources().getString(R.string.londres3));
			respuestaCorrecta = 1;
			numeroDeRespuesta = 1;
			break;
		case 21:
			videoBackTrivia
					.setVideoPath("android.resource://com.caromatias.apppisodigitallan/"
							+ R.raw.cuzco);
			preguntaTrivia.setText(getResources().getString(R.string.cuzco));
			respuestaUno.setText("a) "
					+ getResources().getString(R.string.cuzco1));
			respuestaDos.setText("b) "
					+ getResources().getString(R.string.cuzco2));
			respuestaTres.setText("c) "
					+ getResources().getString(R.string.cuzco3));
			respuestaCorrecta = 1;
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
		Bundle bundle = getIntent().getExtras();
		setAnimacionListener(bundle.getInt("ruta"));
		layPregTrivia.startAnimation(animMundoOut);
		imgMundoTrivia.startAnimation(animMundoOutMundo);
		creaSalidaTrivia2();
	}

	public void creaMpOk() {
		final MediaPlayer mpoK = MediaPlayer
				.create(this, R.raw.sonido_correcto);
		mpoK.start();
		mpoK.setOnCompletionListener(new OnCompletionListener() {
			public void onCompletion(MediaPlayer mp) {
				mp.release();
			};
		});
	}
	public void creaMpOkCorrectaUno() {
		final MediaPlayer mpoK = MediaPlayer.create(this, R.raw.sonido_correcto);
		mpoK.start();
		mpoK.setOnCompletionListener(new OnCompletionListener() {
			public void onCompletion(MediaPlayer mp) {
				mp.release();
				if (respuestaCorrecta != 1) {
					Intent act = new Intent(
							LaminaTresActivity.this,
							GameOverActivity.class);
					act.putExtra("game", 1);
					startActivity(act);
					overridePendingTransition(R.anim.fade_in,
							R.anim.fade_out);
					// mp.stop();
					mpTrivia.stop();
				} else if (respuestaCorrecta == 1) {
					if (numeroDeRespuesta != 2) {
						savingAnimation.stop();
						preguntasLan();
						ImageView imaIncorrecta2 = (ImageView) findViewById(R.id.img_respuesta_correcta);
						imaIncorrecta2.setVisibility(View.GONE);
					} else if (numeroDeRespuesta == 2) {
						reconRutaJugada();
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
			};
		});
	}
	public void creaMpFailIncorrectaUno() {
		final MediaPlayer mpFai = MediaPlayer.create(this, R.raw.sonido_incorrecto_large);
		mpFai.start();
		mpFai.setOnCompletionListener(new OnCompletionListener() {
			public void onCompletion(MediaPlayer mp) {
				mp.release();
				if (respuestaCorrecta != 1) {
					Intent act = new Intent(
							LaminaTresActivity.this,
							GameOverActivity.class);
					act.putExtra("game", 1);
					startActivity(act);
					overridePendingTransition(R.anim.fade_in,
							R.anim.fade_out);
					// mp.stop();
					mpTrivia.stop();
				} else if (respuestaCorrecta == 1) {
					if (numeroDeRespuesta != 2) {
						savingAnimation.stop();
						preguntasLan();
						ImageView imaIncorrecta2 = (ImageView) findViewById(R.id.img_respuesta_correcta);
						imaIncorrecta2.setVisibility(View.GONE);
					} else if (numeroDeRespuesta == 2) {
						reconRutaJugada();
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
			};
		});
	}
	public void creaMpOkCorrectaDos() {
		final MediaPlayer mpoK = MediaPlayer
				.create(this, R.raw.sonido_correcto);
		mpoK.start();
		mpoK.setOnCompletionListener(new OnCompletionListener() {
			public void onCompletion(MediaPlayer mp) {
				mp.release();
				if (respuestaCorrecta != 2) {
					mpTrivia.stop();
					Intent act = new Intent(
							LaminaTresActivity.this,
							GameOverActivity.class);
					act.putExtra("game", 1);
					startActivity(act);
					overridePendingTransition(R.anim.fade_in,
							R.anim.fade_out);
					// mp.stop();
				} else if (respuestaCorrecta == 2) {
					if (numeroDeRespuesta != 2) {
						savingAnimation.stop();
						// layPregTrivia.startAnimation(animTriviaOut);
						preguntasLan();
						ImageView imaIncorrecta2 = (ImageView) findViewById(R.id.img_respuesta_correcta);
						imaIncorrecta2.setVisibility(View.GONE);
					} else if (numeroDeRespuesta == 2) {
						reconRutaJugada();
						mpTrivia.stop();
						Intent act = new Intent(
								LaminaTresActivity.this,
								GameOverActivity.class);
						act.putExtra("game", 2);
						startActivity(act);
						overridePendingTransition(
								R.anim.fade_in, R.anim.fade_out);
						// mp.stop();
					}
				}
			};
		});
	}
	public void creaMpFailIncorrectaDos() {
		final MediaPlayer mpFai = MediaPlayer.create(this, R.raw.sonido_incorrecto_large);
		mpFai.start();
		mpFai.setOnCompletionListener(new OnCompletionListener() {
			public void onCompletion(MediaPlayer mp) {
				mp.release();
				if (respuestaCorrecta != 2) {
					mpTrivia.stop();
					Intent act = new Intent(
							LaminaTresActivity.this,
							GameOverActivity.class);
					act.putExtra("game", 1);
					startActivity(act);
					overridePendingTransition(R.anim.fade_in,
							R.anim.fade_out);
					// mp.stop();
				} else if (respuestaCorrecta == 2) {
					if (numeroDeRespuesta != 2) {
						savingAnimation.stop();
						// layPregTrivia.startAnimation(animTriviaOut);
						preguntasLan();
						ImageView imaIncorrecta2 = (ImageView) findViewById(R.id.img_respuesta_correcta);
						imaIncorrecta2.setVisibility(View.GONE);
					} else if (numeroDeRespuesta == 2) {
						reconRutaJugada();
						mpTrivia.stop();
						Intent act = new Intent(
								LaminaTresActivity.this,
								GameOverActivity.class);
						act.putExtra("game", 2);
						startActivity(act);
						overridePendingTransition(
								R.anim.fade_in, R.anim.fade_out);
						// mp.stop();
					}
				}
			};
		});
	}
	public void creaMpOkCorrectaTres() {
		final MediaPlayer mpoK = MediaPlayer
				.create(this, R.raw.sonido_correcto);
		mpoK.start();
		mpoK.setOnCompletionListener(new OnCompletionListener() {
			public void onCompletion(MediaPlayer mp) {
				mp.release();
				if (respuestaCorrecta != 3) {
					mpTrivia.stop();
					Intent act = new Intent(
							LaminaTresActivity.this,
							GameOverActivity.class);
					act.putExtra("game", 1);
					startActivity(act);
					overridePendingTransition(R.anim.fade_in,
							R.anim.fade_out);
					// mp.stop();
				} else if (respuestaCorrecta == 3) {
					if (numeroDeRespuesta != 2) {
						savingAnimation.stop();
						// layPregTrivia.startAnimation(animTriviaOut);
						preguntasLan();
						ImageView imaIncorrecta2 = (ImageView) findViewById(R.id.img_respuesta_correcta);
						imaIncorrecta2.setVisibility(View.GONE);
					} else if (numeroDeRespuesta == 2) {
						reconRutaJugada();
						mpTrivia.stop();
						Intent act = new Intent(
								LaminaTresActivity.this,
								GameOverActivity.class);
						act.putExtra("game", 2);
						startActivity(act);
						overridePendingTransition(
								R.anim.fade_in, R.anim.fade_out);
						// mp.stop();
					}
				}
			};
		});
	}
	public void creaMpFailIncorrectaTres() {
		final MediaPlayer mpFai = MediaPlayer.create(this, R.raw.sonido_incorrecto_large);
		mpFai.start();
		mpFai.setOnCompletionListener(new OnCompletionListener() {
			public void onCompletion(MediaPlayer mp) {
				mp.release();
				if (respuestaCorrecta != 3) {
					mpTrivia.stop();
					Intent act = new Intent(
							LaminaTresActivity.this,
							GameOverActivity.class);
					act.putExtra("game", 1);
					startActivity(act);
					overridePendingTransition(R.anim.fade_in,
							R.anim.fade_out);
					// mp.stop();
				} else if (respuestaCorrecta == 3) {
					if (numeroDeRespuesta != 2) {
						savingAnimation.stop();
						// layPregTrivia.startAnimation(animTriviaOut);
						preguntasLan();
						ImageView imaIncorrecta2 = (ImageView) findViewById(R.id.img_respuesta_correcta);
						imaIncorrecta2.setVisibility(View.GONE);
					} else if (numeroDeRespuesta == 2) {
						reconRutaJugada();
						mpTrivia.stop();
						Intent act = new Intent(
								LaminaTresActivity.this,
								GameOverActivity.class);
						act.putExtra("game", 2);
						startActivity(act);
						overridePendingTransition(
								R.anim.fade_in, R.anim.fade_out);
						// mp.stop();
					}
				}
			};
		});
	}

	public void btnRespuestas() {
		// /////// RESPUESTA 1 /////////////
		findViewById(R.id.btn_respuesta1).setOnClickListener(
				new OnClickListener() {
					public void onClick(View arg0) {
						creaSonidoMundo();
						respuestaUno.setBackgroundResource(R.drawable.botoncomenzar);
						imgMundoTrivia.setVisibility(View.VISIBLE);
						setAnimationListenerRotacionMundo();
						imgMundoTrivia.startAnimation(animMundoRotacion);
						respuestaUno.setEnabled(false);
						respuestaDos.setEnabled(false);
						respuestaTres.setEnabled(false);
						// //////////////////////
					}
				});
		// /////////////////////////////////
		// /////// RESPUESTA 2 /////////////
		findViewById(R.id.btn_respuesta2).setOnClickListener(
				new OnClickListener() {
					public void onClick(View arg0) {
						creaSonidoMundo();
						respuestaDos.setBackgroundResource(R.drawable.botoncomenzar);
						respuestaUno.setEnabled(false);
						respuestaDos.setEnabled(false);
						respuestaTres.setEnabled(false);
						setAnimationListenerRotacionMundoDos();
						imgMundoTrivia.startAnimation(animMundoRotacion);
						// //////////////////////
					}
				});
		// /////////////////////////////////
		// /////// RESPUESTA 3 /////////////
		findViewById(R.id.btn_respuesta3).setOnClickListener(
				new OnClickListener() {
					public void onClick(View arg0) {
						creaSonidoMundo();
						respuestaTres
								.setBackgroundResource(R.drawable.botoncomenzar);
						respuestaUno.setEnabled(false);
						respuestaDos.setEnabled(false);
						respuestaTres.setEnabled(false);
						setAnimationListenerRotacionMundoTres();
						imgMundoTrivia.startAnimation(animMundoRotacion);
						// //////////////////////
					}
				});
		// /////////////////////////////////
	}

	public int generaRandom(int max) {
		int min = 1;
		// int max = 2;

		Random r = new Random();
		int i1 = r.nextInt(max - min + 1) + min;
		return i1;
	}

	public void reconRutaJugada() {
		Bundle bundle = getIntent().getExtras();
		switch (bundle.getInt("ruta")) {
		case 1:
			LaminaBienvenidaActivity.btn1 = 1;
			break;
		case 2:
			LaminaBienvenidaActivity.btn2 = 1;
			break;
		case 3:
			LaminaBienvenidaActivity.btn3 = 1;
			break;
		case 4:
			LaminaBienvenidaActivity.btn4 = 1;
			break;
		case 5:
			LaminaBienvenidaActivity.btn5 = 1;
			break;
		case 6:
			LaminaBienvenidaActivity.btn6 = 1;
			break;
		case 7:
			LaminaBienvenidaActivity.btn7 = 1;
			break;
		case 8:
			LaminaBienvenidaActivity.btn8 = 1;
			break;
		case 9:
			LaminaBienvenidaActivity.btn9 = 1;
			break;
		case 10:
			LaminaBienvenidaActivity.btn10 = 1;
			break;
		case 11:
			LaminaBienvenidaActivity.btn11 = 1;
			break;
		case 12:
			LaminaBienvenidaActivity.btn12 = 1;
			break;
		case 13:
			LaminaBienvenidaActivity.btn13 = 1;
			break;
		case 14:
			LaminaBienvenidaActivity.btn14 = 1;
			break;
		case 15:
			LaminaBienvenidaActivity.btn15 = 1;
			break;
		case 16:
			LaminaBienvenidaActivity.btn16 = 1;
			break;
		case 17:
			LaminaBienvenidaActivity.btn17 = 1;
			break;
		case 18:
			LaminaBienvenidaActivity.btn18 = 1;
			break;
		case 19:
			LaminaBienvenidaActivity.btn19 = 1;
			break;
		case 20:
			LaminaBienvenidaActivity.btn20 = 1;
			break;
		case 21:
			LaminaBienvenidaActivity.btn21 = 1;
			break;
		}
	}

	public void creaSalidaTrivia() {
		MediaPlayer mpSalidaTrivia = MediaPlayer.create(this,
				R.raw.igh_fast_swoosh);
		mpSalidaTrivia.start();
		mpSalidaTrivia.setOnCompletionListener(new OnCompletionListener() {
			public void onCompletion(MediaPlayer mpSali) {
				mpSali.release();
			};
		});
	}

	public void creaSalidaTrivia2() {
		MediaPlayer mpSalidaTrivia2 = MediaPlayer.create(this,
				R.raw.igh_fast_swoosh_large);
		mpSalidaTrivia2.start();
		mpSalidaTrivia2.setOnCompletionListener(new OnCompletionListener() {
			public void onCompletion(MediaPlayer mpSalida) {
				mpSalida.release();
			};
		});
	}
	public void creaSalidaTrivia3() {
		MediaPlayer mpSalidaTrivia3 = MediaPlayer.create(this,
				R.raw.igh_fast_swoosh_large_02);
		mpSalidaTrivia3.start();
		mpSalidaTrivia3.setOnCompletionListener(new OnCompletionListener() {
			public void onCompletion(MediaPlayer mpSalida3) {
				mpSalida3.release();
			};
		});
	}

	public void creaSonidoMundo() {
		MediaPlayer mpMundo = MediaPlayer.create(this, R.raw.engranaje_mundo);
		mpMundo.start();
		mpMundo.setOnCompletionListener(new OnCompletionListener() {
			public void onCompletion(MediaPlayer mpMundoS) {
				mpMundoS.release();
			};
		});
	}

	public void fadeInWhite() {
		imgWhiteTres.setVisibility(View.VISIBLE);
		imgWhiteTres.setAlpha(0f);
		imgWhiteTres.animate().setDuration(800).alpha(1)
				.setListener(new AnimatorListenerAdapter() {
					@Override
					public void onAnimationEnd(Animator animation) {
						imgWhiteTres.setVisibility(View.VISIBLE);
						videoBackTriviaB.setVideoPath("android.resource://com.caromatias.apppisodigitallan/"+ R.raw.back_trivia_1);
						videoBackTrivia.setVisibility(View.GONE);
						videoBackTriviaB.start();
						creaSalidaTrivia3();
						fadeOutWhite();
					}
				});
	}

	public void fadeOutWhite() {
		imgWhiteTres.animate().setDuration(800).alpha(0)
				.setListener(new AnimatorListenerAdapter() {
					@Override
					public void onAnimationEnd(Animator animation) {
						imgWhiteTres.setVisibility(View.GONE);
					}
				});
	}
	
	public void setAnimacionListener(int opcion) {
		animMundoOut = AnimationUtils.loadAnimation(this,R.anim.anim_translate_mundo_out);
		switch (opcion) {
		case 1:
			animMundoOut.setAnimationListener(new Animation.AnimationListener() {
				@Override
				public void onAnimationEnd(Animation animation) {
					preguntaTrivia.setText(getResources().getString(
							R.string.planArg));
					respuestaUno.setText("a) "
							+ getResources().getString(R.string.rlanArg_1));
					respuestaDos.setText("b) "
							+ getResources().getString(R.string.rlanArg_2));
					respuestaTres.setText("c) "
							+ getResources().getString(R.string.rlanArg_3));
					respuestaCorrecta = 3;
					numeroDeRespuesta = 2;
					layPregTrivia.startAnimation(animMundoIn);
					imgMundoTrivia.startAnimation(animMundoIn);
				}

				@Override
				public void onAnimationRepeat(Animation arg0) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onAnimationStart(Animation animation) {
					// TODO Auto-generated method stub

				}
			});
			break;
		case 3:
			animMundoOut.setAnimationListener(new Animation.AnimationListener() {
				@Override
				public void onAnimationEnd(Animation animation) {
					preguntaTrivia.setText(getResources().getString(
							R.string.planRio));
					respuestaUno.setText("a) "
							+ getResources().getString(R.string.rlanRio_1));
					respuestaDos.setText("b) "
							+ getResources().getString(R.string.rlanRio_2));
					respuestaTres.setText("c) "
							+ getResources().getString(R.string.rlanRio_3));
					respuestaCorrecta = 2;
					numeroDeRespuesta = 2;
					// layPregTrivia.startAnimation(animTriviaIn);
					layPregTrivia.startAnimation(animMundoIn);
					imgMundoTrivia.startAnimation(animMundoIn);
					// mpSalidaTrivia.start();
				}

				@Override
				public void onAnimationRepeat(Animation arg0) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onAnimationStart(Animation animation) {
					// TODO Auto-generated method stub

				}
			});
			break;
		case 4:
			animMundoOut.setAnimationListener(new Animation.AnimationListener() {
				@Override
				public void onAnimationEnd(Animation animation) {
					preguntaTrivia.setText(getResources().getString(
							R.string.planBogota));
					respuestaUno.setText("a) "
							+ getResources().getString(R.string.rlanBogota_1));
					respuestaDos.setText("b) "
							+ getResources().getString(R.string.rlanBogota_2));
					respuestaTres.setText("c) "
							+ getResources().getString(R.string.rlanBogota_3));
					respuestaCorrecta = 1;
					numeroDeRespuesta = 2;
					// layPregTrivia.startAnimation(animTriviaIn);
					layPregTrivia.startAnimation(animMundoIn);
					imgMundoTrivia.startAnimation(animMundoIn);
					// mpSalidaTrivia.start();
				}

				@Override
				public void onAnimationRepeat(Animation arg0) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onAnimationStart(Animation animation) {
					// TODO Auto-generated method stub

				}
			});
			break;
		case 11:
			animMundoOut.setAnimationListener(new Animation.AnimationListener() {
				@Override
				public void onAnimationEnd(Animation animation) {
					preguntaTrivia.setText(getResources().getString(
							R.string.planLosAngeles));
					respuestaUno.setText("a) "
							+ getResources().getString(
									R.string.rlanLosAngeles_1));
					respuestaDos.setText("b) "
							+ getResources().getString(
									R.string.rlanLosAngeles_2));
					respuestaTres.setText("c) "
							+ getResources().getString(
									R.string.rlanLosAngeles_3));
					respuestaCorrecta = 2;
					numeroDeRespuesta = 2;
					// layPregTrivia.startAnimation(animTriviaIn);
					layPregTrivia.startAnimation(animMundoIn);
					imgMundoTrivia.startAnimation(animMundoIn);
					// mpSalidaTrivia.start();
				}

				@Override
				public void onAnimationRepeat(Animation arg0) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onAnimationStart(Animation animation) {
					// TODO Auto-generated method stub

				}
			});
			break;
		case 13:
			animMundoOut.setAnimationListener(new Animation.AnimationListener() {
				@Override
				public void onAnimationEnd(Animation animation) {
					preguntaTrivia.setText(getResources().getString(
							R.string.planMexico));
					respuestaUno.setText("a) "
							+ getResources().getString(R.string.rlanMexico_1));
					respuestaDos.setText("b) "
							+ getResources().getString(R.string.rlanMexico_2));
					respuestaTres.setText("c) "
							+ getResources().getString(R.string.rlanMexico_3));
					respuestaCorrecta = 3;
					numeroDeRespuesta = 2;
					// layPregTrivia.startAnimation(animTriviaIn);
					layPregTrivia.startAnimation(animMundoIn);
					imgMundoTrivia.startAnimation(animMundoIn);
					// mpSalidaTrivia.start();
				}

				@Override
				public void onAnimationRepeat(Animation arg0) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onAnimationStart(Animation animation) {
					// TODO Auto-generated method stub

				}
			});
			break;
		case 21:
			animMundoOut.setAnimationListener(new Animation.AnimationListener() {
				@Override
				public void onAnimationEnd(Animation animation) {
					preguntaTrivia.setText(getResources().getString(
							R.string.planLimaCuz));
					respuestaUno.setText("a) "
							+ getResources().getString(R.string.rlanLimaCuz_1));
					respuestaDos.setText("b) "
							+ getResources().getString(R.string.rlanLimaCuz_2));
					respuestaTres.setText("c) "
							+ getResources().getString(R.string.rlanLimaCuz_3));
					respuestaCorrecta = 2;
					numeroDeRespuesta = 2;
					// layPregTrivia.startAnimation(animTriviaIn);
					layPregTrivia.startAnimation(animMundoIn);
					imgMundoTrivia.startAnimation(animMundoIn);
					// mpSalidaTrivia.start();
				}

				@Override
				public void onAnimationRepeat(Animation arg0) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onAnimationStart(Animation animation) {
					// TODO Auto-generated method stub

				}
			});
			break;
		default:
			int numeroRandom = generaRandom(14);
			switch (numeroRandom) {
			case 1:
				animMundoOut.setAnimationListener(new Animation.AnimationListener() {
					@Override
					public void onAnimationEnd(Animation animation) {
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
						// mpSalidaTrivia.start();
					}

					@Override
					public void onAnimationRepeat(Animation arg0) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onAnimationStart(Animation animation) {
						// TODO Auto-generated method stub

					}
				});
				break;
			case 2:
				animMundoOut.setAnimationListener(new Animation.AnimationListener() {
					@Override
					public void onAnimationEnd(Animation animation) {
						preguntaTrivia.setText(getResources().getString(
								R.string.plan7));
						respuestaUno.setText("a) "
								+ getResources().getString(R.string.rlan7_1));
						respuestaDos.setText("b) "
								+ getResources().getString(R.string.rlan7_2));
						respuestaTres.setText("c) "
								+ getResources().getString(R.string.rlan7_3));
						respuestaCorrecta = 1;
						numeroDeRespuesta = 2;
						// layPregTrivia.startAnimation(animTriviaIn);
						layPregTrivia.startAnimation(animMundoIn);
						imgMundoTrivia.startAnimation(animMundoIn);
						// mpSalidaTrivia.start();
					}

					@Override
					public void onAnimationRepeat(Animation arg0) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onAnimationStart(Animation animation) {
						// TODO Auto-generated method stub

					}
				});
				break;
			case 3:
				animMundoOut.setAnimationListener(new Animation.AnimationListener() {
					@Override
					public void onAnimationEnd(Animation animation) {
						preguntaTrivia.setText(getResources().getString(
								R.string.plan3));
						respuestaUno.setText("a) "
								+ getResources().getString(R.string.rlan3_1));
						respuestaDos.setText("b) "
								+ getResources().getString(R.string.rlan3_2));
						respuestaTres.setText("c) "
								+ getResources().getString(R.string.rlan3_3));
						respuestaCorrecta = 2;
						numeroDeRespuesta = 2;
						// layPregTrivia.startAnimation(animTriviaIn);
						layPregTrivia.startAnimation(animMundoIn);
						imgMundoTrivia.startAnimation(animMundoIn);
						// mpSalidaTrivia.start();
					}

					@Override
					public void onAnimationRepeat(Animation arg0) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onAnimationStart(Animation animation) {
						// TODO Auto-generated method stub

					}
				});
				break;
			case 4:
				animMundoOut.setAnimationListener(new Animation.AnimationListener() {
					@Override
					public void onAnimationEnd(Animation animation) {
						preguntaTrivia.setText(getResources().getString(
								R.string.plan3));
						respuestaUno.setText("a) "
								+ getResources().getString(R.string.rlan3_1));
						respuestaDos.setText("b) "
								+ getResources().getString(R.string.rlan3_2));
						respuestaTres.setText("c) "
								+ getResources().getString(R.string.rlan3_3));
						respuestaCorrecta = 2;
						numeroDeRespuesta = 2;
						// layPregTrivia.startAnimation(animTriviaIn);
						layPregTrivia.startAnimation(animMundoIn);
						imgMundoTrivia.startAnimation(animMundoIn);
						// mpSalidaTrivia.start();
					}

					@Override
					public void onAnimationRepeat(Animation arg0) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onAnimationStart(Animation animation) {
						// TODO Auto-generated method stub

					}
				});
				break;
			case 5:
				animMundoOut.setAnimationListener(new Animation.AnimationListener() {
					@Override
					public void onAnimationEnd(Animation animation) {
						preguntaTrivia.setText(getResources().getString(
								R.string.plan9));
						respuestaUno.setText("a) "
								+ getResources().getString(R.string.rlan9_1));
						respuestaDos.setText("b) "
								+ getResources().getString(R.string.rlan9_2));
						respuestaTres.setText("c) "
								+ getResources().getString(R.string.rlan9_3));
						respuestaCorrecta = 2;
						numeroDeRespuesta = 2;
						// layPregTrivia.startAnimation(animTriviaIn);
						layPregTrivia.startAnimation(animMundoIn);
						imgMundoTrivia.startAnimation(animMundoIn);
						// mpSalidaTrivia.start();
					}

					@Override
					public void onAnimationRepeat(Animation arg0) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onAnimationStart(Animation animation) {
						// TODO Auto-generated method stub

					}
				});
				break;
			case 6:
				animMundoOut.setAnimationListener(new Animation.AnimationListener() {
					@Override
					public void onAnimationEnd(Animation animation) {
						preguntaTrivia.setText(getResources().getString(
								R.string.plan12));
						respuestaUno.setText("a) "
								+ getResources().getString(R.string.rlan12_1));
						respuestaDos.setText("b) "
								+ getResources().getString(R.string.rlan12_2));
						respuestaTres.setText("c) "
								+ getResources().getString(R.string.rlan12_3));
						respuestaCorrecta = 2;
						numeroDeRespuesta = 2;
						// layPregTrivia.startAnimation(animTriviaIn);
						layPregTrivia.startAnimation(animMundoIn);
						imgMundoTrivia.startAnimation(animMundoIn);
						// mpSalidaTrivia.start();
					}

					@Override
					public void onAnimationRepeat(Animation arg0) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onAnimationStart(Animation animation) {
						// TODO Auto-generated method stub

					}
				});
				break;
			case 7:
				animMundoOut.setAnimationListener(new Animation.AnimationListener() {
					@Override
					public void onAnimationEnd(Animation animation) {
						preguntaTrivia.setText(getResources().getString(
								R.string.plan14));
						respuestaUno.setText("a) "
								+ getResources().getString(R.string.rlan14_1));
						respuestaDos.setText("b) "
								+ getResources().getString(R.string.rlan14_2));
						respuestaTres.setText("c) "
								+ getResources().getString(R.string.rlan14_3));
						respuestaCorrecta = 1;
						numeroDeRespuesta = 2;
						// layPregTrivia.startAnimation(animTriviaIn);
						layPregTrivia.startAnimation(animMundoIn);
						imgMundoTrivia.startAnimation(animMundoIn);
						// mpSalidaTrivia.start();
					}

					@Override
					public void onAnimationRepeat(Animation arg0) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onAnimationStart(Animation animation) {
						// TODO Auto-generated method stub

					}
				});
				break;
			case 8:
				animMundoOut.setAnimationListener(new Animation.AnimationListener() {
					@Override
					public void onAnimationEnd(Animation animation) {
						preguntaTrivia.setText(getResources().getString(
								R.string.plan7));
						respuestaUno.setText("a) "
								+ getResources().getString(R.string.rlan7_1));
						respuestaDos.setText("b) "
								+ getResources().getString(R.string.rlan7_2));
						respuestaTres.setText("c) "
								+ getResources().getString(R.string.rlan7_3));
						respuestaCorrecta = 1;
						numeroDeRespuesta = 2;
						// layPregTrivia.startAnimation(animTriviaIn);
						layPregTrivia.startAnimation(animMundoIn);
						imgMundoTrivia.startAnimation(animMundoIn);
						// mpSalidaTrivia.start();
					}

					@Override
					public void onAnimationRepeat(Animation arg0) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onAnimationStart(Animation animation) {
						// TODO Auto-generated method stub

					}
				});
				break;
			case 9:
				animMundoOut.setAnimationListener(new Animation.AnimationListener() {
					@Override
					public void onAnimationEnd(Animation animation) {
						preguntaTrivia.setText(getResources().getString(
								R.string.plan9));
						respuestaUno.setText("a) "
								+ getResources().getString(R.string.rlan9_1));
						respuestaDos.setText("b) "
								+ getResources().getString(R.string.rlan9_2));
						respuestaTres.setText("c) "
								+ getResources().getString(R.string.rlan9_3));
						respuestaCorrecta = 2;
						numeroDeRespuesta = 2;
						// layPregTrivia.startAnimation(animTriviaIn);
						layPregTrivia.startAnimation(animMundoIn);
						imgMundoTrivia.startAnimation(animMundoIn);
						// mpSalidaTrivia.start();
					}

					@Override
					public void onAnimationRepeat(Animation arg0) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onAnimationStart(Animation animation) {
						// TODO Auto-generated method stub

					}
				});
				break;
			case 10:
				animMundoOut.setAnimationListener(new Animation.AnimationListener() {
					@Override
					public void onAnimationEnd(Animation animation) {
						preguntaTrivia.setText(getResources().getString(
								R.string.plan10));
						respuestaUno.setText("a) "
								+ getResources().getString(R.string.rlan10_1));
						respuestaDos.setText("b) "
								+ getResources().getString(R.string.rlan10_2));
						respuestaTres.setText("c) "
								+ getResources().getString(R.string.rlan10_3));
						respuestaCorrecta = 1;
						numeroDeRespuesta = 2;
						// layPregTrivia.startAnimation(animTriviaIn);
						layPregTrivia.startAnimation(animMundoIn);
						imgMundoTrivia.startAnimation(animMundoIn);
						// mpSalidaTrivia.start();
					}

					@Override
					public void onAnimationRepeat(Animation arg0) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onAnimationStart(Animation animation) {
						// TODO Auto-generated method stub

					}
				});
				break;
			case 11:
				animMundoOut.setAnimationListener(new Animation.AnimationListener() {
					@Override
					public void onAnimationEnd(Animation animation) {
						preguntaTrivia.setText(getResources().getString(
								R.string.plan11));
						respuestaUno.setText("a) "
								+ getResources().getString(R.string.rlan11_1));
						respuestaDos.setText("b) "
								+ getResources().getString(R.string.rlan11_2));
						respuestaTres.setText("c) "
								+ getResources().getString(R.string.rlan11_3));
						respuestaCorrecta = 3;
						numeroDeRespuesta = 2;
						// layPregTrivia.startAnimation(animTriviaIn);
						layPregTrivia.startAnimation(animMundoIn);
						imgMundoTrivia.startAnimation(animMundoIn);
						// mpSalidaTrivia.start();
					}

					@Override
					public void onAnimationRepeat(Animation arg0) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onAnimationStart(Animation animation) {
						// TODO Auto-generated method stub

					}
				});
				break;
			case 12:
				animMundoOut.setAnimationListener(new Animation.AnimationListener() {
					@Override
					public void onAnimationEnd(Animation animation) {
						preguntaTrivia.setText(getResources().getString(
								R.string.plan12));
						respuestaUno.setText("a) "
								+ getResources().getString(R.string.rlan12_1));
						respuestaDos.setText("b) "
								+ getResources().getString(R.string.rlan12_2));
						respuestaTres.setText("c) "
								+ getResources().getString(R.string.rlan12_3));
						respuestaCorrecta = 2;
						numeroDeRespuesta = 2;
						// layPregTrivia.startAnimation(animTriviaIn);
						layPregTrivia.startAnimation(animMundoIn);
						imgMundoTrivia.startAnimation(animMundoIn);
						// mpSalidaTrivia.start();
					}

					@Override
					public void onAnimationRepeat(Animation arg0) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onAnimationStart(Animation animation) {
						// TODO Auto-generated method stub

					}
				});
				break;
			case 13:
				animMundoOut.setAnimationListener(new Animation.AnimationListener() {
					@Override
					public void onAnimationEnd(Animation animation) {
						preguntaTrivia.setText(getResources().getString(
								R.string.plan13));
						respuestaUno.setText("a) "
								+ getResources().getString(R.string.rlan13_1));
						respuestaDos.setText("b) "
								+ getResources().getString(R.string.rlan13_2));
						respuestaTres.setText("c) "
								+ getResources().getString(R.string.rlan13_3));
						respuestaCorrecta = 1;
						numeroDeRespuesta = 2;
						// layPregTrivia.startAnimation(animTriviaIn);
						layPregTrivia.startAnimation(animMundoIn);
						imgMundoTrivia.startAnimation(animMundoIn);
						// mpSalidaTrivia.start();
					}

					@Override
					public void onAnimationRepeat(Animation arg0) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onAnimationStart(Animation animation) {
						// TODO Auto-generated method stub

					}
				});
				break;
			case 14:
				animMundoOut.setAnimationListener(new Animation.AnimationListener() {
					@Override
					public void onAnimationEnd(Animation animation) {
						preguntaTrivia.setText(getResources().getString(
								R.string.plan14));
						respuestaUno.setText("a) "
								+ getResources().getString(R.string.rlan14_1));
						respuestaDos.setText("b) "
								+ getResources().getString(R.string.rlan14_2));
						respuestaTres.setText("c) "
								+ getResources().getString(R.string.rlan14_3));
						respuestaCorrecta = 1;
						numeroDeRespuesta = 2;
						// layPregTrivia.startAnimation(animTriviaIn);
						layPregTrivia.startAnimation(animMundoIn);
						imgMundoTrivia.startAnimation(animMundoIn);
						// mpSalidaTrivia.start();
					}

					@Override
					public void onAnimationRepeat(Animation arg0) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onAnimationStart(Animation animation) {
						// TODO Auto-generated method stub

					}
				});
				break;
			default:
				break;
			}
			break;
		}
	}
	public void setAnimationListenerRotacionMundo() {
		animMundoRotacion = AnimationUtils.loadAnimation(this,R.anim.anim_rotacion_mundo);
		animMundoRotacion.setAnimationListener(new Animation.AnimationListener() {
					@Override
					public void onAnimationEnd(Animation animation) {
						switch (respuestaCorrecta) {
						case 1:
							respuestaUno.setBackgroundResource(R.anim.anim_respuesta_correcta);
							savingAnimation = (AnimationDrawable) respuestaUno.getBackground();
							savingAnimation.start();
							ImageView imaCorrecta = (ImageView) findViewById(R.id.img_respuesta_correcta);
							imaCorrecta.setVisibility(View.VISIBLE);
							creaMpOkCorrectaUno();
							break;
						case 2:
							respuestaDos.setBackgroundResource(R.anim.anim_respuesta_correcta);
							savingAnimation = (AnimationDrawable) respuestaDos.getBackground();
							savingAnimation.start();
							ImageView imaIncorrecta = (ImageView) findViewById(R.id.img_respuesta_incorrecta);
							imaIncorrecta.setVisibility(View.VISIBLE);
							creaMpFailIncorrectaUno();
							break;
						case 3:
							respuestaTres.setBackgroundResource(R.anim.anim_respuesta_correcta);
							savingAnimation = (AnimationDrawable) respuestaTres.getBackground();
							savingAnimation.start();
							ImageView imaIncorrecta2 = (ImageView) findViewById(R.id.img_respuesta_incorrecta);
							imaIncorrecta2.setVisibility(View.VISIBLE);
							creaMpFailIncorrectaUno();
							break;
						default:
							break;
						}
					}

					@Override
					public void onAnimationRepeat(Animation arg0) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onAnimationStart(Animation animation) {
						// TODO Auto-generated method stub

					}
				});
	}
	public void setAnimationListenerRotacionMundoDos() {
		animMundoRotacion = AnimationUtils.loadAnimation(this,R.anim.anim_rotacion_mundo);
		animMundoRotacion.setAnimationListener(new Animation.AnimationListener() {
					@Override
					public void onAnimationEnd(Animation animation) {
						switch (respuestaCorrecta) {
						case 1:
							respuestaUno
									.setBackgroundResource(R.anim.anim_respuesta_correcta);
							savingAnimation = (AnimationDrawable) respuestaUno
									.getBackground();
							savingAnimation.start();
							ImageView imaIncorrecta = (ImageView) findViewById(R.id.img_respuesta_incorrecta);
							imaIncorrecta.setVisibility(View.VISIBLE);
							creaMpFailIncorrectaDos();
							break;
						case 2:
							respuestaDos
									.setBackgroundResource(R.anim.anim_respuesta_correcta);
							savingAnimation = (AnimationDrawable) respuestaDos
									.getBackground();
							savingAnimation.start();
							ImageView imaCorrecta = (ImageView) findViewById(R.id.img_respuesta_correcta);
							imaCorrecta.setVisibility(View.VISIBLE);
							creaMpOkCorrectaDos();
							break;
						case 3:
							respuestaTres
									.setBackgroundResource(R.anim.anim_respuesta_correcta);
							savingAnimation = (AnimationDrawable) respuestaTres
									.getBackground();
							savingAnimation.start();
							ImageView imaIncorrecta2 = (ImageView) findViewById(R.id.img_respuesta_incorrecta);
							imaIncorrecta2.setVisibility(View.VISIBLE);
							creaMpFailIncorrectaDos();
							break;
						default:
							break;
						}
					}

					@Override
					public void onAnimationRepeat(Animation arg0) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onAnimationStart(Animation animation) {
						// TODO Auto-generated method stub

					}
				});
	}
	public void setAnimationListenerRotacionMundoTres() {
		animMundoRotacion = AnimationUtils.loadAnimation(this,R.anim.anim_rotacion_mundo);
		animMundoRotacion.setAnimationListener(new Animation.AnimationListener() {
					@Override
					public void onAnimationEnd(Animation animation) {
						switch (respuestaCorrecta) {
						case 1:
							respuestaUno
									.setBackgroundResource(R.anim.anim_respuesta_correcta);
							savingAnimation = (AnimationDrawable) respuestaUno
									.getBackground();
							savingAnimation.start();
							ImageView imaIncorrecta = (ImageView) findViewById(R.id.img_respuesta_incorrecta);
							imaIncorrecta.setVisibility(View.VISIBLE);
							creaMpFailIncorrectaTres();
							break;
						case 2:
							respuestaDos
									.setBackgroundResource(R.anim.anim_respuesta_correcta);
							savingAnimation = (AnimationDrawable) respuestaDos
									.getBackground();
							savingAnimation.start();
							ImageView imaIncorrecta2 = (ImageView) findViewById(R.id.img_respuesta_incorrecta);
							imaIncorrecta2.setVisibility(View.VISIBLE);
							creaMpFailIncorrectaTres();
							break;
						case 3:
							respuestaTres
									.setBackgroundResource(R.anim.anim_respuesta_correcta);
							savingAnimation = (AnimationDrawable) respuestaTres
									.getBackground();
							savingAnimation.start();
							ImageView imaCorrecta = (ImageView) findViewById(R.id.img_respuesta_correcta);
							imaCorrecta.setVisibility(View.VISIBLE);
							creaMpOkCorrectaTres();
							break;
						default:
							break;
						}
					}

					@Override
					public void onAnimationRepeat(Animation arg0) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onAnimationStart(Animation animation) {
						// TODO Auto-generated method stub

					}
				});
	}

	@Override
	public void onBackPressed() {
		return;
	}
}
