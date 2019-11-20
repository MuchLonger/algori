package learning.Day5;

import java.util.Arrays;

/**
 * @description: 给定一个字符串数组，输出字符串数组所有字符串拼接后“字典序”最小的数组
 * 字典序：笔记上找
 * @Time: 2019/11/13 22:18
 */
public class LowestDictionarySort {
    public static String[] arr={"bc","qw","a"};

    public static String lowestString(String[] strArr){
        if(strArr==null||strArr.length==0)
            return "";
        //注意str1和str2是字符串，需要比较的是两者拼接起来（str1+str2）和（str2+str1）的比较（注意顺序），！！而不能是（str1和str2的比较，局部最优不代表是总体最优）
        Arrays.sort(strArr,(str1,str2)->(str1+str2).compareTo(str2+str1));
        String result="";
        for (int i = 0; i < strArr.length; i++) {
            result+=strArr[i];
        }
        return result;
    }

    public static void main(String[] args){
        String[] strs1 = { "jibw", "ji", "jp", "bw", "jibw" };
        System.out.println(lowestString(strs1));

        String[] strs2 = { "ba", "b" };
        System.out.println(lowestString(strs2));
    }


}
