package codeBook.code_2_LinkArray;

/**
 * @description:
 * 1）删除链表中间的节点：1 2删除1；1 2 3 删除2；1 2 3 4删除2；
 * 答：删除就需要找到pre节点和next节点。解法：一个节点走一步，一个节点走两步
 *
 * 2）删除链表的第a/b个节点：如 3个节点的 a=1 b=2，取向上取整的值，即删除第2位置 ==> 3*(1/2)向上取整
 * 答：同上，删除需要找到pre节点和next节点。解法：和第2题差不多，第二题是倒数，这一题是顺数
 * @Time: 2019/12/5 9:40
 */
public class _3_RemoveAnyWayNode {
    public static Node removeMidNode(Node head) {
        if (head == null) {
            throw new RuntimeException("参数出错");
        }
        if (head.next.next == null) {
            return head.next;  // 1 2 删除1
        }
        Node pre = head;  // 走一步
        Node cur = head.next.next;  //走两步
        while (cur.next != null && cur.next.next != null) {  //一是判空，二是这样停止的位置就是中心的前一个节点
            pre = pre.next;
            cur = cur.next.next;
        }
        pre.next = pre.next.next;  // 前一个节点指向后一个节点 实现删除
        return head;
    }

    /**
     * 删除a/b位置的节点（就是删除任意位置的节点）
     *
     * @return
     */
    public static Node removeByRatio(Node head, int a, int b) {
        if (head == null || a < 1 || a > b) {  // 隐式条件 b>0，不能超过1，也不能小于1
            throw new RuntimeException("参数出错");
        }
        int K = 0; // 记录链表长度，连着下面的while
        Node cur = head;
        while (cur != null) {
            cur = cur.next;
            K++;
        }
        int ratio = (int) Math.ceil(((double)a /(double) b) * K); //注意转型成double
        if (ratio == 1) {
            head = head.next;    // 删除第一个节点只需要head=head.next
        }
        if (ratio > 1) { // 至少是第1个节点开始
            cur = head;  // 从头开始
            while (--ratio != 1) {  // 注意边界先--，要找到的是那个节点的前一个节点
                cur=cur.next;
            }
            cur.next=cur.next.next;
        }
        return head;
    }

    public static void main(String[] args){
        Node n1 = new Node(1);
        n1.next = new Node(2);
        n1.next.next = new Node(3);
        n1.next.next.next = new Node(4);
        n1.next.next.next.next = new Node(5);

        Node n2 = new Node(2);
        n2.next = new Node(4);
        n2.next.next = new Node(5);
        n2.next.next.next = new Node(6);
        n2.next.next.next.next = new Node(7);
        _2_RemoveLastLinkArray.printLinkedList(removeMidNode(n1));
        _2_RemoveLastLinkArray.printLinkedList(removeByRatio(n2,2,3));

    }
}
