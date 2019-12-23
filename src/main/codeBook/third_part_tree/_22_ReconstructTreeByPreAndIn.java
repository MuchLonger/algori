package codeBook.third_part_tree;

import java.util.HashMap;

/**
 * @description: 1）通过 先序数组,中序数组 重建二叉树
 * @Time: 2019/12/22 15:13
 */
public class _22_ReconstructTreeByPreAndIn {

    /**
     * 先序遍历加中序遍历重建二叉树
     */
    public static Node preInToTree(int[] pre, int[] in) {
        if (pre == null || in == null) {
            return null;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < in.length; i++) {  //  将 中序遍历的值为key，索引为value放入map，方便找到对应的下标
            map.put(in[i], i);
        }
        return preIn(pre, 0, pre.length - 1, in, 0, in.length - 1, map);
    }

    /**
     * 举例说明：
     * 先序遍历数组A：[1,2,4,5,8,9,3,6,7]，中序遍历数组B：[4,2,8,5,9,1,6,3,7]。
     * 先找到A 第一个节点1就是头结点，再从 B 找到对应1的位置的左边数组[4,2,8,5,9]，长度为5（构成左节点）和1的右边数组[6,3,7]（构成右节点），长度为3
     * 左子树的先序遍历的就为1后面的5个（上面的中序遍历左数组的长度）元素[2,4,5,8,9]，右子树的先序遍历就为9后面长度为3的[3,6,7]
     * <p>
     * pS+（inIndex-iS）得到的就是 “1后面的5个元素”的最后一个元素的索引。
     *
     * @param pre 先序遍历数组
     * @param pS  先序遍历数组的有效部分的开头（因为不是全部都会用上，比如说左子树是左边部分 故是a到b，右子树是右边部分的b+1到c）
     * @param pE  先序遍历数组的有效部分的结尾
     * @param in  中序遍历数组
     * @param iS  中序遍历数组的有效部分的开头
     * @param iE  中序遍历数组的有效部分的结尾
     * @param map key为中序遍历值，value为中序遍历索引
     */
    private static Node preIn(int[] pre, int pS, int pE,
                              int[] in, int iS, int iE,
                              HashMap<Integer, Integer> map) {
        if (pS > pE) {  // 跳出条件
            return null;
        }
        Node head = new Node(pre[pS]);
        int inIndex = map.get(pre[pS]);
        // 先序遍历数组，A的头+1(索引)，头+左子树中序遍历长度(索引)，中序遍历数组，中序遍历开头，"1"前面一个
        head.left = preIn(pre, pS + 1, pS + (inIndex - iS),    // inIndex-iS，就是中序遍历数组 [1]-[4]得到的差值，就是length，
                in, iS, inIndex - 1,
                map);
        // 先序遍历数组，剩下的后面的部分开始，结束，中序遍历数组，就是inIndex+1，结束
        head.right = preIn(pre, (pS + (inIndex - iS)) + 1, pE,
                in, inIndex + 1, iE,
                map);
        return head;
    }

    public static void main(String[] args) {
        int[] pre = {1, 2, 4, 5, 8, 9, 3, 6, 7};
        int[] in = {4, 2, 8, 5, 9, 1, 6, 3, 7};
        _4_PrintTreeOnConsole.printTree(preInToTree(pre, in));
        _1_PrintTree.postOrderNoRecur(preInToTree(pre, in));
    }
}
