package com.example.lab9_desafio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.graphics.*;
import android.graphics.Paint.Style;
import android.graphics.PorterDuff.Mode;
import android.view.*;
import android.content.*;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new GraphicsView(this));
    }
    static public class GraphicsView extends View{
        public GraphicsView(Context context){
            super(context);
        }
        @Override
        protected void onDraw(Canvas canvas)
        {
            super.onDraw(canvas);
            Paint paint= new Paint();
            Ciudad objCiudad= new Ciudad(canvas,paint);

            //Dibujado
            //Fondo
            canvas.drawColor(Color.rgb(155,255,247));
            //Dibujar Ciudadad
            objCiudad.CityDraw(canvas,paint);
            //dibujar Carretera
            objCiudad.PathDraw(canvas,paint);
            //Imagenes
            Bitmap bmpCar = BitmapFactory.decodeResource(getResources(), R.drawable.carro);
            Bitmap bmpBush = BitmapFactory.decodeResource(getResources(), R.drawable.bush);
            Bitmap bmpTree= BitmapFactory.decodeResource(getResources(), R.drawable.tree);
            objCiudad.AddImg(canvas,paint,bmpCar,bmpBush,bmpTree);
            //dibujar sol
            objCiudad.SunDraw(canvas,paint);
        }


    }
}