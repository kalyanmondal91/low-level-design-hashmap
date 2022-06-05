package com.github.systemdesign;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        Map<Integer, String> map = new EntryHashMap<>();
        map.put(1, "One");
        map.put(2, "Two");
        map.put(3, "Three");
        map.put(4, "Four");
        map.put(5, "Five");

        System.out.println(map.get(4));
        System.out.println(map.get(5));
        System.out.println(map.get(6));
    }
}
