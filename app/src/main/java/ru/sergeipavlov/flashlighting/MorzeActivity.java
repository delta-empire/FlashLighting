package ru.sergeipavlov.flashlighting;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.Locale;

public class MorzeActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.morze);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.morze);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemID = item.getItemId();
                if (itemID == R.id.flashlight) {
                    Intent intent = new Intent(MorzeActivity.this, FlashLightActivity.class);
                    startActivity(intent);
                } else if (itemID == R.id.navigation) {
                    Intent intent = new Intent(MorzeActivity.this, NavigationActivity.class);
                    startActivity(intent);
                }
                return false;
            }
        });

        Toast.makeText(this,Locale.getDefault().getLanguage().toString(), Toast.LENGTH_LONG).show();
        Locale.getDefault().getLanguage().toString();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.morze), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
