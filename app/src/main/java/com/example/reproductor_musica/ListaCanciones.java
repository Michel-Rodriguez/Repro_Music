package com.example.reproductor_musica;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ListaCanciones extends AppCompatActivity implements AdapterView.OnItemClickListener{

    ListView miLista;

    private String [] titulo= new String[]{"Bohemian Rhapsody", "Paint it Black", "Wonderwall", "Toxicity" }; //Datos
    private String [] grupo= new String[]{"Queen", "Rolling Stones", "Oasis", "System Of A Down"}; //Datos
    private  String indiceCancion;

    private int[] imagenes =new int[]{R.drawable.queen,R.drawable.rolling_stones_logo,R.drawable.oasis_logo, R.drawable.system_ofad_logo};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        ArrayList<Cancion> canciones = new ArrayList<Cancion>();
        for(int i=0;i< titulo.length;i++){
            Cancion c=new Cancion(titulo[i],grupo[i],imagenes[i]);
            canciones.add(c);
            indiceCancion = canciones.get(i).getTitulo();

        }



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_canciones);

        miLista=findViewById(R.id.miLista);

        MiAdaptador adapter=
                new MiAdaptador(this,R.layout.mi_fila_personalizada,canciones);
        ////support_simple_spinner_dropdown_item
        //enfuchar adaptador a la vista
        miLista.setAdapter(adapter);
        miLista.setOnItemClickListener(this);


    }

    public void reproducirMusic(){
        Intent intent2 =  new Intent(this, ReproMusic.class);
        intent2.putExtra("indiceC", indiceCancion);  // Pasamos esto a la otra clase
        startActivity(intent2);

    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        reproducirMusic();


    }



    public class MiAdaptador extends ArrayAdapter<Cancion>{
        private int mResource;
        private ArrayList<Cancion> misCanciones;

        public MiAdaptador(@NonNull Context context, int resource, @NonNull List<Cancion> objects) {
            super(context, resource, objects);
            mResource = resource;
            misCanciones=(ArrayList<Cancion>) objects;
        }

        //Este método sólo es necesario reescribirlo si el adaptador se enchufa a un spinner
        @Override
        public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            return crearFila(position,convertView,parent);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            //indiceCancion = misCanciones.get(position).titulo;
            return crearFila(position,convertView,parent);
        }

        private View crearFila(int position, @Nullable View convertView, @NonNull ViewGroup parent){
            //Este método es invocado tantas veces como "filas" se pinten en la actividad

            LayoutInflater miInflador= getLayoutInflater();
            View miFila=miInflador.inflate(mResource,parent,false);

            TextView txtGrupo=miFila.findViewById(R.id.txtGrupo);
            TextView txtTitulo=miFila.findViewById(R.id.txtTitulo);
            ImageView imgCiudad=miFila.findViewById(R.id.imgCiudad);

            txtGrupo.setText(misCanciones.get(position).grupo);
            txtTitulo.setText(misCanciones.get(position).titulo);
            imgCiudad.setImageResource(misCanciones.get(position).imagen);

            return miFila;
        }




    }


//**** Clase canciones****

    public class Cancion{
        String titulo;
        String grupo;
        int imagen;

        public Cancion(String titulo, String grupo, int imagen) {
            this.titulo = titulo;
            this.grupo = grupo;
            this.imagen = imagen;
        }

        public String getTitulo(){
            return titulo;
        }


    }
}