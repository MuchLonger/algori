package Util;

import Day2.IsPalindromeList;

/**
 * @description:
 * @Time: 2019/10/28 22:25
 */
public class Node {
    public int value;
    public Node next;

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                ", next=" + next +
                '}';
    }

    public Node(int value){

        this.value=value;
    }
}
