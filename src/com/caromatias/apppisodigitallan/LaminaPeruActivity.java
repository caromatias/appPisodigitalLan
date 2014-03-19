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

public class LaminaPeruActivity extends Activity {

	private Button btnGoGame;
	private Animation animGoGame;
	private ImageView imgMapaPeru;
	private Animation animMapaPeru;
	private Animation animMapaPeruOut;
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
		setContentView(R.layout.activity_lamina_peru);
		initVars();
		imgMapaPeru.startAnimation(animMapaPeru);
		btnGoGame.startAnimation(animGoGame);
		animaCiudadesPeru();
		initButtons();
		activeButton();
		cambioImagen();
		popUpInfo();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lamina_peru, menu);
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
						imgMapaPeru.startAnimation(animMapaInter);
						btnArgentina.setEnabled(false);
						final Handler handler = new Handler();
						handler.postDelayed(new Runnable() {
							@Override
							public void run() {
								Intent act = new Intent(
										LaminaPeruActivity.this,
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
				imgMapaPeru.startAnimation(animMapaInter);
				btnBrasil.setEnabled(false);
				final Handler handler = new Handler();
				handler.postDelayed(new Runnable() {
					@Override
					public void run() {
						Intent act = new Intent(LaminaPeruActivity.this,
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
				imgMapaPeru.startAnimation(animMapaInter);
				btnChile.setEnabled(false);
				final Handler handler = new Handler();
				handler.postDelayed(new Runnable() {
					@Override
					public void run() {
						Intent act = new Intent(LaminaPeruActivity.this,
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
						imgMapaPeru.startAnimation(animMapaInter);
						btnColombia.setEnabled(false);
						final Handler handler = new Handler();
						handler.postDelayed(new Runnable() {
							@Override
							public void run() {
								Intent act = new Intent(
										LaminaPeruActivity.this,
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
						imgMapaPeru.startAnimation(animMapaInter);
						btnEcuador.setEnabled(false);
						final Handler handler = new Handler();
						handler.postDelayed(new Runnable() {
							@Override
							public void run() {
								Intent act = new Intent(
										LaminaPeruActivity.this,
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
				imgMapaPeru.startAnimation(animMapaInter);
				btnPeru.setEnabled(false);
				final Handler handler = new Handler();
				handler.postDelayed(new Runnable() {
					@Override
					public void run() {
						Intent act = new Intent(LaminaPeruActivity.this,
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
						imgMapaPeru.startAnimation(animMapaInter);
						btnInter.setEnabled(false);
						final Handler handler = new Handler();
						handler.postDelayed(new Runnable() {
							@Override
							public void run() {
								Intent act = new Intent(
										LaminaPeruActivity.this,
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
		imgMapaPeru = (ImageView) findViewById(R.id.mapa_peru);
		animMapaPeru = AnimationUtils.loadAnimation(this, R.anim.anim_mapa_in);
		animMapaPeruOut = AnimationUtils.loadAnimation(this,
				R.anim.anim_mapa_inter_out);
		btnGoGame = (Button) findViewById(R.id.btn_ir_al_juego);
		animGoGame = AnimationUtils.loadAnimation(this, R.anim.animacion);
		btnInter = (Button) findViewById(R.id.btn_internacional);
		btnPeru = (Button) findViewById(R.id.btn_peru);
		btnBrasil = (Button) findViewById(R.id.btn_brasil);
		btnEcuador = (Button) findViewById(R.id.btn_ecuador);
		btnColombia = (Button) findViewById(R.id.btn_colombia);
		btnChile = (Button) findViewById(R.id.btn_chile);
		btnArgentina = (Button) findViewById(R.id.btn_argentina);
		contenedorBotones = (RelativeLayout) findViewById(R.id.lay_mapa_peru);
		layPopupInfo = (RelativeLayout) findViewById(R.id.lay_popup_info);
		tituloPopInfo = (TextView) findViewById(R.id.txt_titulo_popup_info);
		descPopInfo = (TextView) findViewById(R.id.txt_descripcion_popup_info);
		animMapaInter = AnimationUtils.loadAnimation(this,
				R.anim.anim_mapa_inter_out);
	}

	public void animaCiudadesPeru() {
		RelativeLayout layTumbes = (RelativeLayout) findViewById(R.id.lay_peru_01);
		Animation animTumbes = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_01);
		layTumbes.startAnimation(animTumbes);

		RelativeLayout layPiura = (RelativeLayout) findViewById(R.id.lay_peru_02);
		Animation animPiura = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_02);
		layPiura.startAnimation(animPiura);

		RelativeLayout layChiclayo = (RelativeLayout) findViewById(R.id.lay_peru_03);
		Animation animChiclayo = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_03);
		layChiclayo.startAnimation(animChiclayo);

		RelativeLayout layTrujillo = (RelativeLayout) findViewById(R.id.lay_peru_04);
		Animation animTrujillo = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_04);
		layTrujillo.startAnimation(animTrujillo);

		RelativeLayout layLima = (RelativeLayout) findViewById(R.id.lay_peru_05);
		Animation animLima = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_05);
		layLima.startAnimation(animLima);

		RelativeLayout layArequipa = (RelativeLayout) findViewById(R.id.lay_peru_06);
		Animation animArequipa = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_06);
		layArequipa.startAnimation(animArequipa);

		RelativeLayout layTacna = (RelativeLayout) findViewById(R.id.lay_peru_07);
		Animation animTacna = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_07);
		layTacna.startAnimation(animTacna);

		RelativeLayout layJulianca = (RelativeLayout) findViewById(R.id.lay_peru_08);
		Animation animJulianca = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_08);
		layJulianca.startAnimation(animJulianca);

		RelativeLayout layCusco = (RelativeLayout) findViewById(R.id.lay_peru_09);
		Animation animCusco = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_09);
		layCusco.startAnimation(animCusco);

		RelativeLayout layPuertoMaldonado = (RelativeLayout) findViewById(R.id.lay_peru_10);
		Animation animPuertoMaldonado = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_10);
		layPuertoMaldonado.startAnimation(animPuertoMaldonado);

		RelativeLayout layPucallpa = (RelativeLayout) findViewById(R.id.lay_peru_11);
		Animation animPucallpa = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_11);
		layPucallpa.startAnimation(animPucallpa);

		RelativeLayout layCajamarca = (RelativeLayout) findViewById(R.id.lay_peru_12);
		Animation animCajamarca = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_12);
		layCajamarca.startAnimation(animCajamarca);

		RelativeLayout layTaraporo = (RelativeLayout) findViewById(R.id.lay_peru_13);
		Animation animTaraporo = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_13);
		layTaraporo.startAnimation(animTaraporo);

		RelativeLayout layIquito = (RelativeLayout) findViewById(R.id.lay_peru_14);
		Animation animIquitos = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_14);
		layIquito.startAnimation(animIquitos);

		ImageView layAnimPeru = (ImageView) findViewById(R.id.img_lineas_peru);
		Animation animLineasPeru = AnimationUtils.loadAnimation(this,
				R.anim.anim_lineas_ecuador);
		layAnimPeru.startAnimation(animLineasPeru);
	}

	public void activeButton() {
		btnPeru.setBackgroundResource(R.drawable.botonrojo);
		btnPeru.setEnabled(false);
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
				imgMapaPeru.setImageResource(R.drawable.mapa_peru_dos);
				contenedorBotones.setVisibility(View.GONE);
				// imgArgentinaView.setVisibility(View.VISIBLE);
			}
		}, 7000);
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

		findViewById(R.id.lay_apretable_peru).setOnClickListener(
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
}
