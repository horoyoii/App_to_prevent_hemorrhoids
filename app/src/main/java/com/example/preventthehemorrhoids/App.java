package com.example.preventthehemorrhoids;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class App extends Application {
    public static final String CHANNEL_ID = "ServiceChannel";
    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannel();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    private void createNotificationChannel(){
        if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.O){
            // oreo 와 같거나 높다면 채널을 생성한다.
            NotificationChannel serviceChannel = new NotificationChannel(
                    CHANNEL_ID,
                    "Service Channel",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(serviceChannel);

        }
    }
}
