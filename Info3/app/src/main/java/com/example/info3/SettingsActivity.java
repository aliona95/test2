package com.example.info3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class SettingsActivity extends AppCompatActivity {

    ImageButton buttonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        buttonBack = (ImageButton) findViewById(R.id.imageButton7);
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //switch (view.getId()) {
                 //   case R.id.imageButton5:

                        Intent i0 = new Intent(SettingsActivity.this, HomeActivity.class);
                        startActivity(i0);
                   //     break;
               // }
            }
        };
        buttonBack.setOnClickListener(onClickListener);
    }
}
