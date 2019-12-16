package codeBook.eighth_part_arrAndMatrix;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 给定一个“无序”数组arr（既有正数又有负数又有0），再给定一个k，求arr的所有 子数组中所有元素相加为k 的 最长子数组的长度。
 * 如arr={-1,2,1,-1,1}，k=3，相加后最长子数组为{2,1,-1,1}，所以返回 数组长度 ，也就是4
 * 注意：如何引出公式：设s(i)为当前所有元素的累加和，s(j)为0-j的累加和，且j小于i。所以 s(j-i)的累加和就是 s(i)-s(j)
 * ！！！！！！！！！！！！☹，公式搞懂就能做出这一题。
 * 重点就是公式：s(j+1...i)=s(i)-s(j)。令s(j+1...i)=k，且s(i)就是累加到i的值，s(j)就是累加到j的值。因为s(i...j)是连续值，所以子数组等于k的就是它
 *
 *  s(j+1...i)=s(i)-s(j); 设s(j...i)为k，那只需要求出s(i)，s(j)即可，将s(j)保存到map内，s(i)就是sum，一切就求出来了。
 * @Time: 2019/12/16 14:38
 */
public class GetMaxLengthInCommonArr {

    // 注意子序列一定是连续的，{1,2,3,4} 234 34 123 1234这样子连续
    public static int getMaxLengthInCommonArr(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k <= 0) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>(); // map的key是0-i的sum值，value是当时的i的值
        map.put(0, -1);  // ！一定要这一步，因为sum的累加和的值就是从第0个位置开始的，如果不包含这个0，那在“k刚好等于sum”的时候(sum-k=0)，就会因为找不到为sum-k=0的值而出错。
        int sum = 0;
        int len = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            // sum-k ==> k=sum-(sum-k)，基于当前的sum值，只要map内有sum-k，那就一定能算出k值。通过（i减去map内的索引）就能得到值为k的长度，通过与之前比较取最大值即可。
            if (map.containsKey(sum - k)) {
                len = Math.max(i - map.get(sum - k), len);
            }
            if (!map.containsKey(sum)) {  // 只有不包含当前sum的时候才添加，因为这时候是最长的
                map.put(sum, i);
            }
        }
        return len;
    }
    public static void main(String[] args){
        int[] arr={-1,2,1,-1,1};
        System.out.println(getMaxLengthInCommonArr(arr, 1)+"：☹");

    }

}
