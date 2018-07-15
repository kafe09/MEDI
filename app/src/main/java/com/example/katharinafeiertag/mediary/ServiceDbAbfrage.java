package com.example.katharinafeiertag.mediary;

import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

//war unser IntentService - restlicher Code in MedikmaentenHinzufugenActivity

public class ServiceDbAbfrage extends Service {
    final static String EXAMPLE_ACTION = "example_action";          // Example action for client communication
    final static String EXAMPLE_CATEGORY = "example_cat";           // Example category to filter extra infor form client
    final static String EXAMPLE_NAME = "Example ScheduledJob";     // Name of the service
    final static int DEFAULT_PROCESSING = 3000;                     // Default processing time
    private NotificationManager mNM;
    ProgressDialog progress;


    @Override
    public void onCreate() {
        super.onCreate();

    }

    public ServiceDbAbfrage() {

    }

    /**
     * Diese Methode wird gestartet, wenn der Benutzer ein neues Medikament
     * zu seiner digitalen Hausapotheke hinzufügt
     * @param intent
     * @param flags
     * @param startId
     * @return
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int wartezeit = intent.getExtras().getInt("wartezeit");

        try {

            Thread.sleep(wartezeit);
            //progress = ProgressDialog.show(this, "Aktualisierung", "Warten Sie einen Moment...");
            //progress.dismiss();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String msg = "Die Medikamentendatenbank wurde aktualisiert!";

        broadcastExampleInfo(msg);

        stopSelf();

        return Service.START_STICKY;
    }

    @Override
    public IBinder onBind (Intent intent) {
        return null;
    }

    private void broadcastExampleInfo(String msg){
        Intent intent = new Intent();
        intent.setAction(EXAMPLE_ACTION);
        intent.putExtra(EXAMPLE_CATEGORY, msg);
        sendBroadcast(intent);
    }


}
