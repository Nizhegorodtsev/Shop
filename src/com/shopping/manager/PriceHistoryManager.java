package com.shopping.manager;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.shopping.data.AbstractStorable;
import com.shopping.entity.PriceHistory;

public class PriceHistoryManager extends AbstractStorable
{
    private static String                              PRICE_HISTORY = "priceHistory";

    /**
     * Первый ключ - id магазина. Второй ключ - id товара. Значение - история цен
     */
    private HashMap<Long, HashMap<Long, PriceHistory>> priceByShop;

    /**
     * Первый ключ - id товара. Второй ключ - id магазина. Значение - история цен
     */
    private HashMap<Long, HashMap<Long, PriceHistory>> priceByCommodity;

    @Override
    public JSONObject store() throws JSONException
    {
        JSONObject state = new JSONObject();
        JSONArray array = new JSONArray();
        for (Map.Entry<Long, HashMap<Long, PriceHistory>> mapEntry : priceByShop.entrySet())
        {
            for (Map.Entry<Long, PriceHistory> entry : mapEntry.getValue().entrySet())
            {
                array.put(entry.getValue().store());
            }
        }
        state.put(PRICE_HISTORY, array);
        return state;
    }

    @Override
    public void restore(JSONObject state) throws JSONException
    {
        JSONArray array = state.getJSONArray(PRICE_HISTORY);
        for (int i = 0; i < array.length(); i++)
        {
            PriceHistory history = new PriceHistory();
            history.restore(array.getJSONObject(i));

            if (!priceByShop.containsKey(history.getShopId()))
                priceByShop.put(history.getShopId(), new HashMap<Long, PriceHistory>());
            priceByShop.get(history.getShopId()).put(history.getCommodityId(), history);

            if (!priceByCommodity.containsKey(history.getCommodityId()))
                priceByCommodity.put(history.getCommodityId(), new HashMap<Long, PriceHistory>());
            priceByCommodity.get(history.getCommodityId()).put(history.getShopId(), history);
        }
    }
}
