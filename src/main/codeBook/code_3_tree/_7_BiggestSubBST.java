package codeBook.code_3_tree;

/**
 * @description: 找到一棵树的最大（节点最多）二叉搜索树
 * @Time: 2019/12/17 10:08
 */
public class _7_BiggestSubBST {

    public static Node biggestSubBST(Node head) {
        int[] record = new int[3];
        return posOrder(head, record);
    }

    /**
     * Node：返回最大二叉树的头结点
     *
     * 需要求出 左、右子树中最大二叉树的头结点（不一定就是左右子树的头结点），左右子树的节点数，以及左、右子树的最小值和最大值。
     * 通过这四个值递归可以判断左右两边谁是最大二叉搜索树，亦或是当前节点就是最大二叉树。
     * <p>
     * 具体操作：先递归左子树 找到每个节点的节点信息：二叉搜索树的头结点、二叉搜索树的节点数、最小值、最大值，再递归找右子树的。
     * 比较这些信息：
     *  如果当前节点可以是二叉搜索树那就返回当前节点，
     *  如果不是那就返回左右二叉搜索树较多节点的头结点。
     * @param head 头结点
     * @param record  0节点数、1最小值、2最大值
     * @return head节点及其下最大二叉树的头结点
     */
    private static Node posOrder(Node head, int[] record) {
        if (head == null) {  // 如果为空了意味着当前节点不是二叉搜索树，所以size=0，最小值等于integer的最大值，最大值等于integer的最小值。
            record[0] = 0;
            record[1] = Integer.MAX_VALUE;
            record[2] = Integer.MIN_VALUE;
            return null;
        }
        // 当前节点值
        int value = head.value;

        Node left = head.left;
        // 求出左子树点的节点信息：二叉搜索树的头结点、二叉搜索树节点数、最小值、最大值
        Node leftBST = posOrder(left, record);  // 找到左子树的一颗二叉搜索树（不一定正好就是左子树(head.left)）
        int leftSize = record[0];
        int leftMin = record[1];  // 为null就是Integer.MAX_VALUE，即将最小值置为integer的最大值，这样任何值都会比它小了
        int leftMax = record[2];

        Node right = head.right;
        // 求出右子树点的节点信息：二叉搜索树头结点、二叉搜索树的节点数、最小值、最大值
        Node rightBST = posOrder(right, record); // 找到右子树的一颗二分搜索树
        int rightSize = record[0];
        int rightMin = record[1];
        int rightMax = record[2];

        record[1] = Math.min(leftMin, value); // 求出最小值
        record[2] = Math.min(rightMax, value);

        /*            取当前节点              */
        // 当且仅当 （左，右子树都是二叉搜索树，并且左子树的值小于当前节点值且当前节点值大于右子树的值）
        if (left == leftBST && right == rightBST && leftMax < value && value < rightMin) {
            record[0] = leftSize + rightSize + 1;  // 统计出当前二叉搜索树的节点数
            return head;
        }

        /*            取左子树节点或右子树节点              */
        // 如果到达这里就表示当前节点不是二叉搜索树，于是就比较左右子树，谁二叉树节点数多谁就是最大二叉搜索树
        record[0] = Math.max(leftSize, rightSize);  // 存入一颗子树的最大值就是最大节点数
        return leftSize > rightSize ? leftBST : rightBST;
    }

    /**
     *           6
     *      4         7
     *   2   5          9
     * 1 3            8
     */
    public static void main(String[] args){
        Node tn = new Node(6);
        tn.left = new Node(4);
        tn.right = new Node(7);
        tn.left.left = new Node(2);
        tn.left.right = new Node(5);
        tn.right.right = new Node(9);
        tn.right.right.left = new Node(8);
        tn.left.left.left = new Node(1);
        tn.left.left.right = new Node(3);

        _4_PrintTreeOnConsole.printTree(biggestSubBST(tn));
    }

}
