package Day2;

import teacherCode.class_03.Code_07_ReverseList;

/**
 * @description: 反转单向链表和双向链表（不理解）
 * @Time: 2019/10/27 17:48
 */
public class ReverseList {
    public static class Node{
        public int value;
        public Node next;
        public Node(int data){
            this.value=data;
        }
    }

    /**
     * 传入一个头节点，从头开始反转链表
     * 基本思想：头结点开始，将下一个节点放到头结点的左边（pre），
     * 之后下一个节点再找到下一个节点，头结点等于刚刚放到左边的头结点
     *
     * 将下一个节点保存下来，一直当做head节点，用来将其设置到pre内，
     *
     *
     * @param head
     * @return
     */
    public static Node reverseList(Node head){
        Node pre=null;
        Node next=null;
        while(head!=null){    //
            next=head.next;   // 保存head的下一个Node，
            head.next=pre;    // 将下一个Node指向前一个
            pre=head;         // 将pre设 作新的头结点
            head=next;        // 当前的头结点变成下一个Node
        }
        return pre;
    }
    public static void printLinkedList(Node head) {
        System.out.print("Linked List: ");
        while (head != null) {
            System.out.print(head.value + " ");
            head = head.next;
        }
        System.out.println();
    }
    public static void main(String[] args){
        ReverseList.Node head1 = new ReverseList.Node(1);
        head1.next = new ReverseList.Node(2);
        head1.next.next = new ReverseList.Node(3);
        printLinkedList(head1);
        head1 = reverseList(head1);
        printLinkedList(head1);
    }
}
