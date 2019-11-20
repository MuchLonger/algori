package learning.Day3;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @description: 判断是否是搜索二叉树，递归版和非递归版。以及判断是否是完全二叉树。
 * 搜索二叉树(BST)：任意节点的左节点小于当前节点，右节点大于当前节点。判断方法：判断中序遍历是否按顺序
 * 完全二叉树（CBT)：一棵树的最后一层的叶节点都是按顺序的（一时不知怎么解释）：
 * 如：   1                                        1
 * 2   3   :这样就叫完全二叉树               2  3   ：这样就不叫完全二叉树（中间空了）
 * 4 5  6       //这里最后一层按顺序（456）  4 5   6
 * @Time: 2019/11/3 13:17
 */
public class IsBSTAndCBT {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    /**
     * 非递归版本判断是否是搜索二叉树：使用中序遍历的方法，只需在输出的那个地方进行修改即可。
     * 因为是中序遍历有序，所以只需要取一个变量一直记录即可判断
     * 中序遍历有序的树就是BST
     *
     * @return
     */
    public static boolean isBstNoCur(Node head) {
        int lastNum = Integer.MIN_VALUE;
        if (head == null) {
            return false;
        }
        Stack<Node> stack = new Stack<>();
        while (!stack.isEmpty() || head != null) {
            if (head != null) {
                stack.push(head);
                head = head.left;
            } else {
                head = stack.pop();

                // BST判断，只需要判断上一个值是否比当前值大即可。
                if (head.value > lastNum) {
                    lastNum = head.value;
                } else {
                    return false;
                }

                head = head.right;
            }
        }
        return true;
    }

    /**
     * 递归版本1:判别是否是搜索二叉树（根据平衡二叉树改版，纯粹自己写的）
     * 中序遍历版本的不会，就暂时用这个当递归版吧
     *
     * @param head
     * @return
     */
    public static boolean isBstCur1(Node head) {
        boolean flag = false;
        boolean res = false;
        if (head == null)
            return true;
        boolean leftTree = isBstCur1(head.left);
        if (!leftTree)
            return false;
        boolean rightTree = isBstCur1(head.right);
        if (!rightTree)
            return false;
        return compareNum(head);
    }

    /**
     * 比较值，四种情况，左右不为null，左null右不为null，左不为null右null，左右为null
     *
     * @param head
     * @return
     */
    public static boolean compareNum(Node head) {
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


    /**
     * 判断是否是完全二叉树：只要满足以下两个条件的就是完全二叉树
     * 条件1）不存在“一个节点有右节点而没有左节点”的情况 （即当一个节点左节点为空右节点有值的时候就不是完全二叉树）
     * 条件2）当存在“一个节点没有左右节点” 或 “一个节点有左节点没有右节点”的情况，这个节点往下（按照左到右，上到下的顺序）的 所有的节点 都必须是叶节点。
     * 满足上面就是完全二叉树
     * 遍历的顺序是从左到右，从上到下！！！（层次遍历）
     *
     * @param head
     * @return
     */
    public static boolean isCBT(Node head) {
        if (head == null)
            return true;
        Queue<Node> queue = new LinkedList<>();  // 实现层次遍历使用队列，关于使用队列来层次遍历可以看序列那个Class
        boolean state = false;  //注意，这个是用来判断情况二是否符合，如果情况二符合就会被置为true，之后的节点都会判断是否是叶节点
        Node leftNode = null;  //当前节点的左节点
        Node rightNode = null;  //当前节点的右节点
        queue.offer(head);
        while (!queue.isEmpty()) {
            head = queue.poll();  //取出节点
            leftNode = head.left;
            rightNode = head.right;
            if (state && (leftNode != null || rightNode != null)   //这个是条件二开启的情况下，判断往下的节点是否是叶节点，如果不是叶节点就返回false
                    || (leftNode == null && rightNode != null)) {   //这个条件是判断条件一（左节点为null右节点不为null）是否符合，如果符合也不是完全二叉树
                return false;
            }

            if (leftNode != null) {
                queue.offer(leftNode);
            }

            if (rightNode != null) {
                queue.offer(rightNode);
            } else {  //当右节点为空的时候就置为true（不论左边有没有值）。
                // 因为左节点为null 右节点不为空的情况 已经返回false了，而两个都不为空的情况就加入队列。剩下的两种情况就都是表示开启条件二
                state = true;
            }

            /*  这句话和上面的else表达的意思一致，都是表示如果当前节点是左右节点为空（叶节点）或“左节点不为空 右节点为空”的情况
            if ((leftNode == null && rightNode == null) || (leftNode != null && rightNode == null)) {
                state = true;
            }
            */
        }
        return true;
    }


    public static void main(String[] args) {
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
        System.out.println(isBstCur1(head));
    }

}
