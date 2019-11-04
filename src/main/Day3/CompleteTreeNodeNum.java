package Day3;


import java.lang.annotation.ElementType;

/**
 * @description: 找到完全二叉树的节点数（使用递归方式）时间复杂度O（lg N）
 * 前瞻知识：求一颗满二叉树的节点数的方法是：2的n次方-1（n为二叉树的层数）
 * @Time: 2019/11/3 17:30
 */
public class CompleteTreeNodeNum {
    public static class Node{
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static int nodeNum(Node head){
        if(head==null)
            return 0;
        return bs(head,1,mostLeftLevel(head,1));
    }

    /**
     * 递归求出完全二叉树的节点数
     *
     * 基本思想：先判断“当前节点的右节点的最左节点”是否到达了树的最后一层：
     * 1）如果“右节点的最左节点(12)”到了最后一层，那毫无疑问当前节点的左节点是一颗满二叉树
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
     * 通过递归上面两个就能得到完全二叉树的节点数
     * @param head 头结点
     * @param level 当前节点所在层数
     * @param high 树高
     * @return 完全二叉树的节点数
     */
    public static int bs(Node head,int level,int high){
        if(level==high){  //递归终止：当当前层数到达最后一层，那就是当前节点仅有1颗节点
            return 1;
        }
        //判断当前节点的“右节点的最左节点”是否到达最后一层，注意是level+1，因为level是当前head的层数，+1就是子节点的层数
        if(mostLeftLevel(head.right,level+1)==high){  //左节点是满二叉树
//            注意，高度是high-level（level是当前节点的层数，总层-当前节点层=即左子树层(高度)），-1是因为满二叉树的节点公式是 (2的n次方)-1 ，+1是因为需要加上当前节点（head）
            //求出当前树的左子树个数 再加上 右子树(head.right)的节点数（递归，不断实现）
            return (int) (Math.pow(2,high-level)-1+1+bs(head.right,level+1,high));
        }else{  //没有到最底，那右节点就是满二叉树
//            这里是-level-1，这是因为 右子树之所以是满二叉树是因为缺少了最后一层，所以就是 high-level-1 个
            //当前节点的右子树已经求了，还剩当前节点的左子树没求（所以是head.left)，依旧传入下一层。
            return (int)Math.pow(2,high-level-1)+bs(head.left,level+1,high);
        }
        //递归完成
    }

    /**
     * 求出一棵树的高度（可以自选节点加上节点所在层数 求出高度）
     * @param node 节点
     * @param level 节点所在的层数
     * @return 树高
     */
    public static int mostLeftLevel(Node node,int level){
        while(node!=null){
            level++;
            node=node.left;   //只需找最左边即可
        }
        return level-1;  //注意-1
    }


    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        System.out.println(nodeNum(head));
    }
}
