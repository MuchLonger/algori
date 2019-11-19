package graph.graphStructure;

import java.util.ArrayList;

/**
 * @description: 图的节点
 * @Time: 2019/11/18 21:46
 */
public class Node {
    public int value; // 这是哪个节点（就是表示当前节点的身份）
    public int in; // 入度（有几条边到达）
    public int out; // 出度（有几条边出去）
    public ArrayList<Node> nexts; // 当前图节点的所有下个节点（指向的下个节点）
    public ArrayList<Edge> edges;  // 当前图节点的所有边

    public Node(int value) {  // 这个
        this.value = value;
        in = 0;
        out = 0;
        nexts = new ArrayList<>();
        edges = new ArrayList<>();
    }

}
