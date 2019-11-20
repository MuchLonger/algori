package learning.Day1;

import Util.ArrUtil;
import Util.MathUitl;

/**
 * @description:
 * @Time: 2019/10/20 0:30
 */
public class SelectSort {
    static int[] arr = {72, 57, 42, 65, 57, 23, 57, 36, 83};

    /**
     * 选择排序
     * 重点在于 min值需要随着i一直走，并且选择的是最小值的下标
     *
     * @param arr
     */
    public static void selSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[min] > arr[j]) {
                    min = j;
                }
            }
            MathUitl.swap(min, i, arr);
        }
    }

    public static void main(String[] args){
        selSort(arr);
        ArrUtil.show(arr);
    }
}
