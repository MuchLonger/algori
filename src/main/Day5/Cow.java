package Day5;

/**
 * @description: 假设母牛不会死，一年生一只小母牛，小母牛三年后成熟，问第n年有几头牛
 * 第一年	第二年	第三年	第四年	第五年	第六年	第七年
 *   1        2        3        4       6      9      12
 * 可以看出前四年不变（因为小牛都为成熟）第五年开始，第二年的小牛可以生崽了，从第五年开始就可以得出
 * 公式如下：    f（N）=f（N-1）+f（N-3）
 * 公式的含义是：今年的牛 等于 去年的所有牛再加上三年前的所有牛（因为三年前的牛可以生崽了）。
 * 去年所有的牛：因为牛不会死，所以去年的牛会全部留下来
 * 三年前的所有牛：因为三年前的所有牛在今年都可以生崽，所以 这三年前的所有牛 指的就是可以生下多少崽。
 * @Time: 2019/11/16 21:15
 */
public class Cow {
    /**
     * 计算n年牛的数量
     * @param n
     * @return
     */
    public static int nYearCow(int n){
        if(n<=0)
            return 0;
       if(n==1||n==2||n==3||n==4)
           return n;
       return nYearCow(n-1)+nYearCow(n-3);
    }

    public static void main(String[] args){
        System.out.println(nYearCow(7));
    }
}
