package learning.Day3;


/**
 * @description: 求 任意一个含有parent指针的Node 的后继节点（后继节点的意思是“中序遍历后”“的节点”“的下一个节点”）
 * ps：扩展，前驱节点就是中序遍历的前一个节点，处理方法就是：如果左子树不为空就找 左子树的最右节点。如果左子树为空 那就找 当前节点是父节点的右节点 输出这个父节点
 * 如中序遍历序列： 4 2 5 1 6 3 7 ，2的后继节点是5，6的后继节点是3。2的前驱节点是4, 6的前驱节点是1
 *
 * 实际上就是求中序遍历的后继节点的过程：
 * 步骤一：因为中序遍历是以“左中右”来遍历的，所以如果当前节点的右节点不为空，那后继节点一定是 当前节点的右子节点 的最左子节点，如果 当前节点的右节点 没有左节点 那这个右节点就是当前节点的后继
 * （就是中序遍历的时候当前节点是中间节点，那自然后继节点（中序遍历的下一个节点）就是右节点的最左节点啦）。（递归的顺序，输出当前节点之后，到达右节点，并一直判断右节点的左节点是否为空）（其实理解中序遍历就理解了）
 * 步骤二：如果右节点为空（那就是中序遍历的“左”），那就判断 当前节点 是否是其父节点的左节点，如果是：那后继节点就是这个父节点！ 如果不是：那就找父节点的父节点再判断“父节点是否是父父节点的左节点”，一直循环，如果到达head就为null，那返回null）
 * 其实也是中序遍历，右节点为空，代表当前节点就是这一颗小树的最后一个输出值，那结束完这个输出之后就要回退到上一个节点即父节点，如果父节点仍是上一颗子树的右节点，那肯定还得回退（左中右）！直到当前是“左”中止，下一步是中，那就输出这个中！，
 * @Time: 2019/11/1 21:22
 */
public class SuccessorNode {

    public static class Node{
        public int value;
        public Node left;
        public Node right;
        public Node parent; //父节点
        public Node(int value){
            this.value=value;
        }
    }

    /**
     * 求后继节点
     * @param node
     * @return
     */
    public static Node getSuccessorNode(Node node){
        if(node==null)
            return node;
        if(node.right!=null){  //不为空，那当前是在“中”，下一步是 右之后递归最左
            return getMostLeft(node.right);  //将下一个右节点放入求出这个右节点的最左节点
        }else{     //为空，那当前也是在中，但是下一步是 回退到上一个左节点阶段
            Node parent=node.parent;  //当前节点的父节点
            while(parent!=null&& parent.left!=node){ //不为首节点 或 父节点的左节点不是当前节点，就继续往上
                node=parent;
                parent=parent.parent;
            }
            return parent;  //当这个节点是父节点的左节点的时候返回这个父节点（“左”）
        }

    }

    /**
     * 求一个节点的最左节点
     * @param node
     * @return
     */
    public static Node getMostLeft(Node node){
        if(node==null)
            return node;
        while(node.left!=null){
            node=node.left; //最左
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
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.left.left.right;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.left;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.left.right;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.left.right.right;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.right.left.left;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.right.left;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.right;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.right.right; // 10's next is null
        System.out.println(test.value + " next: " + getSuccessorNode(test));
    }

}
