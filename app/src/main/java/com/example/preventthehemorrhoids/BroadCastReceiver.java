package com.example.preventthehemorrhoids;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import static com.example.preventthehemorrhoids.MainActivity.TURNON;
import static com.example.preventthehemorrhoids.MainActivity.TURNONSETTING;

public class BroadCastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        String action = intent.getAction();
        if(action.equals("android.intent.action.BOOT_COMPLETED")){ // 휴대폰 재부팅 시 실행
            SharedPreferences sp = context.getSharedPreferences("MySettings", Activity.MODE_PRIVATE);
            String setting = sp.getString(TURNONSETTING, "");

            if(setting.equals(TURNON)){
                //TODO : Start the service
            }
        }



    }
}
