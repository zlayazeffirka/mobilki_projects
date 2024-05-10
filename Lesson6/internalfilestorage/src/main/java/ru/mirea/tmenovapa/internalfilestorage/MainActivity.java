package ru.mirea.tmenovapa.internalfilestorage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;

import ru.mirea.tmenovapa.internalfilestorage.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    public void OnSaveTextButtonClicked(View v){
        FileOutputStream outputStream;
        try{
            outputStream = openFileOutput("information.txt", Context.MODE_PRIVATE);
            outputStream.write(binding.inputTextPanel.getText().toString().getBytes());
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
            outputStream.close();
        } catch (IOException e) {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }

    }
}