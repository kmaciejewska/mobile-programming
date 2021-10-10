package com.example.task2;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button secondActivityBtn = (Button) findViewById(R.id.secondActivityBtn);
        Button googleBtn = (Button) findViewById(R.id.googleBtn);
        Button mapBtn = (Button) findViewById(R.id.mapBtn);
        Button phoneBtn = (Button) findViewById(R.id.phoneBtn);
        Button deltaBtn = (Button) findViewById(R.id.deltaBtn);

        secondActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), SecondActivity.class);
                callIntent(startIntent);
            }
        });

        googleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri webadress = Uri.parse(getResources().getString(R.string.google));
                Intent goToGoogle = new Intent(Intent.ACTION_VIEW, webadress);
                callIntent(goToGoogle);
            }
        });

        mapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri location = Uri.parse(getResources().getString(R.string.location));
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, location);
                //this had to be added - the check did not work. Opens the built-in application Android Maps:
                mapIntent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                callIntent(mapIntent);
            }
        });

        phoneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri number = Uri.parse(getResources().getString(R.string.phone));
                Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
                callIntent(callIntent);
            }
        });

        deltaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent launchIntent = new Intent(Intent.ACTION_VIEW);
                launchIntent.setClassName("com.example.quadraticequation",
                            "com.example.quadraticequation.MainActivity");
                callIntent(launchIntent);
            }
        });
    }

    public void callIntent(Intent intent) {
        String chooserTitle = getResources().getString(R.string.chooser_title);
        String toastTitle = getResources().getString(R.string.toast_title);
        Intent chooser = Intent.createChooser(intent, chooserTitle);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(chooser);
        } else {
            Toast.makeText(MainActivity.this, toastTitle, Toast.LENGTH_SHORT).show();
        }
    }
}