package codeBook.third_part_tree;

/**
 * @description: 给定一个数字num，表示一个从1到num的数组[1,2,....,num}，请返回以这个数组为中序遍历数组的二叉树的结构有多少。
 * 注意，因为是按照顺序，所有这是一颗二叉搜索树。
 * 解决方法：
 * 根据数组{1,...,i,...num}可分为三种情况
 * 1）如果1作为头结点，那因为不能有左子树（二叉搜索树），所以所有的结构都依靠右子树，因为右子树有num-1个节点，故共有numTrees(num-1)种方法
 * 2）如果i作为头结点，那左子树有i-1个节点，右子树有num-i个节点，所以共有numTrees(i-1)*numTrees(num-i)种方法（注意是乘法）
 * 3）如果num作为头结点，那不存在右子树，又因为左子树有num-1个节点，故有numTrees(num-1)种方法。
 * 上面所有加起来就是答案。上面所有情况可以都可以用 numTrees(i-1) * numTrees(num-i) 表示（左子树为0或右子树为0 就是0,）
 * @Time: 2019/12/23 16:12
 */
public class _26_NumTrees {
    private static int n = 0;

    // 递归版本错误的（百思不得其解）
    public static int numTreesCur(int num, int j) {
        /*if (j < 2)
            return 1;
        for (int i = 1; i < num + 1; i++) {
            n += numTreesCur(i, j - 1) * numTreesCur(i, num - j);
        }*/
        return n;
    }

    /**
     * 使用动态规划的方法：
     * 根据数组{1,...,i,...num}可分为三种情况
     * 1）如果1作为头结点，那因为不能有左子树（二叉搜索树），所以所有的结构都依靠右子树，因为右子树有num-1个节点，故共有numTrees(num-1)种方法
     * 2）如果i作为头结点，那左子树有i-1个节点，右子树有num-i个节点，所以共有numTrees(i-1)*numTrees(num-i)种方法（注意是乘法）
     * 3）如果num作为头结点，那不存在右子树，又因为左子树有num-1个节点，故有numTrees(num-1)种方法。
     * 上面所有加起来就是答案。上面所有情况可以都可以用 numTrees(i-1) * numTrees(num-i) 表示（左子树为0或右子树为0 就是0,）
     */
    public static int numTrees(int num) {
        if (num < 2) {
            return 1;  //  1个节点自然只有一棵树
        }
        int[] arr = new int[num + 1];  // 可变参数仅num，故为一维
        arr[0] = 1;  // 终止条件赋值
        for (int i = 1; i < num + 1; i++) {   // 最终状态为这个，一步步走到num上
            for (int j = 1; j < i + 1; j++) { // 这两个for循环也构成了普遍位置，
                arr[i] += arr[j - 1] * arr[i - j];  // 这里的i就充当了num，j充当了i的位置，在对应位置上已经赋好值了
            }
        }
        return arr[num];
    }

    public static void main(String[] args) {
        System.out.println(numTreesCur(3, 3));
    }
}
