package com.example.miprimergrafico;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CuartoGV extends View {
    public CuartoGV(Context context){
        super(context);
    }
    public CuartoGV(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CuartoGV(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        float width = getMeasuredWidth();
        float height = getMeasuredHeight();
        float right = getRight();
        float bottom = getBottom();
        canvas.drawColor(Color.YELLOW);
        float s = getResources().getDisplayMetrics().scaledDensity;

        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(2*s);

        // Dibujar la primera línea diagonal
        canvas.drawLine(0, 0, width, height, paint);

        // Dibujar la segunda línea diagonal
        canvas.drawLine(0, height, width, 0, paint);

// Dibujar el texto
        paint.setColor(Color.BLACK);
        paint.setTextSize(30 * s); // Tamaño del texto ajustado
        paint.setTextAlign(Paint.Align.CENTER); // Alineación del texto al centro

        // Calcular la posición para centrar el texto en el centro del Canvas
        float textX = width / 2;
        float textY = (height / 2) - ((paint.descent() + paint.ascent()) / 2);

        // Dibujar cada línea de texto una debajo de la otra
        float lineHeight = paint.getTextSize() + 10; // Espacio entre cada línea de texto
        canvas.drawText("width: " + width, textX, textY, paint);
        canvas.drawText("height: " + height, textX, textY + lineHeight, paint);
        canvas.drawText("right: " + width, textX, textY + 2 * lineHeight, paint); // Establecemos "right" a la misma posición que "width" para este ejemplo
        canvas.drawText("bottom: " + height, textX, textY + 3 * lineHeight, paint); // Establecemos "bottom" a la misma posición que "height" para este ejemplo
    }
}
