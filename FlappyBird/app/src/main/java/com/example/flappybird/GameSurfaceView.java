package com.example.flappybird;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameSurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    private GameThread gameThread;


    public GameSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        getHolder().addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        // Cuando la superficie de dibujo se crea, iniciar el hilo del juego
        gameThread = new GameThread(holder, getContext());
        gameThread.setRunning(true);
        gameThread.setGameSurfaceView(this); // Llamar a setGameSurfaceView después de que GameSurfaceView se haya creado
        gameThread.start();
    }


    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
// Ajustes cuando cambia el tamaño
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        // Cuando la superficie de dibujo se destruye, detener el hilo del juego
        boolean retry = true;
        gameThread.setRunning(false);
        while (retry) {
            try {
                gameThread.join();
                retry = false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
