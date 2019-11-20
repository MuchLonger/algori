package learning.Day2;

/**
 * @description: 给定一个二维数组，按Z字型打印、
 * 如：
 * 1  2  3  4
 * 5  6  7  8    打印：1 2 5 9 6 3 4 7 10 11 8 12，其实就是打印对角线以及正序打印和逆序打印
 * 9 10 11 12
 * @Time: 2019/10/27 22:47
 */
public class ZigZagPrintMatrix {

    /**
     * 按Z字型打印二维数组（对角线正逆序打印）
     * <p>
     * 基本思想： 给(0,0)点A,B：
     * A向右移动，如果到底(matrix[0].length-1)就向下移动，如果到拐角 底(matrix.length-1)就中断，
     * B向下移动，如果到底（matrix.length-1)就像右移动，如果到拐角 底（matrix[0].length-1）就中断
     * 两点同时移动，每移动一次就会生成新的两个坐标，分别按正逆序打印这两点的对角线值
     *
     * @param matrix
     */
    public static void printMatrixZigZag(int[][] matrix) {
        int LR = 0;
        int LC = 0;  //点A，向右移动
        int RR = 0;
        int RC = 0;  //点B，向下移动
        int endC = matrix[0].length - 1; //右边的底（最大列索引）
        int endR = matrix.length - 1;  //下面的底（最大行索引）
        boolean seq = false;  //通过它来进行正序逆序打印对角线
        while (LR < endR + 1) { // 注意是小于endR+1，下面的底（最大行索引，一个直角三角形），因为走完这一个直角三角形就表示点A和点B同时走完了（A的长加宽=B的宽加长）
            printLevel(matrix, LR, LC, RR, RC, seq);
//            A点开始  注意执行顺序："会改变的" LC放在下面，因为如果 LC放在LR上面 那在正好到拐角的时候LC会+1下面LR再判断就会出错，从而导致数字出错
            LR = LC == endC ? LR + 1 : LR;  //判断是否向下走，如果LC走到底了LR就++，否则就是没到底 LR不变，
            LC = LC == endC ? LC : LC + 1;  //判断是否向右走，LC没走到底就向右走（LC++)，到底就不变。 这样就能保证只有LR,LC只有一个点能动
//            A点结束
//            B点开始 同样，会改变的RR放下面，防止拐点的时候RR+1导致RC不动
            RC = RR == endR ? RC+1 : RC;  // RR到下底就向右走，并且RR不变RC++。RR和RC的条件需要一直
            RR = RR == endR ? RR : RR+1;  // RR还没到下底就向下走，否则RC不变RR++。
//            B点结束
            seq=!seq;
        }
    }

    /**
     *  根据两点和顺序，打印对角线上的点
     * @param matrix 二维数组
     * @param LR 第几个数组
     * @param LC 数组第几个  A点，向右下走
     * @param RR  第几个数组
     * @param RC  数组第几个 B点，像下右走
     * @param seq 正逆序
     */
    public static void printLevel(int[][] matrix, int LR, int LC, int RR, int RC, boolean seq) {
        if(seq){
            while (LR!=RR+1){  //注意是RR+1，因为最后一次也会++
//                *   *   /1    打印的数肯定是/上的（因为从右到下），所以直接LR++（一行行向下打印）,LC--（一列列倒着打印）。
//                *   /2  *     当前输出顺序：/1 -> /2 -> /3
//                /3  *   *
                System.out.print(matrix[LR++][LC--]+" ");
            }
        }else{
            while(RC!=LC+1){  //当前顺序 /3 -> /2 -> /1
                System.out.print(matrix[RR--][RC++]+" ");
            }
        }
    }
    public static void main(String[] args) {
        int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 } };
        printMatrixZigZag(matrix);

    }
}
