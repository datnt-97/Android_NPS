package base.ui.core;

import android.app.DatePickerDialog;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateTimePicker {
    DatePickerDialog datePickerDialog;
    Context context;
    final Calendar cldr = Calendar.getInstance();
    int day = cldr.get(Calendar.DAY_OF_MONTH);
    int month = cldr.get(Calendar.MONTH);
    int year = cldr.get(Calendar.YEAR);

    public DatePickerDialog getDatePickerDialog() {
        return datePickerDialog;
    }

    public void setDatePickerDialog(DatePickerDialog datePickerDialog) {
        this.datePickerDialog = datePickerDialog;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public Calendar getCldr() {
        return cldr;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public DateTimePicker(Context context) {
        this.context = context;
    }

    public DatePickerDialog setDatePickerDialog(DatePickerDialog.OnDateSetListener onDateSetListener, Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        datePickerDialog = new DatePickerDialog(context, onDateSetListener, calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        return datePickerDialog;
    }
}
