package codeBook.code_1_stackAndQueue;

import java.util.Stack;

/**
 * @description: 使用一个栈来对另外一个栈进行排序：取出一个栈元素与help栈比较，
 * 1）大于 直接压栈到help，
 * 2）小于等于 就将help栈顶取出到当前栈，继续比较，直到符合1。
 * @Time: 2019/12/3 16:06
 */
public class _5_SortOtherStackByStack {
    public static Stack<Integer> stack=new Stack<>();
    public static void sort(Stack<Integer> s){
        if(s.isEmpty()){
            return ;
        }
        Stack<Integer> helpStack=new Stack<>();
        while(!stack.isEmpty()){
            int cur=stack.pop();
            while(!helpStack.isEmpty()&&helpStack.peek()>cur){  // 不为空并且 比help栈顶小时就取出help的值添加到stack内
                stack.push(helpStack.pop()); // 之所以放到stack内，是因为没有多余的数据结构了。反正后面会再将值添加进去。
            }
            helpStack.push(cur);
        }
        while (!helpStack.isEmpty()) {
            stack.push(helpStack.pop());
        }
    }
    public static void main(String[] args){

    }
}
