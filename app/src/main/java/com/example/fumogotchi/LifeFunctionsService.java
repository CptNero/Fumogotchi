package com.example.fumogotchi;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.Timer;

public class LifeFunctionsService extends Service
{

    private static Timer timer = new Timer();
    private Fumo fumo;
    private ImageView fumoImageView;
    private ProgressBar hungerBar;
    private ProgressBar moodBar;
    private Context context;

    public LifeFunctionsService(Fumo fumo, ImageView fumoImageView, ProgressBar hungerBar, ProgressBar moodBar, Context context) {
        this.fumo = fumo;
        this.fumoImageView = fumoImageView;
        this.hungerBar = hungerBar;
        this.moodBar = moodBar;
        this.context = context;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }

    public void onCreate()
    {
        super.onCreate();
        startService();
    }

    private void startService()
    {
        timer.scheduleAtFixedRate(new HungerTask(fumo, fumoImageView, hungerBar, context), 5000, 10000);
        timer.scheduleAtFixedRate(new MoodTask(fumo, fumoImageView ,moodBar, context), 5000, 10000);
    }

}
