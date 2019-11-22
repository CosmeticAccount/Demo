package com.isolsgroup.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import static java.lang.Thread.sleep;

public class SplashActivity extends AppCompatActivity {
    final String value = "100";
    final String quantity = "1";
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = this;

        PrefernceSettings.openDataBase(context);
        PrefernceSettings.setMax(value);
        PrefernceSettings.setQuantity(quantity);

        SessionManager sessionManager = new SessionManager (SplashActivity.this);
        if (sessionManager.isLoggedIn ()){
            startActivity(new Intent(SplashActivity.this, HomeActivity.class));
        }else {
            startActivity(new Intent(SplashActivity.this, LoginActivity.class));
        }

        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        finish();
    }
}
