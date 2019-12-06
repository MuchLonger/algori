package leecode.firstPart;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @description: 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * <p>
 * 示例 2:
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 * @Time: 2019/11/26 18:10
 */
public class _3_diffString {
    /**
     * 使用滑动窗口的方式，
     * 表现形式：从i开始，没有遇到重复的就加入数组，并使用偏移量(j-i)更新ans（总长度）同时j++，
     *                    如果遇到重复的j不变，i++，并将i索引对应的值删除（为的是不影响后面的值，因为它已经被划过去了（即当前已经从i++开始，如果不删就会影响后面的判断））
     *           终止条件是i>=s.length（头滑到底了，一边是因为后面(j)有一排重复的）或j>=s.length（结尾到底了，表示后面(i)没有一排重复的）
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {
        int length = s.length();
        Set<Character> set = new HashSet<>();
        int ans = 0, i = 0, j = 0;
        while (i < length && j < length) {
            if (!set.contains(s.charAt(j))) {
                set.add(s.charAt(j++));
                ans = Math.max(ans, j - i);  // ans是总的最长值，j-i是当前最长值
            } else {
                set.remove(s.charAt(i++));
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Map<Long, String> map = new HashMap<>();
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
    }
}
