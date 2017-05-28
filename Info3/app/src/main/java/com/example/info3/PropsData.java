package com.example.info3;

import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.HashMap;

public class PropsData{
    private static int PROXIMITY_RADIUS = 10000;
    public static void getData(){
        switch (HomeActivity.selection){
        case"restaurant":
            String url = getUrl(HomeActivity.lastLatitude,HomeActivity.lastLongitude,HomeActivity.selection);
           // Object[]DataTransfer=new Object[2];
            //DataTransfer[0]=mMap;
            //DataTransfer[1]=url;
            GetNearbyPlacesData getNearbyPlacesData=new GetNearbyPlacesData();
            getNearbyPlacesData.execute(url);
            for (int i = 0; i < getNearbyPlacesData.nearbyPlacesList.size(); i++) {
                Log.d("onPostExecute","Entered into showing locations");
                MarkerOptions markerOptions = new MarkerOptions();
                HashMap<String, String> googlePlace = getNearbyPlacesData.nearbyPlacesList.get(i);
                double lat = Double.parseDouble(googlePlace.get("lat"));
                double lng = Double.parseDouble(googlePlace.get("lng"));
                String placeName = googlePlace.get("place_name");
                String vicinity = googlePlace.get("vicinity");
                LatLng latLng = new LatLng(lat, lng);
                Log.d("Gauname pavadinima", placeName);
                Log.d("Vicinity", vicinity);
            }
        }
    }
    private static String getUrl(double latitude, double longitude, String nearbyPlace) {
        StringBuilder googlePlacesUrl = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
        googlePlacesUrl.append("location=" + latitude + "," + longitude);
        googlePlacesUrl.append("&radius=" + PROXIMITY_RADIUS);
        googlePlacesUrl.append("&type=" + nearbyPlace);
        googlePlacesUrl.append("&sensor=true");
        googlePlacesUrl.append("&key=" + "AIzaSyATuUiZUkEc_UgHuqsBJa1oqaODI-3mLs0");
        //  googlePlacesUrl.append("&key=" + "AIzaSyCSccq3CCLfyNcGvhppIamI11NPLO5uL8g");
        return (googlePlacesUrl.toString());
    }
}
