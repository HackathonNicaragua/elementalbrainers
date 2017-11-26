package com.elementalbraines.expressapp.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.elementalbraines.expressapp.R;
import com.stephentuso.welcome.WelcomeHelper;
import com.tumblr.remember.Remember;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SplashActivity extends AppCompatActivity {

    Bundle bundle;
    WelcomeHelper welcomeScreen;
    int REQUEST_WELCOME_SCREEN_RESULT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        Remember.init(getApplicationContext(), "com.elementalbraines.expressapp");
        bundle = savedInstanceState;

    }

    @OnClick(R.id.btnWelcome)
    void onShowWelcome(View view){
        boolean show = Remember.getBoolean("SHOW_WELLCOME", false);
        if(true){
            welcomeScreen = new WelcomeHelper(this, WelcomeAppActivity.class);
            welcomeScreen.forceShow();
            Remember.putBoolean("SHOW_WELLCOME", true);
        }else{
            showMenuActivity();
        }

        this.finish();
    }

    private void showMenuActivity(){
        Intent i = new Intent(this, MenuActivity.class);
        startActivity(i);
    }

}
