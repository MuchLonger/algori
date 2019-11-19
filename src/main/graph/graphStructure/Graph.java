package graph.graphStructure;


import java.util.HashMap;
import java.util.HashSet;

/**
 * @description: 构建一整图对象
 * @Time: 2019/11/18 21:45
 */
public class Graph {
    // 图的所有节点。key表示节点的from和to。如从1-2，存入的就是key:1,value:Node1.key:2,value:Node2
    public HashMap<Integer, Node> nodes;
    public HashSet<Edge> edges; // 图的所有边

    public Graph() {
        nodes = new HashMap<>();
        edges = new HashSet<>();
    }

}
