package codeBook.second_part_LinkArray;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @description: 二叉搜索树转为双向链表，
 *      6
 *    4    7
 *  2  5     9             将这个二叉搜索树从1-9,转化成双向链表。最后就是 1 ←→ 2 ←→ ... ←→ 9
 * 1 3     8            即原本的 “树节点left”改成“双向链表节点的pre”，“树节点right”改成“双向链表节点的next”，
 * @Time: 2019/12/11 13:54
 */
public class _15_BSTConvertDoubleNode {
    /**
     * 使用队列：将二叉搜索树“中序遍历”之后放入队列中，之后再poll重新构建DoubleNode。
     */
    public static DoubleNode convert1(TreeNode head){
        Queue<TreeNode> queue=new LinkedList<>();
        inOrderPull(queue,head);
        if (queue.isEmpty()) {
            return null;
        }
        head=queue.poll();  // 从1开始获取（所以就从1开始构建）

        DoubleNode pre=new DoubleNode(head.value);  // 双向链表的构建
        DoubleNode dbHead=pre;
        pre.pre=null;  // 1-9 很明显pre的前一个节点是没有值的
        TreeNode cur=null;
        DoubleNode next=null;   // 用来记录下一个节点（用来连接）
        while (!queue.isEmpty()) {
            cur=queue.poll();  // 值为2的节点
            next=new DoubleNode(cur.value);
            pre.next=next;
            next.pre=pre;
            pre=next;  // 继续向后找
        }
        return dbHead;
    }

    private static void inOrderPull(Queue<TreeNode> queue, TreeNode head) {
        if (head == null) {
            return ;
        }
        inOrderPull(queue,head.left);
        queue.offer(head);
        inOrderPull(queue,head.right);
    }

    public static void main(String[] args){
        TreeNode tn=new TreeNode(6);
        tn.left=new TreeNode(4);
        tn.right=new TreeNode(7);
        tn.left.left=new TreeNode(2);
        tn.left.right=new TreeNode(5);
        tn.right.right=new TreeNode(9);
        tn.left.left.left=new TreeNode(1);
        tn.left.left.right=new TreeNode(3);
        _2_RemoveLastLinkArray.printLinkedList(convert1(tn));
    }
}
