package com.joneikholm.recyclerviewdemo3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.joneikholm.recyclerviewdemo3.adapter.MyAdapter;
import com.joneikholm.recyclerviewdemo3.storage.NoteStorage;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MyAdapter myAdapter;
    private boolean isLastItemReached;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NoteStorage.init(this);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myAdapter = new MyAdapter(new NoteStorage());
        recyclerView.setAdapter(myAdapter); // assign the adapter to recyclerView
    }

    public void notifyAdapter() {
        myAdapter.notifyDataSetChanged();
    }

    public void setIsLastItemReached(boolean b) {
        isLastItemReached = b;
    }
}
