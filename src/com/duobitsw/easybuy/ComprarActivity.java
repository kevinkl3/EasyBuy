package com.duobitsw.easybuy;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ComprarActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_comprar);
		
		Button boton_escanear = (Button) findViewById(R.id.btn_qrscan);
		boton_escanear.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent("com.google.zxing.client.android.SCAN");
				intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
				startActivityForResult(intent, 0);
			}
			
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.comprar, menu);
		return true;
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		 if (requestCode == 0) {
		      if (resultCode == RESULT_OK) {
		         String contents = data.getStringExtra("SCAN_RESULT");
		         String format = data.getStringExtra("SCAN_RESULT_FORMAT");
		         // Handle successful scan
		         try{
		        	Log.d("QRCODE",contents);
		        	int id = Integer.parseInt(contents);
		         	
		        	Intent abrirProducto = new Intent(ComprarActivity.this,VerProducto.class);
		         	abrirProducto.putExtra("PRODUCTO_ID", id);
		         	
		         	startActivity(abrirProducto);
		         	
		         }catch(NumberFormatException e){
		        	 Log.d("QRCODE",e.getMessage());
		         }
		      } else if (resultCode == RESULT_CANCELED) {
		         // Handle cancel
		      }
		   }
	}
	
}
