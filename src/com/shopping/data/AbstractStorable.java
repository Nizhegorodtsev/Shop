package com.shopping.data;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class AbstractStorable
{
    public static String ID   = "id";

    public static String NAME = "name";

    /**
     * Создание JSON объекта, в котором фиксируется состояние сохроняемой сущности.
     * 
     * @return - состояние объекта
     */
    public abstract JSONObject store() throws JSONException;

    /**
     * Восстановление состояния объекта, которое было сохранено в JSON
     * 
     * @param state
     *            сохраренное состояние
     * @throws JSONException
     */
    public abstract void restore(JSONObject state) throws JSONException;

    /**
     * 
     * @param state
     * @return
     * @throws JSONException
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public static AbstractStorable newInstance(JSONObject state) throws JSONException, ClassNotFoundException, InstantiationException, IllegalAccessException
    {
        Class<?> c = Class.forName(state.getString("")); // TODO доделать, если будет нужно
        Object object = c.newInstance();
        AbstractStorable instance = (AbstractStorable) object;
        instance.restore(state);
        return instance;
    }
}
