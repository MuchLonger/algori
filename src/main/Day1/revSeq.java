package Day1;

import Util.ArrUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 逆序问题：求当前数组每个数的左边大于当前数的值，组成的逆序对，如{3,1,4} 如{3,1}
 * O（N）=NlgN，空间复杂度：N
 * @Time: 2019/10/20 19:38
 */
public class revSeq {
    public static int[] arr = {7,5,6,4};
    public static List<int[]> arrList=new ArrayList();

    public static void revSeq(int[] arr) {
        if (arr == null || arr.length < 2)
            return;
        mergeSort(arr, 0, arr.length - 1);
    }

    /**
     * 必须先归并排序，因为必须有序才可以进行 merge的乘法操作
     */
    private static void mergeSort(int[] arr, int L, int R) {
        if (L == R)
            return;
        int mid = L+((R-L)>>1);
        mergeSort(arr, L, mid);
        mergeSort(arr, mid + 1, R);
        merge(arr, L, mid, R);
    }

    private static void merge(int[] arr, int L, int M, int R) {
        int[] copy = new int[R-L+1];
        int i = 0;
        int p1 = L;
        int p2 = M + 1;

        while (p1 <=M && p2 <= R) {
            if(arr[p2]<arr[p1]){
                for (int o = p1; o < M+1; o++) {
                    System.out.println(arr[p1]+","+arr[p2]);
                }
            }
            copy[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <=M)
            copy[i++] = arr[p1++];
        while (p2 <= R)
            copy[i++] = arr[p2++];
        for (int j = 0; j < copy.length; j++) {
            arr[L + j] = copy[j];
        }
    }

    public static void main(String[] args) {
        ArrUtil.show(arr);
        revSeq(arr);
        ArrUtil.show(arr);
        for(int[] a : arrList){
            ArrUtil.show(a);
        }
    }
}
