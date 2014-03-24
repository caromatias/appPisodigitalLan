package com.caromatias.apppisodigitallan;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

public class LaminaDosActivity extends Activity {

	private AnimationDrawable savingAnimation;
	private AnimationDrawable savingAnimationLuz;
	// ///////////////////////////////////////
	private ProgressBar mProgress;
	private int mProgressStatus = 0;
	private int terminaProceso = 0;
	private int estadoProgress = 1;
	private Button botonStop;
	private ImageView ivAnimacion;
	private ImageView intentoUno;
	private ImageView intentoDos;
	private ImageView intentoTres;
	private ImageView ImagenBackDespegue;
	private ImageView despegueOk;
	private ImageView despegueFail;
	private ImageView cargaOk;
	private ImageView cargaFail;
	private ImageView flecha;
	private ImageView flechaCarga;
	private ImageView imgBackCarga;
	private ImageView imgWhiteDos;
	private int intentos = 1;
	private VideoView videoBackDespegue;
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
	private VideoView videoBackCarga;
	private Animation animMensajesDespegue;
	private Animation animMensajesDespegueOut;
	private Animation animMensajesDespegueFailIn;
	private Animation animMensajesDespegueFailOut;
	private RelativeLayout imgLuzEstado;
	private Animation animFlechaRebote;
	// private MediaPlayer mp;
	// private MediaPlayer mpFail;
	// private MediaPlayer mpOk;
	public static MediaPlayer mpDespegue;
	private MediaPlayer mpMapaJuego;
	private VideoView videoInterface;
	private Animation animVideoMain;
	private Animation animVideoMainOut;
	private boolean pause = false;
	private int paso = 1;
	private RelativeLayout nextInterface;
	private Animation animNetxInterfaceIn;
	private Animation animNetxInterfaceOut;
	private Animation animCuentasAtras;

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
				System.gc();
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
						findViewById(R.id.btn_comenzar_juego_despegue)
								.setEnabled(false);
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
										runOnUiThread(new Runnable()
										{
											public void run() {
												creaMpFail();
												flecha.setVisibility(View.GONE);
												despegueFail.setVisibility(View.VISIBLE);
												setAnimacionListenerFailIn();
												despegueFail.startAnimation(animMensajesDespegueFailIn);
												imgLuzEstado.setBackgroundResource(R.anim.anim_despegue_fail);
												savingAnimationLuz = (AnimationDrawable) imgLuzEstado.getBackground();
												savingAnimationLuz.start();
											}
										});
										intentoDos();
									} else if (mProgressStatus >= 70) {
										runOnUiThread(new Runnable()
										{
											public void run() {
												findViewById(R.id.button1).setEnabled(false);
												creaMpOk();
												despegueOk.setVisibility(View.VISIBLE);
												setAnimacionListener();
												despegueOk.startAnimation(animMensajesDespegue);
												imgLuzEstado.setBackgroundResource(R.anim.anim_despegue_ok);
												savingAnimationLuz = (AnimationDrawable) imgLuzEstado.getBackground();
												savingAnimationLuz.start();
												/*
												 * Handler handlerDespegueOkUno
												 * = new Handler();
												 * handlerDespegueOkUno
												 * .postDelayed( new Runnable()
												 * {
												 * 
												 * @Override public void run() {
												 * videoBackDespegue .start();
												 * ImagenBackDespegue
												 * .startAnimation
												 * (animImgBackDespegue);
												 * ImagenBackDespegue
												 * .setVisibility(View.GONE);
												 * despegueOk .startAnimation(
												 * animMensajesDespegueOut);
												 * despegueOk
												 * .setVisibility(View.GONE); }
												 * }, 2000);
												 */
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
										/*
										if (estadoProgress == 3
												&& mProgressStatus > 70) {
											// ImagenBackDespegue.startAnimation(animImgBackDespegue);
											// videoBackDespegue.start();
											// ImagenBackDespegue.setVisibility(View.GONE);
										}
										*/
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
						findViewById(R.id.btn_comenzar_juego_carga).setEnabled(
								false);
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

		videoBackDespegue.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
					public void onCompletion(MediaPlayer mp) {
						//mp.release();
						resetImages();
						System.gc();
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
		
		setAnimacionListenerCuentaAtras();
		findViewById(R.id.txt_cuanta_atras_uno).startAnimation(animCuentasAtras);
		
		/*
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
		*/
		/*
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
		*/
	}

	public void comienzaCuentaAtras() {
		videoBackCarga.setVideoPath("android.resource://com.caromatias.apppisodigitallan/"+ R.raw.video_carga);
		videoBackCarga.start();
		/*
		final Handler handlerCargaImg = new Handler();
		handlerCargaImg.postDelayed(new Runnable() {
			@Override
			public void run() {
				imgBackCarga.setVisibility(View.GONE);
			}
		}, 500);
		*/
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
					setAnimacionListenerFailCargaIn();
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
						/*
						 * runOnUiThread(new Runnable() // run on ui thread {
						 * public void run() { final Handler
						 * handlerCargaQuitaUno = new Handler();
						 * handlerCargaQuitaUno.postDelayed( new Runnable() {
						 * 
						 * @Override public void run() { cargaFail
						 * .startAnimation(animMensajesDespegueFailOut);
						 * cargaFail .setVisibility(View.GONE); } }, 1500);
						 * final Handler handlerCargaQuita = new Handler();
						 * handlerCargaQuita.postDelayed(new Runnable() {
						 * 
						 * @Override public void run() { muestraCuentaAtas(); }
						 * }, 2100); } });
						 */
					} else if (intentosCarga == 3) {
						/*
						runOnUiThread(new Runnable() // run on ui thread
						{
							public void run() {
								final Handler handlerCargaFinalTres = new Handler();
								handlerCargaFinalTres.postDelayed(
										new Runnable() {
											@Override
											public void run() {
												// creaMpFail();
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
						*/
						System.gc();
						mpDespegue.release();
						Intent act = new Intent(LaminaDosActivity.this,GameOverActivity.class);
						act.putExtra("game", 3);
						startActivity(act);
						overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
					}
					/*
					 * runOnUiThread(new Runnable() // run on ui thread { public
					 * void run() { final Handler handlerCargaQuitaUno = new
					 * Handler(); handlerCargaQuitaUno.postDelayed(new
					 * Runnable() {
					 * 
					 * @Override public void run() { cargaFail
					 * .startAnimation(animMensajesDespegueFailOut);
					 * cargaFail.setVisibility(View.GONE); } }, 1500); final
					 * Handler handlerCargaQuita = new Handler();
					 * handlerCargaQuita.postDelayed(new Runnable() {
					 * 
					 * @Override public void run() { muestraCuentaAtas(); } },
					 * 2100); } });
					 */
				} else if (currentRotation >= 60) {
					creaMpOk();
					System.gc();
					cargaOk.setVisibility(View.VISIBLE);
					setAnimacionListenerOkCargaIn();
					cargaOk.startAnimation(animMensajesDespegueFailIn);
					/*
					runOnUiThread(new Runnable() // run on ui thread
					{
						public void run() {
							
							 final Handler handlerCargaQuitaUno = new
							 Handler(); handlerCargaQuitaUno.postDelayed(new
							 Runnable() {
							 
							 @Override public void run() {
							 cargaOk.startAnimation
							 (animMensajesDespegueFailOut);
							 cargaOk.setVisibility(View.GONE); } }, 2400);
							 
							
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
					*/
				}
			}
		}.start();
	}

	public void intentoDos() {
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
								setAnimacionListenerFailIn();
								despegueFail.startAnimation(animMensajesDespegueFailIn);
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
								findViewById(R.id.button1).setEnabled(false);
								despegueOk.setVisibility(View.VISIBLE);
								setAnimacionListener();
								despegueOk.startAnimation(animMensajesDespegue);
								imgLuzEstado
										.setBackgroundResource(R.anim.anim_despegue_ok);
								savingAnimationLuz = (AnimationDrawable) imgLuzEstado
										.getBackground();
								savingAnimationLuz.start();
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
								Intent act = new Intent(LaminaDosActivity.this,GameOverActivity.class);
								act.putExtra("game", 3);
								startActivity(act);
								overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
							}
						});
					} else if (mProgressStatus >= 70) {
						runOnUiThread(new Runnable() // run on
						// ui
						// thread
						{
							public void run() {
								creaMpOk();
								findViewById(R.id.button1).setEnabled(false);
								despegueOk.setVisibility(View.VISIBLE);
								setAnimacionListener();
								despegueOk.startAnimation(animMensajesDespegue);
								imgLuzEstado.setBackgroundResource(R.anim.anim_despegue_ok);
								savingAnimationLuz = (AnimationDrawable) imgLuzEstado.getBackground();
								savingAnimationLuz.start();
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
				switch (LaminaBienvenidaActivity.juego) {
				case 1:
					layJuegoDespegue.setVisibility(View.VISIBLE);
					layJuegoDespegue.startAnimation(animJuego);
					LaminaBienvenidaActivity.juego = 2;
					break;
				case 2:
					layJuegoCarga.setVisibility(View.VISIBLE);
					layJuegoCarga.startAnimation(animJuego);
					LaminaBienvenidaActivity.juego = 1;
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
				switch (LaminaBienvenidaActivity.juego) {
				case 1:
					layJuegoDespegue.setVisibility(View.VISIBLE);
					layJuegoDespegue.startAnimation(animJuego);
					LaminaBienvenidaActivity.juego = 2;
					break;
				case 2:
					layJuegoCarga.setVisibility(View.VISIBLE);
					layJuegoCarga.startAnimation(animJuego);
					LaminaBienvenidaActivity.juego = 1;
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
				switch (LaminaBienvenidaActivity.juego) {
				case 1:
					layJuegoDespegue.setVisibility(View.VISIBLE);
					layJuegoDespegue.startAnimation(animJuego);
					LaminaBienvenidaActivity.juego = 2;
					break;
				case 2:
					layJuegoCarga.setVisibility(View.VISIBLE);
					layJuegoCarga.startAnimation(animJuego);
					LaminaBienvenidaActivity.juego = 1;
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
				switch (LaminaBienvenidaActivity.juego) {
				case 1:
					layJuegoDespegue.setVisibility(View.VISIBLE);
					layJuegoDespegue.startAnimation(animJuego);
					LaminaBienvenidaActivity.juego = 2;
					break;
				case 2:
					layJuegoCarga.setVisibility(View.VISIBLE);
					layJuegoCarga.startAnimation(animJuego);
					LaminaBienvenidaActivity.juego = 1;
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
				switch (LaminaBienvenidaActivity.juego) {
				case 1:
					layJuegoDespegue.setVisibility(View.VISIBLE);
					layJuegoDespegue.startAnimation(animJuego);
					LaminaBienvenidaActivity.juego = 2;
					break;
				case 2:
					layJuegoCarga.setVisibility(View.VISIBLE);
					layJuegoCarga.startAnimation(animJuego);
					LaminaBienvenidaActivity.juego = 1;
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
				switch (LaminaBienvenidaActivity.juego) {
				case 1:
					layJuegoDespegue.setVisibility(View.VISIBLE);
					layJuegoDespegue.startAnimation(animJuego);
					LaminaBienvenidaActivity.juego = 2;
					break;
				case 2:
					layJuegoCarga.setVisibility(View.VISIBLE);
					layJuegoCarga.startAnimation(animJuego);
					LaminaBienvenidaActivity.juego = 1;
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
				switch (LaminaBienvenidaActivity.juego) {
				case 1:
					layJuegoDespegue.setVisibility(View.VISIBLE);
					layJuegoDespegue.startAnimation(animJuego);
					LaminaBienvenidaActivity.juego = 2;
					break;
				case 2:
					layJuegoCarga.setVisibility(View.VISIBLE);
					layJuegoCarga.startAnimation(animJuego);
					LaminaBienvenidaActivity.juego = 1;
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
				switch (LaminaBienvenidaActivity.juego) {
				case 1:
					layJuegoDespegue.setVisibility(View.VISIBLE);
					layJuegoDespegue.startAnimation(animJuego);
					LaminaBienvenidaActivity.juego = 2;
					break;
				case 2:
					layJuegoCarga.setVisibility(View.VISIBLE);
					layJuegoCarga.startAnimation(animJuego);
					LaminaBienvenidaActivity.juego = 1;
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
				switch (LaminaBienvenidaActivity.juego) {
				case 1:
					layJuegoDespegue.setVisibility(View.VISIBLE);
					layJuegoDespegue.startAnimation(animJuego);
					LaminaBienvenidaActivity.juego = 2;
					break;
				case 2:
					layJuegoCarga.setVisibility(View.VISIBLE);
					layJuegoCarga.startAnimation(animJuego);
					LaminaBienvenidaActivity.juego = 1;
					break;
				}
				rutaSeleccionada = 9;
				layMasterComp.setVisibility(View.GONE);
				mpMapaJuego.stop();
				// volumeJuego();
			}
		});
		// ////////////////////////////
		// /////// BOTON DIEZ //////////
		findViewById(R.id.btnDiez).setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				volumeCabinaJuego();
				mpDespegue.start();
				switch (LaminaBienvenidaActivity.juego) {
				case 1:
					layJuegoDespegue.setVisibility(View.VISIBLE);
					layJuegoDespegue.startAnimation(animJuego);
					LaminaBienvenidaActivity.juego = 2;
					break;
				case 2:
					layJuegoCarga.setVisibility(View.VISIBLE);
					layJuegoCarga.startAnimation(animJuego);
					LaminaBienvenidaActivity.juego = 1;
					break;
				}
				rutaSeleccionada = 10;
				layMasterComp.setVisibility(View.GONE);
				mpMapaJuego.stop();
				// volumeJuego();
			}
		});
		// ////////////////////////////
		// /////// BOTON ONCE //////////
		findViewById(R.id.btnOnce).setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				volumeCabinaJuego();
				mpDespegue.start();
				switch (LaminaBienvenidaActivity.juego) {
				case 1:
					layJuegoDespegue.setVisibility(View.VISIBLE);
					layJuegoDespegue.startAnimation(animJuego);
					LaminaBienvenidaActivity.juego = 2;
					break;
				case 2:
					layJuegoCarga.setVisibility(View.VISIBLE);
					layJuegoCarga.startAnimation(animJuego);
					LaminaBienvenidaActivity.juego = 1;
					break;
				}
				rutaSeleccionada = 11;
				layMasterComp.setVisibility(View.GONE);
				mpMapaJuego.stop();
				// volumeJuego();
			}
		});
		// ////////////////////////////
		// /////// BOTON DOCE //////////
		findViewById(R.id.btnDoce).setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				volumeCabinaJuego();
				mpDespegue.start();
				switch (LaminaBienvenidaActivity.juego) {
				case 1:
					layJuegoDespegue.setVisibility(View.VISIBLE);
					layJuegoDespegue.startAnimation(animJuego);
					LaminaBienvenidaActivity.juego = 2;
					break;
				case 2:
					layJuegoCarga.setVisibility(View.VISIBLE);
					layJuegoCarga.startAnimation(animJuego);
					LaminaBienvenidaActivity.juego = 1;
					break;
				}
				rutaSeleccionada = 12;
				layMasterComp.setVisibility(View.GONE);
				mpMapaJuego.stop();
				// volumeJuego();
			}
		});
		// ////////////////////////////
		// /////// BOTON TRECE //////////
		findViewById(R.id.btnTrece).setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				volumeCabinaJuego();
				mpDespegue.start();
				switch (LaminaBienvenidaActivity.juego) {
				case 1:
					layJuegoDespegue.setVisibility(View.VISIBLE);
					layJuegoDespegue.startAnimation(animJuego);
					LaminaBienvenidaActivity.juego = 2;
					break;
				case 2:
					layJuegoCarga.setVisibility(View.VISIBLE);
					layJuegoCarga.startAnimation(animJuego);
					LaminaBienvenidaActivity.juego = 1;
					break;
				}
				rutaSeleccionada = 13;
				layMasterComp.setVisibility(View.GONE);
				mpMapaJuego.stop();
				// volumeJuego();
			}
		});
		// ////////////////////////////
		// /////// BOTON CATORCE //////////
		findViewById(R.id.btnCatorce).setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				volumeCabinaJuego();
				mpDespegue.start();
				switch (LaminaBienvenidaActivity.juego) {
				case 1:
					layJuegoDespegue.setVisibility(View.VISIBLE);
					layJuegoDespegue.startAnimation(animJuego);
					LaminaBienvenidaActivity.juego = 2;
					break;
				case 2:
					layJuegoCarga.setVisibility(View.VISIBLE);
					layJuegoCarga.startAnimation(animJuego);
					LaminaBienvenidaActivity.juego = 1;
					break;
				}
				rutaSeleccionada = 14;
				layMasterComp.setVisibility(View.GONE);
				mpMapaJuego.stop();
				// volumeJuego();
			}
		});
		// ////////////////////////////
		// /////// BOTON QUINCE //////////
		findViewById(R.id.btnQuince).setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				volumeCabinaJuego();
				mpDespegue.start();
				switch (LaminaBienvenidaActivity.juego) {
				case 1:
					layJuegoDespegue.setVisibility(View.VISIBLE);
					layJuegoDespegue.startAnimation(animJuego);
					LaminaBienvenidaActivity.juego = 2;
					break;
				case 2:
					layJuegoCarga.setVisibility(View.VISIBLE);
					layJuegoCarga.startAnimation(animJuego);
					LaminaBienvenidaActivity.juego = 1;
					break;
				}
				rutaSeleccionada = 15;
				layMasterComp.setVisibility(View.GONE);
				mpMapaJuego.stop();
				// volumeJuego();
			}
		});
		// ////////////////////////////
		// /////// BOTON 16 //////////
		findViewById(R.id.btn16).setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				volumeCabinaJuego();
				mpDespegue.start();
				switch (LaminaBienvenidaActivity.juego) {
				case 1:
					layJuegoDespegue.setVisibility(View.VISIBLE);
					layJuegoDespegue.startAnimation(animJuego);
					LaminaBienvenidaActivity.juego = 2;
					break;
				case 2:
					layJuegoCarga.setVisibility(View.VISIBLE);
					layJuegoCarga.startAnimation(animJuego);
					LaminaBienvenidaActivity.juego = 1;
					break;
				}
				rutaSeleccionada = 16;
				layMasterComp.setVisibility(View.GONE);
				mpMapaJuego.stop();
				// volumeJuego();
			}
		});
		// ////////////////////////////
		// /////// BOTON 17 //////////
		findViewById(R.id.btn17).setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				volumeCabinaJuego();
				mpDespegue.start();
				switch (LaminaBienvenidaActivity.juego) {
				case 1:
					layJuegoDespegue.setVisibility(View.VISIBLE);
					layJuegoDespegue.startAnimation(animJuego);
					LaminaBienvenidaActivity.juego = 2;
					break;
				case 2:
					layJuegoCarga.setVisibility(View.VISIBLE);
					layJuegoCarga.startAnimation(animJuego);
					LaminaBienvenidaActivity.juego = 1;
					break;
				}
				rutaSeleccionada = 17;
				layMasterComp.setVisibility(View.GONE);
				mpMapaJuego.stop();
				// volumeJuego();
			}
		});
		// ////////////////////////////
		// /////// BOTON 18 //////////
		findViewById(R.id.btn18).setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				volumeCabinaJuego();
				mpDespegue.start();
				switch (LaminaBienvenidaActivity.juego) {
				case 1:
					layJuegoDespegue.setVisibility(View.VISIBLE);
					layJuegoDespegue.startAnimation(animJuego);
					LaminaBienvenidaActivity.juego = 2;
					break;
				case 2:
					layJuegoCarga.setVisibility(View.VISIBLE);
					layJuegoCarga.startAnimation(animJuego);
					LaminaBienvenidaActivity.juego = 1;
					break;
				}
				rutaSeleccionada = 18;
				layMasterComp.setVisibility(View.GONE);
				mpMapaJuego.stop();
				// volumeJuego();
			}
		});
		// ////////////////////////////
		// /////// BOTON 19 //////////
		findViewById(R.id.btn19).setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				volumeCabinaJuego();
				mpDespegue.start();
				switch (LaminaBienvenidaActivity.juego) {
				case 1:
					layJuegoDespegue.setVisibility(View.VISIBLE);
					layJuegoDespegue.startAnimation(animJuego);
					LaminaBienvenidaActivity.juego = 2;
					break;
				case 2:
					layJuegoCarga.setVisibility(View.VISIBLE);
					layJuegoCarga.startAnimation(animJuego);
					LaminaBienvenidaActivity.juego = 1;
					break;
				}
				rutaSeleccionada = 19;
				layMasterComp.setVisibility(View.GONE);
				mpMapaJuego.stop();
				// volumeJuego();
			}
		});
		// ////////////////////////////
		// /////// BOTON 20 //////////
		findViewById(R.id.btn20).setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				volumeCabinaJuego();
				mpDespegue.start();
				switch (LaminaBienvenidaActivity.juego) {
				case 1:
					layJuegoDespegue.setVisibility(View.VISIBLE);
					layJuegoDespegue.startAnimation(animJuego);
					LaminaBienvenidaActivity.juego = 2;
					break;
				case 2:
					layJuegoCarga.setVisibility(View.VISIBLE);
					layJuegoCarga.startAnimation(animJuego);
					LaminaBienvenidaActivity.juego = 1;
					break;
				}
				rutaSeleccionada = 20;
				layMasterComp.setVisibility(View.GONE);
				mpMapaJuego.stop();
				// volumeJuego();
			}
		});
		// ////////////////////////////
		// /////// BOTON 20 //////////
		findViewById(R.id.btn20).setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				volumeCabinaJuego();
				mpDespegue.start();
				switch (LaminaBienvenidaActivity.juego) {
				case 1:
					layJuegoDespegue.setVisibility(View.VISIBLE);
					layJuegoDespegue.startAnimation(animJuego);
					LaminaBienvenidaActivity.juego = 2;
					break;
				case 2:
					layJuegoCarga.setVisibility(View.VISIBLE);
					layJuegoCarga.startAnimation(animJuego);
					LaminaBienvenidaActivity.juego = 1;
					break;
				}
				rutaSeleccionada = 20;
				layMasterComp.setVisibility(View.GONE);
				mpMapaJuego.stop();
				// volumeJuego();
			}
		});
		// ////////////////////////////
		// /////// BOTON 21 //////////
		findViewById(R.id.btn21).setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				volumeCabinaJuego();
				mpDespegue.start();
				switch (LaminaBienvenidaActivity.juego) {
				case 1:
					layJuegoDespegue.setVisibility(View.VISIBLE);
					layJuegoDespegue.startAnimation(animJuego);
					LaminaBienvenidaActivity.juego = 2;
					break;
				case 2:
					layJuegoCarga.setVisibility(View.VISIBLE);
					layJuegoCarga.startAnimation(animJuego);
					LaminaBienvenidaActivity.juego = 1;
					break;
				}
				rutaSeleccionada = 21;
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
		if (LaminaBienvenidaActivity.btn1 == 1) {
			findViewById(R.id.btnUno).setEnabled(false);
			findViewById(R.id.btnUno).setBackgroundResource(
					R.drawable.btn_rutas_jugada);
		}
		if (LaminaBienvenidaActivity.btn2 == 1) {
			findViewById(R.id.btnDos).setEnabled(false);
			findViewById(R.id.btnDos).setBackgroundResource(
					R.drawable.btn_rutas_jugada);
		}
		if (LaminaBienvenidaActivity.btn3 == 1) {
			findViewById(R.id.btnTres).setEnabled(false);
			findViewById(R.id.btnTres).setBackgroundResource(
					R.drawable.btn_rutas_jugada);
		}
		if (LaminaBienvenidaActivity.btn4 == 1) {
			findViewById(R.id.btnCuatro).setEnabled(false);
			findViewById(R.id.btnCuatro).setBackgroundResource(
					R.drawable.btn_rutas_jugada);
		}
		if (LaminaBienvenidaActivity.btn5 == 1) {
			findViewById(R.id.btnCinco).setEnabled(false);
			findViewById(R.id.btnCinco).setBackgroundResource(
					R.drawable.btn_rutas_jugada);
		}
		if (LaminaBienvenidaActivity.btn6 == 1) {
			findViewById(R.id.btnSeis).setEnabled(false);
			findViewById(R.id.btnSeis).setBackgroundResource(
					R.drawable.btn_rutas_jugada);
		}
		if (LaminaBienvenidaActivity.btn7 == 1) {
			findViewById(R.id.btnSiete).setEnabled(false);
			findViewById(R.id.btnSiete).setBackgroundResource(
					R.drawable.btn_rutas_jugada);
		}
		if (LaminaBienvenidaActivity.btn8 == 1) {
			findViewById(R.id.btnOcho).setEnabled(false);
			findViewById(R.id.btnOcho).setBackgroundResource(
					R.drawable.btn_rutas_jugada);
		}
		if (LaminaBienvenidaActivity.btn9 == 1) {
			findViewById(R.id.btnNueve).setEnabled(false);
			findViewById(R.id.btnNueve).setBackgroundResource(
					R.drawable.btn_rutas_jugada);
		}
		if (LaminaBienvenidaActivity.btn10 == 1) {
			findViewById(R.id.btnDiez).setEnabled(false);
			findViewById(R.id.btnDiez).setBackgroundResource(
					R.drawable.btn_rutas_jugada);
		}
		if (LaminaBienvenidaActivity.btn11 == 1) {
			findViewById(R.id.btnOnce).setEnabled(false);
			findViewById(R.id.btnOnce).setBackgroundResource(
					R.drawable.btn_rutas_jugada);
		}
		if (LaminaBienvenidaActivity.btn12 == 1) {
			findViewById(R.id.btnDoce).setEnabled(false);
			findViewById(R.id.btnDoce).setBackgroundResource(
					R.drawable.btn_rutas_jugada);
		}
		if (LaminaBienvenidaActivity.btn13 == 1) {
			findViewById(R.id.btnTrece).setEnabled(false);
			findViewById(R.id.btnTrece).setBackgroundResource(
					R.drawable.btn_rutas_jugada);
		}
		if (LaminaBienvenidaActivity.btn14 == 1) {
			findViewById(R.id.btnCatorce).setEnabled(false);
			findViewById(R.id.btnCatorce).setBackgroundResource(
					R.drawable.btn_rutas_jugada);
		}
		if (LaminaBienvenidaActivity.btn15 == 1) {
			findViewById(R.id.btnQuince).setEnabled(false);
			findViewById(R.id.btnQuince).setBackgroundResource(
					R.drawable.btn_rutas_jugada);
		}
		if (LaminaBienvenidaActivity.btn16 == 1) {
			findViewById(R.id.btn16).setEnabled(false);
			findViewById(R.id.btn16).setBackgroundResource(
					R.drawable.btn_rutas_jugada);
		}
		if (LaminaBienvenidaActivity.btn17 == 1) {
			findViewById(R.id.btn17).setEnabled(false);
			findViewById(R.id.btn17).setBackgroundResource(
					R.drawable.btn_rutas_jugada);
		}
		if (LaminaBienvenidaActivity.btn18 == 1) {
			findViewById(R.id.btn18).setEnabled(false);
			findViewById(R.id.btn18).setBackgroundResource(
					R.drawable.btn_rutas_jugada);
		}
		if (LaminaBienvenidaActivity.btn19 == 1) {
			findViewById(R.id.btn19).setEnabled(false);
			findViewById(R.id.btn19).setBackgroundResource(
					R.drawable.btn_rutas_jugada);
		}
		if (LaminaBienvenidaActivity.btn20 == 1) {
			findViewById(R.id.btn20).setEnabled(false);
			findViewById(R.id.btn20).setBackgroundResource(
					R.drawable.btn_rutas_jugada);
		}
		if (LaminaBienvenidaActivity.btn21 == 1) {
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
				mpoK.release();
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
				mpFail.release();
			};
		});
	}

	public void creaCuentaAtras() {
		final MediaPlayer mp = MediaPlayer.create(this, R.raw.cuenta_atras);
		mp.start();
		mp.setOnCompletionListener(new OnCompletionListener() {
			public void onCompletion(MediaPlayer mpCu) {
				mpCu.release();
				mp.release();
			};
		});
	}

	public void muestraInterfaceJuego() {

		layMasterComp.setVisibility(View.GONE);
		videoInterface = (VideoView) findViewById(R.id.video_interface_uno);
		nextInterface = (RelativeLayout) findViewById(R.id.next_interface);
		animNetxInterfaceIn = AnimationUtils.loadAnimation(this,
				R.anim.anim_translate_botone_next_in);
		animNetxInterfaceOut = AnimationUtils.loadAnimation(this,
				R.anim.anim_translate_boton_next);
		Bundle bundle = getIntent().getExtras();
		if (bundle.getInt("isInterface") == 0) {
			videoInterface
					.setVideoPath("android.resource://com.caromatias.apppisodigitallan/"
							+ R.raw.interface_1);
			videoInterface.start();

			videoInterface.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

						@Override
						public void onCompletion(MediaPlayer vmp) {
							nextInterface.setVisibility(View.VISIBLE);
							nextInterface.startAnimation(animNetxInterfaceIn);
						}
					});
		} else {
			videoInterface.setVisibility(View.GONE);
			layMasterComp.setVisibility(View.VISIBLE);
		}
		// ///////////// evento boton pause //////////////
		findViewById(R.id.btn_next_interface).setOnClickListener(
				new OnClickListener() {
					public void onClick(View arg0) {
						System.gc();
						findViewById(R.id.btn_next_interface).setEnabled(false);
						if (paso == 1) {
							paso = 2;
							videoInterface
									.setVideoPath("android.resource://com.caromatias.apppisodigitallan/"
											+ R.raw.interface_2);
							videoInterface.start();
							nextInterface.startAnimation(animNetxInterfaceOut);
							nextInterface.setVisibility(View.GONE);
							videoInterface
									.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

										@Override
										public void onCompletion(MediaPlayer vmp) {
											nextInterface
													.setVisibility(View.VISIBLE);
											nextInterface
													.startAnimation(animNetxInterfaceIn);
											findViewById(
													R.id.btn_next_interface)
													.setEnabled(true);
										}
									});
						} else if (paso == 2) {
							paso = 3;
							videoInterface
									.setVideoPath("android.resource://com.caromatias.apppisodigitallan/"
											+ R.raw.interface_3);
							videoInterface.start();
							nextInterface.startAnimation(animNetxInterfaceOut);
							nextInterface.setVisibility(View.GONE);
							videoInterface
									.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
										@Override
										public void onCompletion(MediaPlayer vmp) {
											nextInterface
													.setVisibility(View.VISIBLE);
											nextInterface
													.startAnimation(animNetxInterfaceIn);
											findViewById(
													R.id.btn_next_interface)
													.setEnabled(true);
										}
									});
						} else if (paso == 3) {
							paso = 4;
							videoInterface
									.setVideoPath("android.resource://com.caromatias.apppisodigitallan/"
											+ R.raw.interface_4);
							videoInterface.start();
							nextInterface.startAnimation(animNetxInterfaceOut);
							nextInterface.setVisibility(View.GONE);
							videoInterface
									.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
										@Override
										public void onCompletion(MediaPlayer vmp) {
											nextInterface
													.setVisibility(View.VISIBLE);
											nextInterface
													.startAnimation(animNetxInterfaceIn);
											findViewById(
													R.id.btn_next_interface)
													.setEnabled(true);
										}
									});

						} else if (paso == 4) {
							paso = 5;
							videoInterface
									.setVideoPath("android.resource://com.caromatias.apppisodigitallan/"
											+ R.raw.interface_5);
							videoInterface.start();
							nextInterface.startAnimation(animNetxInterfaceOut);
							nextInterface.setVisibility(View.GONE);
							videoInterface
									.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
										@Override
										public void onCompletion(MediaPlayer vmp) {
											fadeInWhite();
										}
									});

						}
						if (pause == true) {
							videoInterface.start();
							pause = false;
						}
					}
				});
	}

	public void setAnimacionListener() {
		animMensajesDespegue = AnimationUtils.loadAnimation(this,
				R.anim.anim_scale_translation_world);
		animMensajesDespegue
				.setAnimationListener(new Animation.AnimationListener() {
					@Override
					public void onAnimationEnd(Animation animation) {
						setAnimacionListenerOut();
						despegueOk.startAnimation(animMensajesDespegueOut);
						videoBackDespegue.start();
						ImagenBackDespegue.startAnimation(animImgBackDespegue);
						ImagenBackDespegue.setVisibility(View.GONE);
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

	public void setAnimacionListenerOut() {
		animMensajesDespegueOut = AnimationUtils.loadAnimation(this,
				R.anim.anim_translacion_out);
		animMensajesDespegueOut
				.setAnimationListener(new Animation.AnimationListener() {
					@Override
					public void onAnimationEnd(Animation animation) {
						despegueOk.setVisibility(View.GONE);
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

	public void setAnimacionListenerFailIn() {
		animMensajesDespegueFailIn = AnimationUtils.loadAnimation(this,
				R.anim.anim_translacion_in);
		animMensajesDespegueFailIn
				.setAnimationListener(new Animation.AnimationListener() {
					@Override
					public void onAnimationEnd(Animation animation) {
						setAnimacionListenerFailOut();
						despegueFail
								.startAnimation(animMensajesDespegueFailOut);
						imgLuzEstado
								.setBackgroundResource(R.anim.anim_despegue_fail);
						savingAnimationLuz = (AnimationDrawable) imgLuzEstado
								.getBackground();
						savingAnimationLuz.start();
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

	public void setAnimacionListenerFailOut() {
		animMensajesDespegueFailOut = AnimationUtils.loadAnimation(this,
				R.anim.anim_translacion_fail_out);
		animMensajesDespegueFailOut
				.setAnimationListener(new Animation.AnimationListener() {
					@Override
					public void onAnimationEnd(Animation animation) {
						despegueFail.setVisibility(View.GONE);
						savingAnimationLuz.stop();
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

	public void setAnimacionListenerOkCargaIn() {
		animMensajesDespegueFailIn = AnimationUtils.loadAnimation(this,
				R.anim.anim_translacion_in);
		animMensajesDespegueFailIn
				.setAnimationListener(new Animation.AnimationListener() {
					@Override
					public void onAnimationEnd(Animation animation) {
						setAnimacionListenerOkCargaOut();
						cargaOk.startAnimation(animMensajesDespegueFailOut);
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

	public void setAnimacionListenerOkCargaOut() {
		animMensajesDespegueFailOut = AnimationUtils.loadAnimation(this,
				R.anim.anim_translacion_fail_out);
		animMensajesDespegueFailOut
				.setAnimationListener(new Animation.AnimationListener() {
					@Override
					public void onAnimationEnd(Animation animation) {
						cargaOk.setVisibility(View.GONE);
						resetImages();
						System.gc();
						Intent act = new Intent(LaminaDosActivity.this,LaminaTresActivity.class);
						act.putExtra("ruta", rutaSeleccionada);
						startActivity(act);
						overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
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

	public void setAnimacionListenerFailCargaIn() {
		animMensajesDespegueFailIn = AnimationUtils.loadAnimation(this,R.anim.anim_translacion_in);
		animMensajesDespegueFailIn.setAnimationListener(new Animation.AnimationListener() {
					@Override
					public void onAnimationEnd(Animation animation) {
						setAnimacionListenerFailCargaOut();
						cargaFail.startAnimation(animMensajesDespegueFailOut);
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

	public void setAnimacionListenerFailCargaOut() {
		animMensajesDespegueFailOut = AnimationUtils.loadAnimation(this,
				R.anim.anim_translacion_fail_out);
		animMensajesDespegueFailOut
				.setAnimationListener(new Animation.AnimationListener() {
					@Override
					public void onAnimationEnd(Animation animation) {
						cargaFail.setVisibility(View.GONE);
						muestraCuentaAtas();
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

	public void setAnimacionListenerCuentaAtras() {
		animCuentasAtras = AnimationUtils.loadAnimation(this,R.anim.anim_primer_cuenta);
		animCuentasAtras.setAnimationListener(new Animation.AnimationListener() {
					@Override
					public void onAnimationEnd(Animation animation) {
						findViewById(R.id.txt_cuanta_atras_uno).setVisibility(View.GONE);
						findViewById(R.id.txt_cuanta_atras_dos).setVisibility(View.VISIBLE);
						setAnimacionListenerCuentaAtrasDos();
						findViewById(R.id.txt_cuanta_atras_dos).startAnimation(animCuentasAtras);
					}

					@Override
					public void onAnimationRepeat(Animation arg0) {
						// TODO Auto-generated method stub
					}

					@Override
					public void onAnimationStart(Animation animation) {
						creaCuentaAtras();
					}
				});
	}
	public void setAnimacionListenerCuentaAtrasDos() {
		animCuentasAtras = AnimationUtils.loadAnimation(this,R.anim.anim_cunta_atras);
		animCuentasAtras.setAnimationListener(new Animation.AnimationListener() {
					@Override
					public void onAnimationEnd(Animation animation) {
						findViewById(R.id.txt_cuanta_atras_dos).setVisibility(View.GONE);
						findViewById(R.id.txt_cuanta_atras_tres).setVisibility(View.VISIBLE);
						setAnimacionListenerCuentaAtrasUno();
						findViewById(R.id.txt_cuanta_atras_tres).startAnimation(animCuentasAtras);
					}

					@Override
					public void onAnimationRepeat(Animation arg0) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onAnimationStart(Animation animation) {
						//creaCuentaAtras();
					}
				});
	}
	public void setAnimacionListenerCuentaAtrasUno() {
		animCuentasAtras = AnimationUtils.loadAnimation(this,R.anim.anim_cunta_atras);
		animCuentasAtras.setAnimationListener(new Animation.AnimationListener() {
					@Override
					public void onAnimationEnd(Animation animation) {
						flechaCarga.setVisibility(View.GONE);
						findViewById(R.id.txt_cuanta_atras_tres).setVisibility(View.GONE);
						findViewById(R.id.lay_pop_cuenta_atras).setVisibility(View.GONE);
						intentosCarga += 1;
						comienzaCuentaAtras();
						imgBackCarga.setVisibility(View.GONE);
					}

					@Override
					public void onAnimationRepeat(Animation arg0) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onAnimationStart(Animation animation) {
						//creaCuentaAtras();
					}
				});
	}
	public void fadeInWhite() {
		imgWhiteDos.setVisibility(View.VISIBLE);
		imgWhiteDos.setAlpha(0f);
		imgWhiteDos.animate().setDuration(800).alpha(1)
				.setListener(new AnimatorListenerAdapter() {
					@Override
					public void onAnimationEnd(Animator animation) {
						imgWhiteDos.setVisibility(View.VISIBLE);
						nextInterface.setVisibility(View.GONE);
						videoInterface.setVisibility(View.GONE);
						fadeOutWhite();
					}
				});
	}

	public void fadeOutWhite() {
		imgWhiteDos.animate().setDuration(800).alpha(0)
				.setListener(new AnimatorListenerAdapter() {
					@Override
					public void onAnimationEnd(Animator animation) {
						imgWhiteDos.setVisibility(View.GONE);
						layMasterComp.setVisibility(View.VISIBLE);
					}
				});
	}
	public void resetImages(){
		ivAnimacion.setImageDrawable(null);
		intentoUno.setImageDrawable(null);
		intentoDos.setImageDrawable(null);
		intentoTres.setImageDrawable(null);
		ImagenBackDespegue.setImageDrawable(null);
		despegueOk.setImageDrawable(null);
		despegueFail.setImageDrawable(null);
		cargaOk.setImageDrawable(null);
		cargaFail.setImageDrawable(null);
		flecha.setImageDrawable(null);
		flechaCarga.setImageDrawable(null);
		imgBackCarga.setImageDrawable(null);
		imgWhiteDos.setImageDrawable(null);
		((BitmapDrawable) ivAnimacion.getDrawable()).setCallback(null);
		((BitmapDrawable) intentoUno.getDrawable()).setCallback(null);
		((BitmapDrawable) intentoDos.getDrawable()).setCallback(null);
		((BitmapDrawable) intentoTres.getDrawable()).setCallback(null);
		((BitmapDrawable) ImagenBackDespegue.getDrawable()).setCallback(null);
		((BitmapDrawable) despegueOk.getDrawable()).setCallback(null);
		((BitmapDrawable) despegueFail.getDrawable()).setCallback(null);
		((BitmapDrawable) cargaOk.getDrawable()).setCallback(null);
		((BitmapDrawable) cargaFail.getDrawable()).setCallback(null);
		((BitmapDrawable) flecha.getDrawable()).setCallback(null);
		((BitmapDrawable) flechaCarga.getDrawable()).setCallback(null);
		((BitmapDrawable) imgBackCarga.getDrawable()).setCallback(null);
		((BitmapDrawable) imgWhiteDos.getDrawable()).setCallback(null);
	}

	@Override
	public void onBackPressed() {
		return;
	}
}
