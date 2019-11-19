package graph.graphStructure;


/**
 * @description: 图的边
 * @Time: 2019/11/18 21:46
 */
public class Edge {
    public int weight; // 边的权重
    public Node from; // 边的起点（从哪）
    public Node to;  // 边的终点（到哪）

    public Edge(int weight, Node from, Node to) {
        this.weight = weight;
        this.from = from;
        this.to = to;
    }


}
