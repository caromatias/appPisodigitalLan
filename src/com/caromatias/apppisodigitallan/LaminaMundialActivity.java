package com.caromatias.apppisodigitallan;

import pl.polidea.view.ZoomView;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class LaminaMundialActivity extends Activity {

	private ZoomView zoomView;
	private RelativeLayout lay_principal;
	private Button btnGoGame;
	private Animation animGoGame;
	private Animation animMapaInterOut;
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
		setContentView(R.layout.activity_lamina_mundial);
		
		final View v = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE))
				.inflate(R.layout.zoomable_view_mundial, null, false);
		v.setLayoutParams(new LinearLayout.LayoutParams(
				android.view.ViewGroup.LayoutParams.FILL_PARENT,
				android.view.ViewGroup.LayoutParams.FILL_PARENT));

		zoomView = new ZoomView(this);
		zoomView.addView(v);
		lay_principal = (RelativeLayout) findViewById(R.id.zona_zoomable_mundial);
		lay_principal.addView(zoomView);
		
		animBotonesMapaInternacional();
		initButtons();
		initVars();
		activeButton();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lamina_mundial, menu);
		return true; 
	}
	public void initButtons() {
		findViewById(R.id.btn_argentina).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						btnArgentina.setBackgroundResource(R.drawable.botonrojo);
						lay_principal.startAnimation(animMapaInterOut);
						btnArgentina.setEnabled(false);
						final Handler handler = new Handler();
						handler.postDelayed(new Runnable() {
							@Override
							public void run() {
								Intent act = new Intent(LaminaMundialActivity.this,	LaminaArgentinaActivity.class);
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
						lay_principal.startAnimation(animMapaInterOut);
						btnBrasil.setEnabled(false);
						final Handler handler = new Handler();
						handler.postDelayed(new Runnable() {
							@Override
							public void run() {
								Intent act = new Intent(LaminaMundialActivity.this,	LaminaBrasilActivity.class);
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
				lay_principal.startAnimation(animMapaInterOut);
				btnChile.setEnabled(false);
				final Handler handler = new Handler();
				handler.postDelayed(new Runnable() {
					@Override
					public void run() {
						Intent act = new Intent(LaminaMundialActivity.this,	LaminaChileActivity.class);
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
				lay_principal.startAnimation(animMapaInterOut);
				btnColombia.setEnabled(false);
				final Handler handler = new Handler();
				handler.postDelayed(new Runnable() {
					@Override
					public void run() {
						Intent act = new Intent(LaminaMundialActivity.this,	LaminaColombiaActivity.class);
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
				lay_principal.startAnimation(animMapaInterOut);
				btnEcuador.setEnabled(false);
				final Handler handler = new Handler();
				handler.postDelayed(new Runnable() {
					@Override
					public void run() {
						Intent act = new Intent(LaminaMundialActivity.this,	LaminaEcuadorActivity.class);
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
				lay_principal.startAnimation(animMapaInterOut);
				btnPeru.setEnabled(false);
				final Handler handler = new Handler();
				handler.postDelayed(new Runnable() {
					@Override
					public void run() {
						Intent act = new Intent(LaminaMundialActivity.this,	LaminaPeruActivity.class);
						startActivity(act);
						overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
					}
				}, 1000);
				btnPeru.setEnabled(false);
			}
		});
	}
	public void initVars(){
		animMapaInter = AnimationUtils.loadAnimation(this,R.anim.anim_mapa_in);
		animMapaInterOut = AnimationUtils.loadAnimation(this, R.anim.anim_mapa_inter_out);
		btnGoGame = (Button) findViewById(R.id.btn_ir_al_juego);
		animGoGame = AnimationUtils.loadAnimation(this, R.anim.animacion);
		btnInter = (Button) findViewById(R.id.btn_internacional);
		btnPeru = (Button) findViewById(R.id.btn_peru);
		btnBrasil = (Button) findViewById(R.id.btn_brasil);
		btnEcuador = (Button) findViewById(R.id.btn_ecuador);
		btnColombia = (Button) findViewById(R.id.btn_colombia);
		btnChile = (Button) findViewById(R.id.btn_chile);
		btnArgentina = (Button) findViewById(R.id.btn_argentina);
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
	public void activeButton(){
		btnInter.setBackgroundResource(R.drawable.botonrojo);
		btnInter.setEnabled(false);
	}
}
