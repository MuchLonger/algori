package codeBook.third_part_tree;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 给定一颗二叉树，求累加和为k的最长路径长度。其中路径指的是 每次最多选择一个孩子节点或者不选择节点而形成的链
 * 理解本篇时先看这个类：eighth_part_arrAndMatrix.GetMaxLengthInCommonArr
 * <p>
 * 依旧是 s(j+1...i)=s(i)-s(j); 设s(j...i)为k，那只需要求出s(i)，s(j)即可，将s(j)保存到map内，s(i)就是sum，一切就求出来了。
 * @Time: 2019/12/16 17:13
 */
public class _6_GetMaxHeight {

    public static int getMaxHeight(Node head, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 0);  // 用来保证第一个节点（头结点）也会被算入sum-k之中
        return preOrder(head, k, 0, 1, 0, map);
    }

    /**
     * 求出和为k的当前树的最长路径
     *
     * @param head      树的头结点
     * @param k         和为k
     * @param preSum    上 level 层的全部路径和
     * @param level     当前是第几层
     * @param maxLength 当前最长路径是多少
     * @param map       保存了key为sum-k，value为高度
     * @return
     */
    private static int preOrder(Node head, int k, int preSum, int level,
                                int maxLength, Map<Integer, Integer> map) {
        if (head == null) {
            return maxLength;
        }
        int curSum = preSum + head.value;  //求出当前值（即sum）

        if (!map.containsKey(curSum)) {  // 第一次遇到的值就放入map
            map.put(curSum, level);
        }
        if (map.containsKey(curSum - k)) {  // 公式  s(j+1...i)=s(i)-s(j)
            maxLength = Math.max(maxLength, level - map.get(curSum - k));
        }

        // 遍历左右子树
        maxLength = preOrder(head.left, k, curSum, level + 1, maxLength, map);
        maxLength = preOrder(head.right, k, curSum, level + 1, maxLength, map);

        // 每次递归的退出都需要清除“当前累加和”对应的路径。因为这是递归添加的，而map内一个值只需要添加最早加入那个即可，
        // 防止数据冲突，因为可能会将小值给覆盖掉了（会将最长的路径给覆盖掉）
        if (level == map.get(curSum)) {
            map.remove(curSum);
        }
        return maxLength;
    }

    /**
     *           6
     *      4         7
     *   2   5      9
     * 1 3       8
     */
    public static void main(String[] args) {
        Node tn = new Node(6);
        tn.left = new Node(4);
        tn.right = new Node(7);
        tn.left.left = new Node(2);
        tn.left.right = new Node(5);
        tn.right.right = new Node(9);
        tn.right.right.left = new Node(8);
        tn.left.left.left = new Node(1);
        tn.left.left.right = new Node(3);

        System.out.println(getMaxHeight(tn, 17));
    }
}
