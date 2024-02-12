package com.example.miprimergrafico;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class TercerGV extends View {
    public TercerGV(Context context) {
        super(context);
    }

    public TercerGV(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TercerGV(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();

        canvas.drawColor(Color.WHITE);

        // Dibujar renglones horizontales equiespaciados
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        float yIncrement = height / 10f;
        for (int i = 1; i < 10; i++) {
            float y = yIncrement * i;
            canvas.drawLine(0, y, width, y, paint);
        }

        // Mostrar información adicional en pantalla
        float s = getResources().getDisplayMetrics().scaledDensity;
        String escala = "Escala: " + s;
        String anchura = "Anchura: " + width;
        String altura = "Altura: " + height;
        String relacionAspecto = "Relación de aspecto: " + ((float) height / (float) width);

        paint.setTextSize(30);
        paint.setColor(Color.RED);
        canvas.drawText(escala, 20, 40, paint);
        canvas.drawText(anchura, 20, 80, paint);
        canvas.drawText(altura, 20, 120, paint);
        canvas.drawText(relacionAspecto, 20, 160, paint);
    }
}
