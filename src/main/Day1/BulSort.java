package Day1;

import Util.ArrUtil;
import Util.MathUitl;

/**
 * @description:
 * @Time: 2019/10/20 0:32
 */
public class BulSort {
    static int[] arr = {72, 57, 42, 65, 57, 23, 57, 36, 83};

    /**
     * 冒泡排序
     * 关键点在于 最外面的循环仅作数字更迭和相隔两两位置交换
     *
     * @param arr
     */
    public static void bulSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    MathUitl.swap(j, j + 1, arr);
                }
            }
        }
    }


    public static void main(String[] args){
        bulSort(arr);
        ArrUtil.show(arr);
    }

}
