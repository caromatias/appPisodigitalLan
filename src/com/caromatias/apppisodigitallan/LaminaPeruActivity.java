package com.caromatias.apppisodigitallan;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class LaminaPeruActivity extends Activity {
	
	private Button btnGoGame;
	private Animation animGoGame;
	private ImageView imgMapaPeru;
	private Animation animMapaPeru;
	private Animation animMapaPeruOut;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lamina_peru);
		initVars();
		imgMapaPeru.startAnimation(animMapaPeru);
		btnGoGame.startAnimation(animGoGame);
		animaCiudadesPeru();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lamina_peru, menu);
		return true;
	}
	
	public void initVars(){
		imgMapaPeru = (ImageView) findViewById(R.id.mapa_peru);
		animMapaPeru = AnimationUtils.loadAnimation(this,R.anim.anim_mapa_in);
		animMapaPeruOut = AnimationUtils.loadAnimation(this, R.anim.anim_mapa_inter_out);
		btnGoGame = (Button) findViewById(R.id.btn_ir_al_juego);
		animGoGame = AnimationUtils.loadAnimation(this, R.anim.animacion);
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

}
