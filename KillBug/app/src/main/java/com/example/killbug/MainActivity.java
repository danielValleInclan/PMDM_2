package com.example.killbug;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    boolean continuar=true;
    int dt=10;
    int tiempo=0;
    Thread hilo=null;
    DinamicaView dinamica;
    float s;
    int numeroBichos = 5;

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        dinamica=new DinamicaView(this);

        setContentView(dinamica);

        s=getResources().getDisplayMetrics().density;

        hilo=new Thread(dinamica);

        hilo.start();

    }

//detenemos el hilo si pausa

    @Override

    public void onPause(){

        super.onPause();

        continuar=false;

    }

//reiniciamos el hilo si resume

    @Override

    public void onResume(){

        super.onResume();

        continuar=true;

        if(!hilo.isAlive()){

            hilo=new Thread(dinamica);

            hilo.start();

        }

    }

    class DinamicaView extends View implements Runnable{

        int ymax, xmax;

        Paint paintFondo,paint;

        List<Bicho> bichos = new ArrayList<>();

        public DinamicaView(Context context) {

            super(context);
            //Colores para el dibujo y el tamaño del texto
            paintFondo=new Paint();
            paint=new Paint();
            paintFondo.setColor(Color.WHITE);
            paint.setColor(Color.BLACK);

        }

        @Override
        public void run() {
            while (continuar) {
                tiempo = tiempo + dt;

                for (Bicho bicho : bichos) {
                    bicho.move();

                    // Verificar los límites de la pantalla para cada bicho individualmente
                    if (bicho.getY() > ymax || bicho.getY() < 0) {
                        bicho.reverseY();
                    }
                    if (bicho.getX() > xmax || bicho.getX() < 0) {
                        bicho.reverseX();
                    }
                }

                postInvalidate();

                try {
                    Thread.sleep(dt);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }



//obtiene geometria del canvas

        @Override

        protected void onSizeChanged(int w,int h,int oldw,int oldh){

            xmax = w - BitmapFactory.decodeResource(getResources(), R.drawable.bicho).getWidth() / 2;
            ymax = h - BitmapFactory.decodeResource(getResources(), R.drawable.bicho).getHeight() / 2;
            bichos.clear();
            Random random = new Random();
            for (int i = 0; i < numeroBichos; i++) {
                int x = random.nextInt(w);
                int y = random.nextInt(h);
                bichos.add(new Bicho(x, y, BitmapFactory.decodeResource(getResources(), R.drawable.bicho)));
            }

        }

        @Override

        public void onDraw(Canvas canvas){
            paint.setTextSize(20 * s);
            canvas.drawPaint(paintFondo);
            canvas.drawText("tiempo= " + tiempo, 10 * s, 75 * s, paint);
            for (Bicho bicho : bichos) {
                if (bicho.isVisible()) {
                    canvas.drawBitmap(bicho.getBichoBitmap(), bicho.getX(), bicho.getY(), paint);
                }
            }

        }
        @Override
        public boolean onTouchEvent(MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                float touchX = event.getX();
                float touchY = event.getY();
                for (Bicho bicho : bichos) {
                    if (bicho.isVisible() && bicho.isTouched(touchX, touchY)) {
                        bicho.setVisible(false);
                        postInvalidate();
                        if (allBichosDead()) {
                            // Mostrar mensaje de felicitaciones
                            // Por ejemplo:
                            Toast.makeText(getContext(), "¡Enhorabuena, has matado a todos los bichos en " + tiempo + " segundos!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
            return true;
        }
        private boolean allBichosDead() {
            for (Bicho bicho : bichos) {
                if (bicho.isVisible()) {
                    return false;
                }
            }
            return true;
        }

    }//fin clase DinamicaView

    class Bicho {
        private int x, y;
        private float velocidadX, velocidadY;
        private final Bitmap bichoBitmap;
        private boolean visible = true;

        public Bicho(int x, int y, Bitmap bichoBitmap) {
            this.x = x;
            this.y = y;
            this.bichoBitmap = bichoBitmap;
            // Inicializar velocidades aleatorias
            Random random = new Random();
            velocidadX = random.nextFloat() * 2 - 1; // Velocidades entre -1 y 1
            velocidadY = random.nextFloat() * 2 - 1;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
        public Bitmap getBichoBitmap() {
            return bichoBitmap;
        }

        public boolean isVisible() {
            return visible;
        }

        public void setVisible(boolean visible) {
            this.visible = visible;
        }

        public boolean isTouched(float touchX, float touchY) {
            return touchX >= x && touchX <= x + bichoBitmap.getWidth() &&
                    touchY >= y && touchY <= y + bichoBitmap.getHeight();
        }

        public void move() {
            x = x + (int) (velocidadX * dt);
            y = y + (int) (velocidadY * dt);
        }

        public void reverseX() {
            velocidadX = -velocidadX;
        }

        public void reverseY() {
            velocidadY = -velocidadY;
        }
    }

}