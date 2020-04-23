package com.joneikholm.masterdetailprep;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class DetailFragment extends Fragment {
//    private static final String KEY = "key";
    private TextView textView;
    String value = "empty";

    // Because of requirement of an empty constructor, this will do:
    public static DetailFragment newInstance( String text) {
        DetailFragment fragment = new DetailFragment();
//        Bundle args = new Bundle();
//        args.putString(KEY, text);
//        fragment.setArguments(args);
        fragment.value = text;
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detaillayout, container, false);
        textView = view.findViewById(R.id.textViewDetail);
//        textView.setText(getArguments().getString(KEY));
        textView.setText(value);
        return view;
    }

}
