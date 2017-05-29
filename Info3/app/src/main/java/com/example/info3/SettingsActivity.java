package com.example.info3;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;


public class SettingsActivity extends AppCompatActivity {

    ImageButton buttonBack;
    Button buttonOk;
    EditText editText;
    SharedPreferences sharedpreferences;
    SettingsActivity settingsActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);



        android.app.Fragment fragment = new SettingsScreen();
        android.app.FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        if(savedInstanceState == null){
            Log.i("TAG", "A2");
            fragmentTransaction.add(R.id.relative_layout, fragment, "settings_fragment");
            fragmentTransaction.commit();
           /* SharedPreferences  preferences = PreferenceManager.getDefaultSharedPreferences(this);
            String username = preferences.getString("editTextField", "default_value");
            Log.i("TAG is activity", username);*/
        }else{
            Log.i("TAG", "A3");
            fragment = getFragmentManager().findFragmentByTag("settings_fragment");
        }

        buttonBack = (ImageButton) findViewById(R.id.imageButton7);






        View.OnClickListener onClickListener = new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                switch (view.getId()) {
                    case R.id.imageButton7:
                        // grizimas atgal i pradini langa



                        finish();
                        break;
                }
            }
        };
        buttonBack.setOnClickListener(onClickListener);
    }





    public static  class SettingsScreen extends PreferenceFragment{
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.settings_screen);
            Log.i("TAG", "A1");


/*
            SharedPreferences  preferences = PreferenceManager.getDefaultSharedPreferences(this.getActivity());
            String username = preferences.getString("editTextField", "default_value");
            Log.i("TAG", username);

*/

        }

    }

}
