package com.example.flappybird;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Player {
    private float x, y; // Posición del jugador
    private float velocityY; // Velocidad vertical
    private float jumpPower; // Potencia de salto
    private float fallSpeed; // Velocidad de caída constante

    private Bitmap bitmap;

    public Player(Bitmap bitmap, float initialX, float initialY, float jumpPower, float velocityY, float fallSpeed) {
        this.bitmap = bitmap;
        this.x = initialX;
        this.y = initialY;
        this.jumpPower = jumpPower;
        this.velocityY = velocityY;
        this.fallSpeed = fallSpeed;
    }

    // Método para actualizar la posición del jugador
    public void update(boolean isTouching) {
        if (isTouching) {
            // Si se detecta un toque en la pantalla, el jugador realiza un salto hacia arriba
            velocityY = jumpPower; // Asignar la potencia de salto
        } else {
            // Si no se detectan toques en la pantalla, el jugador cae a una velocidad constante
            velocityY = fallSpeed; // Asignar la velocidad de caída constante
        }

        // Actualizar la posición vertical del jugador
        y += velocityY;

        // Verificar si el jugador ha alcanzado la parte superior de la pantalla
        if (y < 0) {
            y = 0; // Establecer la posición del jugador en la parte superior de la pantalla
        }
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, x, y, null);
    }


    // Métodos getter y setter
    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

}

