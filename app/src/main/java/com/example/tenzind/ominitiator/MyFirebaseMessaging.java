package com.example.tenzind.ominitiator;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by tenzind on 12/1/2017.
 */
public class MyFirebaseMessaging extends FirebaseMessagingService{
    private static final String TAG="MyFirebaseMessaging";
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.e(TAG, "From: " + remoteMessage.getFrom());
        if(remoteMessage.getData().size()>0)
        {
            Log.d(TAG,"Message data :"+remoteMessage.getData());
        }
        if(remoteMessage.getNotification()!=null)
        {
           Log.d(TAG,"message body :"+remoteMessage.getNotification().getBody());
           sendNotification(remoteMessage.getNotification().getBody());
        }
    }
    private void sendNotification(String body) {
        Intent intent=new Intent(this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);
        Uri notificationSound= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notiBuilder=new NotificationCompat.Builder(this).setSmallIcon(R.mipmap.ic_launcher).setContentTitle("Order Management Notifications").setContentText(body).setAutoCancel(true).setSound(notificationSound).setContentIntent(pendingIntent);
        NotificationManager notificationManager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0,notiBuilder.build());
    }
}
