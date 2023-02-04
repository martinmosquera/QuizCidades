package com.semana13.quizcidades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.semana13.quizcidades.task.CityTask;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button confirmar;
    Button next;
    ImageView city;
    EditText resposta;
    TextView result;
    Random random;
    private int score;
    private int ask;
    private int cidade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        score =0;
        ask =1;
        confirmar = findViewById(R.id.confirm);
        next = findViewById(R.id.next);
        resposta = findViewById(R.id.resposta);
        result = findViewById(R.id.result);
        city = findViewById(R.id.cidade);
        String[] links = {"01_barcelona.jpg","02_brasilia.jpg","03_curitiba.jpg","04_lasvegas.jpg",
                "05_montreal.jpg","06_paris.jpg","07_riodejaneiro.jpg","08_salvador.jpg","09_saopaulo.jpg","10_toquio.jpg"};
        random = new Random();
        cidade = random.nextInt(10);
        String urlBlockChain = "http://31.220.51.31/ufpr/cidades/";
        String result = urlBlockChain.concat(links[cidade]);
        CityTask task = new CityTask(city);
        task.execute(result);
        next.setEnabled(false);
    }

    public void confirm(View view){
        String[] cidades = {"barcelona","brasilia","curitiba","las vegas",
                "montreal","paris","rio de janeiro","salvador","sao paulo","toquio"};
        if(resposta.getText().toString().isEmpty()){
            Toast.makeText(this, "Digite uma Cidade", Toast.LENGTH_SHORT).show();
        }else{
            next.setEnabled(true);
            resposta.setEnabled(false);
            confirmar.setEnabled(false);
            String res = resposta.getText().toString();
            if(res.equalsIgnoreCase(cidades[cidade])){
                result.setText("Muito Bem!!! Você acertou!");
                score++;
            }else{
                result.setText("Errrouu!!! A cidade é "+cidades[cidade].toUpperCase());
            }
        }

    }

    public void next(View view){
        if(ask < 4){
            resposta.setText("");
            resposta.setEnabled(true);
            confirmar.setEnabled(true);
            String[] links = {"01_barcelona.jpg","02_brasilia.jpg","03_curitiba.jpg","04_lasvegas.jpg",
                    "05_montreal.jpg","06_paris.jpg","07_riodejaneiro.jpg","08_salvador.jpg","09_saopaulo.jpg","10_toquio.jpg"};
            String urlBlockChain = "http://31.220.51.31/ufpr/cidades/";
            cidade = random.nextInt(10);
            String result = urlBlockChain.concat(links[cidade]);
            CityTask task = new CityTask(city);
            task.execute(result);
            ask++;
        }else{
            Intent intent = new Intent(MainActivity.this,Resultado.class);
            Bundle params = new Bundle();
            params.putInt("score",score);
            intent.putExtras(params);
            startActivity(intent);
            finish();
        }

    }
}