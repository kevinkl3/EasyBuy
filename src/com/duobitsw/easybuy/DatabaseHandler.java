package com.duobitsw.easybuy;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {

	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "EasyBuyDB";	
	//Table name
	private static final String TABLE_USERS = "USERS";
	private static final String TABLE_STORES = "STORES";
	private static final String TABLE_PRODUCTOS = "products";

	// Contacts Users Columns names
	private static final String KEY_ID = "id";
	private static final String KEY_USERNAME = "USERNAME";
	private static final String KEY_PIC = "PROFILEPIC";
	private static final String KEY_EMAIL = "EMAIL";
	private static final String KEY_PH_NO = "PHONENUMBER";
	private static final String KEY_POSTALADDRESS = "ADDRESS";
	private static final String KEY_PASS = "PASS";

	// Stores Table Columns Names
	private static final String KEY_ID_S = "id";
	private static final String KEY_NAME_S = "storeName";
	private static final String KEY_DESCRIPTION_S = "storeDescription";	
	private static final String KEY_ADDRESS_S = "Address";
	private static final String KEY_MAINPIC_S = "storemainpic";
	
	//Products Table Columns Names
	
	//Log Table Columns Names
	private static final String KEY_STATE = "STATE";
	private static final String KEY_MAIL_LOGEADO = "MAIL";

	String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USERS + "(" + KEY_ID
			+ " INTEGER PRIMARY KEY, " + KEY_USERNAME + " TEXT," + KEY_PIC  +" TEXT," + KEY_EMAIL
			+ " TEXT," + KEY_PH_NO + " TEXT, " + KEY_POSTALADDRESS + " TEXT,"
			+ KEY_PASS + " TEXT" + ")";
	
	String CREATE_STORES_TABLE = "CREATE TABLE "+TABLE_STORES + " ( " + KEY_ID_S 
			+ " INTEGER PRIMARY KEY," + KEY_NAME_S +" TEXT,"+ KEY_DESCRIPTION_S
			+ "TEXT, " + KEY_ADDRESS_S + " TEXT," + KEY_MAINPIC_S + " TEXT)";
	
	 String CREATE_LOGON_TABLE = "CREATE TABLE " + "LOGON" + "(" + KEY_STATE + " TEXT," + KEY_MAIL_LOGEADO +" TEXT)";


	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {		
		db.execSQL(CREATE_USER_TABLE);		
		db.execSQL(CREATE_LOGON_TABLE);
	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {		
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);		
		onCreate(db);
	}
	
	/**
	 * All CRUD(Create, Read, Update, Delete) Operations
	 */
	public boolean InsertCreate(String tableName, ContentValues values ){		
		SQLiteDatabase db = this.getWritableDatabase();	
		if (values.size()!=0){							
			db.insert(tableName, null, values);			
			db.close();			
			return true;			
		}		
		db.close();
		return false;
	}
		
	public List<List<String>> Query(String tableName,String filtro ,String[] campos, String[] args) {
		SQLiteDatabase db = this.getReadableDatabase();
		List<List<String>> retorno = new ArrayList<List<String>>();
		Cursor cursor;
		if(filtro!=null){
			cursor = db.query(tableName, campos, filtro + "=?", args, null,null, null);
		}else{
			cursor = db.query(tableName, campos,null, null, null,null, null);
		}	

		if (cursor.moveToFirst()) {
			do {
				List<String> tupla = new ArrayList<String>();
				for (int i = 0; i < cursor.getColumnCount(); i++)
					tupla.add(cursor.getString(i));
				retorno.add(tupla);
			} while (cursor.moveToNext());
		}
		db.close();
		return retorno;
	}

//	public List<List<String>> AllUsers() {
//		SQLiteDatabase db = this.getReadableDatabase();		
//		List<List<String>> retorno = new ArrayList<List<String>>();					
//		Cursor cursor = db.rawQuery("SELECT * FROM USERS", null);
//		
//		if (cursor.moveToFirst()) {
//			do {				
//				List<String> tupla = new ArrayList<String>();
//				for(int i=0 ; i<cursor.getColumnCount();i++)
//					tupla.add(cursor.getString(i));							
//				retorno.add(tupla);
//				
//			} while (cursor.moveToNext());
//		}
//		db.close();
//		return retorno;
//	}
	
}
