package codeBook.code_2_LinkArray;

/**
 * @description: 单链表的选择排序
 * @Time: 2019/12/11 14:57
 */
public class _16_SelectSort {
    /**
     * 链表分成两部分，左边为排好序的，右边为未排序的。从右边找到最小值，然后删除（pre.next=cur.next），再将这个最小值放到左边
     * 分三步：1）在右边找到最小值并删除，2）将最小值放到左边上，3）继续遍历右边节点
     */
    public static Node selectSort(Node head) {
        Node tail = null;
        Node cur = head;
        Node smallPre = null;
        Node small = null;
        while (cur != null) {
            small = cur;    // 先将small的值设为cur
            smallPre = getSmallestPreNode(cur);   // 得到最小节点的上一个节点
            if (smallPre != null) {          // 如果存在这个节点，就将后面的那个节点（就是最小那个节点）删除掉！
                small = smallPre.next;     // pre.next就是最小值
                smallPre.next = small.next;
            }
            //   cur不用移动，因为它是用来遍历最小值的（当small被删除的后新的最小值就诞生了），
            // 当cur就是最小值时，使用cur.next移动（因为cur已被移到左边）
            cur = cur == small ? cur.next : cur;

            // 把右边被删除的节点（就是small节点）接到左边的tail节点上。
            if (tail == null) { // 分 是否是头结点 的情况
                head = small;
            } else {
                tail.next = small;
            }
            tail = small;  // tail移动
        }
        return head;
    }

    /**
     * 找到head之后最小的节点的前一个节点
     *
     * @param head
     * @return
     */
    private static Node getSmallestPreNode(Node head) {
        Node smallPre = null;  // 最小节点的前一个节点
        Node small = head;  // 最小节点
        Node pre = head;   // 用来记录当前节点的上一个节点（最后就是给smallPre赋值的）
        Node cur = head.next;  //从head下一个节点开始找（next）
        while (cur != null) {
            if (cur.value < small.value) {  // 如果找到更小的值，记录前一个节点，并重新设置最小的值
                small = cur;
                smallPre = pre;
            }
            pre = cur;  // 记录当前节点（就是下一个节点的前一个节点）
            cur = cur.next;
        }
        return smallPre;
    }

    public static void main(String[] args) {
        Node n1 = new Node(2);
        n1.next = new Node(3);
        n1.next.next = new Node(1);
        n1.next.next.next = new Node(5);
        n1.next.next.next.next = new Node(2);
        n1.next.next.next.next.next = new Node(4);
        _2_RemoveLastLinkArray.printLinkedList(selectSort(n1));
    }

}
