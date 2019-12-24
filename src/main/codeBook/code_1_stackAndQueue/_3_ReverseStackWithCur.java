package codeBook.code_1_stackAndQueue;

import java.util.Stack;

/**
 * @description: 仅使用递归结构和栈的操作，返回一个逆序的栈: 将栈底放置到栈顶
 * @Time: 2019/12/3 15:22
 */
public class _3_ReverseStackWithCur {
    public static Stack<Integer> stacks=new Stack<>();

    /**
     * 返回并删除栈底的元素，递归
     * @param stack
     * @return
     */
    public static int getAndRemoveLastElement(Stack<Integer> stack ){
        int result=stack.pop();
        if(stack.isEmpty()){
            return result; // 直接返回栈底，并且不再添加回栈内
        }else{
            int last=getAndRemoveLastElement(stack);
            stack.push(result);
            return last;
        }
    }

    /**
     * 通过上面的删除并获取栈底，只需要将栈底元素重新放回到栈内，重复到栈为空，然后再重新添加进去
     * @param stacks
     */
    public static void reverse(Stack<Integer> stacks){
        if (stacks.isEmpty()) {
            return ;
        }
        int last=getAndRemoveLastElement(stacks);
        reverse(stacks);  // 先执行这一步，这样递归到底的时候就能根据是否为空来判断了
        stacks.push(last);
    }

    public static void main(String[] args){
        stacks.push(1);
        stacks.push(2);
        stacks.push(3);
        stacks.push(4);
        stacks.push(5);
        reverse(stacks);
        while(!stacks.isEmpty()){
            System.out.print(stacks.pop()+"  :  ");
        }

    }

}
