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
import android.widget.TextView;

public class LaminaChileActivity extends Activity {

	private Button btnGoGame;
	private Animation animGoGame;
	private ImageView imgMapaChile;
	private Animation animMapaChile;
	private Animation animMapaChileOut;
	private Button btnInter;
	private Button btnPeru;
	private Button btnBrasil;
	private Button btnEcuador;
	private Button btnColombia;
	private Button btnChile;
	private Button btnArgentina;
	private Animation animMapaInter;
	private RelativeLayout contenedorBotones;
	private RelativeLayout layPopupInfo;
	private boolean doubleClick = false;
	private TextView tituloPopInfo;
	private TextView descPopInfo;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lamina_chile);
		initVars();
		imgMapaChile.startAnimation(animMapaChile);
		btnGoGame.startAnimation(animGoGame);
		animaCiudadesChile();
		initButtons();
		activeButton();
		cambioImagen();
		popUpInfo();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lamina_chile, menu);
		return true;
	}
	public void initButtons() {
		findViewById(R.id.btn_argentina).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						btnArgentina.setBackgroundResource(R.drawable.botonrojo);
						imgMapaChile.startAnimation(animMapaInter);
						btnArgentina.setEnabled(false);
						final Handler handler = new Handler();
						handler.postDelayed(new Runnable() {
							@Override
							public void run() {
								Intent act = new Intent(LaminaChileActivity.this,	LaminaArgentinaActivity.class);
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
						imgMapaChile.startAnimation(animMapaInter);
						btnBrasil.setEnabled(false);
						final Handler handler = new Handler();
						handler.postDelayed(new Runnable() {
							@Override
							public void run() {
								Intent act = new Intent(LaminaChileActivity.this,	LaminaBrasilActivity.class);
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
				imgMapaChile.startAnimation(animMapaInter);
				btnChile.setEnabled(false);
				final Handler handler = new Handler();
				handler.postDelayed(new Runnable() {
					@Override
					public void run() {
						Intent act = new Intent(LaminaChileActivity.this,	LaminaChileActivity.class);
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
				imgMapaChile.startAnimation(animMapaInter);
				btnColombia.setEnabled(false);
				final Handler handler = new Handler();
				handler.postDelayed(new Runnable() {
					@Override
					public void run() {
						Intent act = new Intent(LaminaChileActivity.this,	LaminaColombiaActivity.class);
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
				imgMapaChile.startAnimation(animMapaInter);
				btnEcuador.setEnabled(false);
				final Handler handler = new Handler();
				handler.postDelayed(new Runnable() {
					@Override
					public void run() {
						Intent act = new Intent(LaminaChileActivity.this,	LaminaEcuadorActivity.class);
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
				imgMapaChile.startAnimation(animMapaInter);
				btnPeru.setEnabled(false);
				final Handler handler = new Handler();
				handler.postDelayed(new Runnable() {
					@Override
					public void run() {
						Intent act = new Intent(LaminaChileActivity.this,	LaminaPeruActivity.class);
						startActivity(act);
						overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
					}
				}, 1000);
				btnPeru.setEnabled(false);
			}
		});
		findViewById(R.id.btn_internacional).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				btnInter.setBackgroundResource(R.drawable.botonrojo);
				imgMapaChile.startAnimation(animMapaInter);
				btnInter.setEnabled(false);
				final Handler handler = new Handler();
				handler.postDelayed(new Runnable() {
					@Override
					public void run() {
						Intent act = new Intent(LaminaChileActivity.this,LaminaMundialActivity.class);
						startActivity(act);
						overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
					}
				}, 1000);
				btnInter.setEnabled(false);
			}
		});
	}

	public void initVars(){
		imgMapaChile = (ImageView) findViewById(R.id.mapa_chile);
		animMapaChile = AnimationUtils.loadAnimation(this,R.anim.anim_mapa_in);
		animMapaChileOut = AnimationUtils.loadAnimation(this, R.anim.anim_mapa_inter_out);
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
		contenedorBotones = (RelativeLayout) findViewById(R.id.lay_mapa_chile);
		layPopupInfo = (RelativeLayout) findViewById(R.id.lay_popup_info);
		tituloPopInfo = (TextView) findViewById(R.id.txt_titulo_popup_info);
		descPopInfo = (TextView) findViewById(R.id.txt_descripcion_popup_info);
	}
	public void animaCiudadesChile() {
		RelativeLayout layArica = (RelativeLayout) findViewById(R.id.lay_chile_01);
		Animation animArica = AnimationUtils.loadAnimation(this,R.anim.anim_botones_01);
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
	public void activeButton(){
		btnChile.setBackgroundResource(R.drawable.botonrojo);
		btnChile.setEnabled(false);
	}
	@Override
	public void onBackPressed() {
		return;
	}
	public void cambioImagen(){
		final Handler handlerTres = new Handler();
		handlerTres.postDelayed(new Runnable() {
			@Override
			public void run() {
				// Do something after 5s = 5000ms
				//imgMapaArgentina.setVisibility(View.GONE);
				imgMapaChile.setImageResource(R.drawable.mapa_chile_dos);      
				contenedorBotones.setVisibility(View.GONE);
				//imgArgentinaView.setVisibility(View.VISIBLE);
			}
		}, 7000);
	}
	public void popUpInfo() {
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
						if (doubleClick == true) {
							tituloPopInfo.setText(getResources().getString(
									R.string.titulo_pop_info1));
							descPopInfo.setText(getResources().getString(
									R.string.desc_pop_info1));
							layPopupInfo.bringToFront();
							layPopupInfo.setVisibility(View.VISIBLE);
						} else {
							doubleClick = true;
							Handler handlerDoubleClickUno = new Handler();
							handlerDoubleClickUno.postDelayed(new Runnable() {
								@Override
								public void run() {
									doubleClick = false;
								}
							}, 600);
						}
					}
				});
	}
}