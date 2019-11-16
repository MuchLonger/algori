package Day5;

/**
 * @description: 打印字符串的所有子序列如 {a,b,c} 打印出 abc ab ac a bc b c
 * 思路：每遍历一个元素都做两个分支：
 * 分支一：加入当前元素，值为a
 * 分支二：不加入当前元素，输出""
 * @Time: 2019/11/15 22:53
 */
public class PrintAllSub {
    /**
     * 输出当前字符串的所有子序列
     * @param charArr 当前字符串拆分出来的char数组
     * @param i 字符串的索引
     * @param res 用于累加的字符串
     */
    public static void printAllSub(char[] charArr,int i,String res){
        if (i >= charArr.length) {  //当到达最后一个字符的时候才输出，然后结束
            System.out.println(res);
            return ;
        }
        // 分支一，加入当前元素
        printAllSub(charArr,i+1,res+charArr[i]);
        // 分支二，不加入，一直递归下去即可一分二二分四，得到所有子序列
        printAllSub(charArr,i+1,res+"");
    }
    public static void main(String[] args){
        String abc="abc";
        printAllSub(abc.toCharArray(),0,"");
    }
}
