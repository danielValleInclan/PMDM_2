package com.example.flappybird;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceHolder;

public class GameThread extends Thread{

    private GameSurfaceView gameSurfaceView;
    private SurfaceHolder surfaceHolder;
    private boolean running = false;
    private Player player;

    public GameThread(SurfaceHolder holder, GameSurfaceView gameSurfaceView) {
        surfaceHolder = holder;
        this.gameSurfaceView = gameSurfaceView;
        running = true;
    }
    public void setRunning(boolean run) {
        running = run;
    }

    @Override
    public void run() {
        while (running) {
            Canvas canvas = null;
            try {
                canvas = surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    if (canvas != null) {
                        updateGame();
                        drawGame(canvas);
                    }
                }
            } finally {
                if (canvas != null) {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }

    private void drawGame(Canvas canvas) {
        canvas.drawColor(Color.BLACK); // Fondo negro
        player.draw(canvas); // Dibujar al jugador
    }

    public void setGameSurfaceView(GameSurfaceView gameSurfaceView) {
        this.gameSurfaceView = gameSurfaceView;

        // Inicializar square y player después de que gameSurfaceView se haya configurado
        if (gameSurfaceView != null) {
            Bitmap playerBitmap = BitmapFactory.decodeResource(gameSurfaceView.getResources(), R.drawable.bird);
            player = new Player(playerBitmap, gameSurfaceView.getWidth() / 5, gameSurfaceView.getHeight() / 2, -25.0f, 0.0f, 10.0f);
        }
    }

    private void updateGame() {
        // Llamada al método update de Player para actualizar su estado
        player.update(gameSurfaceView.getIsTouching());
        // Otras actualizaciones del juego, como la lógica de colisión, actualización de objetos, etc.
    }

}
