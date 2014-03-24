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

public class LaminaColombiaActivity extends Activity {

	private Button btnGoGame;
	private Animation animGoGame;
	private ImageView imgMapaColombia;
	private Animation animMapaColombia;
	private Animation animMapaColombiaOut;
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
		setContentView(R.layout.activity_lamina_colombia);
		initVars();
		imgMapaColombia.startAnimation(animMapaColombia);
		btnGoGame.startAnimation(animGoGame);
		animaCiudadesColombia();
		initButtons();
		activeButton();
		popUpInfo();
		cambiaActivity();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lamina_colombia, menu);
		return true;
	}

	public void initButtons() {
		findViewById(R.id.btn_argentina).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						reseteaBotones();
						compAnimationBienvenida(1);
						imgMapaColombia.startAnimation(animMapaInter);
						btnArgentina.setBackgroundResource(R.drawable.botonrojo);
						btnArgentina.setEnabled(false);
					}
				});
		findViewById(R.id.btn_brasil).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				reseteaBotones();
				compAnimationBienvenida(2);
				imgMapaColombia.startAnimation(animMapaInter);
				btnBrasil.setBackgroundResource(R.drawable.botonrojo);
				btnBrasil.setEnabled(false);
			}
		});

		findViewById(R.id.btn_chile).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				reseteaBotones();
				compAnimationBienvenida(3);
				imgMapaColombia.startAnimation(animMapaInter);
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
						imgMapaColombia.startAnimation(animMapaInter);
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
						imgMapaColombia.startAnimation(animMapaInter);
						btnEcuador.setBackgroundResource(R.drawable.botonrojo);
						btnEcuador.setEnabled(false);
					}
				});
		findViewById(R.id.btn_peru).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				reseteaBotones();
				compAnimationBienvenida(6);
				imgMapaColombia.startAnimation(animMapaInter);
				btnPeru.setBackgroundResource(R.drawable.botonrojo);
				btnPeru.setEnabled(false);
			}
		});
		findViewById(R.id.btn_internacional).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				reseteaBotones();
				compAnimationBienvenida(7);
				imgMapaColombia.startAnimation(animMapaInter);
				btnInter.setBackgroundResource(R.drawable.botonrojo);
				btnInter.setEnabled(false);
			}
		});
	}

	public void initVars() {
		imgMapaColombia = (ImageView) findViewById(R.id.mapa_colombia);
		animMapaColombia = AnimationUtils.loadAnimation(this,
				R.anim.anim_mapa_in);
		animMapaColombiaOut = AnimationUtils.loadAnimation(this,
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
		contenedorBotones = (RelativeLayout) findViewById(R.id.lay_mapa_colombia);
		layPopupInfo = (RelativeLayout) findViewById(R.id.lay_popup_info);
		tituloPopInfo = (TextView) findViewById(R.id.txt_titulo_popup_info);
		descPopInfo = (TextView) findViewById(R.id.txt_descripcion_popup_info);
		animaciones = AnimationUtils.loadAnimation(this, R.anim.fade_out);
		animacionesdos = AnimationUtils.loadAnimation(this, R.anim.fade_in);
	}

	public void animaCiudadesColombia() {
		RelativeLayout laySanAndres = (RelativeLayout) findViewById(R.id.lay_colombia_01);
		Animation animSanAndres = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_08);
		laySanAndres.startAnimation(animSanAndres);

		RelativeLayout laySantaMarta = (RelativeLayout) findViewById(R.id.lay_colombia_02);
		Animation animSantaMarta = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_09);
		laySantaMarta.startAnimation(animSantaMarta);

		RelativeLayout layBarranquilla = (RelativeLayout) findViewById(R.id.lay_colombia_03);
		Animation animBarranquilla = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_10);
		layBarranquilla.startAnimation(animBarranquilla);

		RelativeLayout layCartagena = (RelativeLayout) findViewById(R.id.lay_colombia_04);
		Animation animCartagena = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_11);
		layCartagena.startAnimation(animCartagena);

		RelativeLayout layValledupar = (RelativeLayout) findViewById(R.id.lay_colombia_05);
		Animation animValledupar = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_12);
		layValledupar.startAnimation(animValledupar);

		RelativeLayout layMonteria = (RelativeLayout) findViewById(R.id.lay_colombia_06);
		Animation animMonteria = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_13);
		layMonteria.startAnimation(animMonteria);

		RelativeLayout layApartado = (RelativeLayout) findViewById(R.id.lay_colombia_07);
		Animation animApartado = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_14);
		layApartado.startAnimation(animApartado);

		RelativeLayout layCucuta = (RelativeLayout) findViewById(R.id.lay_colombia_08);
		Animation animCucuta = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_15);
		layCucuta.startAnimation(animCucuta);

		RelativeLayout layMedellin = (RelativeLayout) findViewById(R.id.lay_colombia_09);
		Animation animMedellin = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_16);
		layMedellin.startAnimation(animMedellin);

		RelativeLayout layBucaramanga = (RelativeLayout) findViewById(R.id.lay_colombia_10);
		Animation animBucaramanda = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_17);
		layBucaramanga.startAnimation(animBucaramanda);

		RelativeLayout layQuibdo = (RelativeLayout) findViewById(R.id.lay_colombia_11);
		Animation animQuibdo = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_18);
		layQuibdo.startAnimation(animQuibdo);

		RelativeLayout layIbaque = (RelativeLayout) findViewById(R.id.lay_colombia_12);
		Animation animIbaque = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_19);
		layIbaque.startAnimation(animIbaque);

		RelativeLayout layYopal = (RelativeLayout) findViewById(R.id.lay_colombia_13);
		Animation animYopal = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_20);
		layYopal.startAnimation(animYopal);

		RelativeLayout layBogota = (RelativeLayout) findViewById(R.id.lay_colombia_14);
		Animation animBogota = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_21);
		layBogota.startAnimation(animBogota);

		RelativeLayout layPereira = (RelativeLayout) findViewById(R.id.lay_colombia_15);
		Animation animPereira = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_22);
		layPereira.startAnimation(animPereira);

		RelativeLayout layVillavicencio = (RelativeLayout) findViewById(R.id.lay_colombia_16);
		Animation animVillavicencio = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_23);
		layVillavicencio.startAnimation(animVillavicencio);

		RelativeLayout layCali = (RelativeLayout) findViewById(R.id.lay_colombia_17);
		Animation animCali = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_24);
		layCali.startAnimation(animCali);

		RelativeLayout layNeiva = (RelativeLayout) findViewById(R.id.lay_colombia_18);
		Animation animNeiva = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_25);
		layNeiva.startAnimation(animNeiva);

		RelativeLayout layPuertoAsis = (RelativeLayout) findViewById(R.id.lay_colombia_19);
		Animation animPuertoAsis = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_26);
		layPuertoAsis.startAnimation(animPuertoAsis);

		RelativeLayout layLeticia = (RelativeLayout) findViewById(R.id.lay_colombia_20);
		Animation animLeticia = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_27);
		animLeticia.setAnimationListener(new Animation.AnimationListener() {
			@Override
			public void onAnimationEnd(Animation animation) {
				imgMapaColombia.setVisibility(View.VISIBLE);
				imgMapaColombia.startAnimation(animacionesdos);
				findViewById(R.id.mapa_colombia_ant).startAnimation(animaciones);
				contenedorBotones.startAnimation(animaciones);
				findViewById(R.id.mapa_colombia_ant).setVisibility(View.GONE);
				contenedorBotones.setVisibility(View.GONE);
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
		layLeticia.startAnimation(animLeticia);
	}

	public void activeButton() {
		btnColombia.setBackgroundResource(R.drawable.botonrojo);
		btnColombia.setEnabled(false);
	}
	public void compAnimationBienvenida(int pais){
		animMapaInter = AnimationUtils.loadAnimation(this,R.anim.anim_mapa_inter_out);
		switch (pais) {
		case 1:
			animMapaInter.setAnimationListener(new Animation.AnimationListener() {
	            @Override
	            public void onAnimationEnd(Animation animation) {
	            	imgMapaColombia.setVisibility(View.GONE);
	            	Intent act = new Intent(LaminaColombiaActivity.this,LaminaArgentinaActivity.class);
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
	            	imgMapaColombia.setVisibility(View.GONE);
	            	Intent act = new Intent(LaminaColombiaActivity.this,LaminaBrasilActivity.class);
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
	            	imgMapaColombia.setVisibility(View.GONE);
	            	Intent act = new Intent(LaminaColombiaActivity.this,LaminaChileActivity.class);
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
	            	imgMapaColombia.setVisibility(View.GONE);
	            	Intent act = new Intent(LaminaColombiaActivity.this,LaminaColombiaActivity.class);
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
	            	imgMapaColombia.setVisibility(View.GONE);
	            	Intent act = new Intent(LaminaColombiaActivity.this,LaminaEcuadorActivity.class);
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
	            	imgMapaColombia.setVisibility(View.GONE);
	            	Intent act = new Intent(LaminaColombiaActivity.this,LaminaPeruActivity.class);
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
	            	imgMapaColombia.setVisibility(View.GONE);
	            	Intent act = new Intent(LaminaColombiaActivity.this,LaminaMundialActivity.class);
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

		findViewById(R.id.lay_apretable_colombia).setOnClickListener(
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
						Intent act = new Intent(LaminaColombiaActivity.this,LaminaDosActivity.class);
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
}
