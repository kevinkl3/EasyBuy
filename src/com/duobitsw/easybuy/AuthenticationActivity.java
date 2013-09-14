package com.duobitsw.easybuy;

import java.util.List;

import android.os.Bundle;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AuthenticationActivity extends Activity {
	private static String[] DUMMY_CREDENTIALS;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_authentication);
		Button btn_signIn = (Button) findViewById(R.id.sign_in_button);
		getCredentials();

		btn_signIn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (getCredentials()) {
					EditText email = (EditText) findViewById(R.id.email);
					EditText password = (EditText) findViewById(R.id.password);
					if (email.getText().toString().isEmpty()
							|| password.getText().toString().isEmpty()) {

					} else {
						if (logIn(email.getText().toString(), password
								.getText().toString())) {
							Intent i = new Intent(getApplicationContext(),ClienteActivity.class);														
							List<List<String>> usuarios = db.Query("USERS",null,null,null);							
							List<List<String>> _user = db.Query("USERS", "id", null, new String[]{usuarios.get(usuarios.size()-1).get(0).toString()});
							
							i.putExtra("USER", _user.get(0).get(0));
							ContentValues vals = new ContentValues();
							vals.put("STATE", "1");
							vals.put("MAIL", email.getText().toString());
							db.InsertCreate("LOGON", vals);
							startActivity(i);
						} else {
							showToast("Error, el correo electrónico o contraseña ingresada no son conrrectos");
						}
					}
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.authentication, menu);
		return true;
	}

	final DatabaseHandler db = new DatabaseHandler(this);

	public boolean getCredentials() {
		DatabaseHandler db = new DatabaseHandler(this);
		List<List<String>> users = db.Query("USERS", null, null, null);
		if (users.size() != 0) {
			this.DUMMY_CREDENTIALS = new String[users.size()];
			for (int i = 0; i < users.size(); i++) {
				String userName = users.get(i).get(3);
				String pass = users.get(i).get(6);
				this.DUMMY_CREDENTIALS[i] = userName + ":" + pass;				
				return true;
			}
		} else {
			System.out.println("No hay usuarios registrados");
		}
		return false;
	}

	public boolean logIn(String email, String password) {

		for (int i = 0; i < DUMMY_CREDENTIALS.length; i++) {
			String emailTo = DUMMY_CREDENTIALS[i].split(":")[0];
			if (emailTo.equals(email)) {
				return (DUMMY_CREDENTIALS[i].split(":")[1].equals(password)) ? true
						: false;
			}
		}
		return false;
	}
	
	public void showToast(String msj){
		Toast.makeText(getApplicationContext(), 
                msj, Toast.LENGTH_LONG).show();
	}

}
