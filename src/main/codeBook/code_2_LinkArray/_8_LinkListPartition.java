package codeBook.code_2_LinkArray;

/**
 * @description: 链表的荷兰国旗算法：使用一个数组保存，之后再使用数组的荷兰国旗算法，最后再拼回变成Node
 * <p>
 * 进阶：保证数组里面的数使用荷兰国旗算法之后的大于、小于、等于区域相对顺序还是一一致，如 原数组大于区域是 4 8 5，荷兰国旗后还是4 8 5
 * @Time: 2019/12/6 11:57
 */
public class _8_LinkListPartition {
    public static Node linkListPartition1(Node head, int pivot) {
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
        length = 0;
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
        int cur = 0;
        while (cur != end) {
            if (nodes[cur].value < pivot) {
                swap(++start, cur++, nodes);
            } else if (nodes[cur].value > pivot) {
                swap(--end, cur, nodes);
            } else {
                cur++;
            }
        }
    }

    public static void swap(int left, int right, Node[] nodes) {
        Node temp = nodes[left];
        nodes[left] = nodes[right];
        nodes[right] = temp;
    }

    public static Node linkListPartition2(Node head, int pivot) {
        Node sH = null;  // 小头
        Node sT = null;  // 小尾
        Node mH = null;  // 中头
        Node mT = null;  // 中尾
        Node bH = null;  // 大头
        Node bT = null;  // 大尾

        Node next = null;  // 记录节点
        while (head != null) {  // 遍历头结点,找到小于，等于，大于的链，同时记录上面的三个头尾节点
            next = head.next;
            head.next = null;   // 防止整一条链都放过去，需要置空

            if (head.value < pivot) {
                if (sH == null) {  // 分情况，如果链的头等于空就初始化头尾节点
                    sH = head;
                    sT = head;
                } else { // 设置头结点和尾节点
                    sT.next = head;  // sH是固定住的（不会就找到不头结点了），而sT是 .next 来连：就能保证以sH开始，以sT来连接。
                    sT = head;  // 等于head，到最后结束的时候就是最后一个节点了
                }
            } else if (head.value == pivot) {   // 和上面大同小异
                if (mH == null) {
                    mH = head;
                    mT = head;
                } else {
                    mT.next = head;
                    mT = head;
                }
            } else {
                if (bH == null) {
                    bH = head;
                    bT = head;
                } else {
                    bT.next = head;
                    bT = head;
                }
            }


            head = next;   //  向后，！！！！！！！！！！！！！！注意head向后，向后的是保存的（因为上面变成null了！！！）
        }

        // 上面就划分成了三条链
        if (sT != null) {  // 如果小于的尾不为null，就将尾连到中头
            sT.next = mH;
            mT = mT == null ? sT : mT;// 注意，这里需要判断中间哪条链是不是为空，为空那中间的头尾都是“小尾”，否则才是中尾
        }
        if (mT != null) {
            mT.next = bH;  // 连上最后一条大于区域的链
        }
        return sH != null ? sH : mH != null ? mH : bH;   //最后需要判断所有区域的链是不是为空，小尾空就返回中，中为空就返回大，大为空那就是null


    }

    public static void main(String[] args) {
        Node n1 = new Node(7);
        n1.next = new Node(4);
        n1.next.next = new Node(2);
        n1.next.next.next = new Node(35);
        n1.next.next.next.next = new Node(12);
        _2_RemoveLastLinkArray.printLinkedList(linkListPartition2(n1, 10));
    }
}
