package codeBook.third_part_tree;

/**
 * @description: 控制台上打印二叉树：难点在于字符串的构建，
 * 二叉树：
 *                                                          v11v
 *                                         v10v
 *                                                           ^9^
 *                         v8v
 *                                          ^7^
 *                                                           ^6^
 *        H5H
 *                                          v4v
 *                         ^3^
 *                                          ^2^
 *                                                           ^1^
 * @Time: 2019/12/14 15:15
 */
public class _4_PrintTreeOnConsole {

    public static void printTree(Node head) {
        System.out.println("二叉树： ");
        // 因为Integer最大长度为32，加上"HH" "vv" "^^"这些标签韩去除34，len就是表示左右分别空格多少
        printInOrder(head, 0, "H", 17);   // 头结点是最后一个输出的
        System.out.println();
    }

    /**
     * 一层一层打印二叉树
     *
     * @param head   当前节点
     * @param height 当前节点到最底的高度
     * @param to     "HH" "vv" "^^"，表示使用这些之中的哪些符号
     * @param len    需要填充多少个空格。v22v 就是要左填充13个空格，右填充13个空格
     */
    private static void printInOrder(Node head, int height, String to, int len) {
        if (head == null) {
            return;
        }
        printInOrder(head.right, height + 1, "v", len);   // 先从右边开始遍历，仅为了美观

        // 构建字符串，包括计算空格 以及 构建值的样式"HH" "vv" "^^"
        String val = to + head.value + to;  // 构建 v234v 这样格式，表示是右子树
        int lengthOfString = val.length();
        int lengthOfLeft = (len - lengthOfString) / 2; // (17-4)/2=7  ：计算左边需要多少空格
        int lengthOfRight=len-lengthOfString-lengthOfLeft; // 17-4-7=6  ：计算右边需要多少空格
        val=getSpace(lengthOfLeft)+val+getSpace(lengthOfRight);

        System.out.println(getSpace(height*len)+val);  // 注意这个乘法，这样就能保证每一层随高度变化而增加，并且是直接填充空格

        printInOrder(head.left,height+1,"^",len);  // 再遍历左边
    }

    /**
     * 返回指定个数的空格
     */
    public static String getSpace(int num){
        String space=" ";  // 计算添加多少空格
        StringBuilder buf=new StringBuilder("");
        for (int i = 0; i < num; i++) {
            buf.append(space);
        }
        return buf.toString();
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
        printTree(head);
    }
}
