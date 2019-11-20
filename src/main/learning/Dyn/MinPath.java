package learning.Dyn;

/**
 * @description: 给定一个二维数组，从0,0出发走到右下角，每步只能走右边或者下边，记录走过二维数组的值，求最小路径和
 * <p>
 * ！！！！注意，千万不要使用++，因为一个类内一个常量会被多次引用！！！！
 * @Time: 2019/11/16 21:33
 */
public class MinPath {
    public static int minPath1(int[][] matrix) {
        return processReverseWithRecur(matrix, matrix.length - 1, matrix[0].length - 1);
    }

    public static int minPath2(int[][] matrix) {
        return processWithRecur(matrix, 0, 0);
    }

    public static int minPath3(int[][] matrix) {
        return processWithDyn(matrix);
    }

    /**
     * ！！！！注意，千万不要使用++，因为一个类内一个常量会被多次引用！！！！
     * <p>
     * 倒着来递归，即从右下角开始到左上角0,0（有道云动态规划写的）
     * <p>
     * 就是每回退一步都判断向上是最优解还是向左是最优解。（因为是倒着来）
     *
     * @param matrix 二维数组
     * @param i      第几行
     * @param j      第几列
     * @return
     */
    private static int processReverseWithRecur(int[][] matrix, int i, int j) {
        int result = matrix[i][j]; //记录当前二维数组的值
        if (i == 0 && j == 0)  //到达原点
            return result;
        if (i == 0 && j != 0) //到达边界：即到达第一行，但是还能继续向左走。
            return result + processReverseWithRecur(matrix, i, j - 1); //当前值加上左边的最短路径和
        if (i != 0 && j == 0) //到达边界，到达第一列，但是还能继续向上走。
            return result + processReverseWithRecur(matrix, i - 1, j); //当前值加上上面的最短路径和
        int left = processReverseWithRecur(matrix, i, j - 1); //计算左边的最短路径和
        int up = processReverseWithRecur(matrix, i - 1, j); //计算右边的最短路径和
        return result + Math.min(left, up); //当前的和加上选择左边最短还是右边最短即可得到。
    }

    /**
     * ！！！！注意，千万不要使用++，因为一个类内一个常量会被多次引用！！！！
     * <p>
     * 正着来写，上面写得有点难看
     *
     * @param matrix 二维数组
     * @param i      第几行（第几个一维数组）
     * @param j      第几列（一维数组的第几个数）
     * @return
     */
    public static int processWithRecur(int[][] matrix, int i, int j) {
        int result = matrix[i][j];
        if (i == matrix.length - 1 && j == matrix[0].length - 1)    // 当到达右下角的时候返回右下角的值
            return matrix[i][j];
        if (i == matrix.length - 1 && j != matrix[0].length - 1)    //当到达最后一行的时候
            return result + processWithRecur(matrix, i, j + 1);   // 加上当前值的同时，最后一行不变一直向右（j++）走
        if (i != matrix.length - 1 && j == matrix[0].length - 1) // 当到达最后一列的时候
            return result + processWithRecur(matrix, i + 1, j);  // 加上当前值的同时，最后一列不变，一直向下走（i++）

        int right = processWithRecur(matrix, i, j + 1);  //什么问题都没有，就向右走
        int down = processWithRecur(matrix, i + 1, j);   //向下走
        return result + Math.min(right, down); // 比较向右走小还是向下走小，再加上当前结果即是最小路径和
    }

    /**
     * 递归改成动态规划
     * 详情可看有道云，详细步骤都在那
     * @param matrix 二维数组
     * @return
     */
    public static int processWithDyn(int[][] matrix) {
        //判空
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0)
            return 0;
        // 根据递归版本可看出有两个可变参数，于是创建二维数组，其中 第一行和第一列 分别用来存储递归中if return的值（即边界值）
        int row = matrix.length;
        int col = matrix[0].length;
        int[][] dp = new int[row][col];
        // 找到最终状态（即第一个值，通过它一步一步走）
        dp[0][0] = matrix[0][0];
        // 以下这个for循环干的事情就是将第一列赋值，赋的值是从起始点开始的边界值（即if return的边界值）
        for (int i = 1; i < row; i++) {
            dp[i][0] = dp[i - 1][0] + matrix[i][0]; //根据递归分解得到的，即（当前位置上面所有行的最小路径）和加上当前的路径和
        }
        // 以下这个for循环将第一行赋值，从0,0出发，一直赋值到底
        for (int j = 1; j < col; j++) {
            dp[0][j] = dp[0][j - 1] + matrix[0][j];  //根据递归分解得到的，即（当前位置上面所有列的最小路径）和加上当前的路径和
        }
        // 这个就是常规值的赋值，因为是逆着来求，所以值就是 比较上面和左边选择最小的（因为上一步是走右还是走左），加上当前位置的和
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + matrix[i][j];
            }
        }
        // 返回右下角
        return dp[row - 1][col - 1];
    }


    public static void main(String[] args) {
        int[][] m = {{1, 3, 5, 9}, {8, 1, 3, 4}, {5, 0, 6, 1}, {8, 8, 4, 0}};
        System.out.println(minPath1(m));
        System.out.println(minPath2(m));
        System.out.println(minPath3(m));
    }
}
