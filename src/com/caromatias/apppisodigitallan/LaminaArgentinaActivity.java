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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lamina_argentina);
		initVars();
		imgMapaArgentina.startAnimation(animMapaArgentina);
		btnGoGame.startAnimation(animGoGame);
		animaCiudadesArgentina();
		initButtons();
		activeButton();
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
						imgMapaArgentina.startAnimation(animMapaInter);
						btnArgentina.setEnabled(false);
						final Handler handler = new Handler();
						handler.postDelayed(new Runnable() {
							@Override
							public void run() {
								Intent act = new Intent(LaminaArgentinaActivity.this,	LaminaArgentinaActivity.class);
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
						imgMapaArgentina.startAnimation(animMapaInter);
						btnBrasil.setEnabled(false);
						final Handler handler = new Handler();
						handler.postDelayed(new Runnable() {
							@Override
							public void run() {
								Intent act = new Intent(LaminaArgentinaActivity.this,	LaminaBrasilActivity.class);
								startActivity(act);
								overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
							}
						}, 1000);
					}
				});
		
		findViewById(R.id.btn_chile).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				btnChile.setBackgroundResource(R.drawable.botonrojo);
				imgMapaArgentina.startAnimation(animMapaInter);
				btnChile.setEnabled(false);
				final Handler handler = new Handler();
				handler.postDelayed(new Runnable() {
					@Override
					public void run() {
						Intent act = new Intent(LaminaArgentinaActivity.this,	LaminaChileActivity.class);
						startActivity(act);
						overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
					}
				}, 1000);
				btnChile.setEnabled(false);
			}
		}); 
		findViewById(R.id.btn_colombia).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				btnColombia.setBackgroundResource(R.drawable.botonrojo);
				imgMapaArgentina.startAnimation(animMapaInter);
				btnColombia.setEnabled(false);
				final Handler handler = new Handler();
				handler.postDelayed(new Runnable() {
					@Override
					public void run() {
						Intent act = new Intent(LaminaArgentinaActivity.this,	LaminaColombiaActivity.class);
						startActivity(act);
						overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
					}
				}, 1000);
				btnColombia.setEnabled(false);  
			}
		});
		findViewById(R.id.btn_ecuador).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				btnEcuador.setBackgroundResource(R.drawable.botonrojo);
				imgMapaArgentina.startAnimation(animMapaInter);
				btnEcuador.setEnabled(false);
				final Handler handler = new Handler();
				handler.postDelayed(new Runnable() {
					@Override
					public void run() {
						Intent act = new Intent(LaminaArgentinaActivity.this,	LaminaEcuadorActivity.class);
						startActivity(act);
						overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
					}
				}, 1000);
				btnEcuador.setEnabled(false);
			}
		});
		findViewById(R.id.btn_peru).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				btnPeru.setBackgroundResource(R.drawable.botonrojo);
				imgMapaArgentina.startAnimation(animMapaInter);
				btnPeru.setEnabled(false);
				final Handler handler = new Handler();
				handler.postDelayed(new Runnable() {
					@Override
					public void run() {
						Intent act = new Intent(LaminaArgentinaActivity.this,	LaminaPeruActivity.class);
						startActivity(act);
						overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
					}
				}, 1000);
				btnPeru.setEnabled(false);
			}
		});
	}
	public void initVars(){
		imgMapaArgentina = (ImageView) findViewById(R.id.mapa_argentina);
		animMapaArgentina = AnimationUtils.loadAnimation(this,R.anim.anim_mapa_in);
		animMapaArgentinaOut = AnimationUtils.loadAnimation(this, R.anim.anim_mapa_inter_out);
		btnGoGame = (Button) findViewById(R.id.btn_ir_al_juego);
		animGoGame = AnimationUtils.loadAnimation(this, R.anim.animacion);
		animMapaInter = AnimationUtils.loadAnimation(this,R.anim.anim_mapa_inter_out);
		btnInter = (Button) findViewById(R.id.btn_internacional);
		btnPeru = (Button) findViewById(R.id.btn_peru);
		btnBrasil = (Button) findViewById(R.id.btn_brasil);
		btnEcuador = (Button) findViewById(R.id.btn_ecuador);
		btnColombia = (Button) findViewById(R.id.btn_colombia);
		btnChile = (Button) findViewById(R.id.btn_chile);
		btnArgentina = (Button) findViewById(R.id.btn_argentina);
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
	public void activeButton(){
		btnArgentina.setBackgroundResource(R.drawable.botonrojo);
		btnArgentina.setEnabled(false);
	}
	@Override
	public void onBackPressed() {
		return;
	}

}
