package com.example.multimediaApp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        //según la card pulsada abriremos una actividad u otra
        switch (view.getId()){
            case R.id.cardDraw:
                Intent i = new Intent(getApplicationContext(), Draw_Activity.class);
                startActivity(i);  //si pulsamos sobre la card de dibujo abrimos la actividad Draw_Activity
                break;
            case R.id.cardMusic:
                Intent it = new Intent(getApplicationContext(), Music_Activity.class);
                startActivity(it);  //si pulsamos sobre la card de música abrimos la actividad Music_Activity
                break;
            case R.id.cardVideo:
                Intent inte = new Intent(getApplicationContext(), Video_Activity.class);
                startActivity(inte);  //si pulsamos sobre la card de video abrimos la actividad Video_Activity
                break;
            case R.id.cardPhoto:
                Intent intent = new Intent(getApplicationContext(), Photo_Activity.class);
                startActivity(intent);  //si pulsamos sobre la card de cámara abrimos la actividad Photo_Activity
                break;
        }
    }
}