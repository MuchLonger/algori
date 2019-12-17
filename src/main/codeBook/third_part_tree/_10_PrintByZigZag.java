package codeBook.third_part_tree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @description: 按照ZigZag（交错）的方式打印树
 * 1
 * 2     3
 * 4  5  6
 * 7 9
 * 打印形式如下；
 * 第一层从左到右：1
 * 第二层从右到左：3 2
 * 第一层从左到右：4 5 6
 * 第二层从右到左：9 7
 * @Time: 2019/12/17 15:28
 */
public class _10_PrintByZigZag {

    /**
     * 使用双端队列（可从头插入也可从尾插入），
     * 当按照从左到右的顺序时，需要从 头节点 开始取出值，从尾节点插入，并先插入左节点再插入右节点，（就像是平常的层次遍历）
     * 当按照从右到左的顺序时，需要从 尾节点 开始取出值，从头节点插入，并先插入右节点再插入左节点（如果不这样，会导致仅一个节点的左右节点逆序）。
     * 还需要注意的是：判断什么时候换行？在当前层第一次插入下一层dq的时候 的那个值就是结束值（因为顺着数他会是逆着数的最后一个，逆着数的最后一个就是顺着数的第一个）
     * @param head
     */
    public static void printByZigZag(Node head) {
        if (head == null) {
            return;
        }

        Deque<Node> dq = new LinkedList<>();

        int level = 1;
        boolean l2r = true; // 从左到右

        // 用于确定什么时候换行。而下一层最后打印的节点就是当前层有孩子节点中最先进入dq的（从左到右和从右到左会有不同的节点进入）
        Node last = head;
        Node moveLast = null;

        dq.offerFirst(head); // 从左到右，所以从头部插入
        printLevelAndOrientation(level++, l2r);

        while (!dq.isEmpty()) {
            if (l2r) {
                head = dq.pollFirst();
                if (head.left != null) {
                    // 第一个进入队列的就是下一行的结束节点。
                    moveLast = moveLast == null ? head.left : moveLast;
                    dq.offerLast(head.left);
                }
                if (head.right != null) {
                    moveLast = moveLast == null ? head.right : moveLast;
                    dq.offerLast(head.right);
                }
            } else {
                head = dq.pollLast();
                if (head.right != null) {
                    // 第一个进入队列的就是下一行的结束节点。
                    moveLast = moveLast == null ? head.right : moveLast;
                    dq.offerFirst(head.right);
                }
                if (head.left != null) {
                    moveLast = moveLast == null ? head.left : moveLast;
                    dq.offerFirst(head.left);
                }
            }
            System.out.print(head.value + " ");
            if (head == last && !dq.isEmpty()) {
                l2r = !l2r;  // 逆序
                last = moveLast;
                moveLast = null;  // 至为null，不然触发不了三目
                System.out.println();
                printLevelAndOrientation(level++, l2r);  // 打印下一行
            }
        }


    }

    private static void printLevelAndOrientation(int level, boolean l2r) {
        System.out.print("Level " + level + " 从");
        System.out.print(l2r ? "左到右：" : "右到左：");
    }

    /**
     *             6
     *       4         7           ==》 6 74 259 831
     *   2   5          9
     * 1 3            8
     */
    public static void main(String[] args) {
        Node tn = new Node(6);
        tn.left = new Node(4);
        tn.right = new Node(7);
        tn.left.left = new Node(2);
        tn.left.right = new Node(5);
        tn.right.right = new Node(9);
        tn.right.right.left = new Node(8);
        tn.left.left.left = new Node(1);
        tn.left.left.right = new Node(3);

        printByZigZag(tn);
    }
}
