package com.shopping.data;

import org.json.JSONException;
import org.json.JSONObject;

public class Category extends AbstractStorable
{
    private static String PARENT = "parent";

    protected long        id     = 0;

    protected String      name   = new String();

    protected long        parent = 0;

    @Override
    public JSONObject store() throws JSONException
    {
        JSONObject state = new JSONObject();
        state.put(ID, id);
        state.put(NAME, name);
        state.put(PARENT, parent);
        return state;
    }

    @Override
    public void restore(JSONObject state) throws JSONException
    {
        id = state.getLong(ID);
        name = state.getString(NAME);
        parent = state.getLong(PARENT);
    }

    public String getName()
    {
        return name;
    }

    public void setName(String categoryName)
    {
        this.name = categoryName;
    }

    public long getParent()
    {
        return parent;
    }

    public void setParent(long parentCategory)
    {
        this.parent = parentCategory;
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
