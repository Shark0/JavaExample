package com.shark.example.algorithm.leetcode.page1

fun main() {
    val nums = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    val result = removeElement(nums, 2);
    println(result)
    println(nums.contentToString())
}

fun removeElement(nums: IntArray, `val`: Int): Int {
    var count = 0
    for (i in nums.indices) {
        if (nums[i] == `val`) {
            count++
        } else {
            nums[i - count] = nums[i]
        }
    }
    return nums.size - count
}