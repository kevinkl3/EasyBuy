package com.duobitsw.easybuy;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
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
		 
	       		
	       		
		Button bt1 = (Button)findViewById(R.id.Carrito);
		bt1.setTextColor(Color.parseColor("#F4D6BC")); 
		bt1.setBackgroundResource(R.drawable.botoncito_bonito);
		
		Button bt2 = (Button)findViewById(R.id.RecienteC);
		bt2.setTextColor(Color.parseColor("#F4D6BC"));
		bt2.setBackgroundResource(R.drawable.botoncito_bonito);
		
		Button bt3 = (Button)findViewById(R.id.Shop);
		bt3.setTextColor(Color.parseColor("#F4D6BC"));
		bt3.setBackgroundResource(R.drawable.botoncito_bonito);
		
		
		//ImageButton bt4= (ImageButton)findViewById(R.id.Imagen);
		//bt4.setBackgroundResource(R.drawable.botoncito_bonito);
	}
	
	
	//////////
	 @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    	
		 
	    	  /*
	    	 
	    	  Se revisa si la imagen viene de la cámara (TAKE_PICTURE) o de la galería (SELECT_PICTURE)
	    	 
	    	if (requestCode == TAKE_PICTURE) {
	    		/**
	    		 * Si se reciben datos en el intent tenemos una vista previa (thumbnail)
	    		 //
	    		if (data != null) {
	    			/**
	    			 * En el caso de una vista previa, obtenemos el extra “data” del intent y 
	    			 * lo mostramos en el ImageView
	    			 
	    			if (data.hasExtra("data")) { 
	    				ImageView iv = (ImageView)findViewById(R.id.imgView);
	    				iv.setImageBitmap((Bitmap) data.getParcelableExtra("data"));
	    			}
	    		/**
	    		 * De lo contrario es una imagen completa
	    		   			
	    		} else {
	    			/**
	    			 * A partir del nombre del archivo ya definido lo buscamos y creamos el bitmap
	    			 * para el ImageView
	    			 
	    			ImageView iv = (ImageView)findViewById(R.id.imgView);
	    			iv.setImageBitmap(BitmapFactory.decodeFile(name));
	    			/**
	    			 * Para guardar la imagen en la galería, utilizamos una conexión a un MediaScanner
	    			 
	    			new MediaScannerConnectionClient() {
	    				private MediaScannerConnection msc = null; {
	    					msc = new MediaScannerConnection(getApplicationContext(), this); msc.connect();
	    				}
	    				public void onMediaScannerConnected() { 
	    					msc.scanFile(name, null);
	    				}
	    				public void onScanCompleted(String path, Uri uri) { 
	    					msc.disconnect();
	    				} 
	    			};				
	    		}
	    	/**
	    	 * Recibimos el URI de la imagen y construimos un Bitmap a partir de un stream de Bytes
	    	 
	    	} else
	    		
	    		*/
	    		
	    		if (requestCode == SELECT_PICTURE){
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

	//////////

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cliente, menu);
		return true;
	}

}
