package com.elementalbraines.expressapp.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.elementalbraines.expressapp.R;
import com.stephentuso.welcome.BasicPage;
import com.stephentuso.welcome.TitlePage;
import com.stephentuso.welcome.WelcomeActivity;
import com.stephentuso.welcome.WelcomeConfiguration;
import com.stephentuso.welcome.WelcomeHelper;

public class WelcomeAppActivity extends WelcomeActivity {

    @Override
    protected WelcomeConfiguration configuration() {
        return new WelcomeConfiguration.Builder(this)
                .defaultTitleTypefacePath("Montserrat-Bold.ttf")
                .defaultHeaderTypefacePath("Montserrat-Bold.ttf")

                .page(new BasicPage(R.drawable.tour1,
                        "Welcome",
                        "An Android library for onboarding, instructional screens, and more")
                        .background(R.color.rosa_claro)
                )
                .page(new BasicPage(R.drawable.tour2,
                        "Simple to use",
                        "Add a welcome screen to your app with only a few lines of code.")
                        .background(R.color.colorPrimary)
                )
                .page(new BasicPage(R.drawable.tour3,
                        "Simple to use",
                        "Add a welcome screen to your app with only a few lines of code.")
                        .background(R.color.colorPrimary)
                )
                .swipeToDismiss(false)
                .exitAnimation(android.R.anim.fade_out)
                .build();
    }

    @Override
    protected void completeWelcomeScreen() {
        super.completeWelcomeScreen();
        showMenuActivity();
    }

    private void showMenuActivity(){
        Intent i = new Intent(this, TraductorActivity.class);
        startActivity(i);
    }
}
