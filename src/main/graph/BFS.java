package graph;

import graph.graphStructure.Node;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * @description: 图的宽度（广度）优先遍历：
 *      1
 *   |  | \
 *  2 ->3->4       如左的图：遍历后是 1 2 3 4 7 5 6
 *  | \ |  |
 *  7  5  6
 * @Time: 2019/11/18 22:19
 */
public class BFS {
    /**
     * 广度优先遍历：
     * 思路：使用一个队列存放下次要输出的值，再使用一个set存已经进入过队列的值（如果进入过队列就不输出，避免重复输出）
     *
     * 具体过程：从头结点开始输出，然后将头结点的所有没有输出过的next节点存入队列，然后再输出
     * @param node
     */
    public static void bfs(Node node){
        if(node==null)
            return ;
        Queue<Node> queue=new LinkedList<>();
        Set<Node> recordNodeSet=new HashSet<>();
        queue.add(node);
        recordNodeSet.add(node);  //记录是否进入过队列，如果进入过就不再打印
        while(!queue.isEmpty()){
            Node cur=queue.poll();
            System.out.println(cur.value);
            for (Node next : cur.nexts) {  //遍历头结点的所有后续节点
                if(!recordNodeSet.contains(next)){  // 只有Set内不存在的值（即没有输出过）才会被加入到队列（避免重复输出）
                    queue.add(next);
                    recordNodeSet.add(next);
                }
            }
        }
    }
}
