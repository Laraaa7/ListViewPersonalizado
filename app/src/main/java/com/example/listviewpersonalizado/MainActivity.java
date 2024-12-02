package com.example.listviewpersonalizado;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView lista;
    private TextView texto;
    private RadioButton radioButton_pulsado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        lista = findViewById(R.id.listView);
        texto = findViewById(R.id.textViewInfo);

        ArrayList<Encapsulador> datos = new ArrayList<Encapsulador>();
        datos.add(new Encapsulador(R.drawable.numero_1, "Número 1", "Descripción número 1", false));
        datos.add(new Encapsulador(R.drawable.numero_2, "Número 2", "Descripción número 2", false));
        datos.add(new Encapsulador(R.drawable.numero_3, "Número 3", "Descripción número 3", false));
        datos.add(new Encapsulador(R.drawable.numero_4, "Número 4", "Descripción número 4", false));
        datos.add(new Encapsulador(R.drawable.numero_5, "Número 5", "Descripción número 5", false));
        datos.add(new Encapsulador(R.drawable.numero_6, "Número 6", "Descripción número 6", false));

        lista.setAdapter(new Adaptador(this, R.layout.entrada, datos) {
            @Override
            public void onEntrada(Object entrada, View view) {
                if (entrada != null) {
                    TextView texto_superior_entrada = (TextView) view.findViewById(R.id.texto_titulo);
                    TextView texto_inferior_entrada = (TextView) view.findViewById(R.id.texto_datos);
                    ImageView imagen_entrada = (ImageView)  view.findViewById(R.id.imagen);
                    RadioButton miRadio = (RadioButton)view.findViewById(R.id.boton);

                    texto_superior_entrada.setText(((Encapsulador)entrada).get_textoTitulo());
                    texto_inferior_entrada.setText(((Encapsulador)entrada).get_textoContenido());
                    imagen_entrada.setImageResource(((Encapsulador)entrada).get_idImagen());

                    miRadio.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                        if(radioButton_pulsado!=null)radioButton_pulsado.setChecked(false);
                        radioButton_pulsado=(RadioButton) v;
                        texto.setText("MARCADA UNA OPCIÓN");
                        }
                    });

                }
            }
        });


    }

    public class Encapsulador {
        private int imagen;
        private String titulo;
        private String texto;
        private boolean dato1;

        public Encapsulador(int idImagen, String textoTitulo, String textoContenido, boolean favorito) {
            this.imagen = idImagen;
            this.titulo = textoTitulo;
            this.texto = textoContenido;
            this.dato1 = favorito;
        }

        public int get_idImagen() {
            return imagen;
        }

        public String get_textoTitulo() {
            return titulo;
        }

        public String get_textoContenido() {
            return texto;
        }

        public boolean get_checkBox1() {
            return dato1;
        }
    }


}
