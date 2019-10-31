package Day1;

import Util.ArrUtil;
import Util.MathUitl;

/**
 * @description: 堆排序
 * 理解前提：父节点等于子节点减一除二 （父=(子-1）/2），子左等于父节点乘二加一（子左=父*2+1），子右等于父节点乘二加二（子右=父*2+2）
 * @Time: 2019/10/21 21:55
 */
public class HeapSort {
    static int[] arr = {72, 57, 42, 65, 57, 23, 57, 36, 83};

    /**
     * 堆排序
     * 实质就是将大根堆的顶部移到堆的最底部，并重新调整大根堆，再将大根堆的heapSize（堆大小）减一
     * 因为大根堆的顶部是整个堆的最大值，所以将其放到底部再将其剔除出堆就能实现从小到大排序。
     * 从大到小就需要小根堆了（方法是一样的）
     *
     * @param arr
     */
    public static void heapSort(int[] arr) {
        if (arr == null || arr.length < 2)
            return;
        for (int i = 0; i < arr.length; i++) {  //将整个数组构建成大根堆
            heapInsert(arr, i);  //构建大根堆
        }
        int heapSize = arr.length;
        MathUitl.swap(0, --heapSize, arr);  //堆顶的元素放到堆底
        while (heapSize > 0) {   //只要堆内还有元素就执行换位操作
            heapify(arr, 0, heapSize);  //重新构建大根堆
            MathUitl.swap(0, --heapSize, arr); //继续堆顶和堆底换位
        }
    }

    /**
     * 构建大根堆中一条路径代码（fori就是全部路径），当子节点比父节点大的时候就交换父子节点
     *
     * @param arr   构建大根堆的数组
     * @param index 当前节点下标
     */
    public static void heapInsert(int[] arr, int index) {
        while (arr[index] > arr[(index - 1) / 2]) {   //当一个子节点比父节点大时才执行
            MathUitl.swap(index, (index - 1) / 2, arr); //父子交换
            index = (index - 1) / 2;  //子节点的位置换到父节点
        }
    }

    /**
     * 当值修改时，将当前树修改成一个大根堆。值修改只需要关注修改的那一条链就好了。
     *
     * @param arr      要修改的数组
     * @param index    修改的下标（父节点）
     * @param heapSize 当前堆的长度（因为不一定整个数组都是堆）
     */
    public static void heapify(int[] arr, int index, int heapSize) {
        int left = index*2+1;  //父节点的左子节点
        while (left < heapSize) {  //设置左节点小于堆长度为循环
//            注意这个三目运算（！！！踩坑了），必须是 “left+1 < heapSize”才执行下一个&& 比较left+1与left大小。否则直接返回left。
            //左右节点比较获取最大的下标。left+1（右节点）必须小于堆长度，才会执行下一个&&（踩坑了）。
            int largest = left + 1 < heapSize && arr[left+1] > arr[left] ? left+1 : left;  //子右节点是否超过堆大小，以及子右和子左谁大取谁
            largest = arr[index] > arr[largest] ? index : largest; //如果父节点大于子节点，代表构建完成。否则则交换
            if (largest == index)  //没有进行交换，即父大于子
                break;
            MathUitl.swap(largest, index, arr);
            index = largest; //父变成子
            left = index * 2 + 1;  //再找到下一级
        }
    }

    public static void main(String[] args) {
        heapSort(arr);
        ArrUtil.show(arr);
    }

}
