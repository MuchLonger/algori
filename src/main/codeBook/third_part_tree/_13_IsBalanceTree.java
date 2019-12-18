package codeBook.third_part_tree;


/**
 * @description: 判断是否是平衡二叉树，平衡二叉树：要么是空子树，要么任一节点的左右节点的高度都不能超过1
 * @Time: 2019/12/18 11:10
 */
public class _13_IsBalanceTree {

    public static boolean isBalanceTree(Node head) {
        // 一定要使用boolean[]数组，如果使用boolean会导致全都为true。（因为无法修改一个局部变量的值）改成全局变量也可以。
        boolean[] res = new boolean[1];
        res[0] = true;
        getHeight(head, 1, res);
        return res[0];
    }

    /**
     * 判断左子树是否是平衡二叉树，再判断右子树。
     * 判断方法：记录左子树高度和右子树高度，若高度差大于1就置为false
     *
     * @param head  头结点
     * @param level 当前层数
     * @param res   当前是否是平衡二叉树
     * @return 返回当前层数
     */
    private static int getHeight(Node head, int level, boolean[] res) {
        if (head == null) {  // 通过这一步将从head到底的层数输出出去
            return level;
        }
        int lH = getHeight(head.left, level + 1, res);  // 左子树层数
        if (!res[0]) {
            return -1;  // 任意返回
        }
        int rH = getHeight(head.right, level + 1, res); // 左子树层数
        if (!res[0]) {
            return -1; // 任意返回
        }
        if (Math.abs(lH - rH) > 1) { // 如果左右子树高度差大于1就设为false
            res[0] = false;
        }
        return Math.max(lH, rH); // 返回层数较高那个
    }

    /**
     *             6
     *       4         7
     *    2   5          1  ==>false  7那左右节点相差为2
     *  9  3           8
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

        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);

        System.out.println(isBalanceTree(head));
    }
}
