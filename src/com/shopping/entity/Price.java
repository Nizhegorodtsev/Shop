package com.shopping.entity;

import org.json.JSONException;
import org.json.JSONObject;

import com.shopping.data.AbstractStorable;

/**
 * Цена на конкретный товар в конкретном магазине
 * 
 * @author anizhegorodtsev
 *
 */
public class Price extends AbstractStorable
{

    public static String SHOP_ID      = "shopId";

    public static String COMMODITY_ID = "commodityId";

    public static String PRICE        = "price";

    public static String TIME         = "time";

    private long         shopId;

    private long         commodityId;

    private double       price;

    private long         time;

    @Override
    public JSONObject store() throws JSONException
    {
        JSONObject state = new JSONObject();
        state.put(SHOP_ID, shopId);
        state.put(COMMODITY_ID, commodityId);
        state.put(PRICE, price);
        state.put(TIME, time);
        return state;
    }

    @Override
    public void restore(JSONObject state) throws JSONException
    {
        shopId = state.getLong(SHOP_ID);
        commodityId = state.getLong(COMMODITY_ID);
        price = state.getDouble(PRICE);
        time = state.getLong(TIME);
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

    public double getPrice()
    {
        return price;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }

    public long getTime()
    {
        return time;
    }

    public void setTime(long time)
    {
        this.time = time;
    }
}
