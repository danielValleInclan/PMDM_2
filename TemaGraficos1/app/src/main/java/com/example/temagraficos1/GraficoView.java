package com.example.temagraficos1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class GraficoView extends View {
    public GraficoView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        super.onDraw(canvas);

        canvas.drawColor(Color.WHITE);

/*Instrucciones para dibujar en el canvas, mediante un objeto
Paint y sus métodos para cambiar el color, tamaño de texto,...*/

// ALGUNOS MÉTODOS DE Paint y Canvas:

        Paint paint = new Paint();

        paint.setColor(Color.WHITE);

        canvas.drawPaint(paint); //PARA RELLENAR TODO EL CANVAS

        paint.setColor(Color.rgb(100, 20, 10)); //rojo,verde, azul

        int width = canvas.getWidth();

        int height = canvas.getHeight();

        float s = getResources().getDisplayMetrics().scaledDensity;
//densidad de escala, para que se vea igual en distintas pantallas

        paint.setAntiAlias(true);//para suavizar los bordes de lo caracteres y evitar el efecto de pixelado

        paint.setTextSize(20 * s);

        canvas.drawText("width: " + width + "height: " + height, 20 *s,
                40 * s, paint); //PARA DIBUJAR TEXTO

        canvas.drawText("escala: " + s, 20 * s, 140 * s, paint); //las coordenadas se reescalan/redimensionan multiplicándose por s

        paint.setColor(Color.BLUE);

        canvas.drawLine(0, 40 * s, width, 40 * s, paint); //coordenada inicio, coordenada fin y objeto paint

        paint.setColor(Color.YELLOW);

        canvas.drawLine(20 * s, 0, 20 * s, height, paint);
    }
}
