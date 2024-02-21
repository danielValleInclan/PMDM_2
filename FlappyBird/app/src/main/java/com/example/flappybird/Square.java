package com.example.flappybird;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Square {
    private float x, y; // Posición del cuadrado
    private float width, height; // Tamaño del cuadrado
    private float speed; // Velocidad del cuadrado
    private Paint paint; // Pincel para dibujar el cuadrado

    public Square(float screenWidth, float screenHeight) {
        // Inicializar posición aleatoria en el lado derecho de la pantalla
        x = screenWidth;
        y = (float) (Math.random() * screenHeight);

        // Tamaño y velocidad del cuadrado
        width = 100;
        height = 100;
        speed = 10;

        // Configuración del pincel para dibujar el cuadrado
        paint = new Paint();
        paint.setColor(Color.BLUE);
    }

    public void update() {
        // Mover el cuadrado hacia la izquierda
        x -= speed;
    }

    public void draw(Canvas canvas) {
        // Dibujar el cuadrado en el lienzo
        canvas.drawRect(x, y, x + width, y + height, paint);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }
}
