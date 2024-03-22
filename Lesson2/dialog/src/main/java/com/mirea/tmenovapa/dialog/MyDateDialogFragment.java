package com.mirea.tmenovapa.dialog;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class MyDateDialogFragment extends DialogFragment {
    private DatePickerDialog.OnDateSetListener listener;

    public static MyDateDialogFragment newInstance(DatePickerDialog.OnDateSetListener listener) {
        MyDateDialogFragment fragment = new MyDateDialogFragment();
        fragment.listener = listener;
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(getActivity(), listener, year, month, day);

        dialog.setTitle("Выберите дату");

        return dialog;
    }
}
