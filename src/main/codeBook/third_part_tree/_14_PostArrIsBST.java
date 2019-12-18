package codeBook.third_part_tree;

/**
 * @description: 1）给定一个数组，判断这个数组的值是否是一颗二叉树后序遍历而来的
 * 中序遍历为：1 2 3 4 5 6 7 8 9的二叉树，
 * 后序遍历是 1 3 2 5 4 8 9 7 6，从中可看出存在一个连续区域（13254）是小于6的，一个连续区域是大于6的（897）
 * @Time: 2019/12/18 11:57
 */
public class _14_PostArrIsBST {
    public static boolean isPostArray(int[] arr) {
        if (arr == null || arr.length == 0) {
            return false;
        }
        return isPost(arr, 0, arr.length - 1);
    }

    /**
     * 后序遍历是 1 3 2 5 4 8 9 7 6，从中可看出存在一个连续区域（13254）是小于6的，一个连续区域是大于6的（897）。
     * 而13254，897又可继续细分，于是就递归寻找下去。还需要 只有左边节点的链或右边节点 情况，
     * 即结尾要么是最小值要么是最大值，故一定要注意顺序，
     *
     * 做法：1）找到左区域和右区域
     *       2）考虑只有左边节点的链或右边节点的情况
     *       3）不是2）的情况，那就判断左区域和右区域是否相邻，不是就为false
     *       4）递归左数组和右数组，结束条件为start=end，并返回true。
     * @param arr 原数组
     * @param start 数组的开头（移动）
     * @param end 数组的结尾（移动）
     * @return
     */
    private static boolean isPost(int[] arr, int start, int end) {
        if (start == end) {  // 如果能遍历到底那就是true，（中间不符合的会为false，一个false全部就会为false）
            return true;
        }

        int less = -1;  // less和more都设置为一个不可能的值
        int more = end;
        // 以下这一步是找到less（下一个递归结尾）和more（下一个递归开头）
        for (int i = start; i < end; i++) {
            if (arr[end] > arr[i]) {  // 找到最后
                less = i;
            } else {
                more = more == end ? i : more;  //more找到一次就不再继续找了
            }
        }

        // 如果是只有左边节点的链或右边节点的链(那样就less和more只会有一个被赋值）
        // 那数组结尾要么是最大值要么是最小值，故直接end-1即可（因为这样就不会执行return false操作从而一直为true）
        //        5
        //     3               如这种情况
        //  2   4
        if (less == -1 || more == end) {
            return isPost(arr, start, end - 1);  // 如图，就相当于 将节点5开头换成节点3开头 再继续考虑是否是搜索二叉树
        }

        // 如果小于区域和大于区域不是紧紧相邻的就直接为false
        if (less != more - 1) {
            return false;
        }
        // 递归寻找左数组和右数组。
        return isPost(arr,start,less)&&isPost(arr,more,end-1);
    }

    public static void main(String[] args){
        int[] arr={1,3,2,5,4,8,9,7,13};
        System.out.println(isPostArray(arr));
    }

}
