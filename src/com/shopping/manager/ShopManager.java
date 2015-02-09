package com.shopping.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.SortedMap;
import java.util.TreeMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;

import com.shopping.MapUtil;
import com.shopping.data.AbstractStorable;
import com.shopping.entity.Shop;

public class ShopManager extends AbstractStorable
{
    public static String                 SHOPS       = "shops";
    /**
     * Контейнер содержит список магазинов. Ключ - название маганиза, значение - магазин
     */
    private static TreeMap<String, Shop> shopTreeMap = new TreeMap<String, Shop>();

    @SuppressLint("UseSparseArrays")
    private static HashMap<Long, Shop>   shopMap     = new HashMap<Long, Shop>();

    private static MapUtil<Shop>         mapUtil     = new MapUtil<Shop>();

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

    @Override
    public JSONObject store() throws JSONException
    {
        return null;
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
            // shopMap.put(shop.get, value)
        }
    }
}
