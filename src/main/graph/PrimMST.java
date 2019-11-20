package graph;

import graph.graphStructure.Edge;
import graph.graphStructure.Graph;
import graph.graphStructure.Node;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @description: 生成最小生成树算法②，使用点来判断。
 *         1
 *    ↗ ②\   ↖
 * ① \    \    \②
 *    \ ① 2  ②\        思路：先从v1开始，这样能解锁1 2 2三条边，选择最小的边-》1，判断v3是否已经连接过，如果没连接过就和v1连接
 *    \ ↗ ③↖ \     然后v3连接进来之后又带入新的边1，3两条边再加上1的2,2边，选择1。v2没加入过，所以也连上了。然后从！！“所有”边中继续挑最小，如果没连过就重复上面步骤
 *     3 ----- 4
 * @Time: 2019/11/19 23:49
 */
public class PrimMST {

    public static Set<Edge> primMST(Graph graph) {
        // 节点的所有边按大小排序
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>((o1, o2) -> o1.weight - o2.weight);

        Set<Node> set = new HashSet<>();  //用来判断节点是否加入过
        Set<Edge> result = new HashSet<>(); // 储存输出的边
        Node startNode = graph.nodes.values().iterator().next(); // 获取第一个节点
        if (!set.contains(startNode)) { // 如果Set内不存在才加入
            set.add(startNode);
            for (Edge edge : startNode.edges) { // 遍历当前节点的所有边加入小根堆
                priorityQueue.add(edge);
            }
            while (!priorityQueue.isEmpty()) {
                Edge edge = priorityQueue.poll(); //取当前节点Set的最小边，注意：比对的是节点Set的所有边
                Node to = edge.to; //注意取的是边的to节点（from没有意义）
                if (!set.contains(to)) { // 如果当前节点是新节点（没有取过）
                    set.add(to); //标记为已取
                    result.add(edge);
                    for (Edge toNextEdge : to.edges) { //再次把 所有边 加入小根堆
                        priorityQueue.add(toNextEdge);
                    }
                }
            }
        }
        return result;
    }

}
