package codeBook.code_3_tree;

import java.util.Stack;

/**
 * @description: 一颗二叉搜索树出现了两个（仅两个）错误节点（即规范出错），要求找到这两个错误节点并返回
 * @Time: 2019/12/18 9:58
 */
public class _11_GetTwoErrNodes {

    /**
     * 基本思路，如果出现两个错误节点，那中序遍历肯定就会乱序，这时分两种情况，设原树的中序遍历为 1 2 3 4 5
     * 情况1：两个错误节点间隔较远：1 5 3 4 2，
     * 第一个错误节点就是“中序遍历出现降序的两个节点”中“较大”那个：如第一个降序是5 3，所以错误节点是5，
     * 第二个错误节点就是“中序遍历出现降序的两个节点“中“较小”那个：如第二个降序就是4 2，所以错误节点为2。
     * 情况2：两个错误节点无间隔：1 2 4 3 5，
     * 第一个错误节点也是“中序遍历出现降序的两个节点”中“较大那个：如4 3，错误节点为4
     * 第二个错误节点也是“中序遍历出现降序的两个节点”中“较小那个：如4 3，错误节点为3
     *
     * @param head
     * @return
     */
    public static Node[] getTwoErrNodes(Node head) {
        Node[] errs = new Node[2];
        if (head == null) {
            return errs;
        }
        Stack<Node> stack = new Stack<>();
        Node pre = null;
        // 使用栈的中序遍历
        while (!stack.isEmpty() || head != null) {
            if (head != null) {
                stack.push(head);
                head = head.left;
            } else {
                head = stack.pop();
                if (pre != null && pre.value > head.value) {
                    // 第一个错误节点是比“中序后一个节点”大的，于是将大的放入错误列表
                    errs[0] = errs[0] == null ? pre : errs[0];  // 如果赋过值就不再更改它了
                    // 第二个错误节点是比“中序遍历后一个节点” 小的，于是将较小的head放入错误列表
                    errs[1] = head;
                }
                pre=head;// 记录好上一个节点
                head = head.right;
            }
        }
        return errs;

    }

    /**
     *             6
     *       4         7
     *   2   5          1
     * 9 3            8
     */
    public static void main(String[] args) {
        Node tn = new Node(6);
        tn.left = new Node(4);
        tn.right = new Node(7);
        tn.left.left = new Node(2);
        tn.left.right = new Node(5);
        tn.right.right = new Node(1);
        tn.right.right.left = new Node(8);
        tn.left.left.left = new Node(9);
        tn.left.left.right = new Node(3);

        Node[] twoErrNodes = getTwoErrNodes(tn);
        System.out.println(twoErrNodes[0].value);
        System.out.println(twoErrNodes[1].value);
    }

}
