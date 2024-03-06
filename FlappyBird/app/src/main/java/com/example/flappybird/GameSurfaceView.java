package com.example.flappybird;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.appcompat.app.AlertDialog;

public class GameSurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    private GameThread gameThread;

    private boolean isTouching = false;

    private Bitmap pauseBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pause);


    public Boolean getIsTouching() {
        return this.isTouching;
    }


    public GameSurfaceView(Context context) {
        super(context);
        getHolder().addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        // Cuando la superficie de dibujo se crea, iniciar el hilo del juego
        gameThread = new GameThread(holder, this);
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

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int touchX = (int) event.getX();
        int touchY = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (touchX < pauseBitmap.getWidth() && touchY < pauseBitmap.getHeight()) {
                    gameThread.togglePause(); // Llamar al método togglePause() de GameThread
                    showPauseDialog(); // Mostrar el diálogo de paus
                }
                isTouching = true;
                break;
            case MotionEvent.ACTION_UP:
                isTouching = false;
                break;
        }
        return true;
    }

    private void showPauseDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Juego pausado");
        builder.setMessage("¿Desea reanudar el juego o salir?");
        builder.setPositiveButton("Reanudar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                gameThread.togglePause(); // Reanudar el hilo del juego
            }
        });
        builder.setNegativeButton("Salir", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Finalizar la actividad actual o salir del juego
                // Por ejemplo:
                ((Activity) getContext()).finish(); // Finalizar la actividad actual
                System.exit(0); // Salir del juego
            }
        });
        builder.setCancelable(false); // Evitar que se pueda cerrar el diálogo tocando fuera de él
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
