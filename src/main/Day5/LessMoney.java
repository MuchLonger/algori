package Day5;

import java.util.PriorityQueue;

/**
 * @description: 问题：假设总钱每分割一次都需要花费相同的钱数，那如何将80块最便宜的分成{10,15,20,35}这四份
 * 举例：80块可以分成35 45那就需要花费80块，45又需要分成20和35，花费55，35需要分成10和15，花费35。所有加起来等于80+55+35，如何取得这个最小“和”
 *
 * 贪心算法：实际上是使用哈弗曼树，两个最小值组成一个值放回数组中，再继续求两个最小值
 *
 * 做法：求出最小堆，每次取出两个最小值，相加后放回最小堆，继续重复
 *
 * @Time: 2019/11/14 22:16
 */
public class LessMoney {
    public static int lessMoney(int[] arr){
        PriorityQueue<Integer> smallHeap=new PriorityQueue<>((o1,o2)->o1-o2); //构建最小堆 即比较器<0
        for (int i = 0; i < arr.length; i++) {
            smallHeap.add(arr[i]);
        }
        int sum=0;  //记录花费
        int cur=0;
        while (smallHeap.size() > 1) {  //至少有两个值
            cur=smallHeap.poll()+smallHeap.poll();  //连续取两个最小值
            sum+=cur;
            smallHeap.add(cur);  //相加后的值放回到数组
        }
        return sum;
    }

}
