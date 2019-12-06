package leecode.firstPart;

import java.util.HashSet;
import java.util.Set;

/**
 * @description: 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 * <p>
 * 示例 1：
 * <p>
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 * @Time: 2019/11/27 18:26
 */
public class _5_LongestPalindrome {
    public static String longestPalindrome(String s) {
        int b = 0, e = s.length() - 1, l = s.length();
        StringBuilder sb = new StringBuilder("");
        Set<Character> set = new HashSet<>();
        while (b < l || e >= l) {
            if (s.charAt(b) == s.charAt(b + 1)) {

                set.add(s.charAt(b));
            } else {
                sb.append(s.charAt(b));
            }
        }

        return "";
    }

    public static boolean isReverse(String s1, String s2) {
        if(s1.length()==0||s2.length()==0||s1==null||s2==null)
            return false;
        if (s1.length() != s2.length()) {
            return false;
        }
        int i = 0, l = s1.length();
        while(s1.charAt(i++)!=s2.charAt(--l))
            return false;
        return true;


    }
    public static void main(String[] args){
        System.out.println(isReverse("abb", "bba"));
    }
}
