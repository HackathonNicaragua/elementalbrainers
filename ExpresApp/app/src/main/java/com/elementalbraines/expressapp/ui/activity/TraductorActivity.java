package com.elementalbraines.expressapp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.util.ArrayMap;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.elementalbraines.expressapp.R;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.droidsonroids.gif.AnimationListener;
import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;
import pl.droidsonroids.gif.GifTextView;
import pl.droidsonroids.gif.MultiCallback;

public class TraductorActivity extends AppCompatActivity {

    int REQUEST_MICROFONO = 100;
    TextToSpeech textToSpeech;

    GifDrawable gifDrawable;
    GifImageView gifInterprete;
    @BindView(R.id.cntInterprete)
    RelativeLayout cntInterprete;

    Map<String, String> lista;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_traductor);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lista = new HashMap<String, String>();
        lista.put("hola", "hola");
        lista.put("estás", "como_estas");
        lista.put("gracias", "gracias");
        lista.put("gracia", "gracias");
        lista.put("bien", "bien");
        lista.put("1", "uno");
        lista.put("2", "dos");
        lista.put("3", "tres");
        lista.put("4", "cuatro");
        lista.put("5", "cinco");
        lista.put("6", "seis");
        lista.put("7", "siete");
        lista.put("8", "ocho");
        lista.put("9", "nueve");
        lista.put("10", "diez");

        gifInterprete = (GifImageView) findViewById(R.id.gifInterprete);

        gifDrawable = (GifDrawable) gifInterprete.getDrawable();


    }

    @OnClick(R.id.fabMicrofono)
    void onFabMicrofono(View view) {
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

        if (requestCode == REQUEST_MICROFONO) {

            if(RESULT_OK == resultCode) {
                ArrayList<String> values = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                if (resultCode == RESULT_OK) {
                    String[] frases = values.get(0).split(" ");
                    List<String> animaciones = new ArrayList<String>();
                    for (String frase : frases) {
                        if (lista.containsKey(frase.toLowerCase())) {
                            animaciones.add(lista.get(frase.toLowerCase()));
                        }
                    }
                    count = 0;
                    if (animaciones.size() > 0)
                        createAnimation(animaciones);
                    else
                        Toast.makeText(getApplicationContext(), "No hay resultados", Toast.LENGTH_LONG);

                    //Toast.makeText(getApplicationContext(), values.get(0), Toast.LENGTH_LONG).show();
                }
            }else{
                Snackbar.make(cntInterprete, "Petición Cancelada", Snackbar.LENGTH_LONG).show();
            }

            //gifInterprete.setBackgroundResource(R.drawable.tres);

        }


    }



    /*boolean continuar = true;
    int count = 0;
    String[] animaciones = new String[]{"hola", "seis", "hola", "seis", "hola", "seis"};
    @Override
    public void onAnimationCompleted(int loopNumber) {
        if(count < 6) {
            gifInterprete.setImageResource(getResources().getIdentifier(animaciones[count], "drawable", getPackageName()));

            count++;
        }
    }*/

    int count = 0;
    public void createAnimation(final List<String> frases) {
        cntInterprete.removeAllViews();
        if (count < frases.size()) {
            GifImageView gifImageView = new GifImageView(getApplicationContext());
            String animation = frases.get(count).toLowerCase();
            gifImageView.setImageResource(getResources().getIdentifier(animation, "drawable", getPackageName()));
            final GifDrawable gifDrawable = (GifDrawable) gifImageView.getDrawable();
            if(gifDrawable != null) {
                gifDrawable.addAnimationListener(new AnimationListener() {
                    @Override
                    public void onAnimationCompleted(int loopNumber) {
                        count++;
                        createAnimation(frases);


                    }
                });

                gifImageView.setImageDrawable(gifDrawable);
                cntInterprete.addView(gifImageView);
                gifDrawable.start();
            }

        }else{
            GifImageView gifImageView = new GifImageView(getApplicationContext());
            gifImageView.setImageResource(getResources().getIdentifier("inicio", "drawable", getPackageName()));;
            cntInterprete.addView(gifImageView);
        }
    }


}
