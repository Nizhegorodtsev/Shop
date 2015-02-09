package com.shopping.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;

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

    public static Shop getShopById(long id)
    {
        return shopMap.get(id);
    }

    public static ArrayList<Shop> getShopListByName(String prefix)
    {
        char nextLetter = (char) (prefix.charAt(prefix.length() - 1) + 1);
        String end = prefix.substring(0, prefix.length() - 1) + nextLetter;
        SortedMap<String, Shop> subMap = shopTreeMap.subMap(prefix.toLowerCase(), end.toLowerCase());
        return mapToList(subMap);
    }

    public ArrayList<Shop> getShopList()
    {
        return mapToList(shopTreeMap);
    }

    public boolean contains(String name)
    {
        return shopMap.containsKey(name.trim());
    }

    private static ArrayList<Shop> mapToList(SortedMap<String, Shop> map)
    {
        ArrayList<Shop> shops = new ArrayList<Shop>(map.size());
        for (Map.Entry<String, Shop> e : map.entrySet())
            shops.add(e.getValue());
        return shops;
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
