package com.example.preventthehemorrhoids;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.TextView;

import static com.example.preventthehemorrhoids.MyService.ALERT_TYPE;

public class DialogActivity extends Activity {
    TextView title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_dialog);

        title = findViewById(R.id.dlg_title);

        Intent intent = getIntent();
        MyService.ALERT type = (MyService.ALERT)intent.getSerializableExtra(ALERT_TYPE);

        switch (type){
            case FIRST_ALERT:
                title.setText("1차 경고");
                break;
            case SECOND_ALERT:
                title.setText("2차 경고");
                break;
            case THIRD_ALERT:
                title.setText("3차 경고");
                break;
        }



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("HEE", "onDestroy in DialogActivity");
    }
}
