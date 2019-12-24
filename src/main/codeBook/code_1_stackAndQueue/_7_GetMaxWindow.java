package codeBook.code_1_stackAndQueue;

import Util.ArrUtil;

import java.util.LinkedList;

/**
 * @description: 当前有一个整型数组arr和一个大小为w的窗口从数组左边滑动到右边，一次滑动一个位置
 * 问题：实现一个输入arr和w，返回一个arr.length-w-1长度的res，res内存的是每个窗口的最大值。
 * {[4,3,5],4,3,3,6,7}  最大值=5
 * {4,[3,5,4],3,3,6,7}  最大值=5
 * {4,3,[5,4,3],3,6,7}  最大值=5 以此类推存入数组然后返回
 * <p> qmax双向队列特性：1）存放的是数组索引，2）数组最大值放前面（小于的值直接poll），3）数组索引从小到大
 * 解法：维护一个qmax的“双向队列”，用来表示当前窗口的最大值对应的数组下标索引，最大值放在队首，qmax内严格遵守大值的索引在前面，并且索引是有顺序的（因为是从前往后滑动，并且会顺序删除元素，这个是为了解释后面的w-i==peekFirst就表示peekFirst已过期）！！。
 * 且qmax内就是窗口最大值的降序集合。最后返回队首就是当前窗口的最大值。
 * @Time: 2019/12/3 17:04
 */
public class _7_GetMaxWindow {
    public static int[] arr = {4, 3, 5, 4, 3, 3, 6, 7};

    public static int[] getMaxWindow(int[] arr, int w) {
        if (arr == null || w < 1 || arr.length < w) {
            return null;
        }
        LinkedList<Integer> qmax = new LinkedList<>();
        int[] res = new int[arr.length - w + 1];
        int index = 0;
        for (int i = 0; i < arr.length; i++) {  // 一步一步滑动arr
            while (!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[i]) {  //qmax内遇到小于当前值（滑动到的数组的值）的直接出抛出poll。因为小于它的不必留着，它就是最大的，反正滑动到数组下一个元素的时候又会添加。
                qmax.pollLast();  // 直接抛出，这样可以保证qmax的索引顺序是从小到大
            }
            qmax.addLast(i);  // 将值放入qmax对应的位置。
            if (qmax.peekFirst() == i - w) {  // i-w就是上一个窗口开头索引，其中i是会改变的，所以是会一直向前滑的。peekFirst是索引最小的且值最大的，这个就表示peekFirst的索引值以过期，需要去掉
                qmax.pollFirst(); // 因为first是索引最小，所以先从它开始判断，如果等于上一个窗口开头就代表这个已经是上个窗口的值了，需要被删除掉
            }
            if (i >= w - 1) {  // 当构成一个窗口时（i大于2）才会将值放入arr
                res[index++] = arr[qmax.peekFirst()];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] res = getMaxWindow(arr, 3);
        ArrUtil.show(res);
    }
}
