package codeBook.second_part_LinkArray;

import Util.MathUitl;

/**
 * @description: 链表的荷兰国旗算法：
 * <p>
 * 进阶：保证数组里面的数使用荷兰国旗算法之后的大于、小于、等于区域相对顺序还是一一致，如 原数组大于区域是 4 8 5，荷兰国旗后还是4 8 5
 * @Time: 2019/12/6 11:57
 */
public class _8_LinkListPartition {
    public static Node linkListPartition(Node head, int pivot) {
        if (head == null) {
            throw new RuntimeException("参数出错");
        }
        Node cur = head;
        int length = 0;
        while (cur != null) {
            cur = cur.next;
            length++; // 得到链表长度，用来新建数组
        }
        Node[] nodes = new Node[length];
        cur = head;  // 再遍历一次
        for (length = 0; length < nodes.length; length++) {
            nodes[length] = cur;
            cur = cur.next;
        }
        helFlag(nodes, pivot);  // 给数组进行荷兰国旗算法
        // 修改后再重新连上
        for (length = 1; length < nodes.length; length++) {
            nodes[length - 1].next = nodes[length];   //倒着连，省一个空间
        }
        nodes[length - 1].next = null;  // 取出尾节点的连接

        return nodes[0];
    }

    private static void helFlag(Node[] nodes, int pivot) {
        int start = -1;
        int end = nodes.length;
        int cur=0;
        while (start != end) {
            if(nodes[cur].value<pivot){
                swap(++start,cur++,nodes);
            }else if(nodes[cur].value>pivot){
                swap(--end,cur,nodes);
            }else{
                cur++;
            }
        }
    }
    public static void swap(int left,int right,Node[] nodes){
        Node temp=nodes[left];
        nodes[left]=nodes[right];
        nodes[right]=temp;
    }
    public static void main(String[] args){
        Node n1 = new Node(7);
        n1.next = new Node(4);
        n1.next.next = new Node(2);
        n1.next.next.next = new Node(35);
        n1.next.next.next.next = new Node(12);
        _2_RemoveLastLinkArray.printLinkedList(linkListPartition(n1,10));
    }
}
