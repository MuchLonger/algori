package learning.Day2;

import Util.ArrUtil;
import Util.Node;

/**
 * @description: 荷兰国旗的链表形式，给定一个数，将链表划分为小于、等于、大于这个数
 * 方法一（O(N)，O(N)）：遍历一次链表，并将链表内的值放入数组中，再以数组的方式求荷兰国旗问题。
 * 方法二（O(N)，O(1)）：准备六个Node，分别是小于区的头，小于区的尾....，遍历Node找到小于的，等于的，大于的 分别放入这些头.next中，最后尾部相连即可
 * @Time: 2019/10/29 21:15
 */
public class HelFlagLinkList {
    // 方法一解法
    public static Node listPartition1(Node head, int pivot) {
        if (head == null)
            return head;
        Node cur = head;

//        下面用来求出链表长度
        int i = 0;
        while (cur != null) {
            i++;
            cur = cur.next;
        }
        Node[] nodeArr = new Node[i];

//        将链表放入数组，使用for循环
        i = 0;
        cur = head;
        for (i = 0; i < nodeArr.length; i++) {
            nodeArr[i] = cur;
            cur = cur.next;
        }

//        调用数组的荷兰国旗求法
        arrPartition(nodeArr, pivot);

        //再将处理好的Node放回到数组内
        for (i = 1; i < nodeArr.length; i++) {
            nodeArr[i - 1].next = nodeArr[i]; //省下一个变量，直接以arr[0]为链表head节点
        }
        nodeArr[i - 1].next = null;
        return nodeArr[0];
    }

    //    方法二，构建六个节点，分成三块，遍历head分别将小于的、等于的、大于的串到对应的节点上
    //    难点在于两个节点的连接，如何将两个节点指向同一条链（直接使用=的方式，因为指向的是内存）
    public static Node listPartition2(Node head, int pivot) {
        Node smallHeadStart = null;
        Node smallHeadEnd = null;

        Node eqHeadStart = null;
        Node eqHeadEnd = null;

        Node bigHeadStart = null;
        Node bigHeadEnd = null;

        Node next = null;  //保存head的下一个值
        while (head != null) {
            next = head.next;
            head.next = null;  //将head置空，这样Start节点就能直接赋值存进去（如果不置空会把一串都存进去）
            if (head.value < pivot) {
                if (smallHeadStart == null) {
                    smallHeadStart = head;  // 两个Node同时指向了head，就代表两个同时指向了一个内存位置,
                    smallHeadEnd = head;    // 所以改变head,SHS,SHE（缩写）任何一个都会导致全部改变值。
                } else {
                    smallHeadEnd.next = head;  //头结点继续连，因为smallHeadStart和smallHeadEnd指向的地址是一个地址，所以不管谁next都一样。但是这个head就不一样了，head是"重新.next生成的"所以不会与这两个值相连
                    smallHeadEnd = head;  //将尾节点指为smallHeadStart最后一个节点（用来表示同一条链）
//                    通过上面smallHeadStart就是一整条链的开头，smallHeadEnd就是一整条链的结尾
                }
            } else if (head.value == pivot) {
                if (eqHeadStart == null) {
                    eqHeadStart = head;
                    eqHeadEnd = head;
                } else {
                    eqHeadEnd.next = head;
                    eqHeadEnd = head;  //将尾节点一直置空
                }
            } else {
                if (bigHeadStart == null) {
                    bigHeadStart = head;
                    bigHeadEnd = head;
                } else {
                    bigHeadEnd.next = head;
                    bigHeadEnd = head;
                }
            }
            head = next;
        }
        //把小于和等于的连接起来
        if (smallHeadEnd != null) {
            smallHeadEnd.next = eqHeadStart;
//            如果eq为空，就设为小于的结尾，否则就是等于的结尾
            eqHeadEnd = eqHeadEnd == null ? smallHeadEnd : eqHeadEnd;
        }
        //把大于的连接
        if (eqHeadEnd != null) {
            eqHeadEnd.next = bigHeadStart;
        }
//        如果小于区域为空就从等于区域开始，如果还为空就从大于区域开始
        return smallHeadStart != null ? smallHeadStart : eqHeadStart != null ? eqHeadStart : bigHeadStart;
    }


    public static void arrPartition(Node[] arr, int pivot) {
        int index = 0;
        int start = -1;
        int end = arr.length;
        while (index != end) {
            if (arr[index].value < pivot) {
                swap(index++, ++start, arr);
            } else if (arr[index].value == pivot) {
                index++;
            } else {
                swap(index, --end, arr);
            }
        }
    }

    public static void swap(int n1, int n2, Node[] nodes) {
        Node tempNode = nodes[n1];
        nodes[n1] = nodes[n2];
        nodes[n2] = tempNode;

    }

    public static void main(String[] args) {

        Node head1 = new Node(7);
        head1.next = new Node(9);
        head1.next.next = new Node(1);
        head1.next.next.next = new Node(8);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(2);
        head1.next.next.next.next.next.next = new Node(5);
        ArrUtil.printLinkedList(head1);
//        head1 = listPartition1(head1, 4);
        head1 = listPartition2(head1, 5);
        ArrUtil.printLinkedList(head1);
    }
}
