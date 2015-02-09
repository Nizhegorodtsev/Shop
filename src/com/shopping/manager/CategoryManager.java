package com.shopping.manager;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.shopping.MapUtil;
import com.shopping.data.AbstractStorable;
import com.shopping.entity.Category;

public class CategoryManager extends AbstractStorable
{
    private static String                      CATEGORIES = "categories";

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

    private static MapUtil<Category>           mapUtil    = new MapUtil<Category>();

    @Override
    public JSONObject store() throws JSONException
    {
        JSONObject state = new JSONObject();
        JSONArray catigories = new JSONArray();
        for (Entry<Long, Category> categoryEntry : categoryMap.entrySet())
            catigories.put(categoryEntry.getValue().store());
        state.put(CATEGORIES, catigories);
        return state;
    }

    @Override
    public void restore(JSONObject state) throws JSONException
    {
        JSONArray categoryArray = state.getJSONArray(CATEGORIES);
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
            if (category.getParentCategory() == 0)
                baseCategoryMap.put(category.getName(), category);
            else
            {
                if (!categoriesByParent.containsKey(category.getParentCategory()))
                    categoriesByParent.put(category.getParentCategory(), new TreeSet<Category>());
                categoriesByParent.get(category.getParentCategory()).add(category);
            }
        }
    }
}
