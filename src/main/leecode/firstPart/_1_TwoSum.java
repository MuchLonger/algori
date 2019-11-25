package leecode.firstPart;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 1.两数之和：给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 * @Time: 2019/11/20 10:41
 */
public class _1_TwoSum {
    public static int[] twoSum(int[] arr,int target){
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i],i);
        }
        for (int i = 0; i < arr.length; i++) {
            int otherNum=target-arr[i];
            if (map.containsKey(otherNum)&&map.get(otherNum)!=i) {
                return new int[]{i,map.get(otherNum)};
            }
        }
        return new int[2];
    }
    public static void main(String[] args){
        int[] w=new int[]{2,7,19,15};
        int[] ints = twoSum(w, 9);
        System.out.println(ints[0]+"+"+ints[1]);
    }
}
