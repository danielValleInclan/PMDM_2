package com.example.burbujaveloz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    boolean continuar = true;
    Thread hilo = null;
    DinamicaView dinamica;
    int puntuacion = 0;
    float s;



    @Override
    public void onPause(){
        super.onPause();
        continuar = false;
    }

    @Override
    public void onResume(){
        super.onResume();
        continuar=true;

        if(!hilo.isAlive()){

            hilo=new Thread(dinamica);

            hilo.start();

        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dinamica=new DinamicaView(this);

        setContentView(dinamica);

        s=getResources().getDisplayMetrics().density;

        hilo=new Thread(dinamica);

        hilo.start();
    }

    class DinamicaView extends View implements Runnable{

        int x1, x2, y1, y2, ymax, xmax;

        Paint paintFondo,paintParticula,paint;

        public DinamicaView (Context context){
            super(context);

            paintFondo=new Paint();

            paintParticula=new Paint();

            paint=new Paint();

            paintFondo.setColor(Color.WHITE);

            paintParticula.setColor(Color.RED);

            paint.setColor(Color.BLACK);
        }

        @Override
        public void run(){
            Random random = new Random();
            while (continuar) {
                if (xmax > 0 && ymax > 0) {
                    x1 = random.nextInt(xmax);
                    y1 = random.nextInt(ymax);
                    x2 = random.nextInt(xmax);
                    y2 = random.nextInt(ymax);
                    postInvalidate();
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        protected void onSizeChanged(int w, int h, int oldw, int oldh) {
            super.onSizeChanged(w, h, oldw, oldh);
            xmax = w;
            ymax = h;
        }


        @Override
        public void onDraw(Canvas canvas){
            canvas.drawPaint(paintFondo);

            paint.setTextSize(20*s);

            canvas.drawCircle(x1, y1, 30 * s, paintParticula);
            canvas.drawCircle(x2, y2, 30 * s, paintParticula);

            canvas.drawText("Puntuación: " + puntuacion, 10*s, 20*s, paint);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            float touchX = event.getX();
            float touchY = event.getY();

            if ((Math.abs(touchX - x1) <= 30 * s && Math.abs(touchY - y1) <= 30 * s) ||
                    (Math.abs(touchX - x2) <= 30 * s && Math.abs(touchY - y2) <= 30 * s)) {
                puntuacion++;
                invalidate();
            }

            return super.onTouchEvent(event);
        }
    }
}