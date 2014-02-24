package com.caromatias.apppisodigitallan;

import pl.polidea.view.ZoomView;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.VideoView;

public class LaminaUnoActivity extends Activity {

	private ZoomView zoomView;
	private int posicionMenu = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lamina_uno);

		View v = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE))
				.inflate(R.layout.zoomableview, null, false);
		v.setLayoutParams(new LinearLayout.LayoutParams(
				android.view.ViewGroup.LayoutParams.FILL_PARENT,
				android.view.ViewGroup.LayoutParams.FILL_PARENT));

		zoomView = new ZoomView(this);
		zoomView.addView(v);
		RelativeLayout lay_principal = (RelativeLayout) findViewById(R.id.zona_zoomable);
		lay_principal.addView(zoomView);

		final VideoView videoView = (VideoView) findViewById(R.id.video_lamina_uno);

		videoView
				.setVideoPath("android.resource://com.caromatias.apppisodigitallan/"
						+ R.raw.transicion);

		videoView.start();

		// //////// Cambio de imagen a video ////////////

		final Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
			@Override
			public void run() {
				// Do something after 5s = 5000ms
				videoView.setVisibility(View.INVISIBLE);
			}
		}, 5000);

		// ////////////////////////////////////////////

		eventosBotones();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lamina_uno, menu);
		return true;
	}

	// ///////////////// EVENTOS BOTONES DEL PANEL /////////////////////////

	public void eventosBotones() {

		final Button btnInter = (Button) findViewById(R.id.btn_internacional);
		btnInter.setBackgroundResource(R.drawable.botonrojo);

		final Button btnPeru = (Button) findViewById(R.id.btn_peru);
		final Button btnBrasil = (Button) findViewById(R.id.btn_brasil);
		final Button btnEcuador = (Button) findViewById(R.id.btn_ecuador);
		final Button btnColombia = (Button) findViewById(R.id.btn_colombia);

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
						Button btnChile = (Button) findViewById(R.id.btn_chile);
						Button btnArgentina = (Button) findViewById(R.id.btn_argentina);
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
									layMapaInter.startAnimation(animMapaEcuador);
									break;
								case 7:
									layMapaColombia.setVisibility(View.GONE);
									layMapaInter.setVisibility(View.VISIBLE);
									layMapaInter.startAnimation(animMapaColombia);
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
				Button btnChile = (Button) findViewById(R.id.btn_chile);
				Button btnArgentina = (Button) findViewById(R.id.btn_argentina);
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
						Button btnChile = (Button) findViewById(R.id.btn_chile);
						Button btnArgentina = (Button) findViewById(R.id.btn_argentina);
						btnArgentina.setBackgroundResource(R.drawable.botonrojo);

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
									layMapaArgentina.setVisibility(View.VISIBLE);
									layMapaArgentina.startAnimation(animMapaArgentina);
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
				Button btnPeru = (Button) findViewById(R.id.btn_peru);
				Button btnArgentina = (Button) findViewById(R.id.btn_argentina);
				Button btnChile = (Button) findViewById(R.id.btn_chile);
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
				Button btnArgentina = (Button) findViewById(R.id.btn_argentina);
				Button btnChile = (Button) findViewById(R.id.btn_chile);
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
		// ///////////////////// CLICK BOTÓN ECUADOR ////////////////////////////
		findViewById(R.id.btn_ecuador).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Button btnArgentina = (Button) findViewById(R.id.btn_argentina);
						Button btnChile = (Button) findViewById(R.id.btn_chile);
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
									layMapaEcuador.startAnimation(animMapaEcuador);
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
		// ///////////////////// CLICK BOTÓN COLOMBIA ///////////////////////////
		findViewById(R.id.btn_colombia).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Button btnArgentina = (Button) findViewById(R.id.btn_argentina);
						Button btnChile = (Button) findViewById(R.id.btn_chile);
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
									layMapaColombia.startAnimation(animMapaColombia);
									break;
								case 6:
									layMapaEcuador.setVisibility(View.GONE);
									layMapaColombia.setVisibility(View.VISIBLE);
									layMapaColombia.startAnimation(animMapaColombia);
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

	// ////////////////////////////////////////////////////////////////

}
