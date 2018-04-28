package com.techm.ms.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Storage {

    private static final Map<Long, User> users = new HashMap<>();

    public static Map<Long, User> getUsers() {
        return users;
    }

//    public static final Set<String> names = new HashSet<>();
//
//    public static Set<String> getNames() {
//        return names;
//    }
}
