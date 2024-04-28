package ru.mirea.tmenovapa.data_thread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import ru.mirea.tmenovapa.data_thread.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        TextView textView = binding.textView;

        // Устанавливаем информацию о различиях методов и последовательности запуска
        textView.setText("Различия между методами и последовательность запуска:\n\n");
        textView.append("Метод runOnUiThread:\n");
        textView.append("- Выполняет указанный код в главном потоке.\n");
        textView.append("- Полезен для обновления пользовательского интерфейса из фонового потока.\n\n");

        textView.append("Метод postDelayed:\n");
        textView.append("- Добавляет указанный код в очередь выполнения с задержкой.\n");
        textView.append("- Код будет выполнен через определенное количество миллисекунд.\n");
        textView.append("- Позволяет выполнить асинхронные операции без блокировки главного потока.\n\n");

        textView.append("Метод post:\n");
        textView.append("- Добавляет указанный код в очередь выполнения.\n");
        textView.append("- Код будет выполнен как только будет выполнена текущая очередь действий для представления.\n");
        textView.append("- Позволяет выполнить асинхронные операции в главном потоке без задержки.\n\n");

        final Runnable runn1 = new Runnable() {
            @Override
            public void run() {
                binding.tvInfo.setText("runn1");
            }
        };
        final Runnable runn2 = new Runnable() {
            @Override
            public void run() {
                binding.tvInfo.setText("runn2");
            }
        };
        final Runnable runn3 = new Runnable() {
            @Override
            public void run() {
                binding.tvInfo.setText("runn3");
            }
        };
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(2);
                    runOnUiThread(runn1);
                    TimeUnit.SECONDS.sleep(1);
                    binding.tvInfo.postDelayed(runn3, 2000);
                    binding.tvInfo.post(runn2);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });
        t.start();
    }
}