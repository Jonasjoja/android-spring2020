package com.joneikholm.imagedemowithrefactor;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import com.joneikholm.imagedemowithrefactor.controller.ImageController;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    public ImageView imageView;
    private ImageController ic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageView);
        ic = new ImageController(this);
    }

    public void photoRollBtnPressed(View view){
        // 1. make an intent
        // start activity (which will make Android system launch one activity which CAN
        // handle this "request"
        Intent intent = new Intent(Intent.ACTION_PICK);  //
        intent.setType("image/*");
        startActivityForResult(intent, 0); // 0 means photoroll, 1 means camera
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        // 1. check if result is OK. If not, then return
        if(resultCode != -1) return; // -1 indicates OK
        ic.handleImageReturn(requestCode, intent);
    }



    public void cameraBtnPressed(View view){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);  // we ask Android for something different
        startActivityForResult(intent, 1);
    }

}

