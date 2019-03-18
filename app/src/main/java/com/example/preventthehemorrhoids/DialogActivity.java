package com.example.preventthehemorrhoids;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;

public class DialogActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_dialog);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("HEE", "onDestroy in DialogActivity");
    }
}
