package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView TextoGanador;
    Integer[] botones;
    int[] tablero = new int[]{
            0, 0, 0,
            0, 0, 0,
            0, 0, 0
    };

    int estado = 0;
    int fichapuesta = 0;
    int turno = 1;
    int[] posGanadora = new int[]{-1, -1, -1};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextoGanador = (TextView) findViewById(R.id.MensajeTexto);
        TextoGanador.setVisibility(View.INVISIBLE);

        botones = new Integer[]{
                R.id.B7, R.id.B8, R.id.B9,
                R.id.B4, R.id.B5, R.id.B6,
                R.id.B1, R.id.B2, R.id.B3,
        };
    }

    public void jugada(View v) {
        if (estado == 0) {
            turno = 1;
            int NumeroBoton = Arrays.asList(botones).indexOf(v.getId());
            if (tablero[NumeroBoton] == 0) {
                v.setBackgroundResource(R.drawable.equis);
                tablero[NumeroBoton] = 1;
                fichapuesta += 1;
                estado =comprobarEstado();
                partidaTerminada();

                if (estado == 0) {
                    turno = -1;
                    ia();
                    fichapuesta += 1L;
                    estado =comprobarEstado();
                    partidaTerminada();
                }

            }


        }
    }

    public void partidaTerminada(){
        if(estado == 1 || estado==-1){

            if(estado == 1){
                TextoGanador.setTextColor(Color.YELLOW);
                TextoGanador.setVisibility(View.VISIBLE);
            }else{
                if(estado == -1){
                    TextoGanador.setText(" You Lose!");
                    TextoGanador.setTextColor(Color.RED);
                    TextoGanador.setVisibility(View.VISIBLE);
                }
            }

        } else if (estado == 2 ){
            TextoGanador.setText(" You Tied!");
            TextoGanador.setTextColor(Color.GRAY);
            TextoGanador.setVisibility(View.VISIBLE);
        }

    }
    public void ia() {
        Random ran = new Random();
        int pos = ran.nextInt(tablero.length);
        while (tablero[pos] != 0) {
            pos = ran.nextInt(tablero.length);
        }
        ImageButton imageButton = findViewById(botones[pos]);
        imageButton.setBackgroundResource(R.drawable.circulo);
        tablero[pos] = -1;
    }

    public int comprobarEstado() {
        for (int i = 0; i < 3; i++) {
            // Comprobar filas
            if (tablero[i * 3] == 1 && tablero[i * 3 + 1] == 1 && tablero[i * 3 + 2] == 1) {
                posGanadora[0] = i * 3;
                posGanadora[1] = i * 3 + 1;
                posGanadora[2] = i * 3 + 2;
                return 1; // Jugador gana
            }
            // Comprobar columnas
            if (tablero[i] == 1 && tablero[i + 3] == 1 && tablero[i + 6] == 1) {
                posGanadora[0] = i;
                posGanadora[1] = i + 3;
                posGanadora[2] = i + 6;
                return 1; // Jugador gana
            }
            // Comprobar filas (IA)
            if (tablero[i * 3] == -1 && tablero[i * 3 + 1] == -1 && tablero[i * 3 + 2] == -1) {
                posGanadora[0] = i * 3;
                posGanadora[1] = i * 3 + 1;
                posGanadora[2] = i * 3 + 2;
                return -1; // IA gana
            }
            // Comprobar columnas (IA)
            if (tablero[i] == -1 && tablero[i + 3] == -1 && tablero[i + 6] == -1) {
                posGanadora[0] = i;
                posGanadora[1] = i + 3;
                posGanadora[2] = i + 6;
                return -1; // IA gana
            }
        }

        // Comprobar diagonales
        if (tablero[0] == 1 && tablero[4] == 1 && tablero[8] == 1) {
            posGanadora[0] = 0;
            posGanadora[1] = 4;
            posGanadora[2] = 8;
            return 1; // Jugador gana
        }
        if (tablero[2] == 1 && tablero[4] == 1 && tablero[6] == 1) {
            posGanadora[0] = 2;
            posGanadora[1] = 4;
            posGanadora[2] = 6;
            return 1; // Jugador gana
        }
        // Comprobar diagonales (IA)
        if (tablero[0] == -1 && tablero[4] == -1 && tablero[8] == -1) {
            posGanadora[0] = 0;
            posGanadora[1] = 4;
            posGanadora[2] = 8;
            return -1; // IA gana
        }
        if (tablero[2] == -1 && tablero[4] == -1 && tablero[6] == -1) {
            posGanadora[0] = 2;
            posGanadora[1] = 4;
            posGanadora[2] = 6;
            return -1; // IA gana
        }

        if (fichapuesta == 9) {
            return 2; // Empate
        }

        return 0; // Juego no ha terminado
    }


}

