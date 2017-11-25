package com.elementalbraines.expressapp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.elementalbraines.expressapp.R;

import java.util.ArrayList;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.droidsonroids.gif.GifTextView;

public class TraductorActivity extends AppCompatActivity {

    int REQUEST_MICROFONO = 100;
    TextToSpeech textToSpeech;
    @BindView(R.id.gifInterprete)
    GifTextView gifInterprete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_traductor);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @OnClick(R.id.fabMicrofono)
    void onFabMicrofono(View view){
        Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        i.putExtra(RecognizerIntent.EXTRA_PROMPT, "Di algo");
        startActivityForResult(i, REQUEST_MICROFONO);

        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    textToSpeech.setSpeechRate(1);
                    textToSpeech.setLanguage(Locale.getDefault());
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_MICROFONO){
            ArrayList<String> values = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            gifInterprete.setBackgroundResource(R.drawable.hola);
            gifInterprete.setBackgroundResource(R.drawable.uno);
            gifInterprete.setBackgroundResource(R.drawable.dos);
            gifInterprete.setBackgroundResource(R.drawable.tres);
            Toast.makeText(getApplicationContext(), values.get(0), Toast.LENGTH_LONG).show();
        }
    }
}
