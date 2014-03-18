package com.caromatias.apppisodigitallan;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class LaminaEcuadorActivity extends Activity {

	private Button btnGoGame;
	private Animation animGoGame;
	private ImageView imgMapaEcuador;
	private Animation animMapaEcuador;
	private Animation animMapaEcuadorOut;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lamina_ecuador);
		initVars();
		imgMapaEcuador.startAnimation(animMapaEcuador);
		btnGoGame.startAnimation(animGoGame);
		animaCiudadesEcuador();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lamina_ecuador, menu);
		return true;
	}

	public void initVars(){
		imgMapaEcuador = (ImageView) findViewById(R.id.mapa_ecuador);
		animMapaEcuador = AnimationUtils.loadAnimation(this,R.anim.anim_mapa_in);
		animMapaEcuadorOut = AnimationUtils.loadAnimation(this, R.anim.anim_mapa_inter_out);
		btnGoGame = (Button) findViewById(R.id.btn_ir_al_juego);
		animGoGame = AnimationUtils.loadAnimation(this, R.anim.animacion);
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

		ImageView layAnimEcuador = (ImageView) findViewById(R.id.img_lineas_ecuador);
		Animation animLineasEcuador = AnimationUtils.loadAnimation(this,
				R.anim.anim_lineas_ecuador);
		layAnimEcuador.startAnimation(animLineasEcuador);
	}
}
