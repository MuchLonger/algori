package learning.Day1;


import Util.ArrUtil;

/**
 * @description: 归并排序
 * 时间复杂度：T(N)=2 (N/2) + O(N) --->  O(N)= Nlg(N) 空间复杂度：O（N）
 * @Time: 2019/10/20 0:33
 */
public class MergeSort {
    static int[] arr = {72, 57, 42, 65, 57, 23, 57, 36, 83};

    /**
     * 主调用函数
     *
     * @param arr 要排序的数组
     */
    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        mergeSort(arr, 0, arr.length - 1);
    }

    /**
     * 递归函数，理解难点：只需要知道“当前方法在干什么”，“当前方法下一个递归方法”在干什么，再下下个方法就会一直执行下去
     *
     * @param arr
     * @param L   左值
     * @param R   右值
     */
    private static void mergeSort(int[] arr, int L, int R) {
//        左右相等，就代表只有一个值的时候退出
        if (L == R)
            return;
        int mid = (L + R) / 2;
        mergeSort(arr, L, mid);
        mergeSort(arr, mid + 1, R);     //这两个递归就将值拨到最后再回调回来
        merge(arr, L, mid, R);        //回调回来最外层的函数肯定是左边有序，右边也有序的 再调用外排序
    }

    /**
     * 将左右两个有序数组执行外排序：（为什么递归后左右分别有序？因为从倒数第一次递归（只有一个值的时候）开始就左右分别排序。
     * 1. 2 6 7|1 3 4  如2>1 将1放入拷贝数组 右边指针p2移动到3，第一次完成。之后 2<3，将2放入拷贝数组，左边指针p1移动到7。
     * 2. 等到一边完全有序了，就将另一边指针剩下的所有值复制到拷贝数组
     *
     * @param arr 左边有序，右边有序的数组
     * @param L   左边开始值
     * @param mid 中间值（分割左边有序右边有序的值）
     * @param R   右边结束值
     */
    public static void merge(int[] arr, int L, int mid, int R) {
//        拷贝数组，用来复制数组
        int[] copy = new int[R - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = mid + 1;
//        左边索引小于中间，中间小于右边
        while (p1 <= mid && p2 <= R) {
//            这一步就执行上面说的拷贝工作（1.）
            copy[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
//        这一步是上面的（2.），并且两个while只会执行一个（详情看上面while条件）
        while (p1 <= mid)
            copy[i++] = arr[p1++];
        while (p2 <= R)
            copy[i++] = arr[p2++];
//        最后将处理后的数组根据偏移量拷贝回原数组
        for (int j = 0; j < copy.length; j++) {
            arr[L+j] = copy[j];
        }
    }

    public static void main(String[] args){
        mergeSort(arr);
        ArrUtil.show(arr);
    }
}
