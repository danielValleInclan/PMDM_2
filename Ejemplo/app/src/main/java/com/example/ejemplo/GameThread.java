package com.example.ejemplo;

import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceHolder;

public class GameThread extends Thread{
    private SurfaceHolder holder;
    private GameSurfaceView gameSurfaceView;
    boolean running = false;

    public GameThread(SurfaceHolder surfaceHolder){
        this.holder = surfaceHolder;
    }

    public void setRunning(boolean run) {
        running = run;
    }

    @Override
    public void run() {
        while (running) {
            Canvas canvas = holder.lockCanvas();
            if (canvas != null) {
                // Operaciones de dibujo en el lienzo
                drawGame(canvas);
                holder.unlockCanvasAndPost(canvas);
            }
        }
    }
    private void drawGame(Canvas canvas) {
        // Dibujar en el canvas
        canvas.drawColor(Color.BLACK); // Fondo negro
        canvas.drawCircle(gameSurfaceView.getPlayerX(), gameSurfaceView.getPlayerY(), 50, gameSurfaceView.getPaint()); // Jugador como un círculo rojo
    }
}
