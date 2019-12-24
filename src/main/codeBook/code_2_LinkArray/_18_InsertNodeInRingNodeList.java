package codeBook.code_2_LinkArray;

/**
 * @description: 给定一个“有序”的环形单链表（尾节点连向头结点），再给定一个num。
 * 题目： 将num作为节点插入到有序环形链表
 * @Time: 2019/12/11 18:06
 */
public class _18_InsertNodeInRingNodeList {
    /**
     * 使用一个pre和cur节点，其中cur等于pre.next，两者紧靠着一起移动，
     * 1）当一个节点 大于pre且小于cur 就将值插入到pre后面。
     * 2）如果小于所有节点，就插入到 结尾.next，它就变成新的头结点（因为是环，所以只需要返回node就行了：返回的就是头结点）
     * 3）如果大于所有节点，就插入到 结尾.next，之后直接返回head即可
     * 4)如果head为null，则node就直接接入变成head，自成一环
     */
    public static Node insertNodeInRingNodeList(Node head, int num) {
        Node node = new Node(num);
        if (head == null) {
            node.next = node;  // 自成一环
            return node;
        }
        Node pre = head;
        Node cur = pre.next;
        while (cur != head) {
            if (pre.value <= num && cur.value >= num)  // 大于pre，小于cur。
                break;
            pre = cur;
            cur = cur.next;
        }
        pre.next = node;   //  不管如何都会执行这个。因为如果找不到“大于pre且小于cur”的就一定会到达最后一个节点（head的前一个），
        node.next = cur; // 于是不管是 跳出循环 还是 到达最底（head的前一个节点） 都是需要插入到 pre之后cur之前。
        return head.value > num ? node : head;  // 比所有的节点都小就返回node为头，否则就是大于所有节点返回head为头
    }

    public static void main(String[] args) {
        Node n1 = new Node(1);
        n1.next = new Node(2);
        n1.next.next = new Node(2);
        n1.next.next.next = new Node(4);
        n1.next.next.next.next = new Node(5);
        n1.next.next.next.next.next = new Node(5);
        n1.next.next.next.next.next.next = n1;
        _2_RemoveLastLinkArray.printLinkedList(insertNodeInRingNodeList(n1, 3));
    }
}
