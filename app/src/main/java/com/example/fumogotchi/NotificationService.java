package com.example.fumogotchi;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import androidx.core.app.NotificationCompat;


public class NotificationService
{

    private String CHANNEL_ID;
    private Context context;

    private NotificationManager manager;

    public NotificationService(String CHANNEL_ID, Context context) {
        this.CHANNEL_ID = CHANNEL_ID;
        this.context = context;
    }

    public void createNotificationChannel()
    {
        manager = (NotificationManager) context.getSystemService(Service.NOTIFICATION_SERVICE);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,"Fumogotchi", NotificationManager.IMPORTANCE_DEFAULT);

            channel.enableLights(true);
            channel.enableVibration(false);
            channel.setLightColor(Color.RED);
            channel.setDescription("Fumogotchi notficiations about fumo's hunger or mood.");

            manager.createNotificationChannel(channel);
        }
    }

    public void sendNotification(String title, String text, int notificationID){
        Intent intent = new Intent(context, GameActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, notificationID, intent, PendingIntent.FLAG_UPDATE_CURRENT);


        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(text)
                .setSmallIcon(R.drawable.fumogotchi_icon)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        manager.notify(notificationID, builder.build());
    }
}

