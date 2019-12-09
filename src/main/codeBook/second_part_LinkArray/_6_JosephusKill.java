package codeBook.second_part_LinkArray;

/**
 * @description: 约瑟夫环，1-n形成一个环，报数到k的就出列同时k置0,只剩一个节点时就结束。
 * 解法：last追head，如果追到就是最后的那个节点。没追到就向前一步，这里的向前一步是last.next然后head.next=last，这样就能一起向前
 * @Time: 2019/12/5 16:50
 */
public class _6_JosephusKill {
    public Node josephusKill(Node head, int k) {
        if (head == null || head.next == head || k < 1) { //不为空，不为空环，k不小于1
            throw new RuntimeException("参数出错");
        }
        Node last = head;  //找到整个环的最后一个节点

        while (last != head) {  //先找到
            last = last.next;
        }
        int count = 0;
        while (head != last) {  // 当两者相等的时候就只剩一个节点了。整个过程就是last追head的过程。（画个图就明了）
            if (++count == k) {  //如果报数到了对应的k
                last.next = head.next;  // 相当于删除了head （last.next就是上一个节点，head.next就是下一个节点）。这样删除可以保证依旧是个环
                count = 0; // 置零
            } else {
                last = last.next;  // 否则last向后
            }
            head = last.next;  // head会同步的到last之后（画个图就很清晰）
        }

        return head;  // 仅剩的一个节点
    }
}
