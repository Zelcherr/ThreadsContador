package com.example.threadscontador;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private boolean iniciar = false;  //variable booleana para controlar el contador
    private int counter = 0;          //variable para llevar la cuenta del contador iniciando en 0
    private TextView txt_counter;    //variable donde almacena y muestra el contador

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt_counter = findViewById(R.id.txtContador);
    }

    public void Start(View view){
        if (!iniciar){
            iniciar = true;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (iniciar){
                        counter++;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                txt_counter.setText(String.valueOf(counter));
                            }
                        });
                        try {
                            Thread.sleep(1000);
                        }catch (InterruptedException e){
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }
    }

    public void Stop(View view){            //OnClick Stop
        iniciar = false;                    //detiene el bucle y mantiene el contador
    }
}