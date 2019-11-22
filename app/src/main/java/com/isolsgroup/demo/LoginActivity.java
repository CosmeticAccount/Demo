package com.isolsgroup.demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edMobile, edPassword;
    String strUserMobile, strUserPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.login);

        getDataFromTable();

        initViews();
    }

    private void getDataFromTable(){
        SQLiteHandler sqLiteHandler = new SQLiteHandler(LoginActivity.this);
        HashMap<String, String> user = sqLiteHandler.getUserDetails ();
        strUserMobile = user.get ("phone_number");
        strUserPassword = user.get ("password");
    }

    private void initViews(){
        edMobile = findViewById (R.id.et_mobile);
        edPassword = findViewById (R.id.et_password);
        Button btnLogin = findViewById (R.id.btn_login);
        TextView txtRegistrationLink = findViewById (R.id.btn_register);

        btnLogin.setOnClickListener (this);
        txtRegistrationLink.setOnClickListener (this);
    }

    @Override
    public void onClick(View v) {
       switch (v.getId ()){
           case R.id.btn_login:
               if(validate ()){
                   loginMethod();
               }
               break;

           case R.id.btn_register:
               Intent intent = new Intent (LoginActivity.this, RegistrationActivity.class);
               startActivity (intent);
               break;
       }
    }

    private void loginMethod(){
        String strMobile = edMobile.getText ().toString ();
        String strPassword = edPassword.getText ().toString ();
        SessionManager sessionManager = new SessionManager (LoginActivity.this);
        sessionManager.setLogin (true);
        if (strMobile.equals (strUserMobile) && strPassword.equals (strUserPassword)){
            Intent intent = new Intent (LoginActivity.this, HomeActivity.class);
            intent.putExtra ("userMobile", strMobile);
            intent.putExtra ("userName", "Ravinder");
            startActivity (intent);
        }else {
            Toast.makeText (LoginActivity.this, "You are not registered user", Toast.LENGTH_LONG).show ();
        }
    }

    public boolean validate() {
        boolean valid = true;
        String mMobile = edMobile.getText().toString().trim();
        String mPassword = edPassword.getText().toString().trim();

        if (mMobile.isEmpty() || !isPhoneNumberValid(mMobile)) {
            edMobile.setError("Enter a valid Indian mobile number");
            valid = false;
        } else {
            edMobile.setError(null);
        }

        if (mPassword.isEmpty()) {
            edPassword.setError("Enter your password");
            valid = false;
        } else {
            edPassword.setError(null);
        }

        return valid;
    }

    private boolean isPhoneNumberValid(String phoneNumber) {
        String firstCharacter = "";
        firstCharacter = String.valueOf(phoneNumber.charAt(0));
        if (phoneNumber.length() == 10 && (firstCharacter.equals("6") || firstCharacter.equals("7") || firstCharacter.equals("8") || firstCharacter.equals("9"))) {
            return true;
        } else {
            return false;
        }
    }

}