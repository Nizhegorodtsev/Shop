package com.shopping.manager;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.shopping.data.AbstractStorable;
import com.shopping.entity.Price;

/**
 * Класс содержит текущие цены на
 * 
 * @author anizhegorodtsev
 *
 */
public class PriceManager extends AbstractStorable
{
    /**
     * Первый ключ - id магазина. Второй ключ - id товара. Значение - цена товара в магазине
     */
    private HashMap<Long, HashMap<Long, Price>> priceByShop;

    /**
     * Первый ключ - id товара. Второй ключ - id магазина. Значение - цена товара в магазине
     */
    // private HashMap<Long, HashMap<Long, Price>> priceByCommodity;

    /**
     * По этому ключу хранится массив цен
     */
    private static String                       PRICE_ARRAY = "priceArray";

    /**
     * Получить цену товара в магазине
     * 
     * @param shopId - магазин
     * @param commodityId - товар
     * @return - цена товара
     */
    public Price getPrice(long shopId, long commodityId)
    {
        return priceByShop.get(priceByShop).get(commodityId);
    }

    public double getPriceHistory(long commodityId, long shopId)
    {
        HashMap<Long, Price> map = priceByShop.get(shopId);
        if (map == null)
            return 0;
        Price price = map.get(commodityId);
        if (price == null)
            return 0;
        return price.getPrice();
    }

    @Override
    public JSONObject store() throws JSONException
    {
        JSONObject state = new JSONObject();
        JSONArray pricaArray = new JSONArray();

        for (Map.Entry<Long, HashMap<Long, Price>> priceMapEntry : priceByShop.entrySet())
            for (Map.Entry<Long, Price> entry : priceMapEntry.getValue().entrySet())
                pricaArray.put(entry.getValue().store());

        state.put(PRICE_ARRAY, pricaArray);
        return state;
    }

    @Override
    public void restore(JSONObject state) throws JSONException
    {
        JSONArray priceArray = state.getJSONArray(PRICE_ARRAY);
        for (int i = 0; i < priceArray.length(); i++)
        {
            JSONObject jsonPrice = priceArray.getJSONObject(i);
            Price price = new Price();
            price.restore(jsonPrice);
            long shopId = price.getShopId();
            long commodityId = price.getCommodityId();
            if (!priceByShop.containsKey(shopId))
                priceByShop.put(shopId, new HashMap<Long, Price>());
            priceByShop.get(shopId).put(commodityId, price);
            //
            // if (!priceByCommodity.containsKey(commodityId))
            // priceByCommodity.put(commodityId, new HashMap<Long, Price>());
            // priceByCommodity.get(commodityId).put(shopId, price);
        }
    }
}
