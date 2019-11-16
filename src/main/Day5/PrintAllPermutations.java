package Day5;

import Util.MathUitl;

/**
 * @description: 打印数组的全排列，如数组{a,b,c}，打印abc cba .....
 *
 * @Time: 2019/11/15 23:07
 */
public class PrintAllPermutations {
    public static void printAllPer(String str){
        char[] charArr=str.toCharArray();
        process(charArr,0);
    }

    /**
     * 求数组的全序列
     *
     * 思路：假设当前索引是index，以当前索引index开始，一直与后面的元素交换位置，到底就输出。
     * 示例：{a,b,c}，如当前索引是0（a），从0开始遍历到底，每遍历一个就分别和当前索引(0)交换位置，
     * 然后索引继续向下，在回来的时候需要将源数组复原（即回到输出的样子）
     * @param charArr
     * @param index
     */
    private static void process(char[] charArr, int index) {
        if (index == charArr.length) {
            System.out.print(String.valueOf(charArr)+",");
        }
        for (int i = index; i < charArr.length;i++) {  //遍历当前索引往下的字母
            swap(charArr,i,index);  //从当前索引开始（包括当前索引）交换位置，每交换一次位置都会有一个分支，然后就可以输出不同的值。
            process(charArr,index+1);  //转移至下个索引，下个索引也像上面一样操作，到达底部就会输出。
            swap(charArr,i,index);  //数组存储的元素复位
        }

    }
    public static void swap(char[] chs, int i, int j) {
        char tmp = chs[i];
        chs[i] = chs[j];
        chs[j] = tmp;
    }
    public static void main(String[] args){
        printAllPer("abc");
    }
}
