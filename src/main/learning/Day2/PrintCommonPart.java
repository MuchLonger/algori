package learning.Day2;

/**
 * @description: 输出两个 有序 链表的公共部分 O(N)
 *
 * 借用归并排序思想，两个有序数组找到相等部分
 * 如果链表1比链表2大，链表2就next
 * 如果链表1比链表2小，链表1就next
 * 如果两个相等，输出并两个同时next
 *
 * 实例：
 * 1 7 8 9 22 | 2 4 7 8 30
 * 1比2小，左 1移到7；7比2大，右 2移到4，以此类推
 *
 * @Time: 2019/10/28 21:32
 */
public class PrintCommonPart {
    public static class Node{
        public int value;
        public Node next;
        public Node(int data){
            this.value=data;
        }
    }

    /**
     * 左大于右，右next。左小于右，左next。相等输出并同时移动
     * @param head1 左有序链表
     * @param head2 右有序链表
     */
    public static void printCommonPart(Node head1,Node head2){
        while(head1 !=null && head2!=null){
            if(head1.value<head2.value){
                head1=head1.next;
            }else if(head1.value>head2.value){
                head2=head2.next;
            }else{
                System.out.print(head1.value+" ");
                head1=head1.next;
                head2=head2.next;
            }
        }
    }

    public static void printLinkedList(Node node) {
        System.out.print("Linked List: ");
        while (node != null) {
            System.out.print(node.value + " ");
            node = node.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node node1 = new Node(2);
        node1.next = new Node(3);
        node1.next.next = new Node(5);
        node1.next.next.next = new Node(6);

        Node node2 = new Node(1);
        node2.next = new Node(2);
        node2.next.next = new Node(5);
        node2.next.next.next = new Node(7);
        node2.next.next.next.next = new Node(8);

        printLinkedList(node1);
        printLinkedList(node2);
        printCommonPart(node1, node2);

    }
}
