package com.caromatias.apppisodigitallan;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class LaminaChileActivity extends Activity {

	private Button btnGoGame;
	private Animation animGoGame;
	private ImageView imgMapaChile;
	private Animation animMapaChile;
	private Animation animMapaChileOut;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lamina_chile);
		initVars();
		imgMapaChile.startAnimation(animMapaChile);
		btnGoGame.startAnimation(animGoGame);
		animaCiudadesChile();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lamina_chile, menu);
		return true;
	}

	public void initVars(){
		imgMapaChile = (ImageView) findViewById(R.id.mapa_chile);
		animMapaChile = AnimationUtils.loadAnimation(this,R.anim.anim_mapa_in);
		animMapaChileOut = AnimationUtils.loadAnimation(this, R.anim.anim_mapa_inter_out);
		btnGoGame = (Button) findViewById(R.id.btn_ir_al_juego);
		animGoGame = AnimationUtils.loadAnimation(this, R.anim.animacion);
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
}
