package com.example.miprimergrafico;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class SegundoGV extends View {
    public SegundoGV(Context context) {
        super(context);
    }

    public SegundoGV(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SegundoGV(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);

//pinta el canvas de blanco

        Paint paint = new Paint();

        paint.setColor(Color.WHITE);

        canvas.drawPaint(paint);

        int width = canvas.getWidth();

        int height = canvas.getHeight();

//densidad de la escala

        float s = getResources().getDisplayMetrics().scaledDensity;

        paint.setColor(Color.BLACK);

        paint.setTextSize(25 * s);

        paint.setAntiAlias(true);

//escribe en el canvas

        canvas.drawText("width: " + width + " height: " + height, 20 * s, 40 * s, paint);

        canvas.drawText("Escala: " + s, 20 * s, 65 * s, paint);

//en el canvas caben 10 Ms de tamaño 36

        paint.setTextSize(36 * s);

        canvas.drawText("MMMMMMMMMM", 20 * s, 100 * s, paint);

//Texto pequeño

        paint.setTextSize(12 * s);

        String quijote = "En un lugar de la mancha de cuyo nombre no quiero acordarme";

        canvas.drawText(quijote, 20 * s, 120 * s, paint);

        paint.setTextSize(20 * s);

        canvas.drawText("Longitud= " + quijote.length(), 20 * s, 140 * s, paint);

//dibuja rectas horizontal y vertical

        paint.setColor(Color.rgb(100, 20, 0));

        canvas.drawLine(0, 40 * s, width, 40 * s, paint);

        paint.setColor(Color.rgb(0, 100, 20));

        canvas.drawLine(20 * s, 0, 20 * s, height, paint);
    }
}
