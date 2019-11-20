package learning.Day3;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @description: 序列化和反序列化一棵树（即将数变成一个字符串（序列化），之后再通过这字符串返回一棵树（反序列化））
 *     1
 *   2  3
 * 4 5  6 7
 * @Time: 2019/11/1 22:19
 */
public class SerialazeAndReconstructTree {
    public static class Node {
        public int value;
        public Node left;
        public Node right;
        public Node(int value){
            this.value=value;
        }
    }

    /**
     * 使用先序遍历序列化一棵树，如将1 2 3 4 5 6 7（自己脑补）变成 1!2!4!#!#!5!#!#!3!6!#!#!7#!#!
     * 就是把null设置成#号，数字以！隔开。
     * @param head 序列化的树头
     * @return
     */
    public static String serialByPre(Node head){
        if(head==null)
            return "#!";  //为null就返回#号
        String res= head.value+"!"; //先序遍历，先输出（值+!）
        res+=serialByPre(head.left);  //拼上去
        res+=serialByPre(head.right);  //拼上去
        return res;
    }

    /**
     * 通过上面的字符串重新构建树（继续使用先序遍历的方式）
     * @param preStr
     * @return
     */
    public static Node reconByPreString(String preStr){
        String[] arr=preStr.split("!");  //分割!号
        Queue<String> queue=new LinkedList<>();
        for (int i = 0; i < arr.length; i++) {
            queue.offer(arr[i]);  //将字符串存入队列
        }
        return reconPreOrder(queue);  //通过这个方法来构建树
    }

    /**
     * 先序构建队列（用什么递归序列化的就用什么递归反序列化）
     * @param queue
     * @return
     */
    public static Node reconPreOrder(Queue<String> queue){
        String value=queue.poll();   //根据取出的值 判断是空还是值
        if("#".equals(value)){
            return null;
        }
        Node head=new Node(Integer.valueOf(value));  //先中序遍历一样构建对象
        head.left=reconPreOrder(queue);  //只要顺序和先序一样，那就能构造出先序树
        head.right=reconPreOrder(queue);
        return head;
    }

    /**
     * 按层序列化
     *           1
     *       2     3          序列化后的结果 1 2 3 4 null null 5 null null null null
     *    4   空 空  5
     * 空 空      空 空
     *
     * 思路：按照上面的顺序将head存入队列内，并且一次执行 同一个Node（一次执行一个） 的左右节点 判断，哪个不为空就将哪个节点加入队列，因为放入队列，所以先进先出，一直左右左右就一直有序。
     * 如上就能实现一层一层的写入
     *
     * 把一个head放入队列，通过循环队列是否有值来将head的左右节点分别再放入队列中。等到下一个节点进来就又再处理下一个节点的左右并放入队列（先进先出）
     * @param head
     * @return
     */
    public static String serialByLevel(Node head){
        if(head==null)
            return "#!";
        String res=head.value+"!";
        Queue<Node> queue=new LinkedList<>();
        queue.offer(head);
        while(!queue.isEmpty()){  //这一步，每进行一个while就是将下一层的所有节点都放入队列中
            head=queue.poll();
            if(head.left!=null){
                res += head.left.value+"!";
                queue.offer(head.left);
            }else{
                res +="#!";
            }

            if(head.right!=null){
                res+=head.right.value+"!";
                queue.offer(head.right);
            }else{
                res+="#!";
            }
        }
        return res;
    }

