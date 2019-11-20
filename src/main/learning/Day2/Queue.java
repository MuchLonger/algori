package learning.Day2;

import Util.ArrUtil;

/**
 * @description: 数组实现队列，使用start和end分别指向队列表示第一个位置和最后一个位置，再使用size来约束队列长度
 * <p>
 * start指向队列第一个位置（逻辑上的）：仅用作输出，即输出后就++
 * end指向队列最后一个位置（逻辑上的）：仅用作插入，即插入后就++
 * size表示队列的实际大小，poll后size--而实际数组不变，push后size++
 * @Time: 2019/10/26 16:06
 */
public class Queue {
    private Integer[] queue;
    private Integer start;
    private Integer end;
    private Integer size;


    public Queue(int init) {
        if (init < 0)
            throw new RuntimeException("不能小于零");
        start = 0;
        end = 0;
        size = 0;
        queue = new Integer[init];
    }

    /**
     * 返回第一个进入队列的元素，而不删除
     *
     * @return
     */
    public int peek() {
        if (size <= 0)
            throw new RuntimeException("数组为空");
        return queue[start++];
    }

    public int poll() {
        if (size <= 0)
            throw new RuntimeException("数组为空");
        size--;
        int temp = start; //记录当前位置，用于输出
        start = start == queue.length - 1 ? 0 : start + 1;  //处理数组：将start向后移
        return queue[temp];
    }

    public void push(int k) {
        if (size >= queue.length)
            throw new RuntimeException("超过队列大小");
        size++;
        queue[end] = k;  //先设置值
        end = end == queue.length - 1 ? 0 : end + 1; //值已经添加进去了，所以当end到达最底的时候end++
    }

    public void display() {
        ArrUtil.show(queue);
    }


    public static void main(String[] args) {

    }
}
