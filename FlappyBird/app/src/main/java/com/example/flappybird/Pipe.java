package com.example.flappybird;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Pipe {
    private float x, y; // Posición de la tubería
    private float speed;
    private Bitmap topPipeBitmap;
    private Bitmap bottomPipeBitmap;

    public Pipe(Bitmap topPipeBitmap, Bitmap bottomPipeBitmap, float initialX, float initialY, float speed) {
        this.topPipeBitmap = topPipeBitmap;
        this.bottomPipeBitmap = bottomPipeBitmap;
        this.x = initialX;
        this.y = initialY;
        this.speed = speed;
    }

    // Método para actualizar la posición de la tubería
    public void update(int width) {
        // Mover la tubería hacia la izquierda
        x -= speed;

        // Verificar si la tubería ha salido completamente de la pantalla
        if (x + topPipeBitmap.getWidth() < 0) {
            // Reiniciar la posición de la tubería al lado derecho de la pantalla
            x = width;
        }
    }

    public void draw(Canvas canvas) {
        // Dibujar la parte superior de la tubería
        canvas.drawBitmap(topPipeBitmap, x, y - topPipeBitmap.getHeight(), null);
        // Dibujar la parte inferior de la tubería
        canvas.drawBitmap(bottomPipeBitmap, x, y + 400 , null);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setX(float x) {
        this.x = x;
    }

}
