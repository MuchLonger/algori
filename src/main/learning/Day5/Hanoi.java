package learning.Day5;

/**
 * @description: 汉诺塔：如何将n个数从左杆移到右杆，可以借助中间的杆，摆放如下
 * |  |  |
 * 1  |  |
 * 2  |  |
 * 3  |  |
 * |  |  |
 * ------------
 * @Time: 2019/11/15 22:10
 */
public class Hanoi {

    public static void hanoi(int n) {
        if (n > 0) {
            process(n, "左", "中", "右");
        }
    }

    /**
     * 思路：分三步
     * 1）左杆上有n个值，先将n-1个值放入到中间杆上，此时左杆还剩一个n，
     * 2）将这个n移动到右杆上
     * 3）然后将help的n-1个值放入到中间杆中即可（之所以放入中间杆即可，是因为将n-1放入中间杆的途中又会递归执行上面的步骤）。
     *
     * @param n    表示左杆有n个数（1...n)，注意是总个数
     * @param from 从这个杆
     * @param to   移到这个杆
     * @param help 借助这个杆
     */
    public static void process(int n, String from, String to, String help) {
//        这一个是第二步，即将n移动到右杆
        if (n == 1) {  //遇到最顶层（1）结束，同时输出将1从当前杆移动到to上去。这一句话实际
            System.out.println("将 1 从 " + from + " 移动到 " + to + " 上去");
        } else {  //想要理解，从中间的sout输出语句入手，如from和to的值是哪些

            // 将 from 的n-1个值借助 help 放到 to 上，   实际输出的是from 和 help（即从from输出到help）
            process(n - 1, from, help, to);
            // 这个会输出 from 的 n-1 放入到 help上
            System.out.println("将 " + n + " 从 " + from + " 移动到 " + to + " 上去 ");
            // 再将help上存的n-1个放回到from中。之所以是help存的，因为在下一步又会递归执行回第一步，且第一步的help就被当成了from（看输出语句from和to）
            process(n - 1, help, to, from);
        }
    }

    public static void main(String[] args) {
        hanoi(3);
    }
}
