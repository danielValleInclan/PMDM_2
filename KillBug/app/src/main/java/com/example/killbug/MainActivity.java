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

public class MainActivity extends AppCompatActivity {

    boolean continuar=true;

    float velocidadY=0.8f;
    float velocidadX=0.8f;

    int dt=10;

    int tiempo=0;

    Thread hilo=null;

    DinamicaView dinamica;

    float s;

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

        int x,y,ymax, xmax;

        Paint paintFondo,paint;

        Bitmap bichoBitmap;

        boolean bichoVisible = true;

        public DinamicaView(Context context) {

            super(context);

//Colores para el dibujo y el tamaño del texto

            paintFondo=new Paint();


            paint=new Paint();

            paintFondo.setColor(Color.WHITE);

            bichoBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bicho);

            paint.setColor(Color.BLACK);

        }

        @Override
        public void run() {
            while (continuar) {
                tiempo = tiempo + dt;
                y = y + (int) (velocidadY * dt);
                x = x + (int) (velocidadX * dt);

                if (y > ymax || y < 0) velocidadY = -velocidadY;
                if (x > xmax || x < 0) velocidadX = -velocidadX;

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

            x=w/2;

            y=0;

            xmax=w;

            ymax=h;

        }

        @Override

        public void onDraw(Canvas canvas){
            paint.setTextSize(20 * s);
            canvas.drawPaint(paintFondo);
            canvas.drawText("y= " + y, 10 * s, 25 * s, paint);
            canvas.drawText("x=" + x, 10 * s, 50 * s, paint);
            canvas.drawText("tiempo= " + tiempo, 10 * s, 75 * s, paint);
            if (bichoVisible) { // Solo dibujar el bicho si está visible
                int bichoWidth = bichoBitmap.getWidth();
                int bichoHeight = bichoBitmap.getHeight();
                int drawX = Math.max(0, Math.min(x - bichoWidth / 2, xmax - bichoWidth));
                int drawY = Math.max(0, Math.min(y - bichoHeight / 2, ymax - bichoHeight));
                canvas.drawBitmap(bichoBitmap, drawX, drawY, paint);
            }

        }
        @Override
        public boolean onTouchEvent(MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                float touchX = event.getX();
                float touchY = event.getY();
                int bichoWidth = bichoBitmap.getWidth();
                int bichoHeight = bichoBitmap.getHeight();
                int drawX = Math.max(0, Math.min(x - bichoWidth / 2, xmax - bichoWidth));
                int drawY = Math.max(0, Math.min(y - bichoHeight / 2, ymax - bichoHeight));
                if (touchX >= drawX && touchX <= drawX + bichoWidth &&
                        touchY >= drawY && touchY <= drawY + bichoHeight) {
                    bichoVisible = false; // Hacer que el bicho sea invisible si se hace clic sobre él
                    postInvalidate(); // Volver a dibujar la vista para que el bicho desaparezca
                }
            }
            return true;
        }

    }//fin clase DinamicaView

}