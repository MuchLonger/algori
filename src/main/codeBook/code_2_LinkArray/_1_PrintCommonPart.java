package codeBook.code_2_LinkArray;

/**
 * @description: 输出两个“有序链表”的公共部分：因为有序，所以n1>n2 就n2++；n1<n2,n1++；n1==n2 输出并n1,n2同时++。一方为null就退出
 * @Time: 2019/12/4 17:35
 */

public class _1_PrintCommonPart {
    public static void printCommonPart(Node n1, Node n2) {
        if (n1 == null || n2 == null) {
            throw new RuntimeException("节点不能为空");
        }
        while (n1 != null || n2 == null) {
            if (n1.value < n2.value) {
                n1 = n1.next;
            } else if (n1.value > n2.value) {
                n2 = n2.next;
            } else {
                System.out.println(n1.value);
                n1 = n1.next;
                n2 = n2.next;
            }
        }
    }
    public static void main(String[] args) {
        Node n1 = new Node(1);
        n1.next = new Node(2);
        n1.next.next = new Node(3);
        n1.next.next.next = new Node(4);
        n1.next.next.next.next = new Node(5);

        Node n2 = new Node(2);
        n2.next = new Node(4);
        n2.next.next = new Node(5);
        n2.next.next.next = new Node(6);
        n2.next.next.next.next = new Node(7);
        printCommonPart(n1,n2);
    }
}
