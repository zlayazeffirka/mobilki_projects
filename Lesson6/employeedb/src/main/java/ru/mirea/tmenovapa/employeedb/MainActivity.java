package ru.mirea.tmenovapa.employeedb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final AppDataBase database = App.getInstance().getDatabase();
        final EmployeeDao employeeDao = database.employeeDao();

        employeeDao.insert(new Employee(1,"Sam Junior", 70000));
        employeeDao.insert(new Employee(2,"Tom Middle", 200000));
        employeeDao.insert(new Employee(3,"Dean Senior", 400000));

    }
}