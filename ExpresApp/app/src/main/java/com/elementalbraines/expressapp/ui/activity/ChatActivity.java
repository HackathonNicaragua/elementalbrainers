package com.elementalbraines.expressapp.ui.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.elementalbraines.expressapp.R;
import com.elementalbraines.expressapp.Util;
import com.elementalbraines.expressapp.models.ChatModel;
import com.elementalbraines.expressapp.ui.adapters.ChatAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.tumblr.remember.Remember;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChatActivity extends AppCompatActivity {

    @BindView(R.id.rcvChat)
    RecyclerView rcvChat;
    public final int REQUEST_MICROFONO = 100;
    TextToSpeech textToSpeech;

    ChatAdapter adapter;
    DatabaseReference dbChat;
    @BindView(R.id.txvTitToolbart)
    TextView txvTitToolbart;

    /*@BindView(R.id.imvReproducir)
    ImageButton imvReproducir;*/

    @BindView(R.id.edtMensaje)
    EditText edtMensaje;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("ChatModel");
        toolbar.setNavigationIcon(R.drawable.ic_logo);

        Remember.init(getApplicationContext(), "com.elementalbraines.expressapp");

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        validatePermission();

        dbChat = FirebaseDatabase.getInstance().getReference()
                .child("salas").child("sala1");

        List<ChatModel> chatModelList = new ArrayList<ChatModel>();


        adapter = new ChatAdapter(getApplicationContext(), chatModelList);
        rcvChat.setAdapter(adapter);
        rcvChat.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        //rcvChat.setHasFixedSize(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
        });


        loadMensaje();

        Typeface font = Typeface.createFromAsset(getAssets(), "RobotoCondensed-Light.ttf");
        txvTitToolbart.setTypeface(font);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    public void validatePermission(){
        if(Remember.getString(Util.USER_ID, "").equals("")){
            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.menu_traductor:
                Intent i = new Intent(this, TraductorActivity.class);
                startActivity(i);
                break;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_MICROFONO) {


            if(resultCode == RESULT_OK){
                ArrayList<String> values = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

                sendMensaje(values.get(0));
                //Toast.makeText(getApplicationContext(), values.get(0), Toast.LENGTH_LONG).show();
            }else{
                Snackbar.make(rcvChat, "Petición cancelada", Snackbar.LENGTH_LONG).show();
            }

            //gifInterprete.setBackgroundResource(R.drawable.tres);

        }


    }

    @OnClick(R.id.imvSend)
    void OnImvSend(View view){

        sendMensaje(edtMensaje.getText().toString());
        edtMensaje.setText("");
    }

    public void sendMensaje(String mensaje){
        String user_id = Remember.getString(Util.USER_ID,"Anonymo");
        String user_name = Remember.getString(Util.USER_NAME,"Anonymo");
        String user_picture = Remember.getString(Util.USER_PICTURE,"Anonymo");

        ChatModel chatModel = new ChatModel(user_id, user_name, mensaje, user_picture);
        dbChat.child(java.util.UUID.randomUUID().toString()).setValue(chatModel);
    }

    public void loadMensaje(){

        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
                String token = dataSnapshot.child("user_id").getValue().toString();
                String nombre = dataSnapshot.child("nombre").getValue().toString();
                String mensaje = dataSnapshot.child("mensaje").getValue().toString();
                String picture = dataSnapshot.child("image").getValue().toString();
                adapter.addChat(new ChatModel(token, nombre, mensaje, picture));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String previousChildName) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        dbChat.addChildEventListener(childEventListener);

    }

}
