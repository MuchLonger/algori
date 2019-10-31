package Util;

/**
 * @description:
 * @Time: 2019/10/18 20:52
 */
public class MathUitl {
    /**
     * 交换数组值
     * @param left 数组左坐标
     * @param right 数组右坐标
     * @param arr 数组
     */
    public static void swap(int left,int right,int[] arr){
        int cur=arr[left];
        arr[left]=arr[right];
        arr[right]=cur;
    }


}
