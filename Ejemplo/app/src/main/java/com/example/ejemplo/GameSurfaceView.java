package com.example.ejemplo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

public class GameSurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    private GameThread gameThread;
    private Paint paint;
    private float playerX, playerY;

    public GameSurfaceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        getHolder().addCallback(this);
        // Configuración de eventos táctiles y de teclado
        setFocusable(true);
        // Inicialización de objetos y recursos
        paint = new Paint();
        paint.setColor(Color.RED);
    }


    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        // Inicialización de hilo de juego
        gameThread = new GameThread(holder);
        gameThread.setGameSurfaceView(this);
        gameThread.setRunning(true);
        gameThread.start();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

        // La superficie ha cambiado (formato o dimensiones)

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        // Detener el hilo de juego y liberar recursos
        boolean retry = true;
        gameThread.setRunning(false);
        while (retry){
            try {
                gameThread.join();
                retry = false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // Manejar eventos táctiles
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                playerX = event.getX();
                playerY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                playerX = event.getX();
                playerY = event.getY();
                break;
        }
        return true;
    }

    public float getPlayerX() {
        return playerX;
    }

    public float getPlayerY() {
        return playerY;
    }

    public Paint getPaint() {
        return paint;
    }
}
