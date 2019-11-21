package com.example.hotelbooking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

public class ProgressBarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_bar);
        ProgressBar s = findViewById(R.id.mainSpinner1);
        s.setVisibility(View.VISIBLE);

        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        Intent mainIntent = new Intent(ProgressBarActivity.this,MainActivity.class);
                        ProgressBarActivity.this.startActivity(mainIntent);
                        ProgressBarActivity.this.finish();
                    }
                },
                5000
        );
    }
}
