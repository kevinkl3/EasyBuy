package com.duobitsw.easybuy;

import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		DatabaseHandler db = new DatabaseHandler(this);
		Button login_btn = (Button) findViewById(R.id.btn_login);
		Button register_btn = (Button) findViewById(R.id.btn_registro);
		
		List<List<String>> qre = db.Query("LOGON", null, null, null);
		if (qre.size() != 0) {
			if (qre.get(qre.size() - 1).get(0).toString().equals("1")) {
				Intent i = new Intent(getApplicationContext(),
						ClienteActivity.class);
				List<List<String>> usuarios = db.Query("USERS",null,null,null);							
				List<List<String>> _user = db.Query("USERS", "EMAIL", null, new String[]{qre.get(qre.size() - 1).get(1)});
				
				i.putExtra("USER", _user.get(0).get(0));
				startActivity(i);
				
			} else {			
				login_btn.setOnClickListener(new OnClickListener() {
					public void onClick(View v) {
						Intent i = new Intent(getApplicationContext(),
								AuthenticationActivity.class);
						startActivity(i);
					}

				});

				register_btn.setOnClickListener(new OnClickListener() {
					public void onClick(View v) {
						Intent i = new Intent(getApplicationContext(),
								RegisterActivity.class);
						startActivity(i);
					}
				});
			}
		} else {

			login_btn.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					Intent i = new Intent(getApplicationContext(),
							AuthenticationActivity.class);
					startActivity(i);
				}

			});

			register_btn.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					Intent i = new Intent(getApplicationContext(),
							RegisterActivity.class);
					startActivity(i);
				}
			});
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
