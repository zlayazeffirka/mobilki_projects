package ru.mirea.tmenovapa.mireaproject;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import java.util.Random;
import java.io.File;
import javax.xml.transform.Result;
public class ProgrammerTips extends Worker {
    private static final String[] PROGRAMMER_TIPS = {
            "Быть программистом, — значит научиться искать ответы на свои вопросы",
            "Будьте тем, у кого другие могут чему-то научиться.",
            "Не стоит накапливать технический долг.",
            "Чтение кода — недооцененный навык, но очень ценный.",
            "Парное программирование позволяет вам побыть и в роли учителя и в роли ученика.",
            "Окружайте себя единомышленниками, мотивирующими вас преодолевать трудности.",
            "Это не всегда будет легко. Но ведь мы все начинали с того же. У вас получится.",
            "Беритесь за задачи, которые пугают. Если они вас не пугают, значит не помогут вам расти."
    };

    public ProgrammerTips(
            @NonNull Context context,
            @NonNull WorkerParameters params) {
        super(context, params);
    }


    @NonNull
    @Override
    public Result doWork() {
        int tipIndex = new Random().nextInt(PROGRAMMER_TIPS.length);
        String dailyTip = "Совет дня: " + PROGRAMMER_TIPS[tipIndex];

        getApplicationContext().getSharedPreferences("ProgrammTips", Context.MODE_PRIVATE)
                .edit()
                .putString("DailyTip", dailyTip)
                .apply();

        return Result.success();
    }
}

