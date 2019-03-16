package com.example.preventthehemorrhoids;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class BroadCastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        String action = intent.getAction();

        if(action.equals("android.intent.action.BOOT_COMPLETED")){
            //TODO : 서비스를 실행하거나 안하거나 - 유저의 세팅에 의하여 판단
        }



    }
}
