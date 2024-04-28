package ru.mirea.tmenovapa.workmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import android.os.Bundle;
import android.view.View;

import ru.mirea.tmenovapa.workmanager.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WorkRequest uploadWorkRequest =
                new OneTimeWorkRequest.Builder(UploadWorker.class)
                        .build();
        WorkManager
                .getInstance(this)
                .enqueue(uploadWorkRequest);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
    public void OnStartTaskButtonClicked(View v) {
        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.UNMETERED)
                .setRequiresBatteryNotLow(true)
                .setRequiresCharging(true)
                .build();
        WorkRequest customWorkRequest = new OneTimeWorkRequest
                .Builder(UploadWorker.class)
                .setConstraints(constraints)
                .build();
        WorkManager.getInstance(this)
                .enqueue(customWorkRequest);
    }
}