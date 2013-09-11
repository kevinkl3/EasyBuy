package com.duobitsw.easybuy;

public class User {

	int _id;
	String _userName;
	String _phoneNumber;
	String _email;
	String _postalAddress;
	String _password;
	String _profilepic;
	
	public User(){}

	public User(int id, String name, String _phone_number) {
		this._id = id;
		this._userName = name;
		this._phoneNumber = _phone_number;
	}

	// getting ID
	public int getID() {
		return this._id;
	}

	// setting id
	public void setID(int id) {
		this._id = id;
	}

	// getting name
	public String getName() {
		return this._userName;
	}

	// setting name
	public void setName(String name) {
		this._userName = name;
	}

	// getting phone number
	public String getPhoneNumber() {
		return this._phoneNumber;
	}

	// setting phone number
	public void setPhoneNumber(String phone_number) {
		this._phoneNumber = phone_number;
	}

	public String get_postalAddress() {
		return _postalAddress;
	}

	public void set_postalAddress(String _postalAddress) {
		this._postalAddress = _postalAddress;
	}

	public String get_password() {
		return _password;
	}

	public void set_password(String _password) {
		this._password = _password;
	}

	public String get_email() {
		return _email;
	}

	public void set_email(String _email) {
		this._email = _email;
	}
}
