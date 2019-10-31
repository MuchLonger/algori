package Day2;

import java.util.Queue;

/**
 * @description: 使用队列来实现栈结构
 * 基本实现：push的时候还是照常push的queue，
 * pop将queue内的前size-1个值取出到help内（留下一个值），然后将queue剩下的那个值输出，再将queue和help队列互换（swap）
 * 注意，交换的时候一定要保证queue内为空，不然数据会脏掉
 * @Time: 2019/10/27 10:50
 */
public class QueueStack {
    private Queue<Integer> queue;
    private Queue<Integer> help;

    public void push(int pushInt) {
        queue.add(pushInt);
    }

    //当queue内有值时就将队列的值全部输出，只剩一个，再将这个输出
    //之后help和queue互换，为的是queue内一直是实际队列，而help仅仅只是暂时存储值
    public int pop() {
        if (queue.isEmpty()) {
            throw new RuntimeException("栈为空");
        }
        while (queue.size() > 1) {
            help.add(queue.poll());
        }
        int res = queue.poll();
        swap();
        return res;
    }

    public int peek() {
        if (queue.isEmpty()) {
            throw new RuntimeException("栈为空");
        }
        while (queue.size() != 1) {
            help.add(queue.poll());
        }
        int res = queue.poll();  //一定要使用poll，取出并删除，保证queue为空
        help.add(res);  //将最后一个值再放回去，因为是peek操作，上面仅仅是为了取得最后一个值。
        swap();
        return res;
    }

    public void swap() {
        Queue<Integer> temp=queue;
        queue=help;
        help= temp;
    }

}
