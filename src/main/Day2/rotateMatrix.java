package Day2;

import Util.ArrUtil;

/**
 * @description: 旋转一个正方形数组90度，要想更好的理解这个，可以先理解“旋转输出整个二维数组”
 * 1 2 3         7 4 1
 * 4 5 6  输出： 8 5 2
 * 7 8 9         9 6 3
 * @Time: 2019/10/27 14:50
 */
public class rotateMatrix {
    public static void rotateMatrix(int[][] matrix) {
        int LR = 0;
        int LC = 0;
        int RR = matrix.length - 1;
        int RC = matrix[0].length - 1;
//        只有在还能缩圈的时候才执行旋转
        while (LR <= RR && LC <= RC) {
            rotateEdge(matrix, LR++, LC++, RR--, RC--); //记得沿中心缩短
        }
    }

    /**
     * 旋转的原理：
     * 1   2   3   4        首先：先从外围一圈开始，之后“再通过左上角的点往右下角++，右下角的点往左上角--”，来达到旋转整个数组的效果
     * 5   6   7   8  原理：之后：1,4,16,13（旋转后的位置）交换位置（13->1, 16->13, 4->16, 1->4），再之后
     * 9  10  11  12，      2,8,15,9（同样的旋转后的位置）再交换位置，
     * 13 14  15  16
     * 之后如果还有，也是按照这样的比例（偏移量）交换。
     * <p>
     * 最简单的方法：套入法，如上15->9 直接就是 [RR][RC-i]（找到15的位置）， [RR-i][LC]（9的位置），直接交换就好
     *
     * @param matrix 二维数组
     * @param LR     第几个一维数组
     * @param LC     一维数组的列  （LR,LC表示的是左上角的点）
     * @param RR     第RC个一维数组
     * @param RC     RC数组的第RC列 （RR,RC表示的是右上角的点）
     */
    public static void rotateEdge(int[][] matrix, int LR, int LC, int RR, int RC) {
//        数组共有多少个数需要旋转：如上就是3-0共3个数（1,2,3）需要旋转
        int times = RC - LC;
//        仅仅用作交换时使用
        int temp = 0;
        for (int i = 0; i < times; i++) { //遍历times次，以下假设i为1！！！
            temp = matrix[LR][LC + i]; //先保留刚开始（如上数组就是2）的值，注意偏移量。
            //注意这个i的值就是偏移量！。并且因为temp保留的值是左上角，所以要先覆盖掉左上角（即1）
            matrix[LR][LC + i] = matrix[RR - i][LC];  //套入法：当前是将9（RR-i,LC) 换到2（LR,LC+i）的位置
            matrix[RR - i][LC] = matrix[RR][RC - i];  // 将15放到9的位置
            matrix[RR][RC - i] = matrix[LR + i][RC];  // 将8放到15的位置
            matrix[LR + i][RC] = temp;  //将保存的temp：2 放入到8的位置
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12},
                {13, 14, 15, 16}};
        ArrUtil.showTwo(matrix);
        rotateMatrix(matrix);
        System.out.println("=========");
        ArrUtil.showTwo(matrix);

    }
}
