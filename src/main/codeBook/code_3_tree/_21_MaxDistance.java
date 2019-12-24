package codeBook.code_3_tree;

/**
 * @description: 找到整棵树内两个节点的最大距离
 *         6
 *     2     3
 *  4   5            如这棵树的最大节点距离就是；4-2-5-9-1：即为5
 *    9  2
 *  1
 * @Time: 2019/12/22 14:10
 */
public class _21_MaxDistance {

    public static int getMaxDistance(Node head){
        int[] record=new int[1];
        return posOrder(head,record);
    }

    /**
     * 最大距离仅在以下三种情况之中发生：1）左子树内，2）右子树内，3）左子树加上右子树加上当前节点
     * 故可以先求出“左子树最大距离”，“右子树最大距离”，以及“左子树到底的距离”加上“右子树到底的距离”加上“当前节点的距离”
     *
     * 注意，最大和到底不是一个距离，最大是包括上面三种情况的，到底是左子树到最后一个叶子节点的距离
     */
    private static int posOrder(Node head, int[] record) {
        if (head == null) {
            record[0]=0;
            return 0;
        }
        int lMax=posOrder(head.left,record);  // 左子树的最大距离
        int maxFromLeft=record[0];  // 左子树到底最深的距离

        int rMax=posOrder(head.right,record);  // 右子树的最大距离
        int maxFromRight=record[0]; // 右子树到底最深的距离

        int curNodeMax=maxFromLeft+maxFromRight+1;  // 左子树加上右子树加上当前节点的距离。

        record[0]=Math.max(maxFromLeft,maxFromRight)+1;  // 当前节点到底的距离（哪棵子树最深就记录哪颗子树）（左子树大记录左子树，右子树大记录右子树）

        return Math.max(Math.max(lMax,rMax),curNodeMax); // 比较上面三种情况，谁大要谁。

    }


    /**
     *             6
     *       4         7
     *    2   5          1
     */
    public static void main(String[] args){
        Node tn = new Node(6);
        tn.left = new Node(4);
        tn.right = new Node(7);
        tn.left.left = new Node(2);
        tn.left.right = new Node(5);
        tn.right.right = new Node(1);

        Node left=new Node(1);
        left.left=new Node(1);
        left.right=new Node(1);
        left.left.left=new Node(2);
        left.left.left.left=new Node(2);
        left.left.left.left.left=new Node(2);
        left.left.left.left.left.left=new Node(2);
        left.left.left.left.left.left.left=new Node(2);

        System.out.println(getMaxDistance(left));

    }
}
