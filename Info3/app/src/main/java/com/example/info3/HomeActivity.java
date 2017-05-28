package com.example.info3;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.Thread.sleep;

public class HomeActivity extends AppCompatActivity implements
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    public static ArrayList<Point> props = new ArrayList<Point>();
    Button button6; // restaurants
    //Button button26; // hospitals
    Button cafe;
    Button bar;
    //Button park;
    //Button museum;
    //Button movie_theater;
    //Button art_gallery;
    //Button library;
    Button bank;
    Button atm;
    /*Button spa;
    Button pharmacy;
    Button gym;
    Button dentist;*/
    Button shopping_mall;
    Button clothing_store;
    //Button book_store;
    //Button jewelry_store;
    Button shoe_store;




    ImageButton buttonAuth;





    public static String selection;

    protected GoogleApiClient mGoogleApiClient;
    String TAG = "AAA";

    protected Location mLastLocation;
    protected String mLatitudeLabel;
    protected String mLongitudeLabel;
    //protected TextView mLatitudeText;
    //protected TextView mLongitudeText;

    public static double lastLatitude;
    public static String stringLastLatitude;
    public static double lastLongitude;
    public static String stringLastLongitude;

    ArrayList<HashMap<String, String>> placesList;
    private static int PROXIMITY_RADIUS = 10000/*1000*//*800*/;
    public static String url;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mLatitudeLabel = getResources().getString(R.string.latitude_label);
        mLongitudeLabel = getResources().getString(R.string.longitude_label);
        //mLatitudeText = (TextView) findViewById((R.id.latitude_text));
        //mLongitudeText = (TextView) findViewById((R.id.longitude_text));
        buildGoogleApiClient();

        placesList = new ArrayList<>();


        buttonAuth = (ImageButton) findViewById(R.id.imageButton5);

        button6 = (Button) findViewById(R.id.button6);
        //button26 = (Button) findViewById(R.id.button26);
        cafe = (Button) findViewById(R.id.button4);
        bar = (Button) findViewById(R.id.button5);
        //park = (Button) findViewById(R.id.button16);
        //museum = (Button) findViewById(R.id.button15);
        //movie_theater = (Button) findViewById(R.id.button14);
        //art_gallery = (Button) findViewById(R.id.button13);
        //library = (Button) findViewById(R.id.button12);
        bank = (Button) findViewById(R.id.button18);
        atm = (Button) findViewById(R.id.button17);
        /*spa = (Button) findViewById(R.id.button25);
        pharmacy = (Button) findViewById(R.id.button24);
        gym = (Button) findViewById(R.id.button23);
        dentist = (Button) findViewById(R.id.button22);*/
        shopping_mall = (Button) findViewById(R.id.button28);
        clothing_store = (Button) findViewById(R.id.button29);
        //book_store = (Button) findViewById(R.id.button31);
        //jewelry_store = (Button) findViewById(R.id.button30);
        shoe_store = (Button) findViewById(R.id.button27);


        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.imageButton5:
                        Toast.makeText(HomeActivity.this, "PASPAUSTA", Toast.LENGTH_LONG).show();

                        Intent i0 = new Intent(HomeActivity.this, Compass.class);
                        startActivity(i0);
                        break;

                    case R.id.button6:      // restoranai
                        /*selection = "restaurant";
                        //PropsData.getData();
                        Intent i = new Intent(HomeActivity.this, CameraViewActivity.class);
                        startActivity(i);
                        break;*/
                        Intent i = new Intent(HomeActivity.this, Compass.class);
                        selection = "restaurant";
                        url = getUrl(HomeActivity.lastLatitude, HomeActivity.lastLongitude, HomeActivity.selection);
                        props.clear();
                        new GetContacts().execute();
                        Log.d("SIZE IN HOME ", String.valueOf(props.size()));
                        //PropsData.getData();
                        try {
                            //Thread.sleep(10000);
                            sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Log.d("SIZE IN HOME ", String.valueOf(props.size()));
                        if (props.size() != 0) {
                            startActivity(i);
                        } else {

                            Toast.makeText(HomeActivity.this, "Nėra duomenų", Toast.LENGTH_LONG).show();
                        }
                        break;
                    /*case R.id.button26:      // ligonine
                        //HomeFragment homeFragment = new HomeFragment();
                        //homeFragment.setText("A");
                        Intent i1 = new Intent(HomeActivity.this, Compass.class);
                        selection = "hospital";
                        url = getUrl(HomeActivity.lastLatitude, HomeActivity.lastLongitude, HomeActivity.selection);
                        props.clear();
                        new GetContacts().execute();
                        Log.d("SIZE IN HOME ", String.valueOf(props.size()));
                        //PropsData.getData();
                        try {
                            //Thread.sleep(10000);
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        Log.d("SIZE IN HOME ", String.valueOf(props.size()));
                        if (props.size() != 0) {
                            startActivity(i1);
                        } else {
                            Toast.makeText(HomeActivity.this, "Nėra duomenų", Toast.LENGTH_LONG).show();
                        }
                        break;*/
                    case R.id.button5:      // baras
                        /*HomeFragment homeFragment = new HomeFragment();
                        homeFragment.setText("A");*/
                        Intent i2 = new Intent(HomeActivity.this, Compass.class);
                        selection = "bar";
                        url = getUrl(HomeActivity.lastLatitude, HomeActivity.lastLongitude, HomeActivity.selection);
                        props.clear();
                        new GetContacts().execute();
                        Log.d("SIZE IN HOME ", String.valueOf(props.size()));
                        //PropsData.getData();
                        try {
                            //Thread.sleep(10000);
                            sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Log.d("SIZE IN HOME ", String.valueOf(props.size()));
                        if (props.size() != 0) {
                            startActivity(i2);
                        } else {
                            Toast.makeText(HomeActivity.this, "Nėra duomenų", Toast.LENGTH_LONG).show();
                        }
                        break;
                    case R.id.button4:      // kavine
                        /*HomeFragment homeFragment = new HomeFragment();
                        homeFragment.setText("A");*/
                        Intent i3 = new Intent(HomeActivity.this, Compass.class);
                        selection = "cafe";
                        url = getUrl(HomeActivity.lastLatitude, HomeActivity.lastLongitude, HomeActivity.selection);
                        props.clear();
                        new GetContacts().execute();
                        Log.d("SIZE IN HOME ", String.valueOf(props.size()));
                        //PropsData.getData();
                        try {
                            //Thread.sleep(10000);
                            sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        Log.d("SIZE IN HOME ", String.valueOf(props.size()));
                        if (props.size() != 0) {
                            startActivity(i3);
                        } else {
                            Toast.makeText(HomeActivity.this, "Nėra duomenų", Toast.LENGTH_LONG).show();
                        }
                        break;
                    /*case R.id.button16:      // parkas
                        //HomeFragment homeFragment = new HomeFragment();
                        //homeFragment.setText("A");
                       Intent i4 = new Intent(HomeActivity.this, Compass.class);
                        selection = "park";
                        url = getUrl(HomeActivity.lastLatitude, HomeActivity.lastLongitude, HomeActivity.selection);
                        props.clear();
                        new GetContacts().execute();
                        Log.d("SIZE IN HOME ", String.valueOf(props.size()));
                        //PropsData.getData();
                        try {
                            //Thread.sleep(10000);
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        Log.d("SIZE IN HOME ", String.valueOf(props.size()));
                        if (props.size() != 0) {
                            startActivity(i4);
                        } else {
                            Toast.makeText(HomeActivity.this, "Nėra duomenų", Toast.LENGTH_LONG).show();
                        }
                        break;
                    */
                    /*case R.id.button15:      // muziejai
                        //HomeFragment homeFragment = new HomeFragment();
                        //homeFragment.setText("A");
                        Intent i5 = new Intent(HomeActivity.this, Compass.class);
                        selection = "museum";
                        url = getUrl(HomeActivity.lastLatitude, HomeActivity.lastLongitude, HomeActivity.selection);
                        props.clear();
                        new GetContacts().execute();
                        Log.d("SIZE IN HOME ", String.valueOf(props.size()));
                        //PropsData.getData();
                        try {
                            //Thread.sleep(10000);
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        Log.d("SIZE IN HOME ", String.valueOf(props.size()));
                        if (props.size() != 0) {
                            startActivity(i5);
                        } else {
                            Toast.makeText(HomeActivity.this, "Nėra duomenų", Toast.LENGTH_LONG).show();
                        }
                        break;
                    case R.id.button14:      // kino teatras
                        //HomeFragment homeFragment = new HomeFragment();
                        //homeFragment.setText("A");
                        Intent i6 = new Intent(HomeActivity.this, Compass.class);
                        selection = "movie_theater";
                        url = getUrl(HomeActivity.lastLatitude, HomeActivity.lastLongitude, HomeActivity.selection);
                        props.clear();
                        new GetContacts().execute();
                        Log.d("SIZE IN HOME ", String.valueOf(props.size()));
                        //PropsData.getData();
                        try {
                            //Thread.sleep(10000);
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        Log.d("SIZE IN HOME ", String.valueOf(props.size()));
                        if (props.size() != 0) {
                            startActivity(i6);
                        } else {
                            Toast.makeText(HomeActivity.this, "Nėra duomenų", Toast.LENGTH_LONG).show();
                        }
                        break;
                    case R.id.button13:      // meno galerija
                        //HomeFragment homeFragment = new HomeFragment();
                        //homeFragment.setText("A");
                        Intent i7 = new Intent(HomeActivity.this, Compass.class);
                        selection = "art_gallery";
                        url = getUrl(HomeActivity.lastLatitude, HomeActivity.lastLongitude, HomeActivity.selection);
                        props.clear();
                        new GetContacts().execute();
                        Log.d("SIZE IN HOME ", String.valueOf(props.size()));
                        //PropsData.getData();
                        try {
                            //Thread.sleep(10000);
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        Log.d("SIZE IN HOME ", String.valueOf(props.size()));
                        if (props.size() != 0) {
                            startActivity(i7);
                        } else {
                            Toast.makeText(HomeActivity.this, "Nėra duomenų", Toast.LENGTH_LONG).show();
                        }
                        break;*/
                    /*case R.id.button12:      // biblioteka
                        //HomeFragment homeFragment = new HomeFragment();
                        //homeFragment.setText("A");
                        Intent i8 = new Intent(HomeActivity.this, Compass.class);
                        selection = "library";
                        url = getUrl(HomeActivity.lastLatitude, HomeActivity.lastLongitude, HomeActivity.selection);
                        props.clear();
                        new GetContacts().execute();
                        Log.d("SIZE IN HOME ", String.valueOf(props.size()));
                        //PropsData.getData();
                        try {
                            //Thread.sleep(10000);
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        Log.d("SIZE IN HOME ", String.valueOf(props.size()));
                        if (props.size() != 0) {
                            startActivity(i8);
                        } else {
                            Toast.makeText(HomeActivity.this, "Nėra duomenų", Toast.LENGTH_LONG).show();
                        }
                        break;*/
                    case R.id.button18:      // bankai
                        /*HomeFragment homeFragment = new HomeFragment();
                        homeFragment.setText("A");*/
                        Intent i9 = new Intent(HomeActivity.this, Compass.class);
                        selection = "bank";
                        url = getUrl(HomeActivity.lastLatitude, HomeActivity.lastLongitude, HomeActivity.selection);
                        props.clear();
                        new GetContacts().execute();
                        Log.d("SIZE IN HOME ", String.valueOf(props.size()));
                        //PropsData.getData();
                        try {
                            //Thread.sleep(10000);
                            sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        Log.d("SIZE IN HOME ", String.valueOf(props.size()));
                        if (props.size() != 0) {
                            startActivity(i9);
                        } else {
                            Toast.makeText(HomeActivity.this, "Nėra duomenų", Toast.LENGTH_LONG).show();
                            //Intent ii = new Intent(HomeActivity.this, ShakeActivity.class);
                            //startActivity(ii);
                        }
                        break;
                    case R.id.button17:      // bankomatai
                        /*HomeFragment homeFragment = new HomeFragment();
                        homeFragment.setText("A");*/
                        /*Intent i10 = new Intent(HomeActivity.this, Compass.class);
                        selection = "atm";
                        url = getUrl(HomeActivity.lastLatitude, HomeActivity.lastLongitude, HomeActivity.selection);
                        props.clear();
                        new GetContacts().execute();
                        Log.d("SIZE IN HOME ", String.valueOf(props.size()));
                        //PropsData.getData();
                        try {
                            //Thread.sleep(10000);
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        Log.d("SIZE IN HOME ", String.valueOf(props.size()));
                        if (props.size() != 0) {
                            startActivity(i10);
                        } else {*/
                            Toast.makeText(HomeActivity.this, "Nėra duomenų", Toast.LENGTH_LONG).show();
                        /*}*/
                        break;
                    /*case R.id.button25:      // spa
                        //HomeFragment homeFragment = new HomeFragment();
                        //homeFragment.setText("A");
                        Intent i11 = new Intent(HomeActivity.this, Compass.class);
                        selection = "spa";
                        url = getUrl(HomeActivity.lastLatitude, HomeActivity.lastLongitude, HomeActivity.selection);
                        props.clear();
                        new GetContacts().execute();
                        Log.d("SIZE IN HOME ", String.valueOf(props.size()));
                        //PropsData.getData();
                        try {
                            //Thread.sleep(10000);
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        Log.d("SIZE IN HOME ", String.valueOf(props.size()));
                        if (props.size() != 0) {
                            startActivity(i11);
                        } else {
                            Toast.makeText(HomeActivity.this, "Nėra duomenų", Toast.LENGTH_LONG).show();
                        }
                        break;
                    case R.id.button24:      // vaistines
                        //HomeFragment homeFragment = new HomeFragment();
                        //homeFragment.setText("A");
                        Intent i12 = new Intent(HomeActivity.this, Compass.class);
                        selection = "pharmacy";
                        url = getUrl(HomeActivity.lastLatitude, HomeActivity.lastLongitude, HomeActivity.selection);
                        props.clear();
                        new GetContacts().execute();
                        Log.d("SIZE IN HOME ", String.valueOf(props.size()));
                        //PropsData.getData();
                        try {
                            //Thread.sleep(10000);
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        Log.d("SIZE IN HOME ", String.valueOf(props.size()));
                        if (props.size() != 0) {
                            startActivity(i12);
                        } else {
                            Toast.makeText(HomeActivity.this, "Nėra duomenų", Toast.LENGTH_LONG).show();
                        }
                        break;*/
                    //case R.id.button23:      // sporto klubas
                        /*HomeFragment homeFragment = new HomeFragment();
                        homeFragment.setText("A");*/
                        /*Intent i13 = new Intent(HomeActivity.this, Compass.class);
                        selection = "gym";
                        url = getUrl(HomeActivity.lastLatitude, HomeActivity.lastLongitude, HomeActivity.selection);
                        props.clear();
                        new GetContacts().execute();
                        Log.d("SIZE IN HOME ", String.valueOf(props.size()));
                        //PropsData.getData();
                        try {
                            //Thread.sleep(10000);
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        Log.d("SIZE IN HOME ", String.valueOf(props.size()));
                        if (props.size() != 0) {
                            startActivity(i13);
                        } else {*/
                         //   Toast.makeText(HomeActivity.this, "Nėra duomenų", Toast.LENGTH_LONG).show();
                        /*}*/

                       // break;

                    /*case R.id.button22:      // dantistai
                        //HomeFragment homeFragment = new HomeFragment();
                        //homeFragment.setText("A");
                        Intent i14 = new Intent(HomeActivity.this, Compass.class);
                        selection = "dentist";
                        url = getUrl(HomeActivity.lastLatitude, HomeActivity.lastLongitude, HomeActivity.selection);
                        props.clear();
                        new GetContacts().execute();
                        Log.d("SIZE IN HOME ", String.valueOf(props.size()));
                        //PropsData.getData();
                        try {
                            //Thread.sleep(10000);
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        Log.d("SIZE IN HOME ", String.valueOf(props.size()));
                        if (props.size() != 0) {
                            startActivity(i14);
                        } else {
                            Toast.makeText(HomeActivity.this, "Nėra duomenų", Toast.LENGTH_LONG).show();
                        }
                        break;*/
                    case R.id.button28:      // prekybos centrai
                        /*HomeFragment homeFragment = new HomeFragment();
                        homeFragment.setText("A");*/
                        Intent i15 = new Intent(HomeActivity.this, Compass.class);
                        selection = "shopping_mall";
                        url = getUrl(HomeActivity.lastLatitude, HomeActivity.lastLongitude, HomeActivity.selection);
                        props.clear();
                        new GetContacts().execute();
                        Log.d("SIZE IN HOME ", String.valueOf(props.size()));
                        //PropsData.getData();
                        try {
                            //Thread.sleep(10000);
                            sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        Log.d("SIZE IN HOME ", String.valueOf(props.size()));
                        if (props.size() != 0) {
                            startActivity(i15);
                        } else {
                            Toast.makeText(HomeActivity.this, "Nėra duomenų", Toast.LENGTH_LONG).show();
                        }
                        break;
                    case R.id.button29:      // drabuziu parduotuves
                        /*HomeFragment homeFragment = new HomeFragment();
                        homeFragment.setText("A");*/
                        Intent i16 = new Intent(HomeActivity.this, Compass.class);
                        selection = "clothing_store";
                        url = getUrl(HomeActivity.lastLatitude, HomeActivity.lastLongitude, HomeActivity.selection);
                        props.clear();
                        new GetContacts().execute();
                        Log.d("SIZE IN HOME ", String.valueOf(props.size()));
                        //PropsData.getData();
                        try {
                            //Thread.sleep(10000);
                            sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        Log.d("SIZE IN HOME ", String.valueOf(props.size()));
                        if (props.size() != 0) {
                            startActivity(i16);
                        } else {
                            Toast.makeText(HomeActivity.this, "Nėra duomenų", Toast.LENGTH_LONG).show();
                        }
                        break;
                    /*case R.id.button31:      // knygu parduotuves
                        //HomeFragment homeFragment = new HomeFragment();
                        //homeFragment.setText("A");
                        Intent i17 = new Intent(HomeActivity.this, Compass.class);
                        selection = "book_store";
                        url = getUrl(HomeActivity.lastLatitude, HomeActivity.lastLongitude, HomeActivity.selection);
                        props.clear();
                        new GetContacts().execute();
                        Log.d("SIZE IN HOME ", String.valueOf(props.size()));
                        //PropsData.getData();
                        try {
                            //Thread.sleep(10000);
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        Log.d("SIZE IN HOME ", String.valueOf(props.size()));
                        if (props.size() != 0) {
                            startActivity(i17);
                        } else {
                            Toast.makeText(HomeActivity.this, "Nėra duomenų", Toast.LENGTH_LONG).show();
                        }
                        break;
                    case R.id.button30:      // pauosalu parduotuves
                        //HomeFragment homeFragment = new HomeFragment();
                        //homeFragment.setText("A");
                        Intent i18 = new Intent(HomeActivity.this, Compass.class);
                        selection = "jewelry_store";
                        url = getUrl(HomeActivity.lastLatitude, HomeActivity.lastLongitude, HomeActivity.selection);
                        props.clear();
                        new GetContacts().execute();
                        Log.d("SIZE IN HOME ", String.valueOf(props.size()));
                        //PropsData.getData();
                        try {
                            //Thread.sleep(10000);
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        Log.d("SIZE IN HOME ", String.valueOf(props.size()));
                        if (props.size() != 0) {
                            startActivity(i18);
                        } else {
                            Toast.makeText(HomeActivity.this, "Nėra duomenų", Toast.LENGTH_LONG).show();
                        }
                        break;*/
                    case R.id.button27:
                        /*HomeFragment homeFragment = new HomeFragment();
                        homeFragment.setText("A");*/
                        Intent i19 = new Intent(HomeActivity.this, Compass.class);
                        selection = "shoe_store";
                        url = getUrl(HomeActivity.lastLatitude, HomeActivity.lastLongitude, HomeActivity.selection);
                        props.clear();
                        new GetContacts().execute();
                        Log.d("SIZE IN HOME ", String.valueOf(props.size()));
                        //PropsData.getData();
                        try {
                            //Thread.sleep(10000);
                            sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        Log.d("SIZE IN HOME ", String.valueOf(props.size()));
                        if (props.size() != 0) {
                            startActivity(i19);
                        } else {
                            Toast.makeText(HomeActivity.this, "Nėra duomenų", Toast.LENGTH_LONG).show();
                        }
                        break;
                }
            }
        };

        buttonAuth.setOnClickListener(onClickListener);
        button6.setOnClickListener(onClickListener);
        //button26.setOnClickListener(onClickListener);
        bar.setOnClickListener(onClickListener);
        cafe.setOnClickListener(onClickListener);
        //park.setOnClickListener(onClickListener);
        //museum.setOnClickListener(onClickListener);
        //movie_theater.setOnClickListener(onClickListener);
        //art_gallery.setOnClickListener(onClickListener);
        //library.setOnClickListener(onClickListener);
        bank.setOnClickListener(onClickListener);
        atm.setOnClickListener(onClickListener);
        /*spa.setOnClickListener(onClickListener);
        pharmacy.setOnClickListener(onClickListener);
        gym.setOnClickListener(onClickListener);
        dentist.setOnClickListener(onClickListener);*/
        shopping_mall.setOnClickListener(onClickListener);
        clothing_store.setOnClickListener(onClickListener);
        //book_store.setOnClickListener(onClickListener);
        //jewelry_store.setOnClickListener(onClickListener);
        shoe_store.setOnClickListener(onClickListener);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    @Override
    protected void onStart() {
        super.onStart();// ATTENTION: This was auto-generated to implement the App Indexing API.
// See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        mGoogleApiClient.connect();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    protected void onStop() {
        super.onStop();// ATTENTION: This was auto-generated to implement the App Indexing API.
// See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.disconnect();
    }

    @Override
    public void onConnected(Bundle connectionHint) {
        // Provides a simple way of getting a device's location and is well suited for
        // applications that do not require a fine-grained location and that do not need location
        // updates. Gets the best and most recent location currently available, which may be null
        // in rare cases when a location is not available.
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (mLastLocation != null) {
            /*mLatitudeText.setText(String.format("%s: %f", mLatitudeLabel,
                    mLastLocation.getLatitude()));
            mLongitudeText.setText(String.format("%s: %f", mLongitudeLabel,
                    mLastLocation.getLongitude()));*/


            lastLatitude = mLastLocation.getLatitude();
            stringLastLatitude = String.valueOf(lastLatitude);

            lastLongitude = mLastLocation.getLongitude();
            stringLastLongitude = String.valueOf(lastLongitude);

            //mLatitudeText.setText((int) mLastLocation.getLatitude());
            //mLongitudeText.setText((int) mLastLocation.getLongitude());
        } else {
            Toast.makeText(this, R.string.no_location_detected, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult result) {
        // Refer to the javadoc for ConnectionResult to see what error codes might be returned in
        // onConnectionFailed.
        Log.i(TAG, "Connection failed: ConnectionResult.getErrorCode() = " + result.getErrorCode());
    }


    @Override
    public void onConnectionSuspended(int cause) {
        // The connection to Google Play services was lost for some reason. We call connect() to
        // attempt to re-establish the connection.
        Log.i(TAG, "Connection suspended");
        mGoogleApiClient.connect();
    }

    private static String getUrl(double latitude, double longitude, String nearbyPlace) {
        StringBuilder googlePlacesUrl = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
        googlePlacesUrl.append("location=" + latitude + "," + longitude);
        googlePlacesUrl.append("&radius=" + PROXIMITY_RADIUS);
        googlePlacesUrl.append("&type=" + nearbyPlace);
        googlePlacesUrl.append("&sensor=true");
        googlePlacesUrl.append("&key=" + "AIzaSyATuUiZUkEc_UgHuqsBJa1oqaODI-3mLs0");
       // googlePlacesUrl.append("&key=" + "AIzaSyCSccq3CCLfyNcGvhppIamI11NPLO5uL8g");
        return (googlePlacesUrl.toString());
    }
/*
    private static String photoUrl(String maxheight, String maxwidth, String photoreference) {
        StringBuilder googlePlacesUrl = new StringBuilder("https://maps.googleapis.com/maps/api/place/photo?");
        googlePlacesUrl.append("maxwidth=" + maxwidth);
        googlePlacesUrl.append("&photoreference=" + photoreference);
        googlePlacesUrl.append("&key=" + "AIzaSyATuUiZUkEc_UgHuqsBJa1oqaODI-3mLs0");
        //  googlePlacesUrl.append("&key=" + "AIzaSyCSccq3CCLfyNcGvhppIamI11NPLO5uL8g");
        return (googlePlacesUrl.toString());
    }
*/
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Home Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }


    /***
     * Async task class to get json by making HTTP call
     */
    private class GetContacts extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            /*
            pDialog = new ProgressDialog(HomeActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();
            */
        }

        @SuppressLint("LongLogTag")
        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url);
            Log.e(TAG, "Response from url: " + jsonStr);
            if (jsonStr != null) try {
                JSONObject jsonObj = new JSONObject(jsonStr);

                // Getting JSON Array node
                JSONArray contacts = jsonObj.getJSONArray("results"); ///represents Json array

                // looping through All Contacts
                for (int i = 0; i < contacts.length(); i++) {
                    JSONObject c = contacts.getJSONObject(i);

                    // String id = c.getString("id");
                    String name = c.getString("name");
                    double lat = c.getJSONObject("geometry").getJSONObject("location")
                            .getDouble("lat");

                    double lng = c.getJSONObject("geometry").getJSONObject("location")
                            .getDouble("lng");
                    // String email = c.getString("email");
                    String address = c.getString("vicinity");

                    String  icon = c.getString("icon");
                    String type = c.getString("types");
                    //////////////////Kuriam url gauti fotkei
                    ////////Nuoroda https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference=CnRtAAAATLZNl354RwP_9UKbQ_5Psy40texXePv4oAlgP4qNEkdIrkyse7rPXYGd9D_Uj1rVsQdWT4oRz4QrYAJNpFX7rzqqMlZw2h2E2y5IKMUZ7ouD_SlcHxYq1yL4KbKUv3qtWgTK0A6QbGh87GB3sscrHRIQiG2RrmU_jF4tENr9wGS_YxoUSSDrYjWmrNfeEHSGSc3FyhNLlBU&key=YOUR_API_KEY
                    ///Reikalingi parametrai: key,photoreference, maxheight,maxwidth.
                    //////Formata jauciu galima bet koki pasidaryt.

                    // Getting JSON Array node
                    ///patikrinti kazkaip reikai kad nemestu erroro!!!!!ro = c.getJSONArray("ro");


                    //JSONArray photos = c.getJSONArray("photos");

/*
                    if(c.getString("photos").equals(JSONObject.NULL)){
                        JSONArray photos = c.getJSONArray("photos"); ///represents Json array
                        String photoreference = "null";
                        String  maxheight = "0";
                        String  maxwidth = "0";
                        JSONObject p = photos.getJSONObject(0);
                        photoreference = p.getString("photo_reference");
                        maxheight = p.getString("height");
                        maxwidth = p.getString("width");
                        String photoUrl =  photoUrl(maxheight, maxwidth, photoreference);
                        Log.d("URL NUOTRAUKOS************", photoUrl);
                        //  Log.d("URL NUOTRAUKOS************",https://maps.googleapis.com/maps/api/place/photo?maxwidth&photoreference=CnRtAAAATLZNl354RwP_9UKbQ_5Psy40texXePv4oAlgP4qNEkdIrkyse7rPXYGd9D_Uj1rVsQdWT4oRz4QrYAJNpFX7rzqqMlZw2h2E2y5IKMUZ7ouD_SlcHxYq1yL4KbKUv3qtWgTK0A6QbGh87GB3sscrHRIQiG2RrmU_jF4tENr9wGS_YxoUSSDrYjWmrNfeEHSGSc3FyhNLlBU&key=YOUR_API_KEY)
                     }else{
                        Log.d("NETURI NUOTRAUKOS************", name);
                        /// kazka  darom...
                     }

*/




                    //??????????????????????????????????????????????????????????????????????????
                    String opening_hours = "-";
                    //if (!jsonObj.isNull("opening_hours")){
                    //if(c.getJSONObject("opening_hours").getString("open_now") != null){
                    //  opening_hours = c.getJSONObject("opening_hours").getString("open_now");
                    //}
                    //opening_hours = c.getJSONObject("opening_hours").getString("open_now");


                    //String openingHours = c.getString("opening_hours");


                    //String gender = c.getString("gender");

                    // Phone node is JSON Object
                    //   JSONObject phone = c.getJSONObject("phone");
                    //  String mobile = phone.getString("mobile");
                    // String home = phone.getString("home");
                    // String office = phone.getString("office");

                    // tmp hash map for single contact
                    HashMap<String, String> contact = new HashMap<>();

                    // adding each child node to HashMap key => value
                    //  contact.put("id", id);
                    //??????????????????????????????????????????????????????????????????????????
                    contact.put("name", name);
                    contact.put("adresas", address);
                    contact.put("lat", String.valueOf(lat));
                    contact.put("lng", String.valueOf(lng));
                    Log.d("VIETOVE************", name);
                    Log.d("KORDINATES************************* lat", String.valueOf(lat));
                    Log.d("KORDINATES************************* lng", String.valueOf(lng));
                    Log.d("ADRESAS************************* ", address);
                    Log.d("URL************************* ", url);
                    //Log.d("opening_hours************************* ", openingHours);


                    // contact.put("email", email);
                    // contact.put("mobile", mobile);
                    // adding contact to contact list
                    placesList.add(contact);
                    ///DETI I PROPS!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

                    props.add(new Point(lat, lng, name, address, opening_hours, type, icon));
                    Log.d("SIZE ", String.valueOf(props.size()));
                }
            } catch (final JSONException e) {
                Log.e(TAG, "Json parsing error: " + e.getMessage());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Json parsing error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
            else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        /*
                        Toast.makeText(getApplicationContext(),"Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG.show();
                                */
                    }
                });
            }
            return null;
        }


        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            /*
            if (pDialog.isShowing())
                pDialog.dismiss();
            /**
                          * Updating parsed JSON data into ListView
                          * */
            /*
            ListAdapter adapter = new SimpleAdapter(
                    HomeActivity.this, placesList,
                    R.layout.list_item, new String[]{"name", "email","mobile"}, new int[]{R.id.name,
            R.id.email, R.id.mobile});
            lv.setAdapter(adapter);
            */
        }
    }

}
