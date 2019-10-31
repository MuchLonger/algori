package Day2;

/**
 * @description:一个栈结构，但是还使用了最小栈获取最小值的操作。
 * @Time: 2019/10/26 17:03
 */

import Util.ArrUtil;

/**
 * 一个栈（没什么好讲的，维护一个指针size，随着插入增加删除减少。
 *
 * 新功能，返回 “当前栈内” 最小元素，要求时间复杂度为O（1）：
 * 做法，维护一个最小栈(实际实现还是数组）：
 * 每当插入一个值就判断，如果比上一个值大就插入上一个值，比上一个值小就插入比较小的值，这样的好处就是能够“同步的”删除和增加
 */
public class Stack{
    private Integer[] arr;
    private Integer size;
    private Integer[] minStack;

    public Stack(int initSize) {
        if (initSize < 0) {
            throw new IllegalArgumentException("The init size is less than 0");
        }
        arr = new Integer[initSize];
        minStack = new Integer[initSize];  //像上面一个数组一样初始化最小栈
        size = 0;
    }

    public Integer peek() {
        if (size == 0) {
            return null;
        }
        return arr[size - 1];
    }

    /**
     * 插入数，并实现最小栈功能
     * @param obj
     */
    public void push(int obj) {
        if (size == arr.length) {
            throw new ArrayIndexOutOfBoundsException("The queue is full");
        }
        arr[size] = obj;
        // 实现插入最小栈
        if(size==0){    //如果是第一个数就直接插入
            minStack[size]=obj;
        }else{
            if(obj<=minStack[size-1]){ //当小于“最小栈的栈顶 size-1为栈顶”才入栈
                minStack[size]=obj;
            }else{
                minStack[size]=minStack[size-1]; //大于最小栈的值，就继续新增一个最小栈的栈顶元素（size-1）
            }
        }

        size++;
    }

    /**
     * 获取栈中最小值（O（1））
     * @return
     */
    public Integer getMin(){
        return  minStack[size-1];
    }
    public void display(){
        ArrUtil.show(arr);
    }

    public void displayMin(){
        ArrUtil.show(minStack);
    }

    public Integer pop() {
        if (size == 0) {
            throw new ArrayIndexOutOfBoundsException("The queue is empty");
        }
        return arr[--size];
    }

    public static void main(String[] args){
        Stack s=new Stack(6);
        s.push(32);
        s.push(51);
        s.push(20);
        s.push(4);
        s.push(14);
//        s.pop();
        s.display();
        s.displayMin();
        System.out.println(s.getMin());
    }
}
