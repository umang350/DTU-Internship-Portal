package com.ecell.dtu.ip;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.ecell.dtu.ip.MainActivity;
import com.ecell.dtu.ip.R;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
private String CHANNEL_ID="";
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Intent i= new Intent(this,MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pi=PendingIntent.getActivities(this,0, new Intent[]{i},PendingIntent.FLAG_ONE_SHOT);
        NotificationCompat.Builder notificationbuilder =new NotificationCompat.Builder(this,CHANNEL_ID);
        notificationbuilder.setContentTitle("InternDTU");
        notificationbuilder.setContentText(remoteMessage.getNotification().getBody());
        notificationbuilder.setAutoCancel(true);
        notificationbuilder.setSmallIcon(R.mipmap.ecell);
        notificationbuilder.setContentIntent(pi);
        NotificationManager notificationManager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0,notificationbuilder.build());
    }

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        Log.e("NEW_TOKEN",s);
        System.out.println("@@@"+s);
    }
}
