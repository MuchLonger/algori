package codeBook.code_3_tree;


/**
 * @description: 给定一个二叉树头结点head，按照以下标准实现二叉树节点边界节点的逆时针打印
 * 标准一：
 * 1）头结点为边界节点；
 * 2）叶节点为边界节点；
 * 3）如果节点为其所在层的最左或最右节点，那它也是边界节点。详情可看有道云
 * @Time: 2019/12/13 16:21
 */
public class _2_PrintTreeWithEdge1 {

    /**
     * 1. 先找出所有最左节点和最右节点
     * 2. 从上到下先打印最左节点
     * 3. 打印那些不属于最左节点和最右节点同时又是叶子节点的节点
     * 4.从下到上打印最右节点，但是这个节点不能既是最左节点又是最右节点
     */
    public static void printTreeStatus1(Node head) {
        if (head == null)
            return;
        int height = getHeight(head, 0);  // 得到从高度0开始的高度
        Node[][] edgeMaps = new Node[height][2]; //保存每一层的最左节点和最右节点
        setEdgeMap(head, 0, edgeMaps);  // 从高度0开始算起

        // 2.先打印最左节点
        for (int i = 0; i < edgeMaps.length; i++) {
            System.out.print(edgeMaps[i][0].value + " ");
        }
        // 3.打印那些不是最左节点和最右节点的叶子节点
        printLeafNotInMap(head, 0, edgeMaps);

        // 4.从下到上打印最右节点，但是这个节点不能既是最左节点又是最右节
        for (int i = edgeMaps.length - 1; i >= 0; i--) {
            if (edgeMaps[i][0] != edgeMaps[i][1]) {  // 即同一个map的值不一样
                System.out.print(edgeMaps[i][1].value + " ");
            }
        }
    }

    /**
     * 打印那些不是最左节点和最右节点的叶子节点
     */
    private static void printLeafNotInMap(Node head, int num, Node[][] edgeMaps) {
        if (head == null) {
            return;
        }
        if (head.left == null
                && head.right == null   //左右节点都没有：叶子节点
                && head != edgeMaps[num][0] // 不等于当前层左节点
                && head != edgeMaps[num][1]) {  // 不等于当前层右节点
            System.out.print(head.value + " ");
        }

        // 一层层执行下去，这样的递归可以保证 所有节点都遍历到！并且left在right前面，不然会逆序（358变成853）
        printLeafNotInMap(head.left, num + 1, edgeMaps);
        printLeafNotInMap(head.right, num + 1, edgeMaps);
    }

    /**
     * 递归找到每一层最左节点或最右节点
     */
    private static void setEdgeMap(Node head, int num, Node[][] edgeMaps) {
        if (head == null) {
            return;
        }
        edgeMaps[num][0] = edgeMaps[num][0] == null ? head : edgeMaps[num][0];  // 注意这个判断条件，仅在第一次进入的时候才赋值，而从下面可以看出第一次进入就是head.left即最左边一个节点
        edgeMaps[num][1] = head;  // 它会被一直赋值，但是因为最后一个递归是停在head.right（一直right）那，就是下一层的最右节点了

//        一层层找
        setEdgeMap(head.left, num + 1, edgeMaps);
        setEdgeMap(head.right, num + 1, edgeMaps);
    }

    /**
     * 递归获取整棵树的高度
     */
    private static int getHeight(Node head, int num) {
        if (head == null)
            return num;
        // 就是递归比对左树高还是右数高
        return Math.max(getHeight(head.left, num + 1), getHeight(head.right, num + 1));
    }

    /**
     *           6
     *      4         7
     *   2   5      9           按照标准2应该是 6 4 2 1 3 5 8 9 7
     * 1 3       8
     */
    public static void main(String[] args) {
        Node tn = new Node(6);
        tn.left = new Node(4);
        tn.right = new Node(7);
        tn.left.left = new Node(2);
        tn.left.right = new Node(5);
        tn.right.right = new Node(9);
        tn.right.right.left = new Node(8);
        tn.left.left.left = new Node(1);
        tn.left.left.right = new Node(3);
        printTreeStatus1(tn);
    }
}
