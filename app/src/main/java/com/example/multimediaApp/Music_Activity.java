package com.example.multimediaApp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;

public class Music_Activity extends AppCompatActivity {

    Button play;
    ImageView img;
    int posicion = 0;  //para controlar la posicion del archivo reproducido
    private int forwardTime = 5000;
    private int backwardTime = 5000;
    private double startTime = 0;
    private double finalTime = 0;
    private boolean pause = true;

    MediaPlayer vectormp [] = new MediaPlayer[3];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_);

        // obtenemos la referencia de las vistas a partir de sus identificadores
        play = (Button)findViewById(R.id.btn_play);
        img = (ImageView) findViewById(R.id.imageMusic);

        //Inicializamos la clase MediaPlayer asociandole los ficheros de Audio
        vectormp[0] = MediaPlayer.create(this, R.raw.sound);
        vectormp[1] = MediaPlayer.create(this, R.raw.tea);
        vectormp[2] = MediaPlayer.create(this, R.raw.race);

    }

    public void onClick(View view) throws IOException {
        switch (view.getId()) {
            //Comprobamos el identificador del boton que ha llamado al evento para ver
            //cual de los botones es y ejecutar la acción correspondiente
            case R.id.btnIcoAtras:  //al pulsar el icono de la flecha volvemos a la pantalla principal
                for(int i=0;i<vectormp.length;i++){
                    if(vectormp[i] != null){
                        vectormp[i].stop();
                        vectormp[i].release(); // para asegurarse de que se retiren correctamente todos los recursos del sistema asignados
                        vectormp[i] = null;  //anulamos el MediaPlayer
                    }
                }
                finish();
                break;
            case R.id.btn_anterior:
                //comprobamos la posicion del archivo que se esta reproduciendo
                if (posicion >= 1) {
                    //si esta reproduciendose la canción la paramos
                    if (vectormp[posicion].isPlaying()) {
                        vectormp[posicion].stop();
                        //Inicializamos la clase MediaPlayer asociandole los ficheros de Audio
                        vectormp[0] = MediaPlayer.create(this, R.raw.sound);
                        vectormp[1] = MediaPlayer.create(this, R.raw.tea);
                        vectormp[2] = MediaPlayer.create(this, R.raw.race);
                        posicion--; //decrementamos la posicion

                        //otorgamos la imagen de fondo segun la posicion de la cancion
                        if (posicion == 0) {
                            img.setImageResource(R.drawable.music1);
                        } else if (posicion == 1) {
                            img.setImageResource(R.drawable.music2);
                        } else if (posicion == 2) {
                            img.setImageResource(R.drawable.music3);
                        }
                        vectormp[posicion].start(); //reproducimos la cancion anterior de la lista
                        play.setBackgroundResource(R.drawable.pausa);  //cambiamos la imagen del boton a pause
                    } else {
                        posicion--;
                        //otorgamos la imagen de fondo segun la posicion de la cancion
                        if (posicion == 0) {
                            img.setImageResource(R.drawable.music1);
                        } else if (posicion == 1) {
                            img.setImageResource(R.drawable.music2);
                        } else if (posicion == 2) {
                            img.setImageResource(R.drawable.music3);
                        }
                        vectormp[posicion].start(); //reproducimos la cancion anterior de la lista
                        play.setBackgroundResource(R.drawable.pausa);  //cambiamos la imagen del boton a pause
                    }
                }
                break;
            case R.id.btn_play:
                if (vectormp[posicion].isPlaying()) {
                    pause = true;
                    vectormp[posicion].pause();  //pausamos la cancion
                    play.setBackgroundResource(R.drawable.reproducir);  //cambiamos la imagen del boton a play
                } else {

                    vectormp[posicion].start();  //reproducimos la cancion
                    finalTime = vectormp[posicion].getDuration();
                    startTime = vectormp[posicion].getCurrentPosition();
                    play.setBackgroundResource(R.drawable.pausa);  //cambiamos la imagen del boton a pause
                }
                break;
            case R.id.btn_stop:
                if (vectormp[posicion] != null) {
                    pause = false;
                    vectormp[posicion].stop();

                    //Inicializamos la clase MediaPlayer asociandole los ficheros de Audio, después de llamar a stop()
                    vectormp[0] = MediaPlayer.create(this, R.raw.sound);
                    vectormp[1] = MediaPlayer.create(this, R.raw.tea);
                    vectormp[2] = MediaPlayer.create(this, R.raw.race);
                    posicion = 0;
                    play.setBackgroundResource(R.drawable.reproducir);  //cambiamos la imagen del boton reproducir a play
                    img.setImageResource(R.drawable.music1);
                }
                break;
            case R.id.btn_next:
                //comprobamos la posicion
                if (posicion < vectormp.length - 1) {
                    if (vectormp[posicion].isPlaying()) {
                        vectormp[posicion].stop(); //si se esta reproduciendo la paramos
                        posicion++; //incrementamos la posicion

                        //otorgamos la imagen de fondo segun la posicion de la cancion
                        if (posicion == 0) {
                            img.setImageResource(R.drawable.music1);
                        } else if (posicion == 1) {
                            img.setImageResource(R.drawable.music2);
                        } else if (posicion == 2) {
                            img.setImageResource(R.drawable.music3);
                        }
                        vectormp[posicion].start(); //reproducimos la cancion siguiente de la lista
                        play.setBackgroundResource(R.drawable.pausa);  //cambiamos la imagen del boton a pause

                    } else {
                        posicion++;
                        //otorgamos la imagen de fondo segun la posicion de la cancion
                        if (posicion == 0) {
                            img.setImageResource(R.drawable.music1);
                        } else if (posicion == 1) {
                            img.setImageResource(R.drawable.music2);
                        } else if (posicion == 2) {
                            img.setImageResource(R.drawable.music3);
                        }
                        vectormp[posicion].start(); //reproducimos la cancion siguiente de la lista
                        play.setBackgroundResource(R.drawable.pausa);  //cambiamos la imagen del boton a pause
                    }
                }
                break;
            case R.id.btn_backward:
                int temp = (int) startTime;
                if (vectormp[posicion].isPlaying()) {
                    if ((temp - backwardTime) > 0) {
                        startTime = startTime - backwardTime;
                        vectormp[posicion].seekTo((int) startTime);
                    }
                }
                break;
            case R.id.btn_forward:
                int tempo = (int)startTime;
                if (vectormp[posicion].isPlaying()) {
                    if((tempo+forwardTime)<=finalTime) {
                        startTime = startTime + forwardTime;
                        vectormp[posicion].seekTo((int) startTime);
                    }
                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + view.getId());
        }
    }

    //si cerramos la aplicación que pare de sonar
    @Override
    public void onDestroy() {
        super.onDestroy();
        for(int i=0;i<vectormp.length;i++){
            if(vectormp[i] != null){
                vectormp[i].stop();
                vectormp[i].release(); // para asegurarse de que se retiren correctamente todos los recursos del sistema asignados
                vectormp[i] = null;  //anulamos el MediaPlayer
            }
        }
    }

    @Override public void onPause() {
        super.onPause();
        for(int i=0;i<vectormp.length;i++){
            if (vectormp[i] != null & !pause) {
                vectormp[i].pause();
            }
        }

    }
    @Override public void onResume() {
        super.onResume();
        for(int i=0;i<vectormp.length;i++) {
            if (vectormp[i] != null & !pause) {
                vectormp[i].start();
            }
        }
    }


}