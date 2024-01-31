package com.example.ejerciciograficos3;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class CanvasDividido2 extends View {

    private Paint paint;

    public CanvasDividido2(Context context) {
        super(context);
        init();
    }

    public CanvasDividido2(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.BLUE); // Cambiado a azul según la imagen
        paint.setStrokeWidth(25); // Ajustado según la imagen
        paint.setTextSize(50); // Aumentado el tamaño del texto
        paint.setTextAlign(Paint.Align.CENTER); // Centrado el texto
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int canvasWidth = getMeasuredWidth(); // Usando getMeasuredWidth()
        int canvasHeight = getMeasuredHeight(); // Usando getMeasuredHeight()

        // Dibujar las dos líneas diagonales del canvas
        canvas.drawLine(0, 0, canvasWidth, canvasHeight, paint);
        canvas.drawLine(canvasWidth, 0, 0, canvasHeight, paint);

        // Mostrar información en pantalla
        String[] texto = {
                "width= " + canvasWidth,
                "height= " + canvasHeight,
                "right= " + getRight(), // Usando getRight()
                "bottom= " + getBottom() // Usando getBottom()
        };

        float textX = canvasWidth / 2; // Centrado en el eje X
        float textY = canvasHeight / 2; // Centrado en el eje Y

        // Dibujar cada línea de texto por separado
        for (int i = 0; i < texto.length; i++) {
            canvas.drawText(texto[i], textX, textY + (i * paint.getTextSize()), paint);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        invalidate(); // Invalidar el canvas cuando el tamaño cambia
    }
}
