package ru.sergeipavlov.flashlighting;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private TextView tvLattitude;
    private TextView tvLongitude;
    private TextView tvHeight;

    private static final DecimalFormat REAL_FORMATTER = new DecimalFormat("0.###");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemID = item.getItemId();
                if (itemID == R.id.flashlight) {
                    Intent intent = new Intent(MainActivity.this, FlashLightActivity.class);
                    startActivity(intent);
                } else if (itemID == R.id.navigation) {
                    Intent intent = new Intent(MainActivity.this, NavigationActivity.class);
                    startActivity(intent);
                } else if (itemID == R.id.morze) {
                    Intent intent = new Intent(MainActivity.this, MorzeActivity.class);
                    startActivity(intent);
            }
                return false;
            }
        });

        tvLattitude = findViewById(R.id.tvLattitude);
        tvLongitude = findViewById(R.id.tvLongitude);
        tvHeight = findViewById(R.id.tvHeight);

        FusedLocationProviderClient fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)  {
            return;
        }

        fusedLocationProviderClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    double lattitude, longitude;
                    double height;

                    lattitude = location.getLatitude();
                    longitude = location.getLongitude();
                    height = location.getAltitude();

                    tvLongitude.setText(REAL_FORMATTER.format(longitude));
                    tvLattitude.setText(REAL_FORMATTER.format(lattitude));
                    tvHeight.setText(REAL_FORMATTER.format(height));
                }
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}