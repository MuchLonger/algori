package Dyn;

/**
 * @description: 给定一个正数数组，再给定一个正数aim，求正数数组内是否可以通过累加得到aim
 * @Time: 2019/11/17 14:49
 */
public class MoneyProblem {

    public static boolean money1(int[] arr,int aim){
        return isSumWithRecur(arr, 0, 0, aim);
    }

    /**
     * 递归实现，有点像求字符串的所有子序列
     * 思路：
     * 第一个数分为取第一个数（加上去）和不取第一个数（不加），第二个数又分为取和不取，以此类推
     * @param arr 正数数组
     * @param i 当前数组下标索引
     * @param sum 当前累加的和
     * @param aim 目标的和
     * @return
     */
    public static boolean isSumWithRecur(int[] arr,int i,int sum,int aim){
        if(i==arr.length) // 用作取消递归 并返回最后一行的结果
            return sum==aim;
        boolean unuse = isSumWithRecur(arr, i + 1, sum, aim);  //不取当前索引的值，直接返回sum
        boolean use = isSumWithRecur(arr, i + 1, sum + arr[i], aim); //取当前索引的值，返回sum+arr[i}
        return use||unuse; // 使用或，只要有一个相等就会返回true，然后因为是“或”，向上的递归直接就会返回true
    }

    /**
     * 动态规划实现：
     * 1）写出上面的递归版本
     * 2）找到可变参数：如上就是 数组索引i和当前的和sum，构建由 dp[i+1][aim+1]。
     * 之所以行是 i+1 是因为终止条件是arr.length，所有i需要和它一致来存放终止条件的值
     * 之所以列是 aim+1 是因为超过aim+1的值都不需要了（都超过了还怎么可能相加之后等于aim）。
     * 3）找到初始值，从上来看就是aim
     * 4）找到递归的终止条件，将所有终止条件return的值 依次存入到dp[i+1][0....sum]
     * 5）找到普遍值，并依据它的条件逆着向上寻找。比如最后一行只有 sum=aim为true，然后根据递归的条件为||，即上一行是 sum+arr[i]=aim为true，和sum=aim为true
     *
     * 注意！！ 下面说的下一行都是从上往下的顺序。
     * @param arr
     * @param aim
     * @return
     */
    public static boolean isSumWithDyn(int[] arr,int aim){
        // 2）
        boolean[][] dp=new boolean[arr.length+1][aim+1];
        // 4）将sum=aim的每一行都置为true，其他就会默认为false。
        for (int i = 0; i < dp.length; i++) {
            dp[i][aim]=true;
        }
        for (int i = arr.length-1; i >=0; i--) {  //从最后一行开始向上递归（因为只有最后一行被确定）
            for (int j = aim-1; j >=0; j--) { // j表示的是所有的sum，从最后一列开始向前（虽然说顺序无所谓）
                // 这个是不取任何值的情况，直接将“当前行的下一行的值”赋值到 当前行。（根据上面递归得到的）
                dp[i][j]=dp[i+1][j];
                if(j+arr[i]<=aim){ //如果sum + arr[i](当前值） 大于了aim，那就不用判断了（都大于要求的值了还执行个毛，直接为false
                    // 直接延续下一行的True或false。
                    // 解释一下：dp[i+1][j+arr[i]]的含义：i+1表示下一行， j+arr[i]就同上面说的，表示的是 “当前值加上当前sum”如果等于aim，那下一行肯定为true，当前行也会被置为true
                    // 示例：如当前是aim=12，所以最后一行的[12]肯定被置为true，那上一行的 sum+arr[i]必须等于12（如下 arr[i]等于8，所以上面sum需要等于4）才能为true，其他都是false。以此类推
                    dp[i][j]=dp[i][j]||dp[i+1][j+arr[i]];
                }
            }
        }
        return dp[0][0];
    }
    public static void main(String[] args) {
        int[] arr = { 1, 4, 8 };
        int aim = 12;
        System.out.println(money1(arr, aim));
        System.out.println(isSumWithDyn(arr, aim));
    }

}

