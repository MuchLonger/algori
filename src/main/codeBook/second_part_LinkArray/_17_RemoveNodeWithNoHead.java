package codeBook.second_part_LinkArray;

/**
 * @description: 给定一个节点但不给定这个节点的头结点，要求删除这个节点
 *      解法：1->2->3->null      删除2，就是将2的位置改成3。
 *  这样有一个弊端，就是3（最后一个节点）不能被更改，因为下一个节点是null，3不能赋值为null，（即使赋值了 2指向的也是3而不是null），因为找不到2来指向null。
 *  即使将3置为null也无济于事，因为2.next指向的就是3，不管3是什么，都会是3。
 * @Time: 2019/12/11 17:41
 */
public class _17_RemoveNodeWithNoHead {
    public static void removeNodeWithNoHead(Node node){
        if (node == null) {
            return ;
        }
        if(node.next==null){
            // 之所以最后一个节点不能被删除
            throw new RuntimeException("最后一个节点不能被删除");
        }
        node.value=node.next.value;  // 全都赋值过去。
        node=node.next;
    }


    public static void main(String[] args){

    }
}
