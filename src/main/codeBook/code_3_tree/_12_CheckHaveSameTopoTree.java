package codeBook.code_3_tree;

/**
 * @description: 给定两颗树，判断A树是否包含B树全部拓扑结构
 * A：             B：
 *      1             2
 *   2    3        4    5             =======》A包含B全部拓扑结构，故返回true
 * 4  5  6  7
 * @Time: 2019/12/18 10:32
 */
public class _12_CheckHaveSameTopoTree {

    /**
     * 判断树A是否包含树B，需要遍历每一个树A的节点。
     *
     * @param left  左子树
     * @param right 右子树
     * @return
     */
    public static boolean contains(Node left, Node right) {
        if (left == null) {
            return false;
        }
        // 遍历树A的每一个节点，使用“||”是因为只要有“一个节点延伸的树”是符合条件就返回true
        return checkHaveSameTopoTree(left, right) || contains(left.left, right) || contains(left.right, right);
    }

    /**
     * 判断方法：B向左移动，A也向左；B向右A也向右，但凡A有一个节点和B不一致就返回false，因为是使用&&，所以全都为false
     * @return A是否包含B
     */
    public static boolean checkHaveSameTopoTree(Node left, Node right) {
        // 递归到叶子就是null
        if (right == null) {
            return true;
        }
        // 若A先到底或存在A的值不等于B，直接为false
        if (left == null || left.value != right.value) {
            return false;
        }
        // B向左移动，A也向左；B向右A也向右
        return checkHaveSameTopoTree(left.left, right.left) && checkHaveSameTopoTree(left.right, right.right);
    }

    public static void main(String[] args) {
        Node A=new Node(1);
        A.left=new Node(2);
        A.right=new Node(3);
        A.left.left=new Node(4);
        A.left.right=new Node(5);
        A.right.left=new Node(6);
        A.right.right=new Node(7);
        _4_PrintTreeOnConsole.printTree(A);
        Node B=new Node(3);
        B.left=new Node(6);
        B.right=new Node(7);

        System.out.println(contains(A, B));

    }

}
