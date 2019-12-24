package codeBook.code_2_LinkArray;

/**
 * @description: 反转单向链表和双向链表，谨记：反转是以自身节点为中心，将自身节点的下个指向前一个，前一个指向自身
 * 1 -> 2 -> 3  head=1：next=head.next,head.next=pre（当前节点会指向上一个）,pre=head,head=next
 * 1 <- 2 <- 3
 * @Time: 2019/12/5 15:31
 */
public class _4_ReverseLinkArray {
    public static Node reverseSingleLink(Node head) {
        if (head == null) {
            throw new RuntimeException("参数出错");
        }
        Node pre = null;
        Node next = null;
        while (head != null) {
            next = head.next;  //记录下一个
            head.next = pre;  //自身下一个变成pre
            pre = head;  //后面会next，自身自然就变成下一个了
            head = next;  //向下
        }
        return pre;  //注意 返回的是pre（以它开头了）
    }

    public static DoubleNode reverseDoubleLink(DoubleNode dn){
        if(dn==null)
            throw new RuntimeException("参数出错");
        DoubleNode pre=null;
        DoubleNode next=null;
        while (dn != null) {  // 形式和上面的大致相同
            next=dn.next;
            dn.next=pre;
            dn.pre=next;
            pre=dn;
            dn=next;
        }
        return pre;
    }

    public static void main(String[] args) {
        Node n1 = new Node(1);
        n1.next = new Node(2);
        n1.next.next = new Node(3);
        n1.next.next.next = new Node(4);
        n1.next.next.next.next = new Node(5);

        DoubleNode dn=new DoubleNode(3);
        dn.next=new DoubleNode(2);
        dn.next.pre=dn;
        dn.next.next=new DoubleNode(5);
        dn.next.next.pre=dn.next.next;
        _2_RemoveLastLinkArray.printLinkedList(reverseSingleLink(n1));
        _2_RemoveLastLinkArray.printLinkedList(reverseDoubleLink(dn));
    }
}
