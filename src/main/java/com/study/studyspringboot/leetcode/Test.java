package com.study.studyspringboot.leetcode;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 说明：
 *
 * @author xiaoyuqin
 * @date 2020/8/17 11:32
 */
@Data
public class Test {

    private String aa;

    public static void main(String[] args) {
        // 1534236469
        // System.out.println(isPalindrome(1534236469));

        System.out.println(reverse4(12342));
        // [2,7,11,15]
//        int[] nums = new int[]{3, 2, 4};
//        int target = 6;
//
//        int[] result = twoSum3(nums, target);
//
//        System.out.println(result[0] + "," + result[1]);

    }

    /**
     * 最长公共前缀-开始
     */
    public static String longestCommonPrefix() {

        return "";
    }

    // 最长公共前缀-结束

    /**
     * 回文数开始
     *
     * @param x
     * @return
     */

    public static boolean isPalindrome(int x) {
        // .x<0
        if (x < 0) {
            return false;
        }

        // .x=>0
        int y = x;
        int reverse = 0;
        while (y > 0) {
            reverse = reverse * 10 + y % 10;
            y = y / 10;
        }

        return reverse == x;
    }

    /**
     * 整数反转开始
     *
     * @param x
     * @return
     */
    public static int reverse4(int x) {

        long num = 0;
        while (x != 0) {
            num = 10 * num + x % 10;
            x = x / 10;
        }
        return num > Integer.MAX_VALUE || num < Integer.MIN_VALUE ? 0 : (int) num;
    }

    public static int reverse3(int x) {
        int ans = 0;
        while (x != 0) {
            if ((ans * 10) / 10 != ans) {
                ans = 0;
                break;
            }
            ans = ans * 10 + x % 10;
            x = x / 10;
        }
        return ans;
    }

    public static int reverse2(int x) {
        //用long类型判断溢出
        long num = 0;

        // x=123
        while (x != 0) {
            num = 10 * num + x % 10;
            x = x / 10;
        }

        //超了最大值低于最小值就返回0
        return num > Integer.MAX_VALUE || num < Integer.MIN_VALUE ? 0 : (int) num;
    }

    public static int reverse(int x) {

        String oldNum;

        // . x=正整数
        if (x > 0) {
            oldNum = String.valueOf(x);
            char[] array = oldNum.toCharArray();

            // 反转得到newNum
            StringBuilder newNum = new StringBuilder();
            for (int i = array.length - 1; i >= 0; i--) {
                newNum.append(array[i]);
            }

            try {
                return Integer.parseInt(newNum.toString());
            } catch (NumberFormatException ignore) {
            }

            return 0;
        }

        // . x=负数
        if (x < 0) {
            oldNum = String.valueOf(x);
            char[] array = oldNum.toCharArray();

            // 反转得到newNum
            StringBuilder newNum = new StringBuilder("-");
            for (int i = array.length - 1; i > 0; i--) {
                newNum.append(array[i]);
            }

            try {
                return Integer.parseInt(newNum.toString());
            } catch (NumberFormatException ignore) {
            }

            return 0;
        }

        // . x=0
        return 0;
    }

    /**
     * 两数之和开始
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum3(int[] nums, int target) {

//        Map<Integer, Integer> map = new HashMap<>((int) (nums.length / 0.75 + 1));
//        for (int i = 0; i < nums.length; i++) {
//            int num = target - nums[i];
//            if (map.containsKey(num)) {
//                return new int[]{map.get(num), i};
//            }
//
//            map.put(nums[i], i);
//        }
//
//        return new IllegalArgumentException("no tow sum solution");

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(nums[i], i);
        }

        throw new IllegalArgumentException("No two sum solution");
    }

    public static int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement) && map.get(complement) != i) {
                return new int[]{i, map.get(complement)};
            }
        }

        throw new IllegalArgumentException("No two sum solution");
    }

    public static int[] twoSum(int[] nums, int target) {

        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if ((nums[i] + nums[j]) == target) {
                    return new int[]{i, j};
                }
            }
        }

        return new int[]{0, 0};
    }

}

