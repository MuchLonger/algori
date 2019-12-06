package codeBook.first_part_stackAndQueue;

import java.util.Stack;

/**
 * @description: 构建一个可以获得最小值的栈：使用两个栈来做，一个数据栈，一个当前最小栈
 * @Time: 2019/12/3 9:59
 */
public class _1_GetMinStack {
    private Stack<Integer> stackData; // 存放实际数据
    private Stack<Integer> stackMin;  // 存放最小值

    public _1_GetMinStack(){
        stackData=new Stack<>();
        stackMin=new Stack<>();
    }

    public void push(int data){
        if(this.stackMin.isEmpty()){
            stackMin.push(data);
        }else if(this.stackMin.peek()>data){
            stackMin.push(data);
        }
        this.stackData.push(data);
    }

    public Integer pop(){
        if(this.stackData.isEmpty()||this.stackMin.isEmpty()){
            throw new RuntimeException("栈内没有值");

        }
        if(this.stackMin.peek()==this.stackData.peek()){
            this.stackMin.pop();
        }
        return this.stackData.pop();
    }
    public Integer getMin(){
        if (this.stackMin.isEmpty()) {
            throw new RuntimeException("栈内没有值");

        }
        return this.stackMin.peek();
    }
    public Integer size(){
        if (this.stackData.isEmpty()) {
            throw new RuntimeException("栈内没有值");

        }
        return stackData.size();
    }
    public Boolean isEmpty() throws Exception {
        if (this.stackData.isEmpty()) {
            throw new Exception("栈内没有值");
        }
        return stackData.isEmpty();
    }


    public static void main(String[] args) throws Exception {
        _1_GetMinStack gms=new _1_GetMinStack();
        gms.push(5);
        gms.push(6);
        gms.push(7);
        gms.push(3);
        gms.push(4);
        while(!gms.isEmpty()){
            System.out.println(gms.getMin());
            System.out.print(gms.pop()+"  :  ");
        }

    }


}
