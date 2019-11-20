package learning.Day4;


import java.util.HashMap;

/**
 * @description: 随机常量池，实现三个功能：
 * 1） insert(key) 不重复的将一个key放入该结构 （假设放入2，如果存在2就可以不放入）
 * 2） delete(key) 将某个key移出
 * 3） getRandom() 等概率的随机返回结构中的一个key
 * 以上三个方法的时间复杂度都是O（1）
 * 使用两个Map，存入的时候map1存入 key和size，map2存入 size和key。获取random就能通过random函数随机获取。
 * 删除的时候需要将 删除的值 和 最后一个值 位置互换再删除，不然会导致结构中有空隙（如删除7，7位置上就没有东西了）
 * 经过深思熟虑，发现还是老师的方法好用
 * @Time: 2019/11/4 21:57
 */
public class RandomPool {
    public static class Pool<K> {
        private HashMap<K, Integer> keyIndexMap;   //key,index
        private HashMap<Integer, K> indexKeyMap;   //index,key
        private int size;   //大小，有几个值size就为几，如1,2,3 size就为3

        public Pool() {  //初始化
            this.keyIndexMap = new HashMap<>();
            this.indexKeyMap = new HashMap<>();
            size = 0;
        }


        /**
         * 同时插入两个map，只有在不存在key的时候才插入
         * 如 存入 （cqy 1）（1 cqy）
         *
         * @param key
         */
        public void insert(K key) {
            if (!this.keyIndexMap.containsKey(key)) {
                this.keyIndexMap.put(key, this.size);
                this.indexKeyMap.put(this.size, key);
                this.size++;  //下一个位置
            }
        }


        /**
         * 等概率的获取随机值
         *
         * @return
         */
        public K getRandom() {
            if (size != 0) {
                int randomIndex = (int) Math.random() * this.size;  // [0,size) 范围内求随机值
                return this.indexKeyMap.get(randomIndex);
            }
            return null;
        }

        /**
         * 删除一个指定的key。
         * 注意，因为要保证当前数据结构不能有空隙（如果出现空隙就会出现 随机出null 的情况，这样是不被允许的）
         * 做法：查找出最后一个key，再查找出要删除的key的index值。将（最后一个key index）存入map1，（index，最后一个key）存入map2
         * 然后remove要删除的key，remove size。
         *
         * @param key
         */
        public void delete(K key) {
            if (keyIndexMap.containsKey(key)) {
                int delKeyIndex = this.keyIndexMap.get(key);  //记录要删除的key所处的索引（用于与最后一个互换索引）
                int lastIndex = --this.size;   // 得到最后一个key的下标。注意是--size，因为insert的时候size是++的
                K lastIndexKey = this.indexKeyMap.get(lastIndex);     //得到当前最后一个map的Key。

                this.keyIndexMap.put(lastIndexKey, delKeyIndex);  //将map1 “最后一个key”的value修改成“要删除的key的索引”
                this.indexKeyMap.put(delKeyIndex, lastIndexKey);  //将map2 “要删除key的索引”的value修改成 “最后一个key”

                this.keyIndexMap.remove(key);  //删除key
                this.indexKeyMap.remove(lastIndex);  //删除

            }
        }

    }

    public static void m(long index) {

    }

    public static void main(String[] args) {
        Pool<String> pool = new Pool<String>();
        pool.insert("c");
        pool.insert("q");
        pool.insert("y");
        System.out.println(pool.size);
        pool.delete("c");
        pool.delete("q");
        System.out.println(pool.getRandom());
        System.out.println(pool.getRandom());
        System.out.println(pool.getRandom());
        System.out.println(pool.getRandom());
        System.out.println(pool.getRandom());
        System.out.println(pool.getRandom());

        long[] bitArr = new long[1000];  //一个long64比特，这个数组就能存放64 000 比特
        long index = 30000;   //如果想把第30000个bit上 置1
        int longIndex = (int) (index / 64);   //获取当前index 位于第几个long中
        int bitIndex = (int) (index % 64);   //获取当前index 位于long内第几个bit中
//        综上就能精确获取到index位于数组的第几个比特位上

//        左移，后面补0。右移，前面添0。
//        注意这个操作：首先 1 << bitIndex 即1左移bitIndex位，实现置一。之所以是左移：因为比特内使用的是从右到左的顺序，(0000) (0001)左移一位。
//        然后再与数组的 第longIndex个值 做“或”运算，数组longIndex上的其他位上的值不变，如果bitIndex位上为0，就会将其置1。
        bitArr[longIndex] = (bitArr[longIndex] | 1 << bitIndex);
    }

}
