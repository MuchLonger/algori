package graph.graphStructure;


/**
 * @description: 图的生成器：给定一个二维数组，生成一个图。二维数组的一维数组的长度为3，分别有：边的权重，form，to
 * 如[ {7 1 2},{8,2,3} ]指向的图是如下，1到2边权为7。2到3边权为8
 *    7    8
 * 1---》2---》3
 *
 *
 * @Time: 2019/11/18 21:51
 */
public class GraphGenerator {
    /**
     * 根据一个二维数组生成一个图，二维数组的一个一维数组就代表一对节点（权重和form和t）
     * @param matrix
     * @return
     */
    public static Graph createGraph(Integer[][] matrix){
        Graph graph=new Graph();
        for (int i = 0; i < matrix.length; i++) {
            Integer weight=matrix[i][0];  //固定：一维数组第1个位置放的是权重
            Integer from=matrix[i][1];  //固定：一维数组第2个位置放的是form节点
            Integer to=matrix[i][2]; //固定：一维数组第3个位置放的是to节点

            //以下是如果图中没有from节点或to节点就新建，值分别就是上面from和to的值
            if(!graph.nodes.containsKey(from)){
                graph.nodes.put(from,new Node(from));
            }
            if(!graph.nodes.containsKey(to)){
                graph.nodes.put(to,new Node(to));
            }

//            从图中取得当前节点，用于赋值
            Node fromNode=graph.nodes.get(from);
            Node toNode=graph.nodes.get(to);
            Edge newEdg=new Edge(weight,fromNode,toNode);

            fromNode.nexts.add(toNode); //将fromNode的下个节点置为toNode
            fromNode.out++;  //入度出度都加一
            fromNode.in++;
            fromNode.edges.add(newEdg); //fromNode的边置为当前边
            graph.edges.add(newEdg);  // 存入 当前图的所有边 的Set中
        }
        return graph;
    }
}
