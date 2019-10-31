package Util;

import teacherCode.class_03.Code_11_IsPalindromeList;

import java.util.Arrays;

/**
 * @description:
 * @Time: 2019/10/18 21:10
 */
public  class ArrUtil {

    /**
     * 显示一元数组的值
     * @param arr
     */
    public static void show(int[] arr){
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            if(i==arr.length-1){
                System.out.print(arr[i]+"]");
                System.out.println();
            }else{
                System.out.print(arr[i]+",");
            }
        }
    }
    public static void show(Integer[] arr){
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            if(i==arr.length-1){
                System.out.print(arr[i]+"]");
                System.out.println();
            }else{
                System.out.print(arr[i]+",");
            }
        }
    }

    /**
     * 输出二维数组
     * @param matrix 二维数组
     */
    public static void showTwo(int[][] matrix){
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println();
        }
    }
    /**
     * 生成随机数组
     * @param size   随机数组最大大小
     * @param value 随机数组取值范围[-value,value]
     * @return
     */
    public static int[] generateArr(int size,int value){
//        生成随机数量的数组
        int[] generateArr=new int[(int) ((size+1)*Math.random())];
        for (int i = 0; i < generateArr.length; i++) {
            generateArr[i]= (int) ((value+1)*Math.random()-(value+1)*Math.random());
        }
        return generateArr;
    }

    /**
     * 复制数组
     * @param arr 需要复制的数组
     * @return
     */
    public static int[] copyArray(int[] arr){
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    /**
     * 绝对成功的数组排序
     * @param arr
     */
    public static void rightMethod(int[] arr){
        Arrays.sort(arr);
    }

    /**
     * 判断两个数组是否相等
     * @param arr1
     * @param arr2
     * @return
     */
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 打印链表数据
     * @param node
     */
    public static void printLinkedList(Node node) {
        System.out.print("Linked List: ");
        while (node != null) {
            System.out.print(node.value + " ");
            node = node.next;
        }
        System.out.println();
    }
}
