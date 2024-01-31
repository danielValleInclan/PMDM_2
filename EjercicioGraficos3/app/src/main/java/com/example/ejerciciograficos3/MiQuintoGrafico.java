package com.example.ejerciciograficos3;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class MiQuintoGrafico extends View {

    private Paint paint;
    private float density;

    public MiQuintoGrafico(Context context) {
        super(context);
        init();
    }

    public MiQuintoGrafico(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
        density = getResources().getDisplayMetrics().density;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Dibujar cinco círculos medio juntos y alineados verticalmente
        float radio = 30 * density;
        float offsetCirculos = 1.5f * radio; // Ajuste para que los círculos estén medio juntos
        float posYCirculos = 50 * density;
        int[] coloresCirculos = {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.MAGENTA}; // Colores de los círculos
        for (int i = 0; i < 5; i++) {
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(coloresCirculos[i]); // Color del círculo
            canvas.drawCircle((i + 1) * offsetCirculos, posYCirculos, radio, paint);
        }

        // Dibujar cinco cuadrados separados y alineados verticalmente
        float ladoCuadrado = 60 * density;
        float offsetCuadrados = 80 * density; // Espacio entre los cuadrados
        float posYCuadrados = 150 * density;
        int[] coloresCuadrados = {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.MAGENTA}; // Colores de los cuadrados
        for (int i = 0; i < 5; i++) {
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(coloresCuadrados[i]); // Color del cuadrado
            canvas.drawRect((i + 1) * offsetCuadrados, posYCuadrados,
                    (i + 1) * offsetCuadrados + ladoCuadrado, posYCuadrados + ladoCuadrado, paint);
        }
    }
}
