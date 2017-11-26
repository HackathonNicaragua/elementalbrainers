package com.elementalbraines.expressapp.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.elementalbraines.expressapp.R;
import com.elementalbraines.expressapp.Util;
import com.stephentuso.welcome.WelcomeHelper;
import com.tumblr.remember.Remember;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.droidsonroids.gif.AnimationListener;
import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

public class SplashActivity extends AppCompatActivity {

    Bundle bundle;
    WelcomeHelper welcomeScreen;
    int REQUEST_WELCOME_SCREEN_RESULT = 1;

    GifDrawable gifDrawable;
    @BindView(R.id.gifSplash)
    GifImageView gifSplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        Remember.init(getApplicationContext(), "com.elementalbraines.expressapp");
        Remember.putString("USER_TOKEN", "123456");
        bundle = savedInstanceState;
        getSupportActionBar().hide();

        gifDrawable = (GifDrawable) gifSplash.getDrawable();
        gifDrawable.addAnimationListener(new AnimationListener() {
            @Override
            public void onAnimationCompleted(int loopNumber) {
                onShowWelcome();
            }
        });

    }

    void onShowWelcome(){
        boolean show = Remember.getBoolean(Util.SHOW_WELLCOME, false);
        if(true){
            welcomeScreen = new WelcomeHelper(this, WelcomeAppActivity.class);
            welcomeScreen.forceShow();
            Remember.putBoolean(Util.SHOW_WELLCOME, true);
        }else{
            showMenuActivity();
        }

        this.finish();
    }

    private void showMenuActivity(){
        Intent i = new Intent(this, TraductorActivity.class);
        startActivity(i);
    }

}
