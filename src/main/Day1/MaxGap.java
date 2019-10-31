package Day1;

/**
 * @description: 求排序之后的 相邻 两数的最大差值：如{2,7,9,10} ：2 7差值5, 7 9差值2，9 10差值1，最后输出5
 * 时间复杂度 O（N） 空间复杂度O（N）
 * @Time: 2019/10/22 22:56
 */
public class MaxGap {

    /**
     * 求最大差值，借用桶的思路：先找到数组的最小值和最大值，以最小值为和最大值为范围，构建一列桶，然后再将其根据bucket划分为n个范围
     * 有N个范围 分配N+1个桶，那肯定有一个桶内的是空值（因为N个范围存N个桶余出一个），通过这一步将“最大差值”在同一个桶内的情况排除
     * 原因是：因为必定有个空桶，而每个桶的范围是一样的（差值肯定在范围之内），那“跨一个空桶的范围”肯定比“一个桶内的范围”大，借此来排除“最大差值”在一个桶内的情况
     * 每个桶内分别有hasNum（用来记录桶内是否有值），mins（当前范围中桶最小值），maxs（当前范围中桶最大值）。
     * 之后再遍历数组每个数，将这些数放入桶中（bucket），之后这些数与桶中的值比较 查看传入的值是否最大值或是最小值，是就重新设置，并将hasNum设为true表示有值
     * 之后通过上一个非空桶的最大值和当前非空桶的最小值相减，就能得到最大相邻差值（一定是上一个最大 下一个最小才能保证相邻）
     *
     * 核心思想：1.生成N+1个桶排除同一个桶的情况。2.上一个非空最大和当前非空最小的差值就是 两个桶间相邻最大差值（一直遍历桶就能找到数组最大差值）
     *
     * 如果有疑惑：为什么是上一个减当前 ==》因为当前肯定是非空的，这是因为如果遇到空的就会跳过（if）。
     *      *      疑惑2：既然非空桶和空桶之间的间隔那么大，那相差的最大值不就是在非空桶与空桶之间了吗？
     *      *      ==》不是，如“ 0-10 11-20 21-30 31-40 ”假设一个值为9（一号桶），11-21二号桶为空桶，一个值为21（三号桶），一个值为39（四号桶）：这样非空桶和空桶的差值仅为12，而非空桶和非空桶的差值为18（所以不对）
     *      *
     * @param nums 数组必须有序
     * @return
     */
    public static int maxGap(int[] nums) {
        if (nums == null || nums.length < 2)
            return 0;
        int len = nums.length; //分配桶的数量，即为nums的长度
        int min = Integer.MAX_VALUE;  //储存数组最小值，默认为int最大值（没有比它大的了）
        int max = Integer.MIN_VALUE;  //储存数组最大值
        for (int i = 0; i < len; i++) {
            min = Math.min(min, nums[i]);
            max = Math.max(max, nums[i]);
        }
        if (min == max)  //如果相等即数组只有一个数
            return 0;

        boolean[] hasNum = new boolean[len + 1]; //当前及以下就代表构建桶，分别为每个桶构建了记录是否有值，桶内最小值，桶内最大值
        int[] mins = new int[len + 1];
        int[] maxs = new int[len + 1];
        int bid = 0;
        for (int i = 0; i < len; i++) {
            bid = bucket(nums[i], len, min, max);
            mins[bid] = hasNum[bid] ? Math.min(mins[bid], nums[i]) : nums[i];  //如果桶内有值，就比较两个值大小，小的存到该桶的min位
            maxs[bid] = hasNum[bid] ? Math.max(maxs[bid], nums[i]) : nums[i];  //同上，表示当前桶的最大值
            hasNum[bid]=true;
        }
        int result = 0; //全局最大差值
        int lastMax = maxs[0];  //上一个桶的最大值
        for (int i = 1; i < len; i++) {  //从第二个桶（索引为1）开始遍历，因为0存放了上一个桶的最大值
            if (hasNum[i]) {
                // mins[i]-lastMax 表示的是上一个非空桶与当前非空桶的 两个桶间差值（两个桶间差值计算：当前桶的最小值和上一个桶的最大值，必须是这样才能保证相邻）
                result = Math.max(result, mins[i] - lastMax); //将当前相差最大值保存
                lastMax = maxs[i];  //将上一个桶的最大值保存
            }
        }
        return result;
    }

    /**
     * 用来划分桶的范围，可以使99个数放10个桶
     * 当前理解：max-min就是桶中值的范围，那它和len约去之后就是n个桶平均分成几份！（如110-10，分成5个桶（len=5），就是桶的范围是1-20，21-40..）
     * 然后 “当前数-最小值” 除以“每个桶范围”就能得到 当前数 大概占据 当前所有桶的哪个范围 （30/20 放在2号桶）
     * 可以改成 (num-min)/((max-min)/len) 更好理解
     * @param num 当前数组的值
     * @param len 数组的索引
     * @param min 当前数组最小值
     * @param max 当前数组最大值
     * @return 对应桶的下标
     */
    public static int bucket(long num, long len, long min, long max) {

        return (int) ((num - min) * len / (max - min));     //不理解，但是经过这计算之后就直接返回当前数应该在桶的哪个位置
    }

    public static void main(String[] args) {
        int[] arr = {2, 4, 7, 8};
        System.out.println(maxGap(arr));
    }
}
