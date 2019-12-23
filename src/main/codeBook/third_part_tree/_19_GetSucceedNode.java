package codeBook.third_part_tree;



/**
 * @description: 现在有“含有parent指针”的Node节点，寻找一棵树的后继节点（后继节点的意思是 当前节点在“中序遍历”中的位置的“的下一个节点”）
 * 如：中序遍历序列： 4 2 5 1 6 3 7 ，2的后继节点是5，6的后继节点是3。2的前驱节点是4, 6的前驱节点是1
 *
 * 简单来说就是找中序遍历的下一个节点，
 *
 * 实际上就是求中序遍历的后继节点的过程：
 * 步骤一：因为中序遍历是以“左中右”来遍历的：
 *              5
 *           2
 *        1    4
 *           3        // 2的后继节点就是3，说得是这种情况
 *      1）所以如果"当前节点的右节点"不为空，那后继节点一定是"当前节点的右子节点的最左子节点"，如果 "当前节点的右节点 没有左节点"那这个右节点就是当前节点的后继
 *      2）如果当前节点右节点为空（那就是中序遍历的“左”），那就判断 当前节点 是否是其父节点的左节点，
 *                 5
 *              2      // 1的后继节点是2，大致就是这种情况
 *           1
 *          2.1 如果是：那后继节点就是这个父节点！
 *                  5
 *               2          // 如图这样找，3的后继节点就是5，
 *                 4
 *                   3
 *          2.2 如果不是：那就找父节点的父节点再判断“父节点是否是父父节点的左节点”，一直循环，如果到达head就为null，那返回null
 *       其实在“第二步”的结局都是向上找到“以对应节点为左节点”的父节点。
 *
 * @Time: 2019/12/20 16:13
 */
public class _19_GetSucceedNode {

    public static class Node{
        public int value;
        public Node left;
        public Node right;
        public Node parent; //父节点
        public Node(int value){
            this.value=value;
        }
    }

    public static  Node getSucceedNode(Node node){
        if (node == null) {
            return node;
        }
        if (node.right != null) { // 右节点不为空
            // 如找到最左节点，那就是这个节点的后继节点。
            return getMostLeft(node.right);
        }else{  // 右节点为空，向上面一样分两种情况
            Node parent=node.parent;
            while(parent!=null&&parent.left!=node){ // 找到左节点
                node=parent;
                parent=parent.parent;
            }
            return parent;
        }
    }

    // 找到最左的节点
    private static Node getMostLeft(Node node) {
        if (node == null) {
            return node;
        }
        while (node.left != null) {
            node=node.left;
        }
        return node;
    }
    public static void main(String[] args) {
        Node head = new Node(6);
        head.parent = null;
        head.left = new Node(3);
        head.left.parent = head;
        head.left.left = new Node(1);
        head.left.left.parent = head.left;
        head.left.left.right = new Node(2);
        head.left.left.right.parent = head.left.left;
        head.left.right = new Node(4);
        head.left.right.parent = head.left;
        head.left.right.right = new Node(5);
        head.left.right.right.parent = head.left.right;
        head.right = new Node(9);
        head.right.parent = head;
        head.right.left = new Node(8);
        head.right.left.parent = head.right;
        head.right.left.left = new Node(7);
        head.right.left.left.parent = head.right.left;
        head.right.right = new Node(10);
        head.right.right.parent = head.right;

        Node test = head.left.left;
        System.out.println(test.value + " next: " + getSucceedNode(test).value);
        test = head.left.left.right;
        System.out.println(test.value + " next: " + getSucceedNode(test).value);
        test = head.left;
        System.out.println(test.value + " next: " + getSucceedNode(test).value);
        test = head.left.right;
        System.out.println(test.value + " next: " + getSucceedNode(test).value);
        test = head.left.right.right;
        System.out.println(test.value + " next: " + getSucceedNode(test).value);
        test = head;
        System.out.println(test.value + " next: " + getSucceedNode(test).value);
        test = head.right.left.left;
        System.out.println(test.value + " next: " + getSucceedNode(test).value);
        test = head.right.left;
        System.out.println(test.value + " next: " + getSucceedNode(test).value);
        test = head.right;
        System.out.println(test.value + " next: " + getSucceedNode(test).value);
        test = head.right.right; // 10's next is null
        System.out.println(test.value + " next: " + getSucceedNode(test));
    }

}
