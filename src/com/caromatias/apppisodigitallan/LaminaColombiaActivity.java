package com.caromatias.apppisodigitallan;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class LaminaColombiaActivity extends Activity {
	
	private Button btnGoGame;
	private Animation animGoGame;
	private ImageView imgMapaColombia;
	private Animation animMapaColombia;
	private Animation animMapaColombiaOut;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lamina_colombia);
		initVars();
		imgMapaColombia.startAnimation(animMapaColombia);
		btnGoGame.startAnimation(animGoGame);
		animaCiudadesColombia();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lamina_colombia, menu);
		return true;
	}
	public void initVars(){
		imgMapaColombia = (ImageView) findViewById(R.id.mapa_colombia);
		animMapaColombia = AnimationUtils.loadAnimation(this,R.anim.anim_mapa_in);
		animMapaColombiaOut = AnimationUtils.loadAnimation(this, R.anim.anim_mapa_inter_out);
		btnGoGame = (Button) findViewById(R.id.btn_ir_al_juego);
		animGoGame = AnimationUtils.loadAnimation(this, R.anim.animacion);
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

}
