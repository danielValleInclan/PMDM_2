package com.example.killbug;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
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

            while(continuar){

                tiempo=tiempo+dt;

//movimiento rectilineo uniforme y=y+v*t

                y=y+(int)(velocidadY*dt);
                x=x+(int) (velocidadX*dt);

                //si llega abajo invertimos la velocidad:
                //si llega arriba invertimos la velocidad:

                if(y>ymax || y<0) velocidadY=-velocidadY;

                if (x>xmax || x<0) velocidadX=-velocidadX;

                postInvalidate();

                try{Thread.sleep(dt);}

                catch (InterruptedException e) {
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
            int bichoWidth = bichoBitmap.getWidth();
            int bichoHeight = bichoBitmap.getHeight();

            // Ajustar las coordenadas de dibujo para asegurarse de que la imagen esté completamente dentro del canvas
            int drawX = Math.max(0, Math.min(x - bichoWidth / 2, xmax - bichoWidth));
            int drawY = Math.max(0, Math.min(y - bichoHeight / 2, ymax - bichoHeight));


            canvas.drawPaint(paintFondo);

            paint.setTextSize(20*s);

            // Dibujar la imagen del bicho ajustada
            canvas.drawBitmap(bichoBitmap, drawX, drawY, paint);

            canvas.drawText("y= "+y,10*s,25*s,paint);
            canvas.drawText("x=" +x, 10*s, 50*s, paint);
            canvas.drawText("tiempo= " + tiempo,10*s,75*s,paint);

        }

    }//fin clase DinamicaView

}