package com.caromatias.apppisodigitallan;

import pl.polidea.view.ZoomView;
import android.media.AudioManager;
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
import android.widget.TextView;
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
	private Button btnFlota;
	private Button btnDestinos;
	private int posiciones = 1;
	public static MediaPlayer mpFondoUno;
	private RelativeLayout layFlota;
	private RelativeLayout layDestinos;
	private RelativeLayout layPopupInfo;
	private boolean doubleClick = false;
	//private RelativeLayout animLayLogo;
	private Animation animLogo;
	private Button animLayDes;
	private Animation animDes;
	private Animation animFlota;
	private RelativeLayout popNuestraFlota;
	private TextView tituloPopInfo;
	private TextView descPopInfo;
	// ///////////////////////////////////
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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lamina_uno);

		reseteaRutas();

		AudioManager audio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
		int currentVolume = audio.getStreamVolume(AudioManager.STREAM_MUSIC);
		int maxVolume = audio.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		float percent = 1.0f;
		int seventyVolume = (int) (maxVolume * percent);
		audio.setStreamVolume(AudioManager.STREAM_MUSIC, seventyVolume, 0);
		mpFondoUno = MediaPlayer.create(this, R.raw.the_shining);

		final View v = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE))
				.inflate(R.layout.zoomableview, null, false);
		v.setLayoutParams(new LinearLayout.LayoutParams(
				android.view.ViewGroup.LayoutParams.FILL_PARENT,
				android.view.ViewGroup.LayoutParams.FILL_PARENT));

		zoomView = new ZoomView(this);
		zoomView.addView(v);
		lay_principal = (RelativeLayout) findViewById(R.id.zona_zoomable);
		lay_principal.addView(zoomView);
		
		popNuestraFlota = (RelativeLayout) findViewById(R.id.lay_img_flota);
		//animLayLogo = (RelativeLayout) findViewById(R.id.lay_logo_pasajero_virtual_dos);
		animLogo = AnimationUtils.loadAnimation(this,R.anim.anim_lineas_brasil);
		animLayDes = (Button) findViewById(R.id.button_nuestros_destinos);
		animDes = AnimationUtils.loadAnimation(this,
				R.anim.anim_lineas_brasil);
		animFlota = AnimationUtils.loadAnimation(this,
				R.anim.anim_lineas_brasil);

		exMain();

		eventosBotones();

		cambiaActivity();
		
		animBotonesMapaInternacional();

		tituloPopInfo = (TextView) findViewById(R.id.txt_titulo_popup_info);
		descPopInfo = (TextView) findViewById(R.id.txt_descripcion_popup_info);

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
		btnFlota = (Button) findViewById(R.id.button_nuestra_flota);
		btnDestinos = (Button) findViewById(R.id.button_nuestros_destinos);
		layPopupInfo = (RelativeLayout) findViewById(R.id.lay_popup_info);
		final Animation animNuevoMapaInter = AnimationUtils.loadAnimation(this,R.anim.anim_in_juego);
		

		findViewById(R.id.btn_cerrar_popup_info).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						layPopupInfo.setVisibility(View.GONE);
					}
				});

		
		findViewById(R.id.lay_apretable_chile).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						if(doubleClick == true){
							tituloPopInfo.setText(getResources().getString(R.string.titulo_pop_info1));
							descPopInfo.setText(getResources().getString(R.string.desc_pop_info1));
							layPopupInfo.bringToFront();
							layPopupInfo.setVisibility(View.VISIBLE);
						}else {
							doubleClick = true;
							Handler handlerDoubleClickUno = new Handler();
							handlerDoubleClickUno.postDelayed(new Runnable() {
								@Override
								public void run() {
									doubleClick = false;
								}
							}, 1000);
						}
					}
				});
		findViewById(R.id.lay_apretable_argentina).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						if(doubleClick == true){
							tituloPopInfo.setText(getResources().getString(R.string.titulo_pop_info2));
							descPopInfo.setText(getResources().getString(R.string.desc_pop_info2));
							layPopupInfo.bringToFront();
							layPopupInfo.setVisibility(View.VISIBLE);
						}else {
							doubleClick = true;
							Handler handlerDoubleClickUno = new Handler();
							handlerDoubleClickUno.postDelayed(new Runnable() {
								@Override
								public void run() {
									doubleClick = false;
								}
							}, 1000);
						}
					}
				});
		findViewById(R.id.lay_apretable_peru).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						if(doubleClick == true){
							tituloPopInfo.setText(getResources().getString(R.string.titulo_pop_info3));
							descPopInfo.setText(getResources().getString(R.string.desc_pop_info3));
							layPopupInfo.bringToFront();
							layPopupInfo.setVisibility(View.VISIBLE);
						}else {
							doubleClick = true;
							Handler handlerDoubleClickUno = new Handler();
							handlerDoubleClickUno.postDelayed(new Runnable() {
								@Override
								public void run() {
									doubleClick = false;
								}
							}, 1000);
						}
					}
				});
		findViewById(R.id.lay_apretable_brasil).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						if(doubleClick == true){
							tituloPopInfo.setText(getResources().getString(R.string.titulo_pop_info4));
							descPopInfo.setText(getResources().getString(R.string.desc_pop_info4));
							layPopupInfo.bringToFront();
							layPopupInfo.setVisibility(View.VISIBLE);
						}else {
							doubleClick = true;
							Handler handlerDoubleClickUno = new Handler();
							handlerDoubleClickUno.postDelayed(new Runnable() {
								@Override
								public void run() {
									doubleClick = false;
								}
							}, 1000);
						}
					}
				});
		findViewById(R.id.lay_apretable_ecuador).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						if(doubleClick == true){
							tituloPopInfo.setText(getResources().getString(R.string.titulo_pop_info5));
							descPopInfo.setText(getResources().getString(R.string.desc_pop_info5));
							layPopupInfo.bringToFront();
							layPopupInfo.setVisibility(View.VISIBLE);
						}else {
							doubleClick = true;
							Handler handlerDoubleClickUno = new Handler();
							handlerDoubleClickUno.postDelayed(new Runnable() {
								@Override
								public void run() {
									doubleClick = false;
								}
							}, 1000);
						}
					}
				});
		findViewById(R.id.lay_apretable_colombia).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						if(doubleClick == true){
							tituloPopInfo.setText(getResources().getString(R.string.titulo_pop_info6));
							descPopInfo.setText(getResources().getString(R.string.desc_pop_info6));
							layPopupInfo.bringToFront();
							layPopupInfo.setVisibility(View.VISIBLE);
						}else {
							doubleClick = true;
							Handler handlerDoubleClickUno = new Handler();
							handlerDoubleClickUno.postDelayed(new Runnable() {
								@Override
								public void run() {
									doubleClick = false;
								}
							}, 1000);
						}
					}
				});

		// ////////////////////// BOTONES FLOTA Y DESTINO
		// //////////////////////////

		final Button layBotonFlota = (Button) findViewById(R.id.button_nuestra_flota);
		final Animation animBotonFlota = AnimationUtils.loadAnimation(this,
				R.anim.anim_mapa_in);
		final Animation animBotonFlotaOut = AnimationUtils.loadAnimation(this,
				R.anim.anim_mapa_inter_out);

		final Button layBotonDestinos = (Button) findViewById(R.id.button_nuestros_destinos);
		final Animation animBotonDestinos = AnimationUtils.loadAnimation(this,
				R.anim.anim_mapa_in);
		final Animation animBotonDestinosOut = AnimationUtils.loadAnimation(
				this, R.anim.anim_mapa_inter_out);

		// //////////////////////// BOTONES MAPA INTERNACIONAL
		// //////////////////////////

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

		// ///////////////////// CLICK BOT�N INTERNACIONAL
		// ////////////////////////////

		findViewById(R.id.button_nuestra_flota).setOnClickListener(

		new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ResizeWidthAnimation anim = new ResizeWidthAnimation(btnFlota,
						300);
				anim.setDuration(500);
				btnFlota.setTextSize(18);
				btnFlota.bringToFront();
				btnFlota.startAnimation(anim);

				ResizeWidthAnimation animDes = new ResizeWidthAnimation(
						btnDestinos, 300);
				animDes.setDuration(500);
				btnDestinos.setTextSize(14);
				btnDestinos.startAnimation(anim);
				popNuestraFlota.setVisibility(View.VISIBLE);
				popNuestraFlota.bringToFront();
				popNuestraFlota.startAnimation(animNuevoMapaInter);
				btnDestinos.bringToFront();
				btnFlota.bringToFront();
			}
		});

		findViewById(R.id.button_nuestros_destinos).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						ResizeWidthAnimation anim = new ResizeWidthAnimation(
								btnDestinos, 355);
						anim.setDuration(500);
						btnDestinos.setTextSize(18);
						btnDestinos.bringToFront();
						btnDestinos.startAnimation(anim);

						ResizeWidthAnimation animDes = new ResizeWidthAnimation(
								btnFlota, 300);
						animDes.setDuration(500);
						btnFlota.setTextSize(14);
						btnFlota.startAnimation(anim);
						popNuestraFlota.startAnimation(animMapaInter);
						popNuestraFlota.setVisibility(View.GONE);
					}
				});

		findViewById(R.id.btn_internacional).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						// btnInter.setBackgroundResource(R.drawable.botonrojo);
						reestableceBotones(btnInter);

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
									layMapaInter
											.startAnimation(animNuevoMapaInter);
									break;
								case 3:
									layMapaArgentina.setVisibility(View.GONE);
									layMapaInter.setVisibility(View.VISIBLE);
									layMapaInter
											.startAnimation(animNuevoMapaInter);
									break;
								case 4:
									layMapaPeru.setVisibility(View.GONE);
									layMapaInter.setVisibility(View.VISIBLE);
									layMapaInter
											.startAnimation(animNuevoMapaInter);
									break;
								case 5:
									layMapaBrasil.setVisibility(View.GONE);
									layMapaInter.setVisibility(View.VISIBLE);
									layMapaInter
											.startAnimation(animNuevoMapaInter);
									break;
								case 6:
									layMapaEcuador.setVisibility(View.GONE);
									layMapaInter.setVisibility(View.VISIBLE);
									layMapaInter
											.startAnimation(animNuevoMapaInter);
									break;
								case 7:
									layMapaColombia.setVisibility(View.GONE);
									layMapaInter.setVisibility(View.VISIBLE);
									layMapaInter
											.startAnimation(animNuevoMapaInter);
									break;
								}
								// ///// Animaciones
								animBotonesMapaInternacional();
								posicionMenu = 1;
							}
						}, 1000);
						btnInter.setEnabled(false);
					}
				});
		// //////////////////////////////////////////////////////////////////
		// ///////////////////// CLICK BOT�N CHILE //////////////////////////

		findViewById(R.id.btn_chile).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// btnChile.setBackgroundResource(R.drawable.botonrojo);
				reestableceBotones(btnChile);

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
		// ///////////////////// CLICK BOT�N SANTIAGO //////////////////////////
		findViewById(R.id.button_internacional_santiago).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						// btnChile.setBackgroundResource(R.drawable.botonrojo);
						reestableceBotones(btnChile);

						switch (posicionMenu) {
						case 1:
							layMapaInter.startAnimation(animMapaInter);
							btnInter.setBackgroundResource(R.drawable.boton);
							btnInter.setEnabled(true);
							break;
						case 2:
							return;
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
		// ///////////////////// CLICK CIRCULO SANTIAGO
		// //////////////////////////
		findViewById(R.id.image_internacional_santiago).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						// btnChile.setBackgroundResource(R.drawable.botonrojo);
						reestableceBotones(btnChile);

						switch (posicionMenu) {
						case 1:
							layMapaInter.startAnimation(animMapaInter);
							btnInter.setBackgroundResource(R.drawable.boton);
							btnInter.setEnabled(true);
							break;
						case 2:
							return;
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
		// ///////////////////// CLICK BOT�N ARGENTINA //////////////////////
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
									layMapaArgentina.setVisibility(View.VISIBLE);
									layMapaArgentina.startAnimation(animMapaArgentina);
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
								animaCiudadesArgentina();
								posicionMenu = 3;
							}
						}, 1000);
						btnArgentina.setEnabled(false);
					}
				});
		// //////////////////////////////////////////////////////////////////
		// ///////////////////// CLICK BOT�N BUENOS AIRES
		// //////////////////////////
		findViewById(R.id.button_internacional_buenos_aires)
				.setOnClickListener(new OnClickListener() {
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
								animaCiudadesArgentina();
								posicionMenu = 3;
							}
						}, 1000);
						btnArgentina.setEnabled(false);
					}
				});

		// //////////////////////////////////////////////////////////////////
		// ///////////////////// CLICK CIRCULO BUENOS AIRES
		// //////////////////////////
		findViewById(R.id.image_internacional_buenos_aires).setOnClickListener(
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
								animaCiudadesArgentina();
								posicionMenu = 3;
							}
						}, 1000);
						btnArgentina.setEnabled(false);
					}
				});
		// //////////////////////////////////////////////////////////////////
		// ///////////////////// CLICK BOT�N PER� ///////////////////////////
		findViewById(R.id.btn_peru).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// btnPeru.setBackgroundResource(R.drawable.botonrojo);
				reestableceBotones(btnPeru);

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
						animaCiudadesPeru();
						posicionMenu = 4;
					}
				}, 1000);
				btnPeru.setEnabled(false);
			}
		});
		// //////////////////////////////////////////////////////////////////
		// ///////////////////// CLICK BOT�N LIMA ///////////////////////////
		findViewById(R.id.button_internacional_lima_1).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						// btnPeru.setBackgroundResource(R.drawable.botonrojo);
						reestableceBotones(btnPeru);

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
								animaCiudadesPeru();
								posicionMenu = 4;
							}
						}, 1000);
						btnPeru.setEnabled(false);
					}
				});

		// //////////////////////////////////////////////////////////////////
		// ///////////////////// CLICK CIRCULO LIMA ///////////////////////////
		findViewById(R.id.image_internacional_lima_1).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						// btnPeru.setBackgroundResource(R.drawable.botonrojo);
						reestableceBotones(btnPeru);

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
								animaCiudadesPeru();
								posicionMenu = 4;
							}
						}, 1000);
						btnPeru.setEnabled(false);
					}
				});

		// //////////////////////////////////////////////////////////////////////
		// ///////////////////// CLICK BOT�N BRASIL ///////////////////////////
		findViewById(R.id.btn_brasil).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// btnBrasil.setBackgroundResource(R.drawable.botonrojo);
				reestableceBotones(btnBrasil);

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
						animaCiudadesBrasil();
						posicionMenu = 5;
					}
				}, 1000);
				btnBrasil.setEnabled(false);
			}
		});
		// //////////////////////////////////////////////////////////////////////
		// ///////////////////// CLICK BOT�N BRASILIA
		// ///////////////////////////
		findViewById(R.id.button_internacional_brasilia).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						// btnBrasil.setBackgroundResource(R.drawable.botonrojo);
						reestableceBotones(btnBrasil);

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
									layMapaBrasil
											.startAnimation(animMapaBrasil);
									break;
								case 2:
									layMapaChile.setVisibility(View.GONE);
									layMapaBrasil.setVisibility(View.VISIBLE);
									layMapaBrasil
											.startAnimation(animMapaBrasil);
									break;
								case 3:
									layMapaArgentina.setVisibility(View.GONE);
									layMapaBrasil.setVisibility(View.VISIBLE);
									layMapaBrasil
											.startAnimation(animMapaBrasil);
									break;
								case 4:
									layMapaPeru.setVisibility(View.GONE);
									layMapaBrasil.setVisibility(View.VISIBLE);
									layMapaBrasil
											.startAnimation(animMapaBrasil);
									break;
								case 5:
									return;
								case 6:
									layMapaEcuador.setVisibility(View.GONE);
									layMapaBrasil.setVisibility(View.VISIBLE);
									layMapaBrasil
											.startAnimation(animMapaBrasil);
									break;
								case 7:
									layMapaColombia.setVisibility(View.GONE);
									layMapaBrasil.setVisibility(View.VISIBLE);
									layMapaBrasil
											.startAnimation(animMapaBrasil);
									break;
								}
								// ///// Animaciones
								animaCiudadesBrasil();
								posicionMenu = 5;
							}
						}, 1000);
						btnBrasil.setEnabled(false);
					}
				});

		// //////////////////////////////////////////////////////////////////////
		// ///////////////////// CLICK CIRCULO BRASILIA
		// ///////////////////////////
		findViewById(R.id.image_internacional_brasilia).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						// btnBrasil.setBackgroundResource(R.drawable.botonrojo);
						reestableceBotones(btnBrasil);

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
									layMapaBrasil
											.startAnimation(animMapaBrasil);
									break;
								case 2:
									layMapaChile.setVisibility(View.GONE);
									layMapaBrasil.setVisibility(View.VISIBLE);
									layMapaBrasil
											.startAnimation(animMapaBrasil);
									break;
								case 3:
									layMapaArgentina.setVisibility(View.GONE);
									layMapaBrasil.setVisibility(View.VISIBLE);
									layMapaBrasil
											.startAnimation(animMapaBrasil);
									break;
								case 4:
									layMapaPeru.setVisibility(View.GONE);
									layMapaBrasil.setVisibility(View.VISIBLE);
									layMapaBrasil
											.startAnimation(animMapaBrasil);
									break;
								case 5:
									return;
								case 6:
									layMapaEcuador.setVisibility(View.GONE);
									layMapaBrasil.setVisibility(View.VISIBLE);
									layMapaBrasil
											.startAnimation(animMapaBrasil);
									break;
								case 7:
									layMapaColombia.setVisibility(View.GONE);
									layMapaBrasil.setVisibility(View.VISIBLE);
									layMapaBrasil
											.startAnimation(animMapaBrasil);
									break;
								}
								// ///// Animaciones
								animaCiudadesBrasil();
								posicionMenu = 5;
							}
						}, 1000);
						btnBrasil.setEnabled(false);
					}
				});
		// //////////////////////////////////////////////////////////////////////
		// ///////////////////// CLICK BOT�N ECUADOR //
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
								animaCiudadesEcuador();
								posicionMenu = 6;
							}
						}, 1000);
						btnEcuador.setEnabled(false);
					}
				});
		// //////////////////////////////////////////////////////////////////////
		// ///////////////////// CLICK BOT�N GUAYAQUIL //
		// ////////////////////////////
		findViewById(R.id.button_internacional_guayaquil).setOnClickListener(
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
								animaCiudadesEcuador();
								posicionMenu = 6;
							}
						}, 1000);
						btnEcuador.setEnabled(false);
					}
				});
		////////////////////////////////////////////////////////////////////////
		/////////////////////// CLICK CIRCULO GUAYAQUIL ////////////////////////
		findViewById(R.id.image_internacional_guayaquil).setOnClickListener(
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
								animaCiudadesEcuador();
								posicionMenu = 6;
							}
						}, 1000);
						btnEcuador.setEnabled(false);
					}
				});

		// //////////////////////////////////////////////////////////////////////
		// ///////////////////// CLICK BOT�N COLOMBIA //
		// ///////////////////////////
		findViewById(R.id.btn_colombia).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						// btnColombia.setBackgroundResource(R.drawable.botonrojo);
						reestableceBotones(btnColombia);

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
								animaCiudadesColombia();
								posicionMenu = 7;
							}
						}, 1000);
						btnColombia.setEnabled(false);
					}
				});

		// //////////////////////////////////////////////////////////////////////
		// ///////////////////// CLICK BOT�N BOGOTA //
		// ///////////////////////////
		findViewById(R.id.button_internacional_bogota).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						// btnColombia.setBackgroundResource(R.drawable.botonrojo);
						reestableceBotones(btnColombia);

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
								animaCiudadesColombia();
								posicionMenu = 7;
							}
						}, 1000);
						btnColombia.setEnabled(false);
					}
				});

		// //////////////////////////////////////////////////////////////////////
		// ///////////////////// CLICK CIRCULO BOGOTA //
		// ///////////////////////////
		findViewById(R.id.image_internacional_bogota).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						// btnColombia.setBackgroundResource(R.drawable.botonrojo);
						reestableceBotones(btnColombia);

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
								animaCiudadesColombia();
								posicionMenu = 7;
							}
						}, 1000);
						btnColombia.setEnabled(false);
					}
				});

	}
	
	public void animBotonesMapaInternacional() {

		// ////////////////////// BOTONES (CAPITALES) DE MAPA INTERNACIONAL /////////////////////////////

		Button animLayInterBogota = (Button) findViewById(R.id.button_internacional_bogota);
		Animation animInterBogota = AnimationUtils.loadAnimation(this,
				R.anim.anim_conoce_flota);
		animLayInterBogota.setVisibility(View.VISIBLE);
		animLayInterBogota.startAnimation(animInterBogota);

		Button animLayInterGuayaquil = (Button) findViewById(R.id.button_internacional_guayaquil);
		Animation animInterGuayaquil = AnimationUtils.loadAnimation(this,
				R.anim.anim_conoce_flota);
		animLayInterGuayaquil.startAnimation(animInterGuayaquil);

		Button animLayInterLima = (Button) findViewById(R.id.button_internacional_lima_1);
		Animation animInterLima = AnimationUtils.loadAnimation(this,
				R.anim.anim_conoce_flota);
		animLayInterLima.startAnimation(animInterLima);

		Button animLayInterSantiago = (Button) findViewById(R.id.button_internacional_santiago);
		Animation animInterSantiago = AnimationUtils.loadAnimation(this,
				R.anim.anim_conoce_flota);
		animLayInterSantiago.startAnimation(animInterSantiago);

		Button animLayInterBuenosAires = (Button) findViewById(R.id.button_internacional_buenos_aires);
		Animation animInterBuenosAires = AnimationUtils.loadAnimation(this,
				R.anim.anim_conoce_flota);
		animLayInterBuenosAires.startAnimation(animInterBuenosAires);

		Button animLayInterBrasilia = (Button) findViewById(R.id.button_internacional_brasilia);
		Animation animInterBrasilia = AnimationUtils.loadAnimation(this,
				R.anim.anim_conoce_flota);
		animLayInterBrasilia.startAnimation(animInterBrasilia);
		
		// ////////////////////// BOTONES (CAPITALES) DE MAPA INTERNACIONAL BOTONES AZULES /////////////////////////////
		
		Button animLayInterSanFrancisco = (Button) findViewById(R.id.btn_inter_sanfrancisco);
		Animation animInterSanFrancisco = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_01);
		animLayInterSanFrancisco.startAnimation(animInterSanFrancisco);
		
		Button animLayInterLosAngeles = (Button) findViewById(R.id.btn_inter_losangeles);
		Animation animInterLosAngeles = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_02);
		animLayInterLosAngeles.startAnimation(animInterLosAngeles);
		
		Button animLayInterMexico = (Button) findViewById(R.id.btn_inter_mexico);
		Animation animInterMexico = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_03);
		animLayInterMexico.startAnimation(animInterMexico);
		
		Button animLayInterCancun = (Button) findViewById(R.id.btn_inter_cancun);
		Animation animInterCancun = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_04);
		animLayInterCancun.startAnimation(animInterCancun);
		
		Button animLayInterVeracruz = (Button) findViewById(R.id.btn_inter_veracruz);
		Animation animInterVeracruz = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_05);
		animLayInterVeracruz.startAnimation(animInterVeracruz);
		
		Button animLayInterOrlando = (Button) findViewById(R.id.btn_inter_orlando);
		Animation animInterOrlando = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_06);
		animLayInterOrlando.startAnimation(animInterOrlando);
		
		Button animLayInterMiami = (Button) findViewById(R.id.btn_inter_miami);
		Animation animInterMiami = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_07);
		animLayInterMiami.startAnimation(animInterMiami);
		
		Button animLayInterLaHabana = (Button) findViewById(R.id.btn_inter_laHabana);
		Animation animInterLaHabana = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_08);
		animLayInterLaHabana.startAnimation(animInterLaHabana);
		
		Button animLayInterPuntacana = (Button) findViewById(R.id.btn_inter_puntacana);
		Animation animInterPunatacana = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_09);
		animLayInterPuntacana.startAnimation(animInterPunatacana);
		
		Button animLayInterSanandres = (Button) findViewById(R.id.btn_inter_sanandres);
		Animation animInterSanandres = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_10);
		animLayInterSanandres.startAnimation(animInterSanandres);
		
		Button animLayInterNew = (Button) findViewById(R.id.btn_inter_newyork);
		Animation animInterNew = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_11);
		animLayInterNew.startAnimation(animInterNew);
		
		Button animLayInterSidney = (Button) findViewById(R.id.btn_inter_sidney);
		Animation animInterSidney = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_12);
		animLayInterSidney.startAnimation(animInterSidney);
		
		Button animLayInterOuckland = (Button) findViewById(R.id.btn_inter_ouckland);
		Animation animInterOuckland = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_13);
		animLayInterOuckland.startAnimation(animInterOuckland);
		
		Button animLayInterPapete = (Button) findViewById(R.id.btn_inter_papeete);
		Animation animInterPapete = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_14);
		animLayInterPapete.startAnimation(animInterPapete);
		
		Button animLayInterIslapascua = (Button) findViewById(R.id.btn_inter_islapascua);
		Animation animInterIslapascua = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_15);
		animLayInterIslapascua.startAnimation(animInterIslapascua);
		
		Button animLayInterRiogallegos = (Button) findViewById(R.id.btn_inter_riogallegos);
		Animation animInterRiogllegos = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_16);
		animLayInterRiogallegos.startAnimation(animInterRiogllegos);
		
		Button animLayInterLonderesAz = (Button) findViewById(R.id.btn_inter_londresaz);
		Animation animInterLondresAz = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_17);
		animLayInterLonderesAz.startAnimation(animInterLondresAz);
		
		Button animLayInterFrankfurt = (Button) findViewById(R.id.btn_inter_frankfurt);
		Animation animInterFrankfurt = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_18);
		animLayInterFrankfurt.startAnimation(animInterFrankfurt);
		
		Button animLayInterParis = (Button) findViewById(R.id.btn_inter_paris);
		Animation animInterParis = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_19);
		animLayInterParis.startAnimation(animInterParis);
		
		Button animLayInterMilan = (Button) findViewById(R.id.btn_inter_milan);
		Animation animInterMilan = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_20);
		animLayInterMilan.startAnimation(animInterMilan);
		
		Button animLayInterMadrid = (Button) findViewById(R.id.btn_inter_madrid);
		Animation animInterMadrid = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_21);
		animLayInterMadrid.startAnimation(animInterMadrid);
	}
	
	public void animaCiudadesChile() {
		RelativeLayout layArica = (RelativeLayout) findViewById(R.id.lay_chile_01);
		Animation animArica = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_01);
		layArica.startAnimation(animArica);

		RelativeLayout layIquique = (RelativeLayout) findViewById(R.id.lay_chile_02);
		Animation animIquique = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_02);
		layIquique.startAnimation(animIquique);

		RelativeLayout layCalama = (RelativeLayout) findViewById(R.id.lay_chile_14);
		Animation animCalama = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_03);
		layCalama.startAnimation(animCalama);

		RelativeLayout layAntofagasta = (RelativeLayout) findViewById(R.id.lay_chile_03);
		Animation animAntofagasta = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_04);
		layAntofagasta.startAnimation(animAntofagasta);

		RelativeLayout layCopiapo = (RelativeLayout) findViewById(R.id.lay_chile_04);
		Animation animCopiapo = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_05);
		layCopiapo.startAnimation(animCopiapo);

		RelativeLayout laySerena = (RelativeLayout) findViewById(R.id.lay_chile_05);
		Animation animSerena = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_06);
		laySerena.startAnimation(animSerena);

		RelativeLayout laySantiago = (RelativeLayout) findViewById(R.id.lay_chile_06);
		Animation animSantiago = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_07);
		laySantiago.startAnimation(animSantiago);

		RelativeLayout layPascua = (RelativeLayout) findViewById(R.id.lay_chile_16);
		Animation animPascua = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_08);
		layPascua.startAnimation(animPascua);

		RelativeLayout layConcepcion = (RelativeLayout) findViewById(R.id.lay_chile_07);
		Animation animConcepcion = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_09);
		layConcepcion.startAnimation(animConcepcion);

		RelativeLayout layTemuco = (RelativeLayout) findViewById(R.id.lay_chile_08);
		Animation animTemuco = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_10);
		layTemuco.startAnimation(animTemuco);

		RelativeLayout layValdivia = (RelativeLayout) findViewById(R.id.lay_chile_09);
		Animation animValdivia = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_11);
		layValdivia.startAnimation(animValdivia);

		RelativeLayout layOsorno = (RelativeLayout) findViewById(R.id.lay_chile_10);
		Animation animOsorno = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_12);
		layOsorno.startAnimation(animOsorno);

		RelativeLayout layMontt = (RelativeLayout) findViewById(R.id.lay_chile_11);
		Animation animMontt = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_13);
		layMontt.startAnimation(animMontt);

		RelativeLayout layChiloe = (RelativeLayout) findViewById(R.id.lay_chile_12);
		Animation animChileo = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_14);
		layChiloe.startAnimation(animChileo);

		RelativeLayout layBalmaceda = (RelativeLayout) findViewById(R.id.lay_chile_15);
		Animation animBalmaceda = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_15);
		layBalmaceda.startAnimation(animBalmaceda);

		RelativeLayout layArenas = (RelativeLayout) findViewById(R.id.lay_chile_13);
		Animation animArenas = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_16);
		layArenas.startAnimation(animArenas);

		ImageView layAnimChile = (ImageView) findViewById(R.id.img_lineas_chile);
		Animation animLineasChile = AnimationUtils.loadAnimation(this,
				R.anim.anim_lineas_chile);
		layAnimChile.startAnimation(animLineasChile);
	}

	public void animaCiudadesArgentina() {
		RelativeLayout laySalta = (RelativeLayout) findViewById(R.id.lay_argentina_01);
		Animation animSalta = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_01);
		laySalta.startAnimation(animSalta);

		RelativeLayout layTucuman = (RelativeLayout) findViewById(R.id.lay_argentina_02);
		Animation animTucuman = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_02);
		layTucuman.startAnimation(animTucuman);

		RelativeLayout layIguazu = (RelativeLayout) findViewById(R.id.lay_argentina_03);
		Animation animIguazu = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_03);
		layIguazu.startAnimation(animIguazu);

		RelativeLayout layCordoba = (RelativeLayout) findViewById(R.id.lay_argentina_04);
		Animation animCordoba = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_04);
		layCordoba.startAnimation(animCordoba);

		RelativeLayout laySanJuan = (RelativeLayout) findViewById(R.id.lay_argentina_05);
		Animation animSanJuan = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_05);
		laySanJuan.startAnimation(animSanJuan);

		RelativeLayout layMendoza = (RelativeLayout) findViewById(R.id.lay_argentina_06);
		Animation animMendoza = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_06);
		layMendoza.startAnimation(animMendoza);

		RelativeLayout layBuenosAires = (RelativeLayout) findViewById(R.id.lay_argentina_07);
		Animation animBuenosAires = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_07);
		layBuenosAires.startAnimation(animBuenosAires);

		RelativeLayout layNeuquen = (RelativeLayout) findViewById(R.id.lay_argentina_08);
		Animation animNeuquen = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_08);
		layNeuquen.startAnimation(animNeuquen);

		RelativeLayout layBahiaBlabca = (RelativeLayout) findViewById(R.id.lay_argentina_10);
		Animation animBahiaBlanca = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_09);
		layBahiaBlabca.startAnimation(animBahiaBlanca);

		RelativeLayout layBariloche = (RelativeLayout) findViewById(R.id.lay_argentina_11);
		Animation animBariloche = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_10);
		layBariloche.startAnimation(animBariloche);

		RelativeLayout layComodoro = (RelativeLayout) findViewById(R.id.lay_argentina_12);
		Animation animComodoro = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_11);
		layComodoro.startAnimation(animComodoro);

		RelativeLayout layCalafate = (RelativeLayout) findViewById(R.id.lay_argentina_13);
		Animation animCalafate = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_12);
		layCalafate.startAnimation(animCalafate);

		RelativeLayout layRioGallegos = (RelativeLayout) findViewById(R.id.lay_argentina_14);
		Animation animRioGallegos = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_13);
		layRioGallegos.startAnimation(animRioGallegos);

		RelativeLayout layUshuai = (RelativeLayout) findViewById(R.id.lay_argentina_15);
		Animation animUshuai = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_14);
		layUshuai.startAnimation(animUshuai);

		ImageView layAnimArgentina = (ImageView) findViewById(R.id.img_lineas_argentina);
		Animation animLineasArgentina = AnimationUtils.loadAnimation(this,
				R.anim.anim_lineas_argentina);
		layAnimArgentina.startAnimation(animLineasArgentina);
	}

	// ////////////////////////////////////////////////////////////////

	public void animaCiudadesBrasil() {
		RelativeLayout layBuenaVista = (RelativeLayout) findViewById(R.id.lay_brasil_01);
		Animation animBuenaVista = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_01);
		layBuenaVista.startAnimation(animBuenaVista);

		RelativeLayout layMacapa = (RelativeLayout) findViewById(R.id.lay_brasil_02);
		Animation animMacapa = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_02);
		layMacapa.startAnimation(animMacapa);

		RelativeLayout layBelem = (RelativeLayout) findViewById(R.id.lay_brasil_03);
		Animation animBelem = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_03);
		layBelem.startAnimation(animBelem);

		RelativeLayout laySao = (RelativeLayout) findViewById(R.id.lay_brasil_04);
		Animation animSao = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_04);
		laySao.startAnimation(animSao);

		RelativeLayout layManaus = (RelativeLayout) findViewById(R.id.lay_brasil_05);
		Animation animManaus = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_05);
		layManaus.startAnimation(animManaus);

		RelativeLayout laySantarem = (RelativeLayout) findViewById(R.id.lay_brasil_06);
		Animation animSantarem = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_06);
		laySantarem.startAnimation(animSantarem);

		RelativeLayout layMaramba = (RelativeLayout) findViewById(R.id.lay_brasil_07);
		Animation animMaramba = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_07);
		layMaramba.startAnimation(animMaramba);

		RelativeLayout layImperatriz = (RelativeLayout) findViewById(R.id.lay_brasil_08);
		Animation animImperatriz = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_08);
		layImperatriz.startAnimation(animImperatriz);

		RelativeLayout layTeresina = (RelativeLayout) findViewById(R.id.lay_brasil_09);
		Animation animTeresina = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_09);
		layTeresina.startAnimation(animTeresina);

		RelativeLayout layNatal = (RelativeLayout) findViewById(R.id.lay_brasil_10);
		Animation animNatal = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_10);
		layNatal.startAnimation(animNatal);

		RelativeLayout layJoao = (RelativeLayout) findViewById(R.id.lay_brasil_11);
		Animation animJoao = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_11);
		layJoao.startAnimation(animJoao);

		RelativeLayout layRecife = (RelativeLayout) findViewById(R.id.lay_brasil_12);
		Animation animRecife = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_12);
		layRecife.startAnimation(animRecife);

		RelativeLayout layMaceio = (RelativeLayout) findViewById(R.id.lay_brasil_13);
		Animation animMaceio = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_13);
		layMaceio.startAnimation(animMaceio);

		RelativeLayout layRioBranco = (RelativeLayout) findViewById(R.id.lay_brasil_14);
		Animation animRioBranco = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_14);
		layRioBranco.startAnimation(animRioBranco);

		RelativeLayout layPortoVelho = (RelativeLayout) findViewById(R.id.lay_brasil_15);
		Animation animPortoVelho = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_15);
		layPortoVelho.startAnimation(animPortoVelho);

		RelativeLayout layPalmas = (RelativeLayout) findViewById(R.id.lay_brasil_16);
		Animation animPalmas = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_16);
		layPalmas.startAnimation(animPalmas);

		RelativeLayout layAracaju = (RelativeLayout) findViewById(R.id.lay_brasil_17);
		Animation animAracaju = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_17);
		layAracaju.startAnimation(animAracaju);

		RelativeLayout laySalvador = (RelativeLayout) findViewById(R.id.lay_brasil_18);
		Animation animSalvador = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_18);
		laySalvador.startAnimation(animSalvador);

		RelativeLayout layIleus = (RelativeLayout) findViewById(R.id.lay_brasil_19);
		Animation animIleus = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_19);
		layIleus.startAnimation(animIleus);

		RelativeLayout layCuiaba = (RelativeLayout) findViewById(R.id.lay_brasil_20);
		Animation animCuiaba = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_20);
		layCuiaba.startAnimation(animCuiaba);

		RelativeLayout layBrasilia = (RelativeLayout) findViewById(R.id.lay_brasil_21);
		Animation animBrasilia = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_21);
		layBrasilia.startAnimation(animBrasilia);

		RelativeLayout layPortoSeguro = (RelativeLayout) findViewById(R.id.lay_brasil_22);
		Animation animPortoSeguro = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_22);
		layPortoSeguro.startAnimation(animPortoSeguro);

		RelativeLayout layGoiania = (RelativeLayout) findViewById(R.id.lay_brasil_23);
		Animation animGoiania = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_23);
		layGoiania.startAnimation(animGoiania);

		RelativeLayout layUberlandia = (RelativeLayout) findViewById(R.id.lay_brasil_24);
		Animation animUberlandia = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_24);
		layUberlandia.startAnimation(animUberlandia);

		RelativeLayout layBeloHorizonte = (RelativeLayout) findViewById(R.id.lay_brasil_25);
		Animation animBeloHorizonte = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_25);
		layBeloHorizonte.startAnimation(animBeloHorizonte);

		RelativeLayout layCampoGrande = (RelativeLayout) findViewById(R.id.lay_brasil_26);
		Animation animCampoGrande = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_26);
		layCampoGrande.startAnimation(animCampoGrande);

		RelativeLayout layRioPreto = (RelativeLayout) findViewById(R.id.lay_brasil_27);
		Animation animRioPreto = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_27);
		layRioPreto.startAnimation(animRioPreto);

		RelativeLayout layRiberaoPreto = (RelativeLayout) findViewById(R.id.lay_brasil_28);
		Animation animRiberaoPreto = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_28);
		layRiberaoPreto.startAnimation(animRiberaoPreto);

		RelativeLayout layVictoria = (RelativeLayout) findViewById(R.id.lay_brasil_29);
		Animation animVictoria = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_29);
		layVictoria.startAnimation(animVictoria);

		RelativeLayout layCampinas = (RelativeLayout) findViewById(R.id.lay_brasil_40);
		Animation animCampinas = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_30);
		layCampinas.startAnimation(animCampinas);

		RelativeLayout layRioDeJaneiro = (RelativeLayout) findViewById(R.id.lay_brasil_31);
		Animation animRioDeJaniro = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_31);
		layRioDeJaneiro.startAnimation(animRioDeJaniro);

		RelativeLayout layLondrina = (RelativeLayout) findViewById(R.id.lay_brasil_32);
		Animation animLondrina = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_32);
		layLondrina.startAnimation(animLondrina);

		RelativeLayout laySaoPaulo = (RelativeLayout) findViewById(R.id.lay_brasil_33);
		Animation animSaoPaulo = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_33);
		laySaoPaulo.startAnimation(animSaoPaulo);

		RelativeLayout layCuritiva = (RelativeLayout) findViewById(R.id.lay_brasil_34);
		Animation animCuritiva = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_34);
		layCuritiva.startAnimation(animCuritiva);

		RelativeLayout layFoz = (RelativeLayout) findViewById(R.id.lay_brasil_35);
		Animation animFoz = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_35);
		layFoz.startAnimation(animFoz);

		RelativeLayout layJoinville = (RelativeLayout) findViewById(R.id.lay_brasil_36);
		Animation animJoinville = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_36);
		layJoinville.startAnimation(animJoinville);

		RelativeLayout layNavegantes = (RelativeLayout) findViewById(R.id.lay_brasil_37);
		Animation animNavegantes = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_37);
		layNavegantes.startAnimation(animNavegantes);

		RelativeLayout layFlorianopolis = (RelativeLayout) findViewById(R.id.lay_brasil_38);
		Animation animFlorianopolis = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_38);
		layFlorianopolis.startAnimation(animFlorianopolis);

		RelativeLayout layPortoAlegre = (RelativeLayout) findViewById(R.id.lay_brasil_39);
		Animation animPortoAlegre = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_39);
		layPortoAlegre.startAnimation(animPortoAlegre);

		RelativeLayout layFoz2 = (RelativeLayout) findViewById(R.id.lay_brasil_41);
		Animation animFoz2 = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_41);
		layFoz2.startAnimation(animFoz2);

		RelativeLayout layFoz3 = (RelativeLayout) findViewById(R.id.lay_brasil_31);
		Animation animFoz3 = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_41);
		layFoz3.startAnimation(animFoz3);

		ImageView layAnimBrasil = (ImageView) findViewById(R.id.img_lineas_brasil);
		Animation animLineasBrasil = AnimationUtils.loadAnimation(this,
				R.anim.anim_lineas_brasil);
		layAnimBrasil.startAnimation(animLineasBrasil);
	}

	// ////////////////////////////////////////////////////////////////

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

	// ////////////////////////////////////////////////////////////////

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

		ImageView layAnimEcuador = (ImageView) findViewById(R.id.img_lineas_ecuador);
		Animation animLineasEcuador = AnimationUtils.loadAnimation(this,
				R.anim.anim_lineas_ecuador);
		layAnimEcuador.startAnimation(animLineasEcuador);
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

	// ////////////////////////////////////////////////////////////////

	public void cambiaActivity() {
		findViewById(R.id.btn_ir_al_juego).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						mpFondoUno.stop();
						Intent act = new Intent(LaminaUnoActivity.this,
								LaminaDosActivity.class);
						act.putExtra("isInterface", 0);
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
				
				// //////////////////////////////// APARICION DE BOTONES DE FLOTA Y
				// DESTINO ////////////////////////////
				Button animLayFlota = (Button) findViewById(R.id.button_nuestra_flota);
				
				animLayFlota.startAnimation(animFlota);

				
				animLayDes.startAnimation(animDes);

				//animLayLogo.startAnimation(animLogo);
				

				final Handler handler = new Handler();
				handler.postDelayed(new Runnable() {
					@Override
					public void run() {       
						// Do something after 5s = 5000ms
						mpFondoUno.start();
						mpFondoUno.setLooping(true);
						AudioManager audio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
						int currentVolume = audio
								.getStreamVolume(AudioManager.STREAM_MUSIC);
						int maxVolume = audio
								.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
						float percent = 0.4f;
						int seventyVolume = (int) (maxVolume * percent);
						audio.setStreamVolume(AudioManager.STREAM_MUSIC,
								seventyVolume, 0);
						videoLaminaDos.start();
						videoView.setVisibility(View.INVISIBLE);
						imgWhite.setAnimation(animVideoMainOut);
						imgWhite.setVisibility(View.GONE);
						videoView.stopPlayback();
						
					}
				}, 800);

				final Handler handlerDos = new Handler();
				handlerDos.postDelayed(new Runnable() {
					@Override
					public void run() {
						// Do something after 5s = 5000ms
						layZonaZoomable.bringToFront();
						layZonaZoomable.startAnimation(animInInter);
						btnFlota.bringToFront();
						btnDestinos.bringToFront();
					}
				}, 4400);

				final Handler handlerTres = new Handler();
				handlerTres.postDelayed(new Runnable() {
					@Override
					public void run() {
						// Do something after 5s = 5000ms
						videoLaminaDos.setVisibility(View.GONE);
						videoLaminaDos.stopPlayback();
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

	public void reestableceBotones(Button btnActivo) {
		btnInter.setBackgroundResource(R.drawable.boton);
		btnPeru.setBackgroundResource(R.drawable.boton);
		btnBrasil.setBackgroundResource(R.drawable.boton);
		btnEcuador.setBackgroundResource(R.drawable.boton);
		btnColombia.setBackgroundResource(R.drawable.boton);
		btnChile.setBackgroundResource(R.drawable.boton);
		btnArgentina.setBackgroundResource(R.drawable.boton);
		btnActivo.setBackgroundResource(R.drawable.botonrojo);
		btnInter.setEnabled(true);
		btnPeru.setEnabled(true);
		btnBrasil.setEnabled(true);
		btnEcuador.setEnabled(true);
		btnColombia.setEnabled(true);
		btnChile.setEnabled(true);
		btnArgentina.setEnabled(true);
		btnActivo.setEnabled(false);
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
	}

	@Override
	public void onBackPressed() {
		return;
	}

}
