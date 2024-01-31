package com.example.ejerciciograficos3;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class CanvasDivididoView extends View {

    private Paint paint;
    private int numDivisiones = 20; // Ajustado según la imagen

    public CanvasDivididoView(Context context) {
        super(context);
        init();
    }

    public CanvasDivididoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(2);
        paint.setTextSize(20); // Ajustado según la imagen
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int canvasWidth = canvas.getWidth();
        int canvasHeight = canvas.getHeight();

        // Dibujar renglones horizontales equiespaciados
        for (int i = 1; i < numDivisiones; i++) {
            int y = canvasHeight * i / numDivisiones;
            canvas.drawLine(0, y, canvasWidth, y, paint);
        }

        // Mostrar información en pantalla
        String texto = "scale= " + getResources().getDisplayMetrics().density +
                "\nwidth= " + canvasWidth +
                "\nheight= " + canvasHeight +
                "\nratio= " + ((float) canvasHeight / canvasWidth);

        float textX = 10;
        float textY = canvasHeight / 2; // Posicionado en el centro del canvas

        canvas.drawText(texto, textX, textY, paint);
    }
}
