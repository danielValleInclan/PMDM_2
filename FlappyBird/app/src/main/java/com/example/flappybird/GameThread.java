package com.example.flappybird;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.SurfaceHolder;

import java.util.ArrayList;
import java.util.List;

public class GameThread extends Thread{

    private GameSurfaceView gameSurfaceView;
    private SurfaceHolder surfaceHolder;
    private boolean running = false;
    private Player player;

    private boolean isPaused = false;

    private Bitmap playerBitmap;

    private Bitmap backgroundBitmap;

    private List<Pipe> pipes;

    private Bitmap topPipeBitmap;
    private Bitmap bottomPipeBitmap;

    private int lives = 4;

    private int gapBetweenPipes;


    public void togglePause() {
        isPaused = !isPaused;
    }


    public GameThread(SurfaceHolder holder, GameSurfaceView gameSurfaceView) {
        surfaceHolder = holder;
        this.gameSurfaceView = gameSurfaceView;
        running = true;
        backgroundBitmap = BitmapFactory.decodeResource(gameSurfaceView.getResources(), R.drawable.day_background);
        playerBitmap = BitmapFactory.decodeResource(gameSurfaceView.getResources(), R.drawable.bird);

        gapBetweenPipes = gameSurfaceView.getHeight() / 5;

        Log.d("PipeDetails", "Tamaño del gap: " + gapBetweenPipes);

        // Crear instancias de tuberías
        pipes = new ArrayList<>();
        topPipeBitmap = BitmapFactory.decodeResource(gameSurfaceView.getResources(), R.drawable.top_pipe);
        bottomPipeBitmap = BitmapFactory.decodeResource(gameSurfaceView.getResources(), R.drawable.bottom_pipe);
        pipes.add(new Pipe(topPipeBitmap, bottomPipeBitmap, 0, gapBetweenPipes, 10.0f));

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
                    if (canvas != null && !isPaused) {
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
        canvas.drawBitmap(backgroundBitmap, 0, 0, null); // Dibujar la imagen de fondo
        player.draw(canvas); // Dibujar al jugador

        for (Pipe pipe : pipes) {
            pipe.draw(canvas);
        }
    }

    public void setGameSurfaceView(GameSurfaceView gameSurfaceView) {
        this.gameSurfaceView = gameSurfaceView;

        // Inicializar square y player después de que gameSurfaceView se haya configurado
        if (gameSurfaceView != null) {
            player = new Player(playerBitmap, gameSurfaceView.getWidth() / 7, gameSurfaceView.getHeight() / 2, -15.0f, 0.0f, 10.0f);

            // Ajustar el tamaño de la imagen de fondo para que ocupe toda la pantalla
            backgroundBitmap = Bitmap.createScaledBitmap(backgroundBitmap, gameSurfaceView.getWidth(), gameSurfaceView.getHeight(), true);

        }
    }

    private void updateGame() {
        // Llamada al método update de Player para actualizar su estado
        player.update(gameSurfaceView.getIsTouching());
        // Otras actualizaciones del juego, como la lógica de colisión, actualización de objetos, etc.

        for (Pipe pipe : pipes) {
            pipe.update(gameSurfaceView.getWidth());
            if (player.getX() + playerBitmap.getWidth() > pipe.getX() && player.getX() < pipe.getX() + bottomPipeBitmap.getWidth() &&
                    player.getY() + playerBitmap.getHeight() > pipe.getY() + 400) {
                playerDie();
            }
        }

        if (player.getY() >= gameSurfaceView.getHeight() - playerBitmap.getHeight()) {
            // Reiniciar el juego
            playerDie();
        }
    }

    public void playerDie() {
        player.setY(gameSurfaceView.getHeight() / 2); // Restablecer las propiedades del jugador
        player.setX(gameSurfaceView.getWidth() / 5);
        // Otras reinicializaciones de variables de juego necesarias
    }


}
