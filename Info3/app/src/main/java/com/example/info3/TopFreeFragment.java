package com.example.info3;


import android.support.v4.app.Fragment;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


/**
 * A simple {@link Fragment} subclass.
 */
public class TopFreeFragment /*extends Fragment*/ extends SupportMapFragment implements
        OnMapReadyCallback {

    // private final LatLng HAMBURG = new LatLng(53.558, 9.927);
    //private final LatLng KIEL = new LatLng(53.551, 9.993);

    // private static final String ARG_SECTION_NUMBER = "section_number";

    private GoogleMap mMap;
    private Marker marker;


    public TopFreeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onResume() {
        super.onResume();

        Log.d("MyMap", "onResume");
        setUpMapIfNeeded();
    }

    private void setUpMapIfNeeded() {

        if (mMap == null) {

            Log.d("MyMap", "setUpMapIfNeeded");

            getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.d("MyMap", "onMapReady");
        mMap = googleMap;
        setUpMap();
    }

    private void setUpMap() {
        //mMap.setMyLocationEnabled(true);
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.getUiSettings().setMapToolbarEnabled(false);
        double lat = HomeActivity.props.get(DrawSurfaceView.indexOfclick).latitude;
        double lng = HomeActivity.props.get(DrawSurfaceView.indexOfclick).longitude;


        LatLng position = new LatLng(lat,lng);
        mMap.addMarker(new MarkerOptions().position(position)
                .title(HomeActivity.props.get(DrawSurfaceView.indexOfclick).description));

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(position, 15));

        mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
    }




   // @Override
  /*  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
       // return inflater.inflate(R.layout./*activity_maps*///activity_maps_tab/*fragment_top_free*/, container, false);
    //}

}
