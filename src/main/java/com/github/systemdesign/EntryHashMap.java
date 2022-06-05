package com.github.systemdesign;

import java.util.Objects;

public class EntryHashMap<K,V> implements Map<K,V>{

    class Entry<K,V> {
        final K key;
        V value;
        Entry<K,V> next;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }
    private final int MAX_LEN = 100000;
    private Entry<K,V>[] map;

    public EntryHashMap() {
        map = new Entry[MAX_LEN];
    }

    @Override
    public void put(K key, V value) {
        int hash = key.hashCode();
        int index = hash % MAX_LEN;
        Entry<K, V> bucket = map[index];
        if(Objects.isNull(bucket)) {
            map[index] = new Entry<>(key, value);
        } else {
            while(bucket.next != null) {
                if(bucket.key.equals(key)) {
                    bucket.value = value;
                    return;
                }
                bucket = bucket.next;
            }
            if(bucket.key.equals(key)) {
                bucket.value = value;
            } else {
                bucket.next = new Entry<>(key, value);
            }
        }
    }

    @Override
    public V get(K key) {
        int hash = key.hashCode();
        int index = hash % MAX_LEN;
        Entry<K, V> bucket = map[index];
        while(Objects.nonNull(bucket)) {
            if(bucket.key.equals(key))
                return bucket.value;
            bucket = bucket.next;
        }
        return null;
    }

    @Override
    public void remove(K key) {
        int hash = key.hashCode();
        int index = hash % MAX_LEN;
        Entry<K, V> bucket = map[index];
        Entry<K, V> prev = null;
        while(Objects.nonNull(bucket)) {
            if(bucket.key.equals(key)) {
                // Remove element
                if(prev == null)
                    map[index] = bucket.next;
                else
                    prev.next = bucket.next;
            }
            prev = bucket;
            bucket = bucket.next;
        }

    }
}
