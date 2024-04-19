package com.shark.example.data.gson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;

public class GsonExample {
    public static void main(String argv[]) {
        HashMap<String, String> map = new HashMap<>();
        map.put("abc", "def");
        Gson gson = new Gson();
        String json = gson.toJson(map);
        System.out.println("json: " + json);
        HashMap<String, String> hashMap2 = gson.fromJson(json, new TypeToken<HashMap<String, String> >(){}.getType());
        System.out.println("value: " + hashMap2.get("abc"));
    }
}
