package com.example.mycounter;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.KeyEvent;
import android.view.Menu;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btnUpper, btnLower, btnSettings;
    TextView txtCurrentNumber;

    SettingsClass settings;
    Context context;

    MediaPlayer media;
    Vibrator vibrator;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = getApplicationContext();
        settings = SettingsClass.getInstance(context);

        settings.loadValues();

        btnUpper = (Button) findViewById(R.id.btnUpper);
        btnLower = (Button) findViewById(R.id.btnLower);
        btnSettings = (Button) findViewById(R.id.btnSettings);
        txtCurrentNumber = (TextView) findViewById(R.id.txtCurrentNumber);

        txtCurrentNumber.setText(String.valueOf(settings.CurrentNumber));

        media = MediaPlayer.create(context, R.raw.beeptone);
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        btnUpper.setOnClickListener(view ->{
            changeCurrentNumber(1);
        });

        btnLower.setOnClickListener(view ->{
            changeCurrentNumber(-1);
        });

        btnSettings.setOnClickListener(view ->{
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        int action = event.getAction();
        int keyCode = event.getKeyCode();

        switch (keyCode){
            case KeyEvent.KEYCODE_VOLUME_UP:
                if(action == KeyEvent.ACTION_DOWN)
                    changeCurrentNumber(5);
                return true;
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                if(action == KeyEvent.ACTION_DOWN)
                    changeCurrentNumber(-5);
                return true;
        }
        return super.dispatchKeyEvent(event);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void changeCurrentNumber(int value){
        if(value<0)
        {
            if (settings.CurrentNumber + value < settings.LowerLimit)
            {
                settings.CurrentNumber = settings.LowerLimit;

                if(settings.lowerSound)
                    media.start();
                if (settings.lowerVib)
                    vibrator.vibrate(VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE));
            }
            else {
                settings.CurrentNumber += value;
            }
        }
        else {
            if (settings.CurrentNumber + value > settings.UpperLimit)
            {
                settings.CurrentNumber = settings.UpperLimit;

                if(settings.upperSound)
                    media.start();
                if (settings.upperVib)
                    vibrator.vibrate(VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE));
            }
            else {
                settings.CurrentNumber += value;
            }
        }

        txtCurrentNumber.setText(String.valueOf(settings.CurrentNumber));
    }

    @Override
    protected void onPause(){
        super.onPause();
        settings.saveValues();
    }

    @Override
    protected void onResume() {
        super.onResume();
        txtCurrentNumber.setText(String.valueOf(settings.CurrentNumber));
    }
}