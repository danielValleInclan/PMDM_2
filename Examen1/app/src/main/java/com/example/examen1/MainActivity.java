package com.example.examen1;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ListView;


public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private boolean isLooping = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ListView listView = findViewById(R.id.listView);
        final Integer[] imageList = {R.drawable.im_buho, R.drawable.im_colibri, R.drawable.im_cuervo,
                R.drawable.im_kiwi, R.drawable.im_loro, R.drawable.im_pavo, R.drawable.im_pinguino};
        final int[] soundList = {R.raw.buho, R.raw.colibri, R.raw.cuervo, R.raw.kiwi, R.raw.loro,
                R.raw.pavo, R.raw.pinguino};

        ImageAdapter adapter = new ImageAdapter(this, imageList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            int soundResourceId = soundList[position];
            playSound(soundResourceId);
        });

        listView.setOnItemLongClickListener((parent, view, position, id) -> {
            stopSound();
            return true;
        });

        listView.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                toggleLooping();
                return true;
            }
            return false;
        });
    }

    private void playSound(int soundResourceId) {
        stopSound(); // Detener cualquier reproducción anterior

        mediaPlayer = MediaPlayer.create(this, soundResourceId);
        if (mediaPlayer != null) {
            mediaPlayer.setLooping(isLooping);
            mediaPlayer.start();
        }

    }

    private void stopSound() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopSound();
    }
    private void toggleLooping() {
        isLooping = !isLooping;
        if (mediaPlayer != null) {
            mediaPlayer.setLooping(isLooping);
        }
    }
}