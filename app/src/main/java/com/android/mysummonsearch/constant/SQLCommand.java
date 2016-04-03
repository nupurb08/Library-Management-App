package com.android.mysummonsearch.constant;

public abstract class SQLCommand
{

    public static String SEARCH_QUERY="Select CalendarId as _id,EventName,EventDate,EventStartTime,EventEndTime from Calendar where EventDate=Date(?)";

}
