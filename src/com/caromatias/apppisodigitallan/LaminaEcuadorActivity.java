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
		cambioImagen();
		popUpInfo();
		cambiaActivity();
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
						// TODO Auto-generated method stub
						btnArgentina
								.setBackgroundResource(R.drawable.botonrojo);
						imgMapaEcuador.startAnimation(animMapaInter);
						btnArgentina.setEnabled(false);
						final Handler handler = new Handler();
						handler.postDelayed(new Runnable() {
							@Override
							public void run() {
								Intent act = new Intent(
										LaminaEcuadorActivity.this,
										LaminaArgentinaActivity.class);
								startActivity(act);
								overridePendingTransition(R.anim.fade_in,
										R.anim.fade_out);
							}
						}, 1000);
					}
				});
		findViewById(R.id.btn_brasil).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				btnBrasil.setBackgroundResource(R.drawable.botonrojo);
				imgMapaEcuador.startAnimation(animMapaInter);
				btnBrasil.setEnabled(false);
				final Handler handler = new Handler();
				handler.postDelayed(new Runnable() {
					@Override
					public void run() {
						Intent act = new Intent(LaminaEcuadorActivity.this,
								LaminaBrasilActivity.class);
						startActivity(act);
						overridePendingTransition(R.anim.fade_in,
								R.anim.fade_out);
					}
				}, 1000);
			}
		});

		findViewById(R.id.btn_chile).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				btnChile.setBackgroundResource(R.drawable.botonrojo);
				imgMapaEcuador.startAnimation(animMapaInter);
				btnChile.setEnabled(false);
				final Handler handler = new Handler();
				handler.postDelayed(new Runnable() {
					@Override
					public void run() {
						Intent act = new Intent(LaminaEcuadorActivity.this,
								LaminaChileActivity.class);
						startActivity(act);
						overridePendingTransition(R.anim.fade_in,
								R.anim.fade_out);
					}
				}, 1000);
				btnChile.setEnabled(false);
			}
		});
		findViewById(R.id.btn_colombia).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						btnColombia.setBackgroundResource(R.drawable.botonrojo);
						imgMapaEcuador.startAnimation(animMapaInter);
						btnColombia.setEnabled(false);
						final Handler handler = new Handler();
						handler.postDelayed(new Runnable() {
							@Override
							public void run() {
								Intent act = new Intent(
										LaminaEcuadorActivity.this,
										LaminaColombiaActivity.class);
								startActivity(act);
								overridePendingTransition(R.anim.fade_in,
										R.anim.fade_out);
							}
						}, 1000);
						btnColombia.setEnabled(false);
					}
				});
		findViewById(R.id.btn_ecuador).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						btnEcuador.setBackgroundResource(R.drawable.botonrojo);
						imgMapaEcuador.startAnimation(animMapaInter);
						btnEcuador.setEnabled(false);
						final Handler handler = new Handler();
						handler.postDelayed(new Runnable() {
							@Override
							public void run() {
								Intent act = new Intent(
										LaminaEcuadorActivity.this,
										LaminaEcuadorActivity.class);
								startActivity(act);
								overridePendingTransition(R.anim.fade_in,
										R.anim.fade_out);
							}
						}, 1000);
						btnEcuador.setEnabled(false);
					}
				});
		findViewById(R.id.btn_peru).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				btnPeru.setBackgroundResource(R.drawable.botonrojo);
				imgMapaEcuador.startAnimation(animMapaInter);
				btnPeru.setEnabled(false);
				final Handler handler = new Handler();
				handler.postDelayed(new Runnable() {
					@Override
					public void run() {
						Intent act = new Intent(LaminaEcuadorActivity.this,
								LaminaPeruActivity.class);
						startActivity(act);
						overridePendingTransition(R.anim.fade_in,
								R.anim.fade_out);
					}
				}, 1000);
				btnPeru.setEnabled(false);
			}
		});
		findViewById(R.id.btn_internacional).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						btnInter.setBackgroundResource(R.drawable.botonrojo);
						imgMapaEcuador.startAnimation(animMapaInter);
						btnInter.setEnabled(false);
						final Handler handler = new Handler();
						handler.postDelayed(new Runnable() {
							@Override
							public void run() {
								Intent act = new Intent(
										LaminaEcuadorActivity.this,
										LaminaMundialActivity.class);
								startActivity(act);
								overridePendingTransition(R.anim.fade_in,
										R.anim.fade_out);
							}
						}, 1000);
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
	}

	public void animaCiudadesEcuador() {
		RelativeLayout layIslaGalapagos = (RelativeLayout) findViewById(R.id.lay_ecuador_01);
		Animation animIslaGalapagos = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_01);
		layIslaGalapagos.startAnimation(animIslaGalapagos);

		RelativeLayout layBaltro = (RelativeLayout) findViewById(R.id.lay_ecuador_02);
		Animation animBaltro = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_02);
		layBaltro.startAnimation(animBaltro);

		RelativeLayout laySanCristobal = (RelativeLayout) findViewById(R.id.lay_ecuador_03);
		Animation animSanCristobal = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_03);
		laySanCristobal.startAnimation(animSanCristobal);

		RelativeLayout layGuayaquil = (RelativeLayout) findViewById(R.id.lay_ecuador_04);
		Animation animGuayaquil = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_04);
		layGuayaquil.startAnimation(animGuayaquil);

		RelativeLayout layQuito = (RelativeLayout) findViewById(R.id.lay_ecuador_05);
		Animation animQuito = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_05);
		layQuito.startAnimation(animQuito);

		RelativeLayout layCuenca = (RelativeLayout) findViewById(R.id.lay_ecuador_06);
		Animation animCuenca = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_06);
		layCuenca.startAnimation(animCuenca);

		//ImageView layAnimEcuador = (ImageView) findViewById(R.id.img_lineas_ecuador);
		//Animation animLineasEcuador = AnimationUtils.loadAnimation(this,
		//		R.anim.anim_lineas_ecuador);
		//layAnimEcuador.startAnimation(animLineasEcuador);
	}

	public void activeButton() {
		btnEcuador.setBackgroundResource(R.drawable.botonrojo);
		btnEcuador.setEnabled(false);
	}

	@Override
	public void onBackPressed() {
		return;
	}

	public void cambioImagen() {
		final Handler handlerTres = new Handler();
		handlerTres.postDelayed(new Runnable() {
			@Override
			public void run() {
				// Do something after 5s = 5000ms
				// imgMapaArgentina.setVisibility(View.GONE);
				imgMapaEcuador.setImageResource(R.drawable.mapa_ecuador_dos);
				contenedorBotones.setVisibility(View.GONE);
				// imgArgentinaView.setVisibility(View.VISIBLE);
			}
		}, 4000);
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
}
