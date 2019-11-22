package com.isolsgroup.demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegistrationActivity extends AppCompatActivity
        implements View.OnClickListener {

    private EditText etUsername, etMobile, etPassword, etConfirmpassword;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.register);

        intView();

    }

    private void intView() {
        etUsername = findViewById (R.id.et_username);
        etMobile = findViewById (R.id.et_mobile);
        etPassword = findViewById (R.id.et_password);
        etConfirmpassword = findViewById (R.id.et_confirmpassword);
        Button btnSignUp = findViewById (R.id.btn_SignUp);
        TextView btnLogin = findViewById (R.id.btn_login);

        btnLogin.setOnClickListener (this);
        btnSignUp.setOnClickListener (this);
    }

    @Override
    public void onClick(View v){
        switch (v.getId ()){
            case R.id.btn_SignUp:
                if (validate ()){
                    signUpMethod();
                }
                break;

            case R.id.btn_login:
                Intent intent = new Intent (RegistrationActivity.this, LoginActivity.class);
               startActivity (intent);
                break;
        }
    }

    private void signUpMethod(){
        SQLiteHandler sqLiteHandler = new SQLiteHandler(RegistrationActivity.this);
        SessionManager sessionManager = new SessionManager (RegistrationActivity.this);
        sessionManager.setLogin (true);
        sqLiteHandler.addUser (etUsername.getText().toString(), etMobile.getText().toString(), etPassword.getText().toString());
        Intent intent = new Intent (RegistrationActivity.this, HomeActivity.class);
        intent.putExtra ("userName", etUsername.getText ().toString ());
        intent.putExtra ("userMobile", etMobile.getText ().toString ());
        startActivity (intent);
    }

    public boolean validate() {
        boolean valid = true;
        String mUserName = etUsername.getText().toString().trim();
        String mMobile = etMobile.getText().toString().trim();
        String mPassword = etPassword.getText().toString().trim();
        String mConfirmPassword = etConfirmpassword.getText().toString().trim();

        if (mUserName.isEmpty()) {
            etUsername.setError("Enter your full name");
            valid = false;
        } else {
            etUsername.setError(null);
        }

        if (mMobile.isEmpty() || !isPhoneNumberValid(mMobile)) {
            etMobile.setError("Enter a valid Indian mobile number");
            valid = false;
        } else {
            etMobile.setError(null);
        }

        if (mPassword.isEmpty()) {
            etPassword.setError("Enter your password");
            valid = false;
        } else {
            etPassword.setError(null);
        }

        if (mConfirmPassword.isEmpty() || !mPassword.equals (mConfirmPassword)) {
            etConfirmpassword.setError("Password doesn't match");
            valid = false;
        } else {
            etConfirmpassword.setError(null);
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

