package learning.Day1;

import Util.ArrUtil;
import Util.MathUitl;

/**
 * @description: 快速（经典）排序与随机快排
 * 快速（经典）排序：时间复杂度 0(NlgN)，空间复杂度：
 * 随机快排：时间复杂度 长期期望是：0(NlgN)，空间复杂度：O(lgN) （这里的空间用在了存放p[]上，长期期望是O(lgN），最差情况是O（N））
 * @Time: 2019/10/21 0:06
 */
public class quickSort {
    static int[] arr = {72, 57, 42, 65, 57, 23, 57, 36, 83};

    /**
     * 改良荷兰国旗算法：
     * 荷兰国旗是传进一个值，然后左边是小于这个值（小于区域），中间等于这个值（等于区域），右边大于这个值（大于区域）
     * 改良之后是不用传入值，直接改为R（数组的最后一个值），并且指针由cur改成了L，这样所有 有关cur指针 都在L上（++操作）进行，
     * 因为最后一个值arr[R]直接默认为要“被划分的值”，所以它的位置是没有被修改的，所以在最后就需要将其设置到“等于区域”上，
     * 等于区域在tail的位置（经过--之后）。即MathUitl.swap(tail,R,arr);
     * @return {hand,tail}等于区域
     */
    public static int[] partition(int[] arr,int L, int R) {
        int hand = L - 1;
        int tail = R;  //R位置上的值就默认是中间值
        while (L < tail) {
            if (arr[L] < arr[R]) { //小于arr[R]的
                MathUitl.swap(++hand, L++, arr);
            } else if (arr[L] > arr[R]) {  //大于arr[R]的，之所以L不用++，是因为当相等的时候会自动++
                MathUitl.swap(--tail, L, arr);
            } else {
                L++;
            }
        }
        // 设置回等于区域（因为就是按照R来划分的，tail的位置就是等于区域）
        MathUitl.swap(tail,R,arr);
//        {0,hand} 小于区域， {hand,tail}等于区域，{tail,R}大于区域
        return new int[]{hand+1,tail};
    }
    public static void qutSort(int[] arr,int L, int R){
        if(L<R){
//            加上这一句话就是随机快排：即随机取一个数组内的值与最后位置R交换（使得每个数字都是概率的成为中间值）
            MathUitl.swap((int) (L+(Math.random()*(R-L+1))),R,arr);
//            划分好一个中间为R值的区域，子再继续划分中间为R值的区域...
            int[] p=partition(arr,L,R);
            qutSort(arr,L,p[0]-1);
            qutSort(arr,p[1]+1,R);
        }
    }

    public static void quickSort(int[] arr){
        if (arr == null || arr.length < 2) {
            return;
        }
        qutSort(arr, 0, arr.length - 1);
    }

    public static void main(String[] args){
        quickSort(arr);
        ArrUtil.show(arr);
    }
}
