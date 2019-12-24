package codeBook.code_3_tree;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @description: 给定head，节点A,节点B。找到AB最近的公共祖先
 *       6
 *    2   3         假设A为4、B为1，公共祖先为6
 *  4 5  1 7
 * 后序遍历：左右中，由中来判断左右间的关系（虽然理论上来说是“左左右”）。
 *  做法：使用后序遍历（这样可以保证公共祖先是最近的，若使用先序遍历那公共祖先自然都会是根），判断左子树是否存在节点A或B，只要存在就返回当前节点，右子树也是这样。
 *  1）如果一个节点C的左子树包含A，右子树包含B（反过来也行），那最近的公共节点就是当前节点C。
 *  2）如果节点C的左子树为空，右子树不为空，那就返回左子树（反过来亦可）。
 *  3）如果左右子树都为空就返回空。
 * @Time: 2019/12/20 17:57
 */
public class _20_GetLowestAncestor {
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // 递归实现
    public static Node lowestAncestor(Node head, Node A, Node B) {
        if (head == null || head == A || head == B) {  // 子树只要遇到一个相等的节点就返回
            return head;
        }
        Node left = lowestAncestor(head.left, A, B);  // 找到左子树是否包含A（B）
        Node right = lowestAncestor(head.right, A, B); // 找到右子树是否包含B（A）
        if (left != null && right != null) {  // 如果都包含，那就返回当前节点（因为是后序遍历）
            return head;
        }
        return left != null ? left : right; // 返回谁不为空就返回谁，都为空就随便返回
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private static HashMap<Node,Node> map=new HashMap<>();
    /**
     * 通过hashMap保存每个节点的父节点，然后根据父节点的包含关系就能迅速得到AB的公共祖先。
     * 具体操作方法：因为map存的是“key子节点对应value父节点”，
     * 1）先使用set保存A向上的所有父节点。从而构建出一条路径
     * 2）然后B再看A的路径（set）是否包含B，如果不包含就向上查找B的父类再继续和A的路径（set）比较。
     */
    public static Node lowestAncestorWithMap(Node head,Node A,Node B){
        setMap(head); // 构造map
        HashSet<Node> path=new HashSet<>();
        while(map.containsKey(A)){  // 通过这个遍历构建出节点A的路径，
            path.add(A);
            A=map.get(A);  // A向上移动
        }
        while(!path.contains(B)){  // 如果路径不包含就再查看A路径是否包含B。遇到第一个包含的就退出。
            B=map.get(B);  // B向上移动
        }
        return B;
    }
    /**
     * 递归构建map， key=子节点，value=父节点 。
     */
    private static void setMap(Node head){
        if (head == null) {
            return ;
        }
        if (head.left != null) {
            map.put(head.left,head);
        }
        if (head.right != null) {
            map.put(head.right,head);
        }
        setMap(head.left);
        setMap(head.right);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     *             6
     *       4         7
     *    2   5          1
     *  9  3           8
     */
    public static void main(String[] args) {
        Node tn = new Node(6);
        tn.left = new Node(4);
        tn.right = new Node(7);
        tn.left.left = new Node(2);
        tn.left.right = new Node(5);
        tn.right.right = new Node(1);
        tn.right.right.left = new Node(8);
        tn.left.left.left = new Node(9);
        tn.left.left.right = new Node(3);

        System.out.println(lowestAncestorWithMap(tn, tn.left.left, tn.left.right).value);
        System.out.println(lowestAncestor(tn, tn.left.left, tn.left.right).value);
    }

}
