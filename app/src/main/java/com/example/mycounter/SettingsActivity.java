package com.example.mycounter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

public class SettingsActivity extends AppCompatActivity {

    Button btnUpperPlus, btnUpperMinus, btnLowerPlus, btnLowerMinus, btnMainActivity;
    Switch swUpperVib, swLowerVib, swUpperSound, swLowerSound;
    EditText extUpperLimit, extLowerLimit;

    SettingsClass settings;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        context = getApplicationContext();
        settings = SettingsClass.getInstance(context);

        btnUpperPlus = (Button) findViewById(R.id.btnUpperPlus);
        btnUpperMinus = (Button) findViewById(R.id.btnUpperMinus);
        btnLowerPlus = (Button) findViewById(R.id.btnLowerPlus);
        btnLowerMinus = (Button) findViewById(R.id.btnLowerMinus);
        btnMainActivity = (Button) findViewById(R.id.btnMainActivity);

        swLowerSound = (Switch) findViewById(R.id.swLowerSound);
        swLowerVib = (Switch) findViewById(R.id.swLowerVib);
        swUpperSound = (Switch) findViewById(R.id.swUpperSound);
        swUpperVib = (Switch) findViewById(R.id.swUpperVib);

        extLowerLimit = (EditText) findViewById(R.id.etxLowerLimit);
        extUpperLimit = (EditText) findViewById(R.id.etxUpperLimit);

        extUpperLimit.setText(String.valueOf(settings.UpperLimit));
        extLowerLimit.setText(String.valueOf(settings.LowerLimit));

        btnUpperPlus.setOnClickListener(view ->{
            settings.UpperLimit++;
            extUpperLimit.setText(String.valueOf(settings.UpperLimit));
        });
        btnUpperMinus.setOnClickListener(view ->{
            settings.UpperLimit--;
            extUpperLimit.setText(String.valueOf(settings.UpperLimit));
        });
        btnLowerPlus.setOnClickListener(view ->{
            settings.LowerLimit++;
            extLowerLimit.setText(String.valueOf(settings.LowerLimit));
        });
        btnLowerMinus.setOnClickListener(view ->{
            settings.LowerLimit--;
            extLowerLimit.setText(String.valueOf(settings.LowerLimit));
        });
        btnMainActivity.setOnClickListener(view ->{
            onBackPressed();
        });
        swLowerSound.setOnCheckedChangeListener((view, hasFocus) ->{
            settings.lowerSound = Boolean.valueOf(swLowerSound.isChecked());
        });
        swUpperSound.setOnCheckedChangeListener((view, hasFocus) ->{
            settings.upperSound = Boolean.valueOf(swUpperSound.isChecked());
        });
        swLowerVib.setOnCheckedChangeListener((view, hasFocus) ->{
            settings.lowerVib = Boolean.valueOf(swLowerVib.isChecked());
        });
        swUpperVib.setOnCheckedChangeListener((view, hasFocus) ->{
            settings.upperVib = Boolean.valueOf(swUpperVib.isChecked());
        });
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        extUpperLimit.setText(String.valueOf(settings.UpperLimit));
        extLowerLimit.setText(String.valueOf(settings.LowerLimit));
        swLowerSound.setChecked(settings.lowerSound);
        swUpperSound.setChecked(settings.upperSound);
        swLowerVib.setChecked(settings.lowerVib);
        swUpperVib.setChecked(settings.upperVib);

    }

    @Override
    protected void onPause(){
        super.onPause();

        settings.saveValues();
    }
}