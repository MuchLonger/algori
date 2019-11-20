package learning.graph;

import learning.graph.graphStructure.Graph;
import learning.graph.graphStructure.Node;

import java.util.*;

/**
 * @description: 图的拓扑结构，拓扑结构阐述如下：（注意：图必须不能有环路且必须是有向图，(因为无向图就是一个环路））
 *       1           注意，箭头上往上的（
 *   ↗ ↑ ↖
 *  2   3  4      如左图：1依赖234, 2依赖7，3依赖57,4依赖6，
 * ↑↗ ↑  ↑    拓部结构的打印顺序就是先打印那些最底层被依赖的：如7 5 6，没有被任何人依赖。（即入度为0就先输出）
 *  7   5   6
 * @Time: 2019/11/19 22:08
 */
public class TopologySort {

    /**
     * 输出拓扑结构
     * 思路：先储存所有节点的度，如果遇到入度为0的就加入队列，然后循环队列，将入度为0的先存入输出队列，
     * 然后将其它所有节点的入度-1（即去掉入度为0之后，又等于0的就是下一层）一直循环
     * @param graph
     * @return
     */
    public static List<Node> sortedTopology(Graph graph){
        HashMap<Node,Integer> inMap=new HashMap<>();
        Queue<Node> zeroInQueue=new LinkedList<>();
        for (Node node : graph.nodes.values()) {  //遍历所有节点，将所有节点的入度存入Map，将入度为0的存入队列
            inMap.put(node,node.in);
            if (node.in == 0) {
                zeroInQueue.add(node);
            }
        }
        List<Node> result=new ArrayList<>();
        while(!zeroInQueue.isEmpty()){  // 循环队列
            Node cur=zeroInQueue.poll();  //取出入度为0的节点
            result.add(cur);
            for (Node next : cur.nexts) {  // 遍历入度为0的所有出度节点：注意思维不要乱：因为是将入度为0的节点入队，再遍历当前节点的所有“出度”节点
                inMap.put(next,inMap.get(next)-1); // 将所有出度节点（next）的入度-1
                if (inMap.get(next) == 0) {  // 如果右发现了0，就再次入队
                    zeroInQueue.add(next);
                }
            }
        }
        return result;
    }

}
