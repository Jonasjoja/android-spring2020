package com.joneikholm.recyclerviewdemo3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.AbsListView;

import com.joneikholm.recyclerviewdemo3.adapter.MyAdapter;
import com.joneikholm.recyclerviewdemo3.storage.NoteStorage;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MyAdapter myAdapter;
    private boolean isLastItemReached;
    private boolean isScrolling;
    private LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NoteStorage.init(this);
        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        myAdapter = new MyAdapter(new NoteStorage());
        recyclerView.setAdapter(myAdapter); // assign the adapter to recyclerView
        setupScrollListener();
    }

    private void setupScrollListener(){
        RecyclerView.OnScrollListener scrollListener = new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                    isScrolling = true; // use this later, to prevent multiple calls to Firebase
                }
            }
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();
                int visibleItemCount = layoutManager.getChildCount(); // get how many items are visible
                int totalItemCount = layoutManager.getItemCount();// get how many items are available in the adapter
                if(isScrolling && (firstVisibleItemPosition + visibleItemCount == totalItemCount) && !isLastItemReached){
                    isScrolling = false; // to prevent extra calls to firebase
                    NoteStorage.getNextNotes(); // load the next 5 items from firebase, starting after the last one.
                }
            }
        };
        recyclerView.addOnScrollListener(scrollListener);
    }

    public void notifyAdapter() {
        myAdapter.notifyDataSetChanged();
    }

    public void setIsLastItemReached(boolean b) {
        isLastItemReached = b;
    }
}
