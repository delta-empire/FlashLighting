package ru.sergeipavlov.flashlighting;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView tvLatitudeShow;
    private TextView tvLongitudeShow;
    private TextView tvHeightShow;
    private TextView tvAddressShow;

    private static final DecimalFormat REAL_FORMATTER = new DecimalFormat("0.###");

    private static final int ACCESS_FINE_LOCATION = 100;
    private static final int ACCESS_COARSE_LOCATION = 101;

    String addressString = "No address";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);



        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnItemSelectedListener(item -> {
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
            } else if (itemID == R.id.settings) {
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
            return false;
        });

        tvLatitudeShow = findViewById(R.id.tvLatitudeShow);
        tvLongitudeShow = findViewById(R.id.tvLongitudeShow);
        tvHeightShow = findViewById(R.id.tvHeightShow);
        tvAddressShow = findViewById(R.id.tvAddressShow);





        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, ACCESS_FINE_LOCATION );
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, ACCESS_COARSE_LOCATION);
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        FusedLocationProviderClient fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        fusedLocationProviderClient.getLastLocation().addOnSuccessListener(this, location -> {
            if (location != null) {
                double lattitude, longitude;
                double height;

                lattitude = location.getLatitude();
                longitude = location.getLongitude();
                height = location.getAltitude();

                tvLongitudeShow.setText(REAL_FORMATTER.format(longitude));
                tvLatitudeShow.setText(REAL_FORMATTER.format(lattitude));
                tvHeightShow.setText(REAL_FORMATTER.format(height));

                Geocoder gc = new Geocoder(MainActivity.this, Locale.getDefault());

                try {
                    List<Address> adresses = gc.getFromLocation(lattitude, longitude, 1);
                    StringBuilder sb = new StringBuilder();
                    if (!(adresses != null ? adresses.isEmpty() : false)) {
                        Address address = null;
                        if (adresses != null) {
                            address = adresses.get(0);
                        }
                        for (int i = 0; i < address.getMaxAddressLineIndex(); i++) {
                            sb.append(address.getAddressLine(i)).append("\n");
                        }

                        sb.append(address.getCountryName()).append("\n");
                        sb.append(address.getPostalCode()).append("\n");
                        sb.append(address.getLocality()).append("\n");
                    }
                    addressString = sb.toString();
                    tvAddressShow.setText(sb.toString());

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}