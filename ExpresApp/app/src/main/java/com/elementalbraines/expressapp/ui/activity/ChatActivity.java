package com.elementalbraines.expressapp.ui.activity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.elementalbraines.expressapp.R;
import com.elementalbraines.expressapp.Util;
import com.elementalbraines.expressapp.models.Chat;
import com.elementalbraines.expressapp.ui.adapters.ChatAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tumblr.remember.Remember;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChatActivity extends AppCompatActivity {

    @BindView(R.id.rcvChat)
    RecyclerView rcvChat;
    public final int REQUEST_MICROFONO = 100;
    TextToSpeech textToSpeech;

    ChatAdapter adapter;
    DatabaseReference dbChat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Chat");

        Remember.init(getApplicationContext(), "com.elementalbraines.expressapp");

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        dbChat = FirebaseDatabase.getInstance().getReference()
                .child("salas").child("sala1");

        List<Chat> chatList = new ArrayList<Chat>();


        adapter = new ChatAdapter(getApplicationContext(), chatList);
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


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_MICROFONO) {


            if(resultCode == RESULT_OK){
                ArrayList<String> values = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                String user_id = Remember.getString(Util.USER_ID,"Anonymo");
                String user_name = Remember.getString(Util.USER_NAME,"Anonymo");
                String user_picture = Remember.getString(Util.USER_PICTURE,"Anonymo");

                Chat chat = new Chat(user_id, user_name, values.get(0), user_picture);
                dbChat.child(java.util.UUID.randomUUID().toString()).setValue(chat);
                //Toast.makeText(getApplicationContext(), values.get(0), Toast.LENGTH_LONG).show();
            }else{
                Snackbar.make(rcvChat, "Petición cancelada", Snackbar.LENGTH_LONG).show();
            }

            //gifInterprete.setBackgroundResource(R.drawable.tres);

        }


    }

    public void loadMensaje(){

        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
                String token = dataSnapshot.child("user_id").getValue().toString();
                String nombre = dataSnapshot.child("nombre").getValue().toString();
                String mensaje = dataSnapshot.child("mensaje").getValue().toString();
                String picture = dataSnapshot.child("image").getValue().toString();
                adapter.addChat(new Chat(token, nombre, mensaje, picture));
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
