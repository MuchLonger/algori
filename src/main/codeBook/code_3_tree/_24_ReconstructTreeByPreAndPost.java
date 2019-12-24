package codeBook.code_3_tree;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 通过 先序数组,后序数组 重建二叉树
 * 有一种情况通过“先序数组+后续数组”无法重建如：[1,2].[2,1]。
 * 所以得出结论：一颗二叉树除了叶节点之外，其他所有的节点都有左孩子和右孩子，这样才能被构建出来。因为无法分辨上面的情况
 * pre：{1, 2, 4, 5, 8, 9, 3, 6, 7}，pos：{4, 8, 9, 5, 2, 6, 7, 3, 1}。pos的最后一个是尾节点，pre的1后面那个就是左节点（因为限制了所有节点都有左右孩子），所有就能成功构建出来了
 * @Time: 2019/12/22 16:12
 */
public class _24_ReconstructTreeByPreAndPost {

    // 这里没做显示判断：“一颗二叉树除了叶节点之外，其他所有的节点都有左孩子和右孩子”，因为可以构造出来，但是有很多种结构。
    public static Node prePostToTree(int[] pre, int[] pos) {
        if (pre == null || pos == null) {
            return null;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < pos.length; i++) {   // 将后序遍历的数组值和索引记录
            map.put(pos[i], i);
        }
        return prePost(pre, 0, pre.length - 1,
                pos, 0, pos.length - 1,
                map);
    }

    // pre：{1, 2, 4, 5, 8, 9, 3, 6, 7}，
    // pos：{4, 8, 9, 5, 2, 6, 7, 3, 1}。
    // pos的最后一个是尾节点，pre的1后面那个(2)就是左节点（因为限制了所有节点都有左右孩子），后序遍历中从4到2就是左子树，2到3就是右子树
    private static Node prePost(int[] pre, int preStart, int preEnd,
                                int[] pos, int posStart, int posEnd,
                                Map<Integer, Integer> map) {
        Node head = new Node(pos[posEnd--]);
        if (preStart == preEnd) {
            return head;
        }
        int posIndex = map.get(pre[++preStart]);   // 1之后那个节点（先序遍历头结点后面那个节点就是左节点），也就是2,2之前是左子树，2之后是右子树
        head.left = prePost(pre, preStart, preStart + (posIndex - posStart),
                pos, posStart, posIndex,
                map);
        head.right = prePost(pre, preStart + (posIndex - posStart) + 1, preEnd,
                pos, posIndex + 1, posEnd,
                map);
        return head;
    }

    public static void main(String[] args){
        int[] pre={1, 2, 4, 5, 8, 9, 3, 6, 7};
        int[] pos={4, 8, 9, 5, 2, 6, 7, 3, 1};

        _4_PrintTreeOnConsole.printTree(prePostToTree(pre,pos));
    }
}
