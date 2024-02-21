package com.example.flappybird;

import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceHolder;

public class GameThread extends Thread{

    private GameSurfaceView gameSurfaceView;
    private SurfaceHolder surfaceHolder;
    private boolean running = false;

    public GameThread(SurfaceHolder holder) {
        surfaceHolder = holder;
    }
    public void setRunning(boolean run) {
        running = run;
    }

    @Override
    public void run() {
        while (running) {
            Canvas canvas = surfaceHolder.lockCanvas();
            if (canvas != null){
                // Operaciones de dibujo en el lienzo
                drawGame(canvas);
                surfaceHolder.unlockCanvasAndPost(canvas);
            }
        }
    }
    private void drawGame(Canvas canvas) {
        // Dibujar en el canvas
        canvas.drawColor(Color.BLACK); // Fondo negro
        canvas.drawCircle(
                gameSurfaceView.getPlayerX(),
                gameSurfaceView.getPlayerY(),
                50,
                gameSurfaceView.getPaint()); // Jugador como un círculo rojo
    }

    private void drawSquare(){

    }
    public void setGameSurfaceView(GameSurfaceView gameSurfaceView) {
        this.gameSurfaceView = gameSurfaceView;
    }

}
