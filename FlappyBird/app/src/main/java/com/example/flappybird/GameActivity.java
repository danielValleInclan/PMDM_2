package com.example.flappybird;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GameSurfaceView gameSurfaceView = new GameSurfaceView(this);
        setContentView(gameSurfaceView);
    }
}
