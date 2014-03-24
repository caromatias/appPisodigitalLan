package com.caromatias.apppisodigitallan;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class LaminaEcuadorActivity extends Activity {

	private Button btnGoGame;
	private Animation animGoGame;
	private ImageView imgMapaEcuador;
	private Animation animMapaEcuador;
	private Animation animMapaEcuadorOut;
	private Button btnInter;
	private Button btnPeru;
	private Button btnBrasil;
	private Button btnEcuador;
	private Button btnColombia;
	private Button btnChile;
	private Button btnArgentina;
	private Animation animMapaInter;
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
		setContentView(R.layout.activity_lamina_ecuador);
		initVars();
		imgMapaEcuador.startAnimation(animMapaEcuador);
		btnGoGame.startAnimation(animGoGame);
		animaCiudadesEcuador();
		initButtons();
		activeButton();
		popUpInfo();
		cambiaActivity();
		desactivaBotones();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lamina_ecuador, menu);
		return true;
	}

	public void initButtons() {
		findViewById(R.id.btn_argentina).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						reseteaBotones();
						compAnimationBienvenida(1);
						imgMapaEcuador.startAnimation(animMapaInter);
						btnArgentina.setBackgroundResource(R.drawable.botonrojo);
						btnArgentina.setEnabled(false);
					}
				});
		findViewById(R.id.btn_brasil).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				reseteaBotones();
				compAnimationBienvenida(2);
				imgMapaEcuador.startAnimation(animMapaInter);
				btnBrasil.setBackgroundResource(R.drawable.botonrojo);
				btnBrasil.setEnabled(false);
			}
		});

		findViewById(R.id.btn_chile).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				reseteaBotones();
				compAnimationBienvenida(3);
				imgMapaEcuador.startAnimation(animMapaInter);
				btnChile.setBackgroundResource(R.drawable.botonrojo);
				btnChile.setEnabled(false);
			}
		});
		findViewById(R.id.btn_colombia).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						reseteaBotones();
						compAnimationBienvenida(4);
						imgMapaEcuador.startAnimation(animMapaInter);
						btnColombia.setBackgroundResource(R.drawable.botonrojo);
						btnColombia.setEnabled(false);
					}
				});
		findViewById(R.id.btn_ecuador).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						reseteaBotones();
						compAnimationBienvenida(5);
						imgMapaEcuador.startAnimation(animMapaInter);
						btnEcuador.setBackgroundResource(R.drawable.botonrojo);
						btnEcuador.setEnabled(false);
					}
				});
		findViewById(R.id.btn_peru).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				reseteaBotones();
				compAnimationBienvenida(6);
				imgMapaEcuador.startAnimation(animMapaInter);
				btnPeru.setBackgroundResource(R.drawable.botonrojo);
				btnPeru.setEnabled(false);
			}
		});
		findViewById(R.id.btn_internacional).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				reseteaBotones();
				compAnimationBienvenida(7);
				imgMapaEcuador.startAnimation(animMapaInter);
				btnInter.setBackgroundResource(R.drawable.botonrojo);
				btnInter.setEnabled(false);
			}
		});
	}

	public void initVars() {
		imgMapaEcuador = (ImageView) findViewById(R.id.mapa_ecuador);
		animMapaEcuador = AnimationUtils.loadAnimation(this,
				R.anim.anim_mapa_in);
		animMapaEcuadorOut = AnimationUtils.loadAnimation(this,
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
		contenedorBotones = (RelativeLayout) findViewById(R.id.lay_mapa_ecuador);
		layPopupInfo = (RelativeLayout) findViewById(R.id.lay_popup_info);
		tituloPopInfo = (TextView) findViewById(R.id.txt_titulo_popup_info);
		descPopInfo = (TextView) findViewById(R.id.txt_descripcion_popup_info);
		animaciones = AnimationUtils.loadAnimation(this, R.anim.fade_out);
		animacionesdos = AnimationUtils.loadAnimation(this, R.anim.fade_in);
	}

	public void animaCiudadesEcuador() {
		RelativeLayout layIslaGalapagos = (RelativeLayout) findViewById(R.id.lay_ecuador_01);
		Animation animIslaGalapagos = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_08);
		layIslaGalapagos.startAnimation(animIslaGalapagos);

		RelativeLayout layBaltro = (RelativeLayout) findViewById(R.id.lay_ecuador_02);
		Animation animBaltro = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_09);
		layBaltro.startAnimation(animBaltro);

		RelativeLayout laySanCristobal = (RelativeLayout) findViewById(R.id.lay_ecuador_03);
		Animation animSanCristobal = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_10);
		laySanCristobal.startAnimation(animSanCristobal);

		RelativeLayout layGuayaquil = (RelativeLayout) findViewById(R.id.lay_ecuador_04);
		Animation animGuayaquil = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_11);
		layGuayaquil.startAnimation(animGuayaquil);

		RelativeLayout layQuito = (RelativeLayout) findViewById(R.id.lay_ecuador_05);
		Animation animQuito = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_12);
		layQuito.startAnimation(animQuito);

		RelativeLayout layCuenca = (RelativeLayout) findViewById(R.id.lay_ecuador_06);
		Animation animCuenca = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_13);
		animCuenca.setAnimationListener(new Animation.AnimationListener() {
			@Override
			public void onAnimationEnd(Animation animation) {
				imgMapaEcuador.setVisibility(View.VISIBLE);
				imgMapaEcuador.startAnimation(animacionesdos);
				findViewById(R.id.mapa_ecuador_ant).startAnimation(animaciones);
				contenedorBotones.startAnimation(animaciones);
				findViewById(R.id.mapa_ecuador_ant).setVisibility(View.GONE);
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
		layCuenca.startAnimation(animCuenca);
	}

	public void activeButton() {
		btnEcuador.setBackgroundResource(R.drawable.botonrojo);
		btnEcuador.setEnabled(false);
	}
	public void compAnimationBienvenida(int pais){
		animMapaInter = AnimationUtils.loadAnimation(this,R.anim.anim_mapa_inter_out);
		switch (pais) {
		case 1:
			animMapaInter.setAnimationListener(new Animation.AnimationListener() {
	            @Override
	            public void onAnimationEnd(Animation animation) {
	            	imgMapaEcuador.setVisibility(View.GONE);
	            	Intent act = new Intent(LaminaEcuadorActivity.this,LaminaArgentinaActivity.class);
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
	            	imgMapaEcuador.setVisibility(View.GONE);
	            	Intent act = new Intent(LaminaEcuadorActivity.this,LaminaBrasilActivity.class);
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
	            	imgMapaEcuador.setVisibility(View.GONE);
	            	Intent act = new Intent(LaminaEcuadorActivity.this,LaminaChileActivity.class);
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
	            	imgMapaEcuador.setVisibility(View.GONE);
	            	Intent act = new Intent(LaminaEcuadorActivity.this,LaminaColombiaActivity.class);
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
	            	imgMapaEcuador.setVisibility(View.GONE);
	            	Intent act = new Intent(LaminaEcuadorActivity.this,LaminaEcuadorActivity.class);
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
	            	imgMapaEcuador.setVisibility(View.GONE);
	            	Intent act = new Intent(LaminaEcuadorActivity.this,LaminaPeruActivity.class);
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
	            	imgMapaEcuador.setVisibility(View.GONE);
	            	Intent act = new Intent(LaminaEcuadorActivity.this,LaminaMundialActivity.class);
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

		findViewById(R.id.lay_apretable_ecuador).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						if (doubleClick == true) {
							tituloPopInfo.setText(getResources().getString(
									R.string.titulo_pop_info1));
							descPopInfo.setText(getResources().getString(
									R.string.desc_pop_info1));
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
						LaminaBienvenidaActivity.mpFondoUno.stop();
						LaminaBienvenidaActivity.mpFondoUno.release();
						Intent act = new Intent(LaminaEcuadorActivity.this,LaminaDosActivity.class);
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
