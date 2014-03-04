package com.caromatias.apppisodigitallan;

import pl.polidea.view.ZoomView;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.VideoView;

public class LaminaUnoActivity extends Activity {

	private ZoomView zoomView;
	private int posicionMenu = 1;
	private VideoView videoLaminaDos;
	private RelativeLayout lay_principal;
	private Button btnInter;
	private Button btnPeru;
	private Button btnBrasil;
	private Button btnEcuador;
	private Button btnColombia;
	private Button btnChile;
	private Button btnArgentina;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lamina_uno);

		final View v = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE))
				.inflate(R.layout.zoomableview, null, false);
		v.setLayoutParams(new LinearLayout.LayoutParams(
				android.view.ViewGroup.LayoutParams.FILL_PARENT,
				android.view.ViewGroup.LayoutParams.FILL_PARENT));

		zoomView = new ZoomView(this);
		zoomView.addView(v);
		lay_principal = (RelativeLayout) findViewById(R.id.zona_zoomable);
		lay_principal.addView(zoomView);

		exMain();

		// //////// Cambio de imagen a video ////////////

		// ////////////////////////////////////////////

		eventosBotones();

		cambiaActivity();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lamina_uno, menu);
		return true;
	}

	// ///////////////// EVENTOS BOTONES DEL PANEL /////////////////////////

	public void eventosBotones() {

		btnInter = (Button) findViewById(R.id.btn_internacional);
		btnInter.setBackgroundResource(R.drawable.botonrojo);

		btnPeru = (Button) findViewById(R.id.btn_peru);
		btnBrasil = (Button) findViewById(R.id.btn_brasil);
		btnEcuador = (Button) findViewById(R.id.btn_ecuador);
		btnColombia = (Button) findViewById(R.id.btn_colombia);
		btnChile = (Button) findViewById(R.id.btn_chile);
		btnArgentina = (Button) findViewById(R.id.btn_argentina);

		final RelativeLayout layMapaInter = (RelativeLayout) findViewById(R.id.lay_mapa_inter);
		final Animation animMapaInter = AnimationUtils.loadAnimation(this,
				R.anim.anim_mapa_inter_out);

		final RelativeLayout layMapaChile = (RelativeLayout) findViewById(R.id.lay_mapa_chile);
		final Animation animMapaChile = AnimationUtils.loadAnimation(this,
				R.anim.anim_mapa_in);
		final Animation animMapaChileOut = AnimationUtils.loadAnimation(this,
				R.anim.anim_mapa_inter_out);

		final RelativeLayout layMapaArgentina = (RelativeLayout) findViewById(R.id.lay_argentina);
		final Animation animMapaArgentina = AnimationUtils.loadAnimation(this,
				R.anim.anim_mapa_in);
		final Animation animMapaArgentinaOut = AnimationUtils.loadAnimation(
				this, R.anim.anim_mapa_inter_out);

		final RelativeLayout layMapaPeru = (RelativeLayout) findViewById(R.id.lay_mapa_peru);
		final Animation animMapaPeru = AnimationUtils.loadAnimation(this,
				R.anim.anim_mapa_in);
		final Animation animMapaPeruOut = AnimationUtils.loadAnimation(this,
				R.anim.anim_mapa_inter_out);

		final RelativeLayout layMapaBrasil = (RelativeLayout) findViewById(R.id.lay_mapa_brasil);
		final Animation animMapaBrasil = AnimationUtils.loadAnimation(this,
				R.anim.anim_mapa_in);
		final Animation animMapaBrasilOut = AnimationUtils.loadAnimation(this,
				R.anim.anim_mapa_inter_out);

		final RelativeLayout layMapaEcuador = (RelativeLayout) findViewById(R.id.lay_mapa_ecuador);
		final Animation animMapaEcuador = AnimationUtils.loadAnimation(this,
				R.anim.anim_mapa_in);
		final Animation animMapaEcuadorOut = AnimationUtils.loadAnimation(this,
				R.anim.anim_mapa_inter_out);

		final RelativeLayout layMapaColombia = (RelativeLayout) findViewById(R.id.lay_mapa_colombia);
		final Animation animMapaColombia = AnimationUtils.loadAnimation(this,
				R.anim.anim_mapa_in);
		final Animation animMapaColombiaOut = AnimationUtils.loadAnimation(
				this, R.anim.anim_mapa_inter_out);

		// ///////////////////// CLICK BOTÓN INTERNACIONAL
		// ////////////////////////////
		findViewById(R.id.btn_internacional).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						btnInter.setBackgroundResource(R.drawable.botonrojo);

						switch (posicionMenu) {
						case 1:
							return;
						case 2:
							layMapaChile.startAnimation(animMapaChileOut);
							btnChile.setBackgroundResource(R.drawable.boton);
							btnChile.setEnabled(true);
							break;
						case 3:
							layMapaArgentina
									.startAnimation(animMapaArgentinaOut);
							btnArgentina
									.setBackgroundResource(R.drawable.boton);
							btnArgentina.setEnabled(true);
							break;
						case 4:
							layMapaPeru.startAnimation(animMapaPeruOut);
							btnPeru.setBackgroundResource(R.drawable.boton);
							btnPeru.setEnabled(true);
							break;
						case 5:
							layMapaBrasil.startAnimation(animMapaPeruOut);
							btnBrasil.setBackgroundResource(R.drawable.boton);
							btnBrasil.setEnabled(true);
							break;
						case 6:
							layMapaEcuador.startAnimation(animMapaEcuadorOut);
							btnEcuador.setBackgroundResource(R.drawable.boton);
							btnEcuador.setEnabled(true);
							break;
						case 7:
							layMapaColombia.startAnimation(animMapaColombiaOut);
							btnColombia.setBackgroundResource(R.drawable.boton);
							btnColombia.setEnabled(true);
							break;
						}

						final Handler handler = new Handler();
						handler.postDelayed(new Runnable() {
							@Override
							public void run() {

								switch (posicionMenu) {
								case 1:
									return;
								case 2:
									layMapaChile.setVisibility(View.GONE);
									layMapaInter.setVisibility(View.VISIBLE);
									layMapaInter.startAnimation(animMapaChile);
									break;
								case 3:
									layMapaArgentina.setVisibility(View.GONE);
									layMapaInter.setVisibility(View.VISIBLE);
									layMapaInter
											.startAnimation(animMapaArgentina);
									break;
								case 4:
									layMapaPeru.setVisibility(View.GONE);
									layMapaInter.setVisibility(View.VISIBLE);
									layMapaInter.startAnimation(animMapaPeru);
									break;
								case 5:
									layMapaBrasil.setVisibility(View.GONE);
									layMapaInter.setVisibility(View.VISIBLE);
									layMapaInter.startAnimation(animMapaBrasil);
									break;
								case 6:
									layMapaEcuador.setVisibility(View.GONE);
									layMapaInter.setVisibility(View.VISIBLE);
									layMapaInter
											.startAnimation(animMapaEcuador);
									break;
								case 7:
									layMapaColombia.setVisibility(View.GONE);
									layMapaInter.setVisibility(View.VISIBLE);
									layMapaInter
											.startAnimation(animMapaColombia);
									break;
								}
								// ///// Animaciones

								posicionMenu = 1;
							}
						}, 1000);
						btnInter.setEnabled(false);
					}
				});
		// //////////////////////////////////////////////////////////////////
		// ///////////////////// CLICK BOTÓN CHILE //////////////////////////
		findViewById(R.id.btn_chile).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				btnChile.setBackgroundResource(R.drawable.botonrojo);
				
				
				
				switch (posicionMenu) {
				case 1:
					layMapaInter.startAnimation(animMapaInter);
					btnInter.setBackgroundResource(R.drawable.boton);
					btnInter.setEnabled(true);
					break;
				case 2:
					return;
				case 3:
					layMapaArgentina.startAnimation(animMapaArgentinaOut);
					btnArgentina.setBackgroundResource(R.drawable.boton);
					btnArgentina.setEnabled(true);
					break;
				case 4:
					layMapaPeru.startAnimation(animMapaPeruOut);
					btnPeru.setBackgroundResource(R.drawable.boton);
					btnPeru.setEnabled(true);
					break;
				case 5:
					layMapaBrasil.startAnimation(animMapaBrasilOut);
					btnBrasil.setBackgroundResource(R.drawable.boton);
					btnBrasil.setEnabled(true);
					break;
				case 6:
					layMapaEcuador.startAnimation(animMapaEcuadorOut);
					btnEcuador.setBackgroundResource(R.drawable.boton);
					btnEcuador.setEnabled(true);
					break;
				case 7:
					layMapaColombia.startAnimation(animMapaColombiaOut);
					btnColombia.setBackgroundResource(R.drawable.boton);
					btnColombia.setEnabled(true);
					break;
				}

				final Handler handler = new Handler();
				handler.postDelayed(new Runnable() {
					@Override
					public void run() {

						switch (posicionMenu) {
						case 1:
							layMapaInter.setVisibility(View.GONE);
							layMapaChile.setVisibility(View.VISIBLE);
							layMapaChile.startAnimation(animMapaChile);
							break;
						case 2:
							return;
						case 3:
							layMapaArgentina.setVisibility(View.GONE);
							layMapaChile.setVisibility(View.VISIBLE);
							layMapaChile.startAnimation(animMapaChile);
							break;
						case 4:
							layMapaPeru.setVisibility(View.GONE);
							layMapaChile.setVisibility(View.VISIBLE);
							layMapaChile.startAnimation(animMapaChile);
							break;
						case 5:
							layMapaBrasil.setVisibility(View.GONE);
							layMapaChile.setVisibility(View.VISIBLE);
							layMapaChile.startAnimation(animMapaChile);
							break;
						case 6:
							layMapaEcuador.setVisibility(View.GONE);
							layMapaChile.setVisibility(View.VISIBLE);
							layMapaChile.startAnimation(animMapaChile);
							break;
						case 7:
							layMapaColombia.setVisibility(View.GONE);
							layMapaChile.setVisibility(View.VISIBLE);
							layMapaChile.startAnimation(animMapaChile);
							break;
						}
						// ///// Animaciones
						animaCiudadesChile();
						posicionMenu = 2;
					}
				}, 1000);
				btnChile.setEnabled(false);
			}
		});
		// //////////////////////////////////////////////////////////////////
		// ///////////////////// CLICK BOTÓN ARGENTINA //////////////////////
		findViewById(R.id.btn_argentina).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						btnArgentina
								.setBackgroundResource(R.drawable.botonrojo);

						switch (posicionMenu) {
						case 1:
							layMapaInter.startAnimation(animMapaInter);
							btnInter.setBackgroundResource(R.drawable.boton);
							btnInter.setEnabled(true);
							break;
						case 2:
							layMapaChile.startAnimation(animMapaChileOut);
							btnChile.setBackgroundResource(R.drawable.boton);
							btnChile.setEnabled(true);
							break;
						case 3:
							return;
						case 4:
							layMapaPeru.startAnimation(animMapaPeruOut);
							btnPeru.setBackgroundResource(R.drawable.boton);
							btnPeru.setEnabled(true);
							break;
						case 5:
							layMapaBrasil.startAnimation(animMapaBrasilOut);
							btnBrasil.setBackgroundResource(R.drawable.boton);
							btnBrasil.setEnabled(true);
							break;
						case 6:
							layMapaEcuador.startAnimation(animMapaEcuadorOut);
							btnEcuador.setBackgroundResource(R.drawable.boton);
							btnEcuador.setEnabled(true);
							break;
						case 7:
							layMapaColombia.startAnimation(animMapaColombiaOut);
							btnColombia.setBackgroundResource(R.drawable.boton);
							btnColombia.setEnabled(true);
							break;
						}

						final Handler handler = new Handler();
						handler.postDelayed(new Runnable() {
							@Override
							public void run() {

								switch (posicionMenu) {
								case 1:
									layMapaInter.setVisibility(View.GONE);
									layMapaArgentina
											.setVisibility(View.VISIBLE);
									layMapaArgentina
											.startAnimation(animMapaArgentina);
									break;
								case 2:
									layMapaChile.setVisibility(View.GONE);
									layMapaArgentina
											.setVisibility(View.VISIBLE);
									layMapaArgentina
											.startAnimation(animMapaArgentina);
									break;
								case 3:
									return;
								case 4:
									layMapaPeru.setVisibility(View.GONE);
									layMapaArgentina
											.setVisibility(View.VISIBLE);
									layMapaArgentina
											.startAnimation(animMapaArgentina);
									break;
								case 5:
									layMapaBrasil.setVisibility(View.GONE);
									layMapaArgentina
											.setVisibility(View.VISIBLE);
									layMapaArgentina
											.startAnimation(animMapaArgentina);
									break;
								case 6:
									layMapaEcuador.setVisibility(View.GONE);
									layMapaArgentina
											.setVisibility(View.VISIBLE);
									layMapaArgentina
											.startAnimation(animMapaArgentina);
									break;
								case 7:
									layMapaColombia.setVisibility(View.GONE);
									layMapaArgentina
											.setVisibility(View.VISIBLE);
									layMapaArgentina
											.startAnimation(animMapaArgentina);
									break;
								}
								// ///// Animaciones

								posicionMenu = 3;
							}
						}, 1000);
						btnArgentina.setEnabled(false);
					}
				});
		// //////////////////////////////////////////////////////////////////
		// ///////////////////// CLICK BOTÓN PERÚ ///////////////////////////
		findViewById(R.id.btn_peru).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				btnPeru.setBackgroundResource(R.drawable.botonrojo);

				switch (posicionMenu) {
				case 1:
					layMapaInter.startAnimation(animMapaInter);
					btnInter.setBackgroundResource(R.drawable.boton);
					btnInter.setEnabled(true);
					break;
				case 2:
					layMapaChile.startAnimation(animMapaChileOut);
					btnChile.setBackgroundResource(R.drawable.boton);
					btnChile.setEnabled(true);
					break;
				case 3:
					layMapaArgentina.startAnimation(animMapaArgentinaOut);
					btnArgentina.setBackgroundResource(R.drawable.boton);
					btnArgentina.setEnabled(true);
					break;
				case 4:
					return;
				case 5:
					layMapaBrasil.startAnimation(animMapaBrasilOut);
					btnBrasil.setBackgroundResource(R.drawable.boton);
					btnBrasil.setEnabled(true);
					break;
				case 6:
					layMapaEcuador.startAnimation(animMapaEcuadorOut);
					btnEcuador.setBackgroundResource(R.drawable.boton);
					btnEcuador.setEnabled(true);
					break;
				case 7:
					layMapaColombia.startAnimation(animMapaColombiaOut);
					btnColombia.setBackgroundResource(R.drawable.boton);
					btnColombia.setEnabled(true);
					break;
				}

				final Handler handler = new Handler();
				handler.postDelayed(new Runnable() {
					@Override
					public void run() {

						switch (posicionMenu) {
						case 1:
							layMapaInter.setVisibility(View.GONE);
							layMapaPeru.setVisibility(View.VISIBLE);
							layMapaPeru.startAnimation(animMapaPeru);
							break;
						case 2:
							layMapaChile.setVisibility(View.GONE);
							layMapaPeru.setVisibility(View.VISIBLE);
							layMapaPeru.startAnimation(animMapaPeru);
							break;
						case 3:
							layMapaArgentina.setVisibility(View.GONE);
							layMapaPeru.setVisibility(View.VISIBLE);
							layMapaPeru.startAnimation(animMapaPeru);
							break;
						case 4:
							return;
						case 5:
							layMapaBrasil.setVisibility(View.GONE);
							layMapaPeru.setVisibility(View.VISIBLE);
							layMapaPeru.startAnimation(animMapaPeru);
							break;
						case 6:
							layMapaEcuador.setVisibility(View.GONE);
							layMapaPeru.setVisibility(View.VISIBLE);
							layMapaPeru.startAnimation(animMapaPeru);
							break;
						case 7:
							layMapaColombia.setVisibility(View.GONE);
							layMapaPeru.setVisibility(View.VISIBLE);
							layMapaPeru.startAnimation(animMapaPeru);
							break;
						}
						// ///// Animaciones

						posicionMenu = 4;
					}
				}, 1000);
				btnPeru.setEnabled(false);
			}
		});
		// //////////////////////////////////////////////////////////////////////
		// ///////////////////// CLICK BOTÓN BRASIL ///////////////////////////
		findViewById(R.id.btn_brasil).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				btnBrasil.setBackgroundResource(R.drawable.botonrojo);

				switch (posicionMenu) {
				case 1:
					layMapaInter.startAnimation(animMapaInter);
					btnInter.setBackgroundResource(R.drawable.boton);
					btnInter.setEnabled(true);
					break;
				case 2:
					layMapaChile.startAnimation(animMapaChileOut);
					btnChile.setBackgroundResource(R.drawable.boton);
					btnChile.setEnabled(true);
					break;
				case 3:
					layMapaArgentina.startAnimation(animMapaArgentinaOut);
					btnArgentina.setBackgroundResource(R.drawable.boton);
					btnArgentina.setEnabled(true);
					break;
				case 4:
					layMapaPeru.startAnimation(animMapaPeruOut);
					btnPeru.setBackgroundResource(R.drawable.boton);
					btnPeru.setEnabled(true);
					break;
				case 5:
					return;
				case 6:
					layMapaEcuador.startAnimation(animMapaEcuadorOut);
					btnEcuador.setBackgroundResource(R.drawable.boton);
					btnEcuador.setEnabled(true);
					break;
				case 7:
					layMapaColombia.startAnimation(animMapaColombiaOut);
					btnColombia.setBackgroundResource(R.drawable.boton);
					btnColombia.setEnabled(true);
					break;
				}

				final Handler handler = new Handler();
				handler.postDelayed(new Runnable() {
					@Override
					public void run() {

						switch (posicionMenu) {
						case 1:
							layMapaInter.setVisibility(View.GONE);
							layMapaBrasil.setVisibility(View.VISIBLE);
							layMapaBrasil.startAnimation(animMapaBrasil);
							break;
						case 2:
							layMapaChile.setVisibility(View.GONE);
							layMapaBrasil.setVisibility(View.VISIBLE);
							layMapaBrasil.startAnimation(animMapaBrasil);
							break;
						case 3:
							layMapaArgentina.setVisibility(View.GONE);
							layMapaBrasil.setVisibility(View.VISIBLE);
							layMapaBrasil.startAnimation(animMapaBrasil);
							break;
						case 4:
							layMapaPeru.setVisibility(View.GONE);
							layMapaBrasil.setVisibility(View.VISIBLE);
							layMapaBrasil.startAnimation(animMapaBrasil);
							break;
						case 5:
							return;
						case 6:
							layMapaEcuador.setVisibility(View.GONE);
							layMapaBrasil.setVisibility(View.VISIBLE);
							layMapaBrasil.startAnimation(animMapaBrasil);
							break;
						case 7:
							layMapaColombia.setVisibility(View.GONE);
							layMapaBrasil.setVisibility(View.VISIBLE);
							layMapaBrasil.startAnimation(animMapaBrasil);
							break;
						}
						// ///// Animaciones

						posicionMenu = 5;
					}
				}, 1000);
				btnBrasil.setEnabled(false);
			}
		});
		// //////////////////////////////////////////////////////////////////////
		// ///////////////////// CLICK BOTÓN ECUADOR
		// ////////////////////////////
		findViewById(R.id.btn_ecuador).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						btnEcuador.setBackgroundResource(R.drawable.botonrojo);

						switch (posicionMenu) {
						case 1:
							layMapaInter.startAnimation(animMapaInter);
							btnInter.setBackgroundResource(R.drawable.boton);
							btnInter.setEnabled(true);
							break;
						case 2:
							layMapaChile.startAnimation(animMapaChileOut);
							btnChile.setBackgroundResource(R.drawable.boton);
							btnChile.setEnabled(true);
							break;
						case 3:
							layMapaArgentina
									.startAnimation(animMapaArgentinaOut);
							btnArgentina
									.setBackgroundResource(R.drawable.boton);
							btnArgentina.setEnabled(true);
							break;
						case 4:
							layMapaPeru.startAnimation(animMapaPeruOut);
							btnPeru.setBackgroundResource(R.drawable.boton);
							btnPeru.setEnabled(true);
							break;
						case 5:
							layMapaBrasil.startAnimation(animMapaBrasilOut);
							btnBrasil.setBackgroundResource(R.drawable.boton);
							btnBrasil.setEnabled(true);
							break;
						case 6:
							return;
						case 7:
							layMapaColombia.startAnimation(animMapaColombiaOut);
							btnColombia.setBackgroundResource(R.drawable.boton);
							btnColombia.setEnabled(true);
							break;
						}

						final Handler handler = new Handler();
						handler.postDelayed(new Runnable() {
							@Override
							public void run() {

								switch (posicionMenu) {
								case 1:
									layMapaInter.setVisibility(View.GONE);
									layMapaEcuador.setVisibility(View.VISIBLE);
									layMapaEcuador
											.startAnimation(animMapaEcuador);
									break;
								case 2:
									layMapaChile.setVisibility(View.GONE);
									layMapaEcuador.setVisibility(View.VISIBLE);
									layMapaEcuador
											.startAnimation(animMapaEcuador);
									break;
								case 3:
									layMapaArgentina.setVisibility(View.GONE);
									layMapaEcuador.setVisibility(View.VISIBLE);
									layMapaEcuador
											.startAnimation(animMapaEcuador);
									break;
								case 4:
									layMapaPeru.setVisibility(View.GONE);
									layMapaEcuador.setVisibility(View.VISIBLE);
									layMapaEcuador
											.startAnimation(animMapaEcuador);
									break;
								case 5:
									layMapaBrasil.setVisibility(View.GONE);
									layMapaEcuador.setVisibility(View.VISIBLE);
									layMapaEcuador
											.startAnimation(animMapaEcuador);
									break;
								case 6:
									return;
								case 7:
									layMapaColombia.setVisibility(View.GONE);
									layMapaEcuador.setVisibility(View.VISIBLE);
									layMapaEcuador
											.startAnimation(animMapaEcuador);
									break;
								}
								// ///// Animaciones

								posicionMenu = 6;
							}
						}, 1000);
						btnEcuador.setEnabled(false);
					}
				});
		// //////////////////////////////////////////////////////////////////////
		// ///////////////////// CLICK BOTÓN COLOMBIA
		// ///////////////////////////
		findViewById(R.id.btn_colombia).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						btnColombia.setBackgroundResource(R.drawable.botonrojo);

						switch (posicionMenu) {
						case 1:
							layMapaInter.startAnimation(animMapaInter);
							btnInter.setBackgroundResource(R.drawable.boton);
							btnInter.setEnabled(true);
							break;
						case 2:
							layMapaChile.startAnimation(animMapaChileOut);
							btnChile.setBackgroundResource(R.drawable.boton);
							btnChile.setEnabled(true);
							break;
						case 3:
							layMapaArgentina
									.startAnimation(animMapaArgentinaOut);
							btnArgentina
									.setBackgroundResource(R.drawable.boton);
							btnArgentina.setEnabled(true);
							break;
						case 4:
							layMapaPeru.startAnimation(animMapaPeruOut);
							btnPeru.setBackgroundResource(R.drawable.boton);
							btnPeru.setEnabled(true);
							break;
						case 5:
							layMapaBrasil.startAnimation(animMapaBrasilOut);
							btnBrasil.setBackgroundResource(R.drawable.boton);
							btnBrasil.setEnabled(true);
							break;
						case 6:
							layMapaEcuador.startAnimation(animMapaEcuadorOut);
							btnEcuador.setBackgroundResource(R.drawable.boton);
							btnEcuador.setEnabled(true);
							break;
						case 7:
							return;
						}

						final Handler handler = new Handler();
						handler.postDelayed(new Runnable() {
							@Override
							public void run() {

								switch (posicionMenu) {
								case 1:
									layMapaInter.setVisibility(View.GONE);
									layMapaColombia.setVisibility(View.VISIBLE);
									layMapaColombia
											.startAnimation(animMapaColombia);
									break;
								case 2:
									layMapaChile.setVisibility(View.GONE);
									layMapaColombia.setVisibility(View.VISIBLE);
									layMapaColombia
											.startAnimation(animMapaColombia);
									break;
								case 3:
									layMapaArgentina.setVisibility(View.GONE);
									layMapaColombia.setVisibility(View.VISIBLE);
									layMapaColombia
											.startAnimation(animMapaColombia);
									break;
								case 4:
									layMapaPeru.setVisibility(View.GONE);
									layMapaColombia.setVisibility(View.VISIBLE);
									layMapaColombia
											.startAnimation(animMapaColombia);
									break;
								case 5:
									layMapaBrasil.setVisibility(View.GONE);
									layMapaColombia.setVisibility(View.VISIBLE);
									layMapaColombia
											.startAnimation(animMapaColombia);
									break;
								case 6:
									layMapaEcuador.setVisibility(View.GONE);
									layMapaColombia.setVisibility(View.VISIBLE);
									layMapaColombia
											.startAnimation(animMapaColombia);
									break;
								case 7:
									return;
								}
								// ///// Animaciones

								posicionMenu = 7;
							}
						}, 1000);
						btnColombia.setEnabled(false);
					}
				});
		// //////////////////////////////////////////////////////////////////////
	}

	public void animaCiudadesChile() {
		RelativeLayout layArica = (RelativeLayout) findViewById(R.id.lay_chile_01);
		Animation animArica = AnimationUtils.loadAnimation(this,
				R.anim.anim_chile_arica);
		layArica.startAnimation(animArica);

		RelativeLayout layIquique = (RelativeLayout) findViewById(R.id.lay_chile_02);
		Animation animIquique = AnimationUtils.loadAnimation(this,
				R.anim.anim_chile_iquique);
		layIquique.startAnimation(animIquique);

		RelativeLayout layCalama = (RelativeLayout) findViewById(R.id.lay_chile_14);
		Animation animCalama = AnimationUtils.loadAnimation(this,
				R.anim.anim_chile_calama);
		layCalama.startAnimation(animCalama);

		RelativeLayout layAntofagasta = (RelativeLayout) findViewById(R.id.lay_chile_03);
		Animation animAntofagasta = AnimationUtils.loadAnimation(this,
				R.anim.anim_chile_antofagasta);
		layAntofagasta.startAnimation(animAntofagasta);

		RelativeLayout layCopiapo = (RelativeLayout) findViewById(R.id.lay_chile_04);
		Animation animCopiapo = AnimationUtils.loadAnimation(this,
				R.anim.anim_chile_copiapo);
		layCopiapo.startAnimation(animCopiapo);

		RelativeLayout laySerena = (RelativeLayout) findViewById(R.id.lay_chile_05);
		Animation animSerena = AnimationUtils.loadAnimation(this,
				R.anim.anim_chile_serena);
		laySerena.startAnimation(animSerena);

		RelativeLayout laySantiago = (RelativeLayout) findViewById(R.id.lay_chile_06);
		Animation animSantiago = AnimationUtils.loadAnimation(this,
				R.anim.anim_chile_santiago);
		laySantiago.startAnimation(animSantiago);

		RelativeLayout layPascua = (RelativeLayout) findViewById(R.id.lay_chile_16);
		Animation animPascua = AnimationUtils.loadAnimation(this,
				R.anim.anim_chile_pascua);
		layPascua.startAnimation(animPascua);

		RelativeLayout layConcepcion = (RelativeLayout) findViewById(R.id.lay_chile_07);
		Animation animConcepcion = AnimationUtils.loadAnimation(this,
				R.anim.anim_chile_concepcion);
		layConcepcion.startAnimation(animConcepcion);

		RelativeLayout layTemuco = (RelativeLayout) findViewById(R.id.lay_chile_08);
		Animation animTemuco = AnimationUtils.loadAnimation(this,
				R.anim.anim_chile_temuco);
		layTemuco.startAnimation(animTemuco);

		RelativeLayout layValdivia = (RelativeLayout) findViewById(R.id.lay_chile_09);
		Animation animValdivia = AnimationUtils.loadAnimation(this,
				R.anim.anim_chile_valdivia);
		layValdivia.startAnimation(animValdivia);

		RelativeLayout layOsorno = (RelativeLayout) findViewById(R.id.lay_chile_10);
		Animation animOsorno = AnimationUtils.loadAnimation(this,
				R.anim.anim_chile_osorno);
		layOsorno.startAnimation(animOsorno);

		RelativeLayout layMontt = (RelativeLayout) findViewById(R.id.lay_chile_11);
		Animation animMontt = AnimationUtils.loadAnimation(this,
				R.anim.anim_chile_montt);
		layMontt.startAnimation(animMontt);

		RelativeLayout layChiloe = (RelativeLayout) findViewById(R.id.lay_chile_12);
		Animation animChileo = AnimationUtils.loadAnimation(this,
				R.anim.anim_chile_chiloe);
		layChiloe.startAnimation(animChileo);

		RelativeLayout layBalmaceda = (RelativeLayout) findViewById(R.id.lay_chile_15);
		Animation animBalmaceda = AnimationUtils.loadAnimation(this,
				R.anim.anim_chile_balmaceda);
		layBalmaceda.startAnimation(animBalmaceda);

		RelativeLayout layArenas = (RelativeLayout) findViewById(R.id.lay_chile_13);
		Animation animArenas = AnimationUtils.loadAnimation(this,
				R.anim.anim_chile_parenas);
		layArenas.startAnimation(animArenas);
	}
	
	public void animaCiudadesArgentina(){
		RelativeLayout laySalta = (RelativeLayout) findViewById(R.id.lay_argentina_01);
		Animation animSalta =  AnimationUtils.loadAnimation(this, R.anim.amin_algo);
		laySalta.startAnimation(animSalta);
		
		RelativeLayout layTucuman = (RelativeLayout) findViewById(R.id.lay_argentina_02);
		Animation animTucuman =  AnimationUtils.loadAnimation(this, R.anim.amin_algo);
		layTucuman.startAnimation(animTucuman);
		
		RelativeLayout layIguazu = (RelativeLayout) findViewById(R.id.lay_argentina_03);
		Animation animIguazu =  AnimationUtils.loadAnimation(this, R.anim.anim_argentina_iguazu);
		layIguazu.startAnimation(animIguazu);
		
		RelativeLayout layCordoba = (RelativeLayout) findViewById(R.id.lay_argentina_04);
		Animation animCordoba =  AnimationUtils.loadAnimation(this, R.anim.anim_argentina_cordoba);
		layCordoba.startAnimation(animCordoba);
		
		RelativeLayout laySanJuan = (RelativeLayout) findViewById(R.id.lay_argentina_05);
		Animation animSanJuan =  AnimationUtils.loadAnimation(this, R.anim.anim_argentina_san_juan);
		laySanJuan.startAnimation(animSanJuan);
		
		RelativeLayout layMendoza = (RelativeLayout) findViewById(R.id.lay_argentina_06);
		Animation animMendoza =  AnimationUtils.loadAnimation(this, R.anim.anim_argentina_mendoza);
		layMendoza.startAnimation(animMendoza);
		
		RelativeLayout layBuenosAires = (RelativeLayout) findViewById(R.id.lay_argentina_07);
		Animation animBuenosAires =  AnimationUtils.loadAnimation(this, R.anim.anim_argentina_buenos_aires);
		layBuenosAires.startAnimation(animBuenosAires);
		
		RelativeLayout layNeuquen = (RelativeLayout) findViewById(R.id.lay_argentina_08);
		Animation animNeuquen =  AnimationUtils.loadAnimation(this, R.anim.anim_argentina_neuquen);
		layNeuquen.startAnimation(animNeuquen);
		
		RelativeLayout layBahiaBlabca = (RelativeLayout) findViewById(R.id.lay_argentina_10);
		Animation animBahiaBlanca =  AnimationUtils.loadAnimation(this, R.anim.anim_argentina_bahia_blanca);
		layBahiaBlabca.startAnimation(animBahiaBlanca);
		
		RelativeLayout layBariloche = (RelativeLayout) findViewById(R.id.lay_argentina_11);
		Animation animBariloche =  AnimationUtils.loadAnimation(this, R.anim.anim_argentina_bariloche);
		layBariloche.startAnimation(animBariloche);
		
		RelativeLayout layComodoro = (RelativeLayout) findViewById(R.id.lay_argentina_12);
		Animation animComodoro =  AnimationUtils.loadAnimation(this, R.anim.anim_argentina_comodoro_rivadavia);
		layComodoro.startAnimation(animComodoro);
		
		RelativeLayout layCalafate = (RelativeLayout) findViewById(R.id.lay_argentina_13);
		Animation animCalafate =  AnimationUtils.loadAnimation(this, R.anim.anim_argentina_comodoro_el_calafate);
		layCalafate.startAnimation(animCalafate);
		
		RelativeLayout layRioGallegos = (RelativeLayout) findViewById(R.id.lay_argentina_14);
		Animation animRioGallegos =  AnimationUtils.loadAnimation(this, R.anim.anim_argentina_comodoro_rio_gallegos);
		layRioGallegos.startAnimation(animRioGallegos);
		
		RelativeLayout layUshuai = (RelativeLayout) findViewById(R.id.lay_argentina_15);
		Animation animUshuai =  AnimationUtils.loadAnimation(this, R.anim.anim_argentina_comodoro_ushuai);
		layUshuai.startAnimation(animUshuai);
	}
	
	// ////////////////////////////////////////////////////////////////
	
	public void animaCiudadesBrasil(){
		RelativeLayout layBuenaVista = (RelativeLayout) findViewById(R.id.lay_brasil_01);
		Animation animBuenaVista =  AnimationUtils.loadAnimation(this, R.anim.anim_argentina_buena_vista);
		layBuenaVista.startAnimation(animBuenaVista);
		
		RelativeLayout layMacapa = (RelativeLayout) findViewById(R.id.lay_brasil_02);
		Animation animMacapa =  AnimationUtils.loadAnimation(this, R.anim.anim_argentina_macapa);
		layMacapa.startAnimation(animMacapa);
		
		RelativeLayout layBelem = (RelativeLayout) findViewById(R.id.lay_brasil_03);
		Animation animBelem =  AnimationUtils.loadAnimation(this, R.anim.anim_argentina_belem);
		layBelem.startAnimation(animBelem);
		
		RelativeLayout laySao = (RelativeLayout) findViewById(R.id.lay_brasil_04);
		Animation animSao =  AnimationUtils.loadAnimation(this, R.anim.anim_argentina_sao_luis);
		laySao.startAnimation(animSao);
		
		RelativeLayout layManaus = (RelativeLayout) findViewById(R.id.lay_brasil_05);
		Animation animManaus =  AnimationUtils.loadAnimation(this, R.anim.anim_argentina_manaus);
		layManaus.startAnimation(animManaus);
		
		RelativeLayout laySantarem = (RelativeLayout) findViewById(R.id.lay_brasil_06);
		Animation animSantarem =  AnimationUtils.loadAnimation(this, R.anim.anim_argentina_santarem);
		laySantarem.startAnimation(animSantarem);
		
		RelativeLayout layMaramba = (RelativeLayout) findViewById(R.id.lay_brasil_07);
		Animation animMaramba =  AnimationUtils.loadAnimation(this, R.anim.anim_argentina_maramba);
		layMaramba.startAnimation(animMaramba);
		
		RelativeLayout layImperatriz = (RelativeLayout) findViewById(R.id.lay_brasil_08);
		Animation animImperatriz =  AnimationUtils.loadAnimation(this, R.anim.anim_argentina_imperatriz);
		layImperatriz.startAnimation(animImperatriz);
		
		RelativeLayout layTeresina = (RelativeLayout) findViewById(R.id.lay_brasil_09);
		Animation animTeresina =  AnimationUtils.loadAnimation(this, R.anim.anim_argentina_teresina);
		layTeresina.startAnimation(animTeresina);
		
		RelativeLayout layNatal = (RelativeLayout) findViewById(R.id.lay_brasil_10);
		Animation animNatal =  AnimationUtils.loadAnimation(this, R.anim.anim_argentina_natal);
		layNatal.startAnimation(animNatal);
		
		RelativeLayout layJoao = (RelativeLayout) findViewById(R.id.lay_brasil_11);
		Animation animJoao =  AnimationUtils.loadAnimation(this, R.anim.anim_argentina_joao);
		layJoao.startAnimation(animJoao);
		
		RelativeLayout layRecife = (RelativeLayout) findViewById(R.id.lay_brasil_12);
		Animation animRecife =  AnimationUtils.loadAnimation(this, R.anim.anim_argentina_recife);
		layRecife.startAnimation(animRecife);
		
		RelativeLayout layMaceio = (RelativeLayout) findViewById(R.id.lay_brasil_13);
		Animation animMaceio =  AnimationUtils.loadAnimation(this, R.anim.anim_argentina_maceio);
		layMaceio.startAnimation(animMaceio);
		
		RelativeLayout layRioBranco = (RelativeLayout) findViewById(R.id.lay_brasil_14);
		Animation animRioBranco =  AnimationUtils.loadAnimation(this, R.anim.anim_argentina_rio_branco);
		layRioBranco.startAnimation(animRioBranco);	
		
		RelativeLayout layPortoVelho = (RelativeLayout) findViewById(R.id.lay_brasil_15);
		Animation animPortoVelho =  AnimationUtils.loadAnimation(this, R.anim.anim_argentina_porto_velho);
		layPortoVelho.startAnimation(animPortoVelho);
		
		RelativeLayout layPalmas = (RelativeLayout) findViewById(R.id.lay_brasil_16);
		Animation animPalmas =  AnimationUtils.loadAnimation(this, R.anim.anim_argentina_palmas);
		layPalmas.startAnimation(animPalmas);
		
		RelativeLayout layAracaju = (RelativeLayout) findViewById(R.id.lay_brasil_17);
		Animation animAracaju =  AnimationUtils.loadAnimation(this, R.anim.anim_argentina_aracaju);
		layAracaju.startAnimation(animAracaju);
		
		RelativeLayout laySalvador = (RelativeLayout) findViewById(R.id.lay_brasil_18);
		Animation animSalvador =  AnimationUtils.loadAnimation(this, R.anim.anim_argentina_salvador);
		laySalvador.startAnimation(animSalvador);
		
		RelativeLayout layIleus = (RelativeLayout) findViewById(R.id.lay_brasil_19);
		Animation animIleus =  AnimationUtils.loadAnimation(this, R.anim.anim_argentina_ileus);
		layIleus.startAnimation(animIleus);
		
		RelativeLayout layCuiaba = (RelativeLayout) findViewById(R.id.lay_brasil_20);
		Animation animCuiaba =  AnimationUtils.loadAnimation(this, R.anim.anim_argentina_cuiaba);
		layCuiaba.startAnimation(animCuiaba);
		
		RelativeLayout layBrasilia = (RelativeLayout) findViewById(R.id.lay_brasil_21);
		Animation animBrasilia =  AnimationUtils.loadAnimation(this, R.anim.anim_argentina_brasilia);
		layBrasilia.startAnimation(animBrasilia);
		
		RelativeLayout layPortoSeguro = (RelativeLayout) findViewById(R.id.lay_brasil_22);
		Animation animPortoSeguro =  AnimationUtils.loadAnimation(this, R.anim.anim_argentina_porto_seguro);
		layPortoSeguro.startAnimation(animPortoSeguro);
		
		RelativeLayout layGoiania = (RelativeLayout) findViewById(R.id.lay_brasil_23);
		Animation animGoiania =  AnimationUtils.loadAnimation(this, R.anim.anim_argentina_goiania);
		layGoiania.startAnimation(animGoiania);
		
		RelativeLayout layUberlandia = (RelativeLayout) findViewById(R.id.lay_brasil_24);
		Animation animUberlandia =  AnimationUtils.loadAnimation(this, R.anim.anim_argentina_uberlandia);
		layUberlandia.startAnimation(animUberlandia);	
		
		RelativeLayout layBeloHorizonte = (RelativeLayout) findViewById(R.id.lay_brasil_25);
		Animation animBeloHorizonte =  AnimationUtils.loadAnimation(this, R.anim.anim_argentina_belo_horizonte);
		layBeloHorizonte.startAnimation(animBeloHorizonte);
		
		RelativeLayout layCampoGrande = (RelativeLayout) findViewById(R.id.lay_brasil_26);
		Animation animCampoGrande =  AnimationUtils.loadAnimation(this, R.anim.anim_argentina_campo_grande);
		layCampoGrande.startAnimation(animCampoGrande);
		
		RelativeLayout layRioPreto = (RelativeLayout) findViewById(R.id.lay_brasil_27);
		Animation animRioPreto =  AnimationUtils.loadAnimation(this, R.anim.anim_argentina_rio_preto);
		layRioPreto.startAnimation(animRioPreto);
		
		RelativeLayout layRiberaoPreto = (RelativeLayout) findViewById(R.id.lay_brasil_28);
		Animation animRiberaoPreto =  AnimationUtils.loadAnimation(this, R.anim.anim_argentina_riberao_preto);
		layRiberaoPreto.startAnimation(animRiberaoPreto);
		
		RelativeLayout layVictoria = (RelativeLayout) findViewById(R.id.lay_brasil_29);
		Animation animVictoria =  AnimationUtils.loadAnimation(this, R.anim.anim_argentina_victoria);
		layVictoria.startAnimation(animVictoria);
		
		RelativeLayout layCampinas = (RelativeLayout) findViewById(R.id.lay_brasil_30);
		Animation animCampinas =  AnimationUtils.loadAnimation(this, R.anim.anim_argentina_campinas);
		layCampinas.startAnimation(animCampinas);
		
		RelativeLayout layRioDeJaneiro = (RelativeLayout) findViewById(R.id.lay_brasil_31);
		Animation animRioDeJaniro =  AnimationUtils.loadAnimation(this, R.anim.anim_argentina_rio_de_janeiro);
		layRioDeJaneiro.startAnimation(animRioDeJaniro);
		
		RelativeLayout layLondrina = (RelativeLayout) findViewById(R.id.lay_brasil_32);
		Animation animLondrina =  AnimationUtils.loadAnimation(this, R.anim.anim_argentina_londrina);
		layLondrina.startAnimation(animLondrina);
		
		RelativeLayout laySaoPaulo = (RelativeLayout) findViewById(R.id.lay_brasil_33);
		Animation animSaoPaulo =  AnimationUtils.loadAnimation(this, R.anim.anim_argentina_sao_paulo);
		laySaoPaulo.startAnimation(animSaoPaulo);
		
		RelativeLayout layCuritiva = (RelativeLayout) findViewById(R.id.lay_brasil_34);
		Animation animCuritiva =  AnimationUtils.loadAnimation(this, R.anim.anim_argentina_curitiva);
		layCuritiva.startAnimation(animCuritiva);
		
		RelativeLayout layFoz = (RelativeLayout) findViewById(R.id.lay_brasil_35);
		Animation animFoz =  AnimationUtils.loadAnimation(this, R.anim.anim_argentina_foz);
		layFoz.startAnimation(animFoz);
		
		RelativeLayout layJoinville = (RelativeLayout) findViewById(R.id.lay_brasil_36);
		Animation animJoinville =  AnimationUtils.loadAnimation(this, R.anim.anim_argentina_joinville);
		layJoinville.startAnimation(animJoinville);
		
		RelativeLayout layNavegantes = (RelativeLayout) findViewById(R.id.lay_brasil_37);
		Animation animNavegantes =  AnimationUtils.loadAnimation(this, R.anim.anim_argentina_navegantes);
		layNavegantes.startAnimation(animNavegantes);
		
		RelativeLayout layFlorianopolis = (RelativeLayout) findViewById(R.id.lay_brasil_38);
		Animation animFlorianopolis =  AnimationUtils.loadAnimation(this, R.anim.anim_argentina_florianopolis);
		layFlorianopolis.startAnimation(animFlorianopolis);
		
		RelativeLayout layPortoAlegre = (RelativeLayout) findViewById(R.id.lay_brasil_39);
		Animation animPortoAlegre =  AnimationUtils.loadAnimation(this, R.anim.anim_argentina_porto_alegre);
		layPortoAlegre.startAnimation(animPortoAlegre);
	}

	// ////////////////////////////////////////////////////////////////
	
	public void animaCiudadesColombia(){
		RelativeLayout laySanAndres = (RelativeLayout) findViewById(R.id.lay_colombia_01);
		Animation animSanAndres = AnimationUtils.loadAnimation(this, R.anim.anim_colombia_san_andres);
		laySanAndres.startAnimation(animSanAndres);
		
		RelativeLayout laySantaMarta = (RelativeLayout) findViewById(R.id.lay_colombia_02);
		Animation animSantaMarta = AnimationUtils.loadAnimation(this, R.anim.anim_colombia_santa_marta);
		laySantaMarta.startAnimation(animSantaMarta);
		
		RelativeLayout layBarranquilla = (RelativeLayout) findViewById(R.id.lay_colombia_03);
		Animation animBarranquilla = AnimationUtils.loadAnimation(this, R.anim.anim_colombia_barranquilla);
		layBarranquilla.startAnimation(animBarranquilla);
		
		RelativeLayout layCartagena = (RelativeLayout) findViewById(R.id.lay_colombia_04);
		Animation animCartagena = AnimationUtils.loadAnimation(this, R.anim.anim_colombia_cartagena);
		layCartagena.startAnimation(animCartagena);
		
		RelativeLayout layValledupar = (RelativeLayout) findViewById(R.id.lay_colombia_05);
		Animation animValledupar = AnimationUtils.loadAnimation(this, R.anim.anim_colombia_valledupar);
		layValledupar.startAnimation(animValledupar);
		
		RelativeLayout layMonteria = (RelativeLayout) findViewById(R.id.lay_colombia_06);
		Animation animMonteria = AnimationUtils.loadAnimation(this, R.anim.anim_colombia_monteria);
		layMonteria.startAnimation(animMonteria);
		
		RelativeLayout layApartado = (RelativeLayout) findViewById(R.id.lay_colombia_07);
		Animation animApartado = AnimationUtils.loadAnimation(this, R.anim.anim_colombia_apartado);
		layApartado.startAnimation(animApartado);
		
		RelativeLayout layCucuta = (RelativeLayout) findViewById(R.id.lay_colombia_08);
		Animation animCucuta = AnimationUtils.loadAnimation(this, R.anim.anim_colombia_cucuta);
		layCucuta.startAnimation(animCucuta);
		
		RelativeLayout layMedellin = (RelativeLayout) findViewById(R.id.lay_colombia_09);
		Animation animMedellin = AnimationUtils.loadAnimation(this, R.anim.anim_colombia_medellin);
		layMedellin.startAnimation(animMedellin);
		
		RelativeLayout layBucaramanga = (RelativeLayout) findViewById(R.id.lay_colombia_10);
		Animation animBucaramanda = AnimationUtils.loadAnimation(this, R.anim.anim_colombia_bucaramanga);
		layBucaramanga.startAnimation(animBucaramanda);
		
		RelativeLayout layQuibdo = (RelativeLayout) findViewById(R.id.lay_colombia_11);
		Animation animQuibdo = AnimationUtils.loadAnimation(this, R.anim.anim_colombia_quibdo);
		layQuibdo.startAnimation(animQuibdo);
		
		RelativeLayout layIbaque = (RelativeLayout) findViewById(R.id.lay_colombia_12);
		Animation animIbaque = AnimationUtils.loadAnimation(this, R.anim.anim_colombia_ibaque);
		layIbaque.startAnimation(animIbaque);
		
		RelativeLayout layYopal = (RelativeLayout) findViewById(R.id.lay_colombia_13);
		Animation animYopal = AnimationUtils.loadAnimation(this, R.anim.anim_colombia_yopal);
		layYopal.startAnimation(animYopal);
		
		RelativeLayout layBogota = (RelativeLayout) findViewById(R.id.lay_colombia_14);
		Animation animBogota = AnimationUtils.loadAnimation(this, R.anim.anim_colombia_bogota);
		layBogota.startAnimation(animBogota);
		
		RelativeLayout layPereira = (RelativeLayout) findViewById(R.id.lay_colombia_15);
		Animation animPereira = AnimationUtils.loadAnimation(this, R.anim.anim_colombia_pereira);
		layPereira.startAnimation(animPereira);
		
		RelativeLayout layVillavicencio = (RelativeLayout) findViewById(R.id.lay_colombia_16);
		Animation animVillavicencio = AnimationUtils.loadAnimation(this, R.anim.anim_colombia_villavicencio);
		layVillavicencio.startAnimation(animVillavicencio);
		
		RelativeLayout layCali = (RelativeLayout) findViewById(R.id.lay_colombia_17);
		Animation animCali = AnimationUtils.loadAnimation(this, R.anim.anim_colombia_cali);
		layCali.startAnimation(animCali);
		
		RelativeLayout layNeiva = (RelativeLayout) findViewById(R.id.lay_colombia_18);
		Animation animNeiva = AnimationUtils.loadAnimation(this, R.anim.anim_colombia_neiva);
		layNeiva.startAnimation(animNeiva);
		
		RelativeLayout layPuertoAsis = (RelativeLayout) findViewById(R.id.lay_colombia_19);
		Animation animPuertoAsis = AnimationUtils.loadAnimation(this, R.anim.anim_colombia_puerto_asis);
		layPuertoAsis.startAnimation(animPuertoAsis);
		
		RelativeLayout layLeticia = (RelativeLayout) findViewById(R.id.lay_colombia_20);
		Animation animPuertoAsis = AnimationUtils.loadAnimation(this, R.anim.anim_colombia_puerto_asis);
		layPuertoAsis.startAnimation(animPuertoAsis);
	}
	
	// ////////////////////////////////////////////////////////////////
	
	public void animaCiudadesEcuador(){
		RelativeLayout layIslaGalapagos = (RelativeLayout) findViewById(R.id.lay_ecuador_01);
		Animation animIslaGalapagos = AnimationUtils.loadAnimation(this, R.anim.anim_ecuador_isla_galapagos);
		layIslaGalapagos.startAnimation(animIslaGalapagos);
		
		RelativeLayout layBaltro = (RelativeLayout) findViewById(R.id.lay_ecuador_02);
		Animation animBaltro = AnimationUtils.loadAnimation(this, R.anim.anim_ecuador_baltro);
		layBaltro.startAnimation(animBaltro);
		
		RelativeLayout laySanCristobal = (RelativeLayout) findViewById(R.id.lay_ecuador_03);
		Animation animSanCristobal = AnimationUtils.loadAnimation(this, R.anim.anim_ecuador_san_cristobal);
		laySanCristobal.startAnimation(animSanCristobal);
		
		RelativeLayout layGuayaquil = (RelativeLayout) findViewById(R.id.lay_ecuador_04);
		Animation animGuayaquil = AnimationUtils.loadAnimation(this, R.anim.anim_ecuador_guallaquil);
		layGuayaquil.startAnimation(animGuayaquil);
		
		RelativeLayout layQuito = (RelativeLayout) findViewById(R.id.lay_ecuador_05);
		Animation animQuito = AnimationUtils.loadAnimation(this, R.anim.anim_ecuador_quito);
		layQuito.startAnimation(animQuito);
		
		RelativeLayout layCuenca = (RelativeLayout) findViewById(R.id.lay_ecuador_06);
		Animation animCuenca = AnimationUtils.loadAnimation(this, R.anim.anim_ecuador_cuenca);
		layCuenca.startAnimation(animCuenca);
	}
	
	public void animaCiudadesPeru(){
		RelativeLayout layTumbes = (RelativeLayout) findViewById(R.id.lay_peru_01);
		Animation animTumbes = AnimationUtils.loadAnimation(this, R.anim.anim_peru_tumbes);
		layTumbes.startAnimation(animTumbes);
		
		RelativeLayout layIquito = (RelativeLayout) findViewById(R.id.lay_peru_02);
		Animation animIquitos = AnimationUtils.loadAnimation(this, R.anim.anim_peru_iquitos);
		layIquito.startAnimation(animIquitos);
		
		RelativeLayout layPiura = (RelativeLayout) findViewById(R.id.lay_peru_03);
		Animation animPiura = AnimationUtils.loadAnimation(this, R.anim.anim_peru_piura);
		layPiura.startAnimation(animPiura);
		
		RelativeLayout layTaraporo = (RelativeLayout) findViewById(R.id.lay_peru_04);
		Animation animTaraporo = AnimationUtils.loadAnimation(this, R.anim.anim_peru_tarporo);
		layTaraporo.startAnimation(animTaraporo);
		
		RelativeLayout layCajamarca = (RelativeLayout) findViewById(R.id.lay_peru_05);
		Animation animCajamarca = AnimationUtils.loadAnimation(this, R.anim.anim_peru_cajamarca);
		layCajamarca.startAnimation(animCajamarca);
		
		RelativeLayout layTrujillo = (RelativeLayout) findViewById(R.id.lay_peru_06);
		Animation animTrujillo = AnimationUtils.loadAnimation(this, R.anim.anim_peru_trujillo);
		layTrujillo.startAnimation(animTrujillo);
		
		RelativeLayout layPucallpa = (RelativeLayout) findViewById(R.id.lay_peru_07);
		Animation animPucallpa = AnimationUtils.loadAnimation(this, R.anim.anim_peru_pucallpa);
		layPucallpa.startAnimation(animPucallpa);
		
		RelativeLayout layLima = (RelativeLayout) findViewById(R.id.lay_peru_08);
		Animation animLima = AnimationUtils.loadAnimation(this, R.anim.anim_peru_lima);
		layLima.startAnimation(animLima);
		
		RelativeLayout layPuertoMaldonado = (RelativeLayout) findViewById(R.id.lay_peru_09);
		Animation animPuertoMaldonado = AnimationUtils.loadAnimation(this, R.anim.anim_peru_maldonado);
		layPuertoMaldonado.startAnimation(animPuertoMaldonado);
		
		RelativeLayout layCusco = (RelativeLayout) findViewById(R.id.lay_peru_10);
		Animation animCusco = AnimationUtils.loadAnimation(this, R.anim.anim_peru_cusco);
		layCusco.startAnimation(animCusco);
		
		RelativeLayout layArequipa = (RelativeLayout) findViewById(R.id.lay_peru_11);
		Animation animArequipa = AnimationUtils.loadAnimation(this, R.anim.anim_peru_arequipa);
		layArequipa.startAnimation(animArequipa);
		
		RelativeLayout layJulianca = (RelativeLayout) findViewById(R.id.lay_peru_12);
		Animation animJulianca = AnimationUtils.loadAnimation(this, R.anim.anim_peru_julianca);
		layJulianca.startAnimation(animJulianca);
		
		RelativeLayout layTacna = (RelativeLayout) findViewById(R.id.lay_peru_13);
		Animation animTacna = AnimationUtils.loadAnimation(this, R.anim.anim_peru_tacna);
		layTacna.startAnimation(animTacna);
	}

	// ////////////////////////////////////////////////////////////////

	public void cambiaActivity() {
		findViewById(R.id.btn_ir_al_juego).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent act = new Intent(LaminaUnoActivity.this,
								LaminaDosActivity.class);
						startActivity(act);
						overridePendingTransition(R.anim.fade_in,
								R.anim.fade_out);
					}
				});
	}

	public void exMain() {

		final VideoView videoView = (VideoView) findViewById(R.id.video_main);

		videoView
				.setVideoPath("android.resource://com.caromatias.apppisodigitallan/"
						+ R.raw.back_a_2);

		videoLaminaDos = (VideoView) findViewById(R.id.video_lamina_uno);

		videoLaminaDos
				.setVideoPath("android.resource://com.caromatias.apppisodigitallan/"
						+ R.raw.world_transition);

		videoView.start();
		videoView.setOnPreparedListener(new OnPreparedListener() {
			@Override
			public void onPrepared(MediaPlayer mp) {
				mp.setLooping(true);
			}
		});

		Button botonAnimado = (Button) findViewById(R.id.btn_com);
		Animation animacion = AnimationUtils.loadAnimation(this,
				R.anim.animacion);
		final RelativeLayout layPanelRutas = (RelativeLayout) findViewById(R.id.panel);
		final RelativeLayout goGameMaster = (RelativeLayout) findViewById(R.id.go_game_master);
		final Button btnGoGame = (Button) findViewById(R.id.btn_ir_al_juego);
		final Animation animGoGame = AnimationUtils.loadAnimation(this,
				R.anim.animacion);
		final Button btnGoGameText = (Button) findViewById(R.id.btn_ir_al_juego_text);
		final Animation animGoGameText = AnimationUtils.loadAnimation(this,
				R.anim.slide_out);
		botonAnimado.startAnimation(animacion);
		final Animation animVideoMain = AnimationUtils.loadAnimation(this,
				R.anim.fade_in);
		final Animation animVideoMainOut = AnimationUtils.loadAnimation(this,
				R.anim.fade_out);
		final Animation animInInter = AnimationUtils.loadAnimation(this,
				R.anim.in_mapa_inter);
		final Animation animPanel = AnimationUtils.loadAnimation(this,
				R.anim.anim_lamina_home_market);
		final RelativeLayout layZonaZoomable = (RelativeLayout) findViewById(R.id.zona_zoomable);

		findViewById(R.id.btn_com).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// VideoView videoView = (VideoView)
				// findViewById(R.id.video_main);
				RelativeLayout layVideoMain = (RelativeLayout) findViewById(R.id.lay_video_main);
				final ImageView imgWhite = (ImageView) findViewById(R.id.img_fade_white);

				// videoView.pause();
				Button botonAnimado = (Button) findViewById(R.id.btn_com);
				botonAnimado.setBackgroundResource(R.drawable.botoncomenzar);
				imgWhite.setVisibility(View.VISIBLE);
				imgWhite.setAnimation(animVideoMain);
				layVideoMain.setVisibility(View.GONE);

				final Handler handler = new Handler();
				handler.postDelayed(new Runnable() {
					@Override
					public void run() {
						// Do something after 5s = 5000ms
						videoLaminaDos.start();
						videoView.setVisibility(View.INVISIBLE);
						imgWhite.setAnimation(animVideoMainOut);
						imgWhite.setVisibility(View.GONE);
					}
				}, 800);

				final Handler handlerDos = new Handler();
				handlerDos.postDelayed(new Runnable() {
					@Override
					public void run() {
						// Do something after 5s = 5000ms
						layZonaZoomable.bringToFront();
						layZonaZoomable.startAnimation(animInInter);
					}
				}, 4000);

				final Handler handlerTres = new Handler();
				handlerTres.postDelayed(new Runnable() {
					@Override
					public void run() {
						// Do something after 5s = 5000ms
						videoLaminaDos.setVisibility(View.GONE);
					}
				}, 5000);

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
			}
		});
	}

}
