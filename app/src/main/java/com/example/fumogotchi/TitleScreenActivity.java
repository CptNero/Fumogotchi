package com.example.fumogotchi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class TitleScreenActivity extends AppCompatActivity
{

    private ImageView titleScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title_screen);

        titleScreen = (ImageView) findViewById(R.id.titleScreen);

        titleScreen.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                navigateToGameActivity();
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

    public void navigateToGameActivity()
    {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }
}
