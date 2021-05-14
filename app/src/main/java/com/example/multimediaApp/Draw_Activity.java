package com.example.multimediaApp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static com.example.multimediaApp.Display.colorList;
import static com.example.multimediaApp.Display.pathList;
import static com.example.multimediaApp.Display.pincelList;
import static com.example.multimediaApp.Display.current_brush;
import static com.example.multimediaApp.Display.pincel_brush;

public class Draw_Activity extends AppCompatActivity {

    public static Path path = new Path();
    public static Paint paint_brush = new Paint();

    Button btnLimpiar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_);
        // obtenemos la referencia de las vistas a partir de sus identificadores
        btnLimpiar = (Button)findViewById(R.id.reset);

        //al pulsar sobre limpiar vaciamos el lienzo de lo dibujado anteriormente
        btnLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pathList.clear();
                colorList.clear();
                pincelList.clear();
                path.reset();
            }
        });
    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnIcoAtras:  //al pulsar el icono de la flecha volvemos a la pantalla principal
                finish();
                break;
            //le otorgamos el color seleccionado para pintar
            case R.id.color1:
                paint_brush.setColor(Color.RED);  // obtenemos el valor entero de color seleccionado  y lo establecemos como color de trazo
                currentColor(paint_brush.getColor());
                break;
            case R.id.color2:
                paint_brush.setColor(Color.BLUE);
                currentColor(paint_brush.getColor());
                break;
            case R.id.color3:
                paint_brush.setColor(Color.YELLOW);
                currentColor(paint_brush.getColor());
                break;
            case R.id.color4:
                paint_brush.setColor(Color.BLACK);
                currentColor(paint_brush.getColor());
                break;
            case R.id.color5:
                paint_brush.setColor(Color.GREEN);
                currentColor(paint_brush.getColor());
                break;
            case R.id.color6:
                paint_brush.setColor(Color.GRAY);
                currentColor(paint_brush.getColor());
                break;
            case R.id.color7:
                paint_brush.setColor(Color.MAGENTA);
                currentColor(paint_brush.getColor());
                break;
            //le otorgamos el grosor seleccionado
            case R.id.grosor1:
                paint_brush.setStrokeWidth(15);
                currentPincel((int) paint_brush.getStrokeWidth());
                break;
            case R.id.grosor2:
                paint_brush.setStrokeWidth(30);
                currentPincel((int) paint_brush.getStrokeWidth());
                break;
            case R.id.grosor3:
                paint_brush.setStrokeWidth(50);
                currentPincel((int) paint_brush.getStrokeWidth());
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + view.getId());
        }
    }

    public void currentColor(int c){
        current_brush = c;
        path = new Path();
    }

    public void currentPincel(int c){
        pincel_brush = c;
        path = new Path();
    }

}