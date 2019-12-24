package codeBook.code_3_tree;

/**
 * @description: 求出完全二叉树的节点数
 * 前瞻知识：求一颗满二叉树的节点数的方法是：2的n次方-1（n为二叉树的层数）
 * @Time: 2019/12/24 9:50
 */
public class _28_CBTNodeNum {
    public static int nodeNum(Node head){
        if (head == null) {
            return 0;
        }
        return getNum(head,1,treeLevel(head,1));
    }

    /**
     * 基本思想：先判断“当前节点的右节点的最左节点”是否到达了树的最后一层：
     * 1）如果“右节点的最左节点(12)”到了最后一层，对于一棵完全二叉树来说，那毫无疑问“当前节点的左节点”是一颗满二叉树
     * 即：从当前节点+1层到最后一层的满二叉树，参考下面的树就是从2所在层到8所在层都是满二叉树。（2 4 5 8 9 10 11 12）
     *          1
     *      2       3
     *   4   5    6   7
     * 8 9 10 11 12
     * 2）如果“右节点的最左节点(6)”没有到最后一层，那当前节点的右节点一直到“右节点的最左节点(6)”都是满二叉树
     * 即：head=3，当前节点+1层开始到6都是满二叉树。（3 6 7）
     *          1
     *      2       3
     *   4   5    6   7
     * 8 9 10
     *
     * @param head
     * @param level
     * @param high
     * @return
     */
    private static int getNum(Node head, int level, int high) {
        if (level == high) {
            return 1;
        }
        // 这个比较就是判断右子树是否到底（level+1是代表当前是第二层），到底就是第一种情况，不然就是第二种情况
        if(treeLevel(head.right,level+1)==high){  // 如果是第一种情况，即左边是满二叉树
            //
            return (int)(Math.pow(2,high-level)-1+getNum(head.right,level+1,high))+1;  // +1是因为需要加上头结点，因为可以-1+1消去
        }else{  // 第二种情况，右边小一级是满二叉树，所以是high-level-1，再继续计算节点数
            return (int)(Math.pow(2,high-level-1)+getNum(head.left,level+1,high)); // 因为可以-1+1消去
        }
    }

    /**
     * 求一棵树的高度
     */
    private static int treeLevel(Node head, int level) {
        while (head != null) {
            level++;
            head=head.left;
        }
        return level-1;
    }
    /**
     *             6
     *       4         7
     *    2   5      12   1
     *  9  3
     */
    public static void main(String[] args) {
        Node tn = new Node(6);
        tn.left = new Node(4);
        tn.right = new Node(7);
        tn.left.left = new Node(2);
        tn.left.right = new Node(5);
        tn.right.right = new Node(1);
        tn.right.left = new Node(12);
        tn.right.right.left = new Node(8);
        tn.left.left.left = new Node(9);
        tn.left.left.right = new Node(3);

        System.out.println(nodeNum(tn));

    }

}
