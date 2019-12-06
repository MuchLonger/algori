package leecode.firstPart;

/**
 * @description: 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
 * <p>
 * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 * 示例 2: nums1 = [1, 2]
 * nums2 = [3, 4]
 * <p>
 * 则中位数是 (2 + 3)/2 = 2.5
 * @Time: 2019/11/27 17:45
 */
public class _4_FindMedianSortedArrays {
    //时间复杂度为(M+N)
    // 用归并法
    public static double findMedianSortedArrays(int[] left, int[] right) {
        int[] copy = new int[left.length + right.length];
        int i = 0, j = 0, a = 0;
        ;
        while (i < left.length && j < right.length) {
            copy[a++] = left[i] > right[j] ? right[j++] : left[i++];
        }
        while (i < left.length) {
            copy[a++] = left[i++];
        }
        while (j < right.length) {
            copy[a++] = right[j++];
        }
        for (int k = 0; k < copy.length; k++) {
            System.out.print(copy[k] + ",");
        }
        if (copy.length % 2 == 0) {
            int mid = copy.length / 2;
            int midNext = mid-1 ;
            double result = (double)(copy[mid] + copy[midNext]) / 2;
            return result;
        } else {
            return (double) copy[copy.length / 2];
        }
    }

    public static void main(String[] args) {
        int[] a = {};
        int[] b = {3, 4};
        System.out.println(findMedianSortedArrays(a, b));
    }
}
