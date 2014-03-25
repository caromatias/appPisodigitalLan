package com.caromatias.apppisodigitallan;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnDoubleTapListener;
import android.view.GestureDetector.OnGestureListener;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class LaminaArgentinaActivity extends Activity {

	private Button btnGoGame;
	private Animation animGoGame; 
	private ImageView imgMapaArgentina;
	private Animation animMapaArgentina;
	private Animation animMapaArgentinaOut;
	private Button btnInter;
	private Button btnPeru;
	private Button btnBrasil;
	private Button btnEcuador;
	private Button btnColombia;
	private Button btnChile;
	private Button btnArgentina;
	private Animation animMapaInter;
	private ImageView imgArgentinaView;
	private RelativeLayout contenedorBotones;
	private RelativeLayout layPopupInfo;
	private boolean doubleClick = false;
	private TextView tituloPopInfo;
	private TextView descPopInfo;
	private Animation animaciones;
	private Animation animacionesdos;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lamina_argentina);
		initVars();
		imgMapaArgentina.startAnimation(animMapaArgentina);
		btnGoGame.startAnimation(animGoGame);
		initButtons();
		animaCiudadesArgentina();
		activeButton();
		popUpInfo();
		cambiaActivity();
		desactivaBotones();
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
						btnGoGame.setEnabled(false);
						reseteaBotones();
						compAnimationBienvenida(1);
						imgMapaArgentina.startAnimation(animMapaInter);
						btnArgentina.setBackgroundResource(R.drawable.botonrojo);
						btnArgentina.setEnabled(false);
					}
				});
		findViewById(R.id.btn_brasil).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				btnGoGame.setEnabled(false);
				reseteaBotones();
				compAnimationBienvenida(2);
				imgMapaArgentina.startAnimation(animMapaInter);
				btnBrasil.setBackgroundResource(R.drawable.botonrojo);
				btnBrasil.setEnabled(false);
			}
		});

		findViewById(R.id.btn_chile).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				btnGoGame.setEnabled(false);
				reseteaBotones();
				compAnimationBienvenida(3);
				imgMapaArgentina.startAnimation(animMapaInter);
				btnChile.setBackgroundResource(R.drawable.botonrojo);
				btnChile.setEnabled(false);
			}
		});
		findViewById(R.id.btn_colombia).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						btnGoGame.setEnabled(false);
						reseteaBotones();
						compAnimationBienvenida(4);
						imgMapaArgentina.startAnimation(animMapaInter);
						btnColombia.setBackgroundResource(R.drawable.botonrojo);
						btnColombia.setEnabled(false);
					}
				});
		findViewById(R.id.btn_ecuador).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						btnGoGame.setEnabled(false);
						reseteaBotones();
						compAnimationBienvenida(5);
						imgMapaArgentina.startAnimation(animMapaInter);
						btnEcuador.setBackgroundResource(R.drawable.botonrojo);
						btnEcuador.setEnabled(false);
					}
				});
		findViewById(R.id.btn_peru).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				btnGoGame.setEnabled(false);
				reseteaBotones();
				compAnimationBienvenida(6);
				imgMapaArgentina.startAnimation(animMapaInter);
				btnPeru.setBackgroundResource(R.drawable.botonrojo);
				btnPeru.setEnabled(false);
			}
		});
		findViewById(R.id.btn_internacional).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				btnGoGame.setEnabled(false);
				reseteaBotones();
				compAnimationBienvenida(7);
				imgMapaArgentina.startAnimation(animMapaInter);
				btnInter.setBackgroundResource(R.drawable.botonrojo);
				btnInter.setEnabled(false);
			}
		});
	}

	public void initVars() {
		imgMapaArgentina = (ImageView) findViewById(R.id.mapa_argentina);
		animMapaArgentina = AnimationUtils.loadAnimation(this,
				R.anim.anim_mapa_in);
		animMapaArgentinaOut = AnimationUtils.loadAnimation(this,
				R.anim.anim_mapa_inter_out);
		btnGoGame = (Button) findViewById(R.id.btn_ir_al_juego);
		animGoGame = AnimationUtils.loadAnimation(this, R.anim.animacion_sin_fade);
		animMapaInter = AnimationUtils.loadAnimation(this,
				R.anim.anim_mapa_inter_out);
		btnInter = (Button) findViewById(R.id.btn_internacional);
		btnPeru = (Button) findViewById(R.id.btn_peru);
		btnBrasil = (Button) findViewById(R.id.btn_brasil);
		btnEcuador = (Button) findViewById(R.id.btn_ecuador);
		btnColombia = (Button) findViewById(R.id.btn_colombia);
		btnChile = (Button) findViewById(R.id.btn_chile);
		btnArgentina = (Button) findViewById(R.id.btn_argentina);
		imgArgentinaView = (ImageView) findViewById(R.drawable.fondo_mapas);
		contenedorBotones = (RelativeLayout) findViewById(R.id.botones_mapa_argentina);
		layPopupInfo = (RelativeLayout) findViewById(R.id.lay_popup_info);
		tituloPopInfo = (TextView) findViewById(R.id.txt_titulo_popup_info);
		descPopInfo = (TextView) findViewById(R.id.txt_descripcion_popup_info);
		animaciones = AnimationUtils.loadAnimation(this, R.anim.fade_out_mapas);
		animacionesdos = AnimationUtils.loadAnimation(this, R.anim.fade_in_mapas);
	}

	public void animaCiudadesArgentina() {
		RelativeLayout laySalta = (RelativeLayout) findViewById(R.id.lay_argentina_01);
		Animation animSalta = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_08);
		laySalta.startAnimation(animSalta);

		RelativeLayout layTucuman = (RelativeLayout) findViewById(R.id.lay_argentina_02);
		Animation animTucuman = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_09);
		layTucuman.startAnimation(animTucuman);

		RelativeLayout layIguazu = (RelativeLayout) findViewById(R.id.lay_argentina_03);
		Animation animIguazu = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_10);
		layIguazu.startAnimation(animIguazu);

		RelativeLayout layCordoba = (RelativeLayout) findViewById(R.id.lay_argentina_04);
		Animation animCordoba = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_11);
		layCordoba.startAnimation(animCordoba);

		RelativeLayout laySanJuan = (RelativeLayout) findViewById(R.id.lay_argentina_05);
		Animation animSanJuan = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_12);
		laySanJuan.startAnimation(animSanJuan);

		RelativeLayout layMendoza = (RelativeLayout) findViewById(R.id.lay_argentina_06);
		Animation animMendoza = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_13);
		layMendoza.startAnimation(animMendoza);

		RelativeLayout layBuenosAires = (RelativeLayout) findViewById(R.id.lay_argentina_07);
		Animation animBuenosAires = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_14);
		layBuenosAires.startAnimation(animBuenosAires);

		RelativeLayout layNeuquen = (RelativeLayout) findViewById(R.id.lay_argentina_08);
		Animation animNeuquen = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_15);
		layNeuquen.startAnimation(animNeuquen);

		RelativeLayout layBahiaBlabca = (RelativeLayout) findViewById(R.id.lay_argentina_10);
		Animation animBahiaBlanca = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_16);
		layBahiaBlabca.startAnimation(animBahiaBlanca);

		RelativeLayout layBariloche = (RelativeLayout) findViewById(R.id.lay_argentina_11);
		Animation animBariloche = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_17);
		layBariloche.startAnimation(animBariloche);

		RelativeLayout layComodoro = (RelativeLayout) findViewById(R.id.lay_argentina_12);
		Animation animComodoro = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_18);
		layComodoro.startAnimation(animComodoro);

		RelativeLayout layCalafate = (RelativeLayout) findViewById(R.id.lay_argentina_13);
		Animation animCalafate = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_19);
		layCalafate.startAnimation(animCalafate);

		RelativeLayout layRioGallegos = (RelativeLayout) findViewById(R.id.lay_argentina_14);
		Animation animRioGallegos = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_20);
		layRioGallegos.startAnimation(animRioGallegos);

		RelativeLayout layUshuai = (RelativeLayout) findViewById(R.id.lay_argentina_15);
		Animation animUshuai = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_21);
		animUshuai.setAnimationListener(new Animation.AnimationListener() {
			@Override
			public void onAnimationEnd(Animation animation) {
				imgMapaArgentina.setVisibility(View.VISIBLE);
				imgMapaArgentina.startAnimation(animacionesdos);
				findViewById(R.id.mapa_argentina_ant).startAnimation(animaciones);
				contenedorBotones.startAnimation(animaciones);
				findViewById(R.id.mapa_argentina_ant).setVisibility(View.GONE);
				contenedorBotones.setVisibility(View.GONE);
				activaBotones();
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
		layUshuai.startAnimation(animUshuai);
	}

	public void activeButton() {
		btnArgentina.setBackgroundResource(R.drawable.botonrojo);
		btnArgentina.setEnabled(false);
	}
	
	public void compAnimationBienvenida(int pais){
		animMapaInter = AnimationUtils.loadAnimation(this,R.anim.anim_mapa_inter_out);
		switch (pais) {
		case 1:
			animMapaInter.setAnimationListener(new Animation.AnimationListener() {
	            @Override
	            public void onAnimationEnd(Animation animation) {
	            	imgMapaArgentina.setVisibility(View.GONE);
	            	Intent act = new Intent(LaminaArgentinaActivity.this,LaminaArgentinaActivity.class);
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
	            	imgMapaArgentina.setVisibility(View.GONE);
	            	Intent act = new Intent(LaminaArgentinaActivity.this,LaminaBrasilActivity.class);
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
	            	imgMapaArgentina.setVisibility(View.GONE);
	            	Intent act = new Intent(LaminaArgentinaActivity.this,LaminaChileActivity.class);
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
	            	imgMapaArgentina.setVisibility(View.GONE);
	            	Intent act = new Intent(LaminaArgentinaActivity.this,LaminaColombiaActivity.class);
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
	            	imgMapaArgentina.setVisibility(View.GONE);
	            	Intent act = new Intent(LaminaArgentinaActivity.this,LaminaEcuadorActivity.class);
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
	            	imgMapaArgentina.setVisibility(View.GONE);
	            	Intent act = new Intent(LaminaArgentinaActivity.this,LaminaPeruActivity.class);
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
	            	imgMapaArgentina.setVisibility(View.GONE);
	            	Intent act = new Intent(LaminaArgentinaActivity.this,LaminaMundialActivity.class);
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

	public void popUpInfo() {
		findViewById(R.id.btn_cerrar_popup_info).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						layPopupInfo.setVisibility(View.GONE);
					}
				});

		findViewById(R.id.lay_apretable_argentina).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						if (doubleClick == true) {
							tituloPopInfo.setText(getResources().getString(
									R.string.titulo_pop_info2));
							descPopInfo.setText(getResources().getString(
									R.string.desc_pop_info2));
							layPopupInfo.bringToFront();
							layPopupInfo.setVisibility(View.VISIBLE);
						} else {
							doubleClick = true;
							Handler handlerDoubleClickUno = new Handler();
							handlerDoubleClickUno.postDelayed(new Runnable() {
								@Override
								public void run() {
									doubleClick = false;
								}
							}, 600);
						}
					}
				});
	}
	public void cambiaActivity() {
		btnGoGame.setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						btnGoGame.setEnabled(false);
						System.gc();
						//LaminaBienvenidaActivity.mpFondoUno.stop();
						//LaminaBienvenidaActivity.mpFondoUno.release();
						Intent act = new Intent(LaminaArgentinaActivity.this,LaminaDosActivity.class);
						act.putExtra("isInterface", 0);
						startActivity(act);
						overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
					}
				});
	}
	public void reseteaBotones(){
		btnArgentina.setBackgroundResource(R.drawable.botongris);
		btnBrasil.setBackgroundResource(R.drawable.botongris);
		btnChile.setBackgroundResource(R.drawable.botongris);
		btnColombia.setBackgroundResource(R.drawable.botongris);
		btnEcuador.setBackgroundResource(R.drawable.botongris);
		btnPeru.setBackgroundResource(R.drawable.botongris);
		btnInter.setBackgroundResource(R.drawable.botongris);
	}
	public void desactivaBotones(){
		btnArgentina.setEnabled(false);
		btnBrasil.setEnabled(false);
		btnChile.setEnabled(false);
		btnColombia.setEnabled(false);
		btnEcuador.setEnabled(false);
		btnPeru.setEnabled(false);
		btnInter.setEnabled(false);
	}
	public void activaBotones(){
		btnArgentina.setEnabled(true);
		btnBrasil.setEnabled(true);
		btnChile.setEnabled(true);
		btnColombia.setEnabled(true);
		btnEcuador.setEnabled(true);
		btnPeru.setEnabled(true);
		btnInter.setEnabled(true);
	}
	
}
