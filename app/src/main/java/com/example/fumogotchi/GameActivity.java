package com.example.fumogotchi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity
{

    private ImageView fumoImageView;

    private ProgressBar hungerBar;
    private ProgressBar moodBar;
    private Drawable hungerProgressDrawable;
    private Drawable moodProgressDrawable;

    private TextView hungerLabel;
    private TextView moodLabel;

    private Button feedButton;
    private Button playButton;

    private Fumo fumo;

    private LifeFunctionsService lifeFunctionsService;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        fumo = new Fumo();

        fumoImageView = (ImageView) findViewById(R.id.fumoImageView);

        hungerBar = (ProgressBar) findViewById(R.id.hungerBar);
        moodBar = (ProgressBar) findViewById(R.id.moodBar);

        hungerLabel = (TextView)findViewById(R.id.hungerLabel);
        moodLabel = (TextView) findViewById(R.id.moodLabel);

        feedButton = (Button) findViewById(R.id.feedButton);
        playButton = (Button) findViewById(R.id.playButton);

        hungerBar.setProgress(fumo.getHunger());
        moodBar.setProgress(fumo.getMood());

        hungerProgressDrawable = hungerBar.getProgressDrawable().mutate();
        hungerProgressDrawable.setColorFilter(Color.GREEN, android.graphics.PorterDuff.Mode.SRC_IN);
        hungerBar.setProgressDrawable(hungerProgressDrawable);

        moodProgressDrawable = moodBar.getProgressDrawable().mutate();
        moodProgressDrawable.setColorFilter(Color.GREEN, android.graphics.PorterDuff.Mode.SRC_IN);
        moodBar.setProgressDrawable(moodProgressDrawable);


        lifeFunctionsService = new LifeFunctionsService(fumo, fumoImageView, hungerBar, moodBar, this);
        lifeFunctionsService.onCreate();

        feedButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                hungerBar.setProgress(fumo.improveHunger());
                if(fumo.getHunger() > 30){
                    hungerProgressDrawable.setColorFilter(Color.GREEN, android.graphics.PorterDuff.Mode.SRC_IN);
                    hungerBar.setProgressDrawable(hungerProgressDrawable);
                    fumoImageView.setImageResource(R.drawable.reimu_blink_neutral);
                }
                if(fumo.getHunger() > 50){
                    fumoImageView.setImageResource(R.drawable.reimu_blink_happy);
                }
            }
        });

        playButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                moodBar.setProgress(fumo.improveMood());
                if(fumo.getMood() > 30){
                    moodProgressDrawable.setColorFilter(Color.GREEN, android.graphics.PorterDuff.Mode.SRC_IN);
                    moodBar.setProgressDrawable(moodProgressDrawable);
                    fumoImageView.setImageResource(R.drawable.reimu_blink_neutral);
                }
                if(fumo.getMood() > 50){
                    fumoImageView.setImageResource(R.drawable.reimu_blink_happy);
                }
            }
        });
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        View decorView = getWindow().getDecorView();

        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }
}
