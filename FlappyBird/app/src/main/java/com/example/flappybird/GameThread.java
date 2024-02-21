package com.example.flappybird;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceHolder;

public class GameThread extends Thread{

    private GameSurfaceView gameSurfaceView;
    private SurfaceHolder surfaceHolder;
    private boolean running = false;

    private Square square;

    public GameThread(SurfaceHolder holder, Context context) {
        surfaceHolder = holder;
        square = new Square(context.getResources().getDisplayMetrics().widthPixels,
                context.getResources().getDisplayMetrics().heightPixels);
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

                updateGame();
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
        square.draw(canvas); // Dibujar el cuadrado azul
    }

    private void drawSquare(){

    }
    public void setGameSurfaceView(GameSurfaceView gameSurfaceView) {
        this.gameSurfaceView = gameSurfaceView;
    }

    private void updateGame() {
        // Actualizar la lógica del juego, incluyendo el movimiento del cuadrado
        square.update();
    }

}
