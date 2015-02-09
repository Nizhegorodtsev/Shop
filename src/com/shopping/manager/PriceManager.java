package com.shopping.manager;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.shopping.data.AbstractStorable;

public class PriceManager extends AbstractStorable
{
    /**
     * Текущие цены
     */
    private HashMap<Long, Double>            currentPriceMap;

    /**
     * История цен
     */
    private HashMap<Long, ArrayList<Double>> priceHistoryMap;

    private static String                    PRICE_ARRAY   = "priceArray";

    private static String                    CURRENT_PRICE = "currentPrice";

    private static String                    PRICE_HISTORY = "priceHistory";

    public double getPrice(long id)
    {
        Double price = currentPriceMap.get(id);
        if (price != null)
            return price;
        return 0;
    }

    public ArrayList<Double> getPriceHistory(long id)
    {
        ArrayList<Double> history = priceHistoryMap.get(id);
        if (history != null)
            return new ArrayList<Double>(history);
        return new ArrayList<Double>();
    }

    public void setPrice(long id, double price)
    {
        ArrayList<Double> history = priceHistoryMap.get(id);
        if (history != null)
            history.add(price);
        else
        {
            history = new ArrayList<Double>();
            history.add(price);
            priceHistoryMap.put(id, history);
        }
        currentPriceMap.put(id, price);
    }

    @Override
    public void restore(JSONObject state) throws JSONException
    {
        JSONArray array = state.getJSONArray(PRICE_ARRAY);
        for (int i = 0; i < array.length(); i++)
        {
            JSONObject price = array.getJSONObject(i);
            Double currentPrice = price.getDouble(CURRENT_PRICE);
            JSONArray priceHistory = price.getJSONArray(PRICE_HISTORY);
            ArrayList<Double> historyArray = new ArrayList<Double>(priceHistory.length());
            for (int j = 0; j < priceHistory.length(); j++)
                historyArray.add(priceHistory.getDouble(j));
        }
    }

    @Override
    public JSONObject store()
    {
        return null;
    }

}
