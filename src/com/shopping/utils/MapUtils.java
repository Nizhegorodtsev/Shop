package com.shopping.utils;

import java.util.ArrayList;
import java.util.Map;
import java.util.SortedMap;

public class MapUtils<T1, T2>
{
    /**
     * Возвращает упорядоченный по ключу список элементов мапы
     * 
     * @param map
     * @return
     */
    public ArrayList<T2> mapToSortedList(SortedMap<T1, T2> map)
    {
        ArrayList<T2> objects = new ArrayList<T2>(map.size());
        for (Map.Entry<T1, T2> e : map.entrySet())
            objects.add(e.getValue());
        return objects;
    }

    /**
     * Возвращает мапу, которая содержит элементы с ключами, начинающимися с указанного префикса
     * 
     * @param map - контейнер для выборки
     * @param prefix - префикс для отбора элементов
     * @return - контейнер с элементами, начинающимися с указанного префикса
     */
    public SortedMap<String, T2> prefixSubMap(SortedMap<String, T2> map, String prefix)
    {
        char nextLetter = (char) (prefix.charAt(prefix.length() - 1) + 1);
        String end = prefix.substring(0, prefix.length() - 1) + nextLetter;
        SortedMap<String, T2> subMap = map.subMap(prefix.toLowerCase(), end.toLowerCase());
        return subMap;
    }
}
