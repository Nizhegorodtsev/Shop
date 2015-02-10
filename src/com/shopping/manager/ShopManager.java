package com.shopping.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;

import com.shopping.data.AbstractStorable;
import com.shopping.entity.Shop;
import com.shopping.utils.MapUtils;

public class ShopManager extends AbstractStorable
{
    public static String                  SHOPS       = "shops";

    /**
     * Контейнер содержит список магазинов. Ключ - название маганиза, значение - магазин
     */
    private static TreeMap<String, Shop>  shopTreeMap = new TreeMap<String, Shop>();

    @SuppressLint("UseSparseArrays")
    /**
     * Контейнер содержит список магазинов. Ключ - id маганиза, значение - магазин
     */
    private static HashMap<Long, Shop>    shopMap     = new HashMap<Long, Shop>();

    private static MapUtils<String, Shop> mapUtil     = new MapUtils<String, Shop>();

    @Override
    public JSONObject store() throws JSONException
    {
        JSONObject state = new JSONObject();
        JSONArray shops = new JSONArray();
        for (Entry<Long, Shop> categoryEntry : shopMap.entrySet())
            shops.put(categoryEntry.getValue().store());
        state.put(SHOPS, shops);
        return state;
    }

    @Override
    public void restore(JSONObject state) throws JSONException
    {
        JSONArray shops = state.getJSONArray(SHOPS);
        for (int i = 0; i < shops.length(); i++)
        {
            Shop shop = new Shop();
            shop.restore(shops.getJSONObject(i));
            shopTreeMap.put(shop.getName().toLowerCase(), shop);
            shopMap.put(shop.getId(), shop);
        }
    }

    public static Shop getShopById(long id)
    {
        return shopMap.get(id);
    }

    public static ArrayList<Shop> getShopListByName(String prefix)
    {
        SortedMap<String, Shop> subMap = mapUtil.prefixSubMap(shopTreeMap, prefix);
        return mapUtil.mapToSortedList(subMap);
    }

    public ArrayList<Shop> getShopList()
    {
        return mapUtil.mapToSortedList(shopTreeMap);
    }

    public boolean contains(String name)
    {
        return shopMap.containsKey(name.trim());
    }

}
