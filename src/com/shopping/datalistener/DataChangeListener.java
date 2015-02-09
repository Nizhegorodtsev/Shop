package com.shopping.datalistener;

import org.json.JSONObject;

public interface DataChangeListener
{
    /**
     * Пользователь добавил данные. Необходима синхронизация DataStorage
     * 
     * @param data
     */
    public void add(JSONObject data);

    /**
     * Пользователь удалил данные. Необходима синхронизация DataStorage
     * 
     * @param data
     */
    public void remove(JSONObject data);

    /**
     * Пользователь изменил данные. Необходима синхронизация DataStorage
     * 
     * @param data
     */
    public void change(JSONObject data);

    /**
     * Предполагается оповещать слушателя, елси были подгруженны новые данные
     */
    public void updateData();
}
