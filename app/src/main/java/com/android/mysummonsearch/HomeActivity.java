package com.android.mysummonsearch;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class HomeActivity extends AppCompatActivity {

    private ImageButton searchButton, chatButton, facebookButton, twitterButton, calendarButton;
    private ImageView wpi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        searchButton = (ImageButton) findViewById(R.id.searchButton);
        chatButton = (ImageButton) findViewById(R.id.chatButton);
        facebookButton = (ImageButton) findViewById(R.id.facebookButton);
        twitterButton = (ImageButton) findViewById(R.id.twitterButton);
        calendarButton = (ImageButton) findViewById(R.id.calendarButton);
        wpi = (ImageView) findViewById(R.id.wpi);

        wpi.setImageDrawable(getResources().getDrawable(R.drawable.wpi));
        chatButton.setImageDrawable(getResources().getDrawable(R.drawable.chat));
        searchButton.setImageDrawable(getResources().getDrawable(R.drawable.search));
        facebookButton.setImageDrawable(getResources().getDrawable(R.drawable.facebook));
        twitterButton.setImageDrawable(getResources().getDrawable(R.drawable.twitter));
        calendarButton.setImageDrawable(getResources().getDrawable(R.drawable.calendar));

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SummonActivity.class);
                startActivity(intent);
            }
        });

        chatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ChatActivity.class);
                startActivity(intent);
            }
        });

        calendarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CalendarNewActivity.class);
                startActivity(intent);
            }
        });

        facebookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://www.facebook.com/WPILibrary/");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        twitterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://twitter.com/WPI_Library");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
    }


}
