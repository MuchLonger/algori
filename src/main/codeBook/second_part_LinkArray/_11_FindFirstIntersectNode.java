package codeBook.second_part_LinkArray;

import java.util.HashSet;
import java.util.Set;

/**
 * @description: 找到第一个相交的节点，具体描述看 FindFirstIntersectNode
 * 1）判断链表是否有环，（1）使用快慢节点（2）使用hashSet
 * 2）判断无环链表是否相交，（1）使用快慢节点（2）使用hashSet
 * 3）判断有环链表是否相交，
 * @Time: 2019/12/9 17:43
 */
public class _11_FindFirstIntersectNode {
    /**
     * 使用set来判断是否有环并返回环节点，每走一步都加入set，每一次都判断
     */
    public static Node getLoopWithSet(Node head) {
        Set<Node> set = new HashSet<>();
        while (head != null) {
            if (set.contains(head)) {
                return head;
            }
            set.add(head);
            head = head.next;
        }
        return null;
    }

    /**
     * 判断是否有环并返回起始点，使用快慢节点
     */
    public static Node getLoopWithFastAndSlow(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        Node slow = head.next;
        Node fast = head.next.next;
        while (slow != fast) {  // 直到两者相撞才结束
            if (fast.next == null || fast.next.next == null) {
                return null;  //不相遇就是没有环
            }
            slow = slow.next;
            fast = fast.next.next;  // 慢节点一次一步，快节点一次两步
        }
        //  如果两者相遇，快节点回到起始，以下用来求环的起始点
        fast = head;
        while (fast != slow) {  // 这个循环死记！！！是用来求环的起始点，一个节点走一步，再次撞到就是环起始点。
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    /**
     * 方法一：使用HashSet：将链表一所有Node放入Set中，再遍历链表二，使用contains方法，如果为true就有相交节点，直接return 当前节点。
     */
    public static Node noLoopNodeWithSet(Node head1, Node head2) {
        Set<Node> set = new HashSet<>();
//        head1所有节点放入set
        while (head1 != null) {
            set.add(head1);
            head1 = head1.next;
        }
//        head2内如果有一致的就直接返回
        while (head2 != null) {
            if (set.contains(head2)) {
                return head2;
            }
            head2 = head2.next;
        }
        return null;
    }

    /**
     * 使用快慢节点，因为拓扑结构已经确定，通过观察可看出两者交点一致
     * 方法二：使用快慢节点：先遍历链表一得到链表一的长度和尾节点，再遍历链表二得到链表二的长度和尾节点。如果两个尾节点不相等，那肯定没有交点(Y尾节点肯定汇合)。
     * 如果相等，再判断链表一长还是链表二长。假设当前链表二长，那用链表二的长度减链表一的长度，得到差值，让长链表（链表二）先走这个差值的步数，再开始链表一和链表二同时遍历，当两者相遇就是交点。
     */
    public static Node noLoop(Node head1, Node head2) {
        Node tailHead1 = head1;  //找到head1尾节点
        Node tailHead2 = head2;  //找到head2尾节点
        int head1Length = 0;
        int head2Length = 0;  //计算长链

        ////////////////用来计算两条链的长度
        while (tailHead1 != null) {
            head1Length++;
            tailHead1 = tailHead2.next;
        }
        while (tailHead2 != null) {
            head2Length++;
            tailHead2 = tailHead2.next;
        }
        /////////////////
        // 尾节点不相同，那肯定不相交。（因为拓扑结构为Y）
        if (tailHead1 != tailHead2) {
            return null;
        }
        // 经过这一步将tailHead1设为长链，tailHead2设为短链
        tailHead1 = head1Length > head2Length ? head1 : head2;  //找到长链，不浪费无用的节点
        tailHead2 = tailHead1 == head1 ? head2 : head1;  // tailHead2的值和上面的相反。


        int count = Math.abs(head2Length - head1Length);  //计算长链要先走几步
        // 长链从头开始走，因为长链比较长，需要多几步才能到底。注意，是长链！！！！！
        while (count > 0) {
            count--;
            tailHead1 = tailHead1.next;
        }
        while (tailHead1 != tailHead2) {  // 两者相遇的时候就是交点
            tailHead1 = tailHead1.next;
            tailHead2 = tailHead2.next;
        }
        return tailHead1;
    }

    public static void main(String[] args) {
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);
        head1.next.next.next.next.next.next = head1.next.next.next; // 7->4
        Node loopWithSet = getLoopWithSet(head1);
        System.out.println(loopWithSet.value);

    }
}
