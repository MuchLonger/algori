package codeBook.second_part_LinkArray;

/**
 * @description: 链表一定要注意移动，while内一定要移动。
 * 我自己总结：1）记录当前节点，2）操作，3）记录节点移动
 * @Time: 2019/12/4 17:35
 */
public class Node {
    public Node next;
    public int value;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "next=" + next +
                ", value=" + value +
                '}';
    }
}
