package com.joneikholm.recyclerviewdemo3.view;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.joneikholm.recyclerviewdemo3.R;

public class MyViewHolder extends RecyclerView.ViewHolder {
    private TextView textView;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        LinearLayout ll = (LinearLayout)itemView;
        textView = ll.findViewById(R.id.textView1);
    }

    public void setData(String d) {
            textView.setText(d);
    }


}
