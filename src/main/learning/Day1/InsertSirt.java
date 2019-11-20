package learning.Day1;

import Util.ArrUtil;
import Util.MathUitl;

/**
 * @description:
 * @Time: 2019/10/20 0:33
 */
public class InsertSirt {
    static int[] arr = {72, 57, 42, 65, 57, 23, 57, 36, 83};

    /**
     * 插入排序
     * 要点，j是以i-1为起点倒着来读,j--与j比较，并且是相隔两两交换
     *
     * @param arr
     */
    public static  void insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = i - 1; j > 0 && arr[j] < arr[j - 1]; j--) {
                MathUitl.swap(j, j - 1, arr);
            }
        }
    }

    public static void main(String[] args){
        insertSort(arr);
        ArrUtil.show(arr);
    }
}
