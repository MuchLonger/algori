package learning.Day1;

import Util.ArrUtil;
import Util.MathUitl;

/**
 * @description:
 * @Time: 2019/10/20 23:43
 */
public class HelFlag {
    static int[] arr = {72, 57, 42, 65, 57, 23, 57, 36, 83};

    /**
     * 荷兰国旗问题
     * space 0(1) TIME O(N)
     * 指针cur，      hand=L-1,      tail=R+1。
     * arr[cur]小于mid，：(cur和++hand交换，cur++)，
     * arr[cur]等于mid ：(cur++),
     * arr[cur]大于mid ：(cur和--tail交换，cur不变）；
     */
    public static int[] partition(int[] arr,int L, int R, int mid) {
        int hand = L - 1;
        int tail = R + 1;
        int cur = L;
        while (cur < tail) {
            if (arr[cur] < mid) {
                MathUitl.swap(++hand, cur++, arr);
            } else if (arr[cur] > mid) {
                MathUitl.swap(--tail, cur, arr);
            } else {
                cur++;
            }
        }
//        {0,hand} 小于区域， {hand,tail}等于区域，{tail,R}大于区域
        return new int[]{hand+1,tail-1};
    }
    public static void main(String[] args){
        int[] b=partition(arr,0,arr.length-1,57);
        ArrUtil.show(arr);
        ArrUtil.show(b);
    }
}
