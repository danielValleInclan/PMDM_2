package com.example.flappybird;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.SurfaceHolder;

import java.util.ArrayList;
import java.util.Iterator;
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

    private Bitmap pauseBitmap;

    private Bitmap lifeBitmap;

    private int lifes = 4;

    private int gapBetweenPipes;

    Timer timer = new Timer();

    Random random = new Random();

    int minGap = 3;
    int maxGap = 6;

    private int initialX;
    private int gap;

    private int record = 0;

    private float speedPipe = 6.0f;

    private int comboPassed = 0;

    public void togglePause() {
        if (isPaused){
            isPaused = false;
        } else {
            isPaused = true;
        }
    }


    public GameThread(SurfaceHolder holder, GameSurfaceView gameSurfaceView) {
        surfaceHolder = holder;
        this.gameSurfaceView = gameSurfaceView;
        running = true;
        backgroundBitmap = BitmapFactory.decodeResource(gameSurfaceView.getResources(), R.drawable.day_background);
        playerBitmap = BitmapFactory.decodeResource(gameSurfaceView.getResources(), R.drawable.bird);
        pauseBitmap = BitmapFactory.decodeResource(gameSurfaceView.getResources(), R.drawable.pause);
        lifeBitmap = BitmapFactory.decodeResource(gameSurfaceView.getResources(), R.drawable.life);

        // Crear instancias de tuberías
        pipes = new ArrayList<>();
        topPipeBitmap = BitmapFactory.decodeResource(gameSurfaceView.getResources(), R.drawable.top_pipe);
        bottomPipeBitmap = BitmapFactory.decodeResource(gameSurfaceView.getResources(), R.drawable.bottom_pipe);
        initialX = gameSurfaceView.getWidth(); // Posición inicial en el borde derecho de la pantalla
        gap = 600; // Separación entre las tuberías

        pipes.add(new Pipe(topPipeBitmap, bottomPipeBitmap, initialX, getRandomYPosition(), speedPipe));
        pipes.add(new Pipe(topPipeBitmap, bottomPipeBitmap, initialX + gap, getRandomYPosition(), speedPipe));
        pipes.add(new Pipe(topPipeBitmap, bottomPipeBitmap, initialX + 2 * gap, getRandomYPosition(), speedPipe));
        pipes.add(new Pipe(topPipeBitmap, bottomPipeBitmap, initialX + 3 * gap, getRandomYPosition(), speedPipe));
    }

    private int getRandomYPosition() {
        int minGap = 200; // Altura mínima de la separación entre las tuberías
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
        gameSurfaceView.showDeadDialog(record);
    }

    private void drawGame(Canvas canvas) {
        canvas.drawColor(Color.BLACK); // Fondo negro
        canvas.drawBitmap(backgroundBitmap, 0, 0, null); // Dibujar la imagen de fondo
        player.draw(canvas); // Dibujar al jugador

        for (Pipe pipe : pipes) {
            pipe.draw(canvas);
        }

        // Pintar el texto con la puntiación
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(50); // Tamaño del texto
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD)); // Tipo de fuente en negrita
        paint.setShadowLayer(5, 5, 5, Color.GRAY); // Añadir sombra al texto
        paint.setUnderlineText(true); // Subrayar el texto
        paint.setFakeBoldText(true); // Hacer que el texto parezca más grueso
        paint.setTextSkewX(-0.25f); // Inclinar el texto hacia la izquierda
        paint.setAntiAlias(true); // Suavizar los bordes del texto

        String livesText = "Puntuacion: " + record;
        // Obtener el ancho y la altura del texto
        float textWidth = paint.measureText(livesText);
        Paint.FontMetrics fm = paint.getFontMetrics();
        float textHeight = fm.descent - fm.ascent;
        canvas.drawText(livesText, canvas.getWidth() - textWidth - 50, textHeight + 20, paint); // Dibujar el texto


        canvas.drawBitmap(pauseBitmap, 20, 20, null); // dibujar pause

        // Dibujar imágenes de vida
        float startX = canvas.getWidth() / 2.5f; // Posición inicial para dibujar las imágenes de vida
        float startY = 20; // Altura fija para dibujar las imágenes de vida

        // Dibujar una imagen de vida por cada vida restante
        for (int i = 0; i < lifes; i++) {
            canvas.drawBitmap(lifeBitmap, startX + (i * lifeBitmap.getWidth() * 1.2f), startY, null);
        }

    }

    public void setGameSurfaceView(GameSurfaceView gameSurfaceView) {
        this.gameSurfaceView = gameSurfaceView;

        // Inicializar square y player después de que gameSurfaceView se haya configurado
        if (gameSurfaceView != null) {
            player = new Player(playerBitmap, gameSurfaceView.getWidth() / 7, gameSurfaceView.getHeight() / 2, -10.0f, 0.0f, 7.0f);

            // Ajustar el tamaño de la imagen de fondo para que ocupe toda la pantalla
            backgroundBitmap = Bitmap.createScaledBitmap(backgroundBitmap, gameSurfaceView.getWidth(), gameSurfaceView.getHeight(), true);

        }
    }

    private void updateGame() {
        // Llamada al método update de Player para actualizar su estado
        player.update(gameSurfaceView.getIsTouching());
        // Otras actualizaciones del juego, como la lógica de colisión, actualización de objetos, etc.

        Iterator<Pipe> iterator = pipes.iterator(); // Evita un error al intentar iterar sobre una lista que se esta modificando
        while (iterator.hasNext()) {
            Pipe pipe = iterator.next();
            pipe.update(gameSurfaceView.getWidth());

            // Verificar si el jugador ha pasado la tubería
            if (pipe.getX() + topPipeBitmap.getWidth() < player.getX() && !pipe.isPassed()) {
                pipe.setPassed(true); // Marcar la tubería como pasada
                record += 10; // Incrementar la puntuación en 10
                comboPassed += 1;
                if (comboPassed == 3 && lifes < 4){
                    lifes += 1;
                    comboPassed = 0;
                }
            }

            if (player.getX() + playerBitmap.getWidth() > pipe.getX() && player.getX() < pipe.getX() + bottomPipeBitmap.getWidth() &&
                    player.getY() + playerBitmap.getHeight() > pipe.getY() + 400) {
                playerDie();
                // Detener el bucle para evitar modificaciones adicionales después de la llamada a playerDie()
                break;
            }
            if ((player.getX() + playerBitmap.getWidth() > pipe.getX() && player.getX() < pipe.getX() + topPipeBitmap.getWidth()) &&
                    ((player.getY() < pipe.getY() && player.getY() + playerBitmap.getHeight() > pipe.getY() - topPipeBitmap.getHeight()))) {
                playerDie();
                // Detener el bucle para evitar modificaciones adicionales después de la llamada a playerDie()
                break;
            }
        }

        // Colisión con la parte inferior del juego
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

        pipes.add(new Pipe(topPipeBitmap, bottomPipeBitmap, initialX, getRandomYPosition(), speedPipe));
        pipes.add(new Pipe(topPipeBitmap, bottomPipeBitmap, initialX + gap, getRandomYPosition(), speedPipe));
        pipes.add(new Pipe(topPipeBitmap, bottomPipeBitmap, initialX + 2 * gap, getRandomYPosition(), speedPipe));
        pipes.add(new Pipe(topPipeBitmap, bottomPipeBitmap, initialX + 3 * gap, getRandomYPosition(), speedPipe));
        lifes = lifes -1;
        if (lifes < 1){
            running = false;
        }
    }


}
