package com.shopping.entity;

import java.util.Map;
import java.util.TreeMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.shopping.data.AbstractStorable;
import com.shopping.utils.MapUtils;
import com.shopping.utils.TimeValuePair;

public class PriceHistory extends AbstractStorable
{
    private static String                        PRICE_HISTORY = "priceHistory";

    private static String                        SHOP_ID       = "shopId";

    private static String                        COMMODITY_ID  = "commodityId";

    private long                                 shopId;

    private long                                 commodityId;

    private TreeMap<Long, TimeValuePair>         history;

    private static MapUtils<Long, TimeValuePair> mapUtils      = new MapUtils<Long, TimeValuePair>();

    @Override
    public JSONObject store() throws JSONException
    {
        JSONObject state = new JSONObject();
        state.put(SHOP_ID, shopId);
        state.put(COMMODITY_ID, commodityId);
        JSONArray priceHistory = new JSONArray();
        for (Map.Entry<Long, TimeValuePair> entry : history.entrySet())
            priceHistory.put(entry.getValue().store());
        state.put(PRICE_HISTORY, priceHistory);
        return state;
    }

    @Override
    public void restore(JSONObject state) throws JSONException
    {
        shopId = state.getLong(SHOP_ID);
        commodityId = state.getLong(COMMODITY_ID);
        JSONArray jsonHistory = state.getJSONArray(PRICE_HISTORY);
        for (int i = 0; i < jsonHistory.length(); i++)
        {
            TimeValuePair pair = new TimeValuePair();
            pair.restore(jsonHistory.getJSONObject(i));
            history.put(pair.getTime(), pair);
        }
    }

    public long getShopId()
    {
        return shopId;
    }

    public void setShopId(long shopId)
    {
        this.shopId = shopId;
    }

    public long getCommodityId()
    {
        return commodityId;
    }

    public void setCommodityId(long commodityId)
    {
        this.commodityId = commodityId;
    }
}
