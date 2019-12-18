package codeBook.third_part_tree;

/**
 * @description: 将一个二叉搜索树后序遍历的数组重新构建成一个新的二叉搜索树。
 * 方法和14差不多，依旧是找到两个区域交界，然后end节点就是两个区域的头结点。
 * 这里就不需要判断是否存在 仅左子树或右子树 的情况了，因为都会构造（上面是因为需要判断less和more之间的距离）
 * @Time: 2019/12/18 14:18
 */
public class _15_PostArrToBST {
    public static Node posArrayToBST(int[] posArr) {
        if (posArr == null) {
            return null;
        }
        return postToBST(posArr, 0, posArr.length - 1);
    }

    private static Node postToBST(int[] posArr, int start, int end) {
        if (start > end) {
            return null;
        }

        Node head = new Node(posArr[end]);  // end节点就是当前树的头结点

        int less = -1;
        int more = end;

        // 依旧是找到左右区域
        for (int i = start; i < end; i++) {
            if (posArr[end] > posArr[i]) {
                less = i;
            } else {
                more = more == end ? i : more; // 仅设置一次
            }
        }

        // 左节点就是左区域的头结点，即当前节点的左节点
        head.left = postToBST(posArr, start, less);
        // 右节点就是左区域的头结点，即当前节点的右节点
        head.right = postToBST(posArr, more, end - 1);

        return head;
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 2, 5, 4, 8, 9, 7, 6};
        _4_PrintTreeOnConsole.printTree(posArrayToBST(arr));
    }

}
