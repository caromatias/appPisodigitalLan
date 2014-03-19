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
		cambioImagen();
		popUpInfo();
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
						// TODO Auto-generated method stub
						btnArgentina
								.setBackgroundResource(R.drawable.botonrojo);
						imgMapaColombia.startAnimation(animMapaInter);
						btnArgentina.setEnabled(false);
						final Handler handler = new Handler();
						handler.postDelayed(new Runnable() {
							@Override
							public void run() {
								Intent act = new Intent(
										LaminaColombiaActivity.this,
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
				imgMapaColombia.startAnimation(animMapaInter);
				btnBrasil.setEnabled(false);
				final Handler handler = new Handler();
				handler.postDelayed(new Runnable() {
					@Override
					public void run() {
						Intent act = new Intent(LaminaColombiaActivity.this,
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
				imgMapaColombia.startAnimation(animMapaInter);
				btnChile.setEnabled(false);
				final Handler handler = new Handler();
				handler.postDelayed(new Runnable() {
					@Override
					public void run() {
						Intent act = new Intent(LaminaColombiaActivity.this,
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
						imgMapaColombia.startAnimation(animMapaInter);
						btnColombia.setEnabled(false);
						final Handler handler = new Handler();
						handler.postDelayed(new Runnable() {
							@Override
							public void run() {
								Intent act = new Intent(
										LaminaColombiaActivity.this,
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
						imgMapaColombia.startAnimation(animMapaInter);
						btnEcuador.setEnabled(false);
						final Handler handler = new Handler();
						handler.postDelayed(new Runnable() {
							@Override
							public void run() {
								Intent act = new Intent(
										LaminaColombiaActivity.this,
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
				imgMapaColombia.startAnimation(animMapaInter);
				btnPeru.setEnabled(false);
				final Handler handler = new Handler();
				handler.postDelayed(new Runnable() {
					@Override
					public void run() {
						Intent act = new Intent(LaminaColombiaActivity.this,
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
						imgMapaColombia.startAnimation(animMapaInter);
						btnInter.setEnabled(false);
						final Handler handler = new Handler();
						handler.postDelayed(new Runnable() {
							@Override
							public void run() {
								Intent act = new Intent(
										LaminaColombiaActivity.this,
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
		imgMapaColombia = (ImageView) findViewById(R.id.mapa_colombia);
		animMapaColombia = AnimationUtils.loadAnimation(this,
				R.anim.anim_mapa_in);
		animMapaColombiaOut = AnimationUtils.loadAnimation(this,
				R.anim.anim_mapa_inter_out);
		btnGoGame = (Button) findViewById(R.id.btn_ir_al_juego);
		animGoGame = AnimationUtils.loadAnimation(this, R.anim.animacion);
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
	}

	public void animaCiudadesColombia() {
		RelativeLayout laySanAndres = (RelativeLayout) findViewById(R.id.lay_colombia_01);
		Animation animSanAndres = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_01);
		laySanAndres.startAnimation(animSanAndres);

		RelativeLayout laySantaMarta = (RelativeLayout) findViewById(R.id.lay_colombia_02);
		Animation animSantaMarta = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_02);
		laySantaMarta.startAnimation(animSantaMarta);

		RelativeLayout layBarranquilla = (RelativeLayout) findViewById(R.id.lay_colombia_03);
		Animation animBarranquilla = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_03);
		layBarranquilla.startAnimation(animBarranquilla);

		RelativeLayout layCartagena = (RelativeLayout) findViewById(R.id.lay_colombia_04);
		Animation animCartagena = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_04);
		layCartagena.startAnimation(animCartagena);

		RelativeLayout layValledupar = (RelativeLayout) findViewById(R.id.lay_colombia_05);
		Animation animValledupar = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_05);
		layValledupar.startAnimation(animValledupar);

		RelativeLayout layMonteria = (RelativeLayout) findViewById(R.id.lay_colombia_06);
		Animation animMonteria = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_06);
		layMonteria.startAnimation(animMonteria);

		RelativeLayout layApartado = (RelativeLayout) findViewById(R.id.lay_colombia_07);
		Animation animApartado = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_07);
		layApartado.startAnimation(animApartado);

		RelativeLayout layCucuta = (RelativeLayout) findViewById(R.id.lay_colombia_08);
		Animation animCucuta = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_08);
		layCucuta.startAnimation(animCucuta);

		RelativeLayout layMedellin = (RelativeLayout) findViewById(R.id.lay_colombia_09);
		Animation animMedellin = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_09);
		layMedellin.startAnimation(animMedellin);

		RelativeLayout layBucaramanga = (RelativeLayout) findViewById(R.id.lay_colombia_10);
		Animation animBucaramanda = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_10);
		layBucaramanga.startAnimation(animBucaramanda);

		RelativeLayout layQuibdo = (RelativeLayout) findViewById(R.id.lay_colombia_11);
		Animation animQuibdo = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_11);
		layQuibdo.startAnimation(animQuibdo);

		RelativeLayout layIbaque = (RelativeLayout) findViewById(R.id.lay_colombia_12);
		Animation animIbaque = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_12);
		layIbaque.startAnimation(animIbaque);

		RelativeLayout layYopal = (RelativeLayout) findViewById(R.id.lay_colombia_13);
		Animation animYopal = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_13);
		layYopal.startAnimation(animYopal);

		RelativeLayout layBogota = (RelativeLayout) findViewById(R.id.lay_colombia_14);
		Animation animBogota = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_14);
		layBogota.startAnimation(animBogota);

		RelativeLayout layPereira = (RelativeLayout) findViewById(R.id.lay_colombia_15);
		Animation animPereira = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_15);
		layPereira.startAnimation(animPereira);

		RelativeLayout layVillavicencio = (RelativeLayout) findViewById(R.id.lay_colombia_16);
		Animation animVillavicencio = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_16);
		layVillavicencio.startAnimation(animVillavicencio);

		RelativeLayout layCali = (RelativeLayout) findViewById(R.id.lay_colombia_17);
		Animation animCali = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_17);
		layCali.startAnimation(animCali);

		RelativeLayout layNeiva = (RelativeLayout) findViewById(R.id.lay_colombia_18);
		Animation animNeiva = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_18);
		layNeiva.startAnimation(animNeiva);

		RelativeLayout layPuertoAsis = (RelativeLayout) findViewById(R.id.lay_colombia_19);
		Animation animPuertoAsis = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_19);
		layPuertoAsis.startAnimation(animPuertoAsis);

		RelativeLayout layLeticia = (RelativeLayout) findViewById(R.id.lay_colombia_20);
		Animation animLeticia = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_20);
		layLeticia.startAnimation(animLeticia);

		ImageView layAnimColombia = (ImageView) findViewById(R.id.img_lineas_colombia);
		Animation animLineasColombia = AnimationUtils.loadAnimation(this,
				R.anim.anim_lineas_colombia);
		layAnimColombia.startAnimation(animLineasColombia);
	}

	public void activeButton() {
		btnColombia.setBackgroundResource(R.drawable.botonrojo);
		btnColombia.setEnabled(false);
	}

	@Override
	public void onBackPressed() {
		return;
	}
<<<<<<< HEAD

	public void cambioImagen() {
=======
	public void cambioImagen(){
>>>>>>> 6b8f3e2ce5ada84cba67bd5e0ade6fc3f6ecfd71
		final Handler handlerTres = new Handler();
		handlerTres.postDelayed(new Runnable() {
			@Override
			public void run() {
				// Do something after 5s = 5000ms
				// imgMapaArgentina.setVisibility(View.GONE);
				imgMapaColombia.setImageResource(R.drawable.mapa_colombia_dos);
				contenedorBotones.setVisibility(View.GONE);
				// imgArgentinaView.setVisibility(View.VISIBLE);
			}
		}, 6000);
	}
<<<<<<< HEAD

=======
>>>>>>> 6b8f3e2ce5ada84cba67bd5e0ade6fc3f6ecfd71
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

}
