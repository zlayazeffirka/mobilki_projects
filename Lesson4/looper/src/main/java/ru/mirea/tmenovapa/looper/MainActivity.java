package ru.mirea.tmenovapa.looper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;

import ru.mirea.tmenovapa.looper.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private Handler mainHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Инициализация основного обработчика для работы с UI потоком
        mainHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                String result = msg.getData().getString("result");
                Log.d("MainActivity", "Result: " + result);
            }
        };

        binding.buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateYearsOfWork();
            }
        });
    }

    private void calculateYearsOfWork() {
        String ageString = binding.editTextAge.getText().toString();
        String profession = binding.editTextProfession.getText().toString();

        try {
            int age = Integer.parseInt(ageString);
            // Проверяем, что возраст положительный, чтобы избежать отрицательного времени задержки
            if (age >= 0) {
                // Вычисляем время задержки в миллисекундах
                long delayMillis = age * 1000; // Преобразуем возраст в миллисекунды
                // Создаем новый поток для выполнения задержки
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(delayMillis);
                            // Создаем сообщение для отправки в основной поток с результатом
                            Message message = new Message();
                            Bundle bundle = new Bundle();
                            bundle.putString("result", "Вы работаете " + profession + " уже " + age + " лет.");
                            message.setData(bundle);
                            // Отправляем сообщение в основной поток через основной обработчик
                            mainHandler.sendMessage(message);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            } else {
                Log.d("MainActivity", "Введите корректный возраст.");
            }
        } catch (NumberFormatException e) {
            Log.d("MainActivity", "Введите корректный возраст.");
        }
    }
}