package Day1;

/**
 * @description: 小和问题：求当前数组每个数的左边小于当前数的值，如{1,3,4} 1:没有 ，3: 1，  4: 1、3，小和就是（1+1+3）
 * O（N）=NlgN，空间复杂度：N
 * @Time: 2019/10/20 19:38
 */
public class SmallSum {
    public static int[] arr = {1, 7, 3, 4, 5};

    public static int smallSum(int[] arr) {
        if (arr == null || arr.length < 2)
            return 0;
        return mergeSort(arr, 0, arr.length - 1);
    }

    /**
     * 必须先归并排序，因为必须有序才可以进行 merge的乘法操作
     */
    private static int mergeSort(int[] arr, int L, int R) {
        if (L == R)
            return 0;
        int mid = L + ((R - L) >> 1);
//        （子的左边数组的小和{1,7,3}）加上（子的右边数组的小和{4,5}）再加上（当前子左数组{1,7,3}和子右数组{4,5}的小和），分到最后的时候就靠merge，之后靠左+右+左右
        return mergeSort(arr, L, mid) + mergeSort(arr, mid + 1, R) + merge(arr, L, mid, R);
    }

    private static int merge(int[] arr, int L, int M, int R) {
        int[] copy = new int[R - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = M + 1;
//        小和，用于累加
        int res = 0;

        while (p1 <= M && p2 <= R) {
//            这段话可能难理解：比如分成的两个数组是 A{1,3,5} & B{4,6，7}。前提：因为A和B肯定是有序的（归并），
//            如果数组B的第一个(p2)数（4） 大于 A的第一（p1)个数（1） 那B后面的所有数字(6,7)肯定都是大于A的第p1个数的，B中共有R-P2+1个数字，所以是arr[p1]*(R-p2+1)
//            上面是将求小和（从当前数左边开始，目标数小于当前数，就添加目标数）的意思转变成（当前数右边开始，共有n个大于当前数的，添加n个当前数）。意思是一样的
            res += arr[p1] < arr[p2] ? arr[p1] * (R - p2 + 1) : 0;
            copy[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }

        while (p1 <= M)
            copy[i++] = arr[p1++];
        while (p2 <= R)
            copy[i++] = arr[p2++];
        for (i = 0; i < copy.length; i++) {
            arr[L + i] = copy[i];
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(smallSum(arr));
    }
}
