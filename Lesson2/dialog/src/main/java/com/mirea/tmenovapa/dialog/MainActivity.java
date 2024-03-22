package com.mirea.tmenovapa.dialog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import android.app.TimePickerDialog;
import android.text.format.DateUtils;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.google.android.material.snackbar.Snackbar;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    TextView currentDateTime;
    Calendar dateAndTime=Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currentDateTime = findViewById(R.id.currentDateTime);
        setInitialDateTime();
    }
    public void onClickShowDialog(View view) {
        MyDialogFragment dialogFragment = new MyDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "mirea");
    }

    public void setTime(View view) {
        MyTimeDialogFragment dialogFragment = MyTimeDialogFragment.newInstance(timeListener);
        dialogFragment.show(getSupportFragmentManager(), "timePicker");
    }
    public void setDate(View view) {
        MyDateDialogFragment dialogFragment = MyDateDialogFragment.newInstance(dateListener);
        dialogFragment.show(getSupportFragmentManager(), "datePicker");
    }
    private void setInitialDateTime() {
        currentDateTime.setText(DateUtils.formatDateTime(this,
                dateAndTime.getTimeInMillis(),
                DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR
                        | DateUtils.FORMAT_SHOW_TIME));
    }



    TimePickerDialog.OnTimeSetListener timeListener = new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            dateAndTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
            dateAndTime.set(Calendar.MINUTE, minute);
            setInitialDateTime();
        }
    };

    DatePickerDialog.OnDateSetListener dateListener =new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            dateAndTime.set(Calendar.YEAR, year);
            dateAndTime.set(Calendar.MONTH, monthOfYear);
            dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            setInitialDateTime();
        }
    };

    public void onClickShowProgress(View view) {
        MyProgressDialogFragment dialogFragment = MyProgressDialogFragment.newInstance("Загрузка...", "Пожалуйста, подождите");
        dialogFragment.show(getSupportFragmentManager(), "progressDialog");


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                dialogFragment.dismissDialog();
                onClickProgress();
            }
        }).start();
    }

    public void onOkClicked() {
        Toast.makeText(getApplicationContext(), "Вы выбрали кнопку \"Иду дальше\"!",
                Toast.LENGTH_LONG).show();
    }
    public void onCancelClicked() {
        Toast.makeText(getApplicationContext(), "Вы выбрали кнопку \"Нет\"!",
                Toast.LENGTH_LONG).show();
    }
    public void onNeutralClicked() {
        Toast.makeText(getApplicationContext(), "Вы выбрали кнопку \"На паузе\"!",
                Toast.LENGTH_LONG).show();
    }

    public void onClickTime(){
        Snackbar.make(findViewById(android.R.id.content), "Время выбрано!",
                        Snackbar.LENGTH_SHORT)
                .show();
    }
    public  void onClickProgress(){
        Snackbar.make(findViewById(android.R.id.content), "Процесс завершен!",
                        Snackbar.LENGTH_LONG)
                .setAction("Ок", new View.OnClickListener (){
                    @Override
                    public void onClick(View v) {
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "Вы нажали Ок",Toast.LENGTH_LONG);
                        toast.show();
                    }
                })
                .show();
    }
}