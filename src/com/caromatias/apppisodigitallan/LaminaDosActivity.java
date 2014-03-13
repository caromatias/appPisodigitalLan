package com.caromatias.apppisodigitallan;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.SystemClock;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

public class LaminaDosActivity extends Activity {

	private ImageView ivAnimacion;
	private AnimationDrawable savingAnimation;
	private AnimationDrawable savingAnimationLuz;
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
	private Animation animImgBackDespegue;
	// ///////////////////////////////////
	public int rutaSeleccionada = 0;
	private int currentRotation = 0;
	private TextView txtPorcentajeCarga;
	private RelativeLayout layPopUpCarga;
	private int comienzaCarga = 0;
	private TextView tiempoParaCarga;
	private TextView tiempoParaCargaCero;
	private Button activaCarga;
	private int intentosCarga = 0;
	private RelativeLayout layMasterComp;
	private ImageView despegueOk;
	private ImageView despegueFail;
	private ImageView cargaOk;
	private ImageView cargaFail;
	private VideoView videoBackCarga;
	private ImageView imgBackCarga;
	private Animation animMensajesDespegue;
	private Animation animMensajesDespegueOut;
	private Animation animMensajesDespegueFailIn;
	private Animation animMensajesDespegueFailOut;
	private RelativeLayout imgLuzEstado;
	private ImageView flecha;
	private ImageView flechaCarga;
	private Animation animFlechaRebote;
	// private MediaPlayer mp;
	// private MediaPlayer mpFail;
	// private MediaPlayer mpOk;
	public static MediaPlayer mpDespegue;
	private MediaPlayer mpMapaJuego;
	private VideoView videoInterface;
	private ImageView imgWhiteDos;
	private Animation animVideoMain;
	private Animation animVideoMainOut;
	private boolean pause = false;
	private int paso = 1;
	private RelativeLayout nextInterface;
	private Animation animNetxInterfaceIn;
	private Animation animNetxInterfaceOut;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lamina_dos);

		findViewById(R.id.btn_comenzar_juego_despegue).setEnabled(true);
		findViewById(R.id.btn_comenzar_juego_carga).setEnabled(true);
		mpMapaJuego = MediaPlayer.create(this, R.raw.a_life_begins);
		AudioManager audio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
		int currentVolume = audio.getStreamVolume(AudioManager.STREAM_MUSIC);
		int maxVolume = audio.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		float percent = 0.6f;
		int seventyVolume = (int) (maxVolume * percent);
		audio.setStreamVolume(AudioManager.STREAM_MUSIC, seventyVolume, 0);
		mpMapaJuego.start();

		mpDespegue = MediaPlayer.create(this, R.raw.airplane_on_board);
		mpDespegue.setOnCompletionListener(new OnCompletionListener() {
			public void onCompletion(MediaPlayer mpDes) {
				mpDes.release();
			};
		});

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
		botonStop = (Button) findViewById(R.id.button1);
		botonStop.setEnabled(false);
		intentoUno = (ImageView) findViewById(R.id.img_intento_uno);
		intentoDos = (ImageView) findViewById(R.id.img_intento_dos);
		intentoTres = (ImageView) findViewById(R.id.img_intento_tres);
		txtPorcentajeCarga = (TextView) findViewById(R.id.txt_tacometro_porcentaje);
		Typeface tf = Typeface.createFromAsset(getAssets(),
				"fonts/wwDigital.ttf");
		tiempoParaCarga = (TextView) findViewById(R.id.txt_tiempo_juego_carga);
		tiempoParaCargaCero = (TextView) findViewById(R.id.txt_tiempo_juego_carga_0);
		tiempoParaCargaCero.setTypeface(tf);
		tiempoParaCarga.setTypeface(tf);
		activaCarga = (Button) findViewById(R.id.btn_activa_carga);
		layMasterComp = (RelativeLayout) findViewById(R.id.lay_master_rutas_comp);
		despegueOk = (ImageView) findViewById(R.id.ok_despegue);
		despegueFail = (ImageView) findViewById(R.id.fail_despegue);
		imgLuzEstado = (RelativeLayout) findViewById(R.id.img_luz_estado);
		flecha = (ImageView) findViewById(R.id.img_flecha_down);
		flechaCarga = (ImageView) findViewById(R.id.img_flecha_carga);
		animMensajesDespegue = AnimationUtils.loadAnimation(this,
				R.anim.anim_scale_translation_world);
		animMensajesDespegueOut = AnimationUtils.loadAnimation(this,
				R.anim.anim_translacion_out);
		animMensajesDespegueFailIn = AnimationUtils.loadAnimation(this,
				R.anim.anim_translacion_in);
		animMensajesDespegueFailOut = AnimationUtils.loadAnimation(this,
				R.anim.anim_translacion_fail_out);
		animFlechaRebote = AnimationUtils.loadAnimation(this,
				R.anim.anim_rebote_flecha);
		animVideoMain = AnimationUtils.loadAnimation(this, R.anim.fade_in);
		animVideoMainOut = AnimationUtils.loadAnimation(this, R.anim.fade_out);
		cargaOk = (ImageView) findViewById(R.id.img_mensaje_carga_ok);
		cargaFail = (ImageView) findViewById(R.id.img_mensaje_carga_fail);
		videoBackCarga = (VideoView) findViewById(R.id.video_back_carga);
		imgBackCarga = (ImageView) findViewById(R.id.img_back_carga);
		imgWhiteDos = (ImageView) findViewById(R.id.img_transicion_interface_uno);
		
		muestraInterfaceJuego();

		findViewById(R.id.button1).setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				estadoProgress = 3;
				switch (intentos) {
				case 1:
					intentoUno.setImageResource(R.drawable.intento_red);
					break;
				case 2:
					intentoDos.setImageResource(R.drawable.intento_red);
					break;
				case 3:
					intentoTres.setImageResource(R.drawable.intento_red);
					break;
				}
				flecha.setVisibility(View.GONE);
			}
		});

		// //////////////////////////////////
		// ///////// BOTON COMENZAR /////////

		final RelativeLayout layPopup = (RelativeLayout) findViewById(R.id.lay_popup_despegue);
		layPopUpCarga = (RelativeLayout) findViewById(R.id.lay_popup_carga);
		final Animation animPopup = AnimationUtils.loadAnimation(this,
				R.anim.anim_mapa_inter_out);
		final ImageView layLogoIzq = (ImageView) findViewById(R.id.img_logo_izq);
		final Animation animLogoIzq = AnimationUtils.loadAnimation(this,
				R.anim.anim_mapa_in);

		videoBackDespegue = (VideoView) findViewById(R.id.video_back_despegue);
		videoBackDespegue
				.setVideoPath("android.resource://com.caromatias.apppisodigitallan/"
						+ R.raw.video_despegue);
		ImagenBackDespegue = (ImageView) findViewById(R.id.img_back_despegue);
		animImgBackDespegue = AnimationUtils.loadAnimation(this,
				R.anim.fade_out);

		findViewById(R.id.btn_comenzar_juego_despegue).setOnClickListener(
				new OnClickListener() {
					public void onClick(View arg0) {
						// mpDespegue.start();
						findViewById(R.id.btn_comenzar_juego_despegue).setEnabled(false);
						AudioManager audio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
						int currentVolume = audio
								.getStreamVolume(AudioManager.STREAM_MUSIC);
						int maxVolume = audio
								.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
						float percent = 0.8f;
						int seventyVolume = (int) (maxVolume * percent);
						audio.setStreamVolume(AudioManager.STREAM_MUSIC,
								seventyVolume, 0);
						layPopup.startAnimation(animPopup);
						layPopup.setVisibility(View.GONE);
						layLogoIzq.setVisibility(View.VISIBLE);
						layLogoIzq.startAnimation(animLogoIzq);
						botonStop.setEnabled(true);
						flecha.setVisibility(View.VISIBLE);
						flecha.startAnimation(animFlechaRebote);
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
										runOnUiThread(new Runnable() // run on
																		// ui
																		// thread
										{
											public void run() {
												creaMpFail();
												flecha.setVisibility(View.GONE);
												despegueFail
														.setVisibility(View.VISIBLE);
												despegueFail
														.startAnimation(animMensajesDespegueFailIn);
												imgLuzEstado
														.setBackgroundResource(R.anim.anim_despegue_fail);
												savingAnimationLuz = (AnimationDrawable) imgLuzEstado
														.getBackground();
												savingAnimationLuz.start();
											}
										});
										intentoDos();
									} else if (mProgressStatus >= 70) {
										runOnUiThread(new Runnable() // run on
																		// ui
																		// thread
										{
											public void run() {
												creaMpOk();
												despegueOk
														.setVisibility(View.VISIBLE);
												despegueOk
														.startAnimation(animMensajesDespegue);
												imgLuzEstado
														.setBackgroundResource(R.anim.anim_despegue_ok);
												savingAnimationLuz = (AnimationDrawable) imgLuzEstado
														.getBackground();
												savingAnimationLuz.start();
												Handler handlerDespegueOkUno = new Handler();
												handlerDespegueOkUno
														.postDelayed(
																new Runnable() {
																	@Override
																	public void run() {
																		videoBackDespegue
																				.start();
																		ImagenBackDespegue
																				.startAnimation(animImgBackDespegue);
																		ImagenBackDespegue
																				.setVisibility(View.GONE);
																	}
																}, 2000);
											}
										});
									}
									break;
								}
								mProgress.setProgress(mProgressStatus);
								runOnUiThread(new Runnable() // run on ui thread
								{
									public void run() {
										textoDos.setText(String
												.valueOf(mProgressStatus + "%"));
										if (estadoProgress == 3
												&& mProgressStatus > 70) {
											// ImagenBackDespegue.startAnimation(animImgBackDespegue);
											// videoBackDespegue.start();
											// ImagenBackDespegue.setVisibility(View.GONE);
										}
									}
								});
							}
						}, delay, period);
						// ///////////////////////////////////////////
					}
				});

		// ////////////////////////////////////////

		findViewById(R.id.btn_comenzar_juego_carga).setOnClickListener(
				new OnClickListener() {
					public void onClick(View arg0) {
						findViewById(R.id.btn_comenzar_juego_carga).setEnabled(false);
						flechaCarga.setVisibility(View.VISIBLE);
						activaCarga.setVisibility(View.VISIBLE);
						flechaCarga.startAnimation(animFlechaRebote);
						layPopUpCarga.startAnimation(animPopup);
						layPopUpCarga.setVisibility(View.GONE);
						// layLogoIzq.setVisibility(View.VISIBLE);
						// layLogoIzq.startAnimation(animLogoIzq);
						muestraCuentaAtas();
					}
				});

		// ///////////////////////////////////////
		desactivaRutas();
		// //////////////////////////////////
		goRutas();
		// //////////////////////////////////

		videoBackDespegue
				.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
					public void onCompletion(MediaPlayer mp) {
						Intent act = new Intent(LaminaDosActivity.this,
								LaminaTresActivity.class);
						act.putExtra("ruta", rutaSeleccionada);
						startActivity(act);
						overridePendingTransition(R.anim.fade_in,
								R.anim.fade_out);
					}
				});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lamina_dos, menu);
		return true;
	}

	public void muestraCuentaAtas() {
		currentRotation = 0;
		comienzaCarga = 0;
		activaCarga.setEnabled(true);
		flechaCarga.setVisibility(View.VISIBLE);
		flechaCarga.startAnimation(animFlechaRebote);
		findViewById(R.id.txt_cuanta_atras_uno).setVisibility(View.VISIBLE);
		findViewById(R.id.lay_pop_cuenta_atras).setVisibility(View.VISIBLE);
		txtPorcentajeCarga.setText("0%");
		final Handler handlerCargaSound = new Handler();
		handlerCargaSound.postDelayed(new Runnable() {
			@Override
			public void run() {
				creaCuentaAtras();
			}
		}, 500);
		final Handler handlerCargaUno = new Handler();
		handlerCargaUno.postDelayed(new Runnable() {
			@Override
			public void run() {
				// Do something after 5s = 5000ms
				findViewById(R.id.txt_cuanta_atras_uno)
						.setVisibility(View.GONE);
				findViewById(R.id.txt_cuanta_atras_dos).setVisibility(
						View.VISIBLE);
			}
		}, 1500);
		final Handler handlerCargaDos = new Handler();
		handlerCargaDos.postDelayed(new Runnable() {
			@Override
			public void run() {
				// Do something after 5s = 5000ms
				findViewById(R.id.txt_cuanta_atras_dos)
						.setVisibility(View.GONE);
				findViewById(R.id.txt_cuanta_atras_tres).setVisibility(
						View.VISIBLE);
			}
		}, 2500);
		final Handler handlerCargaTres = new Handler();
		handlerCargaTres.postDelayed(new Runnable() {
			@Override
			public void run() {
				// Do something after 5s = 5000ms
				flechaCarga.setVisibility(View.GONE);
				findViewById(R.id.txt_cuanta_atras_tres).setVisibility(
						View.GONE);
				findViewById(R.id.lay_pop_cuenta_atras)
						.setVisibility(View.GONE);
				intentosCarga += 1;
				comienzaCuentaAtras();
			}
		}, 3500);
	}

	public void comienzaCuentaAtras() {
		videoBackCarga
				.setVideoPath("android.resource://com.caromatias.apppisodigitallan/"
						+ R.raw.video_carga);
		videoBackCarga.start();
		final Handler handlerCargaImg = new Handler();
		handlerCargaImg.postDelayed(new Runnable() {
			@Override
			public void run() {
				imgBackCarga.setVisibility(View.GONE);
			}
		}, 500);
		comienzaCarga += 1;
		new CountDownTimer(10000, 1000) {

			public void onTick(long millisUntilFinished) {
				tiempoParaCarga.setText("0" + millisUntilFinished / 1000);
			}

			public void onFinish() {
				tiempoParaCarga.setText("00");
				activaCarga.setEnabled(false);
				if (currentRotation < 60) {
					creaMpFail();
					cargaFail.setVisibility(View.VISIBLE);
					cargaFail.startAnimation(animMensajesDespegueFailIn);
					if (intentosCarga < 3) {
						switch (intentosCarga) {
						case 1:
							ImageView intUno = (ImageView) findViewById(R.id.img_intento_carga_uno);
							intUno.setImageResource(R.drawable.intento_red);
							break;
						case 2:
							ImageView intDos = (ImageView) findViewById(R.id.img_intento_carga_dos);
							intDos.setImageResource(R.drawable.intento_red);
							break;
						case 3:
							ImageView intTres = (ImageView) findViewById(R.id.img_intento_carga_tres);
							intTres.setImageResource(R.drawable.intento_red);
							break;
						}
						runOnUiThread(new Runnable() // run on ui thread
						{
							public void run() {
								final Handler handlerCargaQuitaUno = new Handler();
								handlerCargaQuitaUno.postDelayed(new Runnable() {
									@Override
									public void run() {
										cargaFail
												.startAnimation(animMensajesDespegueFailOut);
										cargaFail.setVisibility(View.GONE);
									}
								}, 1500);
								final Handler handlerCargaQuita = new Handler();
								handlerCargaQuita.postDelayed(new Runnable() {
									@Override
									public void run() {
										muestraCuentaAtas();
									}
								}, 2100);
							}
						});
					} else if (intentosCarga == 3) {
						runOnUiThread(new Runnable() // run on ui thread
						{
							public void run() {
								final Handler handlerCargaFinalTres = new Handler();
								handlerCargaFinalTres.postDelayed(
										new Runnable() {
											@Override
											public void run() {
												//creaMpFail();
												mpDespegue.release();
												Intent act = new Intent(
														LaminaDosActivity.this,
														GameOverActivity.class);
												act.putExtra("game", 3);
												startActivity(act);
												overridePendingTransition(
														R.anim.fade_in,
														R.anim.fade_out);
											}
										}, 1500);
							}
						});
					}
					/*
					runOnUiThread(new Runnable() // run on ui thread
					{
						public void run() {
							final Handler handlerCargaQuitaUno = new Handler();
							handlerCargaQuitaUno.postDelayed(new Runnable() {
								@Override
								public void run() {
									cargaFail
											.startAnimation(animMensajesDespegueFailOut);
									cargaFail.setVisibility(View.GONE);
								}
							}, 1500);
							final Handler handlerCargaQuita = new Handler();
							handlerCargaQuita.postDelayed(new Runnable() {
								@Override
								public void run() {
									muestraCuentaAtas();
								}
							}, 2100);
						}
					});
					*/
				} else if (currentRotation >= 60) {
					creaMpOk();
					cargaOk.setVisibility(View.VISIBLE);
					cargaOk.startAnimation(animMensajesDespegueFailIn);
					runOnUiThread(new Runnable() // run on ui thread
					{
						public void run() {
							final Handler handlerCargaQuitaUno = new Handler();
							handlerCargaQuitaUno.postDelayed(new Runnable() {
								@Override
								public void run() {
									cargaOk.startAnimation(animMensajesDespegueFailOut);
									cargaOk.setVisibility(View.GONE);
								}
							}, 2400);
							final Handler handlerCargaQuita = new Handler();
							handlerCargaQuita.postDelayed(new Runnable() {
								@Override
								public void run() {
									Intent act = new Intent(
											LaminaDosActivity.this,
											LaminaTresActivity.class);
									act.putExtra("ruta", rutaSeleccionada);
									startActivity(act);
									overridePendingTransition(R.anim.fade_in,
											R.anim.fade_out);
								}
							}, 3000);
						}
					});
				}
			}
		}.start();
	}

	public void intentoDos() {
		runOnUiThread(new Runnable() // run on ui thread
		{
			public void run() {
				final Handler handlerDespegueMensajeOut = new Handler();
				handlerDespegueMensajeOut.postDelayed(new Runnable() {
					@Override
					public void run() {
						despegueFail
								.startAnimation(animMensajesDespegueFailOut);
						despegueFail.setVisibility(View.GONE);
						savingAnimationLuz.stop();
					}
				}, 3000);
			}
		});
		final TextView textoDos = (TextView) findViewById(R.id.txt_porcentaje);
		int delay = 3000; // delay for 1 sec.
		int period = 3; // repeat every 10 sec.
		estadoProgress = 1;
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
						runOnUiThread(new Runnable() // run on ui thread
						{
							public void run() {
								creaMpFail();
								despegueFail.setVisibility(View.VISIBLE);
								despegueFail
										.startAnimation(animMensajesDespegueFailIn);
								Handler handlerCargaTerminaJuego = new Handler();
								handlerCargaTerminaJuego.postDelayed(
										new Runnable() {
											@Override
											public void run() {
												imgLuzEstado
														.setBackgroundResource(R.anim.anim_despegue_fail);
												savingAnimationLuz = (AnimationDrawable) imgLuzEstado
														.getBackground();
												savingAnimationLuz.start();
											}
										}, 1500);
							}
						});
						intentoTres();
					} else if (mProgressStatus >= 70) {
						runOnUiThread(new Runnable() // run on
						// ui
						// thread
						{
							public void run() {
								creaMpOk();
								despegueOk.setVisibility(View.VISIBLE);
								despegueOk.startAnimation(animMensajesDespegue);
								imgLuzEstado
										.setBackgroundResource(R.anim.anim_despegue_ok);
								savingAnimationLuz = (AnimationDrawable) imgLuzEstado
										.getBackground();
								savingAnimationLuz.start();
								Handler handlerDespegueOkUno = new Handler();
								handlerDespegueOkUno.postDelayed(
										new Runnable() {
											@Override
											public void run() {
												videoBackDespegue.start();
												ImagenBackDespegue
														.startAnimation(animImgBackDespegue);
												ImagenBackDespegue
														.setVisibility(View.GONE);
											}
										}, 2000);
							}
						});
					}
					break;
				}
				mProgress.setProgress(mProgressStatus);
				runOnUiThread(new Runnable() // run on ui thread
				{
					public void run() {
						textoDos.setText(String.valueOf(mProgressStatus + "%"));
						if (estadoProgress == 3 && mProgressStatus > 70) {
							// ImagenBackDespegue.startAnimation(animImgBackDespegue);
							// videoBackDespegue.start();
							// ImagenBackDespegue.setVisibility(View.GONE);
						}
					}
				});
			}
		}, delay, period);
	}

	public void intentoTres() {
		runOnUiThread(new Runnable() // run on ui thread
		{
			public void run() {
				final Handler handlerDespegueMensajeOut = new Handler();
				handlerDespegueMensajeOut.postDelayed(new Runnable() {
					@Override
					public void run() {
						despegueFail
								.startAnimation(animMensajesDespegueFailOut);
						despegueFail.setVisibility(View.GONE);
						savingAnimationLuz.stop();
					}
				}, 3000);
			}
		});
		final TextView textoDos = (TextView) findViewById(R.id.txt_porcentaje);
		int delay = 3000; // delay for 1 sec.
		int period = 3; // repeat every 10 sec.
		estadoProgress = 1;
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
						runOnUiThread(new Runnable() // run on ui thread
						{
							public void run() {
								creaMpFail();
								mpDespegue.release();
								Intent act = new Intent(LaminaDosActivity.this,
										GameOverActivity.class);
								act.putExtra("game", 3);
								startActivity(act);
								overridePendingTransition(R.anim.fade_in,
										R.anim.fade_out);
							}
						});
					} else if (mProgressStatus >= 70) {
						runOnUiThread(new Runnable() // run on
						// ui
						// thread
						{
							public void run() {
								creaMpOk();
								despegueOk.setVisibility(View.VISIBLE);
								despegueOk.startAnimation(animMensajesDespegue);
								imgLuzEstado
										.setBackgroundResource(R.anim.anim_despegue_ok);
								savingAnimationLuz = (AnimationDrawable) imgLuzEstado
										.getBackground();
								savingAnimationLuz.start();
								Handler handlerDespegueOkUno = new Handler();
								handlerDespegueOkUno.postDelayed(
										new Runnable() {
											@Override
											public void run() {
												videoBackDespegue.start();
												ImagenBackDespegue
														.startAnimation(animImgBackDespegue);
												ImagenBackDespegue
														.setVisibility(View.GONE);
											}
										}, 2000);
							}
						});
					}
					break;
				}
				mProgress.setProgress(mProgressStatus);
				runOnUiThread(new Runnable() // run on ui thread
				{
					public void run() {
						textoDos.setText(String.valueOf(mProgressStatus + "%"));
						if (estadoProgress == 3 && mProgressStatus > 70) {
							// ImagenBackDespegue.startAnimation(animImgBackDespegue);
							imgLuzEstado
									.setBackgroundResource(R.anim.anim_despegue_ok);
							savingAnimationLuz = (AnimationDrawable) imgLuzEstado
									.getBackground();
							savingAnimationLuz.start();
							// ImagenBackDespegue.setVisibility(View.GONE);
						}
					}
				});
			}
		}, delay, period);
	}

	public void goRutas() {

		final RelativeLayout layJuegoDespegue = (RelativeLayout) findViewById(R.id.lay_juego_master);
		final RelativeLayout layJuegoCarga = (RelativeLayout) findViewById(R.id.lay_juego_carga);
		final Animation animJuego = AnimationUtils.loadAnimation(this,
				R.anim.anim_in_juego);

		final Animation animRotate = AnimationUtils.loadAnimation(this,
				R.anim.anim_rotacion);
		// /////// BOTON UNO //////////
		findViewById(R.id.btnUno).setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				volumeCabinaJuego();
				mpDespegue.start();
				int numeroRandom = generaRandom();
				switch (numeroRandom) {
				case 1:
					layJuegoDespegue.setVisibility(View.VISIBLE);
					layJuegoDespegue.startAnimation(animJuego);
					break;
				case 2:
					layJuegoCarga.setVisibility(View.VISIBLE);
					layJuegoCarga.startAnimation(animJuego);
					break;
				}
				rutaSeleccionada = 1;
				layMasterComp.setVisibility(View.GONE);
				mpMapaJuego.stop();
				// volumeJuego();
			}
		});
		// ////////////////////////////
		// /////// BOTON DOS //////////
		findViewById(R.id.btnDos).setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				volumeCabinaJuego();
				mpDespegue.start();
				int numeroRandom = generaRandom();
				switch (numeroRandom) {
				case 1:
					layJuegoDespegue.setVisibility(View.VISIBLE);
					layJuegoDespegue.startAnimation(animJuego);
					break;
				case 2:
					layJuegoCarga.setVisibility(View.VISIBLE);
					layJuegoCarga.startAnimation(animJuego);
					break;
				}
				rutaSeleccionada = 2;
				layMasterComp.setVisibility(View.GONE);
				mpMapaJuego.stop();
				// volumeJuego();
			}
		});
		// ////////////////////////////
		// /////// BOTON TRES //////////
		findViewById(R.id.btnTres).setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				volumeCabinaJuego();
				mpDespegue.start();
				int numeroRandom = generaRandom();
				switch (numeroRandom) {
				case 1:
					layJuegoDespegue.setVisibility(View.VISIBLE);
					layJuegoDespegue.startAnimation(animJuego);
					break;
				case 2:
					layJuegoCarga.setVisibility(View.VISIBLE);
					layJuegoCarga.startAnimation(animJuego);
					break;
				}
				rutaSeleccionada = 3;
				layMasterComp.setVisibility(View.GONE);
				mpMapaJuego.stop();
				// volumeJuego();
			}
		});
		// ////////////////////////////
		// /////// BOTON CUATRO //////////
		findViewById(R.id.btnCuatro).setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				volumeCabinaJuego();
				mpDespegue.start();
				int numeroRandom = generaRandom();
				switch (numeroRandom) {
				case 1:
					layJuegoDespegue.setVisibility(View.VISIBLE);
					layJuegoDespegue.startAnimation(animJuego);
					break;
				case 2:
					layJuegoCarga.setVisibility(View.VISIBLE);
					layJuegoCarga.startAnimation(animJuego);
					break;
				}
				rutaSeleccionada = 4;
				layMasterComp.setVisibility(View.GONE);
				mpMapaJuego.stop();
				// volumeJuego();
			}
		});
		// ////////////////////////////
		// /////// BOTON CINCO //////////
		findViewById(R.id.btnCinco).setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				volumeCabinaJuego();
				mpDespegue.start();
				int numeroRandom = generaRandom();
				switch (numeroRandom) {
				case 1:
					layJuegoDespegue.setVisibility(View.VISIBLE);
					layJuegoDespegue.startAnimation(animJuego);
					break;
				case 2:
					layJuegoCarga.setVisibility(View.VISIBLE);
					layJuegoCarga.startAnimation(animJuego);
					break;
				}
				rutaSeleccionada = 5;
				layMasterComp.setVisibility(View.GONE);
				mpMapaJuego.stop();
				// volumeJuego();
			}
		});
		// ////////////////////////////
		// /////// BOTON SEIS //////////
		findViewById(R.id.btnSeis).setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				volumeCabinaJuego();
				mpDespegue.start();
				int numeroRandom = generaRandom();
				switch (numeroRandom) {
				case 1:
					layJuegoDespegue.setVisibility(View.VISIBLE);
					layJuegoDespegue.startAnimation(animJuego);
					break;
				case 2:
					layJuegoCarga.setVisibility(View.VISIBLE);
					layJuegoCarga.startAnimation(animJuego);
					break;
				}
				rutaSeleccionada = 6;
				layMasterComp.setVisibility(View.GONE);
				mpMapaJuego.stop();
				// volumeJuego();
			}
		});
		// ////////////////////////////
		// /////// BOTON SIETE //////////
		findViewById(R.id.btnSiete).setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				volumeCabinaJuego();
				mpDespegue.start();
				int numeroRandom = generaRandom();
				switch (numeroRandom) {
				case 1:
					layJuegoDespegue.setVisibility(View.VISIBLE);
					layJuegoDespegue.startAnimation(animJuego);
					break;
				case 2:
					layJuegoCarga.setVisibility(View.VISIBLE);
					layJuegoCarga.startAnimation(animJuego);
					break;
				}
				rutaSeleccionada = 7;
				layMasterComp.setVisibility(View.GONE);
				mpMapaJuego.stop();
				// volumeJuego();
			}
		});
		// ////////////////////////////
		// /////// BOTON OCHO //////////
		findViewById(R.id.btnOcho).setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				volumeCabinaJuego();
				mpDespegue.start();
				int numeroRandom = generaRandom();
				switch (numeroRandom) {
				case 1:
					layJuegoDespegue.setVisibility(View.VISIBLE);
					layJuegoDespegue.startAnimation(animJuego);
					break;
				case 2:
					layJuegoCarga.setVisibility(View.VISIBLE);
					layJuegoCarga.startAnimation(animJuego);
					break;
				}
				rutaSeleccionada = 8;
				layMasterComp.setVisibility(View.GONE);
				mpMapaJuego.stop();
				// volumeJuego();
			}
		});
		// ////////////////////////////
		// /////// BOTON NUEVE //////////
		findViewById(R.id.btnNueve).setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				volumeCabinaJuego();
				mpDespegue.start();
				int numeroRandom = generaRandom();
				switch (numeroRandom) {
				case 1:
					layJuegoDespegue.setVisibility(View.VISIBLE);
					layJuegoDespegue.startAnimation(animJuego);
					break;
				case 2:
					layJuegoCarga.setVisibility(View.VISIBLE);
					layJuegoCarga.startAnimation(animJuego);
					break;
				}
				rutaSeleccionada = 9;
				layMasterComp.setVisibility(View.GONE);
				mpMapaJuego.stop();
				// volumeJuego();
			}
		});
		// ////////////////////////////

		findViewById(R.id.btn_activa_carga).setOnClickListener(
				new OnClickListener() {
					public void onClick(View arg0) {
						if (comienzaCarga == 1) {
							if (currentRotation < 100) {
								// int currentRotation = 0;
								RotateAnimation anim = new RotateAnimation(
										currentRotation, currentRotation + 1,
										Animation.RELATIVE_TO_SELF, 0.5f,
										Animation.RELATIVE_TO_SELF, 0.5f);
								currentRotation = (currentRotation) % 360;

								anim.setInterpolator(new LinearInterpolator());
								anim.setDuration(0);
								anim.setFillEnabled(true);

								anim.setFillAfter(true);
								findViewById(R.id.img_aguja_tacom_transp)
										.startAnimation(anim);
								currentRotation += 1;
								txtPorcentajeCarga.setText(currentRotation
										+ "%");
							} else {
								activaCarga.setEnabled(false);
							}
						} else {

						}
					}
				});
	}

	public int generaRandom() {
		int min = 1;
		int max = 2;

		Random r = new Random();
		int i1 = r.nextInt(max - min + 1) + min;
		return i1;
	}

	public void volumeJuego() {
		AudioManager audio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
		int currentVolume = audio.getStreamVolume(AudioManager.STREAM_MUSIC);
		int maxVolume = audio.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		float percent = 0.5f;
		int seventyVolume = (int) (maxVolume * percent);
		audio.setStreamVolume(AudioManager.STREAM_MUSIC, seventyVolume, 0);
	}

	public void volumeCabinaJuego() {
		AudioManager audio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
		int currentVolume = audio.getStreamVolume(AudioManager.STREAM_MUSIC);
		int maxVolume = audio.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		float percent = 0.8f;
		int seventyVolume = (int) (maxVolume * percent);
		audio.setStreamVolume(AudioManager.STREAM_MUSIC, seventyVolume, 0);
	}

	public void desactivaRutas() {
		if (LaminaUnoActivity.btn1 == 1) {
			findViewById(R.id.btnUno).setEnabled(false);
			findViewById(R.id.btnUno).setBackgroundResource(
					R.drawable.btn_rutas_jugada);
		}
		if (LaminaUnoActivity.btn2 == 1) {
			findViewById(R.id.btnDos).setEnabled(false);
			findViewById(R.id.btnDos).setBackgroundResource(
					R.drawable.btn_rutas_jugada);
		}
		if (LaminaUnoActivity.btn3 == 1) {
			findViewById(R.id.btnTres).setEnabled(false);
			findViewById(R.id.btnTres).setBackgroundResource(
					R.drawable.btn_rutas_jugada);
		}
		if (LaminaUnoActivity.btn4 == 1) {
			findViewById(R.id.btnCuatro).setEnabled(false);
			findViewById(R.id.btnCuatro).setBackgroundResource(
					R.drawable.btn_rutas_jugada);
		}
		if (LaminaUnoActivity.btn5 == 1) {
			findViewById(R.id.btnCinco).setEnabled(false);
			findViewById(R.id.btnCinco).setBackgroundResource(
					R.drawable.btn_rutas_jugada);
		}
		if (LaminaUnoActivity.btn6 == 1) {
			findViewById(R.id.btnSeis).setEnabled(false);
			findViewById(R.id.btnSeis).setBackgroundResource(
					R.drawable.btn_rutas_jugada);
		}
		if (LaminaUnoActivity.btn7 == 1) {
			findViewById(R.id.btnSiete).setEnabled(false);
			findViewById(R.id.btnSiete).setBackgroundResource(
					R.drawable.btn_rutas_jugada);
		}
		if (LaminaUnoActivity.btn8 == 1) {
			findViewById(R.id.btnOcho).setEnabled(false);
			findViewById(R.id.btnOcho).setBackgroundResource(
					R.drawable.btn_rutas_jugada);
		}
		if (LaminaUnoActivity.btn9 == 1) {
			findViewById(R.id.btnNueve).setEnabled(false);
			findViewById(R.id.btnNueve).setBackgroundResource(
					R.drawable.btn_rutas_jugada);
		}
		if (LaminaUnoActivity.btn10 == 1) {
			findViewById(R.id.btnDiez).setEnabled(false);
			findViewById(R.id.btnDiez).setBackgroundResource(
					R.drawable.btn_rutas_jugada);
		}
		if (LaminaUnoActivity.btn11 == 1) {
			findViewById(R.id.btnOnce).setEnabled(false);
			findViewById(R.id.btnOnce).setBackgroundResource(
					R.drawable.btn_rutas_jugada);
		}
		if (LaminaUnoActivity.btn12 == 1) {
			findViewById(R.id.btnDoce).setEnabled(false);
			findViewById(R.id.btnDoce).setBackgroundResource(
					R.drawable.btn_rutas_jugada);
		}
		if (LaminaUnoActivity.btn13 == 1) {
			findViewById(R.id.btnTrece).setEnabled(false);
			findViewById(R.id.btnTrece).setBackgroundResource(
					R.drawable.btn_rutas_jugada);
		}
		if (LaminaUnoActivity.btn14 == 1) {
			findViewById(R.id.btnCatorce).setEnabled(false);
			findViewById(R.id.btnCatorce).setBackgroundResource(
					R.drawable.btn_rutas_jugada);
		}
		if (LaminaUnoActivity.btn15 == 1) {
			findViewById(R.id.btnQuince).setEnabled(false);
			findViewById(R.id.btnQuince).setBackgroundResource(
					R.drawable.btn_rutas_jugada);
		}
		if (LaminaUnoActivity.btn16 == 1) {
			findViewById(R.id.btn16).setEnabled(false);
			findViewById(R.id.btn16).setBackgroundResource(
					R.drawable.btn_rutas_jugada);
		}
		if (LaminaUnoActivity.btn17 == 1) {
			findViewById(R.id.btn17).setEnabled(false);
			findViewById(R.id.btn17).setBackgroundResource(
					R.drawable.btn_rutas_jugada);
		}
		if (LaminaUnoActivity.btn18 == 1) {
			findViewById(R.id.btn18).setEnabled(false);
			findViewById(R.id.btn18).setBackgroundResource(
					R.drawable.btn_rutas_jugada);
		}
		if (LaminaUnoActivity.btn19 == 1) {
			findViewById(R.id.btn19).setEnabled(false);
			findViewById(R.id.btn19).setBackgroundResource(
					R.drawable.btn_rutas_jugada);
		}
		if (LaminaUnoActivity.btn20 == 1) {
			findViewById(R.id.btn20).setEnabled(false);
			findViewById(R.id.btn20).setBackgroundResource(
					R.drawable.btn_rutas_jugada);
		}
		if (LaminaUnoActivity.btn21 == 1) {
			findViewById(R.id.btn21).setEnabled(false);
			findViewById(R.id.btn21).setBackgroundResource(
					R.drawable.btn_rutas_jugada);
		}
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

	public void creaMpFail() {
		final MediaPlayer mpFail = MediaPlayer.create(this,
				R.raw.sonido_incorrecto);
		mpFail.start();
		mpFail.setOnCompletionListener(new OnCompletionListener() {
			public void onCompletion(MediaPlayer mp) {
				mp.release();

			};
		});
	}

	public void creaCuentaAtras() {
		final MediaPlayer mp = MediaPlayer.create(this, R.raw.cuenta_atras);
		mp.start();
		mp.setOnCompletionListener(new OnCompletionListener() {
			public void onCompletion(MediaPlayer mpCu) {
				mpCu.release();
			};
		});
	}

	public void muestraInterfaceJuego() {

		videoInterface = (VideoView) findViewById(R.id.video_interface_uno);
		nextInterface = (RelativeLayout) findViewById(R.id.next_interface);
		animNetxInterfaceIn = AnimationUtils.loadAnimation(this,R.anim.anim_translate_botone_next_in);
		animNetxInterfaceOut = AnimationUtils.loadAnimation(this,R.anim.anim_translate_boton_next);
		Bundle bundle = getIntent().getExtras();
		if (bundle.getInt("isInterface") == 0) {
			videoInterface
					.setVideoPath("android.resource://com.caromatias.apppisodigitallan/"
							+ R.raw.interface_final);
			videoInterface.start();
			
			Handler handlerInterfacePasoUno = new Handler();
			handlerInterfacePasoUno.postDelayed(new Runnable() {
				@Override
				public void run() {
					videoInterface.pause();
					pause = true;
				}
			}, 10000);
			Handler handlerInterfaceBotonUno = new Handler();
			handlerInterfaceBotonUno.postDelayed(new Runnable() {
				@Override
				public void run() {
					nextInterface.setVisibility(View.VISIBLE);
					nextInterface.startAnimation(animNetxInterfaceIn);
				}
			}, 9500);
			/*
			Handler handlerInterfaceUno = new Handler();
			handlerInterfaceUno.postDelayed(new Runnable() {
				@Override
				public void run() {
					imgWhiteDos.setVisibility(View.VISIBLE);
					imgWhiteDos.startAnimation(animVideoMain);
				}
			}, 29500);
			Handler handlerInterfaceVid = new Handler();
			handlerInterfaceVid.postDelayed(new Runnable() {
				@Override
				public void run() {
					videoInterface.setVisibility(View.GONE);
				}
			}, 30400);
			Handler handlerInterfaceDos = new Handler();
			handlerInterfaceDos.postDelayed(new Runnable() {
				@Override
				public void run() {
					imgWhiteDos.startAnimation(animVideoMainOut);
					imgWhiteDos.setVisibility(View.GONE);
				}
			}, 30900);
			*/
		}else{
			videoInterface.setVisibility(View.GONE);
		}
		/////////////// evento boton pause //////////////
		findViewById(R.id.btn_next_interface).setOnClickListener(
				new OnClickListener() {
					public void onClick(View arg0) {
						if(paso == 1){
							paso = 2;
							nextInterface.startAnimation(animNetxInterfaceOut);
							nextInterface.setVisibility(View.GONE);
							Handler handlerInterfaceBotonDos = new Handler();
							handlerInterfaceBotonDos.postDelayed(new Runnable() {
								@Override
								public void run() {
									nextInterface.setVisibility(View.VISIBLE);
									nextInterface.startAnimation(animNetxInterfaceIn);
								}
							}, 6500);
							Handler handlerInterfacePasoDos = new Handler();
							handlerInterfacePasoDos.postDelayed(new Runnable() {
								@Override
								public void run() {
									videoInterface.pause();
									pause = true;
								}
							}, 7000);
						} else if(paso == 2){
							nextInterface.startAnimation(animNetxInterfaceOut);
							nextInterface.setVisibility(View.GONE);
							Handler handlerInterfaceBotonTres = new Handler();
							handlerInterfaceBotonTres.postDelayed(new Runnable() {
								@Override
								public void run() {
									nextInterface.setVisibility(View.VISIBLE);
									nextInterface.startAnimation(animNetxInterfaceIn);
								}
							}, 6000);
							Handler handlerInterfacePasoTres = new Handler();
							handlerInterfacePasoTres.postDelayed(new Runnable() {
								@Override
								public void run() {
									paso = 3;
									videoInterface.pause();
									pause = true;
								}
							}, 6500);
						}else if(paso == 3){
							nextInterface.startAnimation(animNetxInterfaceOut);
							nextInterface.setVisibility(View.GONE);
							Handler handlerInterfaceBotonTres = new Handler();
							handlerInterfaceBotonTres.postDelayed(new Runnable() {
								@Override
								public void run() {
									nextInterface.setVisibility(View.VISIBLE);
									nextInterface.startAnimation(animNetxInterfaceIn);
								}
							}, 7000);
							////////////////////////////////////////
							Handler handlerInterfaceUno = new Handler();
							handlerInterfaceUno.postDelayed(new Runnable() {
								@Override
								public void run() {
									imgWhiteDos.setVisibility(View.VISIBLE);
									imgWhiteDos.startAnimation(animVideoMain);
								}
							}, 7500);
							Handler handlerInterfaceVid = new Handler();
							handlerInterfaceVid.postDelayed(new Runnable() {
								@Override
								public void run() {
									videoInterface.setVisibility(View.GONE);
								}
							}, 8400);
							Handler handlerInterfaceDos = new Handler();
							handlerInterfaceDos.postDelayed(new Runnable() {
								@Override
								public void run() {
									imgWhiteDos.startAnimation(animVideoMainOut);
									imgWhiteDos.setVisibility(View.GONE);
								}
							}, 8900);
						}
						if(pause == true){
							videoInterface.start();
							pause = false;
						}
					}
				});
	}

	@Override
	public void onBackPressed() {
		return;
	}
}
