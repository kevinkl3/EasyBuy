package com.duobitsw.easybuy;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

public class VerProducto extends Activity {
	int PRODUCTO_ID;
	
	String Datos[][] = {
			{"Judo de Naranja Sula","Lps. 14.57",""+R.drawable.prod_0},
			{"CocaCola","Lps. 17.99",""+R.drawable.prod_1},
			{"MacBook Pro","$ 500",""+R.drawable.prod_2}
	};
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ver_producto);
		
		Bundle extras = getIntent().getExtras();
		PRODUCTO_ID = extras.getInt("PRODUCTO_ID");
		setDatos();
	}

	private void setDatos(){
		if(PRODUCTO_ID >= Datos.length)return;
		
		Drawable d = getResources().getDrawable(Integer.parseInt(Datos[PRODUCTO_ID][2]) )
				;
		TextView nombre = (TextView) findViewById(R.id.producto_nombre);
		TextView precio = (TextView) findViewById(R.id.precio_val);
		ImageView imagen = (ImageView) findViewById(R.id.prod_img);
		nombre.setText(Datos[PRODUCTO_ID][0]);
		precio.setText(Datos[PRODUCTO_ID][1]);
		imagen.setImageDrawable(d);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ver_producto, menu);
		return true;
	}

}
