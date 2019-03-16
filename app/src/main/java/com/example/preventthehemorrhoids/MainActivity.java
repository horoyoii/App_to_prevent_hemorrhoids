package com.example.preventthehemorrhoids;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.suke.widget.SwitchButton;


// https://github.com/zcweng/SwitchButton

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BroadCastReceiver br = new BroadCastReceiver();
        IntentFilter filter = new IntentFilter(Intent.ACTION_BOOT_COMPLETED);
        this.registerReceiver(br, filter);


        SwitchButton switchButton = findViewById(R.id.switch_button);
        //switchButton.setChecked(true);
        switchButton.isChecked();
        /*
        switchButton.toggle();     //switch state
        switchButton.toggle(false);//switch without animation
        switchButton.setShadowEffect(true);//disable shadow effect
        switchButton.setEnabled(false);//disable button
        switchButton.setEnableEffect(false);//disable the switch animation
        */
        switchButton.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                if(isChecked == true){
                    Toast.makeText(getApplicationContext(), "확인", Toast.LENGTH_LONG).show();
                }else if(isChecked == false){
                    Toast.makeText(getApplicationContext(), "취ㅜ소", Toast.LENGTH_LONG).show();
                }
            }
        });

        //TODO : 버튼을 통하여 세팅의 값을 Shared Preference 에 저장한다.
        /*
        이를 통하여 재부팅 시 서비스를 실행하기 전 SP의 값을 참조하여 세팅이 되어있다면 서비스를 실행하고
        안되어있다면 실행하지 않는다.

         */

    }
}
