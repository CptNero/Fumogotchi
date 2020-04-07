package com.example.fumogotchi;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.DrawableRes;

import java.util.TimerTask;

public class MoodTask extends TimerTask
{
    Fumo fumo;
    ImageView fumoImageView;
    ProgressBar moodBar;
    Context context;
    NotificationService notificationService;
    boolean notificationWasSent;

    Drawable progressDrawable;

    public MoodTask(Fumo fumo, ImageView fumoImageView, ProgressBar moodBar, Context context) {
        this.fumo = fumo;
        this.fumoImageView = fumoImageView;
        this.moodBar = moodBar;
        this.context = context;
        notificationService = new NotificationService("FUMO_MOOD", context);
        notificationService.createNotificationChannel();
        progressDrawable = moodBar.getProgressDrawable().mutate();
    }

    @Override
    public void run()
    {
        if(fumo.getMood() > 0){
            fumo.setMood(fumo.getMood() - 10);
            moodBar.setProgress(fumo.getMood());
        }
        if(fumo.getMood() == 0){
            fumoImageView.setImageResource(R.drawable.reimu_blink_sad);
        }
        if(fumo.getMood() > 30){
            notificationWasSent = false;
        }
        if(fumo.getMood() < 30 && fumo.getMood() > 0 && !notificationWasSent){
            progressDrawable.setColorFilter(Color.RED, android.graphics.PorterDuff.Mode.SRC_IN);
            moodBar.setProgressDrawable(progressDrawable);
            fumoImageView.setImageResource(R.drawable.reimu_blink_neutral);
            notificationService.sendNotification("Bored Fumo!", "Your fumo is bored, play with her!", 2);
            notificationWasSent = true;
        }
    }
}
