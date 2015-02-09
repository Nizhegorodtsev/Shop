package com.shopping;

import java.util.ArrayList;
import java.util.Map;
import java.util.SortedMap;

public class MapUtil<T>
{
    public ArrayList<T> mapToSortedList(SortedMap<String, T> map)
    {
        ArrayList<T> objects = new ArrayList<T>(map.size());
        for (Map.Entry<String, T> e : map.entrySet())
            objects.add(e.getValue());
        return objects;
    }

    public SortedMap<String, T> prefixSubMap(SortedMap<String, T> map, String prefix)
    {
        char nextLetter = (char) (prefix.charAt(prefix.length() - 1) + 1);
        String end = prefix.substring(0, prefix.length() - 1) + nextLetter;
        SortedMap<String, T> subMap = map.subMap(prefix.toLowerCase(), end.toLowerCase());
        return subMap;
    }
}
