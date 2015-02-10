package com.shopping.utils;

import org.json.JSONException;
import org.json.JSONObject;

import com.shopping.data.AbstractStorable;

public class TimeValuePair extends AbstractStorable
{
    private static String VALUE = "value";

    private static String TIME  = "time";

    private long          time;

    private double        value;

    public TimeValuePair()
    {
    }

    public TimeValuePair(long time, double value)
    {
        this.time = time;
        this.value = value;
    }

    @Override
    public JSONObject store() throws JSONException
    {
        JSONObject state = new JSONObject();
        state.put(VALUE, value);
        state.put(TIME, time);
        return state;
    }

    @Override
    public void restore(JSONObject state) throws JSONException
    {
        time = state.getLong(TIME);
        value = state.getDouble(VALUE);
    }

    public long getTime()
    {
        return time;
    }

    public void setTime(long time)
    {
        this.time = time;
    }

    public double getValue()
    {
        return value;
    }

    public void setValue(double value)
    {
        this.value = value;
    }
}
