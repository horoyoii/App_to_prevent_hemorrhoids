package com.example.preventthehemorrhoids;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.MonitorNotifier;
import org.altbeacon.beacon.RangeNotifier;
import org.altbeacon.beacon.Region;

import java.util.Collection;

import static com.example.preventthehemorrhoids.App.CHANNEL_ID;

// 알림 주기 2분 - 5분 -  10분
// 칼만 필터 란??

public class MyService extends Service implements BeaconConsumer {
    private BeaconManager beaconManager;
    private static final String TAG = "HEE";
    private static final int VALID_RANGE = 5;
    private enum STATE{
        EMPTY, USING
    }
    STATE State;

    private int threadhold, usingtime, major, minor;
    public MyService() {
        threadhold = 0;
        usingtime = 0;
        major = 1002;
        minor = 20;
        State = STATE.EMPTY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        // 1) init beaconManager
        beaconManager = BeaconManager.getInstanceForApplication(this);
        beaconManager.getBeaconParsers().add(new BeaconParser().setBeaconLayout("m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24"));
        beaconManager.bind(this);
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(intent != null) {
            //major = intent.getIntExtra("major", 0);
            //minor = intent.getIntExtra("minor", 0);
        }
        Intent notificationIntent = new Intent(this, MainActivity.class); // Notif 눌렀을 때 돌아갈 activity
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                0, notificationIntent, 0);
        Notification notification =  new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("사용중")
                .setContentText("사용중중입니다.")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentIntent(pendingIntent)
                .build();
        startForeground(1,notification);

        // do heavy work on a background THREAD
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        beaconManager.unbind(this);

    }


    // ==============================================================================

    @Override
    public void onBeaconServiceConnect() {
        beaconManager.removeAllRangeNotifiers();
        beaconManager.addRangeNotifier(new RangeNotifier() {
            @Override
            public void didRangeBeaconsInRegion(Collection<Beacon> beacons, Region region) {
                // The callback for these APIs is didRangeBeaconsInRegion(Region region,
                // Collection<Beacon>)which gives you a list of every beacon matched in the last scan interval.
                // unbind 전까지 1초에 한번씩 호출된다.
                threadhold++;
                if(threadhold==5){
                    StartDialogAct();
                }
                Log.d(TAG, "How many beacon in region ::" + String.valueOf(beacons.size()));
                if (beacons.size() > 0) {
                    for (Beacon beacon : beacons) {
                            Log.i(TAG, "Reading…" + "\n" + "proximityUuid:" + " " + beacon.getId1() + "\n" +
                                    "major:" + " " + beacon.getId2() + "\n" +
                                    "minor:" + " " + beacon.getId3() +"\n"+
                                    "Rssi:" + " "+beacon.getRssi());
                        if(major == beacon.getId2().toInt() && minor == beacon.getId3().toInt()) {
                            if(beacon.getDistance() < VALID_RANGE){
                                usingtime++;
                            }

                        }
                    }
                }
            }
        });

        beaconManager.addMonitorNotifier(new MonitorNotifier() {

            @Override
            public void didEnterRegion(Region region) {
                Log.i(TAG, "I just saw an beacon for the first time!");
            }

            @Override
            public void didExitRegion(Region region) {
                Log.i(TAG, "I no longer see an beacon");
            }

            @Override
            public void didDetermineStateForRegion(int state, Region region) {
                Log.i(TAG, "I have just switched from seeing/not seeing beacons: " + state);
            }
        });

        try {
            beaconManager.startMonitoringBeaconsInRegion(new Region("myMonitoringUniqueId", null, null, null));

        } catch (RemoteException e) {
        }


        try {
            beaconManager.startRangingBeaconsInRegion(new Region("myRangingUniqueId", null, null, null));
        } catch (RemoteException e) {    }
    }

    public void StartDialogAct(){
        Intent dialogIntent = new Intent(this, DialogActivity.class);
        startActivity(dialogIntent);
    }

}
