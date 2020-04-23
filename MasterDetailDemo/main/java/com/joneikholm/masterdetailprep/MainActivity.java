package com.joneikholm.masterdetailprep;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    private boolean isTablet = false;
    private DetailFragment detailFragment;
    private ListFragment listFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FrameLayout frameLayout = findViewById(R.id.phoneframe_id); // just to check if it EXISTS.
        listFragment = ListFragment.newInstance(this);
        if(frameLayout != null){
            System.out.println("PHONE");
            isTablet = false;
            presentFragment(R.id.phoneframe_id, listFragment);
        }else {
            System.out.println("TABLET");
            isTablet = true;
            detailFragment = DetailFragment.newInstance("Welcome");
            presentFragment(R.id.frameLayoutTabletList, listFragment);
            presentFragment(R.id.frameLayoutTabletDetail, detailFragment);
        }
    }

    public void handleItemClick(String value){
        if(isTablet){
            detailFragment = DetailFragment.newInstance(value);
            presentFragment(R.id.frameLayoutTabletDetail, detailFragment);
        }else {
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra(DetailActivity.KEY, value);
            startActivity(intent);
        }
    }

    private void presentFragment(int resourceId, Fragment fragment){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(resourceId, fragment)
                .commit();
    }
}
























