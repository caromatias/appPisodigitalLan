package com.caromatias.apppisodigitallan;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.os.Handler;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
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

public class LaminaBienvenidaActivity extends Activity {

	private int posicionMenu = 1;
	private VideoView videoLaminaDos;
	private VideoView videoView;
	private ImageView imgMapaBienvenida;
	private ImageView imgWhite;
	private RelativeLayout lay_principal;
	private Button btnInter;
	private Button btnPeru;
	private Button btnBrasil;
	private Button btnEcuador;
	private Button btnColombia;
	private Button btnChile;
	private Button btnArgentina;
	private Button btnFlota;
	private Button btnDestinos;
	private Button botonAnimado;
	private Button btnGoGame;
	private Button btnGoGameText;
	public static MediaPlayer mpFondoUno;
	private RelativeLayout layFlota;
	private RelativeLayout layDestinos;
	private RelativeLayout popNuestraFlota;
	private RelativeLayout layPanelRutas;
	private RelativeLayout goGameMaster;
	private RelativeLayout layVideoMain;
	private Button animLayFlota;
	private Animation animLogo;
	private Button animLayDes;
	private Animation animDes;
	private Animation animFlota;
	private Animation animacion;
	private Animation animGoGame;
	private Animation animGoGameText;
	private Animation animVideoMain;
	private Animation animVideoMainOut;
	private Animation animInInter;
	private Animation animPanel;
	private TextView tituloPopInfo;
	private TextView descPopInfo;
	private Animation animMapaInter;
	// ///////////////////////////////////
	public static int juego = 1;
	public static int btn1 = 0;
	public static int btn2 = 0;
	public static int btn3 = 0;
	public static int btn4 = 0;
	public static int btn5 = 0;
	public static int btn6 = 0;
	public static int btn7 = 0;
	public static int btn8 = 0;
	public static int btn9 = 0;
	public static int btn10 = 0;
	public static int btn11 = 0;
	public static int btn12 = 0;
	public static int btn13 = 0;
	public static int btn14 = 0;
	public static int btn15 = 0;
	public static int btn16 = 0;
	public static int btn17 = 0;
	public static int btn18 = 0;
	public static int btn19 = 0;
	public static int btn20 = 0;
	public static int btn21 = 0;
	// ////////////////////////////////////
	private Handler handler;
	private Handler  handlerDos;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lamina_bienvenida);
		// INICIALIZACIÓN DE VARIABLES
		initVar();
		// EJECUCIÓN
		exMain();
		initButtons();
		reseteaRutas();
		cambiaActivity();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lamina_argentina, menu);
		return true;
	}

	public void initButtons() {
		findViewById(R.id.btn_argentina).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						compAnimationBienvenida(1);
						imgMapaBienvenida.startAnimation(animMapaInter);
						btnArgentina.setBackgroundResource(R.drawable.botonrojo);
						btnArgentina.setEnabled(false);
					}
				});
		findViewById(R.id.btn_brasil).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				compAnimationBienvenida(2);
				imgMapaBienvenida.startAnimation(animMapaInter);
				btnBrasil.setBackgroundResource(R.drawable.botonrojo);
				btnBrasil.setEnabled(false);
			}
		});

		findViewById(R.id.btn_chile).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				compAnimationBienvenida(3);
				imgMapaBienvenida.startAnimation(animMapaInter);
				btnChile.setBackgroundResource(R.drawable.botonrojo);
				btnChile.setEnabled(false);
			}
		});
		findViewById(R.id.btn_colombia).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						compAnimationBienvenida(4);
						imgMapaBienvenida.startAnimation(animMapaInter);
						btnColombia.setBackgroundResource(R.drawable.botonrojo);
						btnColombia.setEnabled(false);
					}
				});
		findViewById(R.id.btn_ecuador).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						compAnimationBienvenida(5);
						imgMapaBienvenida.startAnimation(animMapaInter);
						btnEcuador.setBackgroundResource(R.drawable.botonrojo);
						btnEcuador.setEnabled(false);
					}
				});
		findViewById(R.id.btn_peru).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				compAnimationBienvenida(6);
				imgMapaBienvenida.startAnimation(animMapaInter);
				btnPeru.setBackgroundResource(R.drawable.botonrojo);
				btnPeru.setEnabled(false);
			}
		});
		findViewById(R.id.btn_internacional).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				compAnimationBienvenida(7);
				imgMapaBienvenida.startAnimation(animMapaInter);
				btnInter.setBackgroundResource(R.drawable.botonrojo);
				btnInter.setEnabled(false);
			}
		});
	}

	public void initVar() {
		videoView = (VideoView) findViewById(R.id.video_main);
		mpFondoUno = MediaPlayer.create(this, R.raw.the_shining);
		popNuestraFlota = (RelativeLayout) findViewById(R.id.lay_img_flota);
		animLogo = AnimationUtils
				.loadAnimation(this, R.anim.anim_lineas_brasil);
		animLayDes = (Button) findViewById(R.id.button_nuestros_destinos);
		animDes = AnimationUtils.loadAnimation(this, R.anim.anim_lineas_brasil);
		animFlota = AnimationUtils.loadAnimation(this,
				R.anim.anim_lineas_brasil);
		tituloPopInfo = (TextView) findViewById(R.id.txt_titulo_popup_info);
		descPopInfo = (TextView) findViewById(R.id.txt_descripcion_popup_info);
		layPanelRutas = (RelativeLayout) findViewById(R.id.panel);
		botonAnimado = (Button) findViewById(R.id.btn_com);
		animacion = AnimationUtils.loadAnimation(this, R.anim.animacion);
		goGameMaster = (RelativeLayout) findViewById(R.id.go_game_master);
		btnGoGame = (Button) findViewById(R.id.btn_ir_al_juego);
		animGoGame = AnimationUtils.loadAnimation(this, R.anim.animacion);
		btnGoGameText = (Button) findViewById(R.id.btn_ir_al_juego_text);
		animGoGameText = AnimationUtils.loadAnimation(this, R.anim.slide_out);
		animVideoMain = AnimationUtils.loadAnimation(this, R.anim.fade_in);
		animVideoMainOut = AnimationUtils.loadAnimation(this, R.anim.fade_out);
		animPanel = AnimationUtils.loadAnimation(this,
				R.anim.anim_lamina_home_market);
		imgMapaBienvenida = (ImageView) findViewById(R.id.mapa_bienvenida);
		imgWhite = (ImageView) findViewById(R.id.img_fade_white);
		layVideoMain = (RelativeLayout) findViewById(R.id.lay_video_main);
		animLayFlota = (Button) findViewById(R.id.button_nuestra_flota);
		animMapaInter = AnimationUtils.loadAnimation(this,
				R.anim.anim_mapa_inter_out);
		btnFlota = (Button) findViewById(R.id.button_nuestra_flota);
		btnDestinos = (Button) findViewById(R.id.button_nuestros_destinos);
		btnPeru = (Button) findViewById(R.id.btn_peru);
		btnBrasil = (Button) findViewById(R.id.btn_brasil);
		btnEcuador = (Button) findViewById(R.id.btn_ecuador);
		btnColombia = (Button) findViewById(R.id.btn_colombia);
		btnChile = (Button) findViewById(R.id.btn_chile);
		btnArgentina = (Button) findViewById(R.id.btn_argentina);
		btnInter = (Button) findViewById(R.id.btn_internacional);
		// layPopupInfo = (RelativeLayout) findViewById(R.id.lay_popup_info);
	}

	public void exMain() {
		videoView.setVideoPath("android.resource://com.caromatias.apppisodigitallan/"+ R.raw.back_a_2);
		videoLaminaDos = (VideoView) findViewById(R.id.video_lamina_uno);
		videoLaminaDos.setVideoPath("android.resource://com.caromatias.apppisodigitallan/"+ R.raw.world_transition);
		videoView.start();
		videoView.setOnPreparedListener(new OnPreparedListener() {
			@Override
			public void onPrepared(MediaPlayer mp) {
				mp.setLooping(true);
			}
		});
		
		videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
			public void onCompletion(MediaPlayer mp) {
				//LaminaTresActivity.mpFondo.stop();
				mp.release();
				videoView.stopPlayback();
			}
		});

		botonAnimado.startAnimation(animacion);

		findViewById(R.id.btn_com).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				Button botonAnimado = (Button) findViewById(R.id.btn_com);
				botonAnimado.setBackgroundResource(R.drawable.botoncomenzar);
				fadeInWhite();
				layVideoMain.setVisibility(View.GONE);

				// //////////////////////////////// APARICION DE BOTONES DE
				// FLOTA Y
				// DESTINO ////////////////////////////

				animLayFlota.startAnimation(animFlota);
				animLayDes.startAnimation(animDes);
				
				animEntradaMundo();

				/*
				final Handler handlerTres = new Handler();
				handlerTres.postDelayed(new Runnable() {
					@Override
					public void run() {
						// Do something after 5s = 5000ms
						videoLaminaDos.setVisibility(View.GONE);
						videoLaminaDos.stopPlayback();
					}
				}, 5500);
				*/

				/*
				final Handler handlerCuatro = new Handler();
				handlerCuatro.postDelayed(new Runnable() {
					@Override
					public void run() {
						// Do something after 5s = 5000ms
						goGameMaster.setVisibility(View.VISIBLE);
						layPanelRutas.setVisibility(View.VISIBLE);
						layPanelRutas.startAnimation(animPanel);
						// ////////// ANIMACION GOGAME ///////////////
						btnGoGame.startAnimation(animGoGame);
						btnGoGameText.startAnimation(animGoGameText);
						// ///////////////////////////////////////////
						layPanelRutas.bringToFront();
						goGameMaster.bringToFront();
					}

				}, 6000);
				*/

			}
		});
	}
	public void reseteaRutas() {
		btn1 = 0;
		btn2 = 0;
		btn3 = 0;
		btn4 = 0;
		btn5 = 0;
		btn6 = 0;
		btn7 = 0;
		btn8 = 0;
		btn9 = 0;
		btn10 = 0;
		btn11 = 0;
		btn12 = 0;
		btn13 = 0;
		btn14 = 0;
		btn15 = 0;
		btn16 = 0;
		btn17 = 0;
		btn18 = 0;
		btn19 = 0;
		btn20 = 0;
		btn21 = 0;
		//juego = 1;
	}
	public void cambiaActivity() {
		btnGoGame.setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						System.gc();
						LaminaBienvenidaActivity.mpFondoUno.stop();
						LaminaBienvenidaActivity.mpFondoUno.release();
						Intent act = new Intent(LaminaBienvenidaActivity.this,LaminaDosActivity.class);
						act.putExtra("isInterface", 0);
						startActivity(act);
						overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
					}
				});
	}
	
	public void fadeInWhite() {
		imgWhite.setVisibility(View.VISIBLE);
		imgWhite.setAlpha(0f);
		imgWhite.animate().setDuration(800).alpha(1)
				.setListener(new AnimatorListenerAdapter() {
					@Override
					public void onAnimationEnd(Animator animation) {
						imgWhite.setVisibility(View.VISIBLE);
						mpFondoUno.start();
						mpFondoUno.setLooping(true);
						AudioManager audio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
						int currentVolume = audio
								.getStreamVolume(AudioManager.STREAM_MUSIC);
						int maxVolume = audio
								.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
						float percent = 0.3f;
						int seventyVolume = (int) (maxVolume * percent);
						audio.setStreamVolume(AudioManager.STREAM_MUSIC,
								seventyVolume, 0);
						videoLaminaDos.start();
						videoView.setVisibility(View.GONE);
						fadeOutWhite();
						videoView.stopPlayback();
					}
				});
	}

	public void fadeOutWhite() {
		imgWhite.animate().setDuration(800).alpha(0)
				.setListener(new AnimatorListenerAdapter() {
					@Override
					public void onAnimationEnd(Animator animation) {
						imgWhite.setVisibility(View.GONE);
					}
				});
	}
	public void animEntradaMundo(){
		videoLaminaDos.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
			public void onCompletion(MediaPlayer mp) {
				imgMapaBienvenida.bringToFront();
				setAnimacionListener();
				imgMapaBienvenida.startAnimation(animInInter);
			}
		});
	}
	public void setAnimacionListener(){
		animInInter = AnimationUtils.loadAnimation(this, R.anim.in_mapa_inter);
		animInInter.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd(Animation animation) {
            	goGameMaster.setVisibility(View.VISIBLE);
				layPanelRutas.setVisibility(View.VISIBLE);
				layPanelRutas.startAnimation(animPanel);
				// ////////// ANIMACION GOGAME ///////////////
				btnGoGame.startAnimation(animGoGame);
				btnGoGameText.startAnimation(animGoGameText);
				// ///////////////////////////////////////////
				layPanelRutas.bringToFront();
				goGameMaster.bringToFront();
            }
			@Override
			public void onAnimationRepeat(Animation arg0) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
				videoLaminaDos.setVisibility(View.GONE);
				videoLaminaDos.stopPlayback();				
			}
        });
	}
	public void compAnimationBienvenida(int pais){
		animMapaInter = AnimationUtils.loadAnimation(this,R.anim.anim_mapa_inter_out);
		switch (pais) {
		case 1:
			animMapaInter.setAnimationListener(new Animation.AnimationListener() {
	            @Override
	            public void onAnimationEnd(Animation animation) {
	            	imgMapaBienvenida.setVisibility(View.GONE);
	            	Intent act = new Intent(LaminaBienvenidaActivity.this,LaminaArgentinaActivity.class);
					startActivity(act);
					overridePendingTransition(R.anim.fade_in_paises,R.anim.fade_out_paises);
	            }
				@Override
				public void onAnimationRepeat(Animation arg0) {
					// TODO Auto-generated method stub
				}
				@Override
				public void onAnimationStart(Animation animation) {
					
				}
	        });
			break;
		case 2:
			animMapaInter.setAnimationListener(new Animation.AnimationListener() {
	            @Override
	            public void onAnimationEnd(Animation animation) {
	            	imgMapaBienvenida.setVisibility(View.GONE);
	            	Intent act = new Intent(LaminaBienvenidaActivity.this,LaminaBrasilActivity.class);
					startActivity(act);
					overridePendingTransition(R.anim.fade_in_paises,R.anim.fade_out_paises);
	            }
				@Override
				public void onAnimationRepeat(Animation arg0) {
					// TODO Auto-generated method stub
				}
				@Override
				public void onAnimationStart(Animation animation) {
					
				}
	        });
			break;
		case 3:
			animMapaInter.setAnimationListener(new Animation.AnimationListener() {
	            @Override
	            public void onAnimationEnd(Animation animation) {
	            	imgMapaBienvenida.setVisibility(View.GONE);
	            	Intent act = new Intent(LaminaBienvenidaActivity.this,LaminaChileActivity.class);
					startActivity(act);
					overridePendingTransition(R.anim.fade_in_paises,R.anim.fade_out_paises);
	            }
				@Override
				public void onAnimationRepeat(Animation arg0) {
					// TODO Auto-generated method stub
				}
				@Override
				public void onAnimationStart(Animation animation) {
					
				}
	        });
			break;
		case 4:
			animMapaInter.setAnimationListener(new Animation.AnimationListener() {
	            @Override
	            public void onAnimationEnd(Animation animation) {
	            	imgMapaBienvenida.setVisibility(View.GONE);
	            	Intent act = new Intent(LaminaBienvenidaActivity.this,LaminaColombiaActivity.class);
					startActivity(act);
					overridePendingTransition(R.anim.fade_in_paises,R.anim.fade_out_paises);
	            }
				@Override
				public void onAnimationRepeat(Animation arg0) {
					// TODO Auto-generated method stub
				}
				@Override
				public void onAnimationStart(Animation animation) {
					
				}
	        });
			break;
		case 5:
			animMapaInter.setAnimationListener(new Animation.AnimationListener() {
	            @Override
	            public void onAnimationEnd(Animation animation) {
	            	imgMapaBienvenida.setVisibility(View.GONE);
	            	Intent act = new Intent(LaminaBienvenidaActivity.this,LaminaEcuadorActivity.class);
					startActivity(act);
					overridePendingTransition(R.anim.fade_in_paises,R.anim.fade_out_paises);
	            }
				@Override
				public void onAnimationRepeat(Animation arg0) {
					// TODO Auto-generated method stub
				}
				@Override
				public void onAnimationStart(Animation animation) {
					
				}
	        });
			break;
		case 6:
			animMapaInter.setAnimationListener(new Animation.AnimationListener() {
	            @Override
	            public void onAnimationEnd(Animation animation) {
	            	imgMapaBienvenida.setVisibility(View.GONE);
	            	Intent act = new Intent(LaminaBienvenidaActivity.this,LaminaPeruActivity.class);
					startActivity(act);
					overridePendingTransition(R.anim.fade_in_paises,R.anim.fade_out_paises);
	            }
				@Override
				public void onAnimationRepeat(Animation arg0) {
					// TODO Auto-generated method stub
				}
				@Override
				public void onAnimationStart(Animation animation) {
					
				}
	        });
			break;
		case 7:
			animMapaInter.setAnimationListener(new Animation.AnimationListener() {
	            @Override
	            public void onAnimationEnd(Animation animation) {
	            	imgMapaBienvenida.setVisibility(View.GONE);
	            	Intent act = new Intent(LaminaBienvenidaActivity.this,LaminaMundialActivity.class);
					startActivity(act);
					overridePendingTransition(R.anim.fade_in_paises,R.anim.fade_out_paises);
	            }
				@Override
				public void onAnimationRepeat(Animation arg0) {
					// TODO Auto-generated method stub
				}
				@Override
				public void onAnimationStart(Animation animation) {
					
				}
	        });
			break;

		default:
			break;
		}
	}
	
	@Override
	public void onBackPressed() {
		return;
	}
	@Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        //videoLaminaDos.stopPlayback();
        //videoView.stopPlayback();
        mpFondoUno.release();
        Log.d("Debug", "onDestroy() has been called!");
    }
}