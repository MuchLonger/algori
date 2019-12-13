package codeBook.third_part_tree;


import java.util.Stack;

/**
 * @description: 使用递归和非递归实现二叉树的先序、中序、后序
 * @Time: 2019/12/13 10:31
 */
public class _1_PrintTree {

    /**
     * 先序遍历，递归打印
     */
    public static void preOrderWithRecur(Node root) {
        if (root == null) {
            return;
        }
        System.out.print(root.value + " ");
        preOrderWithRecur(root.left);
        preOrderWithRecur(root.right);
    }

    /**
     * 先序遍历，非递归打印
     */
    public static void preOrderNoRecur(Node root) {
        System.out.println("\n先序遍历非递归：");
        if (root == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(root);  // 实战的时候就会知道为什么在这里add：因为下面是 !stack.isEmpty()
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            System.out.print(node.value+" ");
            if (node.right != null) {  // 先压入右再压左，下一次取出的还是左节点
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
    }


    /**
     * 中序遍历，递归打印
     */
    public static void inOrderWithRecur(Node root) {
        if (root == null) {
            return;
        }
        inOrderWithRecur(root.left);
        System.out.print(root.value + " ");
        inOrderWithRecur(root.right);

    }

    /**
     * 一直将左节点压入栈中直到没有左节点，然后再开始压右节点，
     * 重复上面压左节点（重复的实现就是将右节点压进去，这样就能继续重复上面压左节点动作）。
     *
     *  基本就是仿着递归来：一开始遍历到最左，然后return ;，之后再遍历右边，右边没有就返回上一个左节点继续遍历右边操作。
     * @param root
     */
    public static void inOrderNoRecur(Node root) {
        System.out.println("\n中序遍历非递归：");
        if (root == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        while (!stack.isEmpty() || root != null) {  // 因为一开始栈是空的，所以加多一个root的判断
            if (root != null) {  // （左或右）节点不为空就压栈
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();  // 取出左节点输出
                System.out.print(root.value + " ");  //这个时候输出
                root = root.right;  // 右节点继续上面操作
            }
        }
    }

    /**
     * 后序遍历，递归打印
     */
    public static void postOrderWithRecur(Node root) {
        if (root == null) {
            return;
        }
        postOrderWithRecur(root.left);
        postOrderWithRecur(root.right);
        System.out.print(root.value + " ");
    }

    /**
     * 后序遍历，非递归打印
     */
    public static void postOrderNoRecur(Node root){
        System.out.println("\n后序遍历非递归:");
        if (root == null) {
            return ;
        }
        Stack<Node> stack=new Stack<>();
        Stack<Node> storage=new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            root=stack.pop();
            storage.push(root);
            if (root.left != null) {
                stack.push(root.left);
            }
            if (root.right != null) {
                stack.push(root.right);
            }
        }
        while(!storage.isEmpty()){
            System.out.print(storage.pop().value+" ");
        }
    }
    public static void main(String[] args){
        Node head = new Node(5);
        head.left = new Node(3);
        head.right = new Node(8);
        head.left.left = new Node(2);
        head.left.right = new Node(4);
        head.left.left.left = new Node(1);
        head.right.left = new Node(7);
        head.right.left.left = new Node(6);
        head.right.right = new Node(10);
        head.right.right.left = new Node(9);
        head.right.right.right = new Node(11);

        System.out.println("先序遍历递归：");
        preOrderWithRecur(head);
        preOrderNoRecur(head);
        System.out.println();

        System.out.println("中序遍历递归：");
        inOrderWithRecur(head);
        inOrderNoRecur(head);
        System.out.println();

        System.out.println("后序遍历递归：");
        postOrderWithRecur(head);
        postOrderNoRecur(head);

    }

}
