package com.example.preventthehemorrhoids;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import static com.example.preventthehemorrhoids.MyService.ALERT_TYPE;

public class DialogActivity extends Activity {
    TextView title, subtitle;
    ImageView imageView;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_dialog);

        title = findViewById(R.id.dlg_title);
        subtitle = findViewById(R.id.dlg_subtitle);
        imageView = findViewById(R.id.dlg_image);
        button = findViewById(R.id.dlg_btn);

        Intent intent = getIntent();
        MyService.ALERT type = (MyService.ALERT)intent.getSerializableExtra(ALERT_TYPE);

        switch (type){
            case FIRST_ALERT:
                title.setText("1차 경고");
                subtitle.setText("awefawef");
                imageView.setBackgroundResource(R.drawable.alert_first);
                break;
            case SECOND_ALERT:
                title.setText("2차 경고");
                subtitle.setText("awefawef");
                imageView.setBackgroundResource(R.drawable.ww);
                break;
            case THIRD_ALERT:
                title.setText("3차 경고");
                subtitle.setText("awefawef");
                imageView.setBackgroundResource(R.drawable.alert_second);
                break;
        }


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("HEE", "onDestroy in DialogActivity");
    }
}