    /**
     * 反序列化字符串
     *
     * 基本思路：依旧是将head存入队列中，然后先将head的左右节点（left，right）都赋好值，再判断左右节点是否还存在左右节点，如果有就再存入队列。
     * @param levelStr
     * @return
     */
    public static Node reconByLevelString(String levelStr){
        String[] value=levelStr.split("!");
        int index=0;
        Node head=generateIntegerNodeByString(value[index++]);
        Queue<Node> queue=new LinkedList<>();
        if(head!=null){
            queue.offer(head);  //首先得先将头节点存入队列，不然进不去while
        }
        Node node=null;
        while(!queue.isEmpty()){
            node=queue.poll();   //将队列的head出去并赋值。当left不为空就放入队列等待下轮插入
//            先将head的左右都赋值好再去判断左右节点是否有值
            node.left=generateIntegerNodeByString(value[index++]);
            node.right=generateIntegerNodeByString(value[index++]);
            if(node.left!=null){  //等待下轮
                queue.offer(node.left);
            }
            if(node.right!=null){
                queue.offer(node.right);
            }
        }
        return head;
    }


    /**
     * 将String转成Integer 并生成一个Node
     * @param value
     * @return
     */
   public static Node generateIntegerNodeByString(String value){
        if("#".equals(value))
            return null;
        return new Node(Integer.valueOf(value));
   }

    public static Node generateNodeByString(String val) {
        if (val.equals("#")) {
            return null;
        }
        return new Node(Integer.valueOf(val));
    }

    // for test -- print tree
    public static void printTree(Node head) {
        System.out.println("Binary Tree:");
        printInOrder(head, 0, "H", 17);
        System.out.println();
    }

    public static void printInOrder(Node head, int height, String to, int len) {
        if (head == null) {
            return;
        }
        printInOrder(head.right, height + 1, "v", len);
        String val = to + head.value + to;
        int lenM = val.length();
        int lenL = (len - lenM) / 2;
        int lenR = len - lenM - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);
        System.out.println(getSpace(height * len) + val);
        printInOrder(head.left, height + 1, "^", len);
    }

    public static String getSpace(int num) {
        String space = " ";
        StringBuffer buf = new StringBuffer("");
        for (int i = 0; i < num; i++) {
            buf.append(space);
        }
        return buf.toString();
    }

    public static void main(String[] args) {
        Node head = null;
        printTree(head);

        String pre = serialByPre(head);
        System.out.println("serialize tree by pre-order: " + pre);
        head = reconByPreString(pre);
        System.out.print("reconstruct tree by pre-order, ");
        printTree(head);

        String level = serialByLevel(head);
        System.out.println("serialize tree by level: " + level);
        head = reconByLevelString(level);
        System.out.print("reconstruct tree by level, ");
        printTree(head);

        System.out.println("====================================");

        head = new Node(1);
        printTree(head);

        pre = serialByPre(head);
        System.out.println("serialize tree by pre-order: " + pre);
        head = reconByPreString(pre);
        System.out.print("reconstruct tree by pre-order, ");
        printTree(head);

        level = serialByLevel(head);
        System.out.println("serialize tree by level: " + level);
        head = reconByLevelString(level);
        System.out.print("reconstruct tree by level, ");
        printTree(head);

        System.out.println("====================================");

        head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.right.right = new Node(5);
        printTree(head);

        pre = serialByPre(head);
        System.out.println("serialize tree by pre-order: " + pre);
        head = reconByPreString(pre);
        System.out.print("reconstruct tree by pre-order, ");
        printTree(head);

        level = serialByLevel(head);
        System.out.println("serialize tree by level: " + level);
        head = reconByLevelString(level);
        System.out.print("reconstruct tree by level, ");
        printTree(head);

        System.out.println("====================================");

        head = new Node(100);
        head.left = new Node(21);
        head.left.left = new Node(37);
        head.right = new Node(-42);
        head.right.left = new Node(0);
        head.right.right = new Node(666);
        printTree(head);

        pre = serialByPre(head);
        System.out.println("serialize tree by pre-order: " + pre);
        head = reconByPreString(pre);
        System.out.print("reconstruct tree by pre-order, ");
        printTree(head);

        level = serialByLevel(head);
        System.out.println("serialize tree by level: " + level);
        head = reconByLevelString(level);
        System.out.print("reconstruct tree by level, ");
        printTree(head);

        System.out.println("====================================");

    }

}
