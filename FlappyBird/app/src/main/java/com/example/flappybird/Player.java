package com.example.flappybird;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Player {
    private float x, y;
    private float gravity;
    private float jumpPower;
    private boolean isTouched;

    private Bitmap bitmap;

    public Player(Bitmap bitmap, float initialX, float initialY, float gravity, float jumpPower) {
        this.bitmap = bitmap;
        this.x = initialX;
        this.y = initialY;
        this.gravity = gravity;
        this.jumpPower = jumpPower;
        this.isTouched = false;
    }

    public void update() {
        if (!isTouched) {
            y += gravity; // Simular la caída del jugador aplicando la gravedad
        }
    }

    public void jump() {
        y += jumpPower; // Aplicar el impulso hacia arriba cuando el jugador salta
        isTouched = true;
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

    public boolean isTouched() {
        return isTouched;
    }

    public void setTouched(boolean touched) {
        isTouched = touched;
    }
}

