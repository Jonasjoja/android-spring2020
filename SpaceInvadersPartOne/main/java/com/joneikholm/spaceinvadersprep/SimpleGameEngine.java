package com.joneikholm.spaceinvadersprep;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class SimpleGameEngine extends AppCompatActivity {
     // gameView will be the view of the game
    // It will also hold the logic of the game
    // and respond to screen touches as well
    GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameView = new GameView(this); // Initialize gameView and set it as the view
        setContentView(gameView);
    }

    // This method executes when the player starts the game
    @Override
    protected void onResume() {
        super.onResume();
        gameView.resume();         // Tell the gameView resume method to execute
    }

    // This method executes when the player quits the game
    @Override
    protected void onPause() {
        super.onPause();
        // Tell the gameView pause method to execute
        gameView.pause();
    }

}