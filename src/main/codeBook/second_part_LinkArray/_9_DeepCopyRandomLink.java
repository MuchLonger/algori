package codeBook.second_part_LinkArray;


import java.util.HashMap;
import java.util.Map;

/**
 * @description: 深拷贝一个包含随机指针的Link数组。（详细题目解释看 Day2->）
 * 难点在于如何保存random指针，
 * @Time: 2019/12/9 11:09
 */
public class _9_DeepCopyRandomLink {
    private static class Node {
        public int value;
        public Node random;
        public Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    /**
     * 使用map来保存random指针（因为random需要指向新copy出来的指针，所以需要保存）
     */
    public static Node deepCopyRandomLink1(Node head) {
        Map<Node, Node> map = new HashMap<>();
        Node cur = head;
        // 保存住对应对象
        while (cur != null) {
            map.put(cur, new Node(cur.value));
            cur = cur.next;
        }
        cur = head;
        while (cur != null) {
            map.get(cur).next = map.get(cur.next); // next和random都保存了，于是就能使用map直接get出来。当前节省一个保存map里面对象的空间
            map.get(cur).random = map.get(cur.random);
            cur = cur.next;
        }
        return map.get(head);  // 返回head对应的复制出来的指针。因为复制好的指针都保存在map内了
    }

    /**
     * 不使用额外的空间来保存。直接将复制出来的节点挂在被复制节点的后面，想要获取复制节点只需要使用next就有了。
     * 1->1'->2->2'->3->3'。构建成这样，最后再分散。（random要哪个就使用，原链表的random的next就能得到）
     */
    public static Node deepCopyRandomLink2(Node head) {
        if (head == null) {
            return null;
        }
        Node cur = head;
        Node next = null;  // 保存下一个被复制的
//        以下是构建 1->1'->2->2'->3->3'
        while (cur != null) {
            next = new Node(cur.value);
            next.next = cur.next;  //注意顺序
            cur.next = next;
            cur=cur.next.next;
        }
        cur = head;
        Node curCopy = null;
        // 以下是构建 深拷贝后的链表
        while (cur != null) {
            next = cur.next.next;
            curCopy = cur.next;
            curCopy.random = cur.random != null ? cur.random.next : null;
            cur=next;
        }
        //  将原数组拆分
        Node copyHead=head.next;  //保留复制的开头（用以返回）
        cur=head;
        while (cur != null) {
            next=cur.next.next;  //1->1'->2，获取2
            curCopy=cur.next;  //1->1'->2 获取1'
            cur.next=next;  // 构造1->2，以此类推
            curCopy.next=next!=null?next.next:null;  //1->1'->2->2' 获取2'并复制到1'后面，构造1'->2'，以此类推
            cur=next;  //  向后
        }
        return copyHead;
    }

    public static void printRandLinkedList(Node head) {
        Node cur = head;
        System.out.print("order: ");
        while (cur != null) {
            System.out.print(cur.value + " ");
            cur = cur.next;
        }
        System.out.println();
        cur = head;
        System.out.print("random:  ");
        while (cur != null) {
            System.out.print(cur.random == null ? "- " : cur.random.value + " ");
            cur = cur.next;
        }
        System.out.println();
    }
    public static void main(String[] args){
        Node head = null;
        Node res1 = null;
        Node res2 = null;
        /*printRandLinkedList(head);
        res1 = deepCopyRandomLink1(head);
        printRandLinkedList(res1);
        res2 = deepCopyRandomLink2(head);
        printRandLinkedList(res2);
        printRandLinkedList(head);
        System.out.println("=========================");*/

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = new Node(6);

        head.random = head.next.next.next.next.next; // 1 -> 6
        head.next.random = head.next.next.next.next.next; // 2 -> 6
        head.next.next.random = head.next.next.next.next; // 3 -> 5
        head.next.next.next.random = head.next.next; // 4 -> 3
        head.next.next.next.next.random = null; // 5 -> null
        head.next.next.next.next.next.random = head.next.next.next; // 6 -> 4

        printRandLinkedList(head);
        res1 = deepCopyRandomLink1(head);
        printRandLinkedList(res1);
        res2 = deepCopyRandomLink2(head);
        printRandLinkedList(res2);
        printRandLinkedList(head);
        System.out.println("=========================");
    }
}
