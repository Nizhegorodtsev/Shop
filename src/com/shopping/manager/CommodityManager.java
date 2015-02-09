package com.shopping.manager;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.shopping.data.AbstractStorable;
import com.shopping.data.Category;

public class CommodityManager extends AbstractStorable
{
    private static String                      CATEGORY = "Category";

    /**
     * Контейнер с категориями. Ключ - id категории
     */
    private HashMap<Long, Category>            categoryMap;

    /**
     * Контейнер содержит категории самого верхнего уровня. Ключ - название категории
     */
    private TreeMap<String, Category>          baseCategoryMap;

    /**
     * Контейнер хранит упорядоченный список категорий. Ключ - id родительской категории
     */
    private TreeMap<Long, SortedSet<Category>> categoriesByParent;

    @Override
    public JSONObject store() throws JSONException
    {
        return null;
    }

    @Override
    public void restore(JSONObject state) throws JSONException
    {
        JSONArray categoryArray = state.getJSONArray(CATEGORY);
        for (int i = 0; i < categoryArray.length(); i++)
        {
            Category category = new Category();
            category.restore(categoryArray.getJSONObject(i));
            categoryMap.put(category.getId(), category);
        }
        structureCategoryMap();
    }

    private void structureCategoryMap()
    {
        for (Entry<Long, Category> categoryEntry : categoryMap.entrySet())
        {
            Category category = categoryEntry.getValue();
            if (category.getParent() == 0)
                baseCategoryMap.put(category.getName(), category);
            else
            {
                if (!categoriesByParent.containsKey(category.getParent()))
                    categoriesByParent.put(category.getParent(), new TreeSet<Category>());
                categoriesByParent.get(category.getParent()).add(category);
            }
        }
    }
}
