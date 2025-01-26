package ru.sergeipavlov.flashlighting;

import android.content.Context;
import android.content.Intent;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class FlashLightActivity extends AppCompatActivity {

    private ToggleButton toggleButtonFlashLight;
    private CameraManager cameraManager;
    private String getCameraID;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.flashlight);

        toggleButtonFlashLight = findViewById(R.id.toogle_button_flashlight);
        cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);

            try {
                getCameraID = cameraManager.getCameraIdList()[0];
            } catch (CameraAccessException e) {
                throw new RuntimeException(e);
            }
        toggleButtonFlashLight.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (toggleButtonFlashLight.isChecked()) {
                    try {
                        cameraManager.setTorchMode(getCameraID, true);
                    } catch (CameraAccessException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    try {
                        cameraManager.setTorchMode(getCameraID, false);
                    } catch (CameraAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.flashlight);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemID = item.getItemId();
                if (itemID == R.id.morze) {
                    Intent intent = new Intent(FlashLightActivity.this, MorzeActivity.class);
                    startActivity(intent);
                } else if (itemID == R.id.navigation) {
                    Intent intent = new Intent(FlashLightActivity.this, NavigationActivity.class);
                    startActivity(intent);
                }
                return false;
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.flashlight), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}


