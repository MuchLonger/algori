import Util.ArrUtil;
import org.junit.Test;

import static learning.Day1.BulSort.bulSort;
import static Util.ArrUtil.*;

/**
 * @description:
 * @Time: 2019/10/19 21:53
 */
public class Sort {
    int[] arr = {72, 57, 42, 65, 57, 23, 57, 36, 83};

    /**
     * 测试代码生成器
     */
    @Test
    public void m1() {
        int testTime = 5000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateArr(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            bulSort(arr1);
            rightMethod(arr2);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");

        int[] arr = generateArr(maxSize, maxValue);
//        ArrUtil.show(arr);
        bulSort(arr);
//        selSort(arr);
//        insertSort(arr);
        ArrUtil.show(arr);
    }

}
