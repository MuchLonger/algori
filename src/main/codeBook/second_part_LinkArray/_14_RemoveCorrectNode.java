package codeBook.second_part_LinkArray;

import java.util.Stack;

/**
 * @description: 删除指定值节点
 * @Time: 2019/12/11 11:41
 */
public class _14_RemoveCorrectNode {
    /**
     * 使用栈来删除重复节点，如果遇到指定值就不连一起。注意栈是先进后出，所以要向前连接链表（可以使用List，单纯想练习）。
     */
    public static Node removeCorrectNodeWithStack(Node head, int num) {
        Stack<Node> stack = new Stack<>();
        Node cur = head;
        while (cur != null) {
            stack.add(cur);
            cur = cur.next;
        }
        Node pre=null;
        while (!stack.isEmpty()) {
            if (stack.peek().value == num) {
                stack.pop();
                continue;
            }
            stack.peek().next=pre;
            pre=stack.pop();
        }
        return pre;
    }

    /**
     * 直接遍历，遇到相同值的直接 .next=.next
     * @param head
     * @param num
     */
    public static Node removeCorrectNodeWithNode(Node head, int num) {
        // 需要考虑头结点就是要删除的值时的换头。以下这个while就是换head。
        while (head != null) {
            if (head.value != num) {
                break;
            }
            head=head.next;
        }

        // 删除也需要两个节点
        Node pre=head;  // pre是上一个节点的值，一个用来删除（pre.next=cur.next)==》这样就表示删除cur的值，
        Node cur=head;  // cur就是当前节点的值，
        while (cur != null) {
            if (cur.value == num) {
                pre.next=cur.next;
            }else{
                pre=cur;  //注意是pre=cur，这样就实现pre保存上一个节点
            }
            cur=cur.next;
        }
        return head;
    }

    public static void main(String[] args){
        Node n1 = new Node(1);
        n1.next = new Node(2);
        n1.next.next = new Node(2);
        n1.next.next.next = new Node(4);
        n1.next.next.next.next = new Node(5);
        n1.next.next.next.next.next = new Node(5);
        _2_RemoveLastLinkArray.printLinkedList(removeCorrectNodeWithNode(n1,5));
    }

}
