package Day4;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * @description: 并查集：
 * 解决两个问题：
 * 1）给定两个节点，判断两个节点是否是同个列表中的节点
 * 2）给定两个节点，将两个节点合并
 * 可以直接使用Set时间，但是合并时时间复杂度较大。
 * @Time: 2019/11/7 22:46
 */
public class UnionFind {
    public static class Node {
        //随便实现，
    }

    /**
     * 并查集
     * 基本思路： 初始化时每个节点都是自己的头结点，求并集的时候会合并，这时 节点数较少的链 会连向 节点数较多的链。
     * 求 两个节点是否在一个节点链中，就只需向上找father节点，如何头结点一致，就在一条链上。
     * 只需要接受一个节点有多个链就好理解。
     */
    public static class UnionFindSet {
        //        保存的map的 key是当前节点 value是当前节点的父节点
        public HashMap<Node, Node> fatherMap;
        //        当前节点所在的集合一共有多少个节点，key：Node value：Node占当前链几个节点
        public HashMap<Node, Integer> sizeMap;

        //初始化的时候传入一个Node的list
        public UnionFindSet(List<Node> nodes) {
            makeSets(nodes);
        }

        public void makeSets(List<Node> nodes) {
            fatherMap = new HashMap<>();
            sizeMap = new HashMap<>();
            for (Node node : nodes) {
                fatherMap.put(node, node);  //当前节点的父节点是自己
                sizeMap.put(node, 1);   //当前节点所在链的节点只有自己（1个）
            }
        }

        /**
         * 实例化好当前节点到头结点的路径（递归版）
         *
         * @param node
         * @return
         */
        private Node findHead(Node node) {
            Node father = fatherMap.get(node);     //当node为空时就会退出递归，查找当前node的父节点（之所以是这样，是因为每个节点都是穿自己的头结点）
            if (father != node) {   //递归向上查找节点。找到最顶部节点。只有当 当前node等于父节点才结束（就是找到了父节点），
                father = findHead(father);
            }
            fatherMap.put(node, father);  //这还在递归的环节，先将当前节点找到的父节点（father），存入到map中并原封不动的返回，因为还在递归环节，子节点的father也指向为当前father。这就实现了以node向上的Node的父节点都是father节点
            return father;
        }
        /**
         * 实例化好当前节点到头结点的路径（非递归版）
         *
         * @param node
         * @return
         */
        private Node findHeadNoRecur(Node node) {
            Stack<Node> stack=new Stack<>();
            Node cur=node;  //记录当前节点
            Node parent=fatherMap.get(cur);
            while(cur!=parent){  //不等于父节点，就代表向上还有节点，
                stack.push(cur);  //沿途所有节点放入栈中，到时再取出
                cur=parent;
                parent=fatherMap.get(cur);  //继续向前
            }
            while (!stack.isEmpty()) {
                fatherMap.put(stack.pop(),parent);  //取出父节点
            }
            return parent;
        }

        /**
         * 判断是否来自一个集合。因为如果来自一个集合最后头节点一定会相同，如果头结点不相同那就不是同一个集合。
         *
         * @param a
         * @param b
         * @return
         */
        public boolean isSameSet(Node a, Node b) {
            return findHead(a) == findHead(b);
        }

        /**
         * 将两个节点融合。这里还有一个优化没实现，就是每次插入都会沿着当前节点向上拆解，然后拆出来的节点重新连向头结点（没实现）
         *
         * @param a
         * @param b
         */
        public void union(Node a, Node b) {
            if (a == null || b == null)
                return;

            Node aHead = findHead(a); //找到a的头结点
            Node bHead = findHead(b); //找到b的头结点
            if (aHead != bHead) {  //头结点不一样才会融合（不然合个屁）
                int aNodeSize = sizeMap.get(aHead);
                int bNodeSize = sizeMap.get(bHead);
                if (aNodeSize <= bNodeSize) {
                    fatherMap.put(aHead, bHead);  //当a链的长度小于b链的长度的时候，就将a链置为b的头结点。少的连向多的
                    sizeMap.put(bHead, aNodeSize + bNodeSize);
                } else {
                    fatherMap.put(bHead, aHead);  //如果a链比较长，就和上面相反，将a链置为b的头节点
                    sizeMap.put(aHead, aNodeSize + bNodeSize);
                }
            }
        }

    }
}
