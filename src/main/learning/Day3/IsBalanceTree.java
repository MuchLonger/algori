package learning.Day3;

/**
 * @description: 递归判断是否是平衡二叉树,
 * 平衡二叉树：任意一颗节点的左子树和右子树的高度差值小于等于1
 * @Time: 2019/11/3 12:28
 */
public class IsBalanceTree {
    public static class Node{
        public int data;
        public Node left;
        public Node right;
        public Node(int data){
            this.data=data;
        }
    }

    public static class ReturnData{
        public boolean isBalance;
        public int high;

        public ReturnData(boolean isBalance, int high) {
            this.isBalance = isBalance;
            this.high = high;
        }
    }
    public static boolean isBalance(Node head){
        return process(head).isBalance;  //直接调用即可
    }
    /**
     * 递归需要获取的是：1）左子树是否平衡 2）右子树是否平衡 3）左子树高度 4）右子树高度
     * 基本思想：左子树平衡且右子树也平衡 就判断左子树高度和右子树高度的差值，如果小于一，则当前节点平衡 返回当前节点的高度。如果大于一就 返回false。
     * @param head
     * @return
     */
    public static ReturnData process(Node head){
        if(head==null)   //如果当前节点为空，直接返回为真，因为空树也是平衡二叉树。这一句话主要用于一棵树的最底部那几个节点
            return new ReturnData(true,0);
        ReturnData left=process(head.left);  //求出左子树的高度 以及 是否是平衡树
        if(!left.isBalance){  //如果右边不是平衡树，直接返回false。这个isBalance的设置值是通过下面比较差值来决定的。
            return new ReturnData(false,0);
        }
        ReturnData right=process(head.right);  //继续判断右子树是否是平衡树
        if(!right.isBalance){  //右子树不是平衡的也直接返回false。
            return new ReturnData(false,0);
        }
        if(Math.abs(left.high-right.high)>1){  //判断高度，
            return new ReturnData(false,0);
        }
        //经过上面就能判断当前是不是平衡树，因为如果不是就会返回false，然后递归返回一直是false

        //最后还缺少“两颗子树都是平衡树”的情况：比较两颗子树高度，取最高高度+1就是当前子树的高度！
        return new ReturnData(true,Math.max(left.high,right.high)+1);

    }
    public static void main(String[] args){
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);

        System.out.println(isBalance(head));
    }

}
