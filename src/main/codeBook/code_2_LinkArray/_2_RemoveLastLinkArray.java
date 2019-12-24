package codeBook.code_2_LinkArray;

/**
 * @description: 删除单链表中倒数第K位的节点和双链表中倒数第K位的节点：假设链表长度为N，倒数第K位，就是顺数的N-K。此外删除就是前一个节点指向后一节点
 * @Time: 2019/12/4 20:29
 */
public class _2_RemoveLastLinkArray {

    public static  Node removeLastSingleLinkArray(Node node, int K) {
        if (node == null || K < 1) {
            throw new RuntimeException("参数出错");
        }
        Node cur = node;
        while (cur != null) { // 一直遍历到底
            K--;  // 得到K-N，K-N就是倒数的位置，比如5个数的列表倒数第3就是 -2，实际上就是第2个位置
            cur = cur.next;
        }
        if (K == 0) { //表示删除的节点是头结点
            node = node.next;
        }
        if (K < 0) {  // 不可能>0 因为上面走过了一整个链表，如果大于零就表示超过了链表长度，所以不用考虑
            cur = node;
            while (++K != 0) { // 使用++K，因为要先找到倒数第K节点的前一个节点位置，如果K++就是找到倒数第K节点的位置
                cur = cur.next;
            }
            cur.next = cur.next.next;  //因为是前一个节点，所以它后面至少一个节点，所以 .next.next 不用判空
        }
        return node; //返回的是头结点
    }

    public static DoubleNode removeLastDoubleLinkArray(DoubleNode head, int K) {
        if (head == null || K < 1)
            throw new RuntimeException("不合法的参数");
        DoubleNode cur = head;
        while (cur != null) {
            cur = cur.next;
            K--; // 老样子 得到K-N
        }
        if (K == 0) {
            head = head.next;
            head.pre = null;  // 将前一个置空
        }
        if (K < 0) {
            cur = head;
            while (++K != 0) { //依旧是先++，这样顺数出来的就是前一个节点。
                cur = cur.next;
            }
            DoubleNode nextNode = cur.next.next; // 找到当前节点的下下节点（下一个节点是删除的）
            if (nextNode != null) {   // 要判空
                nextNode.pre = cur;  // 将下下节点的pre改成当前
            }
            cur.next = nextNode;
        }
        return head;
    }

    public static void main(String[] args) {
        Node n1 = new Node(1);
        n1.next = new Node(2);
        n1.next.next = new Node(3);
        n1.next.next.next = new Node(4);
        n1.next.next.next.next = new Node(5);

        DoubleNode dn=new DoubleNode(3);
        dn.next=new DoubleNode(2);
        dn.next.pre=dn;
        dn.next.next=new DoubleNode(5);
        dn.next.next.pre=dn.next.next;

        printLinkedList(removeLastSingleLinkArray(n1,1));
        printLinkedList(removeLastDoubleLinkArray(dn,1));
    }

    public static void printLinkedList(Node node) {
        System.out.print("Linked List: ");
        while (node != null) {
            System.out.print(node.value + " ");
            node = node.next;
        }
        System.out.println();
    }
    public static void printLinkedList(DoubleNode node) {
        System.out.print("Linked List: ");
        while (node != null) {
            System.out.print(node.value + " ");
            node = node.next;
        }
        System.out.println();
    }
}
