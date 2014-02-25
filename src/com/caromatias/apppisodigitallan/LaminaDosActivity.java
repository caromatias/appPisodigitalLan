package com.caromatias.apppisodigitallan;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.view.Menu;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class LaminaDosActivity extends Activity {

	private ImageView ivAnimacion;
    private AnimationDrawable savingAnimation;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lamina_dos);
		
		
		RelativeLayout layLineaRoja = (RelativeLayout) findViewById(R.id.img_back_barra);
		Animation animLayBarra = AnimationUtils.loadAnimation(this,R.anim.slide_down);
		layLineaRoja.startAnimation(animLayBarra);
		
		RelativeLayout layBackRutas = (RelativeLayout) findViewById(R.id.img_back_rutas);
		Animation animLayRutas = AnimationUtils.loadAnimation(this,R.anim.slide_rutas);
		layBackRutas.startAnimation(animLayRutas);
		
		ivAnimacion = (ImageView)findViewById(R.id.img_elige_ruta);
        ivAnimacion.setBackgroundResource(R.anim.anim_elige_ruta);
        savingAnimation = (AnimationDrawable)ivAnimacion.getBackground();
        savingAnimation.start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lamina_dos, menu);
		return true;
	}

}
