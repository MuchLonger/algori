package codeBook.code_2_LinkArray;

/**
 * @description: 反转部分单链表：传入from和to表示分反转哪一部分。解法：找到from的前一个节点（fPre），to的后一个节点（tPos），将这一部分的节点反转，然后fPre的next等于反转的节点，反转后的节点等于tPos
 * 需要考虑是否会换head节点，
 * 1->3->2  ==》from 1 to 2 ==》 3 -> 1 -> 2 就需要将1（反转的最后一个节点）放到头结点上
 * @Time: 2019/12/5 16:09
 */
public class _5_ReversePartLinkArray {

    public static Node reversePartLinkArray(Node head, int from, int to) {  // 从1开始
        if (head == null || from > to || from < 1)  // 隐式条件 to>=1
            throw new RuntimeException("参数出错");
        int len = 0;  // 从0开始
        Node cur = head;
        Node fPre = null; //整个反转链表 之前的那个节点
        Node tPos = null; //整个反转链表 之后的那个节点
        while (cur != null) {
            len++;
            if (len == from - 1)  //获取整个反转链表 之前的那个节点
                fPre = cur;
            if (len == to + 1)  //获得整个反转链表 之后的那个节点
                tPos = cur;
            cur = cur.next;
        }
        if (from > to || from < 1 || to > len) {
            return head;
        }
        cur = fPre == null ? head : fPre.next; //为空就代表需要更换head，不为空就指向 反转链表的开头
        Node startReverse = cur.next;   // 从这个节点开始颠倒这个链表（因为cur.next需要指向 原链表 的to之后那个节点）
        cur.next = tPos;        // 这一步的意思：因为反转后的链表 原本反转前链表的第一个节点就会指向tPos（指向颠倒后的后一个节点），所以要将cur.next指向tPos
        Node next = null;
        while (startReverse != tPos) {  // 从startReverse开始，到tPos为止
            next = startReverse.next;
            startReverse.next = cur;  // 使用cur作为pre节点
            cur = startReverse;   // 保存startReverse为pre节点
            startReverse = next;
        }
        if (fPre != null) {  // 不是反转头结点的时候就将 fPre的next指回cur（连回颠倒后的链表） 并返回head
            fPre.next = cur;
            return head;
        }
        return cur;  //当反转头结点的时候，返回cur
    }
    public static void main(String[] args){
        Node n1 = new Node(1);
        n1.next = new Node(2);
        n1.next.next = new Node(3);
        n1.next.next.next = new Node(4);
        n1.next.next.next.next = new Node(5);
        _2_RemoveLastLinkArray.printLinkedList(reversePartLinkArray(n1,1,2));

    }
}
