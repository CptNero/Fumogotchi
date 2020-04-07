package com.example.fumogotchi;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.RequiresApi;

import java.util.TimerTask;

public class HungerTask extends TimerTask
{

    Fumo fumo;
    ImageView fumoImageView;
    ProgressBar hungerBar;
    Context context;
    NotificationService notificationService;
    boolean notificationWasSent;

    Drawable progressDrawable;

    public HungerTask(Fumo fumo, ImageView fumoImageView, ProgressBar hungerBar, Context context) {
        this.fumo = fumo;
        this.fumoImageView = fumoImageView;
        this.hungerBar = hungerBar;
        this.context = context;
        notificationService = new NotificationService("FUMO_HUNGER", context);
        notificationService.createNotificationChannel();
        progressDrawable = hungerBar.getProgressDrawable().mutate();
    }

    @Override
    public void run()
    {
        if(fumo.getHunger() > 0) {
            fumo.setHunger(fumo.getHunger() - 10);
            hungerBar.setProgress(fumo.getHunger());
        }
        if(fumo.getHunger() == 0){
            fumoImageView.setImageResource(R.drawable.reimu_blink_sad);
        }
        if(fumo.getHunger() > 30){
            notificationWasSent = false;
        }
        if(fumo.getHunger() < 30 && fumo.getMood() > 0 && !notificationWasSent){
            progressDrawable.setColorFilter(Color.RED, android.graphics.PorterDuff.Mode.SRC_IN);
            hungerBar.setProgressDrawable(progressDrawable);
            fumoImageView.setImageResource(R.drawable.reimu_blink_neutral);
            notificationService.sendNotification("Hungry Fumo!", "Your fumo is hungry, feed her!", 1);
            notificationWasSent = true;
        }
    }
}
