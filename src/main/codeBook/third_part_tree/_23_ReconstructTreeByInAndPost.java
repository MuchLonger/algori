package codeBook.third_part_tree;

import java.util.HashMap;

/**
 * @description: 通过 中序数组,后序数组 重建二叉树
 * 做法和先序数组中序数组一直，因为后续数组的头结点就是在数组的最后.
 * 中序遍历数组A：[4,2,8,5,9,1,6,3,7]，后序遍历数组B：[4,8,9,5,2,6,7,3,1]。
 * 后序遍历左右子树依旧是分为[4,8,9,5,2],[6,7,3]
 * @Time: 2019/12/22 16:10
 */
public class _23_ReconstructTreeByInAndPost {

    public static Node inPostToTree(int[] in, int[] pos) {
        if (in == null || pos == null) {
            return null;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < in.length; i++) {
            map.put(in[i], i);
        }
        return inPos(pos, 0, pos.length - 1,
                in, 0, in.length - 1,
                map);
    }

    private static Node inPos(int[] pos, int pS, int pE,
                              int[] in, int iS, int iE,
                              HashMap<Integer, Integer> map) {
        if (pS > pE) {
            return null;
        }
        Node head = new Node(pos[pE]);
        int inIndex = map.get(pos[pE]);
        head.left = inPos(pos, pS, pS + (inIndex - iS) - 1,
                in, iS, inIndex - 1
                , map);   // 到这里都基本没变化
        head.right = inPos(pos, pS + (inIndex - iS), pE-1,  // 这一行有变化，因为头结点不是在中间而是在结尾，所以使用的不是pS+(inIndex-iS)+1，而是pS+(inIndex-iS)，结尾也需要-1，因为结尾被头结点占用了
                in, inIndex + 1, iE
                , map);
        return head;
    }

    public static void main(String[] args) {
        int[] pos = {4, 8, 9, 5, 2, 6, 7, 3, 1};
        int[] in = {4, 2, 8, 5, 9, 1, 6, 3, 7};
        _4_PrintTreeOnConsole.printTree(inPostToTree(in, pos));
    }
}
