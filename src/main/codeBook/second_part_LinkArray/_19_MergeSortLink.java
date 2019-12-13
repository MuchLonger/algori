package codeBook.second_part_LinkArray;

/**
 * @description: 合并两个有序链表：  前提！！！一定是左链和右链比较
 * 1）归并，左链设为最小值开头，右链为大值。
 * 2）右链如果找到比左链更小的值就将 右链一个节点赋值给左链，然后跳到右链遍历，
 * 3）如果左链又有比右链更小的值，就跳到左链去遍历
 * 4）最后把剩余一条链整条复制到另一条先结束的链。（因为总有先结束的一条链）
 * @Time: 2019/12/12 11:37
 */
public class _19_MergeSortLink {

    public static Node mergeSortLink(Node n1, Node n2) {
        if (n1 == null || n2 == null)  // 如果有一个为空，就返回另外一个节点
            return n1 != null ? n1 : n2;

        Node head = n1.value < n2.value ? n1 : n2;  //取最小节点为首节点。返回的是这个（head），通过cur1修改
        Node cur1 = head == n1 ? n1 : n2;  // cur1为较小节点开始的链，如n1 1-10-4，n2 3-20-1。这样cur1就是1，cur2就是3。
        Node cur2 = head == n1 ? n2 : n1;  // cur2为较大节点开始的链，
        Node pre = null;  //记录上一个节点，这样找到 大于当前值A 的就直接使用 pre.next指向A ，A再指向下一个节点。从而实现
        Node next = null;
        while (cur1 != null && cur2 != null) {
            if (cur1.value <= cur2.value) {   // 如果左链小于右链，那自然是继续向后  n1 123  n2 356 这样不用管。只需找到pre节点，以及向后移动即可
                pre = cur1;
                cur1 = cur1.next;
            } else {  // 找到第一个大于左链的节点，n1 123 n2 256 ，就是2（左链3>右链2)，然后pre.next=2, 2.next=3（n1的)
                next = cur2.next; // 记录右链
                // 就是连接右链的操作
                pre.next = cur2;
                cur2.next = cur1;  // 记录连回左链，这里会有困惑，多看几次。之所以连会左链，是能让左链和右链继续比较，因为比较的一定是左右链

                pre = cur2;  // 记录当前节点
                cur2 = next;  // next是左链的，记录从左链开始找
            }
        }
        pre.next = cur1 == null ? cur2 : cur1;   //如果哪条链节点没连完，就直接指向它，因为没连完的后面一定是大于前面所有的值
        return head;
    }


    public static void main(String[] args) {
        Node n1 = new Node(1);
        n1.next = new Node(2);
        n1.next.next = new Node(2);
        n1.next.next.next = new Node(4);
        n1.next.next.next.next = new Node(5);
        n1.next.next.next.next.next = new Node(5);

        Node n2 = new Node(1);
        n2.next = new Node(2);
        n2.next.next = new Node(2);
        n2.next.next.next = new Node(4);
        n2.next.next.next.next = new Node(5);
        n2.next.next.next.next.next = new Node(5);

        _2_RemoveLastLinkArray.printLinkedList(mergeSortLink(n1,n2));
    }
}
