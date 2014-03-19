package com.caromatias.apppisodigitallan;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class LaminaBrasilActivity extends Activity {

	private Button btnGoGame;
	private Animation animGoGame;
	private ImageView imgMapaBrasil;
	private Animation animMapaBrasil;
	private Animation animMapaBrasilOut;
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
	private GestureDetector gestureDetector;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lamina_brasil);
		initVars();
		imgMapaBrasil.startAnimation(animMapaBrasil);
		btnGoGame.startAnimation(animGoGame);
		animaCiudadesBrasil();
		initButtons();
		activeButton();
		cambioImagen();
		popUpInfo();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lamina_brasil, menu);
		return true;
	}
	public void initButtons() {
		findViewById(R.id.btn_argentina).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						btnArgentina.setBackgroundResource(R.drawable.botonrojo);
						imgMapaBrasil.startAnimation(animMapaInter);
						btnArgentina.setEnabled(false);
						final Handler handler = new Handler();
						handler.postDelayed(new Runnable() {
							@Override
							public void run() {
								Intent act = new Intent(LaminaBrasilActivity.this,	LaminaArgentinaActivity.class);
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
						imgMapaBrasil.startAnimation(animMapaInter);
						btnBrasil.setEnabled(false);
						final Handler handler = new Handler();
						handler.postDelayed(new Runnable() {
							@Override
							public void run() {
								Intent act = new Intent(LaminaBrasilActivity.this,	LaminaBrasilActivity.class);
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
				imgMapaBrasil.startAnimation(animMapaInter);
				btnChile.setEnabled(false);
				final Handler handler = new Handler();
				handler.postDelayed(new Runnable() {
					@Override
					public void run() {
						Intent act = new Intent(LaminaBrasilActivity.this,	LaminaChileActivity.class);
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
				imgMapaBrasil.startAnimation(animMapaInter);
				btnColombia.setEnabled(false);
				final Handler handler = new Handler();
				handler.postDelayed(new Runnable() {
					@Override
					public void run() {
						Intent act = new Intent(LaminaBrasilActivity.this,	LaminaColombiaActivity.class);
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
				imgMapaBrasil.startAnimation(animMapaInter);
				btnEcuador.setEnabled(false);
				final Handler handler = new Handler();
				handler.postDelayed(new Runnable() {
					@Override
					public void run() {
						Intent act = new Intent(LaminaBrasilActivity.this,	LaminaEcuadorActivity.class);
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
				imgMapaBrasil.startAnimation(animMapaInter);
				btnPeru.setEnabled(false);
				final Handler handler = new Handler();
				handler.postDelayed(new Runnable() {
					@Override
					public void run() {
						Intent act = new Intent(LaminaBrasilActivity.this,	LaminaPeruActivity.class);
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
				imgMapaBrasil.startAnimation(animMapaInter);
				btnInter.setEnabled(false);
				final Handler handler = new Handler();
				handler.postDelayed(new Runnable() {
					@Override
					public void run() {
						Intent act = new Intent(LaminaBrasilActivity.this,LaminaMundialActivity.class);
						startActivity(act);
						overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
					}
				}, 1000);
				btnInter.setEnabled(false);
			}
		});
	}
	
	public void initVars(){
		imgMapaBrasil = (ImageView) findViewById(R.id.mapa_brasil);
		animMapaBrasil = AnimationUtils.loadAnimation(this,R.anim.anim_mapa_in);
		animMapaBrasilOut = AnimationUtils.loadAnimation(this, R.anim.anim_mapa_inter_out);
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
		contenedorBotones = (RelativeLayout) findViewById(R.id.lay_mapa_brasil);
		layPopupInfo = (RelativeLayout) findViewById(R.id.lay_popup_info);
		tituloPopInfo = (TextView) findViewById(R.id.txt_titulo_popup_info);
		descPopInfo = (TextView) findViewById(R.id.txt_descripcion_popup_info);
	}
	public void animaCiudadesBrasil() {
		RelativeLayout layBuenaVista = (RelativeLayout) findViewById(R.id.lay_brasil_01);
		Animation animBuenaVista = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_01);
		layBuenaVista.startAnimation(animBuenaVista);

		RelativeLayout layMacapa = (RelativeLayout) findViewById(R.id.lay_brasil_02);
		Animation animMacapa = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_02);
		layMacapa.startAnimation(animMacapa);

		RelativeLayout layBelem = (RelativeLayout) findViewById(R.id.lay_brasil_03);
		Animation animBelem = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_03);
		layBelem.startAnimation(animBelem);

		RelativeLayout laySao = (RelativeLayout) findViewById(R.id.lay_brasil_04);
		Animation animSao = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_04);
		laySao.startAnimation(animSao);

		RelativeLayout layManaus = (RelativeLayout) findViewById(R.id.lay_brasil_05);
		Animation animManaus = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_05);
		layManaus.startAnimation(animManaus);

		RelativeLayout laySantarem = (RelativeLayout) findViewById(R.id.lay_brasil_06);
		Animation animSantarem = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_06);
		laySantarem.startAnimation(animSantarem);

		RelativeLayout layMaramba = (RelativeLayout) findViewById(R.id.lay_brasil_07);
		Animation animMaramba = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_07);
		layMaramba.startAnimation(animMaramba);

		RelativeLayout layImperatriz = (RelativeLayout) findViewById(R.id.lay_brasil_08);
		Animation animImperatriz = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_08);
		layImperatriz.startAnimation(animImperatriz);

		RelativeLayout layTeresina = (RelativeLayout) findViewById(R.id.lay_brasil_09);
		Animation animTeresina = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_09);
		layTeresina.startAnimation(animTeresina);

		RelativeLayout layNatal = (RelativeLayout) findViewById(R.id.lay_brasil_10);
		Animation animNatal = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_10);
		layNatal.startAnimation(animNatal);

		RelativeLayout layJoao = (RelativeLayout) findViewById(R.id.lay_brasil_11);
		Animation animJoao = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_11);
		layJoao.startAnimation(animJoao);

		RelativeLayout layRecife = (RelativeLayout) findViewById(R.id.lay_brasil_12);
		Animation animRecife = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_12);
		layRecife.startAnimation(animRecife);

		RelativeLayout layMaceio = (RelativeLayout) findViewById(R.id.lay_brasil_13);
		Animation animMaceio = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_13);
		layMaceio.startAnimation(animMaceio);

		RelativeLayout layRioBranco = (RelativeLayout) findViewById(R.id.lay_brasil_14);
		Animation animRioBranco = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_14);
		layRioBranco.startAnimation(animRioBranco);

		RelativeLayout layPortoVelho = (RelativeLayout) findViewById(R.id.lay_brasil_15);
		Animation animPortoVelho = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_15);
		layPortoVelho.startAnimation(animPortoVelho);

		RelativeLayout layPalmas = (RelativeLayout) findViewById(R.id.lay_brasil_16);
		Animation animPalmas = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_16);
		layPalmas.startAnimation(animPalmas);

		RelativeLayout layAracaju = (RelativeLayout) findViewById(R.id.lay_brasil_17);
		Animation animAracaju = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_17);
		layAracaju.startAnimation(animAracaju);

		RelativeLayout laySalvador = (RelativeLayout) findViewById(R.id.lay_brasil_18);
		Animation animSalvador = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_18);
		laySalvador.startAnimation(animSalvador);

		RelativeLayout layIleus = (RelativeLayout) findViewById(R.id.lay_brasil_19);
		Animation animIleus = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_19);
		layIleus.startAnimation(animIleus);

		RelativeLayout layCuiaba = (RelativeLayout) findViewById(R.id.lay_brasil_20);
		Animation animCuiaba = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_20);
		layCuiaba.startAnimation(animCuiaba);

		RelativeLayout layBrasilia = (RelativeLayout) findViewById(R.id.lay_brasil_21);
		Animation animBrasilia = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_21);
		layBrasilia.startAnimation(animBrasilia);

		RelativeLayout layPortoSeguro = (RelativeLayout) findViewById(R.id.lay_brasil_22);
		Animation animPortoSeguro = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_22);
		layPortoSeguro.startAnimation(animPortoSeguro);

		RelativeLayout layGoiania = (RelativeLayout) findViewById(R.id.lay_brasil_23);
		Animation animGoiania = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_23);
		layGoiania.startAnimation(animGoiania);

		RelativeLayout layUberlandia = (RelativeLayout) findViewById(R.id.lay_brasil_24);
		Animation animUberlandia = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_24);
		layUberlandia.startAnimation(animUberlandia);

		RelativeLayout layBeloHorizonte = (RelativeLayout) findViewById(R.id.lay_brasil_25);
		Animation animBeloHorizonte = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_25);
		layBeloHorizonte.startAnimation(animBeloHorizonte);

		RelativeLayout layCampoGrande = (RelativeLayout) findViewById(R.id.lay_brasil_26);
		Animation animCampoGrande = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_26);
		layCampoGrande.startAnimation(animCampoGrande);

		RelativeLayout layRioPreto = (RelativeLayout) findViewById(R.id.lay_brasil_27);
		Animation animRioPreto = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_27);
		layRioPreto.startAnimation(animRioPreto);

		RelativeLayout layRiberaoPreto = (RelativeLayout) findViewById(R.id.lay_brasil_28);
		Animation animRiberaoPreto = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_28);
		layRiberaoPreto.startAnimation(animRiberaoPreto);

		RelativeLayout layVictoria = (RelativeLayout) findViewById(R.id.lay_brasil_29);
		Animation animVictoria = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_29);
		layVictoria.startAnimation(animVictoria);

		RelativeLayout layCampinas = (RelativeLayout) findViewById(R.id.lay_brasil_40);
		Animation animCampinas = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_30);
		layCampinas.startAnimation(animCampinas);

		RelativeLayout layRioDeJaneiro = (RelativeLayout) findViewById(R.id.lay_brasil_31);
		Animation animRioDeJaniro = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_31);
		layRioDeJaneiro.startAnimation(animRioDeJaniro);

		RelativeLayout layLondrina = (RelativeLayout) findViewById(R.id.lay_brasil_32);
		Animation animLondrina = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_32);
		layLondrina.startAnimation(animLondrina);

		RelativeLayout laySaoPaulo = (RelativeLayout) findViewById(R.id.lay_brasil_33);
		Animation animSaoPaulo = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_33);
		laySaoPaulo.startAnimation(animSaoPaulo);

		RelativeLayout layCuritiva = (RelativeLayout) findViewById(R.id.lay_brasil_34);
		Animation animCuritiva = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_34);
		layCuritiva.startAnimation(animCuritiva);

		RelativeLayout layFoz = (RelativeLayout) findViewById(R.id.lay_brasil_35);
		Animation animFoz = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_35);
		layFoz.startAnimation(animFoz);

		RelativeLayout layJoinville = (RelativeLayout) findViewById(R.id.lay_brasil_36);
		Animation animJoinville = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_36);
		layJoinville.startAnimation(animJoinville);

		RelativeLayout layNavegantes = (RelativeLayout) findViewById(R.id.lay_brasil_37);
		Animation animNavegantes = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_37);
		layNavegantes.startAnimation(animNavegantes);

		RelativeLayout layFlorianopolis = (RelativeLayout) findViewById(R.id.lay_brasil_38);
		Animation animFlorianopolis = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_38);
		layFlorianopolis.startAnimation(animFlorianopolis);

		RelativeLayout layPortoAlegre = (RelativeLayout) findViewById(R.id.lay_brasil_39);
		Animation animPortoAlegre = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_39);
		layPortoAlegre.startAnimation(animPortoAlegre);

		RelativeLayout layFoz2 = (RelativeLayout) findViewById(R.id.lay_brasil_41);
		Animation animFoz2 = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_41);
		layFoz2.startAnimation(animFoz2);

		RelativeLayout layFoz3 = (RelativeLayout) findViewById(R.id.lay_brasil_31);
		Animation animFoz3 = AnimationUtils.loadAnimation(this,
				R.anim.anim_botones_41);
		layFoz3.startAnimation(animFoz3);

		ImageView layAnimBrasil = (ImageView) findViewById(R.id.img_lineas_brasil);
		Animation animLineasBrasil = AnimationUtils.loadAnimation(this,
				R.anim.anim_lineas_brasil);
		layAnimBrasil.startAnimation(animLineasBrasil);
	}
	public void activeButton(){
		btnBrasil.setBackgroundResource(R.drawable.botonrojo);
		btnBrasil.setEnabled(false);
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
				imgMapaBrasil.setImageResource(R.drawable.mapa_brasil_dos);      
				contenedorBotones.setVisibility(View.GONE);
				//imgArgentinaView.setVisibility(View.VISIBLE);
			}
		}, 10000);
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

		findViewById(R.id.lay_apretable_brasil).setOnClickListener(
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
