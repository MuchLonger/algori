package leecode.firstPart;

/**
 * @description: 2.两数相加
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * 示例：
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 * @Time: 2019/11/20 11:02
 */
public class _2_addTwoNumbers {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {

        }

        ListNode(int x) {
            val = x;
        }
    }

    /**
     * 并没有通过
     */
    public static ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        StringBuilder sbl1 = new StringBuilder();
        StringBuilder sbl2 = new StringBuilder();
        ListNode next = null;
        ListNode pre1 = null;
        while (l1 != null) {
            next = l1.next;
            l1.next = pre1;
            pre1 = l1;
            l1 = next;
        }
        next = null;
        ListNode pre2 = null;
        while (l2 != null) {
            next = l2.next;
            l2.next = pre2;
            pre2 = l2;
            l2 = next;
        }
        while (pre1 != null) {
            sbl1.append(pre1.val);
            pre1 = pre1.next;
        }
        while (pre2 != null) {
            sbl2.append(pre2.val);
            pre2 = pre2.next;
        }
        Long l1Int = Long.valueOf(sbl1.toString().equals("") ? "" : sbl1.toString());
        Long l2Int = Long.valueOf(sbl2.toString().equals("") ? "" : sbl2.toString());
        Long result = l1Int + l2Int;
        char[] chars = result.toString().toCharArray();
        ListNode node = new ListNode();
        ListNode head = node;
        for (int i = 0; i < chars.length; i++) {
            if (node != null) {
                node.val = Integer.valueOf(String.valueOf(chars[i]));
                node.next = i < chars.length - 1 ? new ListNode() : null;
                node = node.next;
            }
        }

        ListNode pre3 = null;
        next = null;
        while (head != null) {
            next = head.next;
            head.next = pre3;
            pre3 = head;
            head = next;
        }
        return pre3;
    }

    public static void printLinkedList(ListNode head) {
        System.out.print("Linked List: ");
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }

    /**
     * 使用进位的方法，就不用担心数值过大
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = null;
        ListNode left = l1;
        ListNode right = l2;
        // 进位
        int carry = 0;
        while (left != null || right != null) { // 或：即使一方为空 也会一直加下去
            int val = (left == null ? 0 : left.val) + (right == null ? 0 : right.val) + carry; //两数相加加上进位
            carry = val >= 10 ? 1 : 0;  // 获得进位
            val = val % 10;  // 得到进位后需要-10
            result = (left == null ? right : left);  //result解决了一方为空的情况以及 表示构建的新链
            result.val = val;                //保存值
            // 两个指针都向后
            left = (left == null) ? null : left.next;
            right = (right == null) ? null : right.next;

            result.next = (left == null ? right : left);
        }
        if (carry > 0) {  // 如果到最后都还有进位，直接next加，因为是逆序的
            result.next = new ListNode(1);
        }
        return l1;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        ListNode l2 = new ListNode(5);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);
        printLinkedList(addTwoNumbers(l1, l2));

    }

}
