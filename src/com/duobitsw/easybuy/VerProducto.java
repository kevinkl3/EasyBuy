package com.duobitsw.easybuy;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class VerProducto extends Activity {
	int PRODUCTO_ID;
	
	String Datos[][] = {
			{"CocaCola","Lps. 17.99"},
			{"Judo de Naranja Sula","Lps. 14.57"}
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
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ver_producto, menu);
		return true;
	}

}
