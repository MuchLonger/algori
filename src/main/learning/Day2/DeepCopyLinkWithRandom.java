package learning.Day2;


import java.util.HashMap;

/**
 * @description: 一个全新的Node，内有next和randomom指针，next指向下一个，randomom指向任意一个。问题：深拷贝一个链表。
 * <p>
 * 理解题目：假设有链表有A，B，C（且都有value,next,randomom字段），相应深拷贝这个链表，就将需要重新生成（new）A',B',C'并组成赋值，
 * 这样就会产生困难，因为A的next指向的是B，而A’的next也需要指向B’，而不是B！借助Map就能实现一一对应，让A对应A’让B对应B’，
 * 这样next指针拷贝的时候直接通过get（B）就能得到B’，而不用再重新new（实现复用）
 * <p>
 * 方法一（0(N),O(N)）：使用map来一一映射 被拷贝节点A 和 拷贝节点A' 因为有randomom指针，所以深拷贝的时候要疯狂跳转。如果使用map就能直接
 * 映射出来对应的是哪个Node，而不用再根据 被拷贝的节点A 再去重新生成 A’ 并艰难的找到且指向这个A’。
 * <p>
 * 方法二（O（N），O（1））：将被拷贝节点A的下个节点指向A’，A’再指向B：形成A->A'->B->B'->C->C'这样
 * 再将这个链表两两取下来，形成A->A'和B->B'和C->C'。如A的randomom指向C，那只需要A'的指针指向C的next节点就是C'，即可实现拷贝
 * @Time: 2019/10/29 23:13
 */
public class DeepCopyLinkWithRandom {
    public static class Node {
        private int data;
        private Node next;
        private Node random;  //指向当前链表的随机节点

        public Node(int data) {
            this.data = data;
        }
    }

    //方法一：使用Map存储被深拷贝的元素和深拷贝的元素，传入一个首节点开始复制，并突出一个复制后的head’节点
    public static Node deepCopyLinkListHaveRandom(Node head) {
        HashMap<Node, Node> map = new HashMap<>();
        Node cur = head;
        while (cur != null) {
            map.put(cur, new Node(cur.data)); //深拷贝，并且只有值。之后存入map中
            cur = cur.next;
        }
        cur = head; //从头开始
        while (cur != null) {
            map.get(cur).next = map.get(cur.next);  //获得 拷贝后的值（A'）。并将它（A’）的next值 改成“ A对应的next值（B）”映射的B’。
            map.get(cur).random = map.get(cur.random);  //同上
            cur = cur.next;
        }
//        将第一个节点返回就完成深拷贝
        return map.get(head);
    }

    public static Node deepCopyLinkListHaveRandom2(Node head) {
        if (head == null)
            return null;
        Node cur = head;
        Node next = null;
//        这一步将A->B->C转成A->A'->B->B'->C->C'
        while (cur != null) {
            next = cur.next;
            cur.next = new Node(cur.data);
//            指向下一个
            cur.next.next = next;
            cur = next; //到B了,next从一开始就是cur的内存，所以直接复制也不会将.next破坏掉
        }
        cur = head;
        Node curCopy = null;
        while (cur != null) {
            next = cur.next.next;  //保存两个之后，一次跳两个
            curCopy = cur.next;
//            将随机的next(B->B')的B’存入A’中
            curCopy.random = cur.random != null ? cur.random.next : null; //注意空指针
            cur = next;
        }
//        当前的链表还是A->A'->B->B'->C->C'，但是所有copy指针的randomom都被赋值好了
//        以下就是分割
        Node res = head.next;  //拷贝后的节点，后面的操作是A->A'->B->B'  ==》 A->B->A'->B'所以输出head.next就是输出从A'开始
        cur = head;  //原节点，也是传进来的节点
        while (cur != null) {
            next = cur.next.next;  //原节点（B）
            curCopy = cur.next;  //拷贝节点A'
            cur.next = next;  //A'和B位置互换以此类推
            curCopy.next = next != null ? next.next : null; //将B的B'接到A'上
            cur = next;
        }
        return res;
    }
    public static void printRandLinkedList(Node head) {
        Node cur = head;
        System.out.print("order: ");
        while (cur != null) {
            System.out.print(cur.data + " ");
            cur = cur.next;
        }
        System.out.println();
        cur = head;
        System.out.print("random:  ");
        while (cur != null) {
            System.out.print(cur.random == null ? "- " : cur.random.data + " ");
            cur = cur.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head = null;
        Node res1 = null;
        Node res2 = null;
        printRandLinkedList(head);
        res1 = deepCopyLinkListHaveRandom(head);
        printRandLinkedList(res1);
        res2 = deepCopyLinkListHaveRandom2(head);
        printRandLinkedList(res2);
        printRandLinkedList(head);
        System.out.println("=========================");

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
        res1 = deepCopyLinkListHaveRandom(head);
        printRandLinkedList(res1);
        res2 = deepCopyLinkListHaveRandom2(head);
        printRandLinkedList(res2);
        printRandLinkedList(head);
        System.out.println("=========================");

    }

}
