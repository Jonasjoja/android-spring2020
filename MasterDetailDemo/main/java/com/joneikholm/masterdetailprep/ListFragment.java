package com.joneikholm.masterdetailprep;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ListFragment extends Fragment {
    private ListView mylist;
    private MainActivity mainActivity;

    public static ListFragment newInstance( MainActivity mainActivity) {
        ListFragment fragment = new ListFragment();
        fragment.mainActivity = mainActivity;
//        Bundle args = new Bundle();
//        args.putString(KEY, text);
//        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        System.out.println("onCreateView called");
        View view = inflater.inflate(R.layout.listlayout, container, false);
        mylist = view.findViewById(R.id.mylist);
        mylist.setOnItemClickListener((adapterView, view2, adapter_pos,row_id) -> {
            TextView tv = (TextView)view2;
            if(tv != null){
                mainActivity.handleItemClick(tv.getText().toString());
            }
        });

        return view;
    }

}
