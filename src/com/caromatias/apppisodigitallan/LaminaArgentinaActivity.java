package com.caromatias.apppisodigitallan;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lamina_argentina);
		initVars();
		imgMapaArgentina.startAnimation(animMapaArgentina);
		btnGoGame.startAnimation(animGoGame);
		animaCiudadesArgentina();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lamina_argentina, menu);
		return true;
	}
	public void initVars(){
		imgMapaArgentina = (ImageView) findViewById(R.id.mapa_argentina);
		animMapaArgentina = AnimationUtils.loadAnimation(this,R.anim.anim_mapa_in);
		animMapaArgentinaOut = AnimationUtils.loadAnimation(this, R.anim.anim_mapa_inter_out);
		btnGoGame = (Button) findViewById(R.id.btn_ir_al_juego);
		animGoGame = AnimationUtils.loadAnimation(this, R.anim.animacion);
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

}
