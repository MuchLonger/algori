package learning.Day2;

import Util.ArrUtil;

/**
 * @description: 顺时针选择输出一个二维数组
 * 1 2 3
 * 4 5 6
 * 7 8 9         输出1 2 3 6 9 8 7 4 5
 * @Time: 2019/10/27 12:36
 */
public class PrintMatrixSpiralOrder {
    //    这是旋转输出的数组
         public static void spiralOrderPrint(int[][] martrix) {
        int LR = 0;
        int LC = 0;
        int RR = martrix.length - 1;
        int RC = martrix[0].length - 1;
        //这一步是将环往里再缩一层
        while (LR <= RR && LC <= RC) {
//            左上角沿对角线往右下（++），右下角沿对角线往左上(--)
            printEdge(martrix, LR++, LC++, RR--, RC--);
        }
    }

    /**
     * 输出一个框
     * 1 2 3
     * 4 5 6
     * 7 8 9  就输出1 2 3 6 9 8 7 4。（注意5没有被输出），
     * 具体实现是：
     *
     * 从LR,LC（1）输出到 LR,RC-1（2）（翻译就是从第一行输出到第一行的倒数第二个元素）（输出1 2）
     * 从LR,RC（3）输出到 RR-1,RC（6）（输出3 6）
     * 从RR,RC（9）输出到 RR,LC-1（8）（输出9 8）
     * 从RR,LC（7）输出到 LR-1,LC（4）。（输出7 4）终止
     *
     * @param m  要输出的数组
     * @param LR（LeftRow)      当前是二维数组的第几个数组
     * @param LC (LeftColumn）  当前是该数组的第几个元素   即现在存入：左上角的坐标（如上就是1）
     * @param RR（RightRow）    当前是第RR个数组
     * @param RC（RightColumn） 当前是RR数组的第RC个元素   即右下角的坐标（如上就是9）
     */
    public static void printEdge(int[][] m, int LR, int LC, int RR, int RC) {
//        LR和RR表示第几个一维数组，如果相等则表示同一个一维数组，直接遍历column输出
        if (LR == RR) {
            for (int i = LC; i <= RC; i++) {
                System.out.print(m[LR][i] + " ");
            }
        }
//        LC和RC表示元素所在列，如果相等则表示在同一列，则只需改变一维数组，直接遍历row输出
        else if (LC == RC) {
            for (int i = LR; i <= RR; i++) {
                System.out.print(m[i][LC] + " ");
            }
        }
//        其它的就代表有多个数构成了一个环
        else {
            int origin_LR_copy = LR;  //保留左上角的哪个一维数组
            int origin_LC_copy = LC;  //保留左上角是哪一列
            while (origin_LC_copy != RC) {
                System.out.print(m[origin_LR_copy][origin_LC_copy] + " "); //就是从 LC->RC-1 遍历，LR（一维数组）恒定
                origin_LC_copy++;  //到了最后origin_LC_copy会变成RC
            }
            while (origin_LR_copy != RR) {
                System.out.print(m[origin_LR_copy][RC] + " ");  // LR->RR-1，RC（列数）恒定
                origin_LR_copy++; //origin_LR_copy-->RR
            }
            while (origin_LC_copy != LC) {
                System.out.print(m[RR][origin_LC_copy] + " "); // RC->LC-1, RR(一维数组)恒定，RR也可以改成origin_LR_copy
                origin_LC_copy--; //origin_LC_copy-->LC
            }
            while (origin_LR_copy != LR) {
                System.out.print(m[origin_LR_copy][LC] + " "); // RR->LR，LC（列）恒定，LC也可以改成（origin_LC_copy）
                origin_LR_copy--; //origin_LR_copy-->LR
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 },
                { 13, 14, 15, 16 } };
        ArrUtil.showTwo(matrix);
        spiralOrderPrint(matrix);
    }
}
