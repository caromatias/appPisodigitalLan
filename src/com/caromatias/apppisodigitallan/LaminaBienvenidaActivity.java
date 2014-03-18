package com.caromatias.apppisodigitallan;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Context;
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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lamina_bienvenida);
		// INICIALIZACIÓN DE VARIABLES
		initVar();
		// EJECUCIÓN
		exMain();
		initButtons();
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
						// TODO Auto-generated method stub
						btnArgentina.setBackgroundResource(R.drawable.botonrojo);
						imgMapaBienvenida.startAnimation(animMapaInter);
						btnArgentina.setEnabled(false);
						final Handler handler = new Handler();
						handler.postDelayed(new Runnable() {
							@Override
							public void run() {
								Intent act = new Intent(LaminaBienvenidaActivity.this,	LaminaArgentinaActivity.class);
								startActivity(act);
								overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
							}
						}, 1000);
					}
				});
		findViewById(R.id.btn_brasil).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						btnBrasil.setBackgroundResource(R.drawable.botonrojo);
						imgMapaBienvenida.startAnimation(animMapaInter);
						btnBrasil.setEnabled(false);
						final Handler handler = new Handler();
						handler.postDelayed(new Runnable() {
							@Override
							public void run() {
								Intent act = new Intent(LaminaBienvenidaActivity.this,	LaminaBrasilActivity.class);
								startActivity(act);
								overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
							}
						}, 1000);
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
		animInInter = AnimationUtils.loadAnimation(this, R.anim.in_mapa_inter);
		animPanel = AnimationUtils.loadAnimation(this,
				R.anim.anim_lamina_home_market);
		imgMapaBienvenida = (ImageView) findViewById(R.id.mapa_bienvenida);
		imgWhite = (ImageView) findViewById(R.id.img_fade_white);
		layVideoMain = (RelativeLayout) findViewById(R.id.lay_video_main);
		animLayFlota = (Button) findViewById(R.id.button_nuestra_flota);
		animMapaInter = AnimationUtils.loadAnimation(this,R.anim.anim_mapa_inter_out);
		btnFlota = (Button) findViewById(R.id.button_nuestra_flota);
		btnDestinos = (Button) findViewById(R.id.button_nuestros_destinos);
		btnPeru = (Button) findViewById(R.id.btn_peru);
		btnBrasil = (Button) findViewById(R.id.btn_brasil);
		btnEcuador = (Button) findViewById(R.id.btn_ecuador);
		btnColombia = (Button) findViewById(R.id.btn_colombia);
		btnChile = (Button) findViewById(R.id.btn_chile);
		btnArgentina = (Button) findViewById(R.id.btn_argentina);
		//layPopupInfo = (RelativeLayout) findViewById(R.id.lay_popup_info);
	}

	public void exMain() {
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

		botonAnimado.startAnimation(animacion);

		findViewById(R.id.btn_com).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				Button botonAnimado = (Button) findViewById(R.id.btn_com);
				botonAnimado.setBackgroundResource(R.drawable.botoncomenzar);
				imgWhite.setVisibility(View.VISIBLE);
				imgWhite.setAnimation(animVideoMain);
				layVideoMain.setVisibility(View.GONE);

				// //////////////////////////////// APARICION DE BOTONES DE
				// FLOTA Y
				// DESTINO ////////////////////////////

				animLayFlota.startAnimation(animFlota);

				animLayDes.startAnimation(animDes);

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
						imgMapaBienvenida.bringToFront();
						imgMapaBienvenida.startAnimation(animInInter);
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
}