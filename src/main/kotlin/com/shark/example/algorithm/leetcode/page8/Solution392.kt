package com.shark.example.algorithm.leetcode.page8

fun main() {
    val s = "abc";
    val t = "aabbcc"
    val result = isSubsequence(s, t);
    println("result: $result")
}

fun isSubsequence(s: String, t: String): Boolean {
    if(s.isEmpty()) {
        return false
    }
    if(t.isEmpty()) {
        return false
    }
    var sIndex = 0;
    var tIndex = 0;

    while (sIndex < s.length && tIndex < t.length) {
        val sChar = s[sIndex];
        val tChar = t[tIndex];

        if(sChar == tChar) {
            sIndex ++;
        }
        if(sIndex == s.length) {
            return true;
        }
        tIndex ++;
    }

    return false
}