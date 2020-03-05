package com.joneikholm.uploaddownloadtocloudstorage.repo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.InputStream;

public class Repo {
    static FirebaseStorage storage = FirebaseStorage.getInstance();

    public static void uploadFile(Context context){
        try {
            InputStream is = context.getAssets().open("alice.txt");
            StorageReference ref = storage.getReference("alice.txt");

            ref.putStream(is).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                    System.out.println("upload completed");
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    System.out.println("error " + e.getMessage());
                }
            });

            if(is != null){
                System.out.println("found it");
            }else {
                System.out.println("no file");
            }
        }catch (Exception e){

        }
    }

    public static void downloadImage(String name, final ImageView iv){
        // get a reference from the file storage, given a
        // file name
        StorageReference ref = storage.getReference(name);
        int max = 1024 * 1024 * 2; // 2 megabytes maximum
        ref.getBytes(max).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Bitmap bm = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                iv.setImageBitmap(bm); // set imagedata to imageView
            }
        });

    }
}
