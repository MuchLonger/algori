package codeBook.code_3_tree;

/**
 * @description: 通过无重复值的有序数组生成平衡二叉树
 * @Time: 2019/12/20 15:49
 */
public class _18_GenerateBalanceTree {

    /**
     * 直接找到数组的中间值，由中间值开始递归，左边就是左子树，右边就是右子树。
     * @param sortArr
     * @return
     */
    public static Node generateBalanceTree(int[] sortArr) {
        if (sortArr == null || sortArr.length == 0) {
            return null;
        }
        return generate(sortArr,0,sortArr.length-1);
    }

    private static Node generate(int[] sortArr, int start, int end) {
        if (start > end) {
            return null;
        }
        int mid=(start+end)/2;
        // 头结点就是mid
        Node head=new Node(sortArr[mid]);
        // 左就是左子树，右就是右子树
        head.left=generate(sortArr,start,mid-1); // mid-1，mid+1，因为mid已经生成过了
        head.right=generate(sortArr,mid+1,end);
        return head;

    }

    public static void main(String[] args){

    }

}
