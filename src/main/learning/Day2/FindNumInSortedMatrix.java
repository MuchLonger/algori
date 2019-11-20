package learning.Day2;

/**
 * @description: 在一个排好序的数组中找到指定值 O（N）
 * 排好序的数组：即上下都有序。如：
 * 1 3 5 7
 * 2 5 6 9
 * 4 8 9 11
 * @Time: 2019/10/28 21:09
 */
public class FindNumInSortedMatrix {
    /**
     * 上下有序的数组找到指定值
     *
     * 从右上角开始向左开始比较，如果相等就输出，如果输入数大于当前值就往下，输入数小于当前值就往左。重复上面动作就能找到
     * 如找到4，从7开始 小于7，向左来到5; 5大于4向左变成3; 3小于4，向下变成5; 5大于4，向左变成2; 2小于4，向下变成4，找到了
     * @param matrix
     * @param num
     * @return
     */
    public static int[] twoIndex(int[][] matrix,int num){
        int row=0;
        int col=matrix[0].length-1;  //row=0，col=matrix[0].length-1。代表的就是右上角的数
        while(row<matrix.length&&col>=0){
            if(matrix[row][col]==num){
                return new int[]{row,col};
            }else if(num<matrix[row][col]){  //小于就向左
                col--;
            }else if(num>matrix[row][col]){  //大于就向下
                row++;
            }
        }
        return new int[]{-1,-1};  //找不到返回-1;
    }

    public static void main(String[] args){
        int[][] matrix=new int[][]{ {1,3,5,7},{2,5,6,9},{4,8,9,11}};
        int[] num=twoIndex(matrix,4);
        System.out.println(num[0]+","+num[1]);
    }
}
