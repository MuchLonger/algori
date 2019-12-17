package codeBook.third_part_tree;

/**
 * @description: 最大二叉搜索树的拓扑结构的节点数。
 *               6                                            6
 *         4         7                                  4          7
 *      2   3          9     如左最大拓扑结构为：    2               9
 *    5 9            18
 *
 *  分三步：
 *      1）“找到(方法1)”当前节点全部“可构成二叉树的结构的节点(方法2)”，再递归找左节点，再递归找右节点。求最大值
 *      2）“找到(方法1)”方法实现：固定head，将递归移动head.left和head.right找到符合方法2的所有节点并加起来
 *      3）“可构成二叉树的结构的节点(方法2)”方法实现：按照小于当前值走左边大于当前值走右边的方式递归寻找，找到一样的就表示可以找到。
 * @Time: 2019/12/17 13:51
 */
public class _8_BSTTopoSize {

    /**
     * 寻找head为6的topo结构个数。再往左寻找head=4的topo结构个数。再往右寻找head=7的topo结构个数。
     */
    public static int getBSTTopoSize(Node head) {
        if (head == null) {
            return 0;
        }
        int max = maxTopo(head, head);   // 寻找当前节点的topo结构个数
        max = Math.max(getBSTTopoSize(head.left), max);  // 寻找左节点的topo结构个数
        max = Math.max(getBSTTopoSize(head.right), max); // 寻找右节点的topo结构个数
        return max;
    }

    /**
     * 如果给定的节点child，符合二叉树规范能走下去，那就再计算child左右节点能否继续走下去。
     * @param head 二叉树头结点（固定）
     * @param child 二叉树的子节点（移动 寻找以head为头结点的子树所有符合条件的节点加起来）
     * @return
     */
    private static int maxTopo(Node head, Node child) {
        if (head != null && child != null
                && isBSTNode(head, child, child.value)) { // 如果当前节点符合二叉树规范才能进去

            // 再计算当前节点head，以及child的左右节点，同时因为当前节点符合规范 故+1。
            return maxTopo(head, child.left) + maxTopo(head, child.right) + 1;
        }
        return 0;
    }

    /**
     *               6
     *         4         7
     *      2   3          9
     *    5 9            18
     * 判断child节点能否以二叉搜索树的方式找到（如head=6,child=9：6<9，走右边head=7; 7<9 走右边，找到了(==)，9就是当前二叉搜索树的节点）
     * 如果可以被找到，那child节点就是二叉搜索树的节点。
     *
     * @param head 从二叉搜索树头结点开始移动的结点
     * @param child 需要判断的节点
     * @param childValue 需要判断的节点值
     * @return
     */
    public static boolean isBSTNode(Node head, Node child, int childValue) {
        if (head == null) {
            return false;
        }
        if (head == child) {
            return true;
        }
        // 通过值判断走左还是走右，当前节点值大于目标值，则走左边，当前节点值小于目标值则走右边（6>9走右)。目标值固定
        return isBSTNode(head.value > childValue ? head.left : head.right, child, childValue);
    }

    /**
     *               6                                             6
     *         4         7                                  4          7
     *      2   3          9     如左最大拓扑结构为：    2               9         输出5
     *    5 9            18
     */
    public static void main(String[] args){
        Node tn = new Node(6);
        tn.left = new Node(4);
        tn.right = new Node(7);
        tn.left.left = new Node(2);
        tn.left.right = new Node(3);
        tn.right.right = new Node(9);
        tn.left.left.left = new Node(5);
        tn.left.left.right = new Node(9);
        tn.right.right.left = new Node(18);

        System.out.println(getBSTTopoSize(tn));
    }
}
