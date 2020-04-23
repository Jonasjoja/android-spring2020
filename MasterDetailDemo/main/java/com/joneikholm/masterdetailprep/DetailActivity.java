package com.joneikholm.masterdetailprep;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    public static final String KEY = "KEY";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detaillayout);
        TextView textView = findViewById(R.id.textViewDetail);
        Bundle bundle = getIntent().getExtras();
        textView.setText(bundle.getString(KEY));
    }
}
