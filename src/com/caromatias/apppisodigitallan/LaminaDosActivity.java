package com.caromatias.apppisodigitallan;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.SystemClock;
import android.app.Activity;
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
	private Animation animMensajesDespegue;
	private Animation animMensajesDespegueOut;
	private Animation animMensajesDespegueFailIn;
	private Animation animMensajesDespegueFailOut;
	private RelativeLayout imgLuzEstado;
	private ImageView flecha;
	private Animation animFlechaRebote;
	// ///////////////////////////////////

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
		animMensajesDespegue = AnimationUtils.loadAnimation(this,R.anim.anim_scale_translation_world);
		animMensajesDespegueOut = AnimationUtils.loadAnimation(this,R.anim.anim_translacion_out);
		animMensajesDespegueFailIn = AnimationUtils.loadAnimation(this,R.anim.anim_translacion_in);
		animMensajesDespegueFailOut = AnimationUtils.loadAnimation(this,R.anim.anim_translacion_fail_out);
		animFlechaRebote = AnimationUtils.loadAnimation(this,R.anim.anim_rebote_flecha);
		

		findViewById(R.id.button1).setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				estadoProgress = 3;
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
				}
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
										runOnUiThread(new Runnable() // run on ui thread
										{
											public void run() {
												flecha.setVisibility(View.GONE);
												despegueFail.setVisibility(View.VISIBLE);
												despegueFail.startAnimation(animMensajesDespegueFailIn);
												imgLuzEstado.setBackgroundResource(R.anim.anim_despegue_fail);
												savingAnimationLuz = (AnimationDrawable) imgLuzEstado.getBackground();
												savingAnimationLuz.start();
											}
										});
										intentoDos();
									} else if (mProgressStatus >= 70) {
										runOnUiThread(new Runnable() // run on ui thread
										{
											public void run() {
												despegueOk.setVisibility(View.VISIBLE);
												despegueOk.startAnimation(animMensajesDespegue);
												imgLuzEstado.setBackgroundResource(R.anim.anim_despegue_ok);
												savingAnimationLuz = (AnimationDrawable) imgLuzEstado.getBackground();
												savingAnimationLuz.start();
											}
										});
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
										if (estadoProgress == 3
												&& mProgressStatus > 70) {
											ImagenBackDespegue
													.startAnimation(animImgBackDespegue);
											// videoBackDespegue.start();
											ImagenBackDespegue
													.setVisibility(View.GONE);
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
						layPopUpCarga.startAnimation(animPopup);
						layPopUpCarga.setVisibility(View.GONE);
						// layLogoIzq.setVisibility(View.VISIBLE);
						// layLogoIzq.startAnimation(animLogoIzq);
						muestraCuentaAtas();
					}
				});

		// ///////////////////////////////////////

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
		findViewById(R.id.lay_pop_cuenta_atras).setVisibility(View.VISIBLE);
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
		comienzaCarga += 1;
		new CountDownTimer(10000, 1000) {

			public void onTick(long millisUntilFinished) {
				tiempoParaCarga.setText("0" + millisUntilFinished / 1000);
			}

			public void onFinish() {
				tiempoParaCarga.setText("00");
				activaCarga.setEnabled(false);
				if (currentRotation < 70) {
					/*
					 * Intent act = new Intent( LaminaDosActivity.this,
					 * GameOverActivity.class); act.putExtra("game", 1);
					 * startActivity(act); overridePendingTransition(
					 * R.anim.fade_in, R.anim.fade_out);
					 */
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
					} else if (intentosCarga == 3) {
						Intent act = new Intent(LaminaDosActivity.this,
								GameOverActivity.class);
						act.putExtra("game", 1);
						startActivity(act);
						overridePendingTransition(R.anim.fade_in,
								R.anim.fade_out);
					}
					muestraCuentaAtas();
				} else if (currentRotation >= 70) {
					Intent act = new Intent(LaminaDosActivity.this,
							LaminaTresActivity.class);
					act.putExtra("ruta", rutaSeleccionada);
					startActivity(act);
					overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
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
						despegueFail.startAnimation(animMensajesDespegueFailOut);
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
								despegueFail.setVisibility(View.VISIBLE);
								despegueFail.startAnimation(animMensajesDespegueFailIn);
								imgLuzEstado.setBackgroundResource(R.anim.anim_despegue_fail);
								savingAnimationLuz = (AnimationDrawable) imgLuzEstado.getBackground();
								savingAnimationLuz.start();
							}
						});
						intentoTres();
					} else if (mProgressStatus >= 70) {
						runOnUiThread(new Runnable() // run on ui thread
						{
							public void run() {
								despegueOk.setVisibility(View.VISIBLE);
								despegueOk.startAnimation(animMensajesDespegue);
								imgLuzEstado.setBackgroundResource(R.anim.anim_despegue_ok);
								savingAnimationLuz = (AnimationDrawable) imgLuzEstado.getBackground();
								savingAnimationLuz.start();
							}
						});
						videoBackDespegue.start();
					}
					break;
				}
				mProgress.setProgress(mProgressStatus);
				runOnUiThread(new Runnable() // run on ui thread
				{
					public void run() {
						textoDos.setText(String.valueOf(mProgressStatus + "%"));
						if (estadoProgress == 3 && mProgressStatus > 70) {
							ImagenBackDespegue
									.startAnimation(animImgBackDespegue);
							// videoBackDespegue.start();
							ImagenBackDespegue.setVisibility(View.GONE);
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
						despegueFail.startAnimation(animMensajesDespegueFailOut);
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
						Intent act = new Intent(LaminaDosActivity.this,
								GameOverActivity.class);
						act.putExtra("game", 1);
						startActivity(act);
						overridePendingTransition(R.anim.fade_in,
								R.anim.fade_out);
					} else if (mProgressStatus >= 70) {
						runOnUiThread(new Runnable() // run on ui thread
						{
							public void run() {
								despegueOk.setVisibility(View.VISIBLE);
								despegueOk.startAnimation(animMensajesDespegue);
								imgLuzEstado.setBackgroundResource(R.anim.anim_despegue_fail);
								savingAnimationLuz = (AnimationDrawable) imgLuzEstado.getBackground();
								savingAnimationLuz.start();
							}
						});
						videoBackDespegue.start();
					}
					break;
				}
				mProgress.setProgress(mProgressStatus);
				runOnUiThread(new Runnable() // run on ui thread
				{
					public void run() {
						textoDos.setText(String.valueOf(mProgressStatus + "%"));
						if (estadoProgress == 3 && mProgressStatus > 70) {
							ImagenBackDespegue
									.startAnimation(animImgBackDespegue);
							imgLuzEstado.setBackgroundResource(R.anim.anim_despegue_ok);
							savingAnimationLuz = (AnimationDrawable) imgLuzEstado.getBackground();
							savingAnimationLuz.start();
							ImagenBackDespegue.setVisibility(View.GONE);
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
			}
		});
		// ////////////////////////////
		// /////// BOTON DOS //////////
		findViewById(R.id.btnDos).setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
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
			}
		});
		// ////////////////////////////
		// /////// BOTON TRES //////////
		findViewById(R.id.btnTres).setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
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
			}
		});
		// ////////////////////////////
		// /////// BOTON CUATRO //////////
		findViewById(R.id.btnCuatro).setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
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
			}
		});
		// ////////////////////////////
		// /////// BOTON CINCO //////////
		findViewById(R.id.btnCinco).setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
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
			}
		});
		// ////////////////////////////
		// /////// BOTON SEIS //////////
		findViewById(R.id.btnSeis).setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
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
			}
		});
		// ////////////////////////////
		// /////// BOTON SIETE //////////
		findViewById(R.id.btnSiete).setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
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
			}
		});
		// ////////////////////////////
		// /////// BOTON OCHO //////////
		findViewById(R.id.btnOcho).setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
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
			}
		});
		// ////////////////////////////
		// /////// BOTON NUEVE //////////
		findViewById(R.id.btnNueve).setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
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
}
