  package com.example.hilos;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.TextView;

  public class MainActivity extends AppCompatActivity {

      private TextView textView;
      private Handler handler;

      @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        handler = new Handler(Looper.getMainLooper()) {
          @Override
          public void handleMessage(Message msg) {
              Bundle bundle = msg.getData();
              int i = bundle.getInt("i"); // Obtenemos el valor de 'i'
              String threadInfo = bundle.getString("message");
              textView.append(threadInfo + "\n");
          }
        };

        Hilo hilo1 = new Hilo(10, 100, handler);
        Hilo hilo2 = new Hilo(14, 200, handler);

        hilo2.setPriority(7);

        hilo1.start();
        hilo2.start();
    }
}