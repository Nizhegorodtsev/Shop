package com.shopping.entity;

import org.json.JSONException;
import org.json.JSONObject;

import com.shopping.data.AbstractStorable;

public class Category extends AbstractStorable
{
    private static String PARENT         = "parent";

    protected long        id             = 0;

    protected String      name           = new String();

    protected long        parentCategory = 0;

    @Override
    public JSONObject store() throws JSONException
    {
        JSONObject state = new JSONObject();
        state.put(ID, id);
        state.put(NAME, name);
        state.put(PARENT, parentCategory);
        return state;
    }

    @Override
    public void restore(JSONObject state) throws JSONException
    {
        id = state.getLong(ID);
        name = state.getString(NAME);
        parentCategory = state.getLong(PARENT);
    }

    public String getName()
    {
        return name;
    }

    public void setName(String categoryName)
    {
        this.name = categoryName;
    }

    public long getParentCategory()
    {
        return parentCategory;
    }

    public void setParentCategory(long parentCategory)
    {
        this.parentCategory = parentCategory;
    }

    public long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }
}
