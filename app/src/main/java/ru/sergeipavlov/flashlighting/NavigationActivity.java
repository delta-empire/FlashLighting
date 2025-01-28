package ru.sergeipavlov.flashlighting;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.location.LocationServices;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import com.google.android.gms.location.FusedLocationProviderClient;

import java.text.DecimalFormat;

public class NavigationActivity extends AppCompatActivity {

    private TextView tvLattitude;
    private TextView tvLongitude;
    private TextView tvHeight;
    private TextView tvAddress;

    String addressString = "No Address";

    private static final DecimalFormat REAL_FORMATTER = new DecimalFormat("0.###");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.navigation);

        tvLattitude = findViewById(R.id.tvLatitude);
        tvLongitude = findViewById(R.id.tvLongitude);
        tvHeight = findViewById(R.id.tvHeight);
        tvAddress = findViewById(R.id.tvAddress);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.navigation);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemID = item.getItemId();
                if (itemID == R.id.flashlight) {
                    Intent intent = new Intent(NavigationActivity.this, FlashLightActivity.class);
                    startActivity(intent);
                } else if (itemID == R.id.navigation) {

                } else if (itemID == R.id.morze) {
                    Intent intent = new Intent(NavigationActivity.this, MorzeActivity.class);
                    startActivity(intent);
                }
                return false;
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.navigation), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    FusedLocationProviderClient fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

    }
}

