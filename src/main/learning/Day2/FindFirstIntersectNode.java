package learning.Day2;

import Util.Node;

import java.util.HashSet;

/**
 * @description: 单链表相交问题：分三步，也可拆成三步来问
 * 1）需要先判断两条链表是否有环，如果有就返回环的起始点：getLoopNode。两种方法：
 * 方法一：使用HashSet来判断：遍历一次链表，将遍历的Node先判断HashSet是否存在，如果存在就有环，并且这个存在的节点就是这个环的起始点，直接return它。如果不存在就放入。一直遍历到结束都没有return就无环。
 * 方法二：使用两个节点：快节点和慢节点，快节点一次走两步，慢节点一次走一步，当两者相遇（==）的时候，快节点回到head节点（即开头节点）并且变成一次走一步，当快节点再次与慢节点相遇（==）的时候就是两个节点环的起始点。（这个方法死记住！！）
 * <p>
 * 2）如果两条链表都无环（即两条链返回null）求是否相交：那拓扑图只有一种情况：Y，像这个Y一样汇合。也是两种方法：
 * 方法一：使用HashSet：将链表一所有Node放入Set中，再遍历链表二，使用contains方法，如果为true就有相交节点，直接return 当前节点。
 * 方法二：使用快慢节点：先遍历链表一得到链表一的长度和尾节点，再遍历链表二得到链表二的长度和尾节点。如果两个尾节点不相等，那肯定没有交点(Y尾节点肯定汇合)。
 * 如果相等，再判断链表一长还是链表二长。假设当前链表二长，那用链表二的长度减链表一的长度，得到差值，让长链表（链表二）先走这个差值的步数，再开始链表一和链表二同时遍历，当两者相遇就是交点。
 * <p>
 * 3）如果一条链表有环一条链表无环，答案是不相交，因为没有这样的结构（有环且相交，那另一条一定也有环）
 * <p>
 * 4）如果两条链表都有环（两条都不返回null）：那有三种拓扑图，1.（6 6 不相交），2.（R，使用快慢节点的方法），3.（\0/，两根都插都圆内）
 * 使用相交节点来判断是哪种情况，两条链表的相交节点相等就是第二种情况，否则就是第一或第三，再使用遍历相交就能甄别是1还是3
 * <p>
 * 先考虑第二种，方法和无环的一样，因为两者不在环上相交，那就是无环时候的相交，所以使用无环的快慢节点方法。
 * 1、3的情况：取一条链的相交节点一直next（往下遍历），如果撞到另一条链的相交节点就是第三种，否则就是第一种返回null。
 * @Time: 2019/10/30 21:56
 */
public class FindFirstIntersectNode {

    /**
     * 返回两条链表是否相交
     *
     * @param head1 链表1
     * @param head2 链表2
     * @return
     */
    public static Node getIntersectNode(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        Node loop1 = getLoopNode(head1);
        Node loop2 = getLoopNode(head2);
        if (loop1 == null && loop2 == null) {
            return noLoopNodeWithSet(head1, head2);
        }
        if (loop1 != null && loop2 != null) {
            return bothLoop(head1, loop1, head2, loop2);
        }
        return null;
    }

