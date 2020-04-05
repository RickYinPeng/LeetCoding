package com.rickyin.coding.list;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

public class coding_460 {
    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(2,1);
        lruCache.put(2,2);
    }
}

class LRUCache {
    LinkedList<Integer> cachelist = new LinkedList<>();
    Map<Integer, Integer> valueMap;
    int capacity;

    public LRUCache(int capacity) {
        this.valueMap = new HashMap<>(capacity);
        this.capacity = capacity;
    }

    public int get(int key) {
        /**
         * 去链表中寻找,如果存在,则将起放到链表首部,返回其值,未找到返回-1
         */
        int value = -1;
        if(cachelist.contains(key)){
            value = valueMap.get(key);
            cachelist.remove(Integer.valueOf(key));
            cachelist.addFirst(key);
        }
        return value;
    }

    public void put(int key, int value) {
        /**
         * 如果此时缓存未满，则将此结点直接插入到链表的头部；
         * 如果此时缓存已满，则链表尾结点删除，将新的数据结点插入链表的头部。
         */
        if (valueMap.size() < capacity) {
            if(!cachelist.contains(key)){
                cachelist.addFirst(key);
            }else {
                cachelist.remove(Integer.valueOf(key));
                cachelist.addFirst(key);
            }
            valueMap.put(key, value);
        } else {
            if(!cachelist.contains(key)){
                Integer last = cachelist.getLast();
                cachelist.removeLast();
                valueMap.remove(last);
                cachelist.addFirst(key);
                valueMap.put(key, value);
            }else {
                cachelist.remove(Integer.valueOf(key));
                cachelist.addFirst(key);
                valueMap.put(key, value);
            }
        }
        return;
    }
}