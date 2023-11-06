package com.example.tetchinyc;

import static java.text.DateFormat.getTimeInstance;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tetchinyc.view.Listener;
import com.example.tetchinyc.view.SchoolDetails;
import com.example.tetchinyc.view.SchoolDisplay;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements Listener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showDisplayFragment();
        Date currentTime = new Date(System.currentTimeMillis());
        long currentTime2 = System.currentTimeMillis()/1000;
//        String currentTimeString = currentTime.toString();
//
//        Log.i("Time: ", currentTimeString);


        DateFormat sdf = //getTimeInstance();
                new SimpleDateFormat("SSS");
        String time1 = sdf.format(currentTime);
        int newTime = Integer.parseInt(time1);
        int futureTime = newTime + 100;
        Log.i("Time", "Time is: " + currentTime2);
        Log.i("Time", "Time is: " + newTime);
    }

    private void showDisplayFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new SchoolDisplay())
                .commit();
    }

    @Override
    public void openDetails(String dbn) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new SchoolDetails().getInstance(dbn))
                .addToBackStack(null)
                .commit();
        for (PackageInfo pack :
                getPackageManager().getInstalledPackages(PackageManager.GET_PROVIDERS)) {
            ProviderInfo[] providers = pack.providers;
            if (providers != null) {
                for (ProviderInfo provider : providers)
                    Log.d("TAG", "openDetails: " + provider.authority);
            }
        }
    }

}