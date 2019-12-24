package codeBook.code_4_dynamic;

/**
 * @description: 给定整数N，返回斐波那数的第N项
 * 斐波那数列：1、1、2、3、5、8、13、21....：看出规律是 除了第一项第二项为1之外，F(N)=F(N-1)+F(N-2)：2=1+1; 3=2+1；
 * 相似题目：
 * 1）走台阶，每次走1级或2级，传入一个N，求N层台阶有几种走法：同样是F(N)=F(N-1)+F(N-2)，只是F(1)=1,F(2)=2;
 * 2）母牛生崽：母牛每年生一头小牛，三年后小牛也能生一头小牛。现在有一头母牛，求n年后共有多少头牛
 * @Time: 2019/12/24 13:53
 */
public class _1_Fibonacci {

    /**
     * 斐波那数列：递归实现（O(2的n次方)）
     */
    public static int f1(int n) {
        if (n < 1) {  // 注意边界
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        return f1(n - 1) + f1(n - 2);
    }

    /**
     * 斐波那数列：for循环实现（0(N)）
     * 因为F(N)=F(N-1)+F(N-2)，故res就模拟F(N-1)，next就模拟F(N-2)，
     * 如1,1,2,3,5。res=1，next=1，之后n=3，就是F(3)=res+next，res再重新赋值变成了F(3),next就接替res的位置
     *
     * @param n
     * @return
     */
    public static int f2(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        int res = 1;  // F(N-1)
        int pre = 1;     //F(N-2)，N-1前一个
        int temp = 0;  //暂时存储res
        for (int i = 3; i < n + 1; i++) {
            temp = res;
            res = res + pre;
            pre = temp;
        }
        return res;
    }

    /**
     * 走台阶：递归实现
     */
    public static int s1(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return n;
        }
        return s1(n - 1) + s1(n - 2); //第N层就是“N-2走2步（两种走法）”或“N-1走1步（一种走法）”
    }

    /**
     * 走台阶：for循环模拟实现
     */
    public static int s2(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return n;
        }
        int res = 2;  // 走2步有两种方法
        int pre = 1; // 走1步有一种方法，res的前一个结果
        int temp = 0;
        for (int i = 3; i < n + 1; i++) {
            temp = res;
            res = res + pre;
            pre = temp;
        }
        return res;
    }

    /**
     * 牛数量：递归实现
     */
    public static int c1(int n) {
        if (n < 1) {
            return 0;
        }
        if (n < 4) {
            return n;  // n=1，返回1，n=2返回2，因为第一头牛在生
        }
        return c1(n-3)+c1(n-1);
    }

    /**
     * 母牛生崽：for循环模拟实现
     */
    public static int c2(int n){
        if (n < 1) {
            return 0;
        }
        if (n < 4) {
            return n;
        }
        int res=3;
        int pre=2;
        int prePre=1;   //需要保存三个值，当前年数量，去年牛数量，前年牛（这些牛已经可以生产了）数量
        int temp1=0;
        for (int i = 4; i < n + 1; i++) {
            temp1=pre;
            pre=res;
            res=prePre+res;  // 通过这一步引出另外三段代码，因为需要更新牛数量。
            prePre=temp1;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(c1(7));
        System.out.println(c2(7));
    }
}
