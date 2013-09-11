package com.duobitsw.easybuy;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class RegisterActivity extends Activity {

	private static int SELECT_PICTURE = 2;
	Bitmap img;
	String profilepic;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		final DatabaseHandler db = new DatabaseHandler(this);

		Button aceptar_btn = (Button) findViewById(R.id.button2);
		Button elegirFoto_btn = (Button) findViewById(R.id.button1);

		aceptar_btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				final EditText username = (EditText) findViewById(R.id.UserNameText);
				final EditText email = (EditText) findViewById(R.id.mailText);
				final EditText phonenumber = (EditText) findViewById(R.id.numberText);
				final EditText dirpostal = (EditText) findViewById(R.id.dirpostalText);
				final EditText password = (EditText) findViewById(R.id.passText);
				ContentValues values = new ContentValues();
				values.put("USERNAME", username.getText().toString());
				call(username.getText().toString(), img);
				profilepic = "/data/data/com.duobitsw.easybuy/app_Imagenes/"
						+ username.getText().toString() + ".jpg";
				values.put("PROFILEPIC", profilepic);
				values.put("EMAIL", email.getText().toString());
				values.put("PHONENUMBER", phonenumber.getText().toString());
				values.put("ADDRESS", dirpostal.getText().toString());
				values.put("PASS", password.getText().toString());
				db.InsertCreate("USERS", values);
				
				//List<List<String>> X = db.Query("USERS",null,null,null);				
			}

		});

		elegirFoto_btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				intent = new Intent(
						Intent.ACTION_PICK,
						android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
				int code = SELECT_PICTURE;
				startActivityForResult(intent, code);
			}
		});

	}

	public void call(String username, Bitmap img) {
		guardarImagen(this, username, img);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.register, menu);
		return true;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == SELECT_PICTURE && resultCode == RESULT_OK) {
			Uri selectedImage = data.getData();
			InputStream is;
			try {
				is = getContentResolver().openInputStream(selectedImage);
				BufferedInputStream bis = new BufferedInputStream(is);
				Bitmap bitmap = BitmapFactory.decodeStream(bis);
				ImageView iv = (ImageView) findViewById(R.id.imgView);
				// iv.setImageBitmap(bitmap);
				this.img = bitmap;
			} catch (FileNotFoundException e) {
			}
		}
	}

	private static String guardarImagen(Context Context, String username,
			Bitmap imagen) {
		ContextWrapper cw = new ContextWrapper(Context);
		File dirImages = cw.getDir("Imagenes", Context.MODE_PRIVATE);
		File myPath = new File(dirImages, username + ".png");

		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(myPath);
			imagen.compress(Bitmap.CompressFormat.JPEG, 100, fos);
			fos.flush();
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return myPath.getAbsolutePath();
	}

}
