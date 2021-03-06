package com.example.preventthehemorrhoids;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.suke.widget.SwitchButton;


// https://github.com/zcweng/SwitchButton

public class MainActivity extends AppCompatActivity {
    public static final String TURNONSETTING ="TURNONSETTING";
    public static final String TURNON = "TURNON";
    public static final String TURNOFF = "TURNOFF";
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    private final String[] permissions = {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION};

    Button btn_setting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sp = getSharedPreferences("MySettings", Activity.MODE_PRIVATE);
        editor = sp.edit();
        SwitchButton switchButton = findViewById(R.id.switch_button);
        btn_setting = findViewById(R.id.btn_button);

        String setting = sp.getString(TURNONSETTING, "NULL");
        if(setting.equals(TURNON)) {
            switchButton.setChecked(true);
        }else{
            switchButton.setChecked(false);
        }

        /*
        switchButton.toggle();     //switch state
        switchButton.toggle(false);//switch without animation
        switchButton.setShadowEffect(true);//disable shadow effect
        switchButton.setEnabled(false);//disable button
        switchButton.setEnableEffect(false);//disable the switch animation
        */

        // ===========================================================================
        // Test




        // ===========================================================================

        switchButton.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                if(isChecked){
                    editor.putString(TURNONSETTING, TURNON);
                    editor.apply();
                    startService();
                }else{
                    editor.putString(TURNONSETTING, TURNOFF);
                    editor.apply();
                    stopService();
                }
            }
        });

        btn_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartSettingAct();

            }
        });

        checkPermissions();
    }

    public void startService(){
        Intent intent = new Intent(this, MyService.class);
        ContextCompat.startForegroundService(this, intent);

    }

    public void stopService(){
        Intent intent = new Intent(this, MyService.class);
        this.stopService(intent);
    }


    public void StartSettingAct(){
//        Intent intent = new Intent(this, SettingsActivity.class);
//        startActivity(intent);
        Intent i = new Intent(this, MyPreferenceActivity.class);
        startActivity(i);
    }


    private void checkPermissions() {
        boolean notgranted = true;
        for (String permission : permissions) {
            notgranted = notgranted && ContextCompat.checkSelfPermission(getApplicationContext(),
                    permission) != PackageManager.PERMISSION_GRANTED;
        }

        if (notgranted) {
            boolean showrationale = true;
            for (String permission : permissions) {
                showrationale = showrationale && (ActivityCompat.shouldShowRequestPermissionRationale(this,
                        permission));
            }
            if (showrationale) {
                Toast.makeText(this, "권한 부족, 설정 확인", Toast.LENGTH_LONG).show();
            } else {
                ActivityCompat.requestPermissions(MainActivity.this,
                        permissions,
                        100);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 100: {
                for (int i = 0; i < grantResults.length; i++) {
                    if (grantResults.length > 0 && grantResults[i] == PackageManager.PERMISSION_GRANTED) {

                    } else {
                        Toast.makeText(this, "권한 부족, 설정 확인", Toast.LENGTH_LONG).show();
                    }
                }
            }
        }
    }
}
