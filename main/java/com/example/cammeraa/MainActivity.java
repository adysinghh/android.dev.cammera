package com.example.cammeraa;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.cammeraa.R;

public class MainActivity extends AppCompatActivity {
    ImageView selling;
    private final int CAMERA_REQ_CODE=1;
    ActivityResultLauncher<Intent> activityResultLauncher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // change the color of status bar
        getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity.this,R.color.white));
        // change the color of status bar icon
        View decor = getWindow().getDecorView();
        decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        selling = findViewById(R.id.selling);
        Button uploadbt= findViewById(R.id.uploadbt);

        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                Bundle bundle = result.getData().getExtras();
                if (bundle != null) {
                    Bitmap upimgg = (Bitmap) bundle.get("data");
                    selling.setImageBitmap(upimgg);
                }
            }

        });

        uploadbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                activityResultLauncher.launch(intent);
            }
        });
    }
}