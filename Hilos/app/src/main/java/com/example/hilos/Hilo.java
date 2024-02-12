package com.example.hilos;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class Hilo extends Thread{
    Handler handler;
    int maximo, tiempo;
    Hilo(int maximo, int tiempo, Handler handler){
        this.handler = handler;
        this.maximo = maximo;
        this.tiempo = tiempo;
    }

    @Override
    public void run() {
        for (int i = 0; i < maximo; i++) {
            try {
                Thread.sleep(tiempo);
            }catch (InterruptedException e){

            }
            Message msg = handler.obtainMessage();
            Bundle b = new Bundle();
            b.putString("message", "i=" + i + " " + currentThread().toString());
            msg.setData(b);
            handler.sendMessage(msg);
        }
    }

}
