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
import com.shopping.entity.Commodity;
import com.shopping.utils.MapUtils;

public class CommodityManager extends AbstractStorable
{

    private static String                       COMMODITIES = "commodities";

    /**
     * Контейнер с товарами. Ключ - id категории
     */
    private HashMap<Long, Commodity>            commodityMap;

    /**
     * Контейнер хранит упорядоченный список товаров. Ключ - id родительской категории
     */
    private TreeMap<Long, SortedSet<Commodity>> commodityByParent;

    private static MapUtils<String, Commodity>  mapUtil     = new MapUtils<String, Commodity>();

    @Override
    public JSONObject store() throws JSONException
    {
        JSONObject state = new JSONObject();
        JSONArray commodities = new JSONArray();
        for (Entry<Long, Commodity> categoryEntry : commodityMap.entrySet())
            commodities.put(categoryEntry.getValue().store());
        state.put(COMMODITIES, commodities);
        return state;
    }

    @Override
    public void restore(JSONObject state) throws JSONException
    {
        JSONArray commodityArray = state.getJSONArray(COMMODITIES);
        for (int i = 0; i < commodityArray.length(); i++)
        {
            Commodity commodity = new Commodity();
            commodity.restore(commodityArray.getJSONObject(i));
            commodityMap.put(commodity.getId(), commodity);
            if (!commodityByParent.containsKey(commodity.getParentCategory()))
                commodityByParent.put(commodity.getParentCategory(), new TreeSet<Commodity>());
            commodityByParent.get(commodity.getParentCategory()).add(commodity);
        }
    }
}
