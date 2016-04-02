package com.android.mysummonsearch;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.text.format.DateFormat;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.mysummonsearch.constant.SQLCommand;
import com.android.mysummonsearch.util.DBOperator;

/**
 * Created by Jigi on 3/26/2016.
 */
    public class CalendarNew extends Activity implements DatePicker.OnDateChangedListener,OnClickListener{
    DatePicker dateP;
    ListView listView;
    Button showTimingsbtn;
    public static String packageName;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar);
        dateP=(DatePicker)this.findViewById(R.id.datePicker);
        listView=(ListView)this.findViewById(R.id.listView);
        showTimingsbtn=(Button)this.findViewById(R.id.button);
        showTimingsbtn.setOnClickListener(this);

        packageName = getPackageName();

    }

    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.button:
                                System.out.println("*******Date :" + dateP.getDayOfMonth()+"/"+dateP.getMonth()+"/"+ dateP.getYear());
                                //Query DB for this date
                                Cursor cursor = DBOperator.getInstance().execQuery(SQLCommand.SEARCH_QUERY, getArgs());
//                                SimpleCursorAdapter adapter = new SimpleCursorAdapter(
//                                        getApplicationContext(), R.layout.listitemevent, cursor,
//                                        new String[] { "EventName", "EventDate", "EventStartTime", "EventEndTime" },new int[]{
//                                        R.id.id_eventName,R.id.id_eventDate,R.id.id_eventStartTime,R.id.id_eventStartTime
//                                },SimpleCursorAdapter.IGNORE_ITEM_VIEW_TYPE);
//                listView.setAdapter(adapter);

        }

    }



    private static final String[] COLS = new String[]
                { CalendarContract.Events.TITLE, CalendarContract.Events.DTSTART};


    private String[] getArgs(){
        String args[]=new String[1];
          int year=dateP.getYear();
        int month=dateP.getMonth();
        int day=dateP.getDayOfMonth();
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        //format the date
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        args[0] = dateFormat.format(calendar.getTime());

        return args;
    }

    @Override
    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, monthOfYear, dayOfMonth);
        System.out.println("######Date Selected : " + dateP.getDayOfMonth()+"/"+dateP.getMonth()+"/"+ dateP.getYear());
    }
}

