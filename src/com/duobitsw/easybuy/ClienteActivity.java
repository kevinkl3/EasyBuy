package com.duobitsw.easybuy;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import com.google.zxing.client.android.CaptureActivity;
import com.google.zxing.client.android.*;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;

public class ClienteActivity extends Activity {
	private static int TAKE_PICTURE = 1;
	private static int SELECT_PICTURE = 2;	
	private String name = "";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cliente);
		 name = Environment.getExternalStorageDirectory() + "/test.jpg";
		 
		 Button btnAction = (Button)findViewById(R.id.btnpic);
			btnAction.setTextColor(Color.parseColor("#F4D6BC"));
			btnAction.setBackgroundResource(R.drawable.botoncito_bonito);
		 btnAction.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent =  new Intent(MediaStore.ACTION_IMAGE_CAPTURE); 
       			int code = 2;//TAKE_PICTURE;
       			///////////////
       			intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
   				code = SELECT_PICTURE;
       			
       			///////////////
   				startActivityForResult(intent, code);
			}
			
		});      
		 
		 ////////////
		 
		 
		 
		 
		 /////////////////
		 
	       		
	       		
		Button btn_carrito = (Button)findViewById(R.id.Carrito);
		btn_carrito.setTextColor(Color.parseColor("#F4D6BC")); 
		btn_carrito.setBackgroundResource(R.drawable.botoncito_bonito);
		
		Button btn_reciente = (Button)findViewById(R.id.RecienteC);
		btn_reciente.setTextColor(Color.parseColor("#F4D6BC"));
		btn_reciente.setBackgroundResource(R.drawable.botoncito_bonito);
		
		Button btn_shop = (Button)findViewById(R.id.Shop);
		btn_shop.setTextColor(Color.parseColor("#F4D6BC"));
		btn_shop.setBackgroundResource(R.drawable.botoncito_bonito);
		
		btn_shop.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				//Intent intent = new Intent(getApplicationContext(), CaptureActivity.class);
				//startActivity(intent);
				
				Intent intent = new Intent("com.google.zxing.client.android.SCAN");
				intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
				startActivityForResult(intent, 0);
				
			}	
		});
		
		
		//ImageButton bt4= (ImageButton)findViewById(R.id.Imagen);
		//bt4.setBackgroundResource(R.drawable.botoncito_bonito);
	}
	
	
	//////////
	 @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
			 if (requestCode == 0) {
			      if (resultCode == RESULT_OK) {
			         String contents = data.getStringExtra("SCAN_RESULT");
			         String format = data.getStringExtra("SCAN_RESULT_FORMAT");
			         // Handle successful scan
			         Log.d("QRCODE",contents);
			      } else if (resultCode == RESULT_CANCELED) {
			         // Handle cancel
			      }
			   }
	    		
	    	if (requestCode == SELECT_PICTURE){
	    		 if (resultCode == RESULT_OK) {
		    		Uri selectedImage = data.getData();
		    		InputStream is;
		    		try {
		    			is = getContentResolver().openInputStream(selectedImage);
		    	    	BufferedInputStream bis = new BufferedInputStream(is);
		    	    	Bitmap bitmap = BitmapFactory.decodeStream(bis);            
		    	    	ImageView iv = (ImageView)findViewById(R.id.imgView);
		    	    	iv.setImageBitmap(bitmap);						
		    		} catch (FileNotFoundException e) {}
	    		}
	    	}
	    }

	//////////

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cliente, menu);
		return true;
	}

}
