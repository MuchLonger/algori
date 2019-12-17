package codeBook.third_part_tree;

import java.nio.file.NotLinkException;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @description: 按层打印二叉树，但是必须按照如下形式（即需要知道什么时候换行，同一层的打印在一行）
 *      1
 *   2     3
 * 4  5  6  7
 * 第1层：1
 * 第2层：2 3
 * 第3层：4 5 6 7
 * @Time: 2019/12/17 14:44
 */
public class _9_PrintByLevel {

    /**
     * 做法：使用一个last节点记录“当前层”的最后一个节点，
     *       使用moveLst节点记录“下一层”的最后一个节点。（每一次记录下一层(left,right)的节点的时候都记录）
     *       当当前节点到达last的时候就开始换行。并且将moveLast赋值给last，因为这个时候也正好就是下一个节点的最后一个节点。
     * @param head
     */
    public static void printByLevel(Node head) {
        if (head == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();

        int level = 1;  // 从1开始
        Node last = head;
        Node moveLast = null;

        queue.offer(head);
        System.out.print("Level " + level + "：");
        while (!queue.isEmpty()) {
            head = queue.poll();
            System.out.print(head.value + " ");  // 常规的层次遍历
            if (head.left != null) {
                queue.offer(head.left);
                moveLast = head.left;  // 每一次都要记录，因为可能right为null，这样left记录就有效了
            }
            if (head.right != null) {
                queue.offer(head.right);
                moveLast = head.right; // 每一次都要记录，
            }
            if (head == last && !queue.isEmpty()) {  // 当head=last就意味着这一层到达最底，需要需要换行，并且注意队列不能为空，不然最后会打印多一行
                System.out.print("\nLevel "+(++level)+" : ");
                // 将下一层的moveLast赋值到last节点上，因为这时候正好也是“这一层的最后一个节点”“的左右节点”被记录，所以这样就实现了当前层和下一层最后一个节点的交换
                last= moveLast;
            }
        }
    }

    /**
     *           6
     *      4         7
     *   2   5          9
     * 1 3            8
     */
    public static void main(String[] args){
        Node tn = new Node(6);
        tn.left = new Node(4);
        tn.right = new Node(7);
        tn.left.left = new Node(2);
        tn.left.right = new Node(5);
        tn.right.right = new Node(9);
        tn.right.right.left = new Node(8);
        tn.left.left.left = new Node(1);
        tn.left.left.right = new Node(3);

        printByLevel(tn);

    }

}
