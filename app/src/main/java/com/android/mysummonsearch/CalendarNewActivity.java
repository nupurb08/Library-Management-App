package com.android.mysummonsearch;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.android.mysummonsearch.constant.SQLCommand;
import com.android.mysummonsearch.util.DBOperator;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Nupurb on 02-04-2016.
 */
public class CalendarNewActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendarnew);
        Button showEvent;
        final DatePicker dateP;
        final ListView listOfEvent;

        try{
            DBOperator.copyDB(getBaseContext());
        } catch(Exception e) {
            e.printStackTrace();
        }

        dateP = (DatePicker) this.findViewById(R.id.datePicker);
        listOfEvent = (ListView) this.findViewById(R.id.listView);

        showEvent = (Button) findViewById(R.id.showEvent_btn);
        showEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int dd = dateP.getDayOfMonth();
                int mm = dateP.getMonth()+1;
                int yy = dateP.getYear();
                System.out.println("Date : "+dd + "/" + mm + "/" + yy);
                Cursor cursor = DBOperator.getInstance().execQuery(SQLCommand.SEARCH_QUERY, this.getParam(dd, mm, yy));
                SimpleCursorAdapter adapter = new SimpleCursorAdapter(getApplicationContext(), R.layout.listxml, cursor,
                        new String[]{"EventName", "EventDate", "EventStartTime", "EventEndTime"},
                        new int[]{R.id.id_eName, R.id.id_eDate, R.id.id_eStTime, R.id.id_eEnTime},
                        SimpleCursorAdapter.IGNORE_ITEM_VIEW_TYPE);

                listOfEvent.setAdapter(adapter);
            }

            private String[] getParam(int dd, int mm, int yy) {
                String args[] = new String[1];
                String mon="",day="";
                if(mm<10)
                    mon="0"+mm;
                else
                    mon=""+mm;
                if(dd<10)
                    day ="0"+dd;
                else
                    day=""+dd;
                String dateInString = yy + "-" + mon + "-" + day;

                args[0] = dateInString;
                return args;
            }

        });
    }
}
