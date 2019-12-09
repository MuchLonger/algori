package codeBook.second_part_LinkArray;

/**
 * @description:
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
