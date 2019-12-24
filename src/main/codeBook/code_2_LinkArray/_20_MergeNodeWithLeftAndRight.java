package codeBook.code_2_LinkArray;

/**
 * @description: 将一个链表划分为左右半区然后一个分一个组合一起，
 * 1-2-3-4，左半区为12 右半区为34，组合成  1324。
 * 1 2 3 4 5，左半区为12，右半区为345。组成  13245.
 * @Time: 2019/12/13 9:46
 */
public class _20_MergeNodeWithLeftAndRight {

    /**
     * 重组链表
     */
    public static void relocate(Node head) {
        if (head == null || head.next == null) {
            return;
        }
        Node mid = head;  // 找到中点，以此来找到左右半区
        Node right = head.next;  // 先next，这样能保证奇数时左边为n/2，右边为 n+1/2
        while (right.next != null && right.next.next != null) {
            mid = mid.next;
            right = right.next.next;
        }
        right = mid.next;  // 右半区起始点
        mid.next = null;  // 左半区置空。
        mergeLR(head, right);
    }

    /**
     * 整合两个节点
     * @param left  左半区起始点
     * @param right 右半区起始点
     */
    private  static void mergeLR(Node left, Node right) {
        Node leftNext = null;
        Node RightNext=null;
        while (left.next!=null) {
            leftNext = left.next;  // 保存左链节点
            RightNext=right.next;  // 保存右链的下一个节点

            left.next = right;  // 将右链连到左链
            right.next = leftNext;

            left = leftNext;  // 移动
            right = RightNext;  // 移动
        }
        left.next=right;  // 注意最后剩下的都连在一起
    }

    public static void main(String[] args) {
        Node n1 = new Node(1);
        n1.next = new Node(2);
        n1.next.next = new Node(3);
        n1.next.next.next = new Node(4);
        n1.next.next.next.next = new Node(5);
        n1.next.next.next.next.next = new Node(6);
        n1.next.next.next.next.next.next = new Node(7);
        relocate(n1);
        _2_RemoveLastLinkArray.printLinkedList(n1);
    }
}
