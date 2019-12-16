package codeBook.eighth_part_arrAndMatrix;

import java.util.Calendar;
import java.util.Date;

/**
 * @description: 给定一个“无序”“正数”数组arr，再给定一个k，求arr的所有 子数组中所有元素相加为k 的 最长子数组的长度。
 * 如arr={1,2,1,1,1}，k=3，相加后最长子数组为{1,1,1}，所以返回 数组长度 ，也就是3
 *  注意：子数组是从arr从顺序分割下去的，{121}属于子数组，{1111}不属于。
 *  注意：如果不是“正数”数组就不能使用这样的方法，因为sum-=arr[left++]，可能越来越大
 * @Time: 2019/12/16 10:25
 */
public class GetMaxLength {
    public static int getMaxLength(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k <= 0) {
            return 0;
        }
        int left = 0;
        int right = 0;
        int sum = arr[0];
        int len = 0;
        // 使用sum来记录子数组总和，
        // 如果子数组小于k，right++。sum的加上right的值
        // 如果子数组大于k，left++，重新寻找。sum的减去left的值
        // 如果子数组等于k，因为需要找最长，所以 left++，sum减去left的值，但是len的值上次遍历已经加上去了
        // 以上就可以遍历所有子序列，一种情况是前面找到第一个len，后面还有len：因为当sum>k，left会++，所有这种情况也会被计算进去
        while (right < arr.length) {
            if (sum == k) {
                len = Math.max(len, right - left + 1); // 是使用以前的len还是使用当前的len ==》取最大的
                sum -= arr[left++];
            } else if (sum < k) {
                right++;
                if (right == arr.length) {  // 注意不能超过数组下标
                    break;
                }
                sum += arr[right];
            } else if (sum > k) {
                sum -= arr[left++];
            }
        }
        return len;
    }

    public static void main(String[] args) {
        int[] arr={1,2,1,1,1};
        System.out.println(getMaxLength(arr, 3));
    }
}
