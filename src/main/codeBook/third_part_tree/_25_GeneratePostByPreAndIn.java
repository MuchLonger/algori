package codeBook.third_part_tree;

import Util.ArrUtil;
import Util.MathUitl;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 通过 先序数组 和 中序数组 生成 后序数组，不能重新构建二叉树。
 * 举例说明：先序遍历[1,2,4,5,3,6,7]，中序遍历[4,2,5,1,6,3,7]
 * 将先序遍历数组的[0]位 放到 后续遍历数组的[6]上
 * 再通过中序遍历数组分割出左右子树，如：左子树先序遍历[2,4,5]中序遍历[4,2,5]，右子树[3,6,7]，[6,3,7]。
 * 然后先从右子树开始插入，即右子树的3插入到“后序遍历数组[5]”上，再继续分割，7插入[4]。
 *
 * @Time: 2019/12/23 15:02
 */
public class _25_GeneratePostByPreAndIn {

    public static int[] getPosArray(int[] pre, int[] in) {
        if (pre == null || in == null) {
            return null;
        }
        int preLen = pre.length;
        int[] pos = new int[preLen];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < in.length; i++) {
            map.put(in[i], i);  // 将中序遍历放入map
        }
        setPos(pre, 0, preLen - 1, in, 0, preLen - 1, pos, preLen - 1, map);
        return pos;
    }


    //   根据先序数组和中序数组，设置后序数组最右边的值，然后再划分出{左子树的先序数组和中序数组}和{右子树的先序数组和中序数组}
    // 再先根据“右子树”（注意是先右子树），再到左子树划分，从“后面开始向前”设置后序数组
    private static int setPos(int[] pre, int preStart, int preEnd,
                               int[] in, int inStart, int inEnd,
                               int[] pos, int posEnd,
                               Map<Integer, Integer> map) {
        if (preStart > preEnd) {
            return posEnd ;
        }
        pos[posEnd--]=pre[preStart];
        int inIndex=map.get(pre[preStart]);
        posEnd=setPos(pre,preStart+(inIndex-inStart)+1,preEnd,  // 从inIndex开始，用上面的例子来说就是：得到先[3,6,7]和中[6,3,7]。
                in,inIndex+1,inEnd,
                pos,posEnd,map);   // 这一步是先从右子树开始从后往前赋值，注意记录posEnd，不然右子树赋完值就找不到左子树的值了！
        return setPos(pre,preStart+1,preStart+(inIndex-inStart),  // 注意：是preStart+(inIndex-inStart)，不需要-1，是从0到inIndex
                in,inStart,inIndex,
                pos,posEnd,map);  // 计算左子树
    }

    public static void main(String[] args){
        int[] pre = {1,2,4,5,3,6,7};
        int[] in = {4,2,5,1,6,3,7};
        ArrUtil.show(getPosArray(pre,in));
    }
}
