package com.example.smartpantrymanager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.materialswitch.MaterialSwitch;

public class SettingsActivity extends AppCompatActivity {

    public static final String PREFREN_name = "smart_pantry_prefren";
    public static final String KEY_EXPIRY_ALERTS= "expiring_alerts";
    public static final String KEY_METRIC_UNITS = "metric_unit";

    private SharedPreferences prefren;
    private TextView textSettingsStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setTitle("-->Settings<--");

        prefren = getSharedPreferences(PREFREN_name, MODE_PRIVATE);

        MaterialSwitch switchExpiryAlerts = findViewById(R.id.switchExpiryAlerts);
        MaterialSwitch switchMetricUnits = findViewById(R.id.switchMetricUnits);
        textSettingsStatus = findViewById(R.id.textSettingsStatus);

        switchExpiryAlerts.setOnCheckedChangeListener((button, isChecked) ->
    {
            prefren.edit().putBoolean(KEY_EXPIRY_ALERTS, isChecked).apply();
            showStatus();
        });


        switchMetricUnits.setOnCheckedChangeListener((button, isChecked) ->
        {
            prefren.edit().putBoolean(KEY_METRIC_UNITS, isChecked).apply();
            showStatus();
        });
        showStatus();
        BottomNavigationView bottomNav = findViewById(R.id.bottomNav);
        bottomNav.setSelectedItemId(R.id.nav_settings);
        bottomNav.setOnItemSelectedListener(menuItem -> {
            if (menuItem.getItemId() == R.id.nav_pantry) {
                startActivity(new Intent(SettingsActivity.this, MainActivity.class));
                finish();
            }
            if (menuItem.getItemId() == R.id.nav_suggested) {
                startActivity(new Intent(SettingsActivity.this, SuggestedRecipesActivity.class));
                finish();
            }
            return true;
        });
    }

    private void showStatus(){
        boolean alerts = prefren.getBoolean(KEY_EXPIRY_ALERTS, true);
        boolean metric = prefren.getBoolean(KEY_METRIC_UNITS, false);
        textSettingsStatus.setText("Expiring Alerts: " + (alerts? "ON" : "OFF")
                + "\nUnits: " + (metric ? "METRIC" :  "STANDARD")
        + "\n\nAll Preferences are saved on this device and would survive even after restarting the app");

    }

}
