package graph;

import graph.graphStructure.Node;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * @description: 图的深入优先遍历：
 *       1
 *   |   |  \
 *  2 -> 3  4        如左的图：遍历后是 1 2 7 3 5 4 6，或1 2 3 7 5 4 6..等，都可以称作深度优先遍历
 *  | /  |   |     具体定义就是：一定把子节点的所有next节点走完才会回到父节点，比如只有把2的所有节点（3,7）都走完才会回到1。3也是7也是
 *  7   5    6
 * @Time: 2019/11/18 22:44
 */
public class DFS {
    /**
     * 深度优先遍历
     *
     * 思路：将首节点存入栈中，然后输出首节点并记录。然后通过栈取出这个首节点，遍历这个首节点的所有子节点，
     * 如果遇到没输出过的就先记录这个父节点再记录这个子节点（具体操作就是存入栈），然后输出这个没输出过的，然后break循环，继续取出上次记录的子节点，再次重复上面步骤实现深度优先遍历
     * @param node
     */
    public static void dfs(Node node){
        if(node ==null)
            return;
        Stack<Node> stack=new Stack<>();
        Set<Node> recordNodeSet=new HashSet<>();
        stack.add(node);  //使用栈来存储
        recordNodeSet.add(node);  // 记录节点是否进入
        System.out.println(node.value); // 直接输出首节点
        while(!stack.isEmpty()){
            Node cur=stack.pop();  // 把栈顶元素取出来
            for (Node next : cur.nexts) { // 遍历所有的next节点
                // 如果遇到没有输出过的“node子节点”就将当前节点存入栈（用来记录，子节点输出完之后需要回退）并记录已输出
                // 然后再将 “node子节点”存入栈，（下次pop的就是它，就实现了一直输出子节点的目的）
                if(!recordNodeSet.contains(next)){
                    stack.push(cur); // 存入next的父节点（即cur）
                    stack.push(next); //存入next（下次pop的就是它）
                    recordNodeSet.add(next); // 记录已输出
                    System.out.println(next.value); // 输出next的值
                    break; // 注意！！！只要遇到一个没有输出过的值中断，转而深度遍历 这个没有输出过的值的子节点
                }
            }
        }
    }
}
