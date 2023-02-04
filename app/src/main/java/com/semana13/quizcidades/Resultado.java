package com.semana13.quizcidades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Resultado extends AppCompatActivity {

    TextView resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        resultado = findViewById(R.id.textViewResultado);
        Intent it = getIntent();

        if (it != null) {
            Bundle params = it.getExtras();
            if(params != null){
                int score = params.getInt("score");
                score = score*25;
                resultado.setText(String.valueOf(score));
            }
        }

    }

    public void reset(View view){
        Intent it = new Intent(Resultado.this,MainActivity.class);
        startActivity(it);
        finish();
    }

}