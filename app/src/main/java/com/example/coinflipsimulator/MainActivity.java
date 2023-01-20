package com.example.coinflipsimulator;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.coinflipsimulator.classes.CoinFlipModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    private CoinFlipModel model = new CoinFlipModel();
    private TextView resultTextView;
    private ImageView resultImageView;
    private FloatingActionButton flipFab;
    private ActivityResultLauncher<Intent> vibrateToggle = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),result -> restoreSettingsFromPreferences()
    );

    private void restoreSettingsFromPreferences() {
        //this will update the main activity to tell us the vibrate setting is now on.
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTextView = findViewById(R.id.result_text_view);
        resultImageView = findViewById(R.id.result_image_view);
        flipFab = findViewById(R.id.flip_fab);
        flipFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                v.vibrate(500);
                model.flipCoin();
                if (model.isHeads()) {
                    resultTextView.setText(R.string.headsResult);
                    resultImageView.setImageResource(R.drawable.head);
                } else {
                    resultTextView.setText(R.string.tailsResult);
                    resultImageView.setImageResource(R.drawable.tail);
                }
                // Show the image for 5 seconds
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        resultImageView.setImageDrawable(null);
                    }
                }, 1000);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.menu_settings) {
            // launches the settings activity
            Intent settingsIntent = new Intent(MainActivity.this, SettingsActivity.class);
            vibrateToggle.launch(settingsIntent);

            return true;
        }
        else if(id == R.id.menu_about)
        {
            // Launch the about activity
            Intent aboutIntent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(aboutIntent);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);

    }

}
