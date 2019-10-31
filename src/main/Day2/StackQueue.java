package Day2;

import java.util.Stack;

/**
 * @description: 使用栈来实现队列结构
 * 基本思路：用两个栈存放，一个push栈（插入就插入这），一个pop栈（输出就输出这）
 * pop的时候需要判断（pop栈是否为空），只有为空的时候才把（push栈的值）pop到pop栈内（不然如果中途插入值，顺序就乱了）。
 * @Time: 2019/10/26 22:53
 */
public class StackQueue {
    private Stack<Integer> pushStack;
    private Stack<Integer> popStack;

    public StackQueue(int init) {
        pushStack = new Stack<>();
        popStack = new Stack<>();
    }

    public void push(Integer value) {
        pushStack.push(value);
    }

    public Integer pop() {
        if (pushStack.empty() && popStack.empty()) {
            throw new RuntimeException("队列为空");
        }
//        只有在popStack为空的时候，才将pushStack内的值全部放入popStack
        if (popStack.empty()) {
            while (!pushStack.empty()) {
                popStack.push(pushStack.pop());
            }
        }
        return popStack.pop(); //将pop栈内的值放出
    }

    public int peek() {
        if (popStack.empty() && pushStack.empty()) {
            throw new RuntimeException("Queue is empty!");
        } else if (popStack.empty()) {
            while (!pushStack.empty()) {
                popStack.push(pushStack.pop());
            }
        }
        return popStack.peek();
    }

    public void swap() {
        Stack<Integer> temp = popStack;
        popStack = pushStack;
        pushStack = temp;
    }
}
