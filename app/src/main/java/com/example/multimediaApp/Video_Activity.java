package com.example.multimediaApp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

public class Video_Activity extends AppCompatActivity {

    Button play;
    VideoView videoView;
    int posicion = 1; //para controlar la posicion del video
    private int forwardTime = 5000;
    private int backwardTime = 5000;
    private double startTime = 0;
    private double finalTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_);

        // obtenemos la referencia de las vistas a partir de sus identificadores
        play = (Button)findViewById(R.id.btn_play2);

        videoView = (VideoView) findViewById(R.id.videoView2);

        Uri path = Uri.parse("android.resource://com.example.multimediaApp/"
                + R.raw.video);

        videoView.setVideoURI(path);

    }

    public void onClick(View view) {
        switch (view.getId()){
            //Comprobamos el identificador del boton que ha llamado al evento para ver
            //cual de los botones es y ejecutar la acción correspondiente
            case R.id.btnIcoAtras:  //al pulsar el icono de la flecha volvemos a la pantalla principal
                finish();
                break;
            case R.id.btn_anterior2:
                //comprobamos la posicion para irnos al anterior video y reproducirlo
                if(posicion == 1) {
                    Uri path = Uri.parse("android.resource://com.example.multimediaApp/"
                            + R.raw.video);
                    videoView.setVideoURI(path);
                    videoView.start();
                    play.setBackgroundResource(R.drawable.pausa);  //cambiamos la imagen del boton a pause
                }else if(posicion == 2){
                    posicion--;
                    Uri path = Uri.parse("android.resource://com.example.multimediaApp/"
                            + R.raw.video);
                    videoView.setVideoURI(path);
                    videoView.start();
                    play.setBackgroundResource(R.drawable.pausa);  //cambiamos la imagen del boton a pause
                }else{
                    posicion--;
                    Uri path = Uri.parse("android.resource://com.example.multimediaApp/"
                            + R.raw.video2);
                    videoView.setVideoURI(path);
                    videoView.start();
                    play.setBackgroundResource(R.drawable.pausa);  //cambiamos la imagen del boton a pause
                }
                break;
            case R.id.btn_play2:
                if(videoView.isPlaying()){
                    videoView.pause();  //pausamos el video
                    play.setBackgroundResource(R.drawable.reproducir);  //cambiamos la imagen del boton a play
                } else {
                    videoView.start();  //reproducimos el video
                    play.setBackgroundResource(R.drawable.pausa);  //cambiamos la imagen del boton a pause
                }
                break;
            case R.id.btn_stop2:
                videoView.stopPlayback();
                videoView.seekTo(0);
                play.setBackgroundResource(R.drawable.reproducir);  //cambiamos la imagen del boton reproducir a play
                //comprobamos la posicion por si pulsamos play volver a reproducir ese video desde el principio
                if(posicion == 1) {
                    Uri path = Uri.parse("android.resource://com.example.multimediaApp/"
                            + R.raw.video);
                    videoView.setVideoURI(path);
                }else if(posicion == 2){
                    Uri path = Uri.parse("android.resource://com.example.multimediaApp/"
                            + R.raw.video2);
                    videoView.setVideoURI(path);
                }else{
                    Uri path = Uri.parse("android.resource://com.example.multimediaApp/"
                            + R.raw.video3);
                    videoView.setVideoURI(path);
                }
                break;
            case R.id.btn_next2:
                //comprobamos la posicion para irnos al siguiente video y reproducirlo
                if(posicion == 3) {
                    Uri path = Uri.parse("android.resource://com.example.multimediaApp/"
                            + R.raw.video3);
                    videoView.setVideoURI(path);
                    videoView.start();
                    play.setBackgroundResource(R.drawable.pausa);  //cambiamos la imagen del boton a pause
                }else if(posicion == 2){
                    posicion++;
                    Uri path = Uri.parse("android.resource://com.example.multimediaApp/"
                            + R.raw.video3);
                    videoView.setVideoURI(path);
                    videoView.start();
                    play.setBackgroundResource(R.drawable.pausa);  //cambiamos la imagen del boton a pause
                }else{
                    posicion++;
                    Uri path = Uri.parse("android.resource://com.example.multimediaApp/"
                            + R.raw.video2);
                    videoView.setVideoURI(path);
                    videoView.start();
                    play.setBackgroundResource(R.drawable.pausa);  //cambiamos la imagen del boton a pause
                }
                break;
            case R.id.btn_backward2:
                if(videoView.isPlaying()){
                    finalTime = videoView.getDuration();
                    startTime = videoView.getCurrentPosition();
                    int temp = (int) startTime;

                    if ((temp - backwardTime) > 0) {
                        startTime = startTime - backwardTime;
                        videoView.seekTo((int) startTime);
                    }
                }
                break;
            case R.id.btn_forward2:
                if(videoView.isPlaying()){
                    finalTime = videoView.getDuration();
                    startTime = videoView.getCurrentPosition();
                    int tempo = (int)startTime;

                    if((tempo+forwardTime)<=finalTime) {
                        startTime = startTime + forwardTime;
                        videoView.seekTo((int) startTime);
                    }
                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + view.getId());
        }
    }


}