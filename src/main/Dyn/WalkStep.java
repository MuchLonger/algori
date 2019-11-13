package Dyn;

import java.util.Date;

/**
 * @description: 有n级台阶，一次走一级或两级，问走完n级台阶有几种方法
 * <p>
 * 思路：走n级台阶的方法数 = “n-1级台阶走一步 方法数” 或 “n-2级走两步 方法数”。从总体来看，就是走n级阶梯，等于“走一步几次”加“走两步几次”
 * 即：f(n)=f(n-1)+f(n-2)。
 * 就能列出关系：
 * 如果n=1  f（1）=1  （走一步）
 * 如果n=2  f（2）=2  （走一步或走两步 两种）
 * 如果n>2  f（n）=f（n-1）+f（n-2）
 * @Time: 2019/11/12 20:39
 */
public class WalkStep {

    public static final int steps = 40;

    public static void process1() {
        Long start = new Date().getTime();
        System.out.println(walkWithRecord(steps, new int[steps + 1][steps + 1]));
        Long end = new Date().getTime();
        System.out.println(end - start + ":process1");
    }

    public static void process2() {
        Long start = new Date().getTime();
        System.out.println(walk(steps));
        Long end = new Date().getTime();
        System.out.println(end - start + ":process2");
    }

    /**
     * 走台阶 暴力搜索方法
     *
     * @param step
     * @return
     */
    public static int walk(int step) {
        if (step < 1)
            return 0;
        if (step == 1 || step == 2)
            return step;  //正好1步就是1,2步就是2
        return walk(step - 1) + walk(step - 2);
    }

    /**
     * 走台阶 记忆搜索
     *
     * @param step
     * @param map
     * @return
     */
    public static int walkWithRecord(int step, int[][] map) {
        int result = 0;
        if (step < 1)
            return 0;
        if (step == 1 || step == 2)
            return step;  //正好 1步就是1,2步就是2
        int totalValue = map[step - 1][step - 2];
        if (totalValue != 0) {
            result = map[step - 1][step - 2] == -1 ? 0 : totalValue;  //下一步的值
        } else {
            result = walkWithRecord(step - 1,map) + walkWithRecord(step - 2,map);
        }
        map[step - 1][step - 2] = result == 0 ? -1 : result;
        return result;
    }

    public static void main(String[] args) {
        process1();
        process2();
    }
}
