package com.android.mysummonsearch;

import java.text.Format;
import java.text.SimpleDateFormat;
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
import android.widget.TextView;
import android.widget.Toast;

import com.android.mysummonsearch.constant.SQLCommand;
import com.android.mysummonsearch.util.DBOperator;

/**
 * Created by Jigi on 3/26/2016.
 */
    public class CalendarNew extends Activity implements OnClickListener,CalendarView.OnDateChangeListener{
        private Cursor mCursor = null;
    DatePicker datePicker;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar);
        datePicker=(DatePicker)this.findViewById(R.id.datePicker);

        mCursor = getContentResolver().query(
        CalendarContract.Events.CONTENT_URI, COLS, null, null, null);
        mCursor.moveToFirst();

        Button b = (Button)findViewById(R.id.next);
        b.setOnClickListener(this);
        b = (Button)findViewById(R.id.previous);
        b.setOnClickListener(this);
        onClick(findViewById(R.id.previous));


    }

    @Override
    public void onClick(View v) {
        TextView tv = (TextView)findViewById(R.id.data);


        String title = "N/A";


        Long start = 0L;


        switch(v.getId()) {
            case R.id.next:
                if(!mCursor.isLast()) mCursor.moveToNext();
                break;
            case R.id.previous:
                if(!mCursor.isFirst()) mCursor.moveToPrevious();
                break;
            case R.id.datePicker:

                int year=datePicker.getYear();
                int month=datePicker.getMonth();
                int day=datePicker.getDayOfMonth();
                Date dateSelected = new Date() ;

                Calendar calendar = Calendar.

                //Query DB for this date

                DBOperator.getInstance().execSQL(SQLCommand.SEARCH_QUERY, this.getParameters(dateSelected));
                Toast.makeText(getApplicationContext(), "All dues have been cleared.", Toast.LENGTH_SHORT).show();

        }


        Format df = DateFormat.getDateFormat(this);
        Format tf = DateFormat.getTimeFormat(this);
        try {
            title = mCursor.getString(0);


            start = mCursor.getLong(1);


        } catch (Exception e) {
//ignore


        }


        tv.setText(title+" on "+df.format(start)+" at "+tf.format(start));


    }



    private static final String[] COLS = new String[]
                { CalendarContract.Events.TITLE, CalendarContract.Events.DTSTART};


    private String[] getArgs(boolean isCheckout){
        String args[]=new String[4];
          int year=datePicker.getYear();
        int month=datePicker.getMonth();
        int day=datePicker.getDayOfMonth();
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        //format the date
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        args[2] = dateFormat.format(calendar.getTime());
        if (isCheckout) args[3]="N";
        else args[3]="Y";
        return args;
    }
}

}
