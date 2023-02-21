package com.example.reproductor_musica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button iniciarReproB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iniciarReproB = findViewById(R.id.button2);
    }


    public void iniciarRepro(){
        Intent intent = new Intent(this, ListaCanciones.class);
        startActivity(intent);

    }

    public void onClick(View view){
       if(iniciarReproB.isPressed()){
           iniciarRepro();
       }
    }








}