    private static Node bothLoop(Node head1, Node loop1, Node head2, Node loop2) {
        Node cur1 = null;
        Node cur2 = null;
        if (loop1 == loop2) { //第二种拓扑结构，使用快慢节点
            cur1 = head1;
            cur2 = head2;
            int n = 0;  //这次只使用一个数来表示谁是长链
//            记录长度和尾节点
            while (cur1 != loop1) {  //只需要到环之前的长度，因为不在环上相交，就能直接使用快慢节点
                n++;
                cur1 = cur1.next;
            }
            while (cur2 != loop2) {
                n--;
                cur2 = cur2.next;
            }
            //这次不用判断尾节点是否相同，因为后面还有个环，必定相同
            cur1 = n > 0 ? head1 : head2;  //获得长链，大于0代表head1长
            cur2 = cur1 == head1 ? head2 : head1;
            n = Math.abs(n);
            while (n != 0) {
                n--;
                cur1 = cur1.next;  //长链next
            }
            while (cur1 != cur2) {
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return cur2;
        } else {
            cur1 = loop1.next;  //从相交之后next
            while (cur1 != loop1) { //遍历一轮环
                if (cur1 == loop2) {  //如果相遇了链二的环起始点,就是第三种情况
                    return cur1;  //根据自定义，有两种结果，一是链一的交点，二是链二的交点，结果都正确。当前是链二的交点
                }
                cur1 = cur1.next;
            }
            return null;  //没遇上就是第一种，也就是没有交点
        }
    }

    /**
     * 两条无环链表判断是否相交，使用快慢节点
     * O（N）O（1）
     *
     * @param head1
     * @param head2
     * @return
     */
    private static Node noLoop(Node head1, Node head2) {
        Node cur1 = head1;  //求head1的尾节点
        Node cur2 = head2;  //求head2的尾节点
        int head1Length = 0;   //判断head1和head2谁是长链
        int head2Length = 0;   //判断head1和head2谁是长链
        while (cur1 != null) {
            head1Length++;
            cur1 = cur1.next;
        }
        while (cur2 != null) {
            head2Length++;
            cur2 = cur2.next;
        }
//        尾节点不相等直接返回空
        if (cur1 != cur2) {
            return null;
        }
        cur1 = head1Length > head2Length ? head1 : head2; //cur1保存长链
        cur2 = cur1 == head1 ? head2 : head1; //与cur1相反
        int count = Math.abs(head1Length - head2Length);
        while (count > 0) {  //这一步是因为要保证两条链节点一致的情况下再比较 两节点是否一致，否则如果长链提前结束可能还没找到就结束了
            count--;
            cur1 = cur1.next;
        }
        while (cur1 != cur2) {
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur1;
    }

    /**
     * 判断是否为环并返回环开始节点，使用快慢节点
     * O（N） O（1）
     *
     * @param head
     * @return 环开始节点
     */
    private static Node getLoopNode(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;  //注意范围，以上都形不成环
        }
        Node slow = head.next;
        Node fast = head.next.next;
        while (slow != fast) {
            if (fast.next == null || fast.next.next == null) {
                return null;  //注意判空，快节点的往下为空直接没有环
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        //回到起始点
        fast = head;
        while (slow != fast) {
            //两个都是一步一步走，撞上了就是环的起始点（记住这个结论）
            slow = slow.next;
            fast = fast.next;
        }
        return slow; //返回哪个都行
    }

    /**
     * 判断是否为环并返回环开始节点，使用Set的方式
     * O（N） O（N）
     *
     * @param head
     * @return 环开始节点
     */
    private static Node getLoopNodeWithSet(Node head) {
        HashSet<Node> set = new HashSet<>();
        while (head.next != null) {
            if (set.contains(head)) {
                System.out.println("get it");
                return head;
            }
            set.add(head);
            head = head.next;
        }
        return null;
    }

    /**
     * 两条无环链表判断是否相交，使用set来实现
     * O(N) O(N)
     *
     * @param head1
     * @param head2
     * @return
     */
    private static Node noLoopNodeWithSet(Node head1, Node head2) {
        HashSet<Node> set = new HashSet<>();
        while (head1.next != null) {
            set.add(head1);
            head1 = head1.next;
        }
        while (head2.next != null) {
            if (set.contains(head2)) {
                return head2;
            }
            head2 = head2.next;
        }
        return null;
    }


    public static void main(String[] args) {
        // 1->2->3->4->5->6->7->null
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);

        // 0->9->8->6->7->null
        Node head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).value);

        // 1->2->3->4->5->6->7->4...
        head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);
        head1.next.next.next.next.next.next = head1.next.next.next; // 7->4

        // 0->9->8->2...
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next; // 8->2
        System.out.println(getIntersectNode(head1, head2).value);

        // 0->9->8->6->4->5->6..
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).value);

    }
}
