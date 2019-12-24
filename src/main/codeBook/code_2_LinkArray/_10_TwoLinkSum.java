package codeBook.code_2_LinkArray;

import java.util.Stack;

/**
 * @description: 假设链表的节点的值为0-9之间，那一个链表就表示一个值。求两个链表相加的值
 * @Time: 2019/12/9 13:46
 */
public class _10_TwoLinkSum {
    /**
     * 使用了栈来保存两个节点的值
     */
    public static Node twoLinkSum1(Node left, Node right) {
        Stack<Integer> s1 = new Stack<>();  //因为从尾部开始相加，所以需要栈来保存链表的值
        Stack<Integer> s2 = new Stack<>();
        while (left != null) {
            s1.push(left.value);
            left = left.next;
        }
        while (right != null) {
            s2.push(right.value);
            right = right.next;
        }

        int help = 0;  // l+r+进位，总数
        int carry = 0;  // 进位
        int l = 0;
        int r = 0;
        Node node = null;  //用来保存当前节点是哪个
        Node pre = null;  //因为组建链表需要从底部开始一直添加头部，所以需要pre节点。如： 开始 3，之后 1->3
        while (!s1.isEmpty() || !s2.isEmpty()) {
            l = s1.isEmpty() ? 0 : s1.pop();
            r = s2.isEmpty() ? 0 : s2.pop();
            help = l + r + carry; // 得到两个节点相加的总值
            pre = node;
            node = new Node(help % 10); // 构建取余10的余数，就是实际的值
            node.next = pre;  // 此时的node是新增的节点，就完成了如上的从底部开始添加头部
            carry = help / 10; // 计算进位
        }
        if (carry == 1) { // 加到开头还有一个进位，如999+1，头肯定等于1
            pre = node;
            node = new Node(1); // 头肯定等于1
            node.next = pre;
        }
        return node;
    }

    /**
     * 先反转两个数组，再计算两个节点的值（这个操作和上面一样，使用pre和next来接，node.next=pre），再反转回去
     */
    public static Node twoLinkSum2(Node left, Node right) {
        left = reverseList(left);
        right = reverseList(right);

        int help = 0;  // l+r+进位，总数
        int carry = 0;  // 进位
        int l = 0;  // 左节点的值
        int r = 0;  // 右节点的值

        Node leftNode = left;
        Node rightNode = right;  //分别等于逆序后的节点，使用这个来遍历。left和right用来再次反转

        Node node = null;
        Node pre = null;
        while (leftNode != null || rightNode != null) {
            l = leftNode == null ? 0 : leftNode.value;
            r = rightNode == null ? 0 : rightNode.value;
            help = l + r + carry;

            pre = node;
            node = new Node(help % 10);  // 剩下的操作都大同小异了
            node.next = pre;  //继续从尾向前

            carry = help / 10;
            leftNode = leftNode == null ? null : leftNode.next;  // 别忘了向后，因为栈的pop会清除所以不需要向后，但是这里手动向后，为null就可以不管（它会自动挂在前面）
            rightNode = rightNode == null ? null : rightNode.next;
        }
        if (carry == 1) {
            pre = node;
            node = new Node(1);
            node.next=pre;
        }
        reverseList(left);
        reverseList(right);
        return node;
    }

    private static Node reverseList(Node head) {
        Node pre = null;
        Node node = null;
        while (head != null) {
            node = head.next;
            head.next = pre;
            pre = head;
            head = node;
        }
        return pre;
    }

    public static void main(String[] args) {
        Node n1 = new Node(1);
        n1.next = new Node(2);
        n1.next.next = new Node(3);

        Node n2 = new Node(1);
        n2.next = new Node(2);
        n2.next.next = new Node(3);
        _2_RemoveLastLinkArray.printLinkedList(twoLinkSum1(n1, n2));
        _2_RemoveLastLinkArray.printLinkedList(twoLinkSum2(n1, n2));
    }

}
