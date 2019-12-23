package codeBook.third_part_tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 给定一个num表示一个从{1,2,..,num}的数组，它表示一棵二叉树的中序遍历顺序，求所有中序遍历顺序为这个数组的二叉树头结点。
 * @Time: 2019/12/23 17:06
 */
public class _27_GenerateTreesByNum {

    /**
     * 从1到n生成一棵树
     */
    public static List<Node> generateTrees(int n) {
        return generate(1, n);
    }

    /**
     * 生成中序遍历为{start,...,end}的树的节点。
     * 然后再递归得到左孩子节点就是 (start到(i-1))和右孩子为((i+1)到end))
     * @param start
     * @param end
     * @return
     */
    private static List<Node> generate(int start, int end) {
        List<Node> res = new ArrayList<>();
        if (start > end) {
            res.add(null);
        }
        Node head = null;
        for (int i = start; i < end + 1; i++) {
            head = new Node(i);  // 以i为头结点开始依次构建节点直到num
            List<Node> lSubs = generate(start, i - 1);    // 得到了所有以左孩子节点为根的树
            List<Node> rSubs = generate(i + 1, end);  // 得到了所有以右孩子节点为根的树


            for (Node l : lSubs) {
                for (Node r : rSubs) {  // 每个左孩子和右孩子自由组合，都能构成一种新的树，再加入到节点
                    head.left = l;
                    head.right = r;
                    res.add(cloneTree(head));  // 使用深拷贝，不然List仅会保存一个数组
                }
            }
        }
        return res;
    }

    /**
     * 深拷贝一棵树
     */
    private static Node cloneTree(Node head) {
        if (head == null) {
            return null;
        }
        Node node = new Node(head.value);
        node.left = cloneTree(head.left);
        node.right = cloneTree(head.right);
        return head;
    }

    public static void main(String[] args) {
        List<Node> nodes = generateTrees(3);
        for (Node node : nodes) {
            System.out.print(node.value + ",");
        }
    }
}
