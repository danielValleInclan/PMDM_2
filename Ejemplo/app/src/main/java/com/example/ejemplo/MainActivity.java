package com.example.ejemplo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    GameSurfaceView gameSurfaceView;
    GameThread gameThread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameSurfaceView = new GameSurfaceView(this, null);
        gameThread = new GameThread(gameSurfaceView.getHolder());
        setContentView(gameSurfaceView);
    }
}