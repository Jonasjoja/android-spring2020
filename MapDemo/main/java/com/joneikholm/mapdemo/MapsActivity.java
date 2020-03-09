package com.joneikholm.mapdemo;

import androidx.fragment.app.FragmentActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.widget.EditText;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        builder = new AlertDialog.Builder(this);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(final LatLng latLng) {
                Log.i("all", "map was clicked");
                // build the builder
                final EditText editText = new EditText(MapsActivity.this);
                editText.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(editText)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        mMap.addMarker(new MarkerOptions().position(latLng).title(editText.getText().toString()));
                        dialog.dismiss();// maybe not
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
            }
        });
        addPizzaAndMusicMarker();
    }

    private void addPizzaAndMusicMarker() {
        // Add a marker in Sydney and move the camera
        LatLng pizza = new LatLng(55.6816809, 12.4842335);  // 55.6816809,12.4842335
        mMap.addMarker(new MarkerOptions().position(pizza).title("Mike Pizza"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pizza, 12));
        LatLng live = new LatLng(55.6679447, 12.5420891);  // 55.6679447,12.5420891,
        mMap.addMarker(new MarkerOptions().position(live).title("Live Music"));
    }
}
