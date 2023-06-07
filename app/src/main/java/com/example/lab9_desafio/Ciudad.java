package com.example.lab9_desafio;
import android.os.Bundle;
import android.graphics.*;
import android.graphics.Paint.Style;
import android.graphics.PorterDuff.Mode;
import android.util.Log;
import android.view.*;
import android.content.*;

import java.util.Random;

public class Ciudad {
    //Properties
    Canvas canvas = null;
    Paint paint = null;

    public Ciudad(Canvas canvas, Paint paint) {
        this.canvas = canvas;
        this.paint = paint;
    }

    public void SunDraw(Canvas canvas, Paint paint) {
        Path objSun = new Path();
        try {
            //Puntero 0,0
            canvas.translate(0, 0);
            objSun.moveTo(60, 50);
            //Borde
            paint.setColor(Color.BLACK);
            paint.setStyle(Style.STROKE);
            paint.setStrokeWidth(2);
            objSun.addCircle(60, 50, 50, Path.Direction.CCW);
            //Relleno amarillo
            paint.setColor(Color.YELLOW);
            paint.setStyle(Style.FILL_AND_STROKE);
            paint.setStrokeWidth(0);
            objSun.addCircle(60, 50, 50, Path.Direction.CCW);
            canvas.drawPath(objSun, paint);
            canvas.restore();


        } catch (Exception e) {
            Log.e("DrawSun", e.getMessage(), e);
        }

    }

    public void PathDraw(Canvas canvas, Paint paint) {
        DashPathEffect pathEffect = new DashPathEffect(new float[]{20, 10}, 0);
        //Carretera
        paint.setColor(Color.GRAY);
        canvas.drawRect(0, 400, canvas.getWidth(), 600, paint);
        //Lineas
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(10);
        paint.setPathEffect(pathEffect);
        int dashWidth = 20;
        int dashSpacing = 30;
        int startX = (dashSpacing - dashWidth) / 2;
        for (int x = startX; x < canvas.getWidth(); x += dashSpacing) {
            canvas.drawLine(x, 450, x + dashWidth, 450, paint);
        }


    }

    public void AddImg(Canvas canvas, Paint paint, Bitmap bmpCar, Bitmap bmpBush, Bitmap bmpTree) {
        try {
            int carWidth = bmpCar.getWidth();
            int carHeight = bmpCar.getHeight();
            int carX = (canvas.getWidth() - carWidth) / 2;
            int carY = 480 - carHeight;
            canvas.drawBitmap(bmpCar, 0, carY, null);
            canvas.drawBitmap(bmpBush, 286, 470, null);
            canvas.drawBitmap(bmpTree, 270, 305, null);
        } catch (Exception e) {
            Log.e("AddImg", e.getMessage(), e);
        }
    }

    public void CityDraw(Canvas canvas, Paint paint) {
        int numBuildings = 5; // Número de edificios
        int totalWidth = canvas.getWidth(); // Anchura de cada edificio
        int maxBuildingHeight = canvas.getHeight() - 300; // Altura de los edificios, abarca desde la parte superior de la carretera hasta la parte inferior de la pantalla

        Random random = new Random(); // Objeto para generar números aleatorios

        int[] buildingHeights = new int[numBuildings]; // Arreglo para almacenar las alturas de los edificios

        // Generar alturas aleatorias para los edificios
        for (int i = 0; i < numBuildings; i++) {
            buildingHeights[i] = random.nextInt(maxBuildingHeight) + 1; // Altura aleatoria entre 1 y maxBuildingHeight (incluyendo ambos)
        }

        int buildingBaseY = 400; // Posición vertical de la base de los edificios

        for (int i = 0; i < numBuildings; i++) {
            int buildingWidth = totalWidth / numBuildings; // Anchura de cada edificio
            int buildingHeight = buildingHeights[i]; // Altura del edificio actual

            int buildingX = i * buildingWidth; // Posición horizontal del edificio actual

            // Dibujar edificio
            paint.setColor(Color.DKGRAY);
            canvas.drawRect(buildingX, buildingBaseY - buildingHeight, buildingX + buildingWidth, buildingBaseY, paint);

            // Dibujar ventanas en el edificio
            int windowSize = buildingWidth / 10; // Tamaño de cada ventana
            int windowHeight = buildingHeight / 8; // Altura de cada piso
            int numFloors = buildingHeight / windowHeight; // Número de pisos en el edificio

            int windowSpacingX = windowSize / 2; // Espacio horizontal entre ventanas
            int windowSpacingY = windowHeight / 2; // Espacio vertical entre ventanas

            for (int floor = 0; floor < numFloors; floor++) {
                for (int window = 0; window < numBuildings; window++) {
                    int windowX = buildingX + window * windowSize + (window + 1) * windowSpacingX;
                    int windowY = buildingBaseY - buildingHeight + floor * windowHeight + (floor + 1) * windowSpacingY;

                    paint.setColor(Color.YELLOW); // Color de las ventanas
                    canvas.drawRect(windowX, windowY, windowX + windowSize, windowY + windowSize, paint);
                }
            }
        }
    }
}
