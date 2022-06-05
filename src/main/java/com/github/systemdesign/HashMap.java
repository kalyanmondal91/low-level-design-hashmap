package com.github.systemdesign;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HashMap<K,V> implements Map<K,V>{
    private final int MAX_LEN = 100000;
    private List<Pair<K, V>>[] map;

    public HashMap() {
        map = new ArrayList[MAX_LEN];
    }

    @Override
    public void put(K key, V value) {
        int hash = key.hashCode();
        int index = hash % MAX_LEN;
        int pos = findKeyPos(key, index);
        if(pos < 0) {
            if(Objects.isNull(map[index]))
                map[index] = new ArrayList<>();
            map[index].add(new Pair<>(key, value));
        } else {
            map[index].set(pos, new Pair<>(key, value));
        }
    }

    @Override
    public V get(K key) {
        int hash = key.hashCode();
        int index = hash % MAX_LEN;
        int pos = findKeyPos(key, index);
        if(pos < 0)
            return null;
        else
            return map[index].get(pos).getValue();
    }

    @Override
    public void remove(K key) {
        int hash = key.hashCode();
        int index = hash % MAX_LEN;
        int pos = findKeyPos(key, index);
        if(pos >= 0) {
            map[index].remove(pos);
        }
    }
    private int findKeyPos(K key, int index) {
        List<Pair<K, V>> temp = map[index];
        if(Objects.isNull(temp))
            return -1;
        for(int i=0; i<temp.size(); i++) {
            if(temp.get(i).getKey().equals(key))
                return i;
        }
        return -1;
    }
}
