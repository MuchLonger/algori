package codeBook.third_part_tree;


import learning.Day3.IsBSTAndCBT;

import java.util.Stack;

/**
 * @description: 判断一棵树是否是搜索二叉树
 * @Time: 2019/12/20 11:44
 */
public class _16_IsBST {

    /**
     * 只需要中序遍历的顺序保持升序就好了
     */
    public static boolean isBST(Node head) {
        int lastNum = Integer.MIN_VALUE;
        if (head == null) {
            return true;
        }
        Stack<Node> stack = new Stack<>();
        while (!stack.isEmpty() || head != null) {
            if (head != null) {
                stack.push(head);
                head = head.left;
            } else {
                head = stack.pop();
                if (head.value >= lastNum) {   // 和上一个中序遍历的值比较，如果小于它那就不是二叉搜索树
                    lastNum = head.value;
                } else {
                    return false;
                }
                head = head.right;  // 不要忘记移动
            }
        }
        return true;
    }

    // 自己写的递归
    public static boolean isBSTCur(Node head) {
        boolean flag=false;
        boolean res=false;
        if (head == null) {
            return true;
        }
        boolean leftTree=isBSTCur(head.left);
        if(!leftTree)
            return false;
        boolean rightTree=isBST(head.right);
        if(!rightTree)
            return false;
        return compareNum(head);
    }

    public static boolean compareNum(Node head) {  // 根据左右子树判断是否是二叉搜索树
        if (head.left != null && head.right != null) {
            if (head.left.value < head.value && head.right.value > head.value) {
                return true;
            }
        }
        if (head.right != null && head.left == null) {
            if (head.right.value > head.value)
                return true;
        }
        if (head.right == null && head.left != null) {
            if (head.left.value < head.value)
                return true;
        }
        if (head.left == null && head.right == null)
            return true;
        return false;
    }



    public static void main(String[] args) {
        Node tn = new Node(6);
        tn.left = new Node(4);
        tn.right = new Node(7);
        tn.left.left = new Node(2);
        tn.left.right = new Node(5);
        tn.right.right = new Node(1);
        tn.right.right.left = new Node(8);
        tn.left.left.left = new Node(9);
        tn.left.left.right = new Node(3);
        int[] b = {0};

        Node head = new Node(5);
        head.left = new Node(3);
        head.right = new Node(8);
        head.left.left = new Node(2);
        head.left.right = new Node(4);
        head.left.left.left = new Node(1);
        head.right.left = new Node(7);
        head.right.left.left = new Node(6);
        head.right.right = new Node(10);
        head.right.right.left = new Node(9);
        head.right.right.right = new Node(11);

        System.out.println(isBST(tn));
        System.out.println(isBSTCur(tn));
        System.out.println("--------------------");
        System.out.println(isBST(head));
        System.out.println(isBSTCur(head));

    }
}
