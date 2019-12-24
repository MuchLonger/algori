package codeBook.code_3_tree;


import java.util.LinkedList;
import java.util.Queue;

/**
 * @description: 层次序列化一棵树以及反序列化一棵树
 * 序列化：使用String表示一棵树
 * 反序列化：读取String重新构建一棵树
 * @Time: 2019/12/14 16:00
 */
public class _5_SerialByLevel {

    /**
     * 通过队列层次遍历一棵树，并实现序列化。
     */
    public static String serialByLevel(Node head) {
        if (head == null)
            return "";
        String res = head.value + "!";  //使用!分隔
        Queue<Node> queue = new LinkedList<>();
        queue.offer(head);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            // 和先序遍历非递归很像，但是使用的是队列
            if (node.left != null) {
                res += head.left.value + "!";
                queue.offer(node.left);
            }

            if (node.right != null) {
                res += head.right.value + "!";
                queue.offer(node.right);
            }
            // 为null就用 "#" 来填充
            if (head.left == null || head.right == null) {
                res += "#";
            }
        }
        return res;
    }

    public static Node reconstructByLevelString(String levelStr){
        String[] values=levelStr.split("!");
        int index=0;
        Node head=generateNodeByString(values[index++]);
        Queue<Node> queue=new LinkedList<>();
        if (head != null) {
            queue.offer(head);
        }
        Node node=null;
        while (!queue.isEmpty()){
            node=queue.poll(); // 入队的left会立刻poll出去
            node.left=generateNodeByString(values[index++]); //  和构建一样重建
            node.right=generateNodeByString(values[index++]);
            if(node.left!=null){    //  和构建一样重建
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        return head;
    }

    /**
     * 通过一个字符串构建一个节点
     */
    public static Node generateNodeByString(String val){
        if(val.equals("#"))
            return null;
        return new Node(Integer.valueOf(val));
    }
    public static void main(String[] args){

    }

}
