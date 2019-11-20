package learning.Day5;

import java.util.PriorityQueue;

/**
 * @description: 有花费数组cost和利润数组profit，两个数组对应的一个值cost[1]对应profit[1]构成一个项目，
 * 一个项目的完成需要 资金达到cost 项目完成就能得到cost+profit的值。一次仅能完成一个项目，且项目完成后会消失，问给定一个起步资金，做完k个项目之后最大值是多少。
 *
 * 实例：cost[10,20,100] profit[5,10,50] 表示项目一需要10块钱，完成可以得到10+5,15块钱。现在给定启动资金100块，问做2次项目后最多可以有多少钱。
 *
 * 做法：将cost和profit绑定成一个对象，把所有对象根据cost值放入一个小根堆中，给定启动资金时，在小根堆中弹出 所有cost小于启动资金 的对象，
 * 然后再将这些对象根据profit值放入大根堆中，依次取出大根堆的堆顶元素进行相加即可。
 *
 * @Time: 2019/11/14 22:36
 */
public class IPO {

    public static class Project{  //构建一个由cost和profit的项目 4 3 6 9 2 1  <0
        private int cost;
        private int profit;

        public Project(int cost, int profit) {
            this.cost = cost;
            this.profit = profit;
        }
    }

    public static int findMaxCapital(int howTime,int startMoney,int[] cost,int[] profit){
        Project[] projects=new Project[cost.length];  //构建项目数组
        for (int i = 0; i < projects.length; i++) {
            projects[i]=new Project(cost[i],profit[i]);
        }
        PriorityQueue<Project> smallCostHeap=new PriorityQueue<>((o1,o2)->o1.cost-o2.cost); //根据项目的花费构建小根堆，o1.cost<o2.cost
        PriorityQueue<Project> bigProfitHeap=new PriorityQueue<>((o1, o2) -> o2.profit-o1.profit);  //根据项目利润构建大根堆，o2.profit>o1.profit -->o2.profit-o1.profit
        for (int i = 0; i < projects.length; i++) {
            smallCostHeap.add(projects[i]);  //将所有项目加入小根堆
        }
        for (int i = 0; i < howTime; i++) {  // 构建howTime次
            while (!smallCostHeap.isEmpty()&&smallCostHeap.peek().cost<startMoney){  //不为空时 在小根堆中找到所有花费小于启动资金的（注意使用的是peek，在加入大根堆的时候才是poll）
                bigProfitHeap.add(smallCostHeap.poll());  //放入大根堆
            }
            if (bigProfitHeap.isEmpty()) {  // 当大根堆内没有项目了，意思就是你的资金不够继续做项目了，就返回当前的钱。
                return startMoney;
            }
            startMoney+=bigProfitHeap.poll().profit;  //一直累加
        }
        return startMoney;
    }
    public static void main(String[] args){
        int[] cost={10,20,100};
        int[] profit={5,10,50};
        System.out.println(findMaxCapital(3, 20, cost, profit));
    }
}
