package com.study.studyspringboot.leetcode;

import lombok.Data;

import java.util.*;

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
        //int[] nums = {3, 1, 2, 4, 0, 9, 35, 21, 4, 2, 5, 10};
        int[] nums = {-1, -2, -3, -4};
        // -4 -3 -2 -1
        int sum = maximumProduct3(nums);
        System.out.println(sum);
    }

    public static int maximumProduct3(int[] nums) {
        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE, max3 = Integer.MIN_VALUE;

        for (int x : nums) {
            if (x < min1) {
                min2 = min1;
                min1 = x;
            } else if (x < min2) {
                min2 = x;
            }

            if (x > max1) {
                max3 = max2;
                max2 = max1;
                max1 = x;
            } else if (x > max2) {
                max3 = max2;
                max2 = x;
            } else if (x > max3) {
                max3 = x;
            }
        }

        return Math.max(min1 * min2 * max1, max2 * max3 * max1);
    }

    public static int maximumProduct(int[] nums) {

        Arrays.sort(nums);

        int len = nums.length;

        int max = Math.max(nums[0] * nums[1], nums[len - 2] * nums[len - 3]);

        return max * nums[len - 1];
    }

    // 试一下冒泡排序
    private static void sort(int nums[]) {
        int temp;
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = 0; j < nums.length - 1 - i; j++) {
                if (nums[j] > nums[j + 1]) {
                    temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                }
            }
        }
    }

    // 青蛙跳台阶
    public static int numWays(int n) {

        if (n == 0 || n == 1) {
            return 1;
        }

        int a = 1;
        int b = 1;
        int sum = 0;
        for (int i = 2; i <= n; i++) {
            sum = (a + b) % 1000000007;
            a = b;
            b = sum;
        }
        return sum;
    }

    // 斐波那契数列
    public static int fib(int n) {

        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }

        int a = 0;
        int b = 1;
        int sum = 0;
        for (int i = 2; i <= n; i++) {
            sum = (a + b) % 1000000007;
            a = b;
            b = sum;
        }

        return sum;
    }

    /**
     * 合并两个有序列表开始
     */
    //     Definition for singly-linked list.
    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        if (l1 == null) {
            return l2;
        }

        if (l2 == null) {
            return l1;
        }

        if (l1.val <= l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }

    /**
     * 有效的括号开始
     */
    public static boolean isValid2(String s) {

        if (s.length() % 2 == 0) {

            Stack<Character> stack = new Stack<>();

            for (int i = 0; i < s.length(); i++) {

                char c = s.charAt(i);

                if (c == '(' || c == '[' || c == '{') {
                    stack.push(c);
                } else if (c == ')') {
                    if (!stack.isEmpty() && stack.peek() == '(') {
                        stack.pop();
                    } else {
                        return false;
                    }
                } else if (c == '}') {
                    if (!stack.isEmpty() && stack.peek() == '{') {
                        stack.pop();
                    } else {
                        return false;
                    }
                } else {
                    if (!stack.isEmpty() && stack.peek() == '[') {
                        stack.pop();
                    } else {
                        return false;
                    }
                }
            }

            return stack.isEmpty();
        }

        return false;
    }

    public static boolean isValid(String s) {

        // 长度为奇数，返回false
        if (s.length() % 2 != 0) {
            return false;
        }

        Map<Character, Character> pairs = new HashMap<Character, Character>((int) (s.length() / 0.75 + 1)) {{
            put(')', '(');
            put(']', '[');
            put('}', '{');
        }};

        // 栈
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (pairs.containsKey(ch)) {

                if (stack.isEmpty() || !stack.peek().equals(pairs.get(ch))) {
                    return false;
                }

                stack.pop();
            } else {
                stack.push(ch);
            }
        }

        return stack.isEmpty();
    }

    /**
     * 最长公共前缀-开始
     */
    public static String longestCommonPrefix5(String[] strs) {

        if (strs == null || strs.length == 0) {
            return "";
        }

        int len = strs[0].length();

        for (int i = 0; i < len; i++) {
            char ch = strs[0].charAt(i);

            for (int j = 1; j < strs.length; j++) {
                if (i == strs[j].length() || ch != strs[j].charAt(i)) {
                    return strs[0].substring(0, i);
                }
            }
        }

        return strs[0];
    }


    public static String longestCommonPrefix4(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        int length = strs[0].length();
        int count = strs.length;

        for (int i = 0; i < length; i++) {

            char c = strs[0].charAt(i);

            for (int j = 1; j < count; j++) {
                if (i == strs[j].length() || strs[j].charAt(i) != c) {
                    return strs[0].substring(0, i);
                }
            }
        }

        return strs[0];
    }

    public static String longestCommonPrefix3(String[] strs) {

        StringBuilder commonPrefix = new StringBuilder("");

        if (strs.length <= 0) {
            return commonPrefix.toString();
        }

        // 最短字符串的长度
        int minLength = strs[0].length();
        for (int i = 1; i < strs.length; i++) {
            if (strs[i].length() < minLength) {
                minLength = strs[i].length();
            }
        }

        List<char[]> strList = new ArrayList<>();
        for (String str : strs) {
            strList.add(str.toCharArray());
        }

        for (int index = 0; index < minLength; index++) {

            char ch = strList.get(0)[index];

            for (int i = 1; i < strList.size(); i++) {
                if (ch != strList.get(i)[index]) {
                    return commonPrefix.toString();
                }
            }

            commonPrefix.append(ch);
        }

        return commonPrefix.toString();
    }

    public static String longestCommonPrefix2(String[] strs) {

        StringBuilder commonPrefix = new StringBuilder("");

        if (strs.length <= 0) {
            return commonPrefix.toString();
        }

        // 最短字符串的长度
        int minLength = strs[0].length();
        for (int i = 1; i < strs.length; i++) {
            if (strs[i].length() < minLength) {
                minLength = strs[i].length();
            }
        }

        for (int i = 0; i < minLength; i++) {

            String sub0 = strs[0].substring(i, i + 1);

            for (int j = 1; j < strs.length; j++) {

                String sub = strs[j].substring(i, i + 1);
                if (!sub.equals(sub0)) {
                    return commonPrefix.toString();
                }
            }

            commonPrefix.append(strs[0], i, i + 1);
        }

        return commonPrefix.toString();
    }

    public static String longestCommonPrefix(String[] strs) {

        String commonPrefix = "";

        if (strs.length <= 0) {
            return commonPrefix;
        }

        // 最短字符串的长度
        int minLength = strs[0].length();
        for (int i = 1; i < strs.length; i++) {
            if (strs[i].length() < minLength) {
                minLength = strs[i].length();
            }
        }

        Set<String> set = new HashSet<>();
        for (int i = 1; i <= minLength; i++) {

            for (String str : strs) {
                set.add(str.substring(0, i));
            }

            if (set.size() != i) {
                return commonPrefix;
            }

            commonPrefix = strs[0].substring(0, i);
        }

        return commonPrefix;
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

