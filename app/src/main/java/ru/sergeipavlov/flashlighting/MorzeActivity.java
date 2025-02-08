package ru.sergeipavlov.flashlighting;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MorzeActivity extends AppCompatActivity {

    private EditText etEnterText;

    private Button btnEncode;

    private TextView tvOutput;

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

        etEnterText = findViewById(R.id.etEnterText);
        btnEncode = findViewById(R.id.btnEncode);
        tvOutput = findViewById(R.id.tvOutput);

        final String[] AlphaNumeric = new String[37];

        final String[] AlphaNumeric1 = new String[37];

        AlphaNumeric[0] = "A";
        AlphaNumeric[1] = "B";
        AlphaNumeric[2] = "C";
        AlphaNumeric[3] = "D";
        AlphaNumeric[4] = "E";
        AlphaNumeric[5] = "F";
        AlphaNumeric[6] = "G";
        AlphaNumeric[7] = "H";
        AlphaNumeric[8] = "I";
        AlphaNumeric[9] = "J";
        AlphaNumeric[10] = "K";
        AlphaNumeric[11] = "L";
        AlphaNumeric[12] = "M";
        AlphaNumeric[13] = "N";
        AlphaNumeric[14] = "O";
        AlphaNumeric[15] = "P";
        AlphaNumeric[16] = "Q";
        AlphaNumeric[17] = "R";
        AlphaNumeric[18] = "S";
        AlphaNumeric[19] = "T";
        AlphaNumeric[20] = "U";
        AlphaNumeric[21] = "V";
        AlphaNumeric[22] = "W";
        AlphaNumeric[23] = "X";
        AlphaNumeric[24] = "Y";
        AlphaNumeric[25] = "Z";
        AlphaNumeric[26] = "0";
        AlphaNumeric[27] = "1";
        AlphaNumeric[28] = "2";
        AlphaNumeric[29] = "3";
        AlphaNumeric[30] = "4";
        AlphaNumeric[31] = "5";
        AlphaNumeric[32] = "6";
        AlphaNumeric[33] = "7";
        AlphaNumeric[34] = "8";
        AlphaNumeric[35] = "9";
        AlphaNumeric[36] = " ";

        AlphaNumeric1[0] = ".-";
        AlphaNumeric1[1] = "-...";
        AlphaNumeric1[2] = "-.-.";
        AlphaNumeric1[3] = "-..";
        AlphaNumeric1[4] = ".";
        AlphaNumeric1[5] = "..-.";
        AlphaNumeric1[6] = "--.";
        AlphaNumeric1[7] = "....";
        AlphaNumeric1[8] = "..";
        AlphaNumeric1[9] = ".---";
        AlphaNumeric1[10] = "-.-";
        AlphaNumeric1[11] = ".-..";
        AlphaNumeric1[12] = "--";
        AlphaNumeric1[13] = "-.";
        AlphaNumeric1[14] = "---";
        AlphaNumeric1[15] = ".--.";
        AlphaNumeric1[16] = "--.-";
        AlphaNumeric1[17] = ".-.";
        AlphaNumeric1[18] = "...";
        AlphaNumeric1[19] = "-";
        AlphaNumeric1[20] = "..-";
        AlphaNumeric1[21] = "...-";
        AlphaNumeric1[22] = ".--";
        AlphaNumeric1[23] = "-..-";
        AlphaNumeric1[24] = "-.--";
        AlphaNumeric1[25] = "--..";
        AlphaNumeric1[26] = "-----";
        AlphaNumeric1[27] = ".----";
        AlphaNumeric1[28] = "..---";
        AlphaNumeric1[29] = "...--";
        AlphaNumeric1[30] = "....-";
        AlphaNumeric1[31] = ".....";
        AlphaNumeric1[32] = "-....";
        AlphaNumeric1[33] = "--...";
        AlphaNumeric1[34] = "---..";
        AlphaNumeric1[35] = "----.";
        AlphaNumeric1[36] = "/";

        btnEncode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // When button encode is clicked then the
                // following lines inside this curly
                // braces will be executed

                // to get the input as string which the user wants to encode
                String input = etEnterText.getText().toString();

                String output = "";

                // variable used to compute the output
                // to get the length of the input string
                int l = input.length();

                // variables used in loops
                int i, j;

                for (i = 0; i < l; i++) {

                    // to extract each Token of the string at a time
                    String ch = input.substring(i, i + 1);

                    // the loop to check the extracted token with
                    // each letter and store the morse code in
                    // the output variable accordingly
                    for (j = 0; j < 37; j++) {

                        if (ch.equalsIgnoreCase(AlphaNumeric[j])) {

                            // concat space is used to separate
                            // the morse code of each token
                            output = output.concat(AlphaNumeric1[j]).concat(" ");


                        }
                    }
                }

                // to display the output
                tvOutput.setText(output);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.morze), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
