package codeBook.second_part_LinkArray;

import java.util.HashSet;
import java.util.Set;

/**
 * @description: 删除链表所有重复的节点：两种方法:
 * 1）使用set保存再重新连。
 * 2）向后查找所有相同值的节点 并将其删除。如  1->2->3->2  到达2时，向后找到所有2，删除
 * @Time: 2019/12/10 18:04
 */
public class _13_removeSameNode {

    /**
     * 使用Set来删除重复节点。ON,ON
     */
    public static void removeSameNodeWithSet(Node head) {
        Set<Integer> set = new HashSet<>();
        Node pre = head;
        Node cur = head.next;  // 之所以要next，是因为下面的pre.next是下一个。
        set.add(head.value);  // 首节点肯定不重复，使用head.value
        while (cur != null) {
            if (set.contains(cur.value)) {
                pre.next=cur.next;   // 使用这个实现删除操作
            }else {
                pre = cur;  // 注意：不能使用pre.next=cur，这样会导致pre不会移动，直接使用pre=next就能实现移动。
                set.add(cur.value);
            }
            cur = cur.next;
        }
    }

    /**
     * 使用Node来删除重复节点。O(N2)
     */
    public static void removeSameNodeWithNode(Node head){
        Node cur=head;
        Node pre=null;  // 用来记录当前值
        Node next=null;  // 记录下一个值的开头
        while (cur != null) {
            pre=cur;  // 保存当前值
            next=cur.next;  // 从下一个开始，这样就可以循环了
            while (next != null) {
                if(cur.value==next.value){    // 注意使用的是cur.value，因为pre会移动。
                    pre.next=next.next;  // 如果找到重复的值，就使用.next来执行删除。
                }else{   // 如果不重复，那就直接=next，注意 不能使用pre=next
                    pre=next;
                }
                next=next.next;
            }
            cur=cur.next;
        }
    }
    public static void main(String[] args) {
        Node n1 = new Node(1);
        n1.next = new Node(2);
        n1.next.next = new Node(2);
        n1.next.next.next = new Node(4);
        n1.next.next.next.next = new Node(5);
        n1.next.next.next.next.next = new Node(5);
        removeSameNodeWithNode(n1);
        _2_RemoveLastLinkArray.printLinkedList(n1);
    }

}
