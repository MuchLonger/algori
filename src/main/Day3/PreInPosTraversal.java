package Day3;


import java.sql.SQLOutput;
import java.util.Stack;

/**
 * @description: 前中后序的二叉树递归打印以及非递归打印
 * 例子：  1
 *       2  3
 *     4 5 6 7
 * 递归的顺序是 1 2 4 4(左为空） 4(右为空) 2 5 5 5 2 3 6 6 6 3 7 7 7 3 1
 * 先序遍历是打印上面每个数字第一次出现的，中序的第二次出现的，后序是第三次出现的
 * @Time: 2019/10/31 21:34
 */
public class PreInPosTraversal {
    public static class Node{
        int value;
        Node left;
        Node right;
        public Node(int value){
            this.value=value;
        }
    }

    /**
     * 先序遍历递归实现：输出1 2 4 5 3 6 7
     * 中左右
     * @param head
     */
    public static void preOrderRecur(Node head){
        if(head ==null)
            return ;
        System.out.print(head.value+" ");
        preOrderRecur(head.left);
        preOrderRecur(head.right);
    }

    /**
     * 中序遍历递归实现：输出 4 2 5 1 6 3 7
     * 左中右
     * @param head
     */
    public static void midOrderRecur(Node head){
        if(head==null)
            return ;
        midOrderRecur(head.left);
        System.out.print(head.value+" ");
        midOrderRecur(head.right);
    }

    /**
     * 后序遍历递归实现：4 5 2 6 7 3 1
     * 左右中
     * @param head
     */
    public static void posOrderRecur(Node head){
        if(head==null)
            return;
        posOrderRecur(head.left);
        posOrderRecur(head.right);
        System.out.print(head.value+" ");
    }

    /**
     * 先序遍历的非递归形式：
     * 生成一个栈，模拟方法压栈的功能。
     * 将首节点压栈，然后当栈不为空的时候就遍历。
     * @param head
     */
    public static void preOrderUnRecur(Node head){
        System.out.println("先序遍历：");
        if(head!=null){
            Stack<Node> stack=new Stack<>();
            stack.add(head);  //首节点压栈
            while(!stack.isEmpty()){  //不为空就遍历，因为会一个一个输出，所以条件是栈不为空
                head=stack.pop();  //将节点从栈取出，
                System.out.print(head.value+" ");  //输出
                if(head.right!=null){  //当右节点不为空就把右节点放入栈中，因为先放入右节点那左节点会先出（先进后出），实现先输出左节点的功能
                    stack.push(head.right);
                }
                if(head.left!=null){  //先进后出，所以左先出实现先序
                    stack.push(head.left);
                }
            }
            System.out.println();
        }
        
    }

    /**
     * 中序遍历的非递归实现：
     * 和递归的逻辑一样，先将左节点一直压入栈中，之后通过判断head是否为空来决定是否继续向下(.next），当左节点都为空的时候结束（这一步就完成了递归的 MidOrderRecur(head.left);）
     * 之后如何head为空了，就从栈中取出（pop）上一个压栈的方法，输出，将其指向右节点，就能实现中序遍历。（因为如果右边又有左节点就又会压入栈中，所以这一步就结束了）
     * @param head
     */
    public static void midOrderUnRecur(Node head) {
        System.out.println("中序遍历 ");
        if (head == null)
            return;
        Stack<Node> stack = new Stack<>();
        while (!stack.isEmpty() || head != null) {
            if (head != null) { //只有为空的时候才压
                stack.push(head);
                head = head.left;  //一直向左并压栈
            } else {  //即到达了最底的时候，输出并转到右节点（像递归一样）
                head = stack.pop(); //取出上一个（就像递归的回退到上一步）
                System.out.print(head.value + " ");
                head = head.right;  //右边继续上面的操作
            }
        }
        System.out.println();
    }

    /**
     * 左右中
     * 后序遍历的非递归形式：使用技巧性方法，先找出遍历顺序为 中右左（和先序差不多）的方式，再放入栈中，输出就是左右中
     * 具体做法是先用中右左的遍历方式（就是先序先插左），然后再将遍历后的值存入栈中，再输出就是后序遍历
     * @param head
     */
    public static void posOrderUnRecur(Node head){
        System.out.println("后序遍历 ");
        if(head==null)
            return;
        Stack<Node> preStack=new Stack<>();  // 存入先序遍历的值 的栈
        Stack<Node> posStack=new Stack<>();  // 将先序遍历的值放入，再取出就是后序遍历
        preStack.add(head);  //从第一个节点开始
        while(!preStack.isEmpty()){
            head=preStack.pop();
            posStack.add(head);
            if(head.left!=null){  //先存左节点，最后先输出的就是右节点！！
                preStack.add(head.left);  //不依靠.next，依靠栈的取出存入
            }
            if(head.right!=null){
                preStack.add(head.right);
            }
        }
        while(!posStack.isEmpty()){
            System.out.print(posStack.pop().value+" ");  //后序遍历结果
        }
    }

    public static void main(String[] args) {
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

        // recursive
        System.out.println("==============recursive==============");
        System.out.print("pre-order: ");
        preOrderRecur(head);
        System.out.println();
        System.out.print("in-order: ");
        midOrderRecur(head);
        System.out.println();
        System.out.print("pos-order: ");
        posOrderRecur(head);
        System.out.println();

        // unrecursive
        System.out.println("============unrecursive=============");
        preOrderUnRecur(head);
        midOrderUnRecur(head);
        posOrderUnRecur(head);

    }

}
