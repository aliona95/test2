package com.example.info3;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;


public class Compass extends Activity implements SensorEventListener, LocationListener{

    private static final String TAG = "Compass";
    private static boolean DEBUG = true;
    private SensorManager mSensorManager;
    private Sensor mSensor;
    private DrawSurfaceView mDrawView;
    LocationManager locMgr;

    private float[] mRotationMatrix = new float[16];
    private float[] mValues = new float[3];
    private View t;

    ImageButton button;

    @Override
    protected void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
        setContentView(R.layout.activity_compass);


        button = (ImageButton)findViewById(R.id.imageButton);

        mDrawView = (DrawSurfaceView) findViewById(R.id.drawSurfaceView);
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.imageButton:
                        Intent i = new Intent(Compass.this, MapsActivity.class);
                        startActivity(i);
                        break;
                }
            }
        };
        button.setOnClickListener(onClickListener);


        locMgr = (LocationManager) this.getSystemService(LOCATION_SERVICE); // <2>
        LocationProvider high = locMgr.getProvider(locMgr.getBestProvider(
                LocationUtils.createFineCriteria(), true));

        // using high accuracy provider... to listen for updates
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locMgr.requestLocationUpdates(high.getName(), 0, 0f, this);
    }

    @Override
    protected void onResume() {
        if (DEBUG)
            Log.d(TAG, "onResume");
        super.onResume();

        mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_GAME);
    }

    /***
     * START SensorEventListener
     */
    @Override
    public void onSensorChanged(SensorEvent event) {
        SensorManager.getRotationMatrixFromVector(mRotationMatrix, event.values);
        SensorManager.getOrientation(mRotationMatrix, mValues);
        if (DEBUG) {
            Log.d(TAG, "sensorChanged (" + Math.toDegrees(mValues[0]) + ", " + Math.toDegrees(mValues[1]) + ", " + Math.toDegrees(mValues[2]) + ")");
        }
        if (mDrawView != null) {
            // KAD JUDETU!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            mDrawView.setOffset((float) Math.toDegrees(mValues[0]));
            mDrawView.invalidate();
        }
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
    /***
     * END SensorEventListener
     */

    /***
     * START LocationListener
     */
    public void onLocationChanged(Location location) {
        // do something here to save this new location
        Log.d(TAG, "Location Changed");
        mDrawView.setMyLocation(location.getLatitude(), location.getLongitude());
        mDrawView.invalidate();
    }

    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    public void onProviderEnabled(String s) {
    }

    public void onProviderDisabled(String s) {
    }

    /***
     * END LocationListener
     */
    @Override
    protected void onStop() {
        if (DEBUG)
            Log.d(TAG, "onStop");
        mSensorManager.unregisterListener(this);
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locMgr.removeUpdates(this);
        super.onDestroy();
    }


}
