package com.joneikholm.spaceinvadersprep;

// GameView class will go here

// Here is our implementation of GameView
// It is an inner class.
// Note how the final closing curly brace }
// is inside SimpleGameEngine

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

// Notice we implement runnable so we have
// A thread and can override the run method.
class GameView extends SurfaceView implements Runnable {

    // This is our thread
    Thread gameThread = null;

    // This is new. We need a SurfaceHolder
    // When we use Paint and Canvas in a thread
    // We will see it in action in the draw method soon.
    SurfaceHolder ourHolder;

    // A boolean which we will set and unset
    // when the game is running- or not.
    volatile boolean playing;

    // A Canvas and a Paint object
    Canvas canvas;
    Paint paint;

    // This variable tracks the game frame rate
    long fps;

    // This is used to help calculate the fps
    private long timeThisFrame;

    // Declare an object of type Bitmap
    Bitmap bitmapBob;
    Bitmap alien;
    Bitmap missile;

    // Bob starts off not moving
    boolean isMoving = false;

    // He can walk at 150 pixels per second
    float walkSpeedPerSecond = 500;

    // He starts 10 pixels from the left
    float bobXPosition = 10;

    // When the we initialize (call new()) on gameView
    // This special constructor method runs
    public GameView(SimpleGameEngine context) {
        super(context);
        ourHolder = getHolder();
        paint = new Paint();

        // Load Bob from his .png file
        bitmapBob = BitmapFactory.decodeResource(this.getResources(), R.drawable.cannon);
        alien = BitmapFactory.decodeResource(this.getResources(), R.drawable.alien);
        missile = BitmapFactory.decodeResource(this.getResources(), R.drawable.missile);

        // Set our boolean to true - game on!
        playing = true;
    }

    @Override
    public void run() {
        while (playing) {
            long startFrameTime = System.currentTimeMillis(); // Capture the current time in milliseconds in startFrameTime
            update(); // Update the frame. Recalculate the position of the cannon
            draw(); // Draw the frame

            // Calculate the fps this frame
            // We can then use the result to
            // time animations and more.
            timeThisFrame = System.currentTimeMillis() - startFrameTime;
            if (timeThisFrame > 0) {
                fps = 1000 / timeThisFrame;
            }
        }
    }

    // Everything that needs to be updated goes in here
    // In later projects we will have dozens (arrays) of objects.
    // We will also do other things like collision detection.
    public void update() {
        // If bob is moving (the player is touching the screen)
        // then move him to the right based on his target speed and the current fps.
        if(isMoving){
            if(shoulMoveRight){
                bobXPosition = bobXPosition + (walkSpeedPerSecond / fps);
            }else {
                bobXPosition = bobXPosition - (walkSpeedPerSecond / fps);
            }
        }
    }

    private int missileY = 1950;
    private int missileX = 200;
    // Draw the newly updated scene
    public void draw() {
        if (ourHolder.getSurface().isValid()) { // Make sure our drawing surface is valid or we crash
            canvas = ourHolder.lockCanvas();// Lock the canvas ready to draw

            // Draw the background color
            canvas.drawColor(Color.argb(255,  222, 128, 182));

            // Choose the brush color for drawing
            paint.setColor(Color.argb(255,  9, 129, 0));

            // Make the text a bit bigger
            paint.setTextSize(65);

            // Display the current fps on the screen
            canvas.drawText("FPS:" + fps, 20, 40, paint);

            // Draw bob at bobXPosition, 200 pixels
            canvas.drawBitmap(bitmapBob, bobXPosition, 1950, paint);
            canvas.drawBitmap(alien, 600, 500, paint);
//            canvas.drawBitmap(missile, missileX, missileY, paint);
//            missileY -= 3;
            // draw missile
            // Draw everything to the screen
            ourHolder.unlockCanvasAndPost(canvas);
        }
    }

    // If SimpleGameEngine Activity is paused/stopped
    // shutdown our thread.
    public void pause() {
        playing = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            Log.e("Error:", "joining thread");
        }

    }

    // If SimpleGameEngine Activity is started then
    // start our thread.
    public void resume() {
        playing = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    // The SurfaceView class implements onTouchListener
    // So we can override this method and detect screen touches.

    private boolean shoulMoveRight = true;
    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {

        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {

            case MotionEvent.ACTION_DOWN: // Player has touched the screen
                isMoving = true;// Set isMoving so Bob is moved in the update method
                if(motionEvent.getX() < 700){
                    shoulMoveRight = false;
                    System.out.println("left");
                }else {
                    shoulMoveRight = true;
                    System.out.println("right");
                }
                break;

            case MotionEvent.ACTION_UP: // Player has removed finger from screen
                isMoving = false; // Set isMoving so Bob does not move
                break;
        }
        return true;
    }
}
