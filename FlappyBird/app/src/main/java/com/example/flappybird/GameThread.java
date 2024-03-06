package com.example.flappybird;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;

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

    private int lifes = 4;

    private int gapBetweenPipes;

    Timer timer = new Timer();

    Random random = new Random();

    int minGap = 3;
    int maxGap = 6;

    private int initialX;
    private int gap;

    public void togglePause() {
        isPaused = !isPaused;
    }


    public GameThread(SurfaceHolder holder, GameSurfaceView gameSurfaceView) {
        surfaceHolder = holder;
        this.gameSurfaceView = gameSurfaceView;
        running = true;
        backgroundBitmap = BitmapFactory.decodeResource(gameSurfaceView.getResources(), R.drawable.day_background);
        playerBitmap = BitmapFactory.decodeResource(gameSurfaceView.getResources(), R.drawable.bird);

        // Crear instancias de tuberías
        pipes = new ArrayList<>();
        topPipeBitmap = BitmapFactory.decodeResource(gameSurfaceView.getResources(), R.drawable.top_pipe);
        bottomPipeBitmap = BitmapFactory.decodeResource(gameSurfaceView.getResources(), R.drawable.bottom_pipe);
        initialX = gameSurfaceView.getWidth(); // Posición inicial en el borde derecho de la pantalla
        gap = 500; // Separación entre las tuberías

        pipes.add(new Pipe(topPipeBitmap, bottomPipeBitmap, initialX, getRandomYPosition(), 8.0f));
        pipes.add(new Pipe(topPipeBitmap, bottomPipeBitmap, initialX + gap, getRandomYPosition(), 8.0f));
        pipes.add(new Pipe(topPipeBitmap, bottomPipeBitmap, initialX + 2 * gap, getRandomYPosition(), 8.0f));
        pipes.add(new Pipe(topPipeBitmap, bottomPipeBitmap, initialX + 3 * gap, getRandomYPosition(), 8.0f));
    }

    private int getRandomYPosition() {
        int minGap = 100; // Altura mínima de la separación entre las tuberías
        int maxGap = gameSurfaceView.getHeight() - 2 * minGap; // Altura máxima de la separación entre las tuberías
        return  (random.nextInt(maxGap - minGap + 1) + minGap);
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
            if ((player.getX() + playerBitmap.getWidth() > pipe.getX() && player.getX() < pipe.getX() + topPipeBitmap.getWidth()) &&
                    ((player.getY() < pipe.getY() && player.getY() + playerBitmap.getHeight() > pipe.getY() - topPipeBitmap.getHeight()))) {
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

        // reinicio de tuberías

        pipes.clear();

        pipes.add(new Pipe(topPipeBitmap, bottomPipeBitmap, initialX, getRandomYPosition(), 8.0f));
        pipes.add(new Pipe(topPipeBitmap, bottomPipeBitmap, initialX + gap, getRandomYPosition(), 8.0f));
        pipes.add(new Pipe(topPipeBitmap, bottomPipeBitmap, initialX + 2 * gap, getRandomYPosition(), 8.0f));
        pipes.add(new Pipe(topPipeBitmap, bottomPipeBitmap, initialX + 3 * gap, getRandomYPosition(), 8.0f));
        lifes--;
    }


}
