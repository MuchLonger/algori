import Util.ArrUtil;
import Util.MathUitl;
import org.junit.Test;

/**
 * @description:
 * @Time: 2019/10/18 20:38
 */
public class algor {

    int[] arr = {72, 57, 42, 65, 57, 23, 57, 36, 83};

    /**
     * 有一个数组，当前传入一个数，小于等于这数的放左边，大于的放右边
     * 空间复杂度为 0(1)，时间复杂度为 O（n）
     * 基本原理：使用一个hand作为开头，每匹配到一个小于等于的数先++hand 再交换，
     */
    public void changeArrByInput(int mid) {
        int hand = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] <= mid) {
                MathUitl.swap(++hand, i, arr);
            }
        }
    }

    /**
     * 荷兰国旗问题
     * space 0(1) TIME O(N)
     * 设置一个hand，结尾tail。小于hand++并交换，等于 不操作， 大于 tail-- 交换。cur快撞上tail时停止
     */
    public void partition(int L, int R, int mid) {
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
    }

    @Test
    public void m1() {
        partition(0,arr.length-1,57);
        ArrUtil.show(arr);
    }

    @Test
    public void m2(){
        int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 },
                { 13, 14, 15, 16 } };
        System.out.println(matrix[0][1]+","+matrix[0][2]);
        System.out.println(matrix.length-1);
        System.out.println(matrix[0].length-1);
    }
}
