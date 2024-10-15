package com.shark.example.algorithm.leetcode.page1.program49;

import com.google.gson.Gson;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Program49Utils {

    public static void compare(String value1, String value2) {
        value1 = value1.replace("[","").replace("]","").replace("\"", "");
        System.out.println(value1);
        value2 = value2.replace("[","").replace("]","").replace("\"", "");
        System.out.println(value2);

        String[] value1Keys = value1.split(",");
        String[] value2Keys = value2.split(",");

        Set<String> value1KeySet = new HashSet<>(Arrays.asList(value1Keys));
        Set<String> value2KeySet = new HashSet<>(Arrays.asList(value2Keys));
        System.out.println("value1KeySet: " + new Gson().toJson(value1KeySet));
        System.out.println("value2KeySet: " + new Gson().toJson(value2KeySet));
        for(String key : value1KeySet) {
            if(!value2KeySet.contains(key)) {
                System.out.println("value2 not contains " + key);
            }
        }
        for(String key : value2KeySet) {
            if(!value1KeySet.contains(key)) {
                System.out.println("value1 not contains " + key);
            }
        }
    }


    public static void main(String[] args) {
        String value1 = "[[\"mud\"],[\"kin\"],[\"tat\"],[\"jug\"],[\"dam\"],[\"tux\"],[\"bur\"],[\"ron\"],[\"ski\"],[\"cad\"],[\"rob\"],[\"gay\"],[\"joy\"],[\"yak\"],[\"mid\"],[\"set\"],[\"ken\"],[\"sin\"],[\"nip\",\"nip\"],[\"rca\"],[\"eat\"],[\"ark\",\"ark\"],[\"oaf\",\"oaf\"],[\"any\"],[\"don\"],[\"pus\",\"sup\"],[\"jay\"],[\"pug\"],[\"gad\"],[\"rub\"],[\"sue\"],[\"cob\"],[\"hug\"],[\"lox\"],[\"luz\"],[\"new\"],[\"chi\"],[\"sac\"],[\"ago\"],[\"hos\",\"ohs\"],[\"fan\"],[\"kid\"],[\"ode\"],[\"dot\",\"dot\"],[\"lab\"],[\"sob\"],[\"eon\"],[\"wad\"],[\"axe\"],[\"maj\"],[\"ton\"],[\"del\"],[\"nit\"]]";
        String value2 = "[[\"del\"],[\"eon\"],[\"dam\"],[\"tat\"],[\"gad\"],[\"luz\"],[\"wad\"],[\"kin\"],[\"eat\"],[\"cob\"],[\"ski\"],[\"bur\",\"rub\"],[\"new\"],[\"sue\"],[\"pug\"],[\"ark\",\"ark\"],[\"dot\",\"dot\"],[\"maj\"],[\"ken\"],[\"sob\"],[\"ton\"],[\"sin\"],[\"gay\"],[\"nit\"],[\"ode\"],[\"joy\"],[\"rob\"],[\"ron\"],[\"lox\"],[\"kid\"],[\"mid\"],[\"nip\",\"nip\"],[\"mud\"],[\"lab\"],[\"ago\"],[\"oaf\",\"oaf\"],[\"don\"],[\"chi\"],[\"rca\"],[\"fan\"],[\"pus\",\"sup\"],[\"jay\"],[\"hos\",\"ohs\"],[\"cad\"],[\"yak\"],[\"any\"],[\"sac\"],[\"jug\"],[\"tux\"],[\"hug\"],[\"axe\"],[\"set\"]]";
        compare(value1, value2);

    }
}
