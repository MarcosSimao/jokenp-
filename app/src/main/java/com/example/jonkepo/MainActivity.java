package com.example.jonkepo;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ImageView jogador1, jogador2;
    ImageButton papel, tesoura, pedra;
    Animation some, aparece;
    int jogada1,jogado2;
    MediaPlayer media;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        media= MediaPlayer.create(MainActivity.this,R.raw.alex_play);
        jogador1 = findViewById(R.id.jogador1);
        jogador2 = findViewById(R.id.jogador2);
        papel = findViewById(R.id.papel);
        tesoura = findViewById(R.id.tesoura);
        pedra = findViewById(R.id.pedra);
        some = new AlphaAnimation(1, 0);
        aparece = new AlphaAnimation(0, 1);

        some.setDuration(1500);
        aparece.setDuration(1500);

        some.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                jogador2.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                jogador2.setVisibility(View.INVISIBLE);
                jogador2.startAnimation(aparece);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        aparece.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                sorteiraJogadaAdversario();
                jogador2.setVisibility(View.INVISIBLE);

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                verificarJogada();
                jogador2.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


    }

    private void verificarJogada() {
        if(jogada1==jogado2){
            Toast.makeText(this, "empate",Toast.LENGTH_SHORT).show();
        }

    }

    private void sorteiraJogadaAdversario() {
        Random r = new Random();
        int numero = r.nextInt(3);

        switch (numero) {
            case 0:
                jogador2.setImageResource(R.drawable.pedra);
                jogado2=1;
                break;
            case 1:
                jogador2.setImageResource(R.drawable.papel);
                jogado2=2;
                break;
            case 2:
                jogador2.setImageResource(R.drawable.tesoura);
                jogado2=3;
                break;
        }
    }
    private void tocaSom(){
        if(media!= null){
            media.start();
        }


    }

    public void clicouBotao(View view) {
        tocaSom();
        jogador1.setScaleX(-1);
        switch (view.getId()) {
            case (R.id.pedra):
                jogador1.setImageResource(R.drawable.pedra);
                jogada1=1;
                break;
            case (R.id.papel):
                jogador1.setImageResource(R.drawable.papel);
                jogada1=2;
                break;
            case (R.id.tesoura):
                jogador1.setImageResource(R.drawable.tesoura);
                jogada1=3;
                break;
        }
        jogador2.setImageResource(R.drawable.interrogacao);
        jogador2.startAnimation(some);
    }
}