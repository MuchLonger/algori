package codeBook.first_part_stackAndQueue;

import java.util.Stack;

/**
 * @description: 两个栈实现一个队列：只需保证 必须一次将push栈的所有值放入pop栈，并列pop栈不为空就不能执行上面操作
 * @Time: 2019/12/3 14:50
 */
public class _2_TwoStackQueue {
    private Stack<Integer> pushStack;
    private Stack<Integer> popStack;

    public _2_TwoStackQueue() {
        pushStack = new Stack<>();
        popStack = new Stack<>();
    }

    public void add(int data) {
        pushStack.push(data);
    }

    public int poll() {
        if (popStack.isEmpty() && pushStack.isEmpty()) {
            throw new RuntimeException("队列内没有值");
        }
        if (popStack.isEmpty()) {
            while (!pushStack.isEmpty()) {
                popStack.push(popStack.pop());
            }
        }
        return popStack.pop();
    }

    public int peek() {
        if (popStack.isEmpty() && pushStack.isEmpty()) {
            throw new RuntimeException("队列内没有值");
        }
        if (popStack.isEmpty()) {
            while (!pushStack.isEmpty()) {
                popStack.push(popStack.pop());
            }
        }
        return popStack.peek();
    }

    public static void main(String[] args) {

    }
}
