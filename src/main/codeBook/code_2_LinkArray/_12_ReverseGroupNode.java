package codeBook.code_2_LinkArray;

import java.util.Stack;

/**
 * @description: 以K为一组，反转每一组链表的节点，不足一组的不反转。
 * 如：1 2 3 4 5 6 7,K=3  =======>  3 2 1 6 5 4 7
 * @Time: 2019/12/10 13:59
 */
public class _12_ReverseGroupNode {
    /**
     * 使用栈来反转，栈内加入K个元素，将K个元素反转。反转后尾节点需要再连入原链表
     */
    public static Node reverseKNodesWithStack(Node head, int K) {
        if (K < 2) {
            return head;  // 小于2不用反转
        }
        Stack<Node> stack = new Stack<>();
        Node newHead = head;
        Node cur = head;
        Node pre = null;  // 用来记录 一组链表 的“前一个节点”  1 234 5中的1
        Node next = null; // 用来记录 一组链表 的“后一个节点”  1 234 5中的5
        while (cur != null) {
            stack.push(cur);
            next = cur.next;   // 记录每一组反转的后一个节点，用来将反转后的节点指向它
            if (stack.size() == K) {
                pre = resign1(stack, pre, next);  // 返回的是反转后的链表的最后一个节点
                newHead = newHead == head ? cur : newHead;  // 获得新的头结点，只需要第一次反转的时候获取行了。注意是cur不是pre
            }
            cur = next;  //使用next，不然在反转的时候，最后一个节点就转成null了
        }
        return newHead;
    }

    /**
     * 将栈内的数组翻转
     *
     * @param stack 栈
     * @param pre   用来记录 一组链表 的“前一个节点”  1 234 5中的1。用来指向反转后的表
     * @param next  用来记录 一组链表 的“后一个节点”  1 234 5中的5。用来被指
     * @return 返回反转链表的最后一个节点
     */
    private static Node resign1(Stack<Node> stack, Node pre, Node next) {
        Node cur = stack.pop();
        if (pre != null) {
            pre.next = cur;  // 指向反转后的链表，从而向前串联起来
        }
        Node n = null;
        while (!stack.isEmpty()) {
            n = stack.pop();
            cur.next = n;
            cur = n;  // 到达后一个节点，不然cur就不会动了
        }
        cur.next = next;  // 指向 后一个节点next，从而 向后串联起来
        return cur;
    }

    /**
     * 使用自身来反转。具体反转方法和 _5_ReversePartLinkArray 的方法挺像，就是找到反转链表的前一个节点接上反转链表，找到反转链表的后一个节点让反转链表接上后一个节点
     *
     * @param head
     * @param K
     * @return
     */
    public static Node reverseKNodesWithNode(Node head, int K) {
        if (K < 2) {
            return head;
        }
        Node cur = head;
        Node start = null;  // 反转链表的开头
        Node pre = null;  // 反转链表的前一个节点
        Node next = null; // 反转链表的后一个节点
        int count = 1;  //从1开始，因为head也包括了进去
        while (cur != null) {
            next = cur.next;
            if (count == K) {
                start = pre == null ? head : pre.next;  //链表开头，pre.next就是 下一个节点的开头。
                head = pre == null ? cur : head;  // 记录最开始的链表节点
                resign2(pre,start,cur,next);  // 逆序位置，分别对应 链上一个节点，链头节点，链尾节点，链下一个节点
                pre=start;  // start在经过上面的resign2反转之后就变成了最后一个节点，即 1 234 5的4。
                count=0;
            }
            count++;
            cur=next;
        }
        return head;
    }

    /**
     *
     * @param pre
     * @param start
     * @param end
     * @param next
     */
    private static void resign2(Node pre, Node start, Node end, Node next) {
        Node p=start;
        Node c=start.next;
        Node n=null;
        while(c!=next){  // 以下就是反转链表的常规操作。
            n=c.next;
            c.next=p;  // 因为c是start.next，所以start.next的上一个就是start ==》即p
            p=c;
            c=n;
        }
        if(pre!=null){
            pre.next=end;  // end会被反转到pre的后面，所以使用pre.next=end
        }
        start.next=next;  // start自然就被反转到最后，所以start.next=next;
    }

    public static void main(String[] args) {
        Node n1 = new Node(1);
        n1.next = new Node(2);
        n1.next.next = new Node(3);
        n1.next.next.next = new Node(4);
        n1.next.next.next.next = new Node(5);
        _2_RemoveLastLinkArray.printLinkedList(reverseKNodesWithNode(n1, 2));
    }
}
