package com.shopping.entity;

import org.json.JSONException;
import org.json.JSONObject;

import com.shopping.data.AbstractStorable;

public class Shop extends AbstractStorable
{
    private long   id   = 0;

    private String name = new String();

    @Override
    public JSONObject store() throws JSONException
    {
        JSONObject state = new JSONObject();
        state.put(ID, id);
        state.put(NAME, name);
        return state;
    }

    @Override
    public void restore(JSONObject state) throws JSONException
    {
        id = state.getLong(ID);
        name = state.getString(NAME);
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }
}
