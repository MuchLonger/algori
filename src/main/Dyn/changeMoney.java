package Dyn;

import java.util.Map;

/**
 * @description: 找钱问题：给定一个数组，所有数字为正数，且数组的值表示钞票的面值。再给定一个钱数，表示找钱有几种方法
 * [5,10,20,1] 1000：表示的是有5,10,20,100种面值的钞票，要找成1000块 有几种找法
 * <p>
 * 使用三种方法：
 * 1）暴力破解
 * @Time: 2019/11/9 16:13
 */
public class changeMoney {
    private static int[] arr = {5, 10, 20, 1};

    /**
     * 使用不同的方法找钱次数
     *
     * @param arr 钞票面值
     * @param aim 找钱数
     * @return
     */
    public static int coins1(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0)
            return 0;
        int[][] recordArr = new int[arr.length + 1][aim + 1];  //注意要加1，因为需要查找到下一个的面额和期望钱数
        return processWithRecurAndArr(arr, 0, aim, recordArr);
/*
    public int coins1(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0)
            return 0;
        return processWithReCur(arr, 0, aim);
*/
    }

    /**
     * 直接使用递归。思路：
     * 使用一张5块 加任意张10 20 1面值找成995块有多少种。
     * 使用两张5块 加任意张10 20 1面值找成900块有多少种，再加上上面
     * 一直重复，终点是 200张5块 加任意张10 20 1找成的0块，一直加就是总数
     * <p>
     * 缺点：大量重复计算。
     * 比如说计算2张5元0张十元的情况，下一个递归是 processWithRecur(arr.,2,990)
     * 0张5元 1张10元的情况，结果应该与上面一致，但是却还是得执行一次递归 processWithRecur(arr.,2,990)。
     * 这就造成了重复计算
     *
     * @param arr   面值数组
     * @param index 从哪个面值开始
     * @param aim   找多少钱
     * @return
     */
    private static int processWithReCur(int[] arr, int index, int aim) {

        int methodCount = 0;
        if (index == arr.length) {
            methodCount = aim == 0 ? 1 : 0;  //如果最后指正好取整，那就多一种方法，再返回
        } else {
            for (int i = 0; i <= (aim / arr[index]); i++) {  //i<= (aim/arr[index]) 的意思面 面值*次数不能超过找钱的值。比如说 1000/5 ，意味着5元的方法只能取200次。
//                存入当前数组，下一个面值（10），总钱数就变成了aim-arr[index]*i（995）。这样就能实现上面说的一直累加
                methodCount += processWithReCur(arr, index + 1, aim - arr[index] * i);
            }
        }
        return methodCount;
    }

    /**
     * 计算流程和递归一样，但是加入了一个map来保存每次递归计算后的结果，这样可以避免重复计算。
     *
     * 个人总结：存的是当前（存当前 面值方案 有几种找法），取的是下次（取下一个 面值方案 的找法）
     * @param arr       面值数组
     * @param index     面值下标
     * @param aim       还要计算多少钱
     * @param recordArr 使用二维数组记录每一笔钱，用来去重。recordArr[i][j]：i表示面值，j表示还要多少钱
     * @return
     */
    private static int processWithRecurAndArr(int[] arr, int index, int aim, int[][] recordArr) {
        int result = 0;
        int recordCurAim=aim-arr[index];  //记录
        if (index == arr.length) {
            result = aim == 0 ? 1 : 0; //能整除次数就加一
        } else {
            int totalValue = 0;
            for (int i = 0; i <= aim / (arr[index]); i++) {
                totalValue = recordArr[index + 1][aim - arr[index] * i]; //下一个面值 和 下一个期望找钱数 构成的就是下次会有多少种找法（注意，一维数组中的存放位置 必须 是面值的倍数
                if (totalValue != 0) {  // 下一个面值有值，直接取
                    //先设置为-1，再在查找时判断是否为-1，如果为-1就返回0，这样的目的是因为默认的数组值为0，所以需要区分
                    result += totalValue == -1 ? 0 : totalValue;
                } else {  //如果下一个面值没有值
                    result += processWithRecurAndArr(arr, index + 1, aim - arr[index] * i, recordArr);
                }
            }
        }
        //先设置为-1，再在查找时判断是否为-1，如果为-1就返回0，这样的目的是因为默认的数组值为0，所以需要区分
        recordArr[index][aim] = result == 0 ? -1 : result;  //这里存的是 当前面值 和 期望找钱数。
        return result;
    }

    public static void main(String[] args) {
        System.out.println(coins1(arr, 1000));

    }
}
