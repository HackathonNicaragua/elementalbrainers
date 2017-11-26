package com.elementalbraines.expressapp.ui.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.elementalbraines.expressapp.R;
import com.elementalbraines.expressapp.ui.fragments.TourFirstFragment;
import com.elementalbraines.expressapp.ui.fragments.TourSecondFragment;
import com.elementalbraines.expressapp.ui.fragments.TourThirdFragment;
import com.stephentuso.welcome.BasicPage;
import com.stephentuso.welcome.FragmentWelcomePage;
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

                .page(new FragmentWelcomePage() {
                    @Override
                    protected Fragment fragment() {
                        return new TourFirstFragment();
                    }
                }.background(R.color.celeste_claro))
                .page(new FragmentWelcomePage() {
                            @Override
                            protected Fragment fragment() {
                                return new TourSecondFragment();
                            }
                        }.background(R.color.rosa_claro)
                )
                .page(new FragmentWelcomePage() {
                            @Override
                            protected Fragment fragment() {
                                return new TourThirdFragment();
                            }
                        }.background(R.color.colorPrimary)
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
