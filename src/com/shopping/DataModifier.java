package com.shopping;

import org.json.JSONException;

import com.shopping.data.AbstractStorable;
import com.shopping.datalistener.DataChangeListener;

public abstract class DataModifier extends AbstractStorable
{
    protected DataChangeListener dataListener;

    public void add(AbstractStorable obj)
    {
    }

    public void addByUser(AbstractStorable obj)
    {
        add(obj);
        try
        {
            dataListener.add(store());
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }

    public void remove(AbstractStorable obj)
    {
    }

    public void removeByUser(AbstractStorable obj)
    {
        remove(obj);
        try
        {
            dataListener.remove(store());
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }

    public void change(AbstractStorable obj)
    {
    }

    public void changeByUser(AbstractStorable obj)
    {
        change(obj);
        try
        {
            dataListener.change(store());
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }
}
