package codeBook.code_3_tree;



import java.util.LinkedList;
import java.util.Queue;

/**
 * @description: 判断一棵树是否是完全二叉树
 * @Time: 2019/12/20 11:45
 */
public class _17_IsCBT {

    /**
     * 判断是否是完全二叉树：只要满足以下两个条件的就是完全二叉树
     * 条件1）不存在“一个节点有右节点而没有左节点”的情况 （即当一个节点左节点为空右节点有值的时候就不是完全二叉树）
     * 条件2）当存在“一个节点没有左右节点” 或 “一个节点有左节点没有右节点”的情况，这个节点往下（按照左到右，上到下的顺序）的 所有的节点 都必须是叶节点。
     * 满足上面就是完全二叉树
     * 遍历的顺序是从左到右，从上到下！！！（层次遍历）
     * <p>
     * 因为是层次遍历，所以是队列存储
     *
     * @param head
     * @return
     */
    public static boolean isCTB(Node head) {
        if (head == null) {
            return true;
        }
        Queue<Node> queue = new LinkedList<>();
        // 判断条件二，一旦出现上面说的情况立刻就转变状态
        boolean state = false;
        Node leftNode = null;
        Node rightNode = null;
        queue.offer(head);  // 层次遍历的步骤
        while (!queue.isEmpty()) {
            head = queue.poll();
            leftNode = head.left;
            rightNode = head.right;
            // 当 “状态开启了还存在左右节点”或“左节点为空右节点不为空”直接返回false
            if ((state && (leftNode != null || rightNode != null))
                    || (leftNode == null && rightNode != null)) {
                return false;
            }
            if (leftNode != null) {
                queue.offer(leftNode);
            }
            if (rightNode != null) {
                queue.offer(rightNode);
            }
            // 开启状态，即当“左右节点都为空”和“左节点为空右节点不为空”的情况下开启
            if ((leftNode == null && rightNode == null)
                    || (leftNode != null && rightNode == null)) {
                state = true;
            }
        }
        return true;
    }

    /**
     *      6
     *   4    7
     * 2  5
     */
    public static void main(String[] args){
        Node tn = new Node(6);
        tn.left = new Node(4);
        tn.right = new Node(7);
        tn.left.left = new Node(2);
        tn.left.right = new Node(5);

        System.out.println(isCTB(tn));
    }
}
