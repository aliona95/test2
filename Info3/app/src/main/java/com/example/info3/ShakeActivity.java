package com.example.info3;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class ShakeActivity extends Activity {

    private ShakeDetector mShakeDetector;
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;

    ImageView image;
    String color = "blue";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shake); 

        final RelativeLayout rl = (RelativeLayout)findViewById(R.id.RelativeLayout02);
        rl.setBackgroundColor(Color.rgb(190, 238, 233));



        // ShakeDetector initialization
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mShakeDetector = new ShakeDetector(new ShakeDetector.OnShakeListener() {
            @Override
            public void onShake() {
                Toast toast = Toast.makeText(getApplicationContext(), "Įrenginys pajudintas. Greitis: " + ShakeDetector.speed, Toast.LENGTH_LONG);
                toast.show();
                if (color.equals("blue")) {
                    rl.setBackgroundColor(Color.rgb(255, 220, 171));
                    color = "yellow";
                    Log.i("COLOR", color);
                }else{

                    rl.setBackgroundColor(Color.rgb(190,238,233));
                    color = "blue";
                }
            }
        }) {
            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(mShakeDetector, mAccelerometer, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        mSensorManager.unregisterListener(mShakeDetector);
        super.onPause();
    }
}
