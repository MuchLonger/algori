package learning.Day5;


import java.util.HashMap;
import java.util.Map;

/**
 * @description: 前缀树，比如三个字符串"cqy" "cyy" cqq""cqp"就能构建出如下树
 *     root
 *    c /
 *   q /  \y      这样的数，注意字母是在路径上，不是节点上（我抠了好久才抠再路上的），节点上可以自定义值：
 * y/ |p \q          比如说经过这条路径几次。以这条路径为尾节点的次数等。
 * <p>
 * 如何实现将字母存放在路径上：在当前节点上存放一个容器（如Map，数组等），容器的索引就是这个字母，就实现了存放再路径上。
 * @Time: 2019/11/8 23:35
 */

public class TrieTree {
    public static class TrieNode {
        public int walkTime;  //经过这条路径节点的次数（用来求以当前字符串为前缀的个数）
        public int endTime;  //以这条路径为终点的个数（用来求是否存在“当前字符串”，或者当前数中“当前字符串”的个数）
        public Map<Integer, TrieNode> nextPath;  //路径，使用map来实现向下保存。（就是拿来存放前缀值的）

        public TrieNode() {
            walkTime = 0;
            endTime = 0;
            nextPath = new HashMap<>();
        }
    }

    public static class Trie {
        private TrieNode root; //根路径

        public Trie() {
            root = new TrieNode();
        }

        /**
         * 传入一个String创建前缀树
         *
         * @param word
         */
        public void insert(String word) {
            if (word == null)
                return;
            char[] singleCharArr = word.toCharArray();

            TrieNode node = root;   //构建前缀树
            int hashcode = 0;
            for (int i = 0; i < singleCharArr.length; i++) {
                hashcode = (int) singleCharArr[i];  //直接存入int类型（当成路径）
                if (node.nextPath.get(hashcode) == null) {  //如果没有这条路径就新建一条新路径，并延续路径
                    node.nextPath.put(hashcode, new TrieNode());
                }
                node = node.nextPath.get(hashcode); //获得下一个节点，如果没有就新建，所以一定会有值。
                node.walkTime++;  //经过当前节点的次数加一。（因为是从root开始，所以一次都不会少）
            }
            node.endTime++;  //以当前word为节点的树加一
        }

        /**
         * 查找树中为word的个数，简单来说就是插入这个word多少次
         *
         * @param word
         * @return 树中为word的个数
         */
        public int search(String word) {
            if (word == null)
                return 0;
            char[] charArr = word.toCharArray();
            TrieNode node = this.root;     //得到当前树结构
            int hashcode = 0;
            for (int i = 0; i < charArr.length; i++) {
                hashcode = charArr[i];
                if (node == null || node.nextPath == null || node.nextPath.get(hashcode) == null) {  //找不到第一个字符开头直接返回0
                    return 0;
                }
                node = node.nextPath.get(hashcode);  //继续向下查找
            }
            return node.endTime;  //直接返回次数（因为在insert内就计算好了）
        }

        /**
         * 删除对应的前缀，
         * 需要考虑：
         * 1）如果找不到，不删除
         * 2）如果当前经过路径的还有别的值，那路径不删除，仅经过路径次数减一，一旦遇到的walkTime--之后=0就直接置空（删除）
         *
         * @param word
         */
        public void delete(String word) {
            if (search(word) == 0)
                return;  //找不到就退出
            char[] charArr = word.toCharArray();
            TrieNode node = this.root;
            int index = 0;
            for (int i = 0; i < charArr.length; i++) {
                index = (int) charArr[i];
                if (--node.nextPath.get(index).walkTime == 0) {  //先将下一条路径的经过次数减一，如果下一条路径被经过的次数等于0，那就代表没有前缀经过这条路了，直接将下一个节点的路径置空，这样就实现了删除。
                    node.nextPath = new HashMap<>(); //当前节点的下一条路径置空，！！！注意这里的置空意思是重新新建一个HashMap，不然会出错
                    return;  //直接退出
                }
                node = node.nextPath.get(index);
            }
            node.endTime--;  //能进行到这一步的代表是的 “被删除的节点”后面还有“可用的节点”（即经过这个节点的WalkTime大于1,），所以不用置空只需要将entTime--就好（因为路径还需要存在，仅仅只是以它为终止次数减少一次而已）
        }

        /**
         * 以word为前缀的串有多少（就是经过的次数，即求word最后一个单词的walkTime）
         *
         * @param word
         * @return
         */
        public int prefixNumber(String word) {
            if (word == null)
                return 0;
            char[] charArr = word.toCharArray();
            TrieNode node = this.root;
            int index = 0;  //map的key
            for (int i = 0; i < charArr.length; i++) {
                index = (int) charArr[i];
                if (node.nextPath.get(index).nextPath == null) {  //如果没有以它为前缀的
                    return 0;
                }
                node = node.nextPath.get(index);
            }
            return node.walkTime;  //返回经过该路径的次数
        }


        public static void display(TrieNode root) {
            if (root == null)
                return;
            root.nextPath.forEach((key, value) -> System.out.println("<" + key + "," + value + ">"));
            root.nextPath.forEach((key, value) ->
                    display(root.nextPath.get(key))
            );
        }
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        System.out.println(trie.search("zuo"));
        trie.insert("zuo");
        System.out.println(trie.search("zuo"));
        trie.delete("zuo");
        System.out.println(trie.search("zuo"));
        trie.insert("zuo");
        trie.insert("zuo");
        trie.delete("zuo");
        System.out.println(trie.search("zuo"));
        trie.delete("zuo");
        System.out.println(trie.search("zuo"));
        trie.insert("zuoa");
        trie.insert("zuoac");
        trie.insert("zuoab");
        trie.insert("zuoad");
        trie.delete("zuoa");
        System.out.println(trie.search("zuoa"));
        System.out.println(trie.prefixNumber("zuo"));

    }
}